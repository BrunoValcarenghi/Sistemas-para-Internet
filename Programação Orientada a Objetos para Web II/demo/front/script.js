const API_BASE = "http://localhost:8080/avaliador-de-projetos";

function mostrarAba(nomeAba) {
    document.querySelectorAll('.aba').forEach(aba => aba.classList.remove('active'));
    document.getElementById(`aba-${nomeAba}`).classList.add('active');

    if (nomeAba === 'alunos') carregarAlunos();
    if (nomeAba === 'avaliadores') carregarAvaliadores();
    if (nomeAba === 'projetos') {
        carregarProjetos();
        carregarOptionsSelects();
    }
}

async function carregarAlunos() {
    const res = await fetch(`${API_BASE}/alunos`);
    const alunos = await res.json();
    const tabela = document.getElementById('tabela-alunos');
    tabela.innerHTML = '';

    alunos.forEach(aluno => {
        tabela.innerHTML += `
            <tr>
                <td>${aluno.nome}</td>
                <td>${aluno.matricula}</td>
                <td>${aluno.email}</td>
                <td><button onclick="deletarItem('alunos', '${aluno.uuid}')">Deletar</button></td>
            </tr>
        `;
    });
}

document.getElementById('form-aluno').addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
        nome: document.getElementById('aluno-nome').value,
        matricula: document.getElementById('aluno-matricula').value,
        email: document.getElementById('aluno-email').value
    };

    await fetch(`${API_BASE}/alunos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    e.target.reset();
    carregarAlunos();
});

async function carregarAvaliadores() {
    const res = await fetch(`${API_BASE}/avaliadores`);
    const avaliadores = await res.json();
    const tabela = document.getElementById('tabela-avaliadores');
    tabela.innerHTML = '';

    avaliadores.forEach(av => {
        tabela.innerHTML += `
            <tr>
                <td>${av.nome}</td>
                <td>${av.especialidade}</td>
                <td><button onclick="deletarItem('avaliadores', '${av.uuid}')">Deletar</button></td>
            </tr>
        `;
    });
}

document.getElementById('form-avaliador').addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
        nome: document.getElementById('avaliador-nome').value,
        especialidade: document.getElementById('avaliador-especialidade').value
    };

    await fetch(`${API_BASE}/avaliadores`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    e.target.reset();
    carregarAvaliadores();
});

async function carregarOptionsSelects() {
    const [resAlunos, resAvaliadores] = await Promise.all([
        fetch(`${API_BASE}/alunos`),
        fetch(`${API_BASE}/avaliadores`)
    ]);

    const alunos = await resAlunos.json();
    const avaliadores = await resAvaliadores.json();

    const selectAluno = document.getElementById('select-aluno');
    const selectAvaliador = document.getElementById('select-avaliador');

    selectAluno.innerHTML = '<option value="">Selecione um Aluno...</option>';
    alunos.forEach(a => {
        selectAluno.innerHTML += `<option value="${a.uuid}">${a.nome} (${a.matricula})</option>`;
    });

    selectAvaliador.innerHTML = '<option value="">Selecione um Avaliador...</option>';
    avaliadores.forEach(a => {
        selectAvaliador.innerHTML += `<option value="${a.uuid}">${a.nome}</option>`;
    });
}

async function carregarProjetos() {
    const res = await fetch(`${API_BASE}/projetos`);
    const projetos = await res.json();
    const tabela = document.getElementById('tabela-projetos');
    tabela.innerHTML = '';

    projetos.forEach(p => {
        tabela.innerHTML += `
            <tr>
                <td>${p.titulo}</td>
                <td>${p.descricao}</td>
                <td>${p.aluno ? p.aluno.nome : 'N/A'}</td>
                <td>${p.avaliador ? p.avaliador.nome : 'N/A'}</td>
                <td><button onclick="deletarItem('projetos', '${p.uuid}')">Deletar</button></td>
            </tr>
        `;
    });
}

document.getElementById('form-projeto').addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
        titulo: document.getElementById('projeto-titulo').value,
        descricao: document.getElementById('projeto-descricao').value,
        alunoUuid: document.getElementById('select-aluno').value,
        avaliadorUuid: document.getElementById('select-avaliador').value
    };

    await fetch(`${API_BASE}/projetos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    e.target.reset();
    carregarProjetos();
});

async function deletarItem(recurso, uuid) {
    if (confirm("Tem certeza que deseja deletar este registro?")) {
        await fetch(`${API_BASE}/${recurso}/${uuid}`, { method: 'DELETE' });
        mostrarAba(recurso);
    }
}

mostrarAba('alunos');
create table sala(

    id_sala serial primary key,
    nome varchar(50) unique not null,
    qnt_aluno int not null,
    qnt_pc int,
    ano int not null,
    area decimal(10,2) not null,
    situacao varchar(30) not null

);
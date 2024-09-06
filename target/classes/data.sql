create table hotel (
    id bigint auto_increment primary key,
    nome varchar(255) not null,
    descricao text,
    avaliacao double not null,
    localizacao varchar(255) not null
);

create table usuario (
    id BIGINT auto_increment primary key,
    nome varchar(255) not null,
    email varchar(255) not null unique,
    forma_de_pagamento varchar(255) not null
);

create table quarto (
    id bigint auto_increment primary key,
    descricao text,
    data_check_in date,
    data_check_out date,
    avaliacao double not null,
    preco decimal(10, 2) not null,
    quantidade_hospede INT,
    usuario_id bigint,
    disponivel boolean not null default true,
    hotel_id bigint,
    foreign key (usuario_id) references usuario(id),
    foreign key (hotel_id) references hotel(id) on delete cascade
);

create table hospede (
    id bigint auto_increment primary key,
    nome varchar(255),
    quantidade int,
    forma_de_pagamento varchar(255),
    data_check_in date,
    data_check_out date,
    quarto_id bigint,
    constraint fk_quarto_hospede foreign key (quarto_id) references quarto(id)
);

insert into usuario (id, nome, email, forma_de_pagamento) values
(1, 'Chiquinho Gomes', 'chiquinho.gomes@example.com', 'Cartão de Crédito'),
(2, 'Rei da Cacimbinha', 'vanessaselva1357@gmail.com', 'Boleto Bancário'),
(3, 'Anaudite Fernandes', 'anaudite.fernandes@example.com', 'Pix');

insert into hotel (id, nome, descricao, avaliacao, localizacao) values
(1, 'Hotel Sunshine', 'Um hotel acolhedor com vista para o mar.', 4.5, 'Rio de Janeiro'),
(2, 'Mountain Retreat', 'Hotel na montanha com vista panorâmica.', 4.7, 'Campos do Jordão'),
(3, 'Urban Escape', 'Hotel moderno no centro da cidade.', 4.2, 'São Paulo'),
(4, 'Beachfront Paradise', 'Hotel luxuoso à beira-mar.', 4.9, 'Florianópolis'),
(5, 'Lakeside Inn', 'Hotel tranquilo à beira do lago.', 4.3, 'Gramado'),
(6, 'Hotel Rio Mar', 'Um hotel das celebridades.', 3.5, 'Rio de Janeiro');

insert into quarto (id, descricao, avaliacao, preco, quantidade_hospede, hotel_id) values
(1, 'Quarto Deluxe com Vista para o Mar', 4.6, 300.00, 2, 1),
(2, 'Quarto Standard com Varanda', 4.2, 200.00, 2, 1),
(3, 'Suite Presidencial', 4.8, 500.00, 4, 1);

insert into quarto (id, descricao, avaliacao, preco, quantidade_hospede, hotel_id) values
(4, 'Chalé com Vista para a Montanha', 4.7, 400.00, 2, 2),
(5, 'Quarto Standard', 4.4, 250.00, 2, 2),
(6, 'Suite Master', 4.9, 600.00, 3, 2);

insert into quarto (id, descricao, avaliacao, preco, quantidade_hospede, hotel_id) values
(7, 'Quarto Executivo', 4.3, 350.00, 2, 3),
(8, 'Quarto Luxo', 4.5, 450.00, 3, 3),
(9, 'Suite Business', 4.6, 550.00, 4, 3);

insert into quarto (id, descricao, avaliacao, preco, quantidade_hospede, hotel_id) values
(10, 'Quarto de Frente para o Mar', 4.9, 800.00, 2, 4),
(11, 'Quarto com Varanda Privativa', 4.7, 650.00, 3, 4),
(12, 'Suite Nupcial', 5.0, 1000.00, 4, 4);

insert into quarto (id, descricao, avaliacao, preco, quantidade_hospede, hotel_id) values
(13, 'Quarto com Vista para o Lago', 4.4, 350.00, 3, 5),
(14, 'Suite Familiar', 4.6, 500.00, 4, 5),
(15, 'Quarto Standard', 4.3, 300.00, 3, 5);

insert into quarto (id, descricao, avaliacao, preco, quantidade_hospede, hotel_id) values
(16, 'Quarto simples', 4.0, 350.00, 2, 6),
(17, 'Suite Familiar', 4.6, 500.00, 4, 6),
(18, 'Quarto Standard', 4.3, 300.00, 3, 6);
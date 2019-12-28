insert into categoria(nome) values('informática');
insert into categoria(nome) values('escritório');

insert into produto(nome, preco) values('Computador', 4800.00);
insert into produto(nome, preco) values('Impressora', 320.00);
insert into produto(nome, preco) values('Mouse', 75.00);

insert into produto_categoria(produto_id, categoria_id) values(1, 1);
insert into produto_categoria(produto_id, categoria_id) values(2, 1);
insert into produto_categoria(produto_id, categoria_id) values(2, 2);
insert into produto_categoria(produto_id, categoria_id) values(3, 1);

insert into estado(nome) values('Minas Gerais');
insert into estado(nome) values('São Paulo');

insert into cidade(nome, estado_id) values('Uberlândia', 1);
insert into cidade(nome, estado_id) values('São Paulo', 2);
insert into cidade(nome, estado_id) values('Campinas', 2);

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

insert into cliente(cpf_ou_cnpj, email, nome, tipo_cliente)
values('12345678912', 'maria@gmail.com', 'Maria Silva', 1);

insert into telefone(cliente_id, telefones) values(1, '91234-5678');
insert into telefone(cliente_id, telefones) values(1, '92345-6789');

insert into endereco(bairro, cep, complemento, logradouro, numero, cidade_id, cliente_id)
values('Jardim', '11111-111', 'Apto 303', 'Rua Flores', 300, 2, 1);

insert into endereco(bairro, cep, complemento, logradouro, numero, cidade_id, cliente_id)
values('Centro', '11111-112', 'Sala 800', 'Avenida Matos', 105, 3, 1);


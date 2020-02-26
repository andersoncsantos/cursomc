insert into categoria(nome) values('Informática'); -- 1
insert into categoria(nome) values('Escritório'); -- 2
insert into categoria(nome) values('Smartphones'); -- 3
insert into categoria(nome) values('SmartTVs'); -- 4
insert into categoria(nome) values('Eletrodomésticos'); -- 5
insert into categoria(nome) values('Eletrônicos'); -- 6
insert into categoria(nome) values('Móveis'); -- 7
insert into categoria(nome) values('Cama, Mesa e Banho'); -- 8

insert into produto(nome, preco) values('Computador', 4800.00); --1
insert into produto(nome, preco) values('Impressora', 320.00); -- 2
insert into produto(nome, preco) values('Mouse', 75.00); -- 3
insert into produto(nome, preco) values('Mesa de escritório', 300.00); -- 4
insert into produto(nome, preco) values('Toalha', 50.00); -- 5
insert into produto(nome, preco) values('Colcha', 200.00); -- 6
insert into produto(nome, preco) values('TV true color', 1200.00); -- 7
insert into produto(nome, preco) values('Geladeira', 800.00); -- 8
insert into produto(nome, preco) values('Abajour', 100.00); -- 9
insert into produto(nome, preco) values('Pendente', 180.00); -- 10
insert into produto(nome, preco) values('Shampoo', 90.00); -- 11

insert into produto_categoria(produto_id, categoria_id) values(1, 1);
insert into produto_categoria(produto_id, categoria_id) values(2, 1);
insert into produto_categoria(produto_id, categoria_id) values(2, 2);
insert into produto_categoria(produto_id, categoria_id) values(3, 1);
insert into produto_categoria(produto_id, categoria_id) values(4, 7);
insert into produto_categoria(produto_id, categoria_id) values(5, 8);
insert into produto_categoria(produto_id, categoria_id) values(6, 8);
insert into produto_categoria(produto_id, categoria_id) values(7, 6);
insert into produto_categoria(produto_id, categoria_id) values(8, 5);
insert into produto_categoria(produto_id, categoria_id) values(9, 2);
insert into produto_categoria(produto_id, categoria_id) values(10, 2);
insert into produto_categoria(produto_id, categoria_id) values(11, 8);

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

insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2019-12-29 15:19:00', 1, 1);
insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2019-12-29 15:26:00', 1, 2);

insert into pagamento(pedido_id, status) values(1, 2);
insert into pagamento_com_cartao(numero_de_parcelas, pedido_id) values(6, 1);

insert into pagamento(pedido_id, status) values(2, 1);
insert into pagamento_com_boleto(data_pagamento, data_vencimento, pedido_id) values(null, '2019-12-29 16:32:00', 2);

insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(0.0, 4800.00, 1, 1, 1);
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(0.0, 75.00, 2, 1, 3);
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(100.0, 320.00, 1, 2, 2);


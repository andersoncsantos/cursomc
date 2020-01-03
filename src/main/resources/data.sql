insert into categoria(nome) values('Informática');
insert into categoria(nome) values('Escritório');
insert into categoria(nome) values('Smartphones');
insert into categoria(nome) values('SmartTVs');
insert into categoria(nome) values('Eletrodomésticos');
insert into categoria(nome) values('Eletrônicos');
insert into categoria(nome) values('Móveis');

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

insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2019-12-29 15:19:00', 1, 1);
insert into pedido(instante, cliente_id, endereco_de_entrega_id) values('2019-12-29 15:26:00', 1, 2);

insert into pagamento(pedido_id, status) values(1, 2);
insert into pagamento_com_cartao(numero_de_parcelas, pedido_id) values(6, 1);

insert into pagamento(pedido_id, status) values(2, 1);
insert into pagamento_com_boleto(data_pagamento, data_vencimento, pedido_id) values(null, '2019-12-29 16:32:00', 2);

insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(0.0, 4800.00, 1, 1, 1);
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(0.0, 75.00, 2, 1, 3);
insert into item_pedido(desconto, preco, quantidade, pedido_id, produto_id) values(100.0, 320.00, 1, 2, 2);


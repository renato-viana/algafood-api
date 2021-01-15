#Cozinha
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira')

#Estado
insert into estado (nome) values ('Rio de Janeiro')
insert into estado (nome) values ('São Paulo')
insert into estado (nome) values ('Minas Gerais')

#Cidade
insert into cidade (nome, estado_id) values ('Petrópolis', 1);
insert into cidade (nome, estado_id) values ('Três Rios', 1);
insert into cidade (nome, estado_id) values ('Piracicaba', 2);
insert into cidade (nome, estado_id) values ('Bauru', 2);
insert into cidade (nome, estado_id) values ('Juiz de Fora', 3); 
insert into cidade (nome, estado_id) values ('Belo Horizonte', 3); 

#Restarurante
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

#FormaPagamento
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

#Produto
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

#Permissão
insert into permissao (nome, descricao) values ('Leitura', 'Permite ler o conteúdo de um arquivo/diretório');
insert into permissao (nome, descricao) values ('Escrita', 'Permite alterar um arquivo/diretório');
insert into permissao (nome, descricao) values ('Execução', 'Permite executar um arquivo ou acessar um diretório e executar comandos');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);
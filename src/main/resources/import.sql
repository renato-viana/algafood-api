#Cozinha
insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

#Restarurante
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Nam Thai', 12.0, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Hoje tem Curry!', 8.0, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Taj Mahal', 10.0, 2);

#Estado
insert into estado (nome) values ('RJ')
insert into estado (nome) values ('SP')
insert into estado (nome) values ('MG')

#Cidade
insert into cidade (nome, estado_id) values ('Petrópolis', 1);
insert into cidade (nome, estado_id) values ('Três Rios', 1);
insert into cidade (nome, estado_id) values ('Piracicaba', 2);
insert into cidade (nome, estado_id) values ('Bauru', 2);
insert into cidade (nome, estado_id) values ('Juiz de Fora', 3); 
insert into cidade (nome, estado_id) values ('Belo Horizonte', 3); 

#FormaPagamento
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Boleto');

#Permissão
insert into permissao (nome, descricao) values ('Leitura', 'Permite ler o conteúdo de um arquivo/diretório');
insert into permissao (nome, descricao) values ('Escrita', 'Permite alterar um arquivo/diretório');
insert into permissao (nome, descricao) values ('Execução', 'Permite executar um arquivo ou acessar um diretório e executar comandos');

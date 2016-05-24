
INSERT INTO Site( id, nome, url, quemSomos, missao, visao, titulo, logo, atorization,create_user,create_date,modify_user,modify_date)values
( 1, 'nome_1', 'url_2', 'quemSomos_3', 'missao_4', 'visao_5', 'titulo_6', 'logo_7',true,'system',1462656383020,'rod',1462656383020);

INSERT INTO Site( id, nome, url, quemSomos, missao, visao, titulo, logo, atorization,create_user,create_date,modify_user,modify_date)values
( 2, 'nome_1', 'url_2', 'quemSomos_3', 'missao_4', 'visao_5', 'titulo_6', 'logo_7',true,'system',1462656383020,'rod',1462656383020);

INSERT INTO Site( id, nome, url, quemSomos, missao, visao, titulo, logo, atorization,create_user,create_date,modify_user,modify_date)values
( 3, 'nome_1', 'url_2', 'quemSomos_3', 'missao_4', 'visao_5', 'titulo_6', 'logo_7',true,'system',1462656383020,'rod',1462656383020);

INSERT INTO Plano(  dataInicio, dataFinal, numeroContrato, descricao, titulo, emprId,create_user,create_date,modify_user,modify_date)values
( 1462656383021, 1462656383021, 1, 'descricao_4', 'titulo_5', 1,'system',1462656383021,'rod',1462656383021);

INSERT INTO Plano(  dataInicio, dataFinal, numeroContrato, descricao, titulo, emprId,create_user,create_date,modify_user,modify_date)values
( 1462656383021, 1462656383021, 2, 'descricao_4', 'titulo_5', 2,'system',1462656383021,'rod',1462656383021);

INSERT INTO Plano(  dataInicio, dataFinal, numeroContrato, descricao, titulo, emprId,create_user,create_date,modify_user,modify_date)values
(  1462656383021, 1462656383021, 3, 'descricao_4', 'titulo_5', 3,'system',1462656383021,'rod',1462656383021);

INSERT INTO Endereco(  parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
(  1, 1, 1, 'logradouro_4', 'bairro_5', 'numero_6', 1, 'cep_8', 'complemento_9', 1,'system',1462656383021,'rod',1462656383021);

INSERT INTO Endereco(  parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
(  2, 2, 2, 'logradouro_4', 'bairro_5', 'numero_6', 2, 'cep_8', 'complemento_9', 2,'system',1462656383021,'rod',1462656383021);

INSERT INTO Endereco(  parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
(  3, 3, 3, 'logradouro_4', 'bairro_5', 'numero_6', 3, 'cep_8', 'complemento_9', 3,'system',1462656383021,'rod',1462656383021);

INSERT INTO Email(  tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
(  1, 1, 1, 'email_4', 1,'system',1462656383021,'rod',1462656383021);

INSERT INTO Email(  tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
(  2, 2, 2, 'email_4', 2,'system',1462656383021,'rod',1462656383021);

INSERT INTO Email(  tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
(  3, 3, 3, 'email_4', 3,'system',1462656383021,'rod',1462656383021);

INSERT INTO Telefone(  typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
(  1, 1, 1, 'ddd_4', 'numero_5', 1, 1,'system',1462656383021,'rod',1462656383021);

INSERT INTO Telefone(  typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
( 2, 2, 2, 'ddd_4', 'numero_5', 2, 2,'system',1462656383021,'rod',1462656383021);

INSERT INTO Telefone(  typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
( 3, 3, 3, 'ddd_4', 'numero_5', 3, 3,'system',1462656383021,'rod',1462656383021);

INSERT INTO Status(  dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
(  1462656383021, 1, 1, 1, 1, 'note_6','system',1462656383021,'rod',1462656383021);

INSERT INTO Status(  dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
(  1462656383021, 2, 2, 2, 2, 'note_6','system',1462656383021,'rod',1462656383021);

INSERT INTO Status(  dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
(  1462656383021, 3, 3, 3, 3, 'note_6','system',1462656383021,'rod',1462656383021);

INSERT INTO Notes(  parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
(  1, 'noteText_2', 1,'system',1462656383021,'rod',1462656383021);

INSERT INTO Notes(  parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
(  2, 'noteText_2', 2,'system',1462656383021,'rod',1462656383021);

INSERT INTO Notes(  parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
(  3, 'noteText_2', 3,'system',1462656383021,'rod',1462656383021);


INSERT INTO ServicoByPlano( id, parentId, servico,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1,1,1,'system',1464059658104,'rod',1464059658104);

INSERT INTO ServicoByPlano( id, parentId, servico,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 2, 2, 2,1,1,'system',1464059658104,'rod',1464059658104);

INSERT INTO ServicoByPlano( id, parentId, servico,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 3, 3, 3,1,1,'system',1464059658104,'rod',1464059658104);

INSERT INTO Preco(dataMarcacao, precoTypeEnum, tabelaEnumValue, valor, dataProInicial, dataProFinal, parentId, emprId,create_user,create_date,modify_user,modify_date)values
(1464059658104, 1, 1, 1, 1464059658104, 1464059658104, 1, 1,'system',1464059658104,'rod',1464059658104);

INSERT INTO Preco(dataMarcacao, precoTypeEnum, tabelaEnumValue, valor, dataProInicial, dataProFinal, parentId, emprId,create_user,create_date,modify_user,modify_date)values
(1464059658104, 2, 2, 2, 1464059658104, 1464059658104, 2, 2,'system',1464059658104,'rod',1464059658104);

INSERT INTO Preco(dataMarcacao, precoTypeEnum, tabelaEnumValue, valor, dataProInicial, dataProFinal, parentId, emprId,create_user,create_date,modify_user,modify_date)values
(1464059658104, 3, 3, 3, 1464059658104, 1464059658104, 3, 3,'system',1464059658104,'rod',1464059658104);


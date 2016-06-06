
INSERT INTO entidade(nome, processId, entidadeId, emprId, entidadeEnumValue, regime,create_user,create_date,modify_user,modify_date)values
('nome_1', 1, 1, 1, 1, 1,'system',1462134312648,'rod',1462134312648);

INSERT INTO entidade(nome, processId, entidadeId, emprId, entidadeEnumValue, regime,create_user,create_date,modify_user,modify_date)values
('nome_1', 2, 2, 2, 1, 2,'system',1462134312648,'rod',1462134312648);

INSERT INTO entidade(nome, processId, entidadeId, emprId, entidadeEnumValue, regime,create_user,create_date,modify_user,modify_date)values
('nome_1', 3, 3, 3, 1, 3,'system',1462134312648,'rod',1462134312648);

INSERT INTO Endereco( parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 'logradouro_4', 'bairro_5', 'numero_6', 1, 'cep_8', 'complemento_9', 1,'system',1462031573997,'rod',1462031573997);

INSERT INTO Endereco( parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
( 2, 1, 2, 'logradouro_4', 'bairro_5', 'numero_6', 2, 'cep_8', 'complemento_9', 2,'system',1462031573997,'rod',1462031573997);

INSERT INTO Endereco(parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
( 3, 1, 3, 'logradouro_4', 'bairro_5', 'numero_6', 3, 'cep_8', 'complemento_9', 3,'system',1462031573998,'rod',1462031573998);

INSERT INTO Documento(tabelaEnumValue, parentId, processId, documentoTypeEnumValue, numero, data,create_user,create_date,modify_user,modify_date)values
(1, 1, 1, 1, 'numero_5', 1461352331960,'system',1461352331960,'rod',1461352331960);

INSERT INTO Documento(tabelaEnumValue, parentId, processId, documentoTypeEnumValue, numero, data,create_user,create_date,modify_user,modify_date)values
(  1, 2, 2, 2, 'numero_5', 1461352331960,'system',1461352331960,'rod',1461352331960);

INSERT INTO Documento(  tabelaEnumValue, parentId, processId, documentoTypeEnumValue, numero, data,create_user,create_date,modify_user,modify_date)values
(  1, 3, 3, 3, 'numero_5', 1461352331960,'system',1461352331960,'rod',1461352331960);

INSERT INTO Email(tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
(1, 1, 1, 'email_4', 1,'system',1461944907888,'rod',1461944907888);

INSERT INTO Email( tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
(1, 2, 2, 'email_5', 2,'system',1461944907888,'rod',1461944907888);

INSERT INTO Email( tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
(1, 3, 3, 'email_6', 3,'system',1461944907888,'rod',1461944907888);


INSERT INTO Telefone(typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 'ddd_4', 'numero_5', 1, 1,'system',1462040163912,'rod',1462040163912);

INSERT INTO Telefone(typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
(2, 2, 2, 'ddd_4', 'numero_5', 2, 2,'system',1462040163912,'rod',1462040163912);

INSERT INTO Telefone(typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
(3, 3, 3, 'ddd_4', 'numero_5', 3, 3,'system',1462040163912,'rod',1462040163912);


INSERT INTO Cnae(codigo, cnae, descricao, abreviado,create_user,create_date,modify_user,modify_date)values
('codigo_1', 'cnae_2', 'descricao_3', 'abreviado_4','system',1462055653172,'rod',1462055653172);

INSERT INTO Cnae(codigo, cnae, descricao, abreviado,create_user,create_date,modify_user,modify_date)values
('codigo_1', 'cnae_2', 'descricao_3', 'abreviado_4','system',1462055653172,'rod',1462055653172);

INSERT INTO Cnae(codigo, cnae, descricao, abreviado,create_user,create_date,modify_user,modify_date)values
('codigo_1', 'cnae_2', 'descricao_3', 'abreviado_4','system',1462055653172,'rod',1462055653172);

INSERT INTO CnaeEmpresa(parentId, tabelaEnumValue, processId, idCnae,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 1,'system',1462055653173,'rod',1462055653173);

INSERT INTO CnaeEmpresa(parentId, tabelaEnumValue, processId, idCnae,create_user,create_date,modify_user,modify_date)values
(  2, 1, 2, 2,'system',1462055653173,'rod',1462055653173);

INSERT INTO CnaeEmpresa(parentId, tabelaEnumValue, processId, idCnae,create_user,create_date,modify_user,modify_date)values
(  3, 1, 3, 3,'system',1462055653173,'rod',1462055653173);

INSERT INTO Status( dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
(  1461530094517, 1, 1, 1, 1, 'note_6','system',1461530094517,'rod',1461530094517);

INSERT INTO Status( dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
(  1461530094517, 2, 1, 1, 1, 'note_6','system',1461530094517,'rod',1461530094517);

INSERT INTO Status(  dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
(  1461530094517, 3, 1, 1, 1, 'note_6','system',1461530094517,'rod',1461530094517);

INSERT INTO Notes( parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
(  1, 'noteText_2', 1,'system',1462035712489,'rod',1462035712489);

INSERT INTO Notes(  parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
(  2, 'noteText_2', 1,'system',1462035712489,'rod',1462035712489);

INSERT INTO Notes(  parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
(  3, 'noteText_2', 1,'system',1462035712489,'rod',1462035712489);






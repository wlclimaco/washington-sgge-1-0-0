
INSERT INTO pessoa( id, nome, nomePai, nomeMae, nomeConjugue, estadoCivil, tipoPessoa, datanasc, foto, pessoaTypeEnum, sexo,create_user,create_date,modify_user,modify_date)values
( 1, 'nome_1', 'nomePai_2', 'nomeMae_3', 'nomeConjugue_4', 1, 1, 1462303614542, 'foto_8', 9, 1,'system',1462303614542,'rod',1462303614542);

INSERT INTO pessoa( id, nome, nomePai, nomeMae, nomeConjugue, estadoCivil, tipoPessoa, datanasc, foto, pessoaTypeEnum, sexo,create_user,create_date,modify_user,modify_date)values
( 2, 'nome_1', 'nomePai_2', 'nomeMae_3', 'nomeConjugue_4', 2, 2, 1462303614542, 'foto_8', 9, 2,'system',1462303614542,'rod',1462303614542);

INSERT INTO pessoa( id, nome, nomePai, nomeMae, nomeConjugue, estadoCivil, tipoPessoa, datanasc, foto, pessoaTypeEnum, sexo,create_user,create_date,modify_user,modify_date)values
( 3, 'nome_1', 'nomePai_2', 'nomeMae_3', 'nomeConjugue_4', 3, 3, 1462303614542, 'foto_8', 9, 3,'system',1462303614542,'rod',1462303614542);

INSERT INTO Endereco( id, parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 1, 'logradouro_4', 'bairro_5', 'numero_6', 1, 'cep_8', 'complemento_9', 1,'system',1462303614542,'rod',1462303614542);

INSERT INTO Endereco( id, parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
( 2, 2, 2, 2, 'logradouro_4', 'bairro_5', 'numero_6', 2, 'cep_8', 'complemento_9', 2,'system',1462303614542,'rod',1462303614542);

INSERT INTO Endereco( id, parentId, tabelaEnumValue, processId, logradouro, bairro, numero, enderecoTypeValue, cep, complemento, cidade,create_user,create_date,modify_user,modify_date)values
( 3, 3, 3, 3, 'logradouro_4', 'bairro_5', 'numero_6', 3, 'cep_8', 'complemento_9', 3,'system',1462303614542,'rod',1462303614542);

INSERT INTO Email( id, tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 1, 'email_4', 1,'system',1462303614542,'rod',1462303614542);

INSERT INTO Email( id, tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
( 2, 2, 2, 2, 'email_4', 2,'system',1462303614542,'rod',1462303614542);

INSERT INTO Email( id, tabelaEnumValue, parentId, typeValue, email, emailTypeEnumValue,create_user,create_date,modify_user,modify_date)values
( 3, 3, 3, 3, 'email_4', 3,'system',1462303614542,'rod',1462303614542);

INSERT INTO Documentos( id, tabelaEnumValue, parentId, processId, documentoTypeEnumValue, numero, data,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 1, 1, 'numero_5', 1462303614542,'system',1462303614542,'rod',1462303614542);

INSERT INTO Documentos( id, tabelaEnumValue, parentId, processId, documentoTypeEnumValue, numero, data,create_user,create_date,modify_user,modify_date)values
( 2, 2, 2, 2, 2, 'numero_5', 1462303614542,'system',1462303614542,'rod',1462303614542);

INSERT INTO Documentos( id, tabelaEnumValue, parentId, processId, documentoTypeEnumValue, numero, data,create_user,create_date,modify_user,modify_date)values
( 3, 3, 3, 3, 3, 'numero_5', 1462303614542,'system',1462303614542,'rod',1462303614542);

INSERT INTO Telefone( id, typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
( 1, 1, 1, 1, 'ddd_4', 'numero_5', 1, 1,'system',1462303614542,'rod',1462303614542);

INSERT INTO Telefone( id, typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
( 2, 2, 2, 2, 'ddd_4', 'numero_5', 2, 2,'system',1462303614542,'rod',1462303614542);

INSERT INTO Telefone( id, typeValue, parentId, tabelaEnumValue, ddd, numero, telefoneTypeEnumValue, processId,create_user,create_date,modify_user,modify_date)values
( 3, 3, 3, 3, 'ddd_4', 'numero_5', 3, 3,'system',1462303614542,'rod',1462303614542);

INSERT INTO Status( id, dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
( 1, 1462303614542, 1, 1, 1, 1, 'note_6','system',1462303614542,'rod',1462303614542);

INSERT INTO Status( id, dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
( 2, 1462303614542, 2, 2, 2, 2, 'note_6','system',1462303614542,'rod',1462303614542);

INSERT INTO Status( id, dataStatus, parentId, statusValue, acaoEnumValue, tabelaEnumValue, note,create_user,create_date,modify_user,modify_date)values
( 3, 1462303614542, 3, 3, 3, 3, 'note_6','system',1462303614542,'rod',1462303614542);

INSERT INTO Note( id, parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
( 1, 1, 'noteText_2', 1,'system',1462303614542,'rod',1462303614542);

INSERT INTO Note( id, parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
( 2, 2, 'noteText_2', 2,'system',1462303614542,'rod',1462303614542);

INSERT INTO Note( id, parentId, noteText, tabelaEnumValue,create_user,create_date,modify_user,modify_date)values
( 3, 3, 'noteText_2', 3,'system',1462303614542,'rod',1462303614542);

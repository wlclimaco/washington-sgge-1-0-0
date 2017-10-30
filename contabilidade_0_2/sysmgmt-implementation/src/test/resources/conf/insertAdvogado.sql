
INSERT INTO advogado( id, nome, nomeFantasia, regime, nomePai, nomeMae, nomeConjugue, estadoCivil, datanasc, tipoPessoa, foto, pessoaTypeEnum, sexo, tempoAtendimento, oab, mediaAtendimento, maxAtendimento, maxEncaixe, estado, tipoOab,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1000, 'nome_0', 'nomeFantasia_0', 1000, 'nomePai_0', 'nomeMae_0', 'nomeConjugue_0', 1000,  1508939151079 , 1000, 'foto_0', 1000, 1000,  1508939151079 , 'oab_0',  1508939151079 , 1000, 1000, 1000, 1000,10000,1,1,1,'system',1508939151079,'rod',1508939151079);

INSERT INTO advogado( id, nome, nomeFantasia, regime, nomePai, nomeMae, nomeConjugue, estadoCivil, datanasc, tipoPessoa, foto, pessoaTypeEnum, sexo, tempoAtendimento, oab, mediaAtendimento, maxAtendimento, maxEncaixe, estado, tipoOab,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1001, 'nome_1', 'nomeFantasia_1', 1001, 'nomePai_1', 'nomeMae_1', 'nomeConjugue_1', 1001,  1508939151079 , 1001, 'foto_1', 1001, 1001,  1508939151079 , 'oab_1',  1508939151079 , 1001, 1001, 1001, 1001,10000,1,1,1,'system',1508939151079,'rod',1508939151079);

INSERT INTO advogado( id, nome, nomeFantasia, regime, nomePai, nomeMae, nomeConjugue, estadoCivil, datanasc, tipoPessoa, foto, pessoaTypeEnum, sexo, tempoAtendimento, oab, mediaAtendimento, maxAtendimento, maxEncaixe, estado, tipoOab,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002, 'nome_2', 'nomeFantasia_2', 1002, 'nomePai_2', 'nomeMae_2', 'nomeConjugue_2', 1002,  1508939151079 , 1002, 'foto_2', 1002, 1002,  1508939151079 , 'oab_2',  1508939151079 , 1002, 1002, 1002, 1002,10000,1,1,1,'system',1508939151079,'rod',1508939151079);


INSERT INTO Especialidade(nome,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
('nome_0',10000,1,1,1,'system',1509213434669,'rod',1509213434669);

INSERT INTO Especialidade(nome,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
('nome_1',10001,1,1,1,'system',1509213434669,'rod',1509213434669);

INSERT INTO Especialidade(nome,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
('nome_2',1002,1,1,1,'system',1509213434669,'rod',1509213434669);

INSERT INTO diasHoras(diasSemanas, horaInicio, horaFinal, diario, semanal, quinzenal, mensal, anual,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1000,  1509213434668 ,  1509213434668 , 1000, 1000, 1000, 1000, 1000,10000,74,1,1,'system',1509213434668,'rod',1509213434668);

INSERT INTO diasHoras( diasSemanas, horaInicio, horaFinal, diario, semanal, quinzenal, mensal, anual,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(  1001,  1509213434668 ,  1509213434668 , 1001, 1001, 1001, 1001, 1001,10000,74,1,1,'system',1509213434668,'rod',1509213434668);

INSERT INTO diasHoras(diasSemanas, horaInicio, horaFinal, diario, semanal, quinzenal, mensal, anual,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1002,  1509213434668 ,  1509213434668 , 1002, 1002, 1002, 1002, 1002,10000,74,1,1,'system',1509213434668,'rod',1509213434668);


INSERT INTO Endereco(pais, logradouro, bairro, numero, enderecoTypeValue, cep, latitude, longitude, complemento, cidade,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1000, 'logradouro_0', 'bairro_0', 'numero_0', 1000, 'cep_0', 10.00, 10.00, 'complemento_0', 1000,1000,74,1,1,'system',1509213434675,'rod',1509213434675);

INSERT INTO Endereco( pais, logradouro, bairro, numero, enderecoTypeValue, cep, latitude, longitude, complemento, cidade,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1001, 'logradouro_1', 'bairro_1', 'numero_1', 1001, 'cep_1', 10.00, 10.00, 'complemento_1', 1001,1001,74,1,1,'system',1509213434675,'rod',1509213434675);

INSERT INTO Endereco( pais, logradouro, bairro, numero, enderecoTypeValue, cep, latitude, longitude, complemento, cidade,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002, 'logradouro_2', 'bairro_2', 'numero_2', 1002, 'cep_2', 10.00, 10.00, 'complemento_2', 1002,1002,74,1,1,'system',1509213434675,'rod',1509213434675);



INSERT INTO Email(typeValue, email, emailTypeEnumValue,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1000, 'email_0', 1000,1000,74,1,1,'system',1509213434676,'rod',1509213434676);

INSERT INTO Email(typeValue, email, emailTypeEnumValue,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1001, 'email_1', 1001,1001,74,1,1,'system',1509213434676,'rod',1509213434676);

INSERT INTO Email( typeValue, email, emailTypeEnumValue,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(1002, 'email_2', 1002,1002,74,1,1,'system',1509213434676,'rod',1509213434676);


INSERT INTO Telefone(typeValue, ddd, numero, telefoneTypeEnumValue,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1000, 'ddd_0', 'numero_0', 1000,1000,74,1,1,'system',1509213434677,'rod',1509213434677);

INSERT INTO Telefone( typeValue, ddd, numero, telefoneTypeEnumValue,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1001, 'ddd_1', 'numero_1', 1001,1001,74,1,1,'system',1509213434677,'rod',1509213434677);

INSERT INTO Telefone( typeValue, ddd, numero, telefoneTypeEnumValue,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 1002, 'ddd_2', 'numero_2', 1002,1002,74,1,1,'system',1509213434677,'rod',1509213434677);

INSERT INTO Documento(documentoTypeEnumValue, numero, data,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(  1000, 'numero_0',  1509213434681 ,1000,74,1,1,'system',1509213434681,'rod',1509213434681);

INSERT INTO Documento( documentoTypeEnumValue, numero, data,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(  1001, 'numero_1',  1509213434681 ,1001,74,1,1,'system',1509213434681,'rod',1509213434681);

INSERT INTO Documento( documentoTypeEnumValue, numero, data,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(  1002, 'numero_2',  1509213434681 ,1002,74,1,1,'system',1509213434681,'rod',1509213434681);


INSERT INTO Arquivo( nome, local, tipo, tamanho,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
( 'nome_0', 'local_0', 'tipo_0', 'tamanho_0',1000,74,1,1,'system',1509213434679,'rod',1509213434679);

INSERT INTO Arquivo( nome, local, tipo, tamanho,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(  'nome_1', 'local_1', 'tipo_1', 'tamanho_1',1001,74,1,1,'system',1509213434679,'rod',1509213434679);

INSERT INTO Arquivo(nome, local, tipo, tamanho,parentId,tabelaEnumValue,emprId,processId,create_user,create_date,modify_user,modify_date)values
(  'nome_2', 'local_2', 'tipo_2', 'tamanho_2',1002,74,1,1,'system',1509213434679,'rod',1509213434679);


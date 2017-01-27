
INSERT INTO pessoa( id, nome, nomePai, nomeMae, nomeConjugue, estadoCivil, pessoaTipo, datanasc, foto, pessoaTypeEnum, sexo,create_user,create_date,modify_user,modify_date)values
( 1004, 'nome_1', 'nomePai_2', 'nomeMae_3', 'nomeConjugue_4', 1, 1, 1462303614542, 'foto_8', 1, 1,'system',1462303614542,'rod',1462303614542);

INSERT INTO pessoa( id, nome, nomePai, nomeMae, nomeConjugue, estadoCivil, pessoaTipo, datanasc, foto, pessoaTypeEnum, sexo,create_user,create_date,modify_user,modify_date)values
( 1005, 'nome_1', 'nomePai_2', 'nomeMae_3', 'nomeConjugue_4', 2, 2, 1462303614542, 'foto_8', 1, 2,'system',1462303614542,'rod',1462303614542);

INSERT INTO pessoa( id, nome, nomePai, nomeMae, nomeConjugue, estadoCivil, pessoaTipo, datanasc, foto, pessoaTypeEnum, sexo,create_user,create_date,modify_user,modify_date)values
( 1006, 'nome_1', 'nomePai_2', 'nomeMae_3', 'nomeConjugue_4', 3, 3, 1462303614542, 'foto_8', 1, 3,'system',1462303614542,'rod',1462303614542);


INSERT INTO pessoatipo(id, parentid, pessoatypeenum, emprid, create_date, create_user, modify_date, modify_user)values
( 1006, 1004, 1,  1, 1462303614542,'system',1462303614542,'rod');

INSERT INTO pessoatipo(id, parentid, pessoatypeenum, emprid, create_date, create_user, modify_date, modify_user)values
( 1007, 1005, 1, 1, 1462303614542,'system',1462303614542,'rod');

INSERT INTO pessoatipo(id, parentid, pessoatypeenum, emprid, create_date, create_user, modify_date, modify_user)values
( 1008, 1006, 1, 1, 1462303614542,'system',1462303614542,'rod');
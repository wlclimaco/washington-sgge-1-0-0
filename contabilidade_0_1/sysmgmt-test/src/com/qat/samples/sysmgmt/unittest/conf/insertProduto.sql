INSERT INTO classificacao(
            id, codigo, descricao, processid, create_date, create_user, modify_date,
            modify_user)
    VALUES (1, 'codigo', 'descricao', 1,  1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO classificacaoprod(
            id, classificacaoid, prodid, processid, create_date, create_user,
            modify_date, modify_user)
    VALUES (1, 1, 1, 1, 1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO unimed(
            id, unimed, sigla, processid, create_date, create_user, modify_date,
            modify_user)
    VALUES (1,'UNIMED','SIGLA', 1, 1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO unimedprod(
            id, unimedid, prodid, processid, create_date, create_user, modify_date,
            modify_user)
    VALUES (1, 1, 1, 1,  1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO marca(
            id, marca, fabricante, processid, create_date, create_user, modify_date,
            modify_user)
    VALUES (1, 'MARCA', 'FABRICANTE', 1, 1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO marcaprod( id, marcaid, prodid, processid, create_date, create_user, modify_date,
       modify_user)VALUES(1,1,1,1, 1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO grupo(
            id, grupo, descricao, processid, create_date, create_user, modify_date,
            modify_user)
    VALUES (1, 'GRUPO','DESCRICAO', 1,  1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO grupoprod(
            id, grupoid, prodid, processid, create_date, create_user, modify_date,
            modify_user)
    VALUES (1, 1, 1001, 1,  1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO porcao(
            id, prodid, valor, processid, idporcaoitens, create_date, create_user,
            modify_date, modify_user)
    VALUES (1, 1000,1.99, 1, 1,  1458046715651, 'ROD', 1458046715651, 'ROD');

INSERT INTO porcaoitens(
            id, idporcao, unimed, porcao, vd, nome, processid, create_date,
            create_user, modify_date, modify_user)
    VALUES (1, 1, 1, 1, 1, 'NOME', 1,  1458046715651, 'ROD', 1458046715651, 'ROD');



INSERT INTO produto(
            id, codigo, cdbarras, classificacao, datacreate, produto, aplicacao,
            localizacao, datavalidade, comissao, fracao, unimed, grupo, subgrupo,
            marca, porcao, pesobruto, pesoliquido, modouso, tributacao, processid,
            create_date, create_user, modify_date, modify_user)
    VALUES (1000,'CODIGO','CODBARRA', 1, 1458046715651, 'PRODUTO','APLICACAO',
            'LOCALIZACAO', 1458046715651, '1', '10', 1, 1, 1,
            1, 1, 1.5, 1.9, ',MODO USO', 1, 1,
            1458046715651, 'ROD', 1458046715651, 'ROD');

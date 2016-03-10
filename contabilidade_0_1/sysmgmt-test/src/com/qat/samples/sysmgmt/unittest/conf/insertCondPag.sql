INSERT INTO condpag(nome, valorini, valorfin, porcentagem, valor, processid, create_date,
            create_user, modify_date, modify_user)
    VALUES ('teste', 1457555476, 1457555476, 10, 10, 1,1457555476, 'rod',
            1457555476, 'koala');

INSERT INTO condpagpessoa(parentid, condpagid, processid, create_date, create_user,
            modify_date, modify_user)
    VALUES (1, 1, 1,1457555476, 'rod',
            1457555476, 'koala');


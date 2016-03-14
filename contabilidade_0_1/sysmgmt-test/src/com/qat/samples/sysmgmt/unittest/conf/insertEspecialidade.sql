INSERT INTO especialidade(parentid, nome, processid, create_user, create_date, modify_user,
            modify_date)
    VALUES  (0,'NOME0',0,'rod',1234567899,null,null),
			(0,'NOME1',0,'rod',1234567899,null,null),
			(0,'NOME2',0,'rod',1234567899,null,null);


INSERT INTO especialidadepessoa(parentid, especialidade, processid, create_user, create_date,modify_user, modify_date)
    VALUES (1,1,1, 'rod',1234567897,null,null);
INSERT INTO [dbo].[regime]
           ([nome]
           ,[descricao]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           ('Simples Nacional','Simples Nacional',1432783357778,'rod',14327833577780,'rod'),
		   ('Lucro Real','Lucro Real',14327833577780,'rod',1000000,'rod'),
		   ('Lucro Presumido','Lucro Presumido',14327833577780,'rod',1000000,'rod')

INSERT INTO [dbo].[status]([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (14327833577780,1,1,1,0,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,2,1,0,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,3,1,0,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')


GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[empresa]
           ([nome]
           ,[regime]
           ,[processId]
	       ,[entidadeId]
		   ,[entidadeEnum]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           ('Telhas Tecplan',1,1,null,1,1000000,'rod',1000000,'rod'),
		   ('boteckin do damiao',2,1,null,1,1000000,'rod',1000000,'rod'),
		   ('silvio casa de ração',3,1,null,1,1000000,'rod',1000000,'rod'),

		   ('Telhas Tecplan',1,1,1,2,1000000,'rod',1000000,'rod'),
		   ('boteckin do damiao',2,1,2,2,1000000,'rod',1000000,'rod'),
		   ('silvio casa de ração',3,1,3,2,1000000,'rod',1000000,'rod'),

		   ('Telhas Tecplan',1,1,4,3,1000000,'rod',1000000,'rod'),
		   ('boteckin do damiao',2,1,5,3,1000000,'rod',1000000,'rod'),
		   ('silvio casa de ração',3,1,6,3,1000000,'rod',1000000,'rod')


	INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,2,1,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,3,1,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')

GO

INSERT INTO [dbo].[documento]
           ([tabela]
           ,[parentId]
           ,[type]
           ,[description]
           ,[numero]
           ,[data]
           ,[estado]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,1,'CNPJ','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (1,2,1,'CPF','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (1,3,1,'IM','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (1,1,1,'IE','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (1,2,1,'RG','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (1,3,1,'CGC','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod')

INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')
GO

INSERT INTO [dbo].[email]
           ([tabela]
           ,[parentId]
           ,[type]
           ,[email]
           ,[description]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (1,2,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (1,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (1,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (1,2,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (1,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod')

		   INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')
GO

INSERT INTO [dbo].[endereco]
           ([tabela]
           ,[parentId]
           ,[type]
           ,[logradouro]
           ,[cidade]
           ,[estado]
           ,[bairro]
           ,[numero]
           ,[cep]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (1,2,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (1,3,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (1,1,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (1,2,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (1,3,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod')

INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,4,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,4,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,4,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,4,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,4,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,4,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')
GO

INSERT INTO [dbo].[telefone]
           ([type]
           ,[parentId]
           ,[tabela]
           ,[ddd]
           ,[telefone]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,1,2,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,1,3,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,1,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,2,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,2,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,3,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,3,1,'034','91782776',1432783357778,'system',1432783357778,'system')

		   INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,7,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,8,1,1,5,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')
GO

INSERT INTO [dbo].[socio] ([parentId],[nome],[cota],[porcentagem],[create_date],[create_user],[modify_date],[modify_user]) VALUES
	 (1,'ze das coves',1,50,14327833577780,'rod',14327833577780,'rod'),
	 (1,'ze das coves',1,50,14327833577780,'rod',14327833577780,'rod'),
	 (2,'ze das coves',1,50,14327833577780,'rod',14327833577780,'rod'),
	 (2,'ze das coves',1,50,14327833577780,'rod',14327833577780,'rod'),
	 (3,'ze das coves',1,50,14327833577780,'rod',14327833577780,'rod'),
	 (3,'ze das coves',1,50,14327833577780,'rod',14327833577780,'rod')



		   INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577100,1,2,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577100,2,2,1,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')

/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[CIDADE]
           ([nome]
           ,[cdIBGE]
           ,[ESTADO]
           ,[MUNICIPIO]
           ,[processId]
           ,[status]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           ('UBERABA' ,'385454',1,'UBERABA',0,0,14327833577780,'system' ,14327833577780,'system')
GO

/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[cnaePorRelacionamento]
           ([idCnae]
           ,[idParentId]
           ,[tabela]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           ('000001',1,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000002',1,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000003',1,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000004',2,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000005',2,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000006',3,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000008',3,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000007',3,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000009',2,1,14327833577780,'system' ,14327833577780,'system'),
		   ('000011',1,1,14327833577780,'system' ,14327833577780,'system')
GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Salario]
           ([parentId],[valor],[data],[create_date],[create_user],[modify_user],[modify_date])
     VALUES
           (1,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (2,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (3,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (4,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (5,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (6,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (7,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (8,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (9,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (10,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (11,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (12,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (13,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (14,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (15,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (1,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (2,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (3,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (4,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (5,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778)
GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Beneficio]
           ([nome],[codigo],[descricao],[valor],[porcentagem],[tipo],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
            ('Vale Transporte','000001','vale transporte',0,3.5,'desc',1432783357778,'System',1432783357778,'System'),
			('UNIMED','000002','Plano de saude',0,3.5,'desc',1432783357778,'System',1432783357778,'System')

GO
/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[BeneficioFunc]
           ([idFunc],[idBenef],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,'System',1432783357778,'System'),
		   (2,1,1432783357778,'System',1432783357778,'System'),
		   (3,1,1432783357778,'System',1432783357778,'System'),
		   (4,1,1432783357778,'System',1432783357778,'System'),
		   (5,1,1432783357778,'System',1432783357778,'System'),
		   (6,1,1432783357778,'System',1432783357778,'System'),
		   (7,1,1432783357778,'System',1432783357778,'System'),
		   (8,1,1432783357778,'System',1432783357778,'System'),
		   (9,1,1432783357778,'System',1432783357778,'System'),
		   (10,1,1432783357778,'System',1432783357778,'System'),
		   (11,1,1432783357778,'System',1432783357778,'System'),
		   (12,1,1432783357778,'System',1432783357778,'System'),
		   (13,1,1432783357778,'System',1432783357778,'System'),
		   (14,1,1432783357778,'System',1432783357778,'System'),
		   (15,1,1432783357778,'System',1432783357778,'System'),
		   (1,2,1432783357778,'System',1432783357778,'System'),
		   (2,2,1432783357778,'System',1432783357778,'System'),
		   (3,2,1432783357778,'System',1432783357778,'System'),
		   (4,2,1432783357778,'System',1432783357778,'System'),
		   (5,2,1432783357778,'System',1432783357778,'System'),
		   (6,2,1432783357778,'System',1432783357778,'System'),
		   (7,2,1432783357778,'System',1432783357778,'System'),
		   (8,2,1432783357778,'System',1432783357778,'System'),
		   (9,2,1432783357778,'System',1432783357778,'System'),
		   (10,2,1432783357778,'System',1432783357778,'System'),
		   (11,2,1432783357778,'System',1432783357778,'System'),
		   (12,2,1432783357778,'System',1432783357778,'System'),
		   (13,2,1432783357778,'System',1432783357778,'System'),
		   (14,2,1432783357778,'System',1432783357778,'System'),
		   (15,2,1432783357778,'System',1432783357778,'System')
GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[BeneficioMesApp]
           ([data],[idFuncBenef],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1432783357778,1,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,2,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,3,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,4,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,5,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,6,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,7,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,8,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,9,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,10,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,11,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,12,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,13,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,14,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,15,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,16,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,17,1432783357778,'System',1432783357778,'System')
GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Evento]
           ([data],[descricao],[tipo],[codigo],[noteText],[valor],[porcentagem],[isMensal],[isSistema],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1432783357778,'FGTS','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (1432783357778,'IR','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (1432783357778,'SINDICATO','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (1432783357778,'INSS','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system')
GO
/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[EventoFunc]
           ([data],[idFunc],[create_date],[create_user],[modify_date],[modify_user],[idEvent])
     VALUES
           (1432783357778,1,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,2,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,3,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,4,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,5,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,6,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,7,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,8,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,9,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,10,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,11,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,12,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,13,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,14,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,15,1432783357778,'System',1432783357778,'System',1),
		   (1432783357778,1,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,2,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,3,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,4,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,5,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,6,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,7,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,8,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,9,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,10,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,11,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,12,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,13,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,14,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,15,1432783357778,'System',1432783357778,'System',2),
		   (1432783357778,1,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,2,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,3,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,4,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,5,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,6,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,7,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,8,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,9,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,10,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,11,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,12,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,13,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,14,1432783357778,'System',1432783357778,'System',3),
		   (1432783357778,15,1432783357778,'System',1432783357778,'System',3)

GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[EventoMesApp]
           ([data],[idFuncEnvent],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1432783357778,1,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,2,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,3,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,4,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,5,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,6,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,7,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,8,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,9,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,10,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,11,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,12,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,13,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,14,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,15,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,16,1432783357778,'System',1432783357778,'System'),
		   (1432783357778,17,1432783357778,'System',1432783357778,'System');
GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Pessoa]
           ([nome],[cdEmpr],[sexo],[type],[dataNasc],[nomePai],[nomeMae],[nomeConjugue],[estadoCivil],[create_date],[modify_date],[modify_user],[create_user])
     VALUES
           ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,4,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System')


INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (14327833577780,1,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577100,1,2,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577100,2,2,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,1,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,2,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,3,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,4,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,5,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577780,6,1,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577100,1,2,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (14327833577100,2,2,1,16,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')

GO

INSERT INTO [dbo].[documento]
           ([tabela],[parentId],[type],[description],[numero],[data] ,[estado],[create_date] ,[create_user],[modify_date],[modify_user])
     VALUES
           (16,1,1,'CNPJ','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,2,1,'CPF','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,3,1,'IM','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,4,1,'IE','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,5,1,'RG','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,6,1,'CGC','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,7,1,'CNPJ','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,8,1,'CPF','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,9,1,'IM','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,10,1,'IE','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,11,1,'RG','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,12,1,'CGC','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,13,1,'CNPJ','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,14,1,'CPF','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,15,1,'IM','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,1,1,'IE','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,2,1,'RG','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod'),
		   (16,3,1,'CGC','2001',14327833577780,1,14327833577780,'rod',14327833577780,'rod')
GO

INSERT INTO [dbo].[email]
           ([tabela],[parentId],[type],[email],[description],[create_date],[create_user] ,[modify_date],[modify_user])
     VALUES
           (16,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,2,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,4,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,5,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,6,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,7,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,8,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,9,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,10,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,11,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,12,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,13,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,14,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,15,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod')
GO

INSERT INTO [dbo].[endereco]
           ([tabela],[parentId],[type],[logradouro],[cidade],[estado],[bairro],[numero],[cep],[create_date] ,[create_user],[modify_date],[modify_user])
     VALUES
           (16,1,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,2,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,3,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,4,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,5,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,6,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,7,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,8,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,9,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,10,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,11,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,12,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,13,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,14,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,15,1,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,1,2,'Rua Maria Conceicao silva',1,1,'centro','686','380802-243',14327833577780,'rod',10000002,'rod')
GO

INSERT INTO [dbo].[telefone]
           ([type] ,[parentId],[tabela],[ddd],[telefone],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,2,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,3,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,4,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,5,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,6,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,7,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,8,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,9,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,10,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,11,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,12,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,13,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,14,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (1,15,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (2,1,16,'034','91782776',1432783357778,'system',1432783357778,'system')
GO

/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[horarios]
           ([parentId],[data],[horarioEntr],[horarioSair],[create_date],[tipo],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (2,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (3,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (4,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (5,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (6,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (7,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (8,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (9,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (10,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (11,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (12,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (13,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (14,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (15,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (1,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (2,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (3,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (4,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (5,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (6,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (7,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (8,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (9,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (10,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (11,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (12,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (13,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (14,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (15,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system')

GO




INSERT INTO [dbo].[Pessoa]
           ([nome],[cdEmpr],[sexo],[type],[dataNasc],[nomePai],[nomeMae],[nomeConjugue],[estadoCivil],[create_date],[modify_date],[modify_user],[create_user])
     VALUES
           ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   ('washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System')


		INSERT INTO [dbo].[status] ([dataStatus],[parentId],[status],[acaoType],[tabelaEnum],[note],[create_date],[create_user],[modify_date],[modify_user]) VALUES

           (57,14327833577780,1,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (58,14327833577780,2,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (59,14327833577780,3,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (60,14327833577780,4,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (61,14327833577780,5,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (62,14327833577780,6,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (63,14327833577100,7,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (64,14327833577100,8,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (65,14327833577780,9,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (66,14327833577780,10,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (67,14327833577780,11,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (68,14327833577780,12,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (69,14327833577780,13,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (70,14327833577780,14,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (71,14327833577100,15,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (72,14327833577100,16,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (73,14327833577780,17,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (74,14327833577780,18,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (75,14327833577780,19,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (76,14327833577780,20,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (77,14327833577780,21,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (78,14327833577780,22,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (79,14327833577100,23,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (80,14327833577100,24,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (81,14327833577780,25,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (82,14327833577780,26,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (83,14327833577780,27,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (84,14327833577780,28,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (85,14327833577780,29,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (86,14327833577780,30,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (87,14327833577100,31,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (88,14327833577100,32,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (89,14327833577780,33,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (90,14327833577780,34,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (91,14327833577780,35,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (92,14327833577780,36,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (93,14327833577780,37,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (94,14327833577780,38,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (95,14327833577100,39,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (96,14327833577100,40,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (97,14327833577780,41,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (98,14327833577780,42,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (99,14327833577780,43,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (100,14327833577780,44,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (101,14327833577780,45,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (102,14327833577780,46,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (103,14327833577100,47,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (104,14327833577100,48,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (105,14327833577100,49,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (106,14327833577100,50,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (107,14327833577780,51,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (108,14327833577780,52,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (109,14327833577780,53,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (110,14327833577780,54,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (111,14327833577780,55,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (112,14327833577780,56,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (113,14327833577100,57,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (114,14327833577100,58,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (115,14327833577100,59,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (116,14327833577100,60,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (117,14327833577100,61,2,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (118,14327833577780,62,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (119,14327833577780,63,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (120,14327833577780,64,1,1,23,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')

GO

/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Profissao]
           ([parentId],[profissao],[renda],[dataAdmissao],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (2,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (3,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (4,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (5,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (6,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (7,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (8,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (9,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (10,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (11,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (12,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (13,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (14,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (15,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (16,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (17,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (18,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (19,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (20,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (21,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System')
GO


/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Convenio]
           ([nome],[dataini],[dataFin],[porcentagem],[valor],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Convenio 0001',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   ('Convenio 0002',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   ('Convenio 0003',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   ('Convenio 0004',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   ('Convenio 0005',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System')
/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[ConvenioPessoa]
           ([parentId],[convId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,'System',1432783357778,'System'),
           (2,2,1432783357778,'System',1432783357778,'System'),
           (3,3,1432783357778,'System',1432783357778,'System'),
           (4,4,1432783357778,'System',1432783357778,'System'),
           (5,5,1432783357778,'System',1432783357778,'System'),
           (6,1,1432783357778,'System',1432783357778,'System'),
           (7,2,1432783357778,'System',1432783357778,'System'),
           (8,3,1432783357778,'System',1432783357778,'System'),
           (9,4,1432783357778,'System',1432783357778,'System'),
           (10,5,1432783357778,'System',1432783357778,'System'),
           (11,1,1432783357778,'System',1432783357778,'System'),
           (12,2,1432783357778,'System',1432783357778,'System'),
           (13,3,1432783357778,'System',1432783357778,'System'),
           (14,4,1432783357778,'System',1432783357778,'System'),
           (15,5,1432783357778,'System',1432783357778,'System'),
           (16,1,1432783357778,'System',1432783357778,'System'),
           (17,1,1432783357778,'System',1432783357778,'System'),
           (18,2,1432783357778,'System',1432783357778,'System'),
           (19,3,1432783357778,'System',1432783357778,'System'),
           (20,4,1432783357778,'System',1432783357778,'System'),
           (21,5,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[CondPag]
           ([nome],[dataini],[dataFin],[porcentagem],[valor],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Condicao Pag 0001',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
           ('Condicao Pag 0002',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
           ('Condicao Pag 0003',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[CondPagPessoa]
           ([parentId],[condPagId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,'System',1432783357778,'System'),
           (2,2,1432783357778,'System',1432783357778,'System'),
           (3,3,1432783357778,'System',1432783357778,'System'),
           (4,1,1432783357778,'System',1432783357778,'System'),
           (5,2,1432783357778,'System',1432783357778,'System'),
           (6,3,1432783357778,'System',1432783357778,'System'),
           (7,2,1432783357778,'System',1432783357778,'System'),
           (8,3,1432783357778,'System',1432783357778,'System'),
           (9,1,1432783357778,'System',1432783357778,'System'),
           (10,2,1432783357778,'System',1432783357778,'System'),
           (11,3,1432783357778,'System',1432783357778,'System'),
           (12,2,1432783357778,'System',1432783357778,'System'),
           (13,3,1432783357778,'System',1432783357778,'System'),
           (14,1,1432783357778,'System',1432783357778,'System'),
           (15,3,1432783357778,'System',1432783357778,'System'),
           (16,1,1432783357778,'System',1432783357778,'System'),
           (17,2,1432783357778,'System',1432783357778,'System'),
           (18,2,1432783357778,'System',1432783357778,'System'),
           (19,3,1432783357778,'System',1432783357778,'System'),
           (20,1,1432783357778,'System',1432783357778,'System'),
           (21,2,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[TipoPag]
           ([descricao],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Cartao Credito',1432783357778,'System',1432783357778,'System'),
           ('Boleto',1432783357778,'System',1432783357778,'System'),
           ('Cartao Debito',1432783357778,'System',1432783357778,'System')
/** ---------------------------------------------------------------*/


INSERT INTO [dbo].[TipoPagReg]
           ([parentId],[tipoPagId],[tabela],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,21,'System',1432783357778,1432783357778,'System'),
           (2,2,21,'System',1432783357778,1432783357778,'System'),
           (3,3,21,'System',1432783357778,1432783357778,'System'),
           (4,4,21,'System',1432783357778,1432783357778,'System'),
           (5,5,21,'System',1432783357778,1432783357778,'System'),
           (6,1,21,'System',1432783357778,1432783357778,'System'),
           (7,1,21,'System',1432783357778,1432783357778,'System'),
           (8,2,21,'System',1432783357778,1432783357778,'System'),
           (9,3,21,'System',1432783357778,1432783357778,'System'),
           (10,1,21,'System',1432783357778,1432783357778,'System'),
           (11,1,21,'System',1432783357778,1432783357778,'System'),
           (12,2,21,'System',1432783357778,1432783357778,'System'),
           (13,3,21,'System',1432783357778,1432783357778,'System'),
           (14,4,21,'System',1432783357778,1432783357778,'System'),
           (15,5,21,'System',1432783357778,1432783357778,'System'),
           (16,1,21,'System',1432783357778,1432783357778,'System'),
           (17,1,21,'System',1432783357778,1432783357778,'System'),
           (18,2,21,'System',1432783357778,1432783357778,'System'),
           (19,1,21,'System',1432783357778,1432783357778,'System'),
           (20,2,21,'System',1432783357778,1432783357778,'System'),
           (21,3,21,'System',1432783357778,1432783357778,'System')


/** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[banco]
           ([nome],[logo],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Banco Brasil','opt:/imagem/',1432783357778,'System',1432783357778,'System'),
           ('Itau','opt:/imagem/',1432783357778,'System',1432783357778,'System'),
           ('Caixa','opt:/imagem/',1432783357778,'System',1432783357778,'System')
/** ---------------------------------------------------------------*/

           INSERT INTO [dbo].[agencia]
           ([nome],[parentId],[numero],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Agencia 0001',1,'00000-25',1432783357778,'System',1432783357778,'System'),
           ('Agencia 0001',2,'00000-26',1432783357778,'System',1432783357778,'System'),
           ('Agencia 0001',3,'00000-27',1432783357778,'System',1432783357778,'System')
/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[BancoPesoa]
           ([parentId],[bancoId],[numCont],[saldo],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (2,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (3,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (4,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (5,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (6,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (7,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (8,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (9,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (10,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (11,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (12,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (13,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (14,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (15,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (16,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (17,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (18,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (20,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (22,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (21,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System')

/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[contato]
           ([parentId],[motivo],[dataContato],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (2,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (3,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (4,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (5,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (6,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (7,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (8,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (9,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (10,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (11,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (12,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (13,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (14,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (15,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (16,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (17,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (18,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (19,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (20,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (21,3,1432783357778,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[contatoItens]
           ([parentId],[texto],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'1',1432783357778,'System',1432783357778,'System'),
           (2,'2',1432783357778,'System',1432783357778,'System'),
           (3,'3',1432783357778,'System',1432783357778,'System'),
           (4,'1',1432783357778,'System',1432783357778,'System'),
           (5,'2',1432783357778,'System',1432783357778,'System'),
           (6,'3',1432783357778,'System',1432783357778,'System'),
           (7,'1',1432783357778,'System',1432783357778,'System'),
           (8,'2',1432783357778,'System',1432783357778,'System'),
           (9,'3',1432783357778,'System',1432783357778,'System'),
           (10,'1',1432783357778,'System',1432783357778,'System'),
           (11,'2',1432783357778,'System',1432783357778,'System'),
           (12,'3',1432783357778,'System',1432783357778,'System'),
           (13,'1',1432783357778,'System',1432783357778,'System'),
           (14,'2',1432783357778,'System',1432783357778,'System'),
           (15,'3',1432783357778,'System',1432783357778,'System'),
           (16,'1',1432783357778,'System',1432783357778,'System'),
           (17,'3',1432783357778,'System',1432783357778,'System'),
           (18,'2',1432783357778,'System',1432783357778,'System'),
           (19,'1',1432783357778,'System',1432783357778,'System'),
           (20,'2',1432783357778,'System',1432783357778,'System'),
           (21,'3',1432783357778,'System',1432783357778,'System')
/** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[ordemServico]
           ([emprId]
           ,[userId]
           ,[nome]
           ,[data]
           ,[typeId]
           ,[assunto]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,'user','Simples Nacional',1432783357778,1,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
		   (2,'user','Simples Nacional',1432783357778,1,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
		   (3,'user','Simples Nacional',1432783357778,1,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
		   (1,'user','Simples Nacional',1432783357778,1,'erro 00001',1432783357778,'rod',14327833577780,'rod')

           /** ---------------------------------------------------------------*/
		   INSERT INTO [dbo].[ordemServicoItens]
           ([idOrdemServico]
           ,[status]
           ,[data]
           ,[texto]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
            (1,1,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(2,1,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(3,1,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(4,1,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(1,2,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(2,2,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(3,2,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(4,2,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(1,3,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod'),
			(2,3,1432783357778,'erro 00001',1432783357778,'rod',14327833577780,'rod')
           /** ---------------------------------------------------------------*/
			INSERT INTO [dbo].[ordemServicoTypes]
           ([type]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,'Financeiro',1432783357778,'rod',14327833577780,'rod'),
		   (1,'Fiscal',1432783357778,'rod',14327833577780,'rod'),
		   (1,'Comercial',1432783357778,'rod',14327833577780,'rod'),
		   (1,'Sistema',1432783357778,'rod',14327833577780,'rod')
           /** ---------------------------------------------------------------*/
		   INSERT INTO [dbo].[ordemServicoStatus]
           ([status]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           ('Finalizado',1432783357778,'rod',14327833577780,'rod'),
		   ('Aguardando Mais informacoes',1432783357778,'rod',14327833577780,'rod'),
		   ('Aberto',1432783357778,'rod',14327833577780,'rod'),
		   ('Analizando',1432783357778,'rod',14327833577780,'rod'),
		   ('Executando',1432783357778,'rod',14327833577780,'rod')
           /** ---------------------------------------------------------------*/
		   INSERT INTO [dbo].[tabela]
           ([nome]
           ,[descricao]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
   	('REGIME','Test',1432783357778,'system',14327833577780,'system'),
	('EMPRESA','Test',1432783357778,'system',14327833577780,'system'),
	('DOCUMENTO','Test',1432783357778,'system',14327833577780,'system'),
	('EMAIL','Test',1432783357778,'system',14327833577780,'system'),
	('ENDERECO','Test',1432783357778,'system',14327833577780,'system'),
	('TELEFONE','Test',1432783357778,'system',14327833577780,'system'),
	('SOCIO','Test',1432783357778,'system',14327833577780,'system'),
	('HISTORICO','Test',1432783357778,'system',14327833577780,'system'),
	('CNAE','Test',1432783357778,'system',14327833577780,'system'),
	('CSOSN','Test',1432783357778,'system',14327833577780,'system'),
	('NCM','Test',1432783357778,'system',14327833577780,'system'),
	('CIDADE','Test',1432783357778,'system',14327833577780,'system'),
	('CNAEPORRELACIONAMENTO','Test',1432783357778,'system',14327833577780,'system'),
	('ESTADO','Test',1432783357778,'system',14327833577780,'system'),
	('UNIMED','Test',1432783357778,'system',14327833577780,'system'),
	('CFOP','Test',1432783357778,'system',14327833577780,'system'),
	('FUNCIONARIO','Test',1432783357778,'system',14327833577780,'system'),
	('CLIENTE','Test',1432783357778,'system',14327833577780,'system'),
	('FORNECEDOR','Test',1432783357778,'system',14327833577780,'system'),
	('TRANSPORTADOR','Test',1432783357778,'system',14327833577780,'system'),
	('CONDPAG','Test',1432783357778,'system',14327833577780,'system'),
	('CONVENIO','Test',1432783357778,'system',14327833577780,'system'),
	('SALARIO','Test',1432783357778,'system',14327833577780,'system'),
	('PESSOA','Test',1432783357778,'system',14327833577780,'system'),
    ('PROFISSAO','Test',1432783357778,'system',14327833577780,'system'),
	('CONVENIOPESSOA','Test',1432783357778,'system',14327833577780,'system'),
	('CONDPAGPESSOA','Test',1432783357778,'system',14327833577780,'system'),
	('TIPOPAG','Test',1432783357778,'system',14327833577780,'system'),
	('TIPOPAGREG','Test',1432783357778,'system',14327833577780,'system'),
	('BANCO','Test',1432783357778,'system',14327833577780,'system'),
	('AGENCIA','Test',1432783357778,'system',14327833577780,'system'),
	('BANCOPESOA','Test',1432783357778,'system',14327833577780,'system'),
	('CONTATO','Test',1432783357778,'system',14327833577780,'system'),
	('CONTATOITENS','Test',1432783357778,'system',14327833577780,'system'),
	('ORDEMSERVICO','Test',1432783357778,'system',14327833577780,'system'),
	('ORDEMSERVICOITENS','Test',1432783357778,'system',14327833577780,'system'),
	('ORDEMSERVICOTYPES','Test',1432783357778,'system',14327833577780,'system'),
	('ORDEMSERVICOSTATUS','Test',1432783357778,'system',14327833577780,'system'),
	('TABELA','Test',1432783357778,'system',14327833577780,'system'),
	('ATRIBUTOS','Test',1432783357778,'system',14327833577780,'system')

           /** ---------------------------------------------------------------*/
	INSERT INTO [dbo].[unimed]
           ([unimed],[sigla],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('Caixa','CX','System',1432783357778,1432783357778,'System'),
           ('Litro','LT','System',1432783357778,1432783357778,'System'),
           ('Unidade','UN','System',1432783357778,1432783357778,'System'),
           ('Duzia','DZ','System',1432783357778,1432783357778,'System');
           /** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[unimedProd]
           ([unimedId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,'System',1432783357778,1432783357778,'System'),
           (2,2,'System',1432783357778,1432783357778,'System'),
           (3,3,'System',1432783357778,1432783357778,'System'),
           (4,4,'System',1432783357778,1432783357778,'System'),
           (1,5,'System',1432783357778,1432783357778,'System'),
           (2,6,'System',1432783357778,1432783357778,'System'),
           (3,7,'System',1432783357778,1432783357778,'System'),
           (4,8,'System',1432783357778,1432783357778,'System'),
           (1,9,'System',1432783357778,1432783357778,'System'),
           (2,10,'System',1432783357778,1432783357778,'System');
           /** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[grupo]
           ([grupo],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('Grupo001','CX','System',1432783357778,1432783357778,'System'),
           ('Grupo002','LT','System',1432783357778,1432783357778,'System'),
           ('Grupo003','UN','System',1432783357778,1432783357778,'System'),
           ('Grupo004','DZ','System',1432783357778,1432783357778,'System');
           /** ---------------------------------------------------------------*/
           INSERT INTO [dbo].[grupoProd]
           ([grupoId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,'System',1432783357778,1432783357778,'System'),
           (2,2,'System',1432783357778,1432783357778,'System'),
           (3,3,'System',1432783357778,1432783357778,'System'),
           (4,4,'System',1432783357778,1432783357778,'System'),
           (1,5,'System',1432783357778,1432783357778,'System'),
           (2,6,'System',1432783357778,1432783357778,'System'),
           (3,7,'System',1432783357778,1432783357778,'System'),
           (4,8,'System',1432783357778,1432783357778,'System'),
           (1,9,'System',1432783357778,1432783357778,'System'),
           (2,10,'System',1432783357778,1432783357778,'System');

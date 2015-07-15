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
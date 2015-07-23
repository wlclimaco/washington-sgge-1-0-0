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

/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Beneficio]
           ([nome],[codigo],[descricao],[valor],[porcentagem],[tipo],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
            ('Vale Transporte','000001','vale transporte',0,3.5,'desc',1432783357778,'System',1432783357778,'System'),
			('UNIMED','000002','Plano de saude',0,3.5,'desc',1432783357778,'System',1432783357778,'System')

GO
/** ---------------------------------------------------------------*/
INSERT INTO [dbo].[Evento]
           ([data],[descricao],[tipo],[codigo],[noteText],[valor],[porcentagem],[isMensal],[isSistema],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1432783357778,'FGTS','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (1432783357778,'IR','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (1432783357778,'SINDICATO','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (1432783357778,'INSS','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system')

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
           INSERT INTO [dbo].[CondPag]
           ([nome],[dataini],[dataFin],[porcentagem],[valor],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Condicao Pag 0001',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
           ('Condicao Pag 0002',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
           ('Condicao Pag 0003',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/

           INSERT INTO [dbo].[TipoPag]
           ([descricao],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           ('Cartao Credito',1432783357778,'System',1432783357778,'System'),
           ('Boleto',1432783357778,'System',1432783357778,'System'),
           ('Cartao Debito',1432783357778,'System',1432783357778,'System')

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

           INSERT INTO [dbo].[grupo]
           ([grupo],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('Grupo001','CX','System',1432783357778,1432783357778,'System'),
           ('Grupo002','LT','System',1432783357778,1432783357778,'System'),
           ('Grupo003','UN','System',1432783357778,1432783357778,'System'),
           ('Grupo004','DZ','System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[subgrupo]
           ([subgrupo],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('SubGrupo001','CX','System',1432783357778,1432783357778,'System'),
           ('SubGrupo002','LT','System',1432783357778,1432783357778,'System'),
           ('SubGrupo003','UN','System',1432783357778,1432783357778,'System'),
           ('SubGrupo004','DZ','System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/

           INSERT INTO [dbo].[marca]
           ([marca],[fabricante],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('Marca0001','Marca0001','System',1432783357778,1432783357778,'System'),
           ('Marca0002','Marca0002','System',1432783357778,1432783357778,'System'),
           ('Marca0003','Marca0003','System',1432783357778,1432783357778,'System'),
           ('Marca0004','Marca0004','System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/

INSERT INTO [dbo].[cst]
           ([nome],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('T','Marca0001','System',1432783357778,1432783357778,'System'),
           ('I','Marca0002','System',1432783357778,1432783357778,'System'),
           ('N','Marca0003','System',1432783357778,1432783357778,'System'),
           ('F','Marca0004','System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/

           INSERT INTO [dbo].[incidencia]
           ([codigo],[texto],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('00','Nenhuma','System',1432783357778,1432783357778,'System'),
           ('01','Monofasica','System',1432783357778,1432783357778,'System'),
           ('02','Subst Tributaria','System',1432783357778,1432783357778,'System'),
           ('03','Aliguota 0','System',1432783357778,1432783357778,'System'),
           ('04','Suspensão','System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/

           INSERT INTO [dbo].[classificacao]
           ([codigo],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('00','Mercadoria para Revenda','System',1432783357778,1432783357778,'System'),
           ('01','Materia Prima','System',1432783357778,1432783357778,'System'),
           ('02','Embalagem','System',1432783357778,1432783357778,'System'),
           ('03','Produto em Processo','System',1432783357778,1432783357778,'System'),
           ('04','Produto Acabado','System',1432783357778,1432783357778,'System'),
           ('05','SubProduto','System',1432783357778,1432783357778,'System'),
           ('06','Produto Intermediario','System',1432783357778,1432783357778,'System'),
           ('07','Material de Uso e Consumo','System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/
    INSERT INTO [dbo].[users] ([username], [password],[enabled],[entidadeId]) VALUES
     ('rod', 'koala', 1,1),
     ('washington', 'koala', 1,1),
     ('admin', 'admin', 1,1);

/** ---------------------------------------------------------------*/
    INSERT INTO [dbo].[user_roles] ([username], [ROLE]) VALUES
     ('washington', 'ROLE_DOMAIN'),
     ('washington', 'ROLE_ADMIN'),
     ('admin', 'ROLE_CSR'),
     ('rod', 'ROLE_CSR'),
     ('rod', 'ROLE_ADMIN');

/** ---------------------------------------------------------------*/


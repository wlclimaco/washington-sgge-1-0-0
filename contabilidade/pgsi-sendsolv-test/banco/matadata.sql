DROP TABLE [dbo].[regime];
CREATE TABLE [dbo].[regime](
	[id]           [int] NOT NULL,
	[nome]         [varchar](100) NULL,
	[descricao]    [varchar](200) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_regime_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[regime]
           ([id]
           ,[nome]
           ,[descricao]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,'Simples Nacional','Simples Nacional',1432783357778,'rod',14327833577780,'rod'),
		   (2,'Lucro Real','Lucro Real',14327833577780,'rod',1000000,'rod'),
		   (3,'Lucro Presumido','Lucro Presumido',14327833577780,'rod',1000000,'rod')
GO
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[empresa];
CREATE TABLE [dbo].[empresa] (
	[id]           [int] NOT NULL,
	[nome]         [varchar](100) NULL,
	[regime]       [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_socio_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[empresa]
           ([id]
           ,[nome]
           ,[regime]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,'Telhas Tecplan',1,1000000,'rod',1000000,'rod'),
		   (2,'boteckin do damiao',2,1000000,'rod',1000000,'rod'),
		   (3,'silvio casa de ração',3,1000000,'rod',1000000,'rod')

GO
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[documento];
CREATE TABLE [dbo].[documento](
	[id]           [int] NOT NULL,
	[tabela]	 [int] NULL,
	[parentId]	 [int] NULL,
	[type]         [int] NULL,
	[description]  [varchar](200) NULL,
	[numero]       [varchar](50) NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[estado]       [varchar](2) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_documento_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



INSERT INTO [dbo].[documento]
           ([id]
           ,[tabela]
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
           (1,1,1,1,'CNPJ','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (2,1,2,1,'CPF','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (3,1,3,1,'IM','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (4,1,1,1,'IE','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (5,1,2,1,'RG','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (6,1,3,1,'CGC','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod')
GO

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[email];
CREATE TABLE [dbo].[email](
	[id]           [int] NOT NULL,
	[tabela]	   [int] NULL,
	[parentId]	   [int] NULL,
	[type]         [int] NULL,
	[email]        [varchar](100)  NULL,
	[description]  [varchar](200) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,

CONSTRAINT [pk_email_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[email]
           ([id]
           ,[tabela]
           ,[parentId]
           ,[type]
           ,[email]
           ,[description]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (2,1,2,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (3,1,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (4,1,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (5,1,2,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (6,1,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod')
GO

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[endereco];
CREATE TABLE [dbo].[endereco](
	[id]           [int] NOT NULL,
	[tabela]		 [int]  NULL,
	[parentId]	 [int]  NULL,
	[type]         [int]  NULL,
	[logradouro]	 [varchar](200)  NULL,
	[cidade]		 [varchar](100)  NULL,
	[estado]		 [varchar](2)  NULL,
	[bairro]		 [varchar](50)  NULL,
	[numero]       [varchar](10)  NULL,
	[cep]			 [varchar](15)  NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50)  NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50)  NULL,
CONSTRAINT [pk_endereco_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[endereco]
           ([id]
           ,[tabela]
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
           (1,1,1,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (2,1,2,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (3,1,3,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (4,1,1,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (5,1,2,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (6,1,3,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod')
GO
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[telefone];
CREATE TABLE [dbo].[telefone] (
	[id]           [int] not NULL,
	[type]         [int] NULL,
	[parentId]	   [int] NULL,
	[tabela]	   [int] NULL,
	[ddd] 		   [varchar](5) NULL,
	[telefone]     [varchar](15) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_telefone_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

INSERT INTO [dbo].[telefone]
           ([id]
           ,[type]
           ,[parentId]
           ,[tabela]
           ,[ddd]
           ,[telefone]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,1,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (2,1,1,2,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (3,1,1,3,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (4,1,1,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (5,1,2,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (6,1,2,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (7,1,3,1,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (8,1,3,1,'034','91782776',1432783357778,'system',1432783357778,'system')
GO





/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[socio];
CREATE TABLE [dbo].[socio](
	[id]           [int] NOT NULL,
	[parentId]	   [int] NULL,
	[nome]         [varchar](200) NULL,
	[cota]         [varchar](10) NULL,
	[porcentagem]  [varchar](10) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,

CONSTRAINT [pk_socios_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[historico];
CREATE TABLE [dbo].[historico] (
	[id]           [int] NOT NULL,
	[parentId]	   [int] NOT NULL,
	[tabela]	   [int] NULL,
	[type]         [int] NULL,
	[acao]         [int] NULL,
	[registro]     [varchar](200) NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[usuario]      [varchar](20) NULL,
	[empresa]      [int] NULL,
	[status]       [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_historico_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[CNAE] (
    [CODIGO]     [VARCHAR](6) NOT NULL,
    [CNAE]       [VARCHAR](10) NULL,
    [DESCRICAO]  [VARCHAR](100) NULL,
    [ABREVIADO]  [VARCHAR](50) NULL,
    [status]       [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cnae_id] PRIMARY KEY CLUSTERED
(
	[CODIGO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



CREATE TABLE [dbo].[CSOSN] (
    [CODIGO]     [VARCHAR](3) NOT NULL,
    [DESCRICAO]  [VARCHAR](200) NULL,
    [status]       [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_csosn_id] PRIMARY KEY CLUSTERED
(
	[CODIGO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[NCM] (
    [NCM]        [VARCHAR](10) NOT NULL,
    [DESCRICAO]  [VARCHAR](100) NULL,
    [UNIDADE]    [VARCHAR](2) NULL,
    [status]       [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ncm_id] PRIMARY KEY CLUSTERED
(
	[NCM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** --------------------------------------------------------------- */
DROP TABLE  [dbo].[CIDADE];

CREATE TABLE [dbo].[CIDADE](
    [CODIGO]     [VARCHAR](6) NOT NULL,
    [CIDADE]     [VARCHAR](40) NULL,
    [UF]         [VARCHAR](2) NULL,
    [CEP]        [VARCHAR](10) NULL,
    [IBGE]       [VARCHAR](10) NULL,
    [ESTADO]     [VARCHAR](10) NULL,
    [MUNICIPIO]  [VARCHAR](10) NULL,
    [status]       [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cidade_id] PRIMARY KEY CLUSTERED
(
	[CODIGO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/** --------------------------------------------------------------- */
DROP TABLE  [dbo].[cnaePorRelacionamento];
CREATE TABLE [dbo].[cnaePorRelacionamento](
    [id]         [int] NOT NULL,
    [idCnae]     [varchar](50) not NULL,
    [idParentId] [int] NOT NULL,
    [tabela]     [int] NOT NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cnaeRelacionamento_id] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[cnaePorRelacionamento]
           ([id]
           ,[idCnae]
           ,[idParentId]
           ,[tabela]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,'000001',1,1,14327833577780,'system' ,14327833577780,'system'),
		   (2,'000002',1,1,14327833577780,'system' ,14327833577780,'system'),
		   (3,'000003',1,1,14327833577780,'system' ,14327833577780,'system'),
		   (4,'000004',2,1,14327833577780,'system' ,14327833577780,'system'),
		   (5,'000005',2,1,14327833577780,'system' ,14327833577780,'system'),
		   (6,'000006',3,1,14327833577780,'system' ,14327833577780,'system'),
		   (7,'000008',3,1,14327833577780,'system' ,14327833577780,'system'),
		   (8,'000007',3,1,14327833577780,'system' ,14327833577780,'system'),
		   (9,'000009',2,1,14327833577780,'system' ,14327833577780,'system'),
		   (10,'000011',1,1,14327833577780,'system' ,14327833577780,'system')
GO
/** --------------------------------------------------------------- */
DROP TABLE  [dbo].[status];

CREATE TABLE [dbo].[status](
    [id]           [int] NOT NULL,
    [dataStatus]   [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [parentId]     [int] NOT NULL,
    [status] 	   [int] NOT NULL,
    [acaoType]     [int] NOT NULL,
    [tabelaEnum]   [int] NOT NULL,
    [note]    	   [varchar](50) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_status_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[status]
           ([id]
           ,[dataStatus]
           ,[parentId]
           ,[status]
           ,[acaoType]
           ,[tabelaEnum]
           ,[note]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,14327833577780,1,1,1,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (2,14327833577780,2,2,1,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (3,14327833577780,3,3,1,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (4,14327833577780,1,1,2,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (5,14327833577780,2,2,2,2,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (6,14327833577780,3,3,2,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (7,14327833577780,1,3,3,3,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (8,14327833577780,2,2,3,1,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (9,14327833577780,3,1,3,6,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod'),
		   (10,14327833577780,1,1,4,7,'Rua Maria Conceicao silva',14327833577780,'rod',14327833577780,'rod')
GO

/** ---------------------------------------------------------------*/

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[funcionario];
CREATE TABLE [dbo].[funcionario](
	[id]           [int] NOT NULL,
	[cdEmpr]       [int] NULL,
	[matricula]    [varchar](20) NULL,
	[nome]         [varchar](200) NULL,
	[sexo]         [int] NULL,
	[dataAdm]      [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_funcionario_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];
INSERT INTO [dbo].[funcionario]
           ([id],[cdEmpr],[matricula] ,[nome],[dataAdm],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,'8460','8460' ,14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (2,1,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (3,1,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (4,1,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (5,1,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (6,2,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (7,2,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (8,2,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (9,2,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (10,2,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (11,3,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (12,3,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (13,3,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (14,3,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system'),
		   (15,3,'8460','8460',14327833577780,14327833577780 ,'system' ,14327833577780,'system')

GO

INSERT INTO [dbo].[documento]
           ([id],[tabela],[parentId],[type],[description],[numero],[data] ,[estado],[create_date] ,[create_user],[modify_date],[modify_user])
     VALUES
           (7,16,1,1,'CNPJ','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (8,16,2,1,'CPF','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (9,16,3,1,'IM','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (10,16,4,1,'IE','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (11,16,5,1,'RG','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (12,16,6,1,'CGC','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (13,16,7,1,'CNPJ','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (14,16,8,1,'CPF','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (15,16,9,1,'IM','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (16,16,10,1,'IE','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (17,16,11,1,'RG','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (18,16,12,1,'CGC','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (19,16,13,1,'CNPJ','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (20,16,14,1,'CPF','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (21,16,15,1,'IM','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (22,16,1,1,'IE','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (23,16,2,1,'RG','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod'),
		   (24,16,3,1,'CGC','2001',14327833577780,'MG',14327833577780,'rod',14327833577780,'rod')
GO

INSERT INTO [dbo].[email]
           ([id],[tabela],[parentId],[type],[email],[description],[create_date],[create_user] ,[modify_date],[modify_user])
     VALUES
           (7,16,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (8,16,2,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (9,16,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (10,16,4,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (11,16,5,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (12,16,6,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (13,16,7,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (14,16,8,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (15,16,9,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (16,16,10,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (17,16,11,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (18,16,12,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (19,16,13,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (20,16,14,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (21,16,15,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (22,16,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (23,16,1,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod'),
		   (24,16,3,1,'wwww.tessr@gmail.com','email',14327833577780,'rod',1432783357778,'rod')
GO

INSERT INTO [dbo].[endereco]
           ([id],[tabela],[parentId],[type],[logradouro],[cidade],[estado],[bairro],[numero],[cep],[create_date] ,[create_user],[modify_date],[modify_user])
     VALUES
           (7,16,1,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (8,16,2,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (9,16,3,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (10,16,4,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (11,16,5,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (12,16,6,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (13,16,7,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (14,16,8,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (15,16,9,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (16,16,10,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (17,16,11,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (18,16,12,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (19,16,13,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (20,16,14,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (21,16,15,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod'),
		   (22,16,1,2,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',14327833577780,'rod',10000002,'rod')
GO

INSERT INTO [dbo].[telefone]
           ([id],[type] ,[parentId],[tabela],[ddd],[telefone],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (9,1,1,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (10,1,2,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (11,1,3,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (12,1,4,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (13,1,5,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (14,1,6,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (15,1,7,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (16,1,8,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (17,1,9,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (18,1,10,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (19,1,11,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (20,1,12,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (21,1,13,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (22,1,14,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (23,1,15,16,'034','91782776',1432783357778,'system',1432783357778,'system'),
		   (24,2,1,16,'034','91782776',1432783357778,'system',1432783357778,'system')
GO

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[horarios];
CREATE TABLE [dbo].[horarios](
	[id]           [int] NOT NULL,
	[parentId]     [int] NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[horarioEntr]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[horarioSair]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[tipo]     	   [varchar](50) NULL,
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_horario_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
USE [PGSi]
GO

INSERT INTO [dbo].[horarios]
           ([id],[parentId],[data],[horarioEntr],[horarioSair],[create_date],[tipo],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (2,2,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (3,3,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (4,4,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (5,5,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (6,6,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (7,7,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (8,8,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (9,9,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (10,10,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (11,11,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (12,12,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (13,13,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (14,14,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (15,15,1432783357778,1432783357778,1432783357778,1432783357778,'Entrada','system',1432783357778,'system'),
		   (16,1,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (17,2,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (18,3,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (19,4,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (20,5,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (21,6,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (22,7,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (23,8,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (24,9,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (25,10,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (26,11,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (27,12,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (28,13,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (29,14,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system'),
		   (30,15,1432783357778,1432783357778,1432783357778,1432783357778,'Saida','system',1432783357778,'system')

GO



/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Salario];
CREATE TABLE [dbo].[Salario](
	[id]           [int] NOT NULL,
	[parentId]     [int] NULL,
	[valor]        [real] NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_salario_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[Salario]
           ([id],[parentId],[valor],[data],[create_date],[create_user],[modify_user],[modify_date])
     VALUES
           (1,1,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (2,2,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (3,3,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (4,4,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (5,5,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (6,6,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (7,7,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (8,8,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (9,9,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (10,10,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (11,11,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (12,12,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (13,13,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (14,14,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (15,15,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (16,1,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (17,2,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (18,3,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (19,4,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778),
		   (20,5,720.99,1432783357778,1432783357778,'sistem' ,'System',1432783357778)
GO
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Beneficio];
CREATE TABLE [dbo].[Beneficio](
	[id]           [int] NOT NULL,
	[nome]         [varchar](50) NULL,
    [codigo]       [varchar](50) NULL,
    [descricao]    [varchar](50) NULL,
	[valor]        [real] NULL,
	[porcentagem]  [real] NULL,
	[tipo]		   [varchar](10) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_beneficio_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[Beneficio]
           ([id],[nome],[codigo],[descricao],[valor],[porcentagem],[tipo],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
            (1,'Vale Transporte','000001','vale transporte',0,3.5,'desc',1432783357778,'System',1432783357778,'System'),
			(2,'UNIMED','000002','Plano de saude',0,3.5,'desc',1432783357778,'System',1432783357778,'System')

GO


DROP TABLE [dbo].[BeneficioFunc];
CREATE TABLE [dbo].[BeneficioFunc](
	[id]           [int] NOT NULL,
	[idFunc]       [int] NOT NULL,
	[idBenef]       [int] NOT NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_beneficioBene_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[BeneficioFunc]
           ([id],[idFunc],[idBenef],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1,1432783357778,'System',1432783357778,'System'),
		   (2,2,1,1432783357778,'System',1432783357778,'System'),
		   (3,3,1,1432783357778,'System',1432783357778,'System'),
		   (4,4,1,1432783357778,'System',1432783357778,'System'),
		   (5,5,1,1432783357778,'System',1432783357778,'System'),
		   (6,6,1,1432783357778,'System',1432783357778,'System'),
		   (7,7,1,1432783357778,'System',1432783357778,'System'),
		   (8,8,1,1432783357778,'System',1432783357778,'System'),
		   (9,9,1,1432783357778,'System',1432783357778,'System'),
		   (10,10,1,1432783357778,'System',1432783357778,'System'),
		   (11,11,1,1432783357778,'System',1432783357778,'System'),
		   (12,12,1,1432783357778,'System',1432783357778,'System'),
		   (13,13,1,1432783357778,'System',1432783357778,'System'),
		   (14,14,1,1432783357778,'System',1432783357778,'System'),
		   (15,15,1,1432783357778,'System',1432783357778,'System'),
		   (16,1,2,1432783357778,'System',1432783357778,'System'),
		   (17,2,2,1432783357778,'System',1432783357778,'System'),
		   (18,3,2,1432783357778,'System',1432783357778,'System'),
		   (19,4,2,1432783357778,'System',1432783357778,'System'),
		   (20,5,2,1432783357778,'System',1432783357778,'System'),
		   (21,6,2,1432783357778,'System',1432783357778,'System'),
		   (22,7,2,1432783357778,'System',1432783357778,'System'),
		   (23,8,2,1432783357778,'System',1432783357778,'System'),
		   (24,9,2,1432783357778,'System',1432783357778,'System'),
		   (25,10,2,1432783357778,'System',1432783357778,'System'),
		   (26,11,2,1432783357778,'System',1432783357778,'System'),
		   (27,12,2,1432783357778,'System',1432783357778,'System'),
		   (28,13,2,1432783357778,'System',1432783357778,'System'),
		   (29,14,2,1432783357778,'System',1432783357778,'System'),
		   (30,15,2,1432783357778,'System',1432783357778,'System')
GO

CREATE TABLE [dbo].[BeneficioMesApp](
	[id]           [int] NOT NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[idFuncBenef] [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoFuncEventApp_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
INSERT INTO [dbo].[BeneficioMesApp]
           ([id],[data],[idFuncBenef],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1432783357778,1,1432783357778,'System',1432783357778,'System'),
		   (2,1432783357778,2,1432783357778,'System',1432783357778,'System'),
		   (3,1432783357778,3,1432783357778,'System',1432783357778,'System'),
		   (4,1432783357778,4,1432783357778,'System',1432783357778,'System'),
		   (5,1432783357778,5,1432783357778,'System',1432783357778,'System'),
		   (6,1432783357778,6,1432783357778,'System',1432783357778,'System'),
		   (7,1432783357778,7,1432783357778,'System',1432783357778,'System'),
		   (8,1432783357778,8,1432783357778,'System',1432783357778,'System'),
		   (9,1432783357778,9,1432783357778,'System',1432783357778,'System'),
		   (10,1432783357778,10,1432783357778,'System',1432783357778,'System'),
		   (11,1432783357778,11,1432783357778,'System',1432783357778,'System'),
		   (12,1432783357778,12,1432783357778,'System',1432783357778,'System'),
		   (13,1432783357778,13,1432783357778,'System',1432783357778,'System'),
		   (14,1432783357778,14,1432783357778,'System',1432783357778,'System'),
		   (15,1432783357778,15,1432783357778,'System',1432783357778,'System'),
		   (16,1432783357778,16,1432783357778,'System',1432783357778,'System'),
		   (17,1432783357778,17,1432783357778,'System',1432783357778,'System')
GO
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Evento];
CREATE TABLE [dbo].[Evento](
	[id]           [int] NOT NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[descricao]    [varchar](50) NULL,
	[tipo]         [varchar](50) NULL,
    [codigo]       [varchar](50) NULL,
    [noteText]     [varchar](200) NULL,
	[valor]        [real] NULL,
	[porcentagem]  [real] NULL,
	[isMensal]     [bit] NULL,
	[isSistema]    [bit] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_evento_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[Evento]
           ([id],[data],[descricao],[tipo],[codigo],[noteText],[valor],[porcentagem],[isMensal],[isSistema],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1432783357778,'FGTS','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (2,1432783357778,'IR','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (3,1432783357778,'SINDICATO','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system'),
		   (4,1432783357778,'INSS','desconto','00001','hhhhhhhhh',0,8,1,1,1432783357778,'System',1432783357778 ,'system')
GO


DROP TABLE [dbo].[EventoFunc];
CREATE TABLE [dbo].[EventoFunc](
	[id]           [int] NOT NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[idFunc]	   [int] NULL,
	[idEvent]	   [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoFunc_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[EventoFunc]
           ([id],[data],[idFunc],[create_date],[create_user],[modify_date],[modify_user],[idEvent])
     VALUES
           (1,1432783357778,1,1432783357778,'System',1432783357778,'System',1),
		   (2,1432783357778,2,1432783357778,'System',1432783357778,'System',1),
		   (3,1432783357778,3,1432783357778,'System',1432783357778,'System',1),
		   (4,1432783357778,4,1432783357778,'System',1432783357778,'System',1),
		   (5,1432783357778,5,1432783357778,'System',1432783357778,'System',1),
		   (6,1432783357778,6,1432783357778,'System',1432783357778,'System',1),
		   (7,1432783357778,7,1432783357778,'System',1432783357778,'System',1),
		   (8,1432783357778,8,1432783357778,'System',1432783357778,'System',1),
		   (9,1432783357778,9,1432783357778,'System',1432783357778,'System',1),
		   (10,1432783357778,10,1432783357778,'System',1432783357778,'System',1),
		   (11,1432783357778,11,1432783357778,'System',1432783357778,'System',1),
		   (12,1432783357778,12,1432783357778,'System',1432783357778,'System',1),
		   (13,1432783357778,13,1432783357778,'System',1432783357778,'System',1),
		   (14,1432783357778,14,1432783357778,'System',1432783357778,'System',1),
		   (15,1432783357778,15,1432783357778,'System',1432783357778,'System',1),
		   (16,1432783357778,1,1432783357778,'System',1432783357778,'System',2),
		   (17,1432783357778,2,1432783357778,'System',1432783357778,'System',2),
		   (18,1432783357778,3,1432783357778,'System',1432783357778,'System',2),
		   (19,1432783357778,4,1432783357778,'System',1432783357778,'System',2),
		   (20,1432783357778,5,1432783357778,'System',1432783357778,'System',2),
		   (21,1432783357778,6,1432783357778,'System',1432783357778,'System',2),
		   (22,1432783357778,7,1432783357778,'System',1432783357778,'System',2),
		   (23,1432783357778,8,1432783357778,'System',1432783357778,'System',2),
		   (24,1432783357778,9,1432783357778,'System',1432783357778,'System',2),
		   (25,1432783357778,10,1432783357778,'System',1432783357778,'System',2),
		   (26,1432783357778,11,1432783357778,'System',1432783357778,'System',2),
		   (27,1432783357778,12,1432783357778,'System',1432783357778,'System',2),
		   (28,1432783357778,13,1432783357778,'System',1432783357778,'System',2),
		   (29,1432783357778,14,1432783357778,'System',1432783357778,'System',2),
		   (30,1432783357778,15,1432783357778,'System',1432783357778,'System',2),
		   (31,1432783357778,1,1432783357778,'System',1432783357778,'System',3),
		   (32,1432783357778,2,1432783357778,'System',1432783357778,'System',3),
		   (33,1432783357778,3,1432783357778,'System',1432783357778,'System',3),
		   (34,1432783357778,4,1432783357778,'System',1432783357778,'System',3),
		   (35,1432783357778,5,1432783357778,'System',1432783357778,'System',3),
		   (36,1432783357778,6,1432783357778,'System',1432783357778,'System',3),
		   (37,1432783357778,7,1432783357778,'System',1432783357778,'System',3),
		   (38,1432783357778,8,1432783357778,'System',1432783357778,'System',3),
		   (39,1432783357778,9,1432783357778,'System',1432783357778,'System',3),
		   (40,1432783357778,10,1432783357778,'System',1432783357778,'System',3),
		   (41,1432783357778,11,1432783357778,'System',1432783357778,'System',3),
		   (42,1432783357778,12,1432783357778,'System',1432783357778,'System',3),
		   (43,1432783357778,13,1432783357778,'System',1432783357778,'System',3),
		   (44,1432783357778,14,1432783357778,'System',1432783357778,'System',3),
		   (45,1432783357778,15,1432783357778,'System',1432783357778,'System',3)

GO


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[EventoMesApp];
CREATE TABLE [dbo].[EventoMesApp](
	[id]           [int] NOT NULL,
	[data]         [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[idFuncEnvent] [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoFuncEventApp_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[EventoMesApp]
           ([id],[data],[idFuncEnvent],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1432783357778,1,1432783357778,'System',1432783357778,'System'),
		   (2,1432783357778,2,1432783357778,'System',1432783357778,'System'),
		   (3,1432783357778,3,1432783357778,'System',1432783357778,'System'),
		   (4,1432783357778,4,1432783357778,'System',1432783357778,'System'),
		   (5,1432783357778,5,1432783357778,'System',1432783357778,'System'),
		   (6,1432783357778,6,1432783357778,'System',1432783357778,'System'),
		   (7,1432783357778,7,1432783357778,'System',1432783357778,'System'),
		   (8,1432783357778,8,1432783357778,'System',1432783357778,'System'),
		   (9,1432783357778,9,1432783357778,'System',1432783357778,'System'),
		   (10,1432783357778,10,1432783357778,'System',1432783357778,'System'),
		   (11,1432783357778,11,1432783357778,'System',1432783357778,'System'),
		   (12,1432783357778,12,1432783357778,'System',1432783357778,'System'),
		   (13,1432783357778,13,1432783357778,'System',1432783357778,'System'),
		   (14,1432783357778,14,1432783357778,'System',1432783357778,'System'),
		   (15,1432783357778,15,1432783357778,'System',1432783357778,'System'),
		   (16,1432783357778,16,1432783357778,'System',1432783357778,'System'),
		   (17,1432783357778,17,1432783357778,'System',1432783357778,'System')
GO

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Pessoa];
CREATE TABLE [dbo].[Pessoa](
	[id]           [int] NOT NULL,
	[nome]         [varchar](200) NULL,
	[cdEmpr] 	   [int] NOT NULL,
	[sexo] 		   [int] NULL,
	[type]         [int] NOT NULL,
	[dataNasc]     [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[nomePai] 	   [varchar](200) NULL,
	[nomeMae]      [varchar](200) NULL,
	[nomeConjugue] [varchar](200) NULL,
	[estadoCivil]  [int] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoPessoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[Pessoa]
           ([id],[nome],[cdEmpr],[sexo],[type],[dataNasc],[nomePai],[nomeMae],[nomeConjugue],[estadoCivil],[create_date],[modify_date],[modify_user],[create_user])
     VALUES
           (1,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (2,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (3,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (4,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (5,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (6,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (7,'washington',1,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (8,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (9,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (10,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (11,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (12,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (13,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (14,'washington',2,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (15,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (16,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (17,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (18,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (19,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (20,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (21,'washington',3,1,1,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (22,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (23,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (24,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (25,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (26,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (27,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (28,'washington',1,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (29,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (30,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (31,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (32,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (33,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (34,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (35,'washington',2,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (36,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (37,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (38,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (39,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (40,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (41,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (42,'washington',3,1,2,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (43,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (44,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (45,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (46,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (47,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (48,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (49,'washington',1,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (50,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (51,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (52,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (53,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (54,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (55,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (56,'washington',2,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (57,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (58,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (59,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (60,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (61,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (62,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System'),
		   (63,'washington',3,1,3,143278335777,'cosme','debora','graciane barbosa',1,1432783357778,1432783357778,'System','System')
GO

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Profissao];
CREATE TABLE [dbo].[Profissao](
	[id]          	 [int] NOT NULL,
	[parentId]     	 [int] NOT NULL,
	[profissao] 	 [varchar](100) NULL,
	[renda] 		 [real] NULL,
	[dataAdmissao]   [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_Profissao_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[Profissao]
           ([id],[parentId],[profissao],[renda],[dataAdmissao],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (2,2,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (3,3,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (4,4,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (5,5,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (6,6,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (7,7,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (8,8,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (9,9,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (10,10,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (11,11,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (12,12,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (13,13,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (14,14,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (15,15,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (16,16,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (17,17,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (18,18,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (19,19,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (20,20,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System'),
		   (21,21,'Analista de Sistemas',720.00,1432783357778,1432783357778,'System',1432783357778,'System')
GO

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Convenio];
CREATE TABLE [dbo].[Convenio](
	[id]          	 [int] NOT NULL,
	[nome] 	 		 [varchar](100) NULL,
	[dataini]   	 [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataFin]   	 [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[porcentagem] 	 [real] NULL,
	[valor] 		 [real] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_Convenio_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[Convenio]
           ([id],[nome],[dataini],[dataFin],[porcentagem],[valor],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Convenio 0001',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   (2,'Convenio 0002',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   (3,'Convenio 0003',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   (4,'Convenio 0004',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
		   (5,'Convenio 0005',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[ConvenioPessoa];
CREATE TABLE [dbo].[ConvenioPessoa](
	[id]          	 [int] NOT NULL,
	[parentId] 	 	 [int] NOT NULL,
	[convId]   	 [int] NOT NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ConvenioPessoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[ConvenioPessoa]
           ([id],[parentId],[convId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1,1432783357778,'System',1432783357778,'System'),
           (2,2,2,1432783357778,'System',1432783357778,'System'),
           (3,3,3,1432783357778,'System',1432783357778,'System'),
           (4,4,4,1432783357778,'System',1432783357778,'System'),
           (5,5,5,1432783357778,'System',1432783357778,'System'),
           (6,6,1,1432783357778,'System',1432783357778,'System'),
           (7,7,2,1432783357778,'System',1432783357778,'System'),
           (8,8,3,1432783357778,'System',1432783357778,'System'),
           (9,9,4,1432783357778,'System',1432783357778,'System'),
           (10,10,5,1432783357778,'System',1432783357778,'System'),
           (11,11,1,1432783357778,'System',1432783357778,'System'),
           (12,12,2,1432783357778,'System',1432783357778,'System'),
           (13,13,3,1432783357778,'System',1432783357778,'System'),
           (14,14,4,1432783357778,'System',1432783357778,'System'),
           (15,15,5,1432783357778,'System',1432783357778,'System'),
           (16,16,1,1432783357778,'System',1432783357778,'System'),
           (17,17,1,1432783357778,'System',1432783357778,'System'),
           (18,18,2,1432783357778,'System',1432783357778,'System'),
           (19,19,3,1432783357778,'System',1432783357778,'System'),
           (20,20,4,1432783357778,'System',1432783357778,'System'),
           (21,21,5,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[CondPag];
CREATE TABLE [dbo].[CondPag](
	[id]          	 [int] NOT NULL,
	[nome] 	 		 [varchar](100) NULL,
	[dataini]   	 [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataFin]   	 [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[porcentagem] 	 [real] NULL,
	[valor] 		 [real] NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_CondPag_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[CondPag]
           ([id],[nome],[dataini],[dataFin],[porcentagem],[valor],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Condicao Pag 0001',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
           (2,'Condicao Pag 0002',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System'),
           (3,'Condicao Pag 0003',1432783357778,1432783357778,2,0,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[CondPagPessoa];
CREATE TABLE [dbo].[CondPagPessoa](
	[id]          	 [int] NOT NULL,
	[parentId] 	 	 [int] NOT NULL,
	[condPagId]   	 [int] NOT NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_CondPagPessoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[CondPagPessoa]
           ([id],[parentId],[condPagId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1,1432783357778,'System',1432783357778,'System'),
           (2,2,2,1432783357778,'System',1432783357778,'System'),
           (3,3,3,1432783357778,'System',1432783357778,'System'),
           (4,4,1,1432783357778,'System',1432783357778,'System'),
           (5,5,2,1432783357778,'System',1432783357778,'System'),
           (6,6,3,1432783357778,'System',1432783357778,'System'),
           (7,7,2,1432783357778,'System',1432783357778,'System'),
           (8,8,3,1432783357778,'System',1432783357778,'System'),
           (9,9,1,1432783357778,'System',1432783357778,'System'),
           (10,10,2,1432783357778,'System',1432783357778,'System'),
           (11,11,3,1432783357778,'System',1432783357778,'System'),
           (12,12,2,1432783357778,'System',1432783357778,'System'),
           (13,13,3,1432783357778,'System',1432783357778,'System'),
           (14,14,1,1432783357778,'System',1432783357778,'System'),
           (15,15,3,1432783357778,'System',1432783357778,'System'),
           (16,16,1,1432783357778,'System',1432783357778,'System'),
           (17,17,2,1432783357778,'System',1432783357778,'System'),
           (18,18,2,1432783357778,'System',1432783357778,'System'),
           (19,19,3,1432783357778,'System',1432783357778,'System'),
           (20,20,1,1432783357778,'System',1432783357778,'System'),
           (21,21,2,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[TipoPag];
CREATE TABLE [dbo].[TipoPag](
	[id]          	 [int] NOT NULL,
	[descricao] 	 [varchar](100) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoTipoPag_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[TipoPag]
           ([id],[descricao],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Cartao Credito',1432783357778,'System',1432783357778,'System'),
           (2,'Boleto',1432783357778,'System',1432783357778,'System'),
           (3,'Cartao Debito',1432783357778,'System',1432783357778,'System')
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[TipoPagReg];
CREATE TABLE [dbo].[TipoPagReg](
	[id]          	         [int] NOT NULL,
	[parentId]          	 [int] NOT NULL,
	[tipoPagId]          	 [int] NOT NULL,
	[tabela]          	     [int] NOT NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoTipoPagReg_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[TipoPagReg]
           ([id],[parentId],[tipoPagId],[tabela],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,1,21,'System',1432783357778,1432783357778,'System'),
           (2,2,2,21,'System',1432783357778,1432783357778,'System'),
           (3,3,3,21,'System',1432783357778,1432783357778,'System'),
           (4,4,4,21,'System',1432783357778,1432783357778,'System'),
           (5,5,5,21,'System',1432783357778,1432783357778,'System'),
           (6,6,1,21,'System',1432783357778,1432783357778,'System'),
           (7,7,1,20,'System',1432783357778,1432783357778,'System'),
           (8,8,2,20,'System',1432783357778,1432783357778,'System'),
           (9,9,3,20,'System',1432783357778,1432783357778,'System'),
           (10,10,1,20,'System',1432783357778,1432783357778,'System'),
           (11,11,1,21,'System',1432783357778,1432783357778,'System'),
           (12,12,2,21,'System',1432783357778,1432783357778,'System'),
           (13,13,3,21,'System',1432783357778,1432783357778,'System'),
           (14,14,4,21,'System',1432783357778,1432783357778,'System'),
           (15,15,5,21,'System',1432783357778,1432783357778,'System'),
           (16,16,1,21,'System',1432783357778,1432783357778,'System'),
           (17,17,1,20,'System',1432783357778,1432783357778,'System'),
           (18,18,2,20,'System',1432783357778,1432783357778,'System'),
           (19,19,1,20,'System',1432783357778,1432783357778,'System'),
           (20,20,2,20,'System',1432783357778,1432783357778,'System'),
           (21,21,3,20,'System',1432783357778,1432783357778,'System')


/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[banco];
CREATE TABLE [dbo].[banco](
	[id]          	         [int] NOT NULL,
	[nome]          	 	 [varchar](50) NULL,
	[logo]          	 	 [varchar](50) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_banco_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[banco]
           ([id],[nome],[logo],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Banco Brasil','opt:/imagem/',1432783357778,'System',1432783357778,'System'),
           (2,'Itau','opt:/imagem/',1432783357778,'System',1432783357778,'System'),
           (3,'Caixa','opt:/imagem/',1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[agencia];
CREATE TABLE [dbo].[agencia](
	[id]          	         [int] NOT NULL,
	[nome]          	 	 [varchar](50) NULL,
	[parentId]          	 [int] NOT NULL,
	[numero]          	 	 [varchar](50) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_agencia_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[agencia]
           ([id],[nome],[parentId],[numero],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Agencia 0001',1,'00000-25',1432783357778,'System',1432783357778,'System'),
           (2,'Agencia 0001',2,'00000-26',1432783357778,'System',1432783357778,'System'),
           (3,'Agencia 0001',3,'00000-27',1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[BancoPesoa];
CREATE TABLE [dbo].[BancoPesoa](
	[id]          	         [int] NOT NULL,
	[parentId]          	 [int] NOT NULL,
	[bancoId]          	 	 [int] NOT NULL,
	[numCont]          	     [varchar](50) NULL,
	[saldo]          	     [real] NOT NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_BancoPesoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[BancoPesoa]
           ([id],[parentId],[bancoId],[numCont],[saldo],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (2,2,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (3,3,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (4,4,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (5,5,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (6,6,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (7,7,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (8,8,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (9,9,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (10,10,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (11,11,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (12,12,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (13,13,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (14,14,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (15,15,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (16,16,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (17,17,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (18,18,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (19,19,1,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (20,20,2,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System'),
           (21,21,3,'026.987.8-8',0.99,'System',1432783357778,1432783357778,'System')

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[contato];
CREATE TABLE [dbo].[contato](
	[id]           [int] NOT NULL,
	[parentId]     [int] NOT NULL,
	[motivo]       [int] NOT NULL,
	[contato_date] [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_contato_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[contato]
           ([id],[parentId],[motivo],[contato_date],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (2,2,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (3,3,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (4,4,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (5,5,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (6,6,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (7,7,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (8,8,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (9,9,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (10,10,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (11,11,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (12,12,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (13,13,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (14,14,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (15,15,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (16,16,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (17,17,3,1432783357778,1432783357778,'System',1432783357778,'System'),
           (18,18,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (19,19,1,1432783357778,1432783357778,'System',1432783357778,'System'),
           (20,20,2,1432783357778,1432783357778,'System',1432783357778,'System'),
           (21,21,3,1432783357778,1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/


DROP TABLE [dbo].[contatoItens];
CREATE TABLE [dbo].[contatoItens](
	[id]           [int] NOT NULL,
	[parentId]     [int] NOT NULL,
	[texto]        [varchar](250) NULL,
	[create_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_contatoItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[contatoItens]
           ([id],[parentId],[texto],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,1,'1',1432783357778,'System',1432783357778,'System'),
           (2,2,'2',1432783357778,'System',1432783357778,'System'),
           (3,3,'3',1432783357778,'System',1432783357778,'System'),
           (4,4,'1',1432783357778,'System',1432783357778,'System'),
           (5,5,'2',1432783357778,'System',1432783357778,'System'),
           (6,6,'3',1432783357778,'System',1432783357778,'System'),
           (7,7,'1',1432783357778,'System',1432783357778,'System'),
           (8,8,'2',1432783357778,'System',1432783357778,'System'),
           (9,9,'3',1432783357778,'System',1432783357778,'System'),
           (10,10,'1',1432783357778,'System',1432783357778,'System'),
           (11,11,'2',1432783357778,'System',1432783357778,'System'),
           (12,12,'3',1432783357778,'System',1432783357778,'System'),
           (13,13,'1',1432783357778,'System',1432783357778,'System'),
           (14,14,'2',1432783357778,'System',1432783357778,'System'),
           (15,15,'3',1432783357778,'System',1432783357778,'System'),
           (16,16,'1',1432783357778,'System',1432783357778,'System'),
           (17,17,'3',1432783357778,'System',1432783357778,'System'),
           (18,18,'2',1432783357778,'System',1432783357778,'System'),
           (19,19,'1',1432783357778,'System',1432783357778,'System'),
           (20,20,'2',1432783357778,'System',1432783357778,'System'),
           (21,21,'3',1432783357778,'System',1432783357778,'System')

/** ---------------------------------------------------------------*/
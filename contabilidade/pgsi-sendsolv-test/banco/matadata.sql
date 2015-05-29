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
		   (3,1,1,3,'034','91782776',1432783357778,'system',1432783357778,'system')
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
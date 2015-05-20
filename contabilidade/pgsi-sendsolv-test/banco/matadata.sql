CREATE TABLE [dbo].[regime](
	[id]           [int] NOT NULL,
	[nome]         [varchar](100) NULL,
	[descricao]    [varchar](200) NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
           (1,'Simples Nacional','Simples Nacional',100000000,'rod',10000000,'rod'),
		   (2,'Lucro Real','Lucro Real',10000000,'rod',1000000,'rod'),
		   (3,'Lucro Presumido','Lucro Presumido',10000000,'rod',1000000,'rod')
GO
/** ---------------------------------------------------------------*/

CREATE TABLE [dbo].[empresa] (
	[id]           [int] NOT NULL,
	[nome]         [varchar](100) NULL,
	[regime]       [int] NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
CREATE TABLE [dbo].[documento](
	[id]           [int] NOT NULL,
	[tabela]	 [int] NULL,
	[parentId]	 [int] NULL,
	[type]         [int] NULL,
	[description]  [varchar](200) NULL,
	[numero]       [varchar](50) NULL,
	[data]         [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[estado]       [varchar](2) NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
           (1,1,1,1,'CNPJ','2001',1111111111,'MG',10000000,'rod',111111111,'rod'),
		   (2,1,2,1,'CPF','2001',1111111111,'MG',10000000,'rod',111111111,'rod'),
		   (3,1,3,1,'IM','2001',1111111111,'MG',10000000,'rod',111111111,'rod'),
		   (4,1,1,1,'IE','2001',1111111111,'MG',10000000,'rod',111111111,'rod'),
		   (5,1,2,1,'RG','2001',1111111111,'MG',10000000,'rod',111111111,'rod'),
		   (6,1,3,1,'CGC','2001',1111111111,'MG',10000000,'rod',111111111,'rod')
GO

/** ---------------------------------------------------------------*/

CREATE TABLE [dbo].[email](
	[id]           [int] NOT NULL,
	[tabela]	   [int] NULL,
	[parentId]	   [int] NULL,
	[type]         [int] NULL,
	[email]        [varchar](100)  NULL,
	[description]  [varchar](200) NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
           (1,1,1,1,'wwww.tessr@gmail.com','email',1000000000,'rod',100000000,'rod'),
		   (2,1,2,1,'wwww.tessr@gmail.com','email',1000000000,'rod',100000000,'rod'),
		   (3,1,3,1,'wwww.tessr@gmail.com','email',1000000000,'rod',100000000,'rod'),
		   (4,1,1,1,'wwww.tessr@gmail.com','email',1000000000,'rod',100000000,'rod'),
		   (5,1,2,1,'wwww.tessr@gmail.com','email',1000000000,'rod',100000000,'rod'),
		   (6,1,3,1,'wwww.tessr@gmail.com','email',1000000000,'rod',100000000,'rod')
GO

/** ---------------------------------------------------------------*/
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
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50)  NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
           (1,1,1,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',1000000000,'rod',10000002,'rod'),
		   (2,1,2,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',1000000000,'rod',10000002,'rod'),
		   (3,1,3,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',1000000000,'rod',10000002,'rod'),
		   (4,1,1,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',1000000000,'rod',10000002,'rod'),
		   (5,1,2,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',1000000000,'rod',10000002,'rod'),
		   (6,1,3,1,'Rua Maria Conceicao silva','Uberaba','MG','centro','686','380802-243',1000000000,'rod',10000002,'rod')
GO
/** ---------------------------------------------------------------*/



CREATE TABLE [dbo].[telefone] (
	[id]           [int] not NULL,
	[type]         [int] NULL,
	[parentId]	   [int] NULL,
	[tabela]	   [int] NULL,
	[ddd] 		   [varchar](5) NULL,
	[telefone]     [varchar](15) NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_telefone_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]




/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[socio](
	[id]           [int] NOT NULL,
	[parentId]	   [int] NULL,
	[nome]         [varchar](200) NULL,
	[cota]         [varchar](10) NULL,
	[porcentagem]  [varchar](10) NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,

CONSTRAINT [pk_socios_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/** ---------------------------------------------------------------*/
CREATE TABLE [dbo].[historico] (
	[id]           [int] NOT NULL,
	[parentId]	   [int] NOT NULL,
	[tabela]	   [int] NULL,
	[type]         [int] NULL,
	[acao]         [int] NULL,
	[registro]     [varchar](200) NULL,
	[data]         [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[usuario]      [varchar](20) NULL,
	[empresa]      [int] NULL,
	[status]       [int] NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ncm_id] PRIMARY KEY CLUSTERED
(
	[NCM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


CREATE TABLE [dbo].[CIDADE](
    [CODIGO]     [VARCHAR](6) NOT NULL,
    [CIDADE]     [VARCHAR](40) NULL,
    [UF]         [VARCHAR](2) NULL,
    [CEP]        [VARCHAR](10) NULL,
    [IBGE]       [VARCHAR](10) NULL,
    [ESTADO]     [VARCHAR](10) NULL,
    [MUNICIPIO]  [VARCHAR](10) NULL,
    [status]       [int] NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cidade_id] PRIMARY KEY CLUSTERED
(
	[CODIGO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[cnaePorRelacionamento](
    [id]         [int] NOT NULL,
    [idCnae]     [int] NOT NULL,
    [idParentId] [int] NOT NULL,
    [tabela]     [int] NOT NULL,
	[create_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [int] NOT NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cnaeRelacionamento_id] PRIMARY KEY CLUSTERED
(
	[CODIGO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
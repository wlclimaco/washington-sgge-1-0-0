DROP TABLE  [dbo].[status];

CREATE TABLE [dbo].[status](
    [id]             [int] identity(1,1) NOT NULL,
    [dataStatus]   [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [parentId]     [int] NOT NULL,
    [status] 	   [int] NOT NULL,
    [acaoType]     [int] NOT NULL,
    [tabelaEnum]   [int] NOT NULL,
    [note]    	   [varchar](50) NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_status_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

DROP TABLE [dbo].[regime];
CREATE TABLE [dbo].[regime](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]         [varchar](100) NULL,
	[descricao]    [varchar](200) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_regime_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[empresa];
CREATE TABLE [dbo].[empresa] (
	[id]           [int] identity(1,1) NOT NULL,
	[nome]         [varchar](100) NULL,
	[regime]       [int] NULL,
	[processId]    [int] NULL,
	[entidadeId]   [int] NULL,
	[entidadeEnum] [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_socio_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[documento];
CREATE TABLE [dbo].[documento](
	[id]             [int] identity(1,1) NOT NULL,
	[tabela]	 [int] NULL,
	[parentId]	 [int] NULL,
	[type]         [int] NULL,
	[processId]    [int] NULL,
	[description]  [varchar](200) NULL,
	[numero]       [varchar](50) NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[estado]       [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_documento_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[email];
CREATE TABLE [dbo].[email](
	[id]             [int] identity(1,1) NOT NULL,
	[tabela]	   [int] NULL,
	[parentId]	   [int] NULL,
	[type]         [int] NULL,
	[processId]    [int] NULL,
	[email]        [varchar](100)  NULL,
	[description]  [varchar](200) NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,

CONSTRAINT [pk_email_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[endereco];
CREATE TABLE [dbo].[endereco](
	[id]             [int] identity(1,1) NOT NULL,
	[tabela]		 [int]  NULL,
	[parentId]	 [int]  NULL,
	[type]         [int]  NULL,
	[processId]    [int] NULL,
	[logradouro]	 [varchar](200)  NULL,
	[cidade]		 [int] NULL,
	[estado]		 [int] NULL,
	[bairro]		 [varchar](50)  NULL,
	[numero]       [varchar](10)  NULL,
	[cep]			 [varchar](15)  NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50)  NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50)  NULL,
CONSTRAINT [pk_endereco_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[telefone];
CREATE TABLE [dbo].[telefone] (
	[id]           [int] identity(1,1) NOT NULL,
	[type]         [int] NULL,
	[parentId]	   [int] NULL,
	[tabela]	   [int] NULL,
	[processId]    [int] NULL,
	[ddd] 		   [varchar](5) NULL,
	[telefone]     [varchar](15) NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_telefone_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY];

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[socio];
CREATE TABLE [dbo].[socio](
	[id]             [int] identity(1,1) NOT NULL,
	[parentId]	   [int] NULL,
	[nome]         [varchar](200) NULL,
	[cota]         real NULL,
	[porcentagem]  real NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,

CONSTRAINT [pk_socios_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[historico];
CREATE TABLE [dbo].[historico] (
	[id]           [int] identity(1,1) NOT NULL,
	[data]	       [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[userId]	   [varchar](20) NULL,
	[emprId]         [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
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
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ncm_id] PRIMARY KEY CLUSTERED
(
	[NCM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** --------------------------------------------------------------- */
DROP TABLE [dbo].[CIDADE];
CREATE TABLE [dbo].[CIDADE](
    [ID]         [int] identity(1,1) NOT NULL,
    [NOME]       [VARCHAR](40) NULL,
    [ESTADO]     [int] NULL,
    [CEP]        [VARCHAR](10) NULL,
    [cdIBGE]     [VARCHAR](10) NULL,
    [MUNICIPIO]  [VARCHAR](40) NULL,
    [processId]    [int] NULL,
    [status]       [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cidade_id] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** --------------------------------------------------------------- */
DROP TABLE  [dbo].[cnaePorRelacionamento];
CREATE TABLE [dbo].[cnaePorRelacionamento](
    [id]         [int] identity(1,1) NOT NULL,
    [idCnae]     [varchar](50) not NULL,
    [idParentId] [int] NOT NULL,
    [tabela]     [int] NOT NULL,
    [processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cnaeRelacionamento_id] PRIMARY KEY CLUSTERED
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** --------------------------------------------------------------- */

DROP TABLE [dbo].[Salario];
CREATE TABLE [dbo].[Salario](
	[id]             [int] identity(1,1) NOT NULL,
	[parentId]     [int] NULL,
	[valor]        [real] NULL,
	[processId]    [int] NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_salario_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

DROP TABLE [dbo].[Beneficio];
CREATE TABLE [dbo].[Beneficio](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]         [varchar](50) NULL,
    [codigo]       [varchar](50) NULL,
    [descricao]    [varchar](50) NULL,
	[valor]        [real] NULL,
	[porcentagem]  [real] NULL,
	[processId]    [int] NULL,
	[tipo]		   [varchar](10) NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_beneficio_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


DROP TABLE [dbo].[BeneficioFunc];
CREATE TABLE [dbo].[BeneficioFunc](
	[id]             [int] identity(1,1) NOT NULL,
	[idFunc]       [int] NOT NULL,
	[idBenef]       [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_beneficioBene_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[BeneficioMesApp];
CREATE TABLE [dbo].[BeneficioMesApp](
	[id]             [int] identity(1,1) NOT NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[idFuncBenef] [int] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoFuncEventApp_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Evento];
CREATE TABLE [dbo].[Evento](
	[id]             [int] identity(1,1) NOT NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[descricao]    [varchar](50) NULL,
	[tipo]         [varchar](50) NULL,
    [codigo]       [varchar](50) NULL,
    [noteText]     [varchar](200) NULL,
	[valor]        [real] NULL,
	[porcentagem]  [real] NULL,
	[isMensal]     [bit] NULL,
	[isSistema]    [bit] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_evento_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[EventoFunc];
CREATE TABLE [dbo].[EventoFunc](
	[id]             [int] identity(1,1) NOT NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[idFunc]	   [int] NULL,
	[idEvent]	   [int] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoFunc_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[EventoMesApp];
CREATE TABLE [dbo].[EventoMesApp](
	[id]             [int] identity(1,1) NOT NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[idFuncEnvent] [int] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ventoMesApp_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Pessoa];
CREATE TABLE [dbo].[Pessoa](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]         [varchar](200) NULL,
	[cdEmpr] 	   [int] NOT NULL,
	[sexo] 		   [int] NULL,
	[type]         [int] NOT NULL,
	[dataNasc]     [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataAdmin]     [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[nomePai] 	   [varchar](200) NULL,
	[nomeMae]      [varchar](200) NULL,
	[nomeConjugue] [varchar](200) NULL,
	[estadoCivil]  [int] NULL,
	[processId]    [int] NULL,
	[matricula]	   [varchar](50) NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoPessoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


DROP TABLE [dbo].[horarios];
CREATE TABLE [dbo].[horarios](
	[id]             [int] identity(1,1) NOT NULL,
	[parentId]     [int] NULL,
	[data]         [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[horarioEntr]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[horarioSair]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[tipo]     	   [varchar](50) NULL,
	[processId]    [int] NULL,
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_horario_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
USE [PGSi]
GO



DROP TABLE [dbo].[Profissao];
CREATE TABLE [dbo].[Profissao](
	[id]          	 [int] identity(1,1) NOT NULL,
	[parentId]     	 [int] NOT NULL,
	[profissao] 	 [varchar](100) NULL,
	[renda] 		 [real] NULL,
	[dataAdmissao]   [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_Profissao_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[Convenio];
CREATE TABLE [dbo].[Convenio](
	[id]          	 [int] identity(1,1) NOT NULL,
	[nome] 	 		 [varchar](100) NULL,
	[dataini]   	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataFin]   	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[porcentagem] 	 [real] NULL,
	[valor] 		 [real] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_Convenio_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[ConvenioPessoa];
CREATE TABLE [dbo].[ConvenioPessoa](
	[id]          	 [int] identity(1,1) NOT NULL,
	[parentId] 	 	 [int] NOT NULL,
	[convId]   	 [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ConvenioPessoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


DROP TABLE [dbo].[CondPag];
CREATE TABLE [dbo].[CondPag](
	[id]          	 [int] identity(1,1) NOT NULL,
	[nome] 	 		 [varchar](100) NULL,
	[dataini]   	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataFin]   	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[porcentagem] 	 [real] NULL,
	[valor] 		 [real] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_CondPag_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[CondPagPessoa];
CREATE TABLE [dbo].[CondPagPessoa](
	[id]          	 [int] identity(1,1) NOT NULL,
	[parentId] 	 	 [int] NOT NULL,
	[condPagId]   	 [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_CondPagPessoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[TipoPag];
CREATE TABLE [dbo].[TipoPag](
	[id]          	[int] identity(1,1) NOT NULL,
	[descricao] 	 [varchar](100) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoTipoPag_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[TipoPagReg];
CREATE TABLE [dbo].[TipoPagReg](
	[id]          	         [int] identity(1,1) NOT NULL,
	[parentId]          	 [int] NOT NULL,
	[tipoPagId]          	 [int] NOT NULL,
	[tabela]          	     [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_eventoTipoPagReg_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[banco];
CREATE TABLE [dbo].[banco](
	[id]          	         [int] identity(1,1) NOT NULL,
	[nome]          	 	 [varchar](50) NULL,
	[logo]          	 	 [varchar](50) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_banco_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]




/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[agencia];
CREATE TABLE [dbo].[agencia](
	[id]          	         [int] identity(1,1) NOT NULL,
	[nome]          	 	 [varchar](50) NULL,
	[parentId]          	 [int] NOT NULL,
	[numeroConta]          	 	 [varchar](50) NULL,
	[gerente]          	 	 [varchar](50) NULL,
	[responsavelConta]          	 	 [varchar](50) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_agencia_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[BancoPesoa];
CREATE TABLE [dbo].[BancoPesoa](
	[id]          	         [int] identity(1,1) NOT NULL,
	[parentId]          	 [int] NOT NULL,
	[bancoId]          	 	 [int] NOT NULL,
	[numCont]          	     [varchar](50) NULL,
	[saldo]          	     [real] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_BancoPesoa_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[contato];
CREATE TABLE [dbo].[contato](
	[id]             [int] identity(1,1) NOT NULL,
	[parentId]     [int] NOT NULL,
	[motivo]       [int] NOT NULL,
	[processId]    [int] NULL,
	[dataContato] [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_contato_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[contatoItens];
CREATE TABLE [dbo].[contatoItens](
	[id]             [int] identity(1,1) NOT NULL,
	[parentId]     [int] NOT NULL,
	[texto]        [varchar](250) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_contatoItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServico];
CREATE TABLE [dbo].[ordemServico](
	[id]             [int] identity(1,1) NOT NULL,
	[emprId]       [int] NOT NULL,
	[userId]       [varchar](50) NULL,
	[nome]         [varchar](100) NULL,
	[data]    	   [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[typeId]       [int] NOT NULL,
	[assunto]      [varchar](100) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ordemServico_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServicoItens];
CREATE TABLE [dbo].[ordemServicoItens](
	[id]             [int] NOT NULL,
	[idOrdemServico] [int] NOT NULL,
	[status]       	 [int] NOT NULL,
	[data]    	     [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[texto]          [varchar](255) NULL,
	[processId]      [int]  NULL,
	[create_date]    [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]    [varchar](50) NULL,
    [modify_date]    [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]    [varchar](50) NULL,
CONSTRAINT [pk_ordemServicoItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServicoTypes];
CREATE TABLE [dbo].[ordemServicoTypes](
	[id]             [int] identity(1,1) NOT NULL,
	[type]         [varchar](100) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ordemServicoTypes_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[ordemServicoStatus];
CREATE TABLE [dbo].[ordemServicoStatus](
	[id]             [int] identity(1,1) NOT NULL,
	[status]       [varchar](100) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ordemServicoStatus_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[tabela];
CREATE TABLE [dbo].[tabela](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]         [varchar](50) NOT NULL,
	[descricao]    [varchar](250) NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_TABELA_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[atributos];
CREATE TABLE [dbo].[atributos](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]         [varchar](50) NOT NULL,
	[descricao]    [varchar](250) NULL,
	[tamanho]      [int] NOT NULL,
	[obrigatorio]  [int] NOT NULL,
	[chavePrimaria]  [int] NOT NULL,
	[chaveSecundaria]  [int] NOT NULL,
	[tabelaSecundaria]  [int] NOT NULL,
	[type]  [int] NOT NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_atributos_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]



/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[unimed];
CREATE TABLE [dbo].[unimed](
	[id]             [int] identity(1,1) NOT NULL,
	[unimed]       [varchar](100) NOT NULL,
	[sigla]        [varchar](5) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_unimed_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[unimedProd];
CREATE TABLE [dbo].[unimedProd](
	[id]             [int] identity(1,1) NOT NULL,
	[unimedId]     [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_unimedProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[grupo];
CREATE TABLE [dbo].[grupo](
	[id]             [int] identity(1,1) NOT NULL,
	[grupo]        [varchar](100) NOT NULL,
	[descricao]    [varchar](250) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_grupo_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[grupoProd];
CREATE TABLE [dbo].[grupoProd](
	[id]             [int] identity(1,1) NOT NULL,
	[grupoId]     [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_grupoProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[subgrupo];
CREATE TABLE [dbo].[subgrupo](
	[id]             [int] identity(1,1) NOT NULL,
	[subgrupo]     [varchar](100) NOT NULL,
	[descricao]    [varchar](250) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_subgrupo_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[subgrupo]
           ([subgrupo],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('SubGrupo001','CX','System',1432783357778,1432783357778,'System'),
           ('SubGrupo002','LT','System',1432783357778,1432783357778,'System'),
           ('SubGrupo003','UN','System',1432783357778,1432783357778,'System'),
           ('SubGrupo004','DZ','System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[subgrupoGrupo];
CREATE TABLE [dbo].[subgrupoGrupo](
	[id]             [int] identity(1,1) NOT NULL,
	[grupoId]      [int] NOT NULL,
	[subGrupoId]   [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_subgrupoGrupo_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[subgrupoGrupo]
           ([grupoId],[subGrupoId],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,'System',1432783357778,1432783357778,'System'),
           (2,2,'System',1432783357778,1432783357778,'System'),
           (3,3,'System',1432783357778,1432783357778,'System'),
           (4,4,'System',1432783357778,1432783357778,'System'),
           (1,4,'System',1432783357778,1432783357778,'System'),
           (2,3,'System',1432783357778,1432783357778,'System'),
           (3,2,'System',1432783357778,1432783357778,'System'),
           (4,1,'System',1432783357778,1432783357778,'System'),
           (1,4,'System',1432783357778,1432783357778,'System'),
           (2,3,'System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[marca];
CREATE TABLE [dbo].[marca](
	[id]             [int] identity(1,1) NOT NULL,
	[marca]        [varchar](100) NOT NULL,
	[fabricante]   [varchar](100) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_marca_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[marca]
           ([marca],[fabricante],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('Marca0001','Marca0001','System',1432783357778,1432783357778,'System'),
           ('Marca0002','Marca0002','System',1432783357778,1432783357778,'System'),
           ('Marca0003','Marca0003','System',1432783357778,1432783357778,'System'),
           ('Marca0004','Marca0004','System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[marcaProd];
CREATE TABLE [dbo].[marcaProd](
	[id]             [int] identity(1,1) NOT NULL,
	[marcaId]      [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_marcaProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[marcaProd]
           ([marcaId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
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
DROP TABLE [dbo].[cst];
CREATE TABLE [dbo].[cst](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]         [varchar](150) NULL,
	[descricao]    [varchar](150) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cst_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[cst]
           ([nome],[descricao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('T','Marca0001','System',1432783357778,1432783357778,'System'),
           ('I','Marca0002','System',1432783357778,1432783357778,'System'),
           ('N','Marca0003','System',1432783357778,1432783357778,'System'),
           ('F','Marca0004','System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------
DROP TABLE [dbo].[cstProd];
CREATE TABLE [dbo].[cstProd](
	[id]             [int] identity(1,1) NOT NULL,
	[cstId]      [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_marca_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[cstProd]
           ([cstId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
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
*/
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[csosnProd];
CREATE TABLE [dbo].[csosnProd](
	[id]             [int] identity(1,1) NOT NULL,
	[csosnId]      [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_csosnProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[csosnProd]
           ([csosnId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
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
DROP TABLE [dbo].[incidencia];
CREATE TABLE [dbo].[incidencia](
	[id]             [int] identity(1,1) NOT NULL,
	[codigo]       [varchar](50) NULL,
	[texto]        [varchar](150) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_incidencia_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[incidencia]
           ([codigo],[texto],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('00','Nenhuma','System',1432783357778,1432783357778,'System'),
           ('01','Monofasica','System',1432783357778,1432783357778,'System'),
           ('02','Subst Tributaria','System',1432783357778,1432783357778,'System'),
           ('03','Aliguota 0','System',1432783357778,1432783357778,'System'),
           ('04','Suspens�o','System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------
DROP TABLE [dbo].[incidenciaProd];
CREATE TABLE [dbo].[incidenciaProd](
	[id]             [int] identity(1,1) NOT NULL,
	[incidenciaId] [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_incidenciaProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[incidenciaProd]
           ([incidenciaId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,'System',1432783357778,1432783357778,'System'),
           (2,2,'System',1432783357778,1432783357778,'System'),
           (3,3,'System',1432783357778,1432783357778,'System'),
           (4,4,'System',1432783357778,1432783357778,'System'),
           (5,5,'System',1432783357778,1432783357778,'System'),
           (6,6,'System',1432783357778,1432783357778,'System'),
           (4,7,'System',1432783357778,1432783357778,'System'),
           (4,8,'System',1432783357778,1432783357778,'System'),
           (1,9,'System',1432783357778,1432783357778,'System'),
           (2,10,'System',1432783357778,1432783357778,'System');*/
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[tributacao];
CREATE TABLE [dbo].[tributacao](
	[id]             [int] identity(1,1) NOT NULL,
	[prodId]       [int] NOT NULL,
	[cstId]        [int] NOT NULL,
	[processId]    [int] NULL,
	[icms]   	   [real] NULL,
	[st]   		   [int] NULL,
	[mva]   	   [real] NULL,
	[csosnId]      [int] NULL,
	[ipi]          [real] NULL,
	[iat]   	   [int] NULL,
	[ippt]   	   [int] NULL,
	[pisconfins]   [int] NULL,
	[incidenciaId] [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_tributacao_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[tributacao]
           ([cstId],[prodId],[icms],[st],[mva],[csosnId],[ipi],[iat],[ippt],[pisconfins],[incidenciaId],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,18,0,0,1,0,0,0,0,1,'System',1432783357778,1432783357778,'System'),
           (2,2,18,0,0,2,0,0,0,0,2,'System',1432783357778,1432783357778,'System'),
           (3,3,18,0,0,3,0,0,0,0,3,'System',1432783357778,1432783357778,'System'),
           (4,4,18,0,0,4,0,0,0,0,4,'System',1432783357778,1432783357778,'System'),
           (1,5,18,0,0,1,0,0,0,0,1,'System',1432783357778,1432783357778,'System'),
           (2,6,18,0,0,2,0,0,0,0,2,'System',1432783357778,1432783357778,'System'),
           (3,7,18,0,0,3,0,0,0,0,3,'System',1432783357778,1432783357778,'System'),
           (4,8,18,0,0,4,0,0,0,0,4,'System',1432783357778,1432783357778,'System'),
           (1,9,18,0,0,1,0,0,0,0,1,'System',1432783357778,1432783357778,'System'),
           (2,10,18,0,0,2,0,0,0,0,2,'System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[classificacao];
CREATE TABLE [dbo].[classificacao](
	[id]             [int] identity(1,1) NOT NULL,
	[codigo]       [varchar](50) NULL,
	[descricao]    [varchar](150) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_classificacao_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

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
DROP TABLE [dbo].[classificacaoProd];
CREATE TABLE [dbo].[classificacaoProd](
	[id]             [int] identity(1,1) NOT NULL,
	[classificacaoId] [int] NOT NULL,
	[prodId]       [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_classificacaoProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[classificacaoProd]
           ([classificacaoId],[prodId],[create_user],[create_date],[modify_date],[modify_user])
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
DROP TABLE [dbo].[estoque];
CREATE TABLE [dbo].[estoque](
	[id]             [int] identity(1,1) NOT NULL,
	[prodId]          [int] NOT NULL,
	[estoqueTypeEnum] [int] NOT NULL,
	[processId]    [int] NULL,
	[ultimoMov]       [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[quant]           [real] NOT NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_estoque_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[estoque]
           ([estoqueTypeEnum],[prodId],[ultimoMov],[quant] ,[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,2,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,3,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,4,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,5,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,6,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,7,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,8,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,9,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,10,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,1,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,2,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,3,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,4,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,5,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,6,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,7,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,8,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,9,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,10,1432783357778,10,'System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[preco];
CREATE TABLE [dbo].[preco](
	[id]             [int] identity(1,1) NOT NULL,
	[prodId]          [int] NOT NULL,
	[precoTypeEnum]   [int] NOT NULL,
	[dataMarcacao]    [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[valor]           [real] NOT NULL,
	[dataProInicial]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataProFinal]    [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_preco_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[preco]
           ([precoTypeEnum],[prodId],[dataMarcacao],[valor] ,[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,2,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,3,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,4,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,5,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,6,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,7,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,8,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,9,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (1,10,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,1,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,2,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,3,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,4,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,5,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,6,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,7,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,8,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,9,1432783357778,10,'System',1432783357778,1432783357778,'System'),
           (4,10,1432783357778,10,'System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[custoItens];
CREATE TABLE [dbo].[custoItens](
	[id]               [int] identity(1,1) NOT NULL,
	[custo]   		   [varchar](50) NULL,
	[custoDesp]        [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_custoItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[custoItens]
           ([custo],[custoDesp],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('Valor Nota Fiscal',2,'System',1432783357778,1432783357778,'System'),
           ('ICMS',1,'System',1432783357778,1432783357778,'System'),
           ('DESCONTO',1,'System',1432783357778,1432783357778,'System'),
           ('FRETE',0,'System',1432783357778,1432783357778,'System'),
           ('SEGURO',0,'System',1432783357778,1432783357778,'System'),
           ('OUTRAS DESPESAS',0,'System',1432783357778,1432783357778,'System'),
           ('IPI',0,'System',1432783357778,1432783357778,'System'),
           ('ICMS SAIDA',0,'System',1432783357778,1432783357778,'System'),
           ('PIS',0,'System',1432783357778,1432783357778,'System'),
           ('CONFINS',0,'System',1432783357778,1432783357778,'System'),
           ('CONTRIBUICAO SOCIAL',0,'System',1432783357778,1432783357778,'System'),
           ('CUSTO OPERACIONAL',0,'System',1432783357778,1432783357778,'System'),
           ('COMISSAO',0,'System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[custo];
CREATE TABLE [dbo].[custo](
	[id]             [int] identity(1,1) NOT NULL,
	[prodId]          [int] NOT NULL,
	[idcustoItens]    [int] NOT NULL,
	[valor]           [real] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_custo_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[custo]
           ([idcustoItens],[prodId],[valor] ,[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,1,10,'System',1432783357778,1432783357778,'System'),
           (2,2,10,'System',1432783357778,1432783357778,'System'),
           (3,3,10,'System',1432783357778,1432783357778,'System'),
           (4,4,10,'System',1432783357778,1432783357778,'System'),
           (5,5,10,'System',1432783357778,1432783357778,'System'),
           (6,6,10,'System',1432783357778,1432783357778,'System'),
           (7,7,10,'System',1432783357778,1432783357778,'System'),
           (8,8,10,'System',1432783357778,1432783357778,'System'),
           (9,9,10,'System',1432783357778,1432783357778,'System'),
           (10,10,10,'System',1432783357778,1432783357778,'System'),
           (11,1,10,'System',1432783357778,1432783357778,'System'),
           (12,2,10,'System',1432783357778,1432783357778,'System'),
           (13,3,10,'System',1432783357778,1432783357778,'System'),
           (1,4,10,'System',1432783357778,1432783357778,'System'),
           (2,5,10,'System',1432783357778,1432783357778,'System'),
           (3,6,10,'System',1432783357778,1432783357778,'System'),
           (4,7,10,'System',1432783357778,1432783357778,'System'),
           (5,8,10,'System',1432783357778,1432783357778,'System'),
           (6,9,10,'System',1432783357778,1432783357778,'System'),
           (7,10,10,'System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[porcao];
CREATE TABLE [dbo].[porcao](
	[id]              [int] identity(1,1) NOT NULL,
	[prodId]          [int] NOT NULL,
	[valor]           [real] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_porcao_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[porcao]
           ([valor],[prodId],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (2,1,'System',1432783357778,1432783357778,'System'),
           (20,2,'System',1432783357778,1432783357778,'System'),
           (30,3,'System',1432783357778,1432783357778,'System'),
           (40,4,'System',1432783357778,1432783357778,'System'),
           (50,5,'System',1432783357778,1432783357778,'System'),
           (60,6,'System',1432783357778,1432783357778,'System'),
           (70,7,'System',1432783357778,1432783357778,'System'),
           (80,8,'System',1432783357778,1432783357778,'System'),
           (90,9,'System',1432783357778,1432783357778,'System'),
           (100,10,'System',1432783357778,1432783357778,'System'),
           (110,1,'System',1432783357778,1432783357778,'System'),
           (20,12,'System',1432783357778,1432783357778,'System'),
           (130,3,'System',1432783357778,1432783357778,'System'),
           (10,4,'System',1432783357778,1432783357778,'System'),
           (52,5,'System',1432783357778,1432783357778,'System'),
           (300,6,'System',1432783357778,1432783357778,'System'),
           (40,7,'System',1432783357778,1432783357778,'System'),
           (50,8,'System',1432783357778,1432783357778,'System'),
           (60,9,'System',1432783357778,1432783357778,'System'),
           (70,10,'System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[porcaoItens];
CREATE TABLE [dbo].[porcaoItens](
	[id]               [int] identity(1,1) NOT NULL,
	[idporcao]         [int] NOT NULL,
	[unimed]           [int] NOT NULL,
	[porcao]   		   [real] NULL,
	[vd]   		       [real] NULL,
	[nome]   		   [varchar](150) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_porcaoItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[porcaoItens]
           ([porcao],[idporcao],[nome],[vd],[unimed],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (30,1,'Valor Calorico',2.6,1,'System',1432783357778,1432783357778,'System'),
           (20,2,'CARBOIDRATOS',2.6,1,'System',1432783357778,1432783357778,'System'),
           (30,3,'PROTEINAS',2.6,1,'System',1432783357778,1432783357778,'System'),
           (40,4,'GORDURAS TOTAIS',2.6,1,'System',1432783357778,1432783357778,'System'),
           (50,5,'GORDURAS SATURADAS',2.6,1,'System',1432783357778,1432783357778,'System'),
           (60,6,'COLESTERAL',2.6,1,'System',1432783357778,1432783357778,'System'),
           (70,7,'FIBRA ALIMENTAR',2.6,1,'System',1432783357778,1432783357778,'System'),
           (80,8,'CALCIO',2.6,1,'System',1432783357778,1432783357778,'System'),
           (90,9,'FERRO',2.6,1,'System',1432783357778,1432783357778,'System'),
           (100,10,'SODIO',2.6,1,'System',1432783357778,1432783357778,'System'),
           (110,1,'SODIO',2.6,1,'System',1432783357778,1432783357778,'System'),
           (20,12,'FERRO',2.6,1,'System',1432783357778,1432783357778,'System'),
           (130,3,'CALCIO',2.6,1,'System',1432783357778,1432783357778,'System'),
           (10,4,'FIBRA ALIMENTAR',2.6,1,'System',1432783357778,1432783357778,'System'),
           (50,5,'COLESTERAL',2.6,1,'System',1432783357778,1432783357778,'System'),
           (300,6,'GORDURAS SATURADAS',2.6,1,'System',1432783357778,1432783357778,'System'),
           (40,7,'GORDURAS TOTAIS',2.6,1,'System',1432783357778,1432783357778,'System'),
           (50,8,'Valor Calorico',2.6,1,'System',1432783357778,1432783357778,'System'),
           (60,9,'Valor Calorico',2.6,1,'System',1432783357778,1432783357778,'System'),
           (70,10,'Valor Calorico',2.6,1,'System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[rentabilidade];
CREATE TABLE [dbo].[rentabilidade](
	[id]                    [int] identity(1,1) NOT NULL,
	[idprod]                [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_rentabilidade_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[rentabilidade]
           ([idprod],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,'System',1432783357778,1432783357778,'System'),
           (2,'System',1432783357778,1432783357778,'System'),
           (3,'System',1432783357778,1432783357778,'System'),
           (4,'System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[rentabilidadeProdutos];
CREATE TABLE [dbo].[rentabilidadeProdutos](
	[id]                   [int] identity(1,1) NOT NULL,
	[idrentabilidade]       [int] NOT NULL,
	[idprod]                [int] NOT NULL,
	[valor]                 [real] NOT NULL,
	[rentabilidadeTypeEnum] [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_rentabilidadeProdutos_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[rentabilidadeProdutos]
           ([idrentabilidade],[idprod],[valor],[rentabilidadeTypeEnum],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           (1,5,10,1,'System',1432783357778,1432783357778,'System'),
           (2,6,15,2,'System',1432783357778,1432783357778,'System'),
           (5,3,7,1,'System',1432783357778,1432783357778,'System'),
           (4,8,25,2,'System',1432783357778,1432783357778,'System');

/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[cfop];
CREATE TABLE [dbo].[cfop](
	[id]            [int] identity(1,1) NOT NULL,
	[cfop]          [varchar](50) NULL,
	[natureza]      [varchar](50) NULL,
	[simplificado]  [varchar](50) NULL,
	[cfopTypeEnum]  [int] NOT NULL,
	[icms]          [real]  NULL,
	[icmsReduzido]  [real]  NULL,
	[margemAgregadaST] [real]  NULL,
	[cstPrincipal]    [real]  NULL,
	[classFiscal]     [real]  NULL,
	[observacao]      [varchar](250) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_cfop_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[cfop]
           ([cfop],[natureza],[simplificado],[cfopTypeEnum],[icms],[icmsReduzido],[margemAgregadaST],[cstPrincipal],[classFiscal],[observacao],[create_user],[create_date],[modify_date],[modify_user])
     VALUES
           ('1','5','10',1,18,2,0,0,1,'OBSERVACAO','System',1432783357778,1432783357778,'System'),
           ('1','5','10',1,18,2,0,0,1,'OBSERVACAO','System',1432783357778,1432783357778,'System'),
           ('1','5','10',1,18,2,0,0,1,'OBSERVACAO','System',1432783357778,1432783357778,'System'),
           ('1','5','10',1,18,2,0,0,1,'OBSERVACAO','System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[CfopProd];
CREATE TABLE [dbo].[CfopProd](
	[id]             [int] identity(1,1) NOT NULL,
	[idCfop]          [int] NOT NULL,
	[prodId]		  [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_CfopProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[CfopProd]
           ([idCfop],[prodId],[create_user],[create_date],[modify_date],[modify_user])
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
           (2,10,'System',1432783357778,1432783357778,'System'),
           (3,1,'System',1432783357778,1432783357778,'System'),
           (4,2,'System',1432783357778,1432783357778,'System'),
           (1,3,'System',1432783357778,1432783357778,'System'),
           (2,4,'System',1432783357778,1432783357778,'System'),
           (3,5,'System',1432783357778,1432783357778,'System'),
           (4,6,'System',1432783357778,1432783357778,'System'),
           (1,7,'System',1432783357778,1432783357778,'System'),
           (2,8,'System',1432783357778,1432783357778,'System'),
           (3,9,'System',1432783357778,1432783357778,'System'),
           (4,10,'System',1432783357778,1432783357778,'System');
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[ForneProd];
CREATE TABLE [dbo].[ForneProd](
	[id]             [int] identity(1,1) NOT NULL,
	[idForn]       [int] NOT NULL,
	[prodId]	   [int] NOT NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_ForneProd_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO [dbo].[ForneProd]
           ([idForn],[prodId],[create_user],[create_date],[modify_date],[modify_user])
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
DROP TABLE [dbo].[produto];
CREATE TABLE [dbo].[produto](
	[id]             [int] identity(1,1) NOT NULL,
	[codigo]       [varchar](50) NOT NULL,
	[cdBarras]     [varchar](50) NULL,
	[dataCreate]   [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[produto]  	   [varchar](250) NOT NULL,
	[modoUso]  	   [varchar](250) NULL,
	[aplicacao]    [varchar](250) NULL,
	[localizacao]  [varchar](200) NULL,
	[dataValidade] [bigint] NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[comissao]     [real]  NULL,
	[fracao]       [real]  NULL,
	[porcao]  	   [real]  NULL,
	[pesoBruto]    [real]  NULL,
	[pesoLiquido]  [real]  NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_produto_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[produto]
           ([codigo]
           ,[cdBarras]
           ,[dataCreate]
           ,[produto]
           ,[aplicacao]
           ,[localizacao]
           ,[dataValidade]
           ,[comissao]
           ,[fracao]
           ,[porcao]
           ,[pesoBruto]
           ,[pesoLiquido]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           ('00001','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00002','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00003','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00004','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00005','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00006','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00007','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00008','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('00009','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('000010','00000101010',1432783357778,'BALA','<aplicacao, varchar(250),>','<localizacao, varchar(200),>',1432783357778,10,50,50,1.5,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM');


GO
/** ---------------------------------------------------------------*/

DROP TABLE [dbo].[conhecimentoTransporte];
CREATE TABLE [dbo].[conhecimentoTransporte](
	[id]             [int] identity(1,1) NOT NULL,
	[IdNota]       			[int] NOT NULL,
	[transportador]			[int] NOT NULL,
	[remetente]    			[varchar](250) NOT NULL,
	[vrTotalMercadorias] 	[real]  NULL,
	[apCreIcms]    			[int] NOT NULL,
	[fretePorConta] 		[int] NOT NULL,
	[placa]  				[varchar](200) NULL,
	[estado] 				[int] NOT NULL,
	[marca]  				[int] NOT NULL,
	[especie]  	  			[real]  NULL,
	[pesoBruto]    		    [real]  NULL,
	[pesoLiquido]  			[real]  NULL,
	[volume]  				[real]  NULL,
	[processId]    			[int] NOT NULL,
	[create_date]  			[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  			[varchar](50) NULL,
    [modify_date]  			[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  			[varchar](50) NULL,
CONSTRAINT [pk_conhecimentoTransporte_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[conhecimentoTransporte]
           ([IdNota]
           ,[transportador]
           ,[remetente]
           ,[vrTotalMercadorias]
           ,[apCreIcms]
           ,[fretePorConta]
           ,[placa]
           ,[estado]
           ,[marca]
           ,[especie]
           ,[pesoBruto]
           ,[pesoLiquido]
           ,[volume]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'BALA',10.25,1,1,'10,50,50,1.5,1',1,1,'especie',1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM')


GO

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[notaFiscalItens];
CREATE TABLE [dbo].[notaFiscalItens](
	[id]             [int] identity(1,1) NOT NULL,
	[IdNota]       	[int] NOT NULL,
	[qnt]			[real] NOT NULL,
	[vrUnitario]    [real] NOT NULL,
	[vrDesconto] 	[real]  NULL,
	[produto]    	[int] NOT NULL,
	[cfop] 			[int] NOT NULL,
	[classificacao] [int] NOT NULL,
	[processId]     [int]  NULL,
	[create_date]  			[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  			[varchar](50) NULL,
    [modify_date]  			[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  			[varchar](50) NULL,
CONSTRAINT [pk_notaFiscalItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[notaFiscalItens]
           ([IdNota]
           ,[qnt]
           ,[vrUnitario]
           ,[vrDesconto]
           ,[produto]
           ,[cfop]
           ,[classificacao]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user])
     VALUES
           (1,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (3,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (3,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (4,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (4,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (5,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (5,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (6,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (6,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (7,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (7,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (8,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (8,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (9,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (9,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (10,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (10,1,2.5,0.3525,1,1,1,1432783357778,'SYSTEM',1432783357778,'SYSTEM')


GO
/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[contasPagarReceber];
CREATE TABLE [dbo].[contasPagarReceber](
	[id]             [int] identity(1,1) NOT NULL,
	[idFornecedor] 	[int] NOT NULL,
	[parentId] 	[int] NOT NULL,
	[emprId]		[int] NOT NULL,
	[contaTypeEnum] [int] NOT NULL,
	[numeroParc] 	[int]  NULL,
	[parcela]    	[int] NOT NULL,
	[valorOriginal]	[real] NOT NULL,
	[dataVencimento] [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataGeracao]    [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataPagamento]  [bigint] NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[juros]			 [real] NOT NULL,
	[taxa]           [real] NOT NULL,
	[valorTotal] 	 [real]  NULL,
	[baixaUser]		 [int]  NULL,
	[baixaDateUTC]	 [bigint] NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[processId]      [int] NULL,
	[create_date]  			[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  			[varchar](50) NULL,
    [modify_date]  			[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  			[varchar](50) NULL,
CONSTRAINT [pk_contasPagar_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[contasPagarReceber]
           ([idFornecedor]
           ,[emprId]
           ,[contaTypeEnum]
           ,[numeroParc]
           ,[parcela]
           ,[valorOriginal]
           ,[dataVencimento]
           ,[dataGeracao]
           ,[dataPagamento]
           ,[juros]
           ,[taxa]
           ,[valorTotal]
           ,[create_date]
           ,[create_user]
           ,[modify_date]
           ,[modify_user]
           ,[parentId])
     VALUES
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',1),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',1),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',2),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',2),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',3),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',4),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',5),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',5),
           (1,1,2,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',6),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',6),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',7),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',7),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',8),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',8),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',9),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',9),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',4),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',3),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',10),
           (1,1,1,2,1,100.99,1432783357778,1432783357778,null,0,0,100.99,1432783357778,'SYSTEM',1432783357778,'SYSTEM',10)


GO

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[itensEspeciais];
CREATE TABLE [dbo].[itensEspeciais](
	[id]             [int] identity(1,1) NOT NULL,
	[item] 		    [int] NOT NULL,
	[IdNota]		[int] NOT NULL,
	[nome]          [varchar](50) NULL,
	[valor] 	    [real]  NULL,
	[baseCalculo]  	[real]  NULL,
	[aliguotaICMS]	[real]  NULL,
	[valorICMS]     [real]  NULL,
	[processId]     [int]  NULL,
	[create_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  	[varchar](50) NULL,
    [modify_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  	[varchar](50) NULL,
CONSTRAINT [pk_itensEspeciais_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[itensEspeciais]
           ([item]
			,[IdNota]
			,[nome]
			,[valor]
			,[baseCalculo]
			,[aliguotaICMS]
			,[valorICMS]
			,[create_date]
		    ,[create_user]
		    ,[modify_date]
		    ,[modify_user])
     VALUES
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,1,'2',2,1,0.10,1.55,1432783357778,'SYSTEM',1432783357778,'SYSTEM')


GO

/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[nFStatus];
CREATE TABLE [dbo].[nFStatus](
	[id]             [int] identity(1,1) NOT NULL,
	[IdNota]		[int] NOT NULL,
	[status]        [int] NOT NULL,
	[dataMudanca]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[processId]     [int]  NULL,
	[create_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  	[varchar](50) NULL,
    [modify_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  	[varchar](50) NULL,
CONSTRAINT [pk_nFStatus2_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[nFStatus]
           ([IdNota]
			,[status]
			,[dataMudanca]
			,[create_date]
		    ,[create_user]
		    ,[modify_date]
		    ,[modify_user])
     VALUES
           (1,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (3,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (3,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (4,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (4,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (5,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (5,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (6,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (6,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (7,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (7,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (8,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (8,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (9,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (9,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (10,1,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (10,2,1432783357778,1432783357778,'SYSTEM',1432783357778,'SYSTEM')


GO


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[notaFiscal];
CREATE TABLE [dbo].[notaFiscal](
	[id]             [int] identity(1,1) NOT NULL,
	[notaType]		 [int] NOT NULL,
	[serie]		     [varchar](50) NULL,
	[ordem]          [varchar](50) NULL,
	[numero]  	     [int] NOT NULL,
	[tipo]  	     [varchar](50) NULL,
    [nfValor]  	     [real] NOT NULL,
    [dataEmissao]  	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [dataSaida]      [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[dataEntrada]	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[modelo]         [varchar](50) NULL,
	[bxEstoque]  	 [int] NULL,
	[descItens]  	 [int] NULL,
    [pcCusto]  	     [int] NULL,
    [pedidoCompraId] [int] NULL,
    [orcamentoId]  	 [int] NULL,
    [cfop]           [int] NOT NULL,
	[transportador]  [int] NULL,
	[conhecimentoTransporte]  [int] NULL,
    [empresa]  	    [int] NULL,
    [fornecedor]    [int] NULL,
    [cliente]  	    [int] NULL,
    [processId]     [int]  NULL,
	[create_date]   [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  	[varchar](50) NULL,
    [modify_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  	[varchar](50) NULL,
CONSTRAINT [pk_notaFiscal_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

USE [PGSi]
GO

INSERT INTO [dbo].[notaFiscal]
        (
			[notaType],
			[serie],
			[ordem],
			[numero],
			[tipo],
			[nfValor],
			[dataEmissao],
			[dataSaida],
			[dataEntrada],
			[modelo],
			[bxEstoque],
			[descItens],
			[pcCusto],
			[pedidoCompraId],
			[orcamentoId],
			[cfop],
			[transportador],
			[conhecimentoTransporte],
			[empresa],
			[fornecedor],
			[cliente]
		,[create_date]
	    ,[create_user]
	    ,[modify_date]
	    ,[modify_user])
     VALUES
           (1,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (1,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           (2,'1','O',1024,'NF',100.85,1432783357778,1432783357778,1432783357778,'55',1,1,1,0,0,1,1,1,1,2,3,1432783357778,'SYSTEM',1432783357778,'SYSTEM')

GO

-----------------------------------------------------------------------------
DROP TABLE [dbo].[process];
CREATE TABLE [dbo].[process](
	[id]             [int] identity(1,1) NOT NULL,
	[tabelaEnum]	 [int] NOT NULL,
	[acaoEnum]		 [int] NOT NULL,
	[empId] 		 [int] NOT NULL,
    [data]  	     [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  	[varchar](50) NULL,
    [modify_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  	[varchar](50) NULL,
CONSTRAINT [pk_process_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


-----------------------------------------------------------------------------
DROP TABLE [dbo].[historicoItens];
CREATE TABLE [dbo].[historicoItens](
	[id]             [int] identity(1,1) NOT NULL,
	[idHist]	     [int] NOT NULL,
	[processId]		 [int] NOT NULL,
	[type] 		     [int] NOT NULL,
	[tabela] 		 [int] NOT NULL,
	[parentId] 		 [int] NOT NULL,
    [data]  	     [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[create_date]  	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  	 [varchar](50) NULL,
    [modify_date]  	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]    [varchar](50) NULL,
CONSTRAINT [pk_historicoItens_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


-----------------------------------------------------------------------------
DROP PROCEDURE [dbo].[fetchProcessId];
CREATE PROCEDURE [dbo].[fetchProcessId]
	@ptabela int,
	@pempId int,
	@pacao int,
	@puser varchar(50)

AS

declare @p_time_of_request int = 0
BEGIN

	@p_time_of_request = 1432783357778

	INSERT  INTO [dbo].[process1] ([tabela],[empId],[acaoEnum],[create_date],[create_user])VALUES(@ptabela, @pempId,@pacao,@p_time_of_request,@puser)
	select @p_time_of_request = max(id) from [dbo].[process1]

  RETURN @p_time_of_request
END

-----------------------------------------------------------------------------


/** ---------------------------------------------------------------*/
DROP TABLE [dbo].[estado];
CREATE TABLE [dbo].[estado](
	[id]             [int] identity(1,1) NOT NULL,
	[nome]		 [varchar](50) NULL,
	[abreviacao]  [varchar](50) NULL,
	[create_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  	[varchar](50) NULL,
    [modify_date]  	[bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  	[varchar](50) NULL,
CONSTRAINT [pk_estado_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[estado]
           ([nome]
			,[abreviacao]
			,[create_date]
		    ,[create_user]
		    ,[modify_date]
		    ,[modify_user])
     VALUES
           ('MINAS GERAIS','MG',1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('RIO DE JANEIRO','RJ',1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('S�O PAULO','MG',1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('BAHIA','BA',1432783357778,'SYSTEM',1432783357778,'SYSTEM'),
           ('ESPIRITO SANTO','ES',1432783357778,'SYSTEM',1432783357778,'SYSTEM')

DROP TABLE [dbo].[contato];
CREATE TABLE [dbo].[contato](
	[id]          	         [int] identity(1,1) NOT NULL,
	[dataContato]         	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_Contato_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[contato]
           ([dataContato],[processId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1432783357778,1,1432783357778,'System',1432783357778,'System'),
           (1432783357778,2,1432783357778,'System',1432783357778,'System'),
           (1432783357778,3,1432783357778,'System',1432783357778,'System')


DROP TABLE [dbo].[contatoItens];
CREATE TABLE [dbo].[contatoItens](
	[id]          	     [int] identity(1,1) NOT NULL,
	[parentId]         	 [int] NULL,
	[motivo]         	 [varchar](250) NULL,
	[dataContato]      	 [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	[nomeContato]        [varchar](50) NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_contatoiTENS_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[contatoItens]
           ([parentId],[motivo],[dataContato],[nomeContato],[processId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Motivo A',1432783357778,'Jurema',1,1432783357778,'System',1432783357778,'System'),
           (2,'Motivo B',1432783357778,'Jurema',2,1432783357778,'System',1432783357778,'System'),
           (3,'Motivo C',1432783357778,'Jurema',3,1432783357778,'System',1432783357778,'System')

DROP TABLE [dbo].[note1];
CREATE TABLE [dbo].[note1](
	[id]          	     [int] identity(1,1) NOT NULL,
	[parentId]         	 [int] NULL,
	[note_text]          [varchar](250) NULL,
	[tabela]        	 [int] NULL,
	[processId]    [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_note_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


INSERT INTO [dbo].[note1]
           ([parentId],[note_text],[tabela],[processId],[create_date],[create_user],[modify_date],[modify_user])
     VALUES
           (1,'Note A',23,1,1432783357778,'System',1432783357778,'System'),
           (2,'Note B',23,2,1432783357778,'System',1432783357778,'System'),
           (3,'Note C',23,3,1432783357778,'System',1432783357778,'System')


DROP TABLE [dbo].[produtoFornecedor];
CREATE TABLE [dbo].[produtoFornecedor](
	[id]          	     [int] identity(1,1) NOT NULL,
	[prodId]         	 [int] NULL,
	[fornecId]         	 [int] NULL,
	[create_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [create_user]  [varchar](50) NULL,
    [modify_date]  [bigint]  NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
    [modify_user]  [varchar](50) NULL,
CONSTRAINT [pk_produtoFornecedor_id] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

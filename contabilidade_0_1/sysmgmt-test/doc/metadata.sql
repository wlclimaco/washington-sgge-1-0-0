DROP SEQUENCE status_id_seq;

CREATE SEQUENCE status_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cnae_id_seq
  OWNER TO postgres;


DROP TABLE  status;

CREATE TABLE status(
    id           integer NOT NULL DEFAULT nextval('status_id_seq'::regclass),
    dataStatus   bigint,
    parentId     integer NOT NULL,
    status 	     integer NOT NULL,
    acaoType     integer NOT NULL,
    tabelaEnum   integer NOT NULL,
    note    	 character varying(50) NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT status_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE status
  OWNER TO postgres;

DROP SEQUENCE regime_id_seq;

CREATE SEQUENCE regime_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE regime_id_seq
  OWNER TO postgres;


DROP TABLE regime;
CREATE TABLE regime(
	id             integer NOT NULL DEFAULT nextval('regime_id_seq'::regclass),
	nome         character varying(100) NULL,
	descricao    character varying(200) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT pk_regime_id PRIMARY KEY  (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE regime
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE entidade_id_seq;

CREATE SEQUENCE entidade_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE entidade_id_seq
  OWNER TO postgres;

DROP TABLE entidade;
CREATE TABLE entidade (
	id           integer NOT NULL DEFAULT nextval('entidade_id_seq'::regclass),
	nome         character varying(100) NULL,
	regime       integer NULL,
	emprId       integer NULL,
	processId    integer NULL,
	entidadeId   integer NULL,
	entidadeEnum integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT pk_entidade_id  PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entidade
  OWNER TO postgres;


/* ---------------------------------------------------------------*/

DROP SEQUENCE documento_id_seq;

CREATE SEQUENCE documento_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE documento_id_seq
  OWNER TO postgres;

DROP TABLE documento;
CREATE TABLE documento(
	id             integer NOT NULL DEFAULT nextval('documento_id_seq'::regclass),
	tabela	 integer NULL,
	parentId	 integer NULL,
	type         integer NULL,
	processId    integer NULL,
	documentoType integer NULL,
	numero       character varying(50) NULL,
	data         bigint,
	estado       integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT pk_documento_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE documento
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE email_id_seq;

CREATE SEQUENCE email_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE email_id_seq
  OWNER TO postgres;

DROP TABLE email;
CREATE TABLE email(
	id             integer NOT NULL DEFAULT nextval('email_id_seq'::regclass),
	tabela	   integer NULL,
	parentId	   integer NULL,
	type         integer NULL,
	processId    integer NULL,
	email        character varying(100)  NULL,
	emailType    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,

CONSTRAINT pk_email_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE email
  OWNER TO postgres;


/* ---------------------------------------------------------------*/
DROP SEQUENCE endereco_id_seq;

CREATE SEQUENCE endereco_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE endereco_id_seq
  OWNER TO postgres;

DROP TABLE endereco;
CREATE TABLE endereco(
	id             integer NOT NULL DEFAULT nextval('endereco_id_seq'::regclass),
	tabela		 integer  NULL,
	parentId	 integer  NULL,
	type         integer  NULL,
	processId    integer NULL,
	logradouro	 character varying(200)  NULL,
	cidade		 integer NULL,
	estado		 integer NULL,
	bairro		 character varying(50)  NULL,
	numero       character varying(10)  NULL,
	cep			 character varying(15)  NULL,
	enderecoType		 integer NULL,
	create_date  bigint,
    create_user  character varying(50)  NULL,
    modify_date  bigint,
    modify_user  character varying(50)  NULL,
CONSTRAINT pk_endereco_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE endereco
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE telefone_id_seq;

CREATE SEQUENCE telefone_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE telefone_id_seq
  OWNER TO postgres;

DROP TABLE telefone;
CREATE TABLE telefone (
	id           integer NOT NULL DEFAULT nextval('telefone_id_seq'::regclass),
	type         integer NULL,
	parentId	   integer NULL,
	tabela	   integer NULL,
	processId    integer NULL,
	telefonetype integer NULL,
	ddd 		   character varying(5) NULL,
	telefone     character varying(15) NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT pk_telefone_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE telefone
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE socio_id_seq;

CREATE SEQUENCE socio_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE socio_id_seq
  OWNER TO postgres;

DROP TABLE socio;
CREATE TABLE socio(
	id             integer NOT NULL DEFAULT nextval('socio_id_seq'::regclass),
	parentId	   integer NULL,
	nome         character varying(200) NULL,
	cota         real NULL,
	porcentagem  real NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,

CONSTRAINT pk_socios_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE socio
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE historico_id_seq;

CREATE SEQUENCE historico_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE historico_id_seq
  OWNER TO postgres;

DROP TABLE historico;

CREATE TABLE historico (
	id           integer NOT NULL DEFAULT nextval('historico_id_seq'::regclass),
	data	       bigint,
	userId	   character varying(20) NULL,
	emprId       integer NULL,
	tabela 	   integer NOT NULL,
	acao 		   integer NOT NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT pk_historico_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historico
  OWNER TO postgres;


/* ---------------------------------------------------------------*/
DROP SEQUENCE cnae_id_seq;

CREATE SEQUENCE cnae_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cnae_id_seq
  OWNER TO postgres;

DROP TABLE CNAE;
CREATE TABLE CNAE (
    id             integer NOT NULL DEFAULT nextval('cnae_id_seq'::regclass),
    CODIGO     character varying(6) NOT NULL,
    CNAE       character varying(10) NULL,
    DESCRICAO  character varying(100) NULL,
    ABREVIADO  character varying(50) NULL,
    status       integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT pk_cnae_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE CNAE
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE CSOSN_id_seq;

CREATE SEQUENCE CSOSN_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE CSOSN_id_seq
  OWNER TO postgres;

CREATE TABLE CSOSN (
    CODIGO     character varying(3) NOT NULL,
    DESCRICAO  character varying(200) NULL,
    status       integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT CSOSN_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE CSOSN
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE NCM_id_seq;

CREATE SEQUENCE NCM_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE NCM_id_seq
  OWNER TO postgres;

CREATE TABLE NCM (
    NCM        character varying(10) NOT NULL,
    DESCRICAO  character varying(100) NULL,
    UNIDADE    character varying(2) NULL,
    status       integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT NCM_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE NCM
  OWNER TO postgres;

/* --------------------------------------------------------------- */
DROP SEQUENCE cidade_id_seq;

CREATE SEQUENCE cidade_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cidade_id_seq
  OWNER TO postgres;

DROP TABLE CIDADE;
CREATE TABLE CIDADE(
    ID         integer NOT NULL DEFAULT nextval('cidade_id_seq'::regclass),
    CIDADE     character varying(40) NULL,
    ESTADO     integer not NULL,
    CEP        character varying(10) NULL,
    CODIGO     character varying(10) NULL,
    MUNICIPIO  character varying(40) NULL,
    UF  		 integer not NULL,
    processId  integer NULL,
    IBGE       character varying(40) NULL,
    status       integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT cidade_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cidade
  OWNER TO postgres;

/* --------------------------------------------------------------- */
DROP SEQUENCE cnaePorRelacionamento_id_seq;

CREATE SEQUENCE cnaePorRelacionamento_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cnaePorRelacionamento_id_seq
  OWNER TO postgres;

DROP TABLE  cnaePorRelacionamento;
CREATE TABLE cnaePorRelacionamento(
    id         integer NOT NULL DEFAULT nextval('cnaePorRelacionamento_id_seq'::regclass),
    idCnae     integer NOT NULL,
    parentId integer NOT NULL,
    tabela     integer NOT NULL,
    processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT cnaePorRelacionamento_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cnaePorRelacionamento
  OWNER TO postgres;

/* --------------------------------------------------------------- */
DROP SEQUENCE Salario_id_seq;

CREATE SEQUENCE Salario_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Salario_id_seq
  OWNER TO postgres;

DROP TABLE Salario;
CREATE TABLE Salario(
	id             integer NOT NULL DEFAULT nextval('Salario_id_seq'::regclass),
	parentId     integer NULL,
	valor        real NULL,
	processId    integer NULL,
	data         bigint,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT Salario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Salario
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE Beneficio_id_seq;

CREATE SEQUENCE Beneficio_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Beneficio_id_seq
  OWNER TO postgres;

DROP TABLE Beneficio;
CREATE TABLE Beneficio(
	id             integer NOT NULL DEFAULT nextval('Beneficio_id_seq'::regclass),
	nome         character varying(50) NULL,
    codigo       character varying(50) NULL,
    descricao    character varying(50) NULL,
	valor        real NULL,
	porcentagem  real NULL,
	processId    integer NULL,
	tipo		   character varying(10) NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT Beneficio_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Beneficio
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE BeneficioFunc_id_seq;

CREATE SEQUENCE BeneficioFunc_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE BeneficioFunc_id_seq
  OWNER TO postgres;

DROP TABLE BeneficioFunc;
CREATE TABLE BeneficioFunc(
	id           integer NOT NULL DEFAULT nextval('BeneficioFunc_id_seq'::regclass),
	idFunc       integer NOT NULL,
	idBenef      integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT BeneficioFunc_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE BeneficioFunc
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE BeneficioMesApp_id_seq;

CREATE SEQUENCE BeneficioMesApp_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE BeneficioMesApp_id_seq
  OWNER TO postgres;

DROP TABLE BeneficioMesApp;
CREATE TABLE BeneficioMesApp(
	id             integer NOT NULL DEFAULT nextval('BeneficioMesApp_id_seq'::regclass),
	data         bigint,
	idFuncBenef integer NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT BeneficioMesApp_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE BeneficioMesApp
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE Evento_id_seq;

CREATE SEQUENCE Evento_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Evento_id_seq
  OWNER TO postgres;

DROP TABLE Evento;
CREATE TABLE Evento(
	id             integer NOT NULL DEFAULT nextval('Evento_id_seq'::regclass),
	data         bigint,
	nome         character varying(50) NULL,
	descricao    character varying(50) NULL,
	tipo         character varying(50) NULL,
    codigo       character varying(50) NULL,
    noteText     character varying(200) NULL,
	valor        real NULL,
	porcentagem  real NULL,
	isMensal     bit NULL,
	isSistema    bit NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT Evento_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Evento
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE EventoFunc_id_seq;

CREATE SEQUENCE EventoFunc_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE EventoFunc_id_seq
  OWNER TO postgres;

DROP TABLE EventoFunc;
CREATE TABLE EventoFunc(
	id           integer NOT NULL DEFAULT nextval('EventoFunc_id_seq'::regclass),
	data         bigint,
	idFunc	   integer NULL,
	idEvent	   integer NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT EventoFunc_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE EventoFunc
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE EventoMesApp_id_seq;

CREATE SEQUENCE EventoMesApp_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE EventoMesApp_id_seq
  OWNER TO postgres;

DROP TABLE EventoMesApp;
CREATE TABLE EventoMesApp(
	id             integer NOT NULL DEFAULT nextval('EventoMesApp_id_seq'::regclass),
	data         bigint,
	idFuncEnvent integer NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT EventoMesApp_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE EventoMesApp
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE Pessoa_id_seq;

CREATE SEQUENCE Pessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Pessoa_id_seq
  OWNER TO postgres;

DROP TABLE Pessoa;
CREATE TABLE Pessoa(
	id             integer NOT NULL DEFAULT nextval('Pessoa_id_seq'::regclass),
	nome         character varying(200) NULL,
	cdEmpr 	   integer NOT NULL,
	sexo 		   integer NULL,
	type         integer NOT NULL,
	dataNasc     bigint,
	dataAdmin    bigint,
	nomePai 	   character varying(200) NULL,
	nomeMae      character varying(200) NULL,
	nomeConjugue character varying(200) NULL,
	estadoCivil  integer NULL,
	processId    integer NULL,
	matricula	   character varying(50) NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT Pessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Pessoa
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE horarios_id_seq;

CREATE SEQUENCE horarios_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE horarios_id_seq
  OWNER TO postgres;

DROP TABLE horarios;
CREATE TABLE horarios(
	id           integer NOT NULL DEFAULT nextval('horarios_id_seq'::regclass),
	parentId     integer NULL,
	data         bigint,
	horarioEntr  bigint,
	horarioSair  bigint,
	create_date  bigint,
	tipo     	   character varying(50) NULL,
	processId    integer NULL,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT horarios_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE horarios
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE Profissao_id_seq;

CREATE SEQUENCE Profissao_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Profissao_id_seq
  OWNER TO postgres;

DROP TABLE Profissao;
CREATE TABLE Profissao(
	id          	 integer NOT NULL DEFAULT nextval('Profissao_id_seq'::regclass),
	parentId     	 integer NOT NULL,
	profissao 	 character varying(100) NULL,
	renda 		 real NULL,
	dataAdmissao   bigint,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT Profissao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Profissao
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE Convenio_id_seq;

CREATE SEQUENCE Convenio_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Convenio_id_seq
  OWNER TO postgres;

DROP TABLE Convenio;
CREATE TABLE Convenio(
	id          	 integer NOT NULL DEFAULT nextval('Convenio_id_seq'::regclass),
	nome 	 		 character varying(100) NULL,
	dataini   	 bigint,
	dataFin   	 bigint,
	porcentagem 	 real NULL,
	valor 		 real NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT Convenio_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Convenio
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE ConvenioPessoa_id_seq;

CREATE SEQUENCE ConvenioPessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ConvenioPessoa_id_seq
  OWNER TO postgres;

DROP TABLE ConvenioPessoa;
CREATE TABLE ConvenioPessoa(
	id          	 integer NOT NULL DEFAULT nextval('ConvenioPessoa_id_seq'::regclass),
	data  bigint,
	parentId 	 	 integer NOT NULL,
	convId   	 integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT ConvenioPessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ConvenioPessoa
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE CondPag_id_seq;

CREATE SEQUENCE CondPag_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE CondPag_id_seq
  OWNER TO postgres;

DROP TABLE CondPag;
CREATE TABLE CondPag(
	id          	 integer NOT NULL DEFAULT nextval('CondPag_id_seq'::regclass),
	nome 	 		 character varying(100) NULL,
	dataini   	 bigint,
	dataFin   	 bigint,
	porcentagem 	 real NULL,
	valor 		 real NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT CondPag_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE CondPag
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE CondPagPessoa_id_seq;

CREATE SEQUENCE CondPagPessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE CondPagPessoa_id_seq
  OWNER TO postgres;

DROP TABLE CondPagPessoa;
CREATE TABLE CondPagPessoa(
	id          	 integer NOT NULL DEFAULT nextval('CondPagPessoa_id_seq'::regclass),
	parentId 	 	 integer NOT NULL,
	condPagId   	 integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT CondPagPessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE CondPagPessoa
  OWNER TO postgres;


/* ---------------------------------------------------------------*/
DROP SEQUENCE TipoPag_id_seq;

CREATE SEQUENCE TipoPag_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE TipoPag_id_seq
  OWNER TO postgres;

DROP TABLE TipoPag;
CREATE TABLE TipoPag(
	id          	integer NOT NULL DEFAULT nextval('TipoPag_id_seq'::regclass),
	descricao 	 character varying(100) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT TipoPag_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE TipoPag
  OWNER TO postgres;


/* ---------------------------------------------------------------*/
DROP SEQUENCE TipoPagReg_id_seq;

CREATE SEQUENCE TipoPagReg_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE TipoPagReg_id_seq
  OWNER TO postgres;

DROP TABLE TipoPagReg;
CREATE TABLE TipoPagReg(
	id          	         integer NOT NULL DEFAULT nextval('TipoPagReg_id_seq'::regclass),
	parentId          	 integer NOT NULL,
	tipoPagId          	 integer NOT NULL,
	tabela          	     integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT TipoPagReg_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE TipoPagReg
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE banco_id_seq;

CREATE SEQUENCE banco_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE banco_id_seq
  OWNER TO postgres;

DROP TABLE banco;
CREATE TABLE banco(
	id          	         integer NOT NULL DEFAULT nextval('banco_id_seq'::regclass),
	nome          	 	 character varying(50) NULL,
	logo          	 	 character varying(50) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT banco_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE banco
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE agencia_id_seq;

CREATE SEQUENCE agencia_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE agencia_id_seq
  OWNER TO postgres;

DROP TABLE agencia;
CREATE TABLE agencia(
	id          	         integer NOT NULL DEFAULT nextval('agencia_id_seq'::regclass),
	nome          	 	 character varying(50) NULL,
	parentId          	 integer NOT NULL,
	numeroConta          	 	 character varying(50) NULL,
	gerente          	 	 character varying(50) NULL,
	responsavelConta          	 	 character varying(50) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT agencia_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE agencia
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE bancoPessoa_id_seq;

CREATE SEQUENCE bancoPessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE bancoPessoa_id_seq
  OWNER TO postgres;

DROP TABLE BancoPessoa;
CREATE TABLE BancoPessoa(
	id          	         integer NOT NULL DEFAULT nextval('bancoPessoa_id_seq'::regclass),
	parentId          	 integer NOT NULL,
	bancoId          	 	 integer NOT NULL,
	numCont          	     character varying(50) NULL,
	saldo          	     real NOT NULL,
	processId    			 integer NULL,
	agenciaId    			 integer NULL,
	tabela    			 integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT bancoPessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bancoPessoa
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE contato_id_seq;

CREATE SEQUENCE contato_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE contato_id_seq
  OWNER TO postgres;

DROP TABLE contato;
CREATE TABLE contato(
	id             integer NOT NULL DEFAULT nextval('contato_id_seq'::regclass),
	parentId     integer NOT NULL,
	motivo       integer NULL,
	processId    integer NULL,
	dataContato bigint,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT contato_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contato
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE contatoItens_id_seq;

CREATE SEQUENCE contatoItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE contatoItens_id_seq
  OWNER TO postgres;

DROP TABLE contatoItens;
CREATE TABLE contatoItens(
	id             integer NOT NULL DEFAULT nextval('contatoItens_id_seq'::regclass),
	parentId     integer NOT NULL,
	texto        character varying(250) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT contatoItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contatoItens
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE ordemServico_id_seq;

CREATE SEQUENCE ordemServico_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ordemServico_id_seq
  OWNER TO postgres;

DROP TABLE ordemServico;
CREATE TABLE ordemServico(
	id             integer NOT NULL DEFAULT nextval('ordemServico_id_seq'::regclass),
	emprId       integer NOT NULL,
	userId       character varying(50) NULL,
	nome         character varying(100) NULL,
	data    	   bigint,
	typeId       integer NOT NULL,
	assunto      character varying(100) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT ordemServico_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ordemServico
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE ordemServicoItens_id_seq;

CREATE SEQUENCE ordemServicoItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ordemServicoItens_id_seq
  OWNER TO postgres;

DROP TABLE ordemServicoItens;
CREATE TABLE ordemServicoItens(
	id             integer NOT NULL DEFAULT nextval('ordemServicoItens_id_seq'::regclass),
	idOrdemServico integer NOT NULL,
	status       	 integer NOT NULL,
	data    	     bigint,
	texto          character varying(255) NULL,
	processId      integer  NULL,
	create_date    bigint,
    create_user    character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT ordemServicoItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cnae
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE ordemServicoTypes_id_seq;

CREATE SEQUENCE ordemServicoTypes_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ordemServicoTypes_id_seq
  OWNER TO postgres;

DROP TABLE ordemServicoTypes;
CREATE TABLE ordemServicoTypes(
	id             integer NOT NULL DEFAULT nextval('ordemServicoTypes_id_seq'::regclass),
	type         character varying(100) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT ordemServicoTypes_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ordemServicoTypes
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE ordemServicoStatus_id_seq;

CREATE SEQUENCE ordemServicoStatus_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ordemServicoStatus_id_seq
  OWNER TO postgres;

DROP TABLE ordemServicoStatus;
CREATE TABLE ordemServicoStatus(
	id             integer NOT NULL DEFAULT nextval('ordemServicoStatus_id_seq'::regclass),
	status       character varying(100) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT ordemServicoStatus_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ordemServicoStatus
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE tabela_id_seq;

CREATE SEQUENCE tabela_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tabela_id_seq
  OWNER TO postgres;

DROP TABLE tabela;
CREATE TABLE tabela(
	id             integer NOT NULL DEFAULT nextval('tabela_id_seq'::regclass),
	nome         character varying(50) NOT NULL,
	descricao    character varying(250) NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT tabela_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tabela
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE atributos_id_seq;

CREATE SEQUENCE atributos_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE atributos_id_seq
  OWNER TO postgres;

DROP TABLE atributos;
CREATE TABLE atributos(
	id             integer NOT NULL DEFAULT nextval('atributos_id_seq'::regclass),
	nome         character varying(50) NOT NULL,
	descricao    character varying(250) NULL,
	tamanho      integer NOT NULL,
	obrigatorio  integer NOT NULL,
	chavePrimaria  integer NOT NULL,
	chaveSecundaria  integer NOT NULL,
	tabelaSecundaria  integer NOT NULL,
	type  integer NOT NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT atributos_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE atributos
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE unimed_id_seq;

CREATE SEQUENCE unimed_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE unimed_id_seq
  OWNER TO postgres;

DROP TABLE unimed;
CREATE TABLE unimed(
	id             integer NOT NULL DEFAULT nextval('unimed_id_seq'::regclass),
	unimed       character varying(100) NOT NULL,
	sigla        character varying(5) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT unimed_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE unimed
  OWNER TO postgres;


/* ---------------------------------------------------------------*/
DROP SEQUENCE unimedProd_id_seq;

CREATE SEQUENCE unimedProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE unimedProd_id_seq
  OWNER TO postgres;

DROP TABLE unimedProd;
CREATE TABLE unimedProd(
	id             integer NOT NULL DEFAULT nextval('unimedProd_id_seq'::regclass),
	unimedId     integer NOT NULL,
	prodId       integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT unimedProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE unimedProd
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE grupo_id_seq;

CREATE SEQUENCE grupo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE grupo_id_seq
  OWNER TO postgres;

DROP TABLE grupo;
CREATE TABLE grupo(
	id             integer NOT NULL DEFAULT nextval('grupo_id_seq'::regclass),
	grupo        character varying(100) NOT NULL,
	descricao    character varying(250) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT grupo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE grupo
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE grupoProd_id_seq;

CREATE SEQUENCE grupoProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE grupoProd_id_seq
  OWNER TO postgres;

DROP TABLE grupoProd;
CREATE TABLE grupoProd(
	id           integer NOT NULL DEFAULT nextval('grupoProd_id_seq'::regclass),
	grupoId      integer NOT NULL,
	prodId       integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT grupoProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE grupoProd
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE subgrupo_id_seq;

CREATE SEQUENCE subgrupo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE subgrupo_id_seq
  OWNER TO postgres;

DROP TABLE subgrupo;
CREATE TABLE subgrupo(
	id             integer NOT NULL DEFAULT nextval('subgrupo_id_seq'::regclass),
	subgrupo     character varying(100) NOT NULL,
	descricao    character varying(250) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT subgrupo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE subgrupo
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE subgrupoProd_id_seq;

CREATE SEQUENCE subgrupoProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE subgrupoProd_id_seq
  OWNER TO postgres;

DROP TABLE subgrupoProd;
CREATE TABLE subgrupoGrupo(
	id           integer NOT NULL DEFAULT nextval('subgrupoProd_id_seq'::regclass),
	grupoId      integer NOT NULL,
	subGrupoId   integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT subgrupoProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE subgrupoProd
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE marca_id_seq;

CREATE SEQUENCE marca_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE marca_id_seq
  OWNER TO postgres;

DROP TABLE marca;
CREATE TABLE marca(
	id             integer NOT NULL DEFAULT nextval('marca_id_seq'::regclass),
	marca        character varying(100) NOT NULL,
	fabricante   character varying(100) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT marca_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE marca
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE marcaProd_id_seq;

CREATE SEQUENCE marcaProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE marcaProd_id_seq
  OWNER TO postgres;

DROP TABLE marcaProd;
CREATE TABLE marcaProd(
	id             integer NOT NULL DEFAULT nextval('marcaProd_id_seq'::regclass),
	marcaId      integer NOT NULL,
	prodId       integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT marcaProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE marcaProd
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE cst_id_seq;

CREATE SEQUENCE cst_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cst_id_seq
  OWNER TO postgres;

DROP TABLE cst;
CREATE TABLE cst(
	id             integer NOT NULL DEFAULT nextval('cst_id_seq'::regclass),
	nome         character varying(150) NULL,
	descricao    character varying(150) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT cst_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cst
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE csosnProd_id_seq;

CREATE SEQUENCE csosnProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE csosnProd_id_seq
  OWNER TO postgres;

DROP TABLE csosnProd;
CREATE TABLE csosnProd(
	id             integer NOT NULL DEFAULT nextval('csosnProd_id_seq'::regclass),
	csosnId      integer NOT NULL,
	prodId       integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT csosnProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE csosnProd
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE incidencia_id_seq;

CREATE SEQUENCE incidencia_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE incidencia_id_seq
  OWNER TO postgres;
DROP TABLE incidencia;
CREATE TABLE incidencia(
	id             integer NOT NULL DEFAULT nextval('incidencia_id_seq'::regclass),
	codigo       character varying(50) NULL,
	texto        character varying(150) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT incidencia_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE incidencia
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE tributacao_id_seq;

CREATE SEQUENCE tributacao_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tributacao_id_seq
  OWNER TO postgres;

DROP TABLE tributacao;
CREATE TABLE tributacao(
	id           integer NOT NULL DEFAULT nextval('tributacao_id_seq'::regclass),
	prodId       integer NOT NULL,
	cstId        integer NOT NULL,
	processId    integer NULL,
	icms   	   real NULL,
	st   		   integer NULL,
	mva   	   real NULL,
	csosnId      integer NULL,
	ipi          real NULL,
	iat   	   integer NULL,
	ippt   	   integer NULL,
	pisconfins   integer NULL,
	parentId   integer NULL,
	incidencia integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT tributacao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tributacao
  OWNER TO postgres;


/* ---------------------------------------------------------------*/

DROP SEQUENCE classificacao_id_seq;

CREATE SEQUENCE classificacao_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE classificacao_id_seq
  OWNER TO postgres;

DROP TABLE classificacao;
CREATE TABLE classificacao(
	id             integer NOT NULL DEFAULT nextval('classificacao_id_seq'::regclass),
	codigo       character varying(50) NULL,
	descricao    character varying(150) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT classificacao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE classificacao
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE classificacaoProd_id_seq;

CREATE SEQUENCE classificacaoProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE classificacaoProd_id_seq
  OWNER TO postgres;

DROP TABLE classificacaoProd;
CREATE TABLE classificacaoProd(
	id             integer NOT NULL DEFAULT nextval('classificacaoProd_id_seq'::regclass),
	classificacaoId integer NOT NULL,
	prodId       integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT classificacaoProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE classificacaoProd
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE estoque_id_seq;

CREATE SEQUENCE estoque_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE estoque_id_seq
  OWNER TO postgres;

DROP TABLE estoque;
CREATE TABLE estoque(
	id              integer NOT NULL DEFAULT nextval('estoque_id_seq'::regclass),
	entidadeId      integer  NULL,
	empId           integer  NULL,
	prodId          integer NOT NULL,
	estoqueTypeEnum integer NOT NULL,
	processId       integer NULL,
	ultimoMov       bigint,
	quant           real NOT NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT estoque_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE estoque
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE tabPreco_id_seq;

CREATE SEQUENCE tabPreco_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tabPreco_id_seq
  OWNER TO postgres;

DROP TABLE tabPreco;
CREATE TABLE tabPreco(
	id              integer NOT NULL DEFAULT nextval('tabPreco_id_seq'::regclass),
	prodId          integer NOT NULL,
	emprId           integer NOT NULL,
	entidadeId      integer NULL,
	precoTypeEnum   integer NOT NULL,
	dataMarcacao    bigint,
	valor           real NOT NULL,
	dataProInicial  bigint,
	dataProFinal    bigint,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT tabPreco_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tabPreco
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE custoItens_id_seq;

CREATE SEQUENCE custoItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE custoItens_id_seq
  OWNER TO postgres;

DROP TABLE custoItens;
CREATE TABLE custoItens(
	id               integer NOT NULL DEFAULT nextval('custoItens_id_seq'::regclass),
	custo   		   character varying(50) NULL,
	custoDesp        integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT custoItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE custoItens
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE custo_id_seq;

CREATE SEQUENCE custo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE custo_id_seq
  OWNER TO postgres;

DROP TABLE custo;
CREATE TABLE custo(
	id             integer NOT NULL DEFAULT nextval('custo_id_seq'::regclass),
	prodId          integer NOT NULL,
	idcustoItens    integer NOT NULL,
	valor           real NOT NULL,
	entidadeId    integer NULL,
	emprId    integer NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT custo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE custo
  OWNER TO postgres;
/* ---------------------------------------------------------------*/

DROP SEQUENCE porcao_id_seq;

CREATE SEQUENCE porcao_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE porcao_id_seq
  OWNER TO postgres;

DROP TABLE porcao;
CREATE TABLE porcao(
	id              integer NOT NULL DEFAULT nextval('porcao_id_seq'::regclass),
	prodId          integer NOT NULL,
	valor           real NOT NULL,
	processId    integer NULL,
	idporcaoItens    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT porcao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE porcao
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE porcaoItens_id_seq;

CREATE SEQUENCE porcaoItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE porcaoItens_id_seq
  OWNER TO postgres;

DROP TABLE porcaoItens;
CREATE TABLE porcaoItens(
	id               integer NOT NULL DEFAULT nextval('porcaoItens_id_seq'::regclass),
	idporcao         integer NOT NULL,
	unimed           integer NOT NULL,
	porcao   		   real NULL,
	vd   		       real NULL,
	nome   		   character varying(150) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT porcaoItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE porcaoItens
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE rentabilidade_id_seq;

CREATE SEQUENCE rentabilidade_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE rentabilidade_id_seq
  OWNER TO postgres;

DROP TABLE rentabilidade;
CREATE TABLE rentabilidade(
	id                    integer NOT NULL DEFAULT nextval('rentabilidade_id_seq'::regclass),
	prodId                integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT rentabilidade_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rentabilidade
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE rentabilidadeProdutos_id_seq;

CREATE SEQUENCE rentabilidadeProdutos_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE rentabilidadeProdutos_id_seq
  OWNER TO postgres;

DROP TABLE rentabilidadeProdutos;
CREATE TABLE rentabilidadeProdutos(
	id                   integer NOT NULL DEFAULT nextval('rentabilidadeProdutos_id_seq'::regclass),
	idrentabilidade       integer NOT NULL,
	idprod                integer NOT NULL,
	valor                 real NOT NULL,
	rentabilidadeTypeEnum integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT rentabilidadeProdutos_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rentabilidadeProdutos
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE cfop_id_seq;

CREATE SEQUENCE cfop_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cfop_id_seq
  OWNER TO postgres;

DROP TABLE cfop;
CREATE TABLE cfop(
	id            integer NOT NULL DEFAULT nextval('cfop_id_seq'::regclass),
	cfop          character varying(50) NULL,
	natureza      character varying(50) NULL,
	simplificado  character varying(50) NULL,
	cfopTypeEnum  integer NOT NULL,
	icms          real  NULL,
	icmsReduzido  real  NULL,
	margemAgregadaST real  NULL,
	cstPrincipal    real  NULL,
	classFiscal     real  NULL,
	observacao      character varying(250) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT cfop_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cfop
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE CfopParentId_id_seq;

CREATE SEQUENCE CfopParentId_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE CfopParentId_id_seq
  OWNER TO postgres;

DROP TABLE CfopParentId;
CREATE TABLE CfopParentId(
	id              integer NOT NULL DEFAULT nextval('CfopParentId_id_seq'::regclass),
	idCfop          integer NOT NULL,
	parentId		  integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT CfopParentId_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE CfopParentId
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE ForneProd_id_seq;

CREATE SEQUENCE ForneProd_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ForneProd_id_seq
  OWNER TO postgres;

DROP TABLE ForneProd;
CREATE TABLE ForneProd(
	id             integer NOT NULL DEFAULT nextval('ForneProd_id_seq'::regclass),
	idForn       integer NOT NULL,
	prodId	   integer NOT NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT ForneProd_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ForneProd
  OWNER TO postgres;

INSERT INTO ForneProd
           (idForn,prodId,create_user,create_date,modify_date,modify_user)
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
/* ---------------------------------------------------------------*/

DROP SEQUENCE produto_id_seq;

CREATE SEQUENCE produto_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE produto_id_seq
  OWNER TO postgres;

DROP TABLE produto;
CREATE TABLE produto(
	id             integer NOT NULL DEFAULT nextval('produto_id_seq'::regclass),
	codigo       character varying(50) NOT NULL,
	cdBarras     character varying(50) NULL,
	dataCreate   bigint,
	produto  	   character varying(250) NOT NULL,
	modoUso  	   character varying(250) NULL,
	aplicacao    character varying(250) NULL,
	localizacao  character varying(200) NULL,
	dataValidade integer NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	comissao     real  NULL,
	fracao       real  NULL,
	porcao  	   real  NULL,
	pesoBruto    real  NULL,
	pesoLiquido  real  NULL,
	processId    integer NULL,
	grupo    integer NULL,
	marca    integer NULL,
	subgrupo    integer NULL,
	unimed    integer NULL,
	tributacao    integer NULL,
	classificacao    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT produto_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE conhecimentoTransporte_id_seq;

CREATE SEQUENCE conhecimentoTransporte_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE conhecimentoTransporte_id_seq
  OWNER TO postgres;

DROP TABLE conhecimentoTransporte;
CREATE TABLE conhecimentoTransporte(
	id             integer NOT NULL DEFAULT nextval('conhecimentoTransporte_id_seq'::regclass),
	IdNota       			integer NOT NULL,
	transportador			integer NOT NULL,
	remetente    			character varying(250) NOT NULL,
	vrTotalMercadorias 	real  NULL,
	apCreIcms    			integer NOT NULL,
	fretePorConta 		integer NOT NULL,
	placa  				character varying(200) NULL,
	estado 				integer NOT NULL,
	marca  				integer NOT NULL,
	especie  	  			real  NULL,
	pesoBruto    		    real  NULL,
	pesoLiquido  			real  NULL,
	volume  				real  NULL,
	processId    			integer NOT NULL,
	create_date  			bigint,
    create_user  			character varying(50) NULL,
    modify_date  			bigint,
    modify_user  			character varying(50) NULL,
CONSTRAINT conhecimentoTransporte_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conhecimentoTransporte
  OWNER TO postgres;
/* ---------------------------------------------------------------*/

DROP SEQUENCE notaFiscalItens_id_seq;

CREATE SEQUENCE notaFiscalItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE notaFiscalItens_id_seq
  OWNER TO postgres;

DROP TABLE notaFiscalItens;
CREATE TABLE notaFiscalItens(
	id             integer NOT NULL DEFAULT nextval('notaFiscalItens_id_seq'::regclass),
	IdNota       	integer NOT NULL,
	qnt			real NOT NULL,
	vrUnitario    real NOT NULL,
	vrDesconto 	real  NULL,
	produto    	integer NOT NULL,
	cfop 			integer NOT NULL,
	classificacao integer NOT NULL,
	processId     integer  NULL,
	create_date  			bigint,
    create_user  			character varying(50) NULL,
    modify_date  			bigint,
    modify_user  			character varying(50) NULL,
CONSTRAINT notaFiscalItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE notaFiscalItens
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE contas_id_seq;

CREATE SEQUENCE contas_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE contas_id_seq
  OWNER TO postgres;

DROP TABLE contas;
CREATE TABLE contas(
	id             integer NOT NULL DEFAULT nextval('contas_id_seq'::regclass),
	docId 	integer NOT NULL,
	pessoaId 	integer NOT NULL,
	parentId 	integer  NULL,
	contasTypeEnum 	integer  NULL,
	emprId		integer  NULL,
	numeroParc 	integer  NULL,
	parcela    	integer  NULL,
	valorOriginal	real  NULL,
	dataVencimento bigint,
	dataGeracao    bigint,
	dataPagamento  integer NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	juros			 real  NULL,
	taxa           real  NULL,
	valorTotal 	 real  NULL,
	baixaUser		 integer  NULL,
	baixaDateUTC	 integer NULL DEFAULT (datediff(second,'1/1/1970',getutcdate())),
	processId      integer NULL,
	create_date  			bigint,
    create_user  			character varying(50) NULL,
    modify_date  			bigint,
    modify_user  			character varying(50) NULL,
CONSTRAINT contas_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contas
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE itensEspeciais_id_seq;

CREATE SEQUENCE itensEspeciais_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE itensEspeciais_id_seq
  OWNER TO postgres;

DROP TABLE itensEspeciais;
CREATE TABLE itensEspeciais(
	id             integer NOT NULL DEFAULT nextval('itensEspeciais_id_seq'::regclass),
	item 		    integer NOT NULL,
	IdNota		integer NOT NULL,
	nome          character varying(50) NULL,
	valor 	    real  NULL,
	baseCalculo  	real  NULL,
	aliguotaICMS	real  NULL,
	valorICMS     real  NULL,
	processId     integer  NULL,
	create_date  	bigint,
    create_user  	character varying(50) NULL,
    modify_date  	bigint,
    modify_user  	character varying(50) NULL,
CONSTRAINT itensEspeciais_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE itensEspeciais
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE nFStatus_id_seq;

CREATE SEQUENCE nFStatus_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE nFStatus_id_seq
  OWNER TO postgres;

DROP TABLE nFStatus;
CREATE TABLE nFStatus(
	id            integer NOT NULL DEFAULT nextval('nFStatus_id_seq'::regclass),
	IdNota		integer  NULL,
	status        integer  NULL,
	dataMudanca  	bigint,
	processId     integer  NULL,
	create_date  	bigint,
    create_user  	character varying(50) NULL,
    modify_date  	bigint,
    modify_user  	character varying(50) NULL,
CONSTRAINT nFStatus_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE nFStatus
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE notaFiscal_id_seq;

CREATE SEQUENCE notaFiscal_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE notaFiscal_id_seq
  OWNER TO postgres;

DROP TABLE notaFiscal;
CREATE TABLE notaFiscal(
	id             integer NOT NULL DEFAULT nextval('notaFiscal_id_seq'::regclass),
	serie		     character varying(50) NULL,
	ordem          character varying(50) NULL,
	numero  	     integer NOT NULL,
	tipo  	     character varying(50) NULL,
    nfValor  	     real NOT NULL,
    dataEmissao  	 bigint,
    dataSaida      bigint,
	dataEntrada	 bigint,
	modelo         character varying(50) NULL,
	bxEstoque  	 integer NULL,
	descItens  	 integer NULL,
    pcCusto  	     integer NULL,
    pedidoCompraId integer NULL,
    orcamentoId  	 integer NULL,
    cfop           integer NOT NULL,
	transportador  integer NULL,
	conhecimentoTransporte  integer NULL,
    empresa  	    integer NULL,
    pessoa    integer NULL,
    processId     integer  NULL,
	create_date   bigint,
    create_user  	character varying(50) NULL,
    modify_date  	bigint,
    modify_user  	character varying(50) NULL,
CONSTRAINT notaFiscal_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE notaFiscal
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE process_id_seq;

CREATE SEQUENCE process_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE process_id_seq
  OWNER TO postgres;

DROP TABLE process;
CREATE TABLE process(
	id             integer NOT NULL DEFAULT nextval('process_id_seq'::regclass),
	tabelaEnum	 integer NOT NULL,
	acaoEnum		 integer NOT NULL,
	empId 		 integer NOT NULL,
    data  	     bigint,
	create_date  	bigint,
    create_user  	character varying(50) NULL,
    modify_date  	bigint,
    modify_user  	character varying(50) NULL,
CONSTRAINT process_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE process
  OWNER TO postgres;


/* ---------------------------------------------------------------*/

DROP SEQUENCE historicoItens_id_seq;

CREATE SEQUENCE historicoItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE historicoItens_id_seq
  OWNER TO postgres;

DROP TABLE historicoItens;
CREATE TABLE historicoItens(
	id             integer NOT NULL DEFAULT nextval('historicoItens_id_seq'::regclass),
	idHist	     integer NOT NULL,
	processId		 integer NOT NULL,
	type 		     integer NOT NULL,
	tabela 		 integer NOT NULL,
	parentId 		 integer NOT NULL,
    data  	     bigint,
	create_date  	 bigint,
    create_user  	 character varying(50) NULL,
    modify_date  	 bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT historicoItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historicoItens
  OWNER TO postgres;


/* ---------------------------------------------------------------

DROP SEQUENCE cnae_id_seq;

CREATE SEQUENCE cnae_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cnae_id_seq
  OWNER TO postgres;

DROP PROCEDURE fetchProcessId;
CREATE PROCEDURE fetchProcessId
	@ptabela int,
	@pempId int,
	@pacao int,
	@puser varchar(50)

AS

declare @p_time_of_request int = 0
BEGIN

	@p_time_of_request = 1432783357778

	INSERT  INTO process1 (tabela,empId,acaoEnum,create_date,create_user)VALUES(@ptabela, @pempId,@pacao,@p_time_of_request,@puser)
	select @p_time_of_request = max(id) from process1

  RETURN @p_time_of_request
END

 ---------------------------------------------------------------*/

DROP SEQUENCE estado_id_seq;

CREATE SEQUENCE estado_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE estado_id_seq
  OWNER TO postgres;


DROP TABLE estado;
CREATE TABLE estado(
	id             integer NOT NULL DEFAULT nextval('estado_id_seq'::regclass),
	nome		 character varying(50) NULL,
	abreviacao  character varying(50) NULL,
	create_date  	bigint,
    create_user  	character varying(50) NULL,
    modify_date  	bigint,
    modify_user  	character varying(50) NULL,
CONSTRAINT estado_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE estado
  OWNER TO postgres;

/* ---------------------------------------------------------------*/
DROP SEQUENCE contato_id_seq;

CREATE SEQUENCE contato_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE contato_id_seq
  OWNER TO postgres;

DROP TABLE contato;
CREATE TABLE contato(
	id          	         integer NOT NULL DEFAULT nextval('contato_id_seq'::regclass),
	dataContato         	 bigint,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT contato_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contato
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE contatoItens_id_seq;

CREATE SEQUENCE contatoItens_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE contatoItens_id_seq
  OWNER TO postgres;

DROP TABLE contatoItens;
CREATE TABLE contatoItens(
	id          	     integer NOT NULL DEFAULT nextval('contatoItens_id_seq'::regclass),
	parentId         	 integer NULL,
	motivo         	 character varying(250) NULL,
	dataContato      	 bigint,
	nomeContato        character varying(50) NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT contatoItens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contatoItens
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE note1_id_seq;

CREATE SEQUENCE note1_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE note1_id_seq
  OWNER TO postgres;

DROP TABLE note1;
CREATE TABLE note1(
	id          	     integer NOT NULL DEFAULT nextval('note1_id_seq'::regclass),
	parentId         	 integer NULL,
	note_text          character varying(250) NULL,
	tabela        	 integer NULL,
	processId    integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT note1_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE note1
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE produtoFornecedor_id_seq;

CREATE SEQUENCE produtoFornecedor_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE produtoFornecedor_id_seq
  OWNER TO postgres;

DROP TABLE produtoFornecedor;
CREATE TABLE produtoFornecedor(
	id          	     integer NOT NULL DEFAULT nextval('produtoFornecedor_id_seq'::regclass),
	prodId         	 integer NULL,
	fornecId         	 integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT produtoFornecedor_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produtoFornecedor
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE formaPg_id_seq;

CREATE SEQUENCE formaPg_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE formaPg_id_seq
  OWNER TO postgres;

DROP TABLE formaPg;
CREATE TABLE formaPg(
	id          	     integer NOT NULL DEFAULT nextval('formaPg_id_seq'::regclass),
	descricao         character varying(150) NULL,
	diasPg         	integer NULL,
	entrada          integer NULL,
	processId         	 integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT formaPg_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE formaPg
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE formaPgPessoa_id_seq;

CREATE SEQUENCE formaPgPessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE formaPgPessoa_id_seq
  OWNER TO postgres;

DROP TABLE formaPgPessoa;
CREATE TABLE formaPgPessoa(
	id          	    integer NOT NULL DEFAULT nextval('formaPgPessoa_id_seq'::regclass),
	parentId         	integer NULL,
	formaPgId         integer NULL,
	processId         integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT formaPgPessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE formaPgPessoa
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE condPag_id_seq;

CREATE SEQUENCE condPag_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE condPag_id_seq
  OWNER TO postgres;

DROP TABLE condPag;
CREATE TABLE condPag(
	id          	    integer NOT NULL DEFAULT nextval('condPag_id_seq'::regclass),
	nome              character varying(150) NULL,
	valorIni         	integer NULL,
	valorFin          real  NULL,
	valorFin          real  NULL,
	parcelas         	integer NULL,
	processId         integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT condPag_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE condPag
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE tipoPag_id_seq;

CREATE SEQUENCE tipoPag_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tipoPag_id_seq
  OWNER TO postgres;

DROP TABLE tipoPag;
CREATE TABLE tipoPag(
	id          	    integer NOT NULL DEFAULT nextval('tipoPag_id_seq'::regclass),
	descricao         character varying(150) NULL,
	processId         integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT tipoPag_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipoPag
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE condPagPessoa_id_seq;

CREATE SEQUENCE condPagPessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE condPagPessoa_id_seq
  OWNER TO postgres;

DROP TABLE condPagPessoa;
CREATE TABLE condPagPessoa(
	id          	    integer NOT NULL DEFAULT nextval('condPagPessoa_id_seq'::regclass),
	parentId         	integer NULL,
	condPagId         integer NULL,
	processId         integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT condPagPessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE condPagPessoa
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE produtoPessoa_id_seq;

CREATE SEQUENCE produtoPessoa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE produtoPessoa_id_seq
  OWNER TO postgres;

DROP TABLE produtoPessoa;
CREATE TABLE produtoPessoa(
	id          	    integer NOT NULL DEFAULT nextval('produtoPessoa_id_seq'::regclass),
	parentId         	integer NULL,
	prodId            integer NULL,
	processId         integer NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT produtoPessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produtoPessoa
  OWNER TO postgres;

/* ---------------------------------------------------------------*/

DROP SEQUENCE users_id_seq;

CREATE SEQUENCE users_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE users_id_seq
  OWNER TO postgres;

DROP TABLE users;
CREATE TABLE users(
	  id          	    integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
	  username 	character varying(45) NOT NULL,
      password 	character varying(450) NOT NULL,
      enabled  	tinyint(4) NOT NULL DEFAULT '1',
      entidadeId  integer(4) NOT NULL,
CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;
/* ---------------------------------------------------------------*/

DROP SEQUENCE user_roles_id_seq;

CREATE SEQUENCE user_roles_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE user_roles_id_seq
  OWNER TO postgres;

DROP TABLE user_roles;
CREATE TABLE user_roles(
      user_role_id integer NOT NULL DEFAULT nextval('user_roles_id_seq'::regclass),
	  username 	 character varying(45) NOT NULL,
      ROLE 	     character varying(45) NOT NULL,
CONSTRAINT user_roles_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_roles
  OWNER TO postgres;
/*-----------------------------------------------------------------*/
DROP SEQUENCE historicoNF_id_seq;

CREATE SEQUENCE historicoNF_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE historicoNF_id_seq
  OWNER TO postgres;

DROP TABLE historicoNF;
CREATE TABLE historicoNF(
	id             integer NOT NULL DEFAULT nextval('historicoNF_id_seq'::regclass),
	notaId         integer  NULL,
	data           bigint,
	notaTypeEnum   integer NOT NULL,
    processId      integer  NULL,
	create_date    bigint,
    create_user  	 character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT historicoNF_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historicoNF
  OWNER TO postgres;

/*-----------------------------------------------------------*/
DROP SEQUENCE tela_id_seq;

CREATE SEQUENCE tela_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tela_id_seq
  OWNER TO postgres;

DROP TABLE tela;
CREATE TABLE tela(
	id             integer NOT NULL DEFAULT nextval('tela_id_seq'::regclass),
	type           integer  NULL,
	description    character varying(250) NULL,
	help           character varying(250) NULL,
	data   		 bigint,
	desenvolvida   character varying(50) NULL,
    processId      integer  NULL,
	create_date    bigint,
    create_user  	 character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT tela_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tela
  OWNER TO postgres;

/*-------------------------------------------*/
DROP SEQUENCE tabs_id_seq;

CREATE SEQUENCE tabs_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tabs_id_seq
  OWNER TO postgres;

DROP TABLE tabs;
CREATE TABLE tabs(
	id             integer NOT NULL DEFAULT nextval('tabs_id_seq'::regclass),
	telaId         integer  NULL,
	text           character varying(250) NULL,
	description    character varying(250) NULL,
	nome   		 character varying(50) NULL,
    processId      integer  NULL,
	create_date    bigint,
    create_user  	 character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT tabs_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tabs
  OWNER TO postgres;

/*----------------------------------------*/
DROP SEQUENCE field_id_seq;

CREATE SEQUENCE field_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE field_id_seq
  OWNER TO postgres;

DROP TABLE field;
CREATE TABLE field(
	id             integer NOT NULL DEFAULT nextval('field_id_seq'::regclass),
	tabId          integer  NULL,
	type           integer  NULL,
	description    character varying(250) NULL,
	numero   		 character varying(50) NULL,
    nome      	 integer  NULL,
    leftt           integer  NULL,
	topp            integer  NULL,
	width          integer  NULL,
	height    	 integer  NULL,
	zIndex   		 integer  NULL,
    label      	 character varying(50) NULL,
    text           character varying(50) NULL,
	classe         character varying(50) NULL,
	mask           character varying(50) NULL,
	validation     character varying(250) NULL,
	size   		 integer  NULL,
    busca      	 integer  NULL,
    processId      integer  NULL,
	create_date    bigint,
    create_user  	 character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT field_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE field
  OWNER TO postgres;

/*----------------------------------------------------*/

DROP SEQUENCE fieldBusca_id_seq;

CREATE SEQUENCE fieldBusca_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE fieldBusca_id_seq
  OWNER TO postgres;

DROP TABLE fieldBusca;
CREATE TABLE fieldBusca(
	id             integer NOT NULL DEFAULT nextval('fieldBusca_id_seq'::regclass),
	fieldId        integer  NULL,
	tabela         integer  NULL,
	processId      integer  NULL,
	create_date    bigint,
    create_user  	 character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT fieldBusca_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE fieldBusca
  OWNER TO postgres;

/*-----------------------------------*/

DROP SEQUENCE plano_id_seq;

CREATE SEQUENCE plano_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE plano_id_seq
  OWNER TO postgres;

DROP TABLE plano;
CREATE TABLE plano(
	id             integer NOT NULL DEFAULT nextval('plano_id_seq'::regclass),
	emprId         integer  NULL,
	dataFinal      bigint,
	dataInicio     bigint,
	desconto       real  NULL,
	numeroContrato integer  NULL,
	processId      integer  NULL,
	descricao  	   character varying(250) NULL,
	titulo  	   character varying(100) NULL,
	create_date    bigint,
    create_user    character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT plano_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE plano
  OWNER TO postgres;

/*-----------------------------------*/
DROP SEQUENCE servico_id_seq;

CREATE SEQUENCE servico_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE servico_id_seq
  OWNER TO postgres;


DROP TABLE  servico;

CREATE TABLE servico(
    id           integer NOT NULL DEFAULT nextval('servico_id_seq'::regclass),
    nome         character varying(100) NULL,
    descricao    character varying(250) NULL,
    emprId     	 integer NOT NULL,
    status 	     integer NOT NULL,
	create_date  bigint,
    create_user  character varying(50) NULL,
    modify_date  bigint,
    modify_user  character varying(50) NULL,
CONSTRAINT servico_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE servico
  OWNER TO postgres;

/*-----------------------------------*/

DROP SEQUENCE planoServ_id_seq;

CREATE SEQUENCE planoServ_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE planoServ_id_seq
  OWNER TO postgres;

DROP TABLE planoServ;
CREATE TABLE planoServ(
	id             integer NOT NULL DEFAULT nextval('planoServ_id_seq'::regclass),
	servId         integer  NULL,
	planoId        integer  NULL,
	processId      integer  NULL,
	status         integer  NULL,
	create_date    bigint,
    create_user    character varying(50) NULL,
    modify_date    bigint,
    modify_user    character varying(50) NULL,
CONSTRAINT planoServ_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE planoServ
  OWNER TO postgres;


   DROP TABLE horarios;

CREATE TABLE horarios
(
  id integer NOT NULL DEFAULT nextval('horarios_id_seq'::regclass),
  parentid integer,
  data bigint,
  horarioentr bigint,
  horariosair bigint,
  create_date bigint,
  tipo character varying(50),
  processid integer,
  create_user character varying(50),
  modify_date bigint,
  modify_user character varying(50),
  CONSTRAINT horarios_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE horarios
  OWNER TO postgres;
 DROP TABLE historico;

CREATE TABLE historico
(
  id integer NOT NULL DEFAULT nextval('historico_id_seq'::regclass),
  data bigint,
  userid character varying(20),
  emprid integer,
  tabela integer NOT NULL,
  acao integer NOT NULL,
  create_date bigint,
  create_user character varying(50),
  modify_date bigint,
  modify_user character varying(50),
  CONSTRAINT pk_historico_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historico
  OWNER TO postgres;

-- Table: historicoitens

 DROP TABLE historicoitens;

CREATE TABLE historicoitens
(
  id integer NOT NULL DEFAULT nextval('historicoitens_id_seq'::regclass),
  idhist integer NOT NULL,
  processid integer NOT NULL,
  type integer NOT NULL,
  tabela integer NOT NULL,
  parentid integer NOT NULL,
  data bigint,
  create_date bigint,
  create_user character varying(50),
  modify_date bigint,
  modify_user character varying(50),
  CONSTRAINT historicoitens_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historicoitens
  OWNER TO postgres;
-- Table: tabpreco

 DROP TABLE tabpreco;

CREATE TABLE tabpreco
(
  id integer NOT NULL DEFAULT nextval('tabpreco_id_seq'::regclass),
  prodid integer NOT NULL,
  emprid integer ,
  entidadeid integer,
  precotypeenum integer NOT NULL,
  datamarcacao bigint,
  valor real NOT NULL,
  dataproinicial bigint,
  dataprofinal bigint,
  processid integer,
  create_date bigint,
  create_user character varying(50),
  modify_date bigint,
  modify_user character varying(50),
  CONSTRAINT tabpreco_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tabpreco
  OWNER TO postgres;


  DROP SEQUENCE imagem_id_seq;

CREATE SEQUENCE imagem_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE imagem_id_seq
  OWNER TO postgres;

 DROP TABLE imagem;

CREATE TABLE imagem
(
	id integer NOT NULL DEFAULT nextval('imagem_id_seq'::regclass),
	local character varying(50),
	tabelaEnum integer,
	nome character varying(50),
	fotoId integer,
	principal integer,
	emprId integer,
	parentId integer,
	processId integer,
	create_user character varying(50),
	create_date bigint,
	modify_user bigint,
	modify_date character varying(50),
  CONSTRAINT imagem_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE imagem
  OWNER TO postgres;

DROP SEQUENCE planobyservico_id_seq;

CREATE SEQUENCE planobyservico_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE planobyservico_id_seq
  OWNER TO postgres;

 DROP TABLE planobyservico;

CREATE TABLE planobyservico
(
	id integer NOT NULL DEFAULT nextval('planobyservico_id_seq'::regclass),
	planoId integer,
	servicoId integer,
	processId integer,
	create_user character varying(50),
	create_date bigint,
	modify_user bigint,
	modify_date character varying(50),
  CONSTRAINT planobyservico_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE planobyservico
  OWNER TO postgres;


 DROP TABLE servico;

CREATE TABLE servico
(
  id integer NOT NULL DEFAULT nextval('servico_id_seq'::regclass),
  nome character varying(100),
  descricao character varying(250),
  emprid integer ,
  status integer ,
  create_date bigint,
  create_user character varying(50),
  modify_date bigint,
  modify_user character varying(50),
  CONSTRAINT servico_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE servico
  OWNER TO postgres;
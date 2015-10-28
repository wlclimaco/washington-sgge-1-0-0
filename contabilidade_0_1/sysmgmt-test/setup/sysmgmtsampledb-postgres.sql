-- Table: cadastro

-- DROP TABLE cadastro;

CREATE TABLE cadastro
(
  cadastroid integer NOT NULL DEFAULT nextval('cadastro_id_seq'::regclass),
  controleid integer,
  descricao character varying(250),
  nome character varying(100),
  acao integer,
  CONSTRAINT cadastro_pkey PRIMARY KEY (cadastroid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cadastro
  OWNER TO postgres;

  -- Table: cidade

-- DROP TABLE cidade;

CREATE TABLE cidade
(
  id serial NOT NULL,
  estado character varying(10) NOT NULL,
  cidade character varying(50),
  versn_lock_num integer,
  controleid integer,
  CONSTRAINT cidade_pkey PRIMARY KEY (id),
  CONSTRAINT cidade_estado_key UNIQUE (estado)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cidade
  OWNER TO postgres;

  -- Table: cliente

-- DROP TABLE cliente;

CREATE TABLE cliente
(
  clienteid serial NOT NULL,
  nome character varying(250),
  email character varying(250),
  controleid integer,
  CONSTRAINT cliente_pkey PRIMARY KEY (clienteid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente
  OWNER TO postgres;

  -- Table: controle

-- DROP TABLE controle;

CREATE TABLE controle
(
  controleid serial NOT NULL,
  data date,
  usuarioid integer,
  acao integer,
  idacao integer,
  tabletype integer,
  CONSTRAINT controle_pkey PRIMARY KEY (controleid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE controle
  OWNER TO postgres;

  -- Table: documentos

-- DROP TABLE documentos;

CREATE TABLE documentos
(
  documentoid serial NOT NULL,
  id integer,
  rginscmuni character varying(250),
  cpfcnpj character varying(250),
  nome character varying(250),
  razao character varying(250),
  controleid integer,
  tabela integer,
  CONSTRAINT documentos_pkey PRIMARY KEY (documentoid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE documentos
  OWNER TO postgres;

  -- Table: endereco

-- DROP TABLE endereco;

CREATE TABLE endereco
(
  enderecoid serial NOT NULL,
  id integer,
  logradouro character varying(250),
  bairro character varying(250),
  estado character varying(250),
  cidade character varying(250),
  numero character varying(250),
  cep character varying(250),
  nome character varying(250),
  complemento character varying(250),
  acao integer,
  CONSTRAINT endereco_pkey PRIMARY KEY (enderecoid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE endereco
  OWNER TO postgres;

  -- Table: produto

-- DROP TABLE produto;

CREATE TABLE produto
(
  produtoid serial NOT NULL,
  codbarra integer,
  nome character varying(250),
  marca integer,
  menu integer,
  submenu integer,
  trimenu integer,
  unimed integer,
  precoid integer,
  controleid integer,
  foto integer,
  supermercadoid integer,
  CONSTRAINT produto_pkey PRIMARY KEY (produtoid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;

  -- Table: supermercado

-- DROP TABLE supermercado;

CREATE TABLE supermercado
(
  supermercadoid serial NOT NULL,
  usuario character varying(50),
  email character varying(250),
  site character varying(250),
  controleid integer,
  senha character varying(100),
  CONSTRAINT supermercado_pkey PRIMARY KEY (supermercadoid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE supermercado
  OWNER TO postgres;

  -- Table: tabpreco

-- DROP TABLE tabpreco;

CREATE TABLE tabpreco
(
  precoid serial NOT NULL,
  produtoid integer,
  supermercadoid integer,
  data date,
  preco numeric(11,2),
  precopromo numeric(11,2),
  promocao integer,
  dataini date,
  datafin date,
  controleid integer,
  CONSTRAINT tabpreco_pkey PRIMARY KEY (precoid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tabpreco
  OWNER TO postgres;

  -- Function: ins_cadastro(character varying, character varying, integer)

-- DROP FUNCTION ins_cadastro(character varying, character varying, integer);

CREATE OR REPLACE FUNCTION ins_cadastro(p_descricao character varying, p_nome character varying, p_acao integer)
  RETURNS integer AS
$BODY$
DECLARE
                id int;
BEGIN
        INSERT INTO cadastro(descricao, nome, acao)
        VALUES
(
        p_descricao,
        p_nome,
        p_acao ) RETURNING cadastroid INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_cadastro(character varying, character varying, integer)
  OWNER TO qat;

  -- Function: ins_produto(integer, character varying, character varying, integer, integer, integer, integer, integer, integer, integer, character varying, integer, numeric, integer)

-- DROP FUNCTION ins_produto(integer, character varying, character varying, integer, integer, integer, integer, integer, integer, integer, character varying, integer, numeric, integer);

CREATE OR REPLACE FUNCTION ins_produto(produtoid integer, p_codbarra character varying, p_nome character varying, p_marca integer, p_menu integer, p_submenu integer, p_trimenu integer, p_unimed integer, p_precoid integer, p_controleid integer, p_foto character varying, p_supermercadoid integer, p_preco numeric, p_usuarioid integer)
  RETURNS integer AS
$BODY$
DECLARE
                id int;
preco_id int;
BEGIN

INSERT INTO produto(
            codbarra, nome, marca, menu, submenu, trimenu, unimed,
            precoid, controleid, foto, supermercadoid)
    VALUES (p_codbarra, p_nome, p_marca, p_menu, p_submenu, p_trimenu, p_unimed,
            p_precoid, p_controleid, p_foto, p_supermercadoid)RETURNING produtoid INTO id;

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,id,2);

INSERT INTO tabpreco(produtoid, supermercadoid, data, preco)
    VALUES (id,p_supermercadoid,(SELECT CURRENT_TIMESTAMP),p_preco)RETURNING precoid INTO preco_id;
INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,preco_id,12);

UPDATE produto
   SET precoid=preco_id
 WHERE produtoid = id;


RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_produto(integer, character varying, character varying, integer, integer, integer, integer, integer, integer, integer, character varying, integer, numeric, integer)
  OWNER TO qat;

  -- Function: ins_supermercado(character varying, character varying, character varying, character varying)

-- DROP FUNCTION ins_supermercado(character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION ins_supermercado(p_usuario character varying, p_email character varying, p_site character varying, p_senha character varying)
  RETURNS integer AS
$BODY$
DECLARE
                id int;
BEGIN
        INSERT INTO supermercado (usuario, email, site, senha)
        VALUES
(
        p_usuario,
        p_email,
        p_site,
        p_senha ) RETURNING supermercadoid INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_supermercado(character varying, character varying, character varying, character varying)
  OWNER TO qat;

  -- Function: ins_supermercado(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying)

-- DROP FUNCTION ins_supermercado(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION ins_supermercado(p_usuario character varying, p_email character varying, p_site character varying, p_senha character varying, p_logradouro character varying, p_bairro character varying, p_estado character varying, p_cidade character varying, p_numero character varying, p_cep character varying, p_nome character varying, p_complemento character varying, p_usuarioid integer, p_rginscmuni character varying, p_cpfcnpj character varying, p_razao character varying)
  RETURNS integer AS
$BODY$
DECLARE
                supermercado_id int;
		endereco_id int;
		documento_id int;
BEGIN
        INSERT INTO supermercado (usuario, email, site, senha)
        VALUES(p_usuario, p_email, p_site, p_senha ) RETURNING supermercadoid INTO supermercado_id;

INSERT INTO endereco(id, logradouro, bairro, estado, cidade, numero, cep, nome, complemento, acao)
    VALUES          (supermercado_id, p_logradouro, p_bairro, p_estado, p_cidade, p_numero, p_cep, p_nome, p_complemento, 1) RETURNING enderecoid INTO endereco_id;

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,endereco_id,10);

INSERT INTO documentos(id, rginscmuni, cpfcnpj, nome, razao, tabela)
    VALUES (supermercado_id, p_rginscmuni, p_cpfcnpj, p_nome, p_razao, 1)RETURNING documentoid INTO documento_id;

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,documento_id,11);

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,supermercado_id,1);


RETURN supermercado_id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_supermercado(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying)
  OWNER TO qat;

  -- Function: upd_supermercado(integer, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying)

-- DROP FUNCTION upd_supermercado(integer, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION upd_supermercado(p_supermercadoid integer, p_usuario character varying, p_email character varying, p_site character varying, p_senha character varying, p_enderecoid integer, p_id integer, p_logradouro character varying, p_bairro character varying, p_estado character varying, p_cidade character varying, p_numero character varying, p_cep character varying, p_nome character varying, p_complemento character varying, p_documentoid integer, p_usuarioid integer, p_rginscmuni character varying, p_cpfcnpj character varying, p_razao character varying)
  RETURNS integer AS
$BODY$
DECLARE
                supermercado_id int;
		endereco_id int;
		documento_id int;
BEGIN
       UPDATE supermercado
	   SET usuario=p_usuario, email=p_email, site=p_site,senha=p_senha
	 WHERE supermercadoid = p_supermercadoid;
INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,2,p_supermercadoid,1);

UPDATE endereco
   SET id=p_id, logradouro=p_logradouro, bairro=p_bairro, estado=p_estado, cidade=p_cidade,
       numero=p_numero, cep=p_cep, nome=p_nome, complemento=p_complemento, acao=1
 WHERE enderecoid = p_enderecoid;

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,2,p_enderecoid,10);


UPDATE documentos
   SET rginscmuni=p_rginscmuni, cpfcnpj=p_cpfcnpj, nome=p_nome, razao=p_razao
 WHERE documentoid = p_documentoid;

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,2,p_documentoid,11);



RETURN p_supermercadoid;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION upd_supermercado(integer, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying)
  OWNER TO postgres;
-- Sequence: cadastro_id_seq

-- DROP SEQUENCE cadastro_id_seq;

CREATE SEQUENCE cadastro_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;
ALTER TABLE cadastro_id_seq
  OWNER TO postgres;
-- Sequence: cidade_id_seq

-- DROP SEQUENCE cidade_id_seq;

CREATE SEQUENCE cidade_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cidade_id_seq
  OWNER TO postgres;
-- Sequence: cliente_clienteid_seq

-- DROP SEQUENCE cliente_clienteid_seq;

CREATE SEQUENCE cliente_clienteid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE cliente_clienteid_seq
  OWNER TO postgres;
-- Sequence: controle_controleid_seq

-- DROP SEQUENCE controle_controleid_seq;

CREATE SEQUENCE controle_controleid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 38
  CACHE 1;
ALTER TABLE controle_controleid_seq
  OWNER TO postgres;
-- Sequence: documentos_documentoid_seq

-- DROP SEQUENCE documentos_documentoid_seq;

CREATE SEQUENCE documentos_documentoid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE documentos_documentoid_seq
  OWNER TO postgres;
-- Sequence: endereco_enderecoid_seq

-- DROP SEQUENCE endereco_enderecoid_seq;

CREATE SEQUENCE endereco_enderecoid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 29
  CACHE 1;
ALTER TABLE endereco_enderecoid_seq
  OWNER TO postgres;
-- Sequence: produto_produtoid_seq

-- DROP SEQUENCE produto_produtoid_seq;

CREATE SEQUENCE produto_produtoid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE produto_produtoid_seq
  OWNER TO postgres;
-- Sequence: supermercado_supermercadoid_seq

-- DROP SEQUENCE supermercado_supermercadoid_seq;

CREATE SEQUENCE supermercado_supermercadoid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 48
  CACHE 1;
ALTER TABLE supermercado_supermercadoid_seq
  OWNER TO postgres;
-- Sequence: tabpreco_precoid_seq

-- DROP SEQUENCE tabpreco_precoid_seq;

CREATE SEQUENCE tabpreco_precoid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE tabpreco_precoid_seq
  OWNER TO postgres;


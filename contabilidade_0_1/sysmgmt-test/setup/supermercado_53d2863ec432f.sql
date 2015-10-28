--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'LATIN1';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: del_cadastro(integer, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION del_cadastro(p_id integer, p_acao integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        DELETE FROM cadastro
		WHERE cadastroid = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,p_acao);


RETURN p_id;
END
$$;


--
-- Name: del_cidade(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION del_cidade(p_id integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        DELETE FROM cidade
		WHERE id = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,13);

RETURN p_id;
END
$$;


--
-- Name: del_embalagem(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION del_embalagem(p_id integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        DELETE FROM embalagem
		WHERE id = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,14);


RETURN p_id;
END
$$;


--
-- Name: del_produto(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION del_produto(p_id integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        DELETE FROM produto
		WHERE produtoid = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,2);

	DELETE FROM tabpreco
		WHERE produtoid = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,12);


RETURN p_id;
END
$$;


--
-- Name: del_supermercado(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION del_supermercado(p_id integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        DELETE FROM supermercado
		WHERE supermercadoid = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,1);

	DELETE FROM endereco
		WHERE id = p_id and acao = 1;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,10);

	DELETE FROM endereco
		WHERE id = p_id and acao = 1;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,11);

RETURN p_id;
END
$$;


--
-- Name: del_unimed(integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION del_unimed(p_id integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        DELETE FROM unimed
		WHERE id = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,3,p_id,7);


RETURN p_id;
END
$$;


--
-- Name: ins_cadastro(character varying, character varying, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_cadastro(p_descricao character varying, p_nome character varying, p_acao integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                id int;
BEGIN
        INSERT INTO cadastro(descricao, nome, acao)
        VALUES
(
        p_descricao,
        p_nome,
        p_acao ) RETURNING cadastroid INTO id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,id,p_acao);

RETURN id;
END
$$;


--
-- Name: ins_cidade(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_cidade(p_cidade character varying, p_estado character varying, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
        
	INSERT INTO cidade(estado, cidade, versn_lock_num, controleid)
	VALUES (p_estado,p_cidade,1,1) RETURNING id INTO l_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,l_id,13);

RETURN l_id;
END
$$;


--
-- Name: ins_cliente(integer, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_cliente(p_type integer, p_nome character varying, p_sobrenome character varying, p_usuario character varying, p_senha character varying, p_email character varying, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
        
	INSERT INTO cliente(type, nome, sobrenome, usuario, senha, email)
	VALUES (p_type,p_nome, p_sobrenome,p_usuario,p_senha, p_email) RETURNING clienteid INTO l_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,l_id,8);

RETURN l_id;
END
$$;


--
-- Name: ins_documento(integer, character varying, character varying, character varying, character varying, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_documento(p_id integer, p_rginscmuni character varying, p_cpfcnpj character varying, p_nome character varying, p_razao character varying, p_tabela integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
        
	INSERT INTO documentos(id, rginscmuni, cpfcnpj, nome, razao,tabela)
	VALUES (p_id, p_rginscmuni, p_cpfcnpj,p_nome,p_razao,p_tabela) RETURNING id INTO l_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,l_id,11);

RETURN l_id;
END
$$;


--
-- Name: ins_embalagem(character varying, integer, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_embalagem(p_nome character varying, p_qnt integer, p_unimedid integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN


	INSERT INTO embalagem(nome, qnt, unimedid)
	VALUES (p_nome,p_qnt, p_unimedid) RETURNING id INTO l_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,l_id,14);

RETURN l_id;
END
$$;


--
-- Name: ins_endereco(integer, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_endereco(p_id integer, p_logradouro character varying, p_bairro character varying, p_estado character varying, p_cidade character varying, p_numero integer, p_cep character varying, p_nome character varying, p_complemento character varying, p_acao integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
        
	INSERT INTO endereco(id, logradouro, bairro, estado, cidade, numero, cep, nome, complemento, acao)
	VALUES (p_id, p_logradouro, p_bairro, p_estado, p_cidade, p_numero, p_cep, p_nome, p_complemento, p_acao) RETURNING id INTO l_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,l_id,10);

RETURN l_id;
END
$$;


--
-- Name: ins_preco(integer, integer, timestamp without time zone, double precision, double precision, integer, timestamp without time zone, timestamp without time zone, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_preco(p_produtoid integer, p_supermercadoid integer, p_data timestamp without time zone, p_preco double precision, p_precopromo double precision, p_promocao integer, p_dataini timestamp without time zone, p_datafin timestamp without time zone, p_usuarioid character varying, _status character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
                l_id int;
preco_id int;
BEGIN

INSERT INTO tabpreco(produtoid, supermercadoid, data, preco, precopromo, promocao, dataini, datafin, controleid,status)
    VALUES (p_produtoid,p_supermercadoid,(SELECT CURRENT_TIMESTAMP), p_preco, p_precopromo,  p_promocao, p_dataini,p_datafin, 1,_status)RETURNING precoid INTO l_id  ;      

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,l_id,12);


RETURN l_id;
END
$$;


--
-- Name: ins_produto(integer, character varying, character varying, integer, integer, integer, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_produto(p_produtoid integer, p_codbarra character varying, p_nome character varying, p_marca integer, p_menu integer, p_unimed integer, p_foto character varying, p_descricao character varying, p_usuarioid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
                l_id int;
preco_id int;
BEGIN

INSERT INTO produto(
            codbarra, nome, marca, menu, unimed, foto, descricao)
    VALUES (p_codbarra, p_nome, p_marca, p_menu, p_unimed, 
              p_foto, p_descricao)RETURNING produtoid INTO l_id  ;      

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,l_id,2);


RETURN l_id;
END
$$;


--
-- Name: ins_supermercado(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_supermercado(p_usuario character varying, p_email character varying, p_site character varying, p_senha character varying, p_logradouro character varying, p_bairro character varying, p_estado character varying, p_cidade character varying, p_numero integer, p_cep character varying, p_nome character varying, p_complemento character varying, p_usuarioid character varying, p_rginscmuni character varying, p_cpfcnpj character varying, p_razao character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
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
$$;


--
-- Name: ins_unimed(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION ins_unimed(p_sigla character varying, p_nome character varying, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
                l_id int;
BEGIN

	INSERT INTO unimed (sigla, nome)
	VALUES (p_sigla,p_nome) RETURNING id INTO l_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,1,l_id,7);

RETURN l_id;
END
$$;


--
-- Name: upd_cadastro(integer, character varying, character varying, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_cadastro(p_id integer, p_descricao character varying, p_nome character varying, p_acao integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN

	UPDATE cadastro
	 SET descricao=p_descricao, nome=p_nome
	WHERE cadastroid = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_id,p_acao);

RETURN p_id;
END
$$;


--
-- Name: upd_cidade(integer, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_cidade(p_id integer, p_cidade character varying, p_estado character varying, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
        UPDATE cidade
	    SET estado=p_estado, cidade=p_cidade, versn_lock_num=2, controleid=2
	 WHERE id = p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_id,13);

RETURN p_id;
END
$$;


--
-- Name: upd_cliente(integer, integer, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_cliente(p_clienteid integer, p_type integer, p_nome character varying, p_sobrenome character varying, p_usuario character varying, p_senha character varying, p_email character varying, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
        
	UPDATE cliente
	   SET type=p_type, nome=p_nome, sobrenome=p_sobrenome, usuario=p_usuario, senha=p_senha, 
	       email=p_email
	 WHERE clienteid = p_clienteid;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_clienteid,8);

RETURN l_id;
END
$$;


--
-- Name: upd_documento(integer, integer, character varying, character varying, character varying, character varying, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_documento(p_documentoid integer, p_id integer, p_rginscmuni character varying, p_cpfcnpj character varying, p_nome character varying, p_razao character varying, p_tabela integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
	UPDATE documentos
	SET  id=p_id, rginscmuni=p_rginscmuni, cpfcnpj=p_cpfcnpj, nome=p_nome, razao=p_razao, controleid=1, tabela=p_tabela
	WHERE documentoid = p_documentoid;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_documentoid,11);

RETURN l_id;
END
$$;


--
-- Name: upd_embalagem(integer, character varying, integer, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_embalagem(p_id integer, p_nome character varying, p_qnt integer, p_unimedid integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
	UPDATE embalagem
	   SET nome=p_nome, qnt=p_qnt, unimedid=p_unimedid
	WHERE id=p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_id,14);

RETURN p_id;
END
$$;


--
-- Name: upd_endereco(integer, integer, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying, integer, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_endereco(p_enderecoid integer, p_id integer, p_logradouro character varying, p_bairro character varying, p_estado character varying, p_cidade character varying, p_numero integer, p_cep character varying, p_nome character varying, p_complemento character varying, p_acao integer, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
BEGIN
        UPDATE endereco
	   SET id=p_id, logradouro=p_logradouro, bairro=p_bairro, estado=p_estado, cidade=p_cidade, 
	       numero=p_numero, cep=p_cep, nome=p_nome, complemento=p_complemento, acao=p_acao
	 WHERE enderecoid=p_enderecoid;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_enderecoid,10);

RETURN l_id;
END
$$;


--
-- Name: upd_preco(integer, integer, integer, double precision, double precision, integer, timestamp without time zone, timestamp without time zone, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_preco(p_id integer, p_produtoid integer, p_supermercadoid integer, p_preco double precision, p_precopromo double precision, p_promocao integer, p_dataini timestamp without time zone, p_datafin timestamp without time zone, p_usuarioid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
                l_id int;
preco_id int;
BEGIN

	UPDATE tabpreco
	SET  produtoid=p_produtoid, supermercadoid=p_supermercadoid, data=(SELECT CURRENT_TIMESTAMP), preco=p_preco, precopromo=p_precopromo, 
	       promocao=p_promocao, dataini=p_dataini, datafin=p_datafin
	WHERE precoid=p_id;   

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,2,p_id,12);


RETURN l_id;
END
$$;


--
-- Name: upd_produto(integer, character varying, character varying, integer, integer, integer, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_produto(p_produtoid integer, p_codbarra character varying, p_nome character varying, p_marca integer, p_menu integer, p_unimed integer, p_foto character varying, p_descricao character varying, p_usuarioid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$DECLARE
                l_id int;
preco_id int;
BEGIN

	UPDATE produto
	SET  nome=p_nome, marca=p_marca, menu=p_menu,  unimed=p_unimed, 
	     descricao=p_descricao, codbarra=p_codbarra, foto=p_foto
	WHERE produtoid = p_produtoid ;

     

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,2,p_produtoid,2);


RETURN l_id;
END
$$;


--
-- Name: upd_produto(integer, character varying, character varying, integer, integer, integer, integer, integer, integer, integer, character varying, integer, double precision, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_produto(p_produtoid integer, p_codbarra character varying, p_nome character varying, p_marca integer, p_menu integer, p_submenu integer, p_trimenu integer, p_unimed integer, p_precoid integer, p_controleid integer, p_foto character varying, p_supermercadoid integer, p_preco double precision, p_usuarioid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
                l_id int;
preco_id int;
BEGIN

UPDATE produto
   SET codbarra=p_codbarra, nome=p_nome, marca=p_marca, menu=p_menu, submenu=p_submenu, 
       trimenu=p_trimenu, unimed=p_unimed,  foto=p_foto, supermercadoid=p_supermercadoid
 WHERE produtoid = p_produtoid;    

INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,2,p_produtoid,2);

INSERT INTO tabpreco(produtoid, supermercadoid, data, preco)
    VALUES (p_produtoid,p_supermercadoid,(SELECT CURRENT_TIMESTAMP),p_preco)RETURNING precoid INTO preco_id;  
INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_usuarioid,1,preco_id,12);

UPDATE produto
   SET precoid=preco_id
 WHERE produtoid = p_produtoid;


RETURN p_produtoid;
END
$$;


--
-- Name: upd_supermercado(integer, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_supermercado(p_supermercadoid integer, p_usuario character varying, p_email character varying, p_site character varying, p_senha character varying, p_enderecoid integer, p_id integer, p_logradouro character varying, p_bairro character varying, p_estado character varying, p_cidade character varying, p_numero character varying, p_cep character varying, p_nome character varying, p_complemento character varying, p_documentoid integer, p_usuarioid character varying, p_rginscmuni character varying, p_cpfcnpj character varying, p_razao character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
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
$$;


--
-- Name: upd_unimed(integer, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION upd_unimed(p_id integer, p_sigla character varying, p_nome character varying, p_userid character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE

BEGIN
	UPDATE embalagem
	   SET nome=p_nome, qnt=p_qnt, unimedid=p_unimedid
	WHERE id=p_id;
	UPDATE unimed
	   SET sigla=p_sigla, nome=p_nome
	WHERE id=p_id;

	INSERT INTO controle(data, usuarioid, acao, idacao, tabletype)VALUES ((SELECT CURRENT_TIMESTAMP),p_userId,2,p_id,7);

RETURN p_id;
END
$$;


--
-- Name: cadastro_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cadastro_id_seq
    START WITH 23
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cadastro; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE cadastro (
    cadastroid integer DEFAULT nextval('cadastro_id_seq'::regclass) NOT NULL,
    controleid integer,
    descricao character varying(250),
    nome character varying(100),
    acao integer
);


--
-- Name: cidade; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE cidade (
    id integer NOT NULL,
    estado character varying(10) NOT NULL,
    cidade character varying(50),
    versn_lock_num integer,
    controleid integer
);


--
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cidade_id_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cidade_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cidade_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE cidade_id_seq1 OWNED BY cidade.id;


--
-- Name: cliente_clienteid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE cliente_clienteid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cliente; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE cliente (
    clienteid integer DEFAULT nextval('cliente_clienteid_seq'::regclass) NOT NULL,
    type integer,
    nome character varying(50),
    sobrenome character varying(50),
    usuario character varying(50),
    senha character varying(50),
    email character varying(50)
);


--
-- Name: controle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE controle (
    controleid integer NOT NULL,
    data timestamp without time zone,
    acao integer,
    idacao integer,
    tabletype integer,
    usuarioid character varying(200),
    tenantid integer
);


--
-- Name: controle_controleid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE controle_controleid_seq
    START WITH 93
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: controle_controleid_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE controle_controleid_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: controle_controleid_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE controle_controleid_seq1 OWNED BY controle.controleid;


--
-- Name: documentos; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE documentos (
    documentoid integer NOT NULL,
    id integer,
    rginscmuni character varying(250),
    cpfcnpj character varying(250),
    nome character varying(250),
    razao character varying(250),
    controleid integer,
    tabela integer
);


--
-- Name: documentos_documentoid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE documentos_documentoid_seq
    START WITH 16
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: documentos_documentoid_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE documentos_documentoid_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: documentos_documentoid_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE documentos_documentoid_seq1 OWNED BY documentos.documentoid;


--
-- Name: embalagem; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE embalagem (
    id integer NOT NULL,
    nome character varying(250),
    qnt integer,
    unimedid integer
);


--
-- Name: embalagem_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE embalagem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: embalagem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE embalagem_id_seq OWNED BY embalagem.id;


--
-- Name: endereco; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE endereco (
    enderecoid integer NOT NULL,
    id integer,
    logradouro character varying(250),
    bairro character varying(250),
    estado character varying(250),
    cidade character varying(250),
    cep character varying(250),
    nome character varying(250),
    complemento character varying(250),
    acao integer,
    numero integer
);


--
-- Name: endereco_enderecoid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE endereco_enderecoid_seq
    START WITH 30
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: endereco_enderecoid_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE endereco_enderecoid_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: endereco_enderecoid_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE endereco_enderecoid_seq1 OWNED BY endereco.enderecoid;


--
-- Name: procedure_proc_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE procedure_proc_id_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: produto; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE produto (
    produtoid integer NOT NULL,
    nome character varying(250),
    marca integer,
    menu integer,
    submenu integer,
    trimenu integer,
    unimed integer,
    precoid integer,
    controleid integer,
    supermercadoid integer,
    codbarra character varying(20),
    foto character varying(100),
    descricao character varying(200),
    embalagem integer
);


--
-- Name: produto_produtoid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE produto_produtoid_seq
    START WITH 22
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: produto_produtoid_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE produto_produtoid_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: produto_produtoid_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE produto_produtoid_seq1 OWNED BY produto.produtoid;


--
-- Name: supermercado; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE supermercado (
    supermercadoid integer NOT NULL,
    usuario character varying(50),
    email character varying(250),
    site character varying(250),
    controleid integer,
    senha character varying(100)
);


--
-- Name: supermercado_supermercadoid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE supermercado_supermercadoid_seq
    START WITH 49
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: supermercado_supermercadoid_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE supermercado_supermercadoid_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: supermercado_supermercadoid_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE supermercado_supermercadoid_seq1 OWNED BY supermercado.supermercadoid;


--
-- Name: tabpreco; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE tabpreco (
    precoid integer NOT NULL,
    produtoid integer,
    supermercadoid integer,
    data timestamp without time zone,
    preco numeric(11,2),
    precopromo numeric(11,2),
    promocao integer,
    dataini timestamp without time zone,
    datafin timestamp without time zone,
    controleid integer,
    status character varying(1)
);


--
-- Name: COLUMN tabpreco.data; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN tabpreco.data IS 'timestamp without time zone	';


--
-- Name: tabpreco_precoid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tabpreco_precoid_seq
    START WITH 26
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tabpreco_precoid_seq1; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tabpreco_precoid_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tabpreco_precoid_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tabpreco_precoid_seq1 OWNED BY tabpreco.precoid;


--
-- Name: thebundle_bundle_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE thebundle_bundle_id_seq
    START WITH 6
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: unimed; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE unimed (
    id integer NOT NULL,
    sigla character varying(2),
    nome character varying(100)
);


--
-- Name: unimed_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE unimed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: unimed_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE unimed_id_seq OWNED BY unimed.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY cidade ALTER COLUMN id SET DEFAULT nextval('cidade_id_seq1'::regclass);


--
-- Name: controleid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY controle ALTER COLUMN controleid SET DEFAULT nextval('controle_controleid_seq1'::regclass);


--
-- Name: documentoid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY documentos ALTER COLUMN documentoid SET DEFAULT nextval('documentos_documentoid_seq1'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY embalagem ALTER COLUMN id SET DEFAULT nextval('embalagem_id_seq'::regclass);


--
-- Name: enderecoid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY endereco ALTER COLUMN enderecoid SET DEFAULT nextval('endereco_enderecoid_seq1'::regclass);


--
-- Name: produtoid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY produto ALTER COLUMN produtoid SET DEFAULT nextval('produto_produtoid_seq1'::regclass);


--
-- Name: supermercadoid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY supermercado ALTER COLUMN supermercadoid SET DEFAULT nextval('supermercado_supermercadoid_seq1'::regclass);


--
-- Name: precoid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tabpreco ALTER COLUMN precoid SET DEFAULT nextval('tabpreco_precoid_seq1'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY unimed ALTER COLUMN id SET DEFAULT nextval('unimed_id_seq'::regclass);


--
-- Data for Name: cadastro; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO cadastro VALUES (26, NULL, '111111', 'Test', 2);
INSERT INTO cadastro VALUES (32, NULL, 'ddd', 'ddd', 2);
INSERT INTO cadastro VALUES (38, NULL, 'Ws', 'Was', 2);
INSERT INTO cadastro VALUES (25, NULL, '11111', 'Test', 1);
INSERT INTO cadastro VALUES (63, NULL, 'washingtons', 'Washingtons', 5);
INSERT INTO cadastro VALUES (31, NULL, 'ffff', 'ddd', 1);
INSERT INTO cadastro VALUES (37, NULL, 'rrr', 'rrr', 1);
INSERT INTO cadastro VALUES (39, NULL, '555', '555', 2);
INSERT INTO cadastro VALUES (40, NULL, '11', '11', 2);
INSERT INTO cadastro VALUES (43, NULL, '444', '4444', 5);
INSERT INTO cadastro VALUES (47, NULL, '1', '1', 4);
INSERT INTO cadastro VALUES (48, NULL, '2', '2', 4);
INSERT INTO cadastro VALUES (49, NULL, '3', '3', 4);
INSERT INTO cadastro VALUES (50, NULL, '4', '4', 4);
INSERT INTO cadastro VALUES (51, NULL, '5', '5', 4);
INSERT INTO cadastro VALUES (52, NULL, '1', '1', 4);
INSERT INTO cadastro VALUES (53, NULL, '9', '9', 4);
INSERT INTO cadastro VALUES (54, NULL, '55', '55', 4);
INSERT INTO cadastro VALUES (55, NULL, '55', '55', 4);
INSERT INTO cadastro VALUES (56, NULL, 'aaa', 'aaa', 4);
INSERT INTO cadastro VALUES (57, NULL, 'sss', 'sss', 4);
INSERT INTO cadastro VALUES (58, NULL, '4', '4', 4);
INSERT INTO cadastro VALUES (59, NULL, '1', '1', 4);
INSERT INTO cadastro VALUES (69, NULL, 'sss', 'sss', 4);
INSERT INTO cadastro VALUES (60, NULL, '8989', '898', 4);
INSERT INTO cadastro VALUES (71, NULL, 'uni', 'uni', 7);
INSERT INTO cadastro VALUES (72, NULL, 'COCA COLA', 'COCA COLA', 4);
INSERT INTO cadastro VALUES (73, NULL, 'ML', 'ml', 7);
INSERT INTO cadastro VALUES (74, NULL, 'TEST', 'TEtst', 4);
INSERT INTO cadastro VALUES (76, NULL, 'test', 'test', 4);
INSERT INTO cadastro VALUES (77, NULL, 'wwwww', 'wwww', 4);
INSERT INTO cadastro VALUES (28, NULL, 'ssss', 'sssss', 4);
INSERT INTO cadastro VALUES (34, NULL, '888', '888q', 4);
INSERT INTO cadastro VALUES (61, NULL, 'ssssss', 'ssss', 4);
INSERT INTO cadastro VALUES (62, NULL, 'ssss', 'sss', 4);
INSERT INTO cadastro VALUES (95, NULL, '555', '555', 3);
INSERT INTO cadastro VALUES (27, NULL, 'Test', 'testas', 3);
INSERT INTO cadastro VALUES (80, NULL, 'SSS', 'dddd', 3);
INSERT INTO cadastro VALUES (94, NULL, '5656', 'ddddd', 3);
INSERT INTO cadastro VALUES (104, NULL, '5555', '5555', 4);
INSERT INTO cadastro VALUES (75, NULL, 'test', 'D', 4);
INSERT INTO cadastro VALUES (105, NULL, '555', 'eeeee', 5);
INSERT INTO cadastro VALUES (106, NULL, '5555', 'TEtstdd', 5);
INSERT INTO cadastro VALUES (107, NULL, '5555', 'sssss', 5);
INSERT INTO cadastro VALUES (108, NULL, '5555', 'aaa', 5);
INSERT INTO cadastro VALUES (109, NULL, '555', 'errr', 5);
INSERT INTO cadastro VALUES (64, NULL, 'dddds', 'COCA COLA', 5);
INSERT INTO cadastro VALUES (117, NULL, 'Was', 'Washingtons', 5);
INSERT INTO cadastro VALUES (118, NULL, '555', 'Washingtons', 6);
INSERT INTO cadastro VALUES (119, NULL, '658', 'aaa', 6);


--
-- Name: cadastro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cadastro_id_seq', 121, true);


--
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO cidade VALUES (7, '111', '10', 2, 2);
INSERT INTO cidade VALUES (8, '5555', '555', 1, 1);
INSERT INTO cidade VALUES (10, '8888', '88', 1, 1);


--
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cidade_id_seq', 4, true);


--
-- Name: cidade_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cidade_id_seq1', 10, true);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: cliente_clienteid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('cliente_clienteid_seq', 17, true);


--
-- Data for Name: controle; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO controle VALUES (1, '2014-06-18 00:00:00', 1, 26, 2, 'rod', NULL);
INSERT INTO controle VALUES (2, '2014-06-24 00:00:00', 1, 27, 2, 'rod', NULL);
INSERT INTO controle VALUES (3, '2014-06-24 00:00:00', 1, 28, 2, 'rod', NULL);
INSERT INTO controle VALUES (4, '2014-06-24 00:00:00', 1, 29, 2, 'rod', NULL);
INSERT INTO controle VALUES (5, '2014-06-24 00:00:00', 1, 30, 2, 'rod', NULL);
INSERT INTO controle VALUES (6, '2014-06-24 00:00:00', 1, 31, 2, 'rod', NULL);
INSERT INTO controle VALUES (7, '2014-06-24 00:00:00', 1, 32, 2, 'rod', NULL);
INSERT INTO controle VALUES (8, '2014-06-24 00:00:00', 1, 33, 2, 'rod', NULL);
INSERT INTO controle VALUES (9, '2014-06-24 00:00:00', 1, 34, 2, 'rod', NULL);
INSERT INTO controle VALUES (10, '2014-06-24 00:00:00', 1, 35, 2, 'rod', NULL);
INSERT INTO controle VALUES (11, '2014-06-24 00:00:00', 1, 36, 2, 'rod', NULL);
INSERT INTO controle VALUES (12, '2014-06-24 00:00:00', 1, 37, 2, 'rod', NULL);
INSERT INTO controle VALUES (13, '2014-06-25 00:00:00', 1, 38, 2, 'rod', NULL);
INSERT INTO controle VALUES (14, '2014-06-25 00:00:00', 1, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (15, '2014-06-25 00:00:00', 1, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (16, '2014-06-25 00:00:00', 1, 5, 13, 'rod', NULL);
INSERT INTO controle VALUES (17, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (18, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (19, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (20, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (21, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (22, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (23, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (24, '2014-06-26 00:00:00', 2, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (25, '2014-06-26 00:00:00', 3, 3, 13, 'rod', NULL);
INSERT INTO controle VALUES (26, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (27, '2014-06-26 00:00:00', 1, 6, 13, 'rod', NULL);
INSERT INTO controle VALUES (28, '2014-06-26 00:00:00', 2, 5, 13, 'rod', NULL);
INSERT INTO controle VALUES (29, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (30, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (31, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (32, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (33, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (34, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (35, '2014-06-26 00:00:00', 2, 5, 13, 'rod', NULL);
INSERT INTO controle VALUES (36, '2014-06-26 00:00:00', 2, 6, 13, 'rod', NULL);
INSERT INTO controle VALUES (37, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (38, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (39, '2014-06-26 00:00:00', 2, 5, 13, 'rod', NULL);
INSERT INTO controle VALUES (40, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (41, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (42, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (43, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (44, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (45, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (46, '2014-06-26 00:00:00', 3, 6, 13, 'rod', NULL);
INSERT INTO controle VALUES (47, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (48, '2014-06-26 00:00:00', 2, 5, 13, 'rod', NULL);
INSERT INTO controle VALUES (49, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (50, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (51, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (52, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (53, '2014-06-26 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (54, '2014-06-27 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (55, '2014-06-27 00:00:00', 2, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (56, '2014-06-27 00:00:00', 3, 5, 13, 'rod', NULL);
INSERT INTO controle VALUES (57, '2014-06-27 00:00:00', 1, 7, 13, 'rod', NULL);
INSERT INTO controle VALUES (58, '2014-06-27 00:00:00', 1, 8, 13, 'rod', NULL);
INSERT INTO controle VALUES (59, '2014-06-27 00:00:00', 1, 10, 13, 'rod', NULL);
INSERT INTO controle VALUES (60, '2014-06-28 00:00:00', 1, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (61, '2014-06-28 00:00:00', 1, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (62, '2014-06-28 00:00:00', 1, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (63, '2014-06-28 00:00:00', 1, 2, 10, NULL, NULL);
INSERT INTO controle VALUES (64, '2014-06-28 00:00:00', 1, 2, 11, NULL, NULL);
INSERT INTO controle VALUES (65, '2014-06-28 00:00:00', 1, 2, 1, NULL, NULL);
INSERT INTO controle VALUES (66, '2014-06-28 00:00:00', 1, 3, 10, NULL, NULL);
INSERT INTO controle VALUES (67, '2014-06-28 00:00:00', 1, 3, 11, NULL, NULL);
INSERT INTO controle VALUES (68, '2014-06-28 00:00:00', 1, 3, 1, NULL, NULL);
INSERT INTO controle VALUES (69, '2014-06-28 00:00:00', 1, 4, 10, NULL, NULL);
INSERT INTO controle VALUES (70, '2014-06-28 00:00:00', 1, 4, 11, NULL, NULL);
INSERT INTO controle VALUES (71, '2014-06-28 00:00:00', 1, 4, 1, NULL, NULL);
INSERT INTO controle VALUES (72, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (73, '2014-06-28 00:00:00', 2, 6, 10, NULL, NULL);
INSERT INTO controle VALUES (74, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (75, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (76, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (77, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (78, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (79, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (80, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (81, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (82, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (83, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (84, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (85, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (86, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (87, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (88, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (89, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (90, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (91, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (92, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (93, '2014-06-28 00:00:00', 2, 1, 1, NULL, NULL);
INSERT INTO controle VALUES (94, '2014-06-28 00:00:00', 2, 1, 10, NULL, NULL);
INSERT INTO controle VALUES (95, '2014-06-28 00:00:00', 2, 1, 11, NULL, NULL);
INSERT INTO controle VALUES (96, '2014-06-28 00:00:00', 1, 5, 10, NULL, NULL);
INSERT INTO controle VALUES (97, '2014-06-28 00:00:00', 1, 5, 11, NULL, NULL);
INSERT INTO controle VALUES (98, '2014-06-28 00:00:00', 1, 5, 1, NULL, NULL);
INSERT INTO controle VALUES (99, '2014-06-28 00:00:00', 3, 1, 1, 'rod', NULL);
INSERT INTO controle VALUES (100, '2014-06-28 00:00:00', 3, 1, 10, 'rod', NULL);
INSERT INTO controle VALUES (101, '2014-06-28 00:00:00', 3, 1, 11, 'rod', NULL);
INSERT INTO controle VALUES (102, '2014-06-28 00:00:00', 3, 2, 1, 'rod', NULL);
INSERT INTO controle VALUES (103, '2014-06-28 00:00:00', 3, 2, 10, 'rod', NULL);
INSERT INTO controle VALUES (104, '2014-06-28 00:00:00', 3, 2, 11, 'rod', NULL);
INSERT INTO controle VALUES (105, '2014-06-28 00:00:00', 3, 4, 13, 'rod', NULL);
INSERT INTO controle VALUES (106, '2014-06-28 00:00:00', 2, 7, 13, 'rod', NULL);
INSERT INTO controle VALUES (107, '2014-06-28 00:00:00', 2, NULL, 1, NULL, NULL);
INSERT INTO controle VALUES (108, '2014-06-28 00:00:00', 2, 3, 10, NULL, NULL);
INSERT INTO controle VALUES (109, '2014-06-28 00:00:00', 2, 3, 11, NULL, NULL);
INSERT INTO controle VALUES (110, '2014-06-28 00:00:00', 3, 3, 1, 'rod', NULL);
INSERT INTO controle VALUES (111, '2014-06-28 00:00:00', 3, 3, 10, 'rod', NULL);
INSERT INTO controle VALUES (112, '2014-06-28 00:00:00', 3, 3, 11, 'rod', NULL);
INSERT INTO controle VALUES (202, '2014-06-30 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (203, '2014-06-30 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (204, '2014-06-30 00:00:00', 1, 39, 2, 'rod', NULL);
INSERT INTO controle VALUES (205, '2014-06-30 00:00:00', 1, 40, 2, 'rod', NULL);
INSERT INTO controle VALUES (206, '2014-06-30 00:00:00', 1, 41, 5, 'rod', NULL);
INSERT INTO controle VALUES (207, '2014-06-30 00:00:00', 1, 42, 5, 'rod', NULL);
INSERT INTO controle VALUES (208, '2014-06-30 00:00:00', 1, 43, 5, 'rod', NULL);
INSERT INTO controle VALUES (209, '2014-06-30 00:00:00', 1, 44, 6, 'rod', NULL);
INSERT INTO controle VALUES (210, '2014-06-30 00:00:00', 1, 45, 6, 'rod', NULL);
INSERT INTO controle VALUES (211, '2014-06-30 00:00:00', 1, 46, 4, 'rod', NULL);
INSERT INTO controle VALUES (212, '2014-06-30 00:00:00', 1, 47, 4, 'rod', NULL);
INSERT INTO controle VALUES (124, '2014-06-29 00:00:00', 1, 11, 8, 'rod', NULL);
INSERT INTO controle VALUES (125, '2014-06-29 00:00:00', 1, 11, 10, 'rod', NULL);
INSERT INTO controle VALUES (126, '2014-06-29 00:00:00', 1, 11, 11, 'rod', NULL);
INSERT INTO controle VALUES (127, '2014-06-29 00:00:00', 1, 12, 8, 'rod', NULL);
INSERT INTO controle VALUES (128, '2014-06-29 00:00:00', 1, 12, 10, 'rod', NULL);
INSERT INTO controle VALUES (129, '2014-06-29 00:00:00', 1, 12, 11, 'rod', NULL);
INSERT INTO controle VALUES (130, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (131, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (132, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (133, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (134, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (135, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (136, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (137, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (138, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (139, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (140, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (141, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (142, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (143, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (144, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (145, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (146, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (147, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (148, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (149, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (150, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (151, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (152, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (153, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (154, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (155, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (156, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (157, '2014-06-29 00:00:00', 1, 17, 8, 'rod', NULL);
INSERT INTO controle VALUES (158, '2014-06-29 00:00:00', 1, 17, 10, 'rod', NULL);
INSERT INTO controle VALUES (159, '2014-06-29 00:00:00', 1, 17, 11, 'rod', NULL);
INSERT INTO controle VALUES (160, '2014-06-29 00:00:00', 2, 17, 8, 'rod', NULL);
INSERT INTO controle VALUES (161, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (162, '2014-06-29 00:00:00', 2, 17, 8, 'rod', NULL);
INSERT INTO controle VALUES (163, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (164, '2014-06-29 00:00:00', 2, 17, 8, 'rod', NULL);
INSERT INTO controle VALUES (165, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (166, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (167, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (168, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (169, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (170, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (171, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (172, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (173, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (174, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (175, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (176, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (177, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (178, '2014-06-29 00:00:00', 2, 17, 8, 'rod', NULL);
INSERT INTO controle VALUES (179, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (180, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (181, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (182, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (183, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (184, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (185, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (186, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (187, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (188, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (189, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (190, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (191, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (192, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (193, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (194, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (195, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (196, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (197, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (198, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (199, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (200, '2014-06-29 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (201, '2014-06-29 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (213, '2014-07-01 00:00:00', 1, 48, 4, 'rod', NULL);
INSERT INTO controle VALUES (214, '2014-07-01 00:00:00', 1, 49, 4, 'rod', NULL);
INSERT INTO controle VALUES (215, '2014-07-01 00:00:00', 1, 50, 4, 'rod', NULL);
INSERT INTO controle VALUES (216, '2014-07-01 00:00:00', 1, 51, 4, 'rod', NULL);
INSERT INTO controle VALUES (217, '2014-07-01 00:00:00', 1, 52, 4, 'rod', NULL);
INSERT INTO controle VALUES (218, '2014-07-01 00:00:00', 1, 53, 4, 'rod', NULL);
INSERT INTO controle VALUES (219, '2014-07-01 00:00:00', 1, 54, 4, 'rod', NULL);
INSERT INTO controle VALUES (220, '2014-07-01 00:00:00', 1, 55, 4, 'rod', NULL);
INSERT INTO controle VALUES (221, '2014-07-01 00:00:00', 1, 56, 4, 'rod', NULL);
INSERT INTO controle VALUES (222, '2014-07-01 00:00:00', 1, 57, 4, 'rod', NULL);
INSERT INTO controle VALUES (223, '2014-07-01 00:00:00', 2, 1, 8, 'rod', NULL);
INSERT INTO controle VALUES (224, '2014-07-01 00:00:00', 1, NULL, 10, 'rod', NULL);
INSERT INTO controle VALUES (225, '2014-07-01 00:00:00', 1, 58, 4, 'rod', NULL);
INSERT INTO controle VALUES (226, '2014-07-01 00:00:00', 1, 59, 4, 'rod', NULL);
INSERT INTO controle VALUES (227, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (228, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (229, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (230, '2014-07-01 00:00:00', 1, 60, 4, 'rod', NULL);
INSERT INTO controle VALUES (231, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (232, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (233, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (234, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (235, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (236, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (237, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (238, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (239, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (240, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (241, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (242, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (243, '2014-07-01 00:00:00', 2, 28, 4, 'rod', NULL);
INSERT INTO controle VALUES (244, '2014-07-01 00:00:00', 2, 34, 4, 'rod', NULL);
INSERT INTO controle VALUES (245, '2014-07-02 00:00:00', 1, 61, 4, 'rod', NULL);
INSERT INTO controle VALUES (246, '2014-07-02 00:00:00', 1, 62, 4, 'rod', NULL);
INSERT INTO controle VALUES (247, '2014-07-04 00:00:00', 1, 63, 5, 'rod', NULL);
INSERT INTO controle VALUES (248, '2014-07-04 00:00:00', 1, 64, 5, 'rod', NULL);
INSERT INTO controle VALUES (249, '2014-07-04 00:00:00', 1, 65, 5, 'rod', NULL);
INSERT INTO controle VALUES (257, '2014-07-06 00:00:00', 3, 35, 5, 'rod', NULL);
INSERT INTO controle VALUES (258, '2014-07-06 00:00:00', 3, 41, 5, 'rod', NULL);
INSERT INTO controle VALUES (259, '2014-07-07 00:00:00', 1, 66, 5, 'rod', NULL);
INSERT INTO controle VALUES (260, '2014-07-07 00:00:00', 3, 66, 5, 'rod', NULL);
INSERT INTO controle VALUES (254, '2014-07-05 00:00:00', 3, 29, 5, 'rod', NULL);
INSERT INTO controle VALUES (255, '2014-07-05 00:00:00', 3, 29, 10, 'rod', NULL);
INSERT INTO controle VALUES (256, '2014-07-05 00:00:00', 3, 29, 11, 'rod', NULL);
INSERT INTO controle VALUES (261, '2014-07-07 00:00:00', 2, 63, 5, 'rod', NULL);
INSERT INTO controle VALUES (262, '2014-07-07 00:00:00', 2, 63, 5, 'rod', NULL);
INSERT INTO controle VALUES (263, '2014-07-07 00:00:00', 3, 30, 6, 'rod', NULL);
INSERT INTO controle VALUES (264, '2014-07-07 00:00:00', 1, 67, 6, 'rod', NULL);
INSERT INTO controle VALUES (265, '2014-07-07 20:53:20.882692', 1, 68, 6, 'rod', NULL);
INSERT INTO controle VALUES (266, '2014-07-07 20:54:38.239096', 2, 68, 6, 'rod', NULL);
INSERT INTO controle VALUES (267, '2014-07-07 21:02:36.977426', 1, 69, 4, 'rod', NULL);
INSERT INTO controle VALUES (268, '2014-07-07 21:08:42.720661', 1, 70, 7, 'rod', NULL);
INSERT INTO controle VALUES (269, '2014-07-07 21:09:18.785634', 1, 71, 7, 'rod', NULL);
INSERT INTO controle VALUES (270, '2014-07-07 21:27:52.482123', 3, 33, 4, 'rod', NULL);
INSERT INTO controle VALUES (271, '2014-07-07 21:29:52.808293', 1, 72, 4, 'rod', NULL);
INSERT INTO controle VALUES (272, '2014-07-07 21:32:59.50322', 1, 73, 7, 'rod', NULL);
INSERT INTO controle VALUES (273, '2014-07-07 21:34:47.832217', 1, 74, 4, 'rod', NULL);
INSERT INTO controle VALUES (274, '2014-07-07 21:35:24.634492', 1, 75, 4, 'rod', NULL);
INSERT INTO controle VALUES (275, '2014-07-07 21:37:58.79723', 1, 76, 4, 'rod', NULL);
INSERT INTO controle VALUES (276, '2014-07-07 21:38:58.691485', 1, 77, 4, 'rod', NULL);
INSERT INTO controle VALUES (277, '2014-07-07 21:40:14.161993', 1, 78, 4, 'rod', NULL);
INSERT INTO controle VALUES (278, '2014-07-07 21:41:55.417933', 1, 79, 3, 'rod', NULL);
INSERT INTO controle VALUES (279, '2014-07-07 21:45:14.441207', 1, 80, 3, 'rod', NULL);
INSERT INTO controle VALUES (280, '2014-07-07 22:12:10.349903', 3, 70, 7, 'rod', NULL);
INSERT INTO controle VALUES (281, '2014-07-07 23:31:13.153381', 3, 42, 5, 'rod', NULL);
INSERT INTO controle VALUES (282, '2014-07-07 23:46:53.717516', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (283, '2014-07-07 23:47:00.799055', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (284, '2014-07-08 13:56:19.199019', 3, 65, 5, 'rod', NULL);
INSERT INTO controle VALUES (285, '2014-07-15 14:52:21.827807', 1, 4, 2, 'rod', NULL);
INSERT INTO controle VALUES (305, '2014-07-16 11:42:34.494514', 1, 14, 2, 'rod', NULL);
INSERT INTO controle VALUES (306, '2014-07-16 11:42:34.494514', 1, 15, 12, 'rod', NULL);
INSERT INTO controle VALUES (307, '2014-07-16 11:48:07.284143', 1, 15, 2, 'rod', NULL);
INSERT INTO controle VALUES (308, '2014-07-16 11:48:07.284143', 1, 16, 12, 'rod', NULL);
INSERT INTO controle VALUES (309, '2014-07-16 11:48:07.284143', 1, 17, 12, 'rod', NULL);
INSERT INTO controle VALUES (310, '2014-07-16 14:14:49.993524', 1, 16, 2, 'rod', NULL);
INSERT INTO controle VALUES (311, '2014-07-16 14:14:49.993524', 1, 18, 12, 'rod', NULL);
INSERT INTO controle VALUES (293, '2014-07-15 15:56:19.256136', 1, 10, 2, 'rod', NULL);
INSERT INTO controle VALUES (294, '2014-07-15 15:56:19.256136', 1, 7, 12, 'rod', NULL);
INSERT INTO controle VALUES (295, '2014-07-15 15:56:19.256136', 1, 8, 12, 'rod', NULL);
INSERT INTO controle VALUES (296, '2014-07-15 15:57:26.788503', 1, 11, 2, 'rod', NULL);
INSERT INTO controle VALUES (297, '2014-07-15 15:57:26.788503', 1, 9, 12, 'rod', NULL);
INSERT INTO controle VALUES (298, '2014-07-15 15:57:26.788503', 1, 10, 12, 'rod', NULL);
INSERT INTO controle VALUES (299, '2014-07-15 17:04:02.707411', 1, 12, 2, 'rod', NULL);
INSERT INTO controle VALUES (300, '2014-07-15 17:04:02.707411', 1, 11, 12, 'rod', NULL);
INSERT INTO controle VALUES (301, '2014-07-15 17:04:02.707411', 1, 12, 12, 'rod', NULL);
INSERT INTO controle VALUES (302, '2014-07-15 22:32:03.330335', 1, 13, 2, 'rod', NULL);
INSERT INTO controle VALUES (303, '2014-07-15 22:32:03.330335', 1, 13, 12, 'rod', NULL);
INSERT INTO controle VALUES (304, '2014-07-15 22:32:03.330335', 1, 14, 12, 'rod', NULL);
INSERT INTO controle VALUES (312, '2014-07-16 14:25:11.36347', 1, 17, 2, 'rod', NULL);
INSERT INTO controle VALUES (313, '2014-07-16 14:25:11.36347', 1, 19, 12, 'rod', NULL);
INSERT INTO controle VALUES (345, '2014-07-17 13:45:47.644999', 2, 4, 2, 'rod', NULL);
INSERT INTO controle VALUES (315, '2014-07-16 21:40:11.746757', 1, 19, 2, 'rod', NULL);
INSERT INTO controle VALUES (316, '2014-07-16 21:40:11.746757', 1, 20, 12, 'rod', NULL);
INSERT INTO controle VALUES (317, '2014-07-16 21:40:11.746757', 1, 21, 12, 'rod', NULL);
INSERT INTO controle VALUES (318, '2014-07-16 21:40:11.746757', 1, 22, 12, 'rod', NULL);
INSERT INTO controle VALUES (319, '2014-07-16 21:54:52.500368', 1, 20, 2, 'rod', NULL);
INSERT INTO controle VALUES (320, '2014-07-16 21:54:52.500368', 1, 23, 12, 'rod', NULL);
INSERT INTO controle VALUES (321, '2014-07-16 21:54:52.500368', 1, 24, 12, 'rod', NULL);
INSERT INTO controle VALUES (322, '2014-07-16 22:08:08.07679', 1, 21, 2, 'rod', NULL);
INSERT INTO controle VALUES (323, '2014-07-16 22:08:08.07679', 1, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (324, '2014-07-16 22:08:08.07679', 1, 26, 12, 'rod', NULL);
INSERT INTO controle VALUES (325, '2014-07-16 22:23:46.625283', 1, 22, 2, 'rod', NULL);
INSERT INTO controle VALUES (326, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (327, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (328, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (329, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (330, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (331, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (332, '2014-07-16 22:23:46.625283', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (333, '2014-07-16 22:37:30.151127', 1, 23, 2, 'rod', NULL);
INSERT INTO controle VALUES (334, '2014-07-16 22:37:30.151127', 1, 27, 12, 'rod', NULL);
INSERT INTO controle VALUES (335, '2014-07-16 22:37:30.151127', 1, 28, 12, 'rod', NULL);
INSERT INTO controle VALUES (336, '2014-07-16 22:42:06.251575', 1, 24, 2, 'rod', NULL);
INSERT INTO controle VALUES (337, '2014-07-16 22:42:06.251575', 1, 29, 12, 'rod', NULL);
INSERT INTO controle VALUES (338, '2014-07-16 22:42:06.251575', 1, 30, 12, 'rod', NULL);
INSERT INTO controle VALUES (339, '2014-07-16 22:42:06.251575', 1, 31, 12, 'rod', NULL);
INSERT INTO controle VALUES (340, '2014-07-16 22:42:06.251575', 1, 32, 12, 'rod', NULL);
INSERT INTO controle VALUES (341, '2014-07-16 22:45:41.51273', 1, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (342, '2014-07-16 22:45:41.51273', 1, 33, 12, 'rod', NULL);
INSERT INTO controle VALUES (343, '2014-07-16 23:08:19.518346', 3, 1, 2, 'rod', NULL);
INSERT INTO controle VALUES (344, '2014-07-16 23:08:19.518346', 3, 1, 12, 'rod', NULL);
INSERT INTO controle VALUES (346, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (347, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (348, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (349, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (350, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (351, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (352, '2014-07-17 13:45:47.644999', 2, 4, 12, 'rod', NULL);
INSERT INTO controle VALUES (353, '2014-07-17 20:20:40.566697', 1, 45, 10, NULL, NULL);
INSERT INTO controle VALUES (354, '2014-07-17 20:20:40.566697', 1, 9, 11, NULL, NULL);
INSERT INTO controle VALUES (355, '2014-07-17 20:20:40.566697', 1, 6, 1, NULL, NULL);
INSERT INTO controle VALUES (356, '2014-07-17 20:21:50.245584', 1, 46, 10, NULL, NULL);
INSERT INTO controle VALUES (357, '2014-07-17 20:21:50.245584', 1, 10, 11, NULL, NULL);
INSERT INTO controle VALUES (358, '2014-07-17 20:21:50.245584', 1, 7, 1, NULL, NULL);
INSERT INTO controle VALUES (359, '2014-07-17 21:06:53.230485', 1, 47, 10, NULL, NULL);
INSERT INTO controle VALUES (360, '2014-07-17 21:06:53.230485', 1, 11, 11, NULL, NULL);
INSERT INTO controle VALUES (361, '2014-07-17 21:06:53.230485', 1, 8, 1, NULL, NULL);
INSERT INTO controle VALUES (362, '2014-07-18 10:59:50.829382', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (363, '2014-07-18 10:59:50.829382', 1, 34, 12, 'rod', NULL);
INSERT INTO controle VALUES (364, '2014-07-18 10:59:50.829382', 1, 35, 12, 'rod', NULL);
INSERT INTO controle VALUES (365, '2014-07-18 11:16:16.897641', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (366, '2014-07-18 11:16:16.897641', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (367, '2014-07-18 11:16:16.897641', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (368, '2014-07-18 11:19:26.269096', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (369, '2014-07-18 11:19:26.269096', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (370, '2014-07-18 11:19:26.269096', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (371, '2014-07-18 11:23:03.083589', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (372, '2014-07-18 11:23:03.083589', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (373, '2014-07-18 11:23:03.083589', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (374, '2014-07-18 11:24:36.267577', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (375, '2014-07-18 11:24:36.267577', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (376, '2014-07-18 11:24:36.267577', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (377, '2014-07-18 11:25:42.92967', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (378, '2014-07-18 11:25:42.92967', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (379, '2014-07-18 11:25:42.92967', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (380, '2014-07-18 11:38:35.312754', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (381, '2014-07-18 11:38:35.312754', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (382, '2014-07-18 11:38:35.312754', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (383, '2014-07-18 11:46:55.513582', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (384, '2014-07-18 11:46:55.513582', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (385, '2014-07-18 11:46:55.513582', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (386, '2014-07-18 12:00:58.246088', 2, 25, 2, 'rod', NULL);
INSERT INTO controle VALUES (387, '2014-07-18 12:00:58.246088', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (388, '2014-07-18 12:00:58.246088', 2, 25, 12, 'rod', NULL);
INSERT INTO controle VALUES (389, '2014-07-19 18:31:53.217943', 1, 36, 12, 'rod', NULL);
INSERT INTO controle VALUES (390, '2014-07-19 18:32:30.013545', 1, 37, 12, 'rod', NULL);
INSERT INTO controle VALUES (391, '2014-07-19 19:18:35.977842', 1, 38, 12, 'rod', NULL);
INSERT INTO controle VALUES (392, '2014-07-19 19:24:18.417693', 1, 39, 12, 'rod', NULL);
INSERT INTO controle VALUES (393, '2014-07-19 19:27:27.290475', 1, 40, 12, 'rod', NULL);
INSERT INTO controle VALUES (394, '2014-07-19 19:29:10.874035', 1, 41, 12, 'rod', NULL);
INSERT INTO controle VALUES (395, '2014-07-19 19:33:59.918166', 1, 42, 12, 'rod', NULL);
INSERT INTO controle VALUES (396, '2014-07-19 19:34:16.765086', 1, 43, 12, 'rod', NULL);
INSERT INTO controle VALUES (397, '2014-07-19 19:35:03.743887', 1, 44, 12, 'rod', NULL);
INSERT INTO controle VALUES (398, '2014-07-19 19:35:23.601791', 1, 45, 12, 'rod', NULL);
INSERT INTO controle VALUES (399, '2014-07-19 19:36:16.922825', 1, 46, 12, 'rod', NULL);
INSERT INTO controle VALUES (400, '2014-07-19 20:55:22.163825', 1, 47, 12, 'rod', NULL);
INSERT INTO controle VALUES (401, '2014-07-19 20:56:15.284644', 1, 48, 12, 'rod', NULL);
INSERT INTO controle VALUES (402, '2014-07-19 20:56:27.253604', 1, 49, 12, 'rod', NULL);
INSERT INTO controle VALUES (403, '2014-07-19 21:08:09.878012', 1, 50, 12, 'rod', NULL);
INSERT INTO controle VALUES (404, '2014-07-21 17:37:41.887144', 1, 26, 2, NULL, NULL);
INSERT INTO controle VALUES (405, '2014-07-21 17:37:41.887144', 1, 51, 12, NULL, NULL);
INSERT INTO controle VALUES (406, '2014-07-21 17:37:41.887144', 1, 52, 12, NULL, NULL);
INSERT INTO controle VALUES (407, '2014-07-21 17:37:41.887144', 1, 53, 12, NULL, NULL);
INSERT INTO controle VALUES (408, '2014-07-21 17:37:41.887144', 1, 54, 12, NULL, NULL);
INSERT INTO controle VALUES (409, '2014-07-21 17:37:41.887144', 1, 55, 12, NULL, NULL);
INSERT INTO controle VALUES (410, '2014-07-21 17:37:41.887144', 1, 56, 12, NULL, NULL);
INSERT INTO controle VALUES (411, '2014-07-21 17:37:41.887144', 1, 57, 12, NULL, NULL);
INSERT INTO controle VALUES (412, '2014-07-21 17:37:41.887144', 1, 58, 12, NULL, NULL);
INSERT INTO controle VALUES (413, '2014-07-21 23:31:12.37398', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (414, '2014-07-21 23:31:18.917533', 2, 79, 3, 'rod', NULL);
INSERT INTO controle VALUES (415, '2014-07-21 23:31:26.967291', 2, 80, 3, 'rod', NULL);
INSERT INTO controle VALUES (416, '2014-07-21 23:31:31.959386', 2, 80, 3, 'rod', NULL);
INSERT INTO controle VALUES (417, '2014-07-21 23:31:57.477157', 3, 36, 6, 'rod', NULL);
INSERT INTO controle VALUES (418, '2014-07-21 23:32:01.285444', 3, 44, 6, 'rod', NULL);
INSERT INTO controle VALUES (419, '2014-07-21 23:32:04.316185', 3, 45, 6, 'rod', NULL);
INSERT INTO controle VALUES (420, '2014-07-21 23:32:07.370853', 3, 67, 6, 'rod', NULL);
INSERT INTO controle VALUES (421, '2014-07-21 23:32:10.816467', 3, 68, 6, 'rod', NULL);
INSERT INTO controle VALUES (422, '2014-07-21 23:32:13.497176', 3, 68, 6, 'rod', NULL);
INSERT INTO controle VALUES (423, '2014-07-22 17:19:02.736732', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (424, '2014-07-22 17:22:30.616783', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (425, '2014-07-22 17:26:27.510542', 1, 81, 3, 'rod', NULL);
INSERT INTO controle VALUES (426, '2014-07-22 17:35:24.079311', 1, 82, 4, 'rod', NULL);
INSERT INTO controle VALUES (427, '2014-07-22 17:37:47.465471', 1, 83, 4, 'rod', NULL);
INSERT INTO controle VALUES (428, '2014-07-22 22:40:09.107554', 3, 46, 3, 'rod', NULL);
INSERT INTO controle VALUES (430, '2014-07-23 00:34:19.012933', 1, 59, 12, 'rod', NULL);
INSERT INTO controle VALUES (431, '2014-07-23 00:34:58.353394', 2, 11, 2, NULL, NULL);
INSERT INTO controle VALUES (432, '2014-07-23 00:34:58.353394', 2, NULL, 12, NULL, NULL);
INSERT INTO controle VALUES (433, '2014-07-23 00:34:58.353394', 2, NULL, 12, NULL, NULL);
INSERT INTO controle VALUES (434, '2014-07-23 00:35:00.356745', 2, 11, 2, NULL, NULL);
INSERT INTO controle VALUES (435, '2014-07-23 00:35:00.356745', 2, NULL, 12, NULL, NULL);
INSERT INTO controle VALUES (436, '2014-07-23 00:35:00.356745', 2, NULL, 12, NULL, NULL);
INSERT INTO controle VALUES (437, '2014-07-23 09:39:08.796313', 1, 84, 3, 'rod', NULL);
INSERT INTO controle VALUES (438, '2014-07-23 09:39:49.240325', 1, 85, 3, 'rod', NULL);
INSERT INTO controle VALUES (439, '2014-07-23 09:40:04.057649', 3, 85, 3, 'rod', NULL);
INSERT INTO controle VALUES (440, '2014-07-23 09:42:13.20163', 3, 84, 3, 'rod', NULL);
INSERT INTO controle VALUES (441, '2014-07-23 09:44:59.018843', 3, 81, 3, 'rod', NULL);
INSERT INTO controle VALUES (442, '2014-07-23 09:45:11.323038', 3, 81, 3, 'rod', NULL);
INSERT INTO controle VALUES (443, '2014-07-23 09:45:43.516327', 3, 81, 3, 'rod', NULL);
INSERT INTO controle VALUES (444, '2014-07-23 09:46:16.779342', 3, 79, 3, 'rod', NULL);
INSERT INTO controle VALUES (445, '2014-07-23 09:48:13.9523', 1, 86, 3, 'rod', NULL);
INSERT INTO controle VALUES (446, '2014-07-23 09:49:45.925579', 1, 87, 3, 'rod', NULL);
INSERT INTO controle VALUES (447, '2014-07-23 09:53:42.076741', 3, 87, 3, 'rod', NULL);
INSERT INTO controle VALUES (448, '2014-07-23 09:53:52.82722', 3, 87, 3, 'rod', NULL);
INSERT INTO controle VALUES (449, '2014-07-23 09:58:31.990875', 3, 86, 3, 'rod', NULL);
INSERT INTO controle VALUES (450, '2014-07-23 09:59:59.329005', 1, 88, 3, 'rod', NULL);
INSERT INTO controle VALUES (451, '2014-07-23 10:00:19.583097', 3, 88, 3, 'rod', NULL);
INSERT INTO controle VALUES (452, '2014-07-23 10:16:49.063045', 1, 89, 3, 'rod', NULL);
INSERT INTO controle VALUES (453, '2014-07-23 10:17:02.063547', 3, 89, 3, 'rod', NULL);
INSERT INTO controle VALUES (454, '2014-07-23 10:18:39.117835', 1, 90, 3, 'rod', NULL);
INSERT INTO controle VALUES (455, '2014-07-23 10:18:57.958711', 3, 90, 3, 'rod', NULL);
INSERT INTO controle VALUES (456, '2014-07-23 10:19:45.394244', 1, 91, 3, 'rod', NULL);
INSERT INTO controle VALUES (457, '2014-07-23 10:19:56.819142', 3, 91, 3, 'rod', NULL);
INSERT INTO controle VALUES (458, '2014-07-23 10:23:41.173656', 1, 92, 3, 'rod', NULL);
INSERT INTO controle VALUES (459, '2014-07-23 10:23:53.245809', 3, 92, 3, 'rod', NULL);
INSERT INTO controle VALUES (460, '2014-07-23 10:24:20.215216', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (461, '2014-07-23 10:24:32.674675', 1, 93, 3, 'rod', NULL);
INSERT INTO controle VALUES (462, '2014-07-23 10:24:44.423385', 2, 93, 3, 'rod', NULL);
INSERT INTO controle VALUES (463, '2014-07-23 10:26:24.889798', 3, 93, 3, 'rod', NULL);
INSERT INTO controle VALUES (464, '2014-07-23 10:30:41.527009', 1, 94, 3, 'rod', NULL);
INSERT INTO controle VALUES (465, '2014-07-23 10:37:16.454127', 1, 95, 3, 'rod', NULL);
INSERT INTO controle VALUES (466, '2014-07-23 10:38:18.645304', 1, 96, 3, 'rod', NULL);
INSERT INTO controle VALUES (467, '2014-07-23 10:38:42.53217', 1, 97, 3, 'rod', NULL);
INSERT INTO controle VALUES (554, '2014-07-24 08:50:32.574425', 2, 3, 2, 'rod', NULL);
INSERT INTO controle VALUES (469, '2014-07-23 11:03:04.609626', 1, 99, 3, 'rod', NULL);
INSERT INTO controle VALUES (470, '2014-07-23 11:03:51.142006', 1, 100, 3, 'rod', NULL);
INSERT INTO controle VALUES (471, '2014-07-23 11:09:14.255625', 1, 101, 3, 'rod', NULL);
INSERT INTO controle VALUES (472, '2014-07-23 11:10:23.291563', 2, 27, 3, 'rod', NULL);
INSERT INTO controle VALUES (473, '2014-07-23 11:10:38.971397', 1, 102, 3, 'rod', NULL);
INSERT INTO controle VALUES (474, '2014-07-23 11:10:49.315552', 2, 80, 3, 'rod', NULL);
INSERT INTO controle VALUES (475, '2014-07-23 11:11:57.315943', 3, 102, 3, 'rod', NULL);
INSERT INTO controle VALUES (476, '2014-07-23 11:14:10.746941', 3, 101, 3, 'rod', NULL);
INSERT INTO controle VALUES (477, '2014-07-23 11:15:22.46261', 2, 94, 3, 'rod', NULL);
INSERT INTO controle VALUES (478, '2014-07-23 11:15:33.069834', 1, 103, 3, 'rod', NULL);
INSERT INTO controle VALUES (479, '2014-07-23 11:15:44.522847', 3, 103, 3, 'rod', NULL);
INSERT INTO controle VALUES (480, '2014-07-23 11:15:51.838101', 3, 100, 3, 'rod', NULL);
INSERT INTO controle VALUES (481, '2014-07-23 11:15:59.30485', 3, 99, 3, 'rod', NULL);
INSERT INTO controle VALUES (482, '2014-07-23 11:17:45.464309', 3, 97, 3, 'rod', NULL);
INSERT INTO controle VALUES (483, '2014-07-23 11:33:47.328512', 3, 96, 3, 'rod', NULL);
INSERT INTO controle VALUES (484, '2014-07-23 11:34:30.869295', 3, 83, 4, 'rod', NULL);
INSERT INTO controle VALUES (485, '2014-07-23 11:38:20.876965', 3, 82, 4, 'rod', NULL);
INSERT INTO controle VALUES (486, '2014-07-23 11:40:31.255607', 3, 78, 4, 'rod', NULL);
INSERT INTO controle VALUES (487, '2014-07-23 11:40:51.706982', 1, 104, 4, 'rod', NULL);
INSERT INTO controle VALUES (488, '2014-07-23 11:41:34.552714', 2, 75, 4, 'rod', NULL);
INSERT INTO controle VALUES (489, '2014-07-23 11:50:30.085121', 1, 60, 12, 'rod', NULL);
INSERT INTO controle VALUES (490, '2014-07-23 12:07:49.781314', 1, 105, 5, 'rod', NULL);
INSERT INTO controle VALUES (491, '2014-07-23 12:10:42.884428', 1, 106, 5, 'rod', NULL);
INSERT INTO controle VALUES (492, '2014-07-23 12:11:45.361765', 1, 107, 5, 'rod', NULL);
INSERT INTO controle VALUES (493, '2014-07-23 12:18:56.051244', 1, 108, 5, 'rod', NULL);
INSERT INTO controle VALUES (494, '2014-07-23 12:23:07.760848', 1, 109, 5, 'rod', NULL);
INSERT INTO controle VALUES (495, '2014-07-23 12:26:55.215809', 1, 110, 5, 'rod', NULL);
INSERT INTO controle VALUES (496, '2014-07-23 12:28:03.879279', 1, 111, 5, 'rod', NULL);
INSERT INTO controle VALUES (497, '2014-07-23 13:15:47.185062', 1, 112, 5, 'rod', NULL);
INSERT INTO controle VALUES (498, '2014-07-23 13:30:45.29325', 3, 112, 5, 'rod', NULL);
INSERT INTO controle VALUES (499, '2014-07-23 13:33:09.642741', 1, 113, 5, 'rod', NULL);
INSERT INTO controle VALUES (500, '2014-07-23 13:33:29.043342', 1, 114, 5, 'rod', NULL);
INSERT INTO controle VALUES (501, '2014-07-23 13:33:42.290179', 2, 64, 5, 'rod', NULL);
INSERT INTO controle VALUES (502, '2014-07-23 13:41:40.239485', 1, 115, 5, 'rod', NULL);
INSERT INTO controle VALUES (503, '2014-07-23 13:41:56.32708', 3, 115, 5, 'rod', NULL);
INSERT INTO controle VALUES (504, '2014-07-23 13:42:01.403345', 3, 110, 5, 'rod', NULL);
INSERT INTO controle VALUES (505, '2014-07-23 14:04:04.725509', 1, 116, 5, 'rod', NULL);
INSERT INTO controle VALUES (506, '2014-07-23 14:21:33.084894', 3, 116, 5, 'rod', NULL);
INSERT INTO controle VALUES (507, '2014-07-23 14:21:46.337601', 3, 116, 5, 'rod', NULL);
INSERT INTO controle VALUES (508, '2014-07-23 14:22:53.573487', 3, 114, 5, 'rod', NULL);
INSERT INTO controle VALUES (509, '2014-07-23 14:23:46.051163', 3, 113, 5, 'rod', NULL);
INSERT INTO controle VALUES (510, '2014-07-23 14:23:51.704457', 3, 111, 5, 'rod', NULL);
INSERT INTO controle VALUES (511, '2014-07-23 14:42:06.878734', 1, 117, 5, 'rod', NULL);
INSERT INTO controle VALUES (512, '2014-07-23 14:46:19.703025', 1, 118, 6, 'rod', NULL);
INSERT INTO controle VALUES (513, '2014-07-23 14:48:32.955888', 1, 119, 6, 'rod', NULL);
INSERT INTO controle VALUES (514, '2014-07-23 14:48:48.651827', 2, 119, 6, 'rod', NULL);
INSERT INTO controle VALUES (515, '2014-07-23 14:56:40.372468', 1, 120, 7, 'rod', NULL);
INSERT INTO controle VALUES (516, '2014-07-23 14:57:22.266797', 1, 61, 12, 'rod', NULL);
INSERT INTO controle VALUES (517, '2014-07-23 14:57:43.351957', 3, 120, 7, 'rod', NULL);
INSERT INTO controle VALUES (518, '2014-07-23 14:58:45.640133', 1, 121, 7, 'rod', NULL);
INSERT INTO controle VALUES (519, '2014-07-23 14:58:57.8338', 3, 121, 7, 'rod', NULL);
INSERT INTO controle VALUES (520, '2014-07-23 16:26:17.185599', 2, 2, 2, 'rod', NULL);
INSERT INTO controle VALUES (521, '2014-07-23 16:26:17.185599', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (522, '2014-07-23 16:26:17.185599', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (523, '2014-07-23 16:26:17.185599', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (524, '2014-07-23 16:46:17.117149', 2, NULL, 2, 'rod', NULL);
INSERT INTO controle VALUES (525, '2014-07-23 16:46:17.117149', 1, 62, 12, 'rod', NULL);
INSERT INTO controle VALUES (526, '2014-07-23 16:46:17.117149', 1, 63, 12, 'rod', NULL);
INSERT INTO controle VALUES (527, '2014-07-23 16:53:59.331614', 1, 28, 2, 'rod', NULL);
INSERT INTO controle VALUES (528, '2014-07-23 16:53:59.331614', 1, 64, 12, 'rod', NULL);
INSERT INTO controle VALUES (529, '2014-07-23 17:00:16.987327', 1, 29, 2, 'rod', NULL);
INSERT INTO controle VALUES (530, '2014-07-23 17:00:16.987327', 1, 65, 12, 'rod', NULL);
INSERT INTO controle VALUES (531, '2014-07-23 17:17:14.539524', 2, 2, 2, 'rod', NULL);
INSERT INTO controle VALUES (532, '2014-07-23 17:17:14.539524', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (533, '2014-07-23 17:17:14.539524', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (534, '2014-07-23 17:17:14.539524', 1, 66, 12, 'rod', NULL);
INSERT INTO controle VALUES (535, '2014-07-23 17:18:59.208226', 2, 2, 2, 'rod', NULL);
INSERT INTO controle VALUES (536, '2014-07-23 17:18:59.208226', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (537, '2014-07-23 17:18:59.208226', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (538, '2014-07-23 17:18:59.208226', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (539, '2014-07-23 17:24:52.619039', 2, 2, 2, 'rod', NULL);
INSERT INTO controle VALUES (540, '2014-07-23 17:24:52.619039', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (541, '2014-07-23 17:24:52.619039', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (542, '2014-07-23 17:24:52.619039', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (543, '2014-07-23 17:24:52.619039', 1, 67, 12, 'rod', NULL);
INSERT INTO controle VALUES (544, '2014-07-23 17:35:32.282245', 2, 2, 2, 'rod', NULL);
INSERT INTO controle VALUES (545, '2014-07-23 17:35:32.282245', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (546, '2014-07-23 17:35:32.282245', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (547, '2014-07-23 17:35:32.282245', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (548, '2014-07-23 17:35:32.282245', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (549, '2014-07-23 17:56:03.485071', 2, 2, 2, 'rod', NULL);
INSERT INTO controle VALUES (550, '2014-07-23 17:56:03.485071', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (551, '2014-07-23 17:56:03.485071', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (552, '2014-07-23 17:56:03.485071', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (553, '2014-07-23 17:56:03.485071', 2, 2, 12, 'rod', NULL);
INSERT INTO controle VALUES (555, '2014-07-24 08:50:32.574425', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (556, '2014-07-24 08:50:32.574425', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (557, '2014-07-24 08:50:32.574425', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (558, '2014-07-24 08:50:32.574425', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (559, '2014-07-24 08:50:32.574425', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (560, '2014-07-24 08:50:32.574425', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (561, '2014-07-24 08:50:32.574425', 1, 68, 12, 'rod', NULL);
INSERT INTO controle VALUES (562, '2014-07-24 08:58:59.894299', 2, 3, 2, 'rod', NULL);
INSERT INTO controle VALUES (563, '2014-07-24 08:58:59.894299', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (564, '2014-07-24 08:58:59.894299', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (565, '2014-07-24 08:58:59.894299', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (566, '2014-07-24 08:58:59.894299', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (567, '2014-07-24 08:58:59.894299', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (568, '2014-07-24 08:58:59.894299', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (569, '2014-07-24 09:00:58.503932', 2, 3, 2, 'rod', NULL);
INSERT INTO controle VALUES (570, '2014-07-24 09:00:58.503932', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (571, '2014-07-24 09:00:58.503932', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (572, '2014-07-24 09:00:58.503932', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (573, '2014-07-24 09:00:58.503932', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (574, '2014-07-24 09:00:58.503932', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (575, '2014-07-24 09:00:58.503932', 2, 3, 12, 'rod', NULL);
INSERT INTO controle VALUES (605, '2014-07-25 11:27:09.728269', 1, 2, 7, 'rod', NULL);
INSERT INTO controle VALUES (606, '2014-07-25 11:36:32.792401', 1, 3, 7, 'rod', NULL);
INSERT INTO controle VALUES (607, '2014-07-25 11:37:27.442607', 1, 4, 7, 'rod', NULL);
INSERT INTO controle VALUES (608, '2014-07-25 11:39:27.476697', 1, 5, 7, 'rod', NULL);
INSERT INTO controle VALUES (609, '2014-07-25 11:40:14.473714', 1, 6, 7, 'rod', NULL);
INSERT INTO controle VALUES (610, '2014-07-25 11:54:35.4679', 1, 7, 7, 'rod', NULL);
INSERT INTO controle VALUES (611, '2014-07-25 11:54:47.898433', 1, 8, 7, 'rod', NULL);
INSERT INTO controle VALUES (612, '2014-07-25 11:54:54.158695', 1, 9, 7, 'rod', NULL);
INSERT INTO controle VALUES (584, '2014-07-24 09:13:24.965114', 1, 30, 2, 'rod', NULL);
INSERT INTO controle VALUES (585, '2014-07-24 09:13:24.965114', 1, 69, 12, 'rod', NULL);
INSERT INTO controle VALUES (586, '2014-07-24 09:13:24.965114', 1, 70, 12, 'rod', NULL);
INSERT INTO controle VALUES (587, '2014-07-24 09:13:24.965114', 1, 71, 12, 'rod', NULL);
INSERT INTO controle VALUES (588, '2014-07-24 09:13:24.965114', 1, 72, 12, 'rod', NULL);
INSERT INTO controle VALUES (589, '2014-07-24 09:13:24.965114', 1, 73, 12, 'rod', NULL);
INSERT INTO controle VALUES (590, '2014-07-24 09:13:24.965114', 1, 74, 12, 'rod', NULL);
INSERT INTO controle VALUES (591, '2014-07-24 09:13:24.965114', 1, 75, 12, 'rod', NULL);
INSERT INTO controle VALUES (592, '2014-07-24 09:13:24.965114', 1, 76, 12, 'rod', NULL);
INSERT INTO controle VALUES (593, '2014-07-24 09:13:24.965114', 1, 77, 12, 'rod', NULL);
INSERT INTO controle VALUES (594, '2014-07-24 09:13:24.965114', 1, 78, 12, 'rod', NULL);
INSERT INTO controle VALUES (595, '2014-07-24 09:13:24.965114', 1, 79, 12, 'rod', NULL);
INSERT INTO controle VALUES (596, '2014-07-24 09:13:24.965114', 1, 80, 12, 'rod', NULL);
INSERT INTO controle VALUES (597, '2014-07-24 09:13:24.965114', 1, 81, 12, 'rod', NULL);
INSERT INTO controle VALUES (598, '2014-07-24 09:13:24.965114', 1, 82, 12, 'rod', NULL);
INSERT INTO controle VALUES (599, '2014-07-24 09:13:24.965114', 1, 83, 12, 'rod', NULL);
INSERT INTO controle VALUES (600, '2014-07-24 09:13:24.965114', 1, 84, 12, 'rod', NULL);
INSERT INTO controle VALUES (601, '2014-07-24 09:13:24.965114', 1, 85, 12, 'rod', NULL);
INSERT INTO controle VALUES (602, '2014-07-24 09:13:24.965114', 1, 86, 12, 'rod', NULL);
INSERT INTO controle VALUES (603, '2014-07-24 09:13:24.965114', 1, 87, 12, 'rod', NULL);
INSERT INTO controle VALUES (604, '2014-07-24 09:13:24.965114', 1, 88, 12, 'rod', NULL);


--
-- Name: controle_controleid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('controle_controleid_seq', 93, false);


--
-- Name: controle_controleid_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('controle_controleid_seq1', 612, true);


--
-- Data for Name: documentos; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO documentos VALUES (9, 6, '111', '111', '111', '111', NULL, 1);
INSERT INTO documentos VALUES (10, 7, '111', '111', '111', '111', NULL, 1);
INSERT INTO documentos VALUES (11, 8, '111', '111', '111', '111', NULL, 1);


--
-- Name: documentos_documentoid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('documentos_documentoid_seq', 16, false);


--
-- Name: documentos_documentoid_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('documentos_documentoid_seq1', 11, true);


--
-- Data for Name: embalagem; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Name: embalagem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('embalagem_id_seq', 1, false);


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO endereco VALUES (45, 6, '1111', '111', 'PR', '111', '111', '111', '11', 1, 111);
INSERT INTO endereco VALUES (46, 7, '1111', '111', 'PR', '111', '111', '111', '11', 1, 111);
INSERT INTO endereco VALUES (47, 8, '1111', '111', 'PR', '111', '111', '111', '11', 1, 111);


--
-- Name: endereco_enderecoid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('endereco_enderecoid_seq', 30, false);


--
-- Name: endereco_enderecoid_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('endereco_enderecoid_seq1', 47, true);


--
-- Name: procedure_proc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('procedure_proc_id_seq', 3, false);


--
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO produto VALUES (4, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 0, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (14, '99999', 80, 43, NULL, NULL, 73, NULL, NULL, 0, '9999', NULL, NULL, NULL);
INSERT INTO produto VALUES (15, '99999', 80, 43, NULL, NULL, 73, NULL, NULL, 0, '9999', NULL, NULL, NULL);
INSERT INTO produto VALUES (16, '99999', 80, 43, NULL, NULL, 73, NULL, NULL, 0, '9999', NULL, NULL, NULL);
INSERT INTO produto VALUES (17, '99999', 80, 43, NULL, NULL, 73, NULL, NULL, 0, '9999', NULL, NULL, NULL);
INSERT INTO produto VALUES (10, '5555', 27, 28, NULL, NULL, 71, NULL, NULL, 0, '5555', NULL, NULL, NULL);
INSERT INTO produto VALUES (12, '111', 27, 28, NULL, NULL, 71, NULL, NULL, 0, '1', NULL, NULL, NULL);
INSERT INTO produto VALUES (13, 'Test 0002', 79, 56, NULL, NULL, 71, NULL, NULL, 0, '000001111', NULL, NULL, NULL);
INSERT INTO produto VALUES (26, 'Test 003', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '2000101', 'foto003.jpg', NULL, NULL);
INSERT INTO produto VALUES (19, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (20, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (21, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (22, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (23, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (24, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (25, '11', 27, 28, NULL, NULL, 71, NULL, NULL, 7, '11', NULL, NULL, NULL);
INSERT INTO produto VALUES (28, 'rrr', 27, NULL, NULL, NULL, 71, NULL, NULL, NULL, 'rrr', NULL, NULL, NULL);
INSERT INTO produto VALUES (29, 'Refrigerante Cola', 94, NULL, NULL, NULL, 73, NULL, NULL, NULL, '00001111', NULL, NULL, NULL);
INSERT INTO produto VALUES (11, '5555', 27, 28, NULL, NULL, 71, NULL, NULL, 0, '5555', NULL, NULL, NULL);
INSERT INTO produto VALUES (3, 'GUGU', 27, 28, 43, 68, 71, 2, 1, 1, 'GUGU', NULL, NULL, NULL);
INSERT INTO produto VALUES (2, 'Test 001', 27, 28, 43, 68, 71, 1, 1, 1, 'GU', NULL, NULL, NULL);
INSERT INTO produto VALUES (30, 'TEST 001', 94, 28, NULL, NULL, 73, NULL, NULL, NULL, 'TEST 001', NULL, NULL, NULL);


--
-- Name: produto_produtoid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('produto_produtoid_seq', 22, false);


--
-- Name: produto_produtoid_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('produto_produtoid_seq1', 30, true);


--
-- Data for Name: supermercado; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO supermercado VALUES (6, '111', '111', '11', NULL, '111');
INSERT INTO supermercado VALUES (7, '111', '111', '11', NULL, '111');
INSERT INTO supermercado VALUES (8, '111', '111', '11', NULL, '111');


--
-- Name: supermercado_supermercadoid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('supermercado_supermercadoid_seq', 49, false);


--
-- Name: supermercado_supermercadoid_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('supermercado_supermercadoid_seq1', 8, true);


--
-- Data for Name: tabpreco; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO tabpreco VALUES (34, 25, 7, '2014-07-18 10:59:50.829382', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (15, 14, 1, '2014-07-16 11:42:34.494514', 1.55, 9.65, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (17, 15, 2, '2014-07-16 11:48:07.284143', 1.00, 100.99, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (14, 13, 2, '2014-07-15 22:32:03.330335', 1.56, 1.40, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (60, 11, 7, '2014-07-23 11:50:30.085121', 2.50, 1.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (61, 11, 7, '2014-07-23 14:57:22.266797', 1.99, 1.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (32, 23, 1, '2014-07-16 22:42:06.251575', 2.00, 2.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (33, 24, 10, '2014-07-16 22:45:41.51273', 101.00, 10.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (49, 3, 8, '2014-07-19 20:56:27.253604', 3.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (50, 25, 7, '2014-07-19 21:08:09.878012', 1.00, 1.00, 1, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (51, 26, 6, '2014-07-21 17:37:41.887144', 55.65, 66.60, 1, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (52, 26, 6, '2014-07-21 17:37:41.887144', 3.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (53, 26, 6, '2014-07-21 17:37:41.887144', 3.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (54, 26, 6, '2014-07-21 17:37:41.887144', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (16, 15, 7, '2014-07-16 11:48:07.284143', 1.56, 1.40, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (18, 16, 7, '2014-07-16 14:14:49.993524', 1.55, 9.65, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (19, 17, 7, '2014-07-16 14:25:11.36347', 1.56, 1.40, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (20, 4, 7, '2014-07-16 21:40:11.746757', 5.55, 55.88, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (23, 4, 7, '2014-07-16 21:54:52.500368', 5.55, 55.88, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (21, 4, 7, '2014-07-16 21:40:11.746757', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (22, 4, 7, '2014-07-16 21:40:11.746757', 2.00, 2.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (24, 4, 7, '2014-07-16 21:54:52.500368', 3.00, 3.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (38, 25, 7, '2014-07-19 19:18:35.977842', 2.63, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (62, NULL, 7, '2014-07-23 16:46:17.117149', 1.00, 1.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (39, 25, 7, '2014-07-19 19:24:18.417693', 2.69, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (40, 25, 7, '2014-07-19 19:27:27.290475', 2.68, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (41, 25, 7, '2014-07-19 19:29:10.874035', 2.67, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (42, 3, 7, '2014-07-19 19:33:59.918166', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (43, 3, 7, '2014-07-19 19:34:16.765086', 1.88, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (44, 25, 7, '2014-07-19 19:35:03.743887', 2.68, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (45, 3, 7, '2014-07-19 19:35:23.601791', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (46, 3, 7, '2014-07-19 19:36:16.922825', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (63, NULL, 8, '2014-07-23 16:46:17.117149', 2.00, 2.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (55, 26, 7, '2014-07-21 17:37:41.887144', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (56, 26, 7, '2014-07-21 17:37:41.887144', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (57, 26, 7, '2014-07-21 17:37:41.887144', 1.88, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (58, 26, 7, '2014-07-21 17:37:41.887144', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (4, 2, 7, '2014-07-17 13:45:47.644999', 5.55, 55.88, 2, NULL, NULL, NULL, NULL);
INSERT INTO tabpreco VALUES (25, 2, 7, '2014-07-18 12:00:58.246088', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (11, 10, 1, '2014-07-15 17:04:02.707411', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (12, 11, 2, '2014-07-15 17:04:02.707411', 2.00, 2.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (13, 12, 1, '2014-07-15 22:32:03.330335', 1.56, 1.44, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (26, 14, 1, '2014-07-16 22:08:08.07679', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (35, 15, 8, '2014-07-18 10:59:50.829382', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (36, 16, 8, '2014-07-19 18:31:53.217943', 1.99, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (37, 17, 8, '2014-07-19 18:32:30.013545', 1.99, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (27, 18, 1, '2014-07-16 22:37:30.151127', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (28, 19, 1, '2014-07-16 22:37:30.151127', 2.00, 2.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (29, 20, 1, '2014-07-16 22:42:06.251575', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (30, 21, 1, '2014-07-16 22:42:06.251575', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (31, 22, 1, '2014-07-16 22:42:06.251575', 1.00, 1.00, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (47, 25, 8, '2014-07-19 20:55:22.163825', 1.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (48, 26, 8, '2014-07-19 20:56:15.284644', 3.99, 66.60, 2, NULL, NULL, 1, NULL);
INSERT INTO tabpreco VALUES (59, 11, 7, '2014-07-23 00:34:19.012933', 2.00, 1.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (64, 28, 8, '2014-07-23 16:53:59.331614', 8.00, 8.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (65, 29, 7, '2014-07-23 17:00:16.987327', 1.00, 1.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (66, 2, 8, '2014-07-23 17:17:14.539524', 5.00, 1.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (68, 3, 9, '2014-07-24 08:50:32.574425', 55.55, 1111.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (67, 2, 6, '2014-07-23 17:24:52.619039', 5.00, 5.00, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (2, 7, NULL, '2014-07-23 17:56:03.485071', 5.55, 55.88, 2, NULL, NULL, NULL, NULL);
INSERT INTO tabpreco VALUES (3, 7, NULL, '2014-07-24 09:00:58.503932', 1.99, 66.60, 2, NULL, NULL, NULL, NULL);
INSERT INTO tabpreco VALUES (69, 30, NULL, '2014-07-24 09:13:24.965114', 1.99, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (70, 30, NULL, '2014-07-24 09:13:24.965114', 2.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (71, 30, NULL, '2014-07-24 09:13:24.965114', 1.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (72, 30, NULL, '2014-07-24 09:13:24.965114', 2.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (73, 30, NULL, '2014-07-24 09:13:24.965114', 1.56, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (74, 30, NULL, '2014-07-24 09:13:24.965114', 1.56, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (75, 30, NULL, '2014-07-24 09:13:24.965114', 1.55, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (76, 30, NULL, '2014-07-24 09:13:24.965114', 1.56, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (77, 30, NULL, '2014-07-24 09:13:24.965114', 1.55, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (78, 30, NULL, '2014-07-24 09:13:24.965114', 1.56, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (79, 30, NULL, '2014-07-24 09:13:24.965114', 2.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (80, 30, NULL, '2014-07-24 09:13:24.965114', 1.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (81, 30, NULL, '2014-07-24 09:13:24.965114', 1.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (82, 30, NULL, '2014-07-24 09:13:24.965114', 1.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (83, 30, NULL, '2014-07-24 09:13:24.965114', 2.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (84, 30, NULL, '2014-07-24 09:13:24.965114', 101.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (85, 30, NULL, '2014-07-24 09:13:24.965114', 1.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (86, 30, NULL, '2014-07-24 09:13:24.965114', 3.99, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (87, 30, NULL, '2014-07-24 09:13:24.965114', 8.00, NULL, 2, NULL, NULL, 1, 'A');
INSERT INTO tabpreco VALUES (88, 30, NULL, '2014-07-24 09:13:24.965114', 1.00, NULL, 2, NULL, NULL, 1, 'A');


--
-- Name: tabpreco_precoid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('tabpreco_precoid_seq', 26, false);


--
-- Name: tabpreco_precoid_seq1; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('tabpreco_precoid_seq1', 88, true);


--
-- Name: thebundle_bundle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('thebundle_bundle_id_seq', 6, false);


--
-- Data for Name: unimed; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO unimed VALUES (1, 'pc', 'Peça');
INSERT INTO unimed VALUES (2, '44', '44');
INSERT INTO unimed VALUES (3, '55', '555q55');
INSERT INTO unimed VALUES (4, '88', '88');
INSERT INTO unimed VALUES (5, '99', '99');
INSERT INTO unimed VALUES (6, '10', '1010');
INSERT INTO unimed VALUES (7, '11', '11');
INSERT INTO unimed VALUES (8, '55', '55');
INSERT INTO unimed VALUES (9, '55', '44');


--
-- Name: unimed_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('unimed_id_seq', 9, true);


--
-- Name: cadastro_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cadastro
    ADD CONSTRAINT cadastro_pkey PRIMARY KEY (cadastroid);


--
-- Name: cidade_estado_key; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_estado_key UNIQUE (estado);


--
-- Name: cidade_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (clienteid);


--
-- Name: cliente_usuario_key; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_usuario_key UNIQUE (usuario);


--
-- Name: controle_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY controle
    ADD CONSTRAINT controle_pkey PRIMARY KEY (controleid);


--
-- Name: documentos_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY documentos
    ADD CONSTRAINT documentos_pkey PRIMARY KEY (documentoid);


--
-- Name: embalagem_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY embalagem
    ADD CONSTRAINT embalagem_pkey PRIMARY KEY (id);


--
-- Name: endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (enderecoid);


--
-- Name: produto_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (produtoid);


--
-- Name: supermercado_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY supermercado
    ADD CONSTRAINT supermercado_pkey PRIMARY KEY (supermercadoid);


--
-- Name: tabpreco_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT tabpreco_pkey PRIMARY KEY (precoid);


--
-- Name: unimed_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY unimed
    ADD CONSTRAINT unimed_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM supermercado;
GRANT ALL ON SCHEMA public TO supermercado;


--
-- PostgreSQL database dump complete
--


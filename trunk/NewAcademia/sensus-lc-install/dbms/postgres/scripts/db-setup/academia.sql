-- Table: academia

-- DROP TABLE academia;

CREATE TABLE academia
(
  cdacad serial NOT NULL,
  academ character varying(100) NOT NULL,
  lograd character varying(150),
  numen integer,
  bairr character varying(50),
  cidade character varying(50),
  cep character varying(15),
  telef character varying(15),
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  latitude double precision,
  longitude double precision,
  dataini timestamp with time zone,
  datafin timestamp with time zone,
  atual boolean,
  CONSTRAINT cdacad_pkey PRIMARY KEY (cdacad),
  CONSTRAINT fk_tenant_cdacad FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE academia
  OWNER TO qat;
---------------------------------------------------
  -- Table: grupomuscular

-- DROP TABLE grupomuscular;

CREATE TABLE grupomuscular
(
  cdgropomusc serial NOT NULL,
  nome character varying(100) NOT NULL,
  descricao character varying(150),
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdgropomusc_pkey PRIMARY KEY (cdgropomusc),
  CONSTRAINT fk_tenant_cdgropomusc FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE grupomuscular
  OWNER TO postgres;
--------------------------------------------------------

  -- Function: ins_academia(character varying, character varying, character varying, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean)

-- DROP FUNCTION ins_academia(character varying, character varying, character varying, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean);

CREATE OR REPLACE FUNCTION ins_academia(p_academ character varying, p_lograd character varying, p_numen character varying, p_bairr character varying, p_cidade character varying, p_cep character varying, p_telef character varying, p_dataini timestamp with time zone, p_datafin timestamp with time zone, p_createdate timestamp with time zone, p_createuser character varying, p_tenantid integer, p_userid integer, p_atual boolean)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN
	INSERT INTO academia
(
		academ,
		lograd,
		bairr,
		cidade,
		cep,
		telef,
		dataini,
		datafin,
		create_date,
		create_user,
		tenant_id,
		atual)
	VALUES
(

	p_academ,
	p_lograd,
	p_bairr,
	p_cidade,
	p_cep,
	p_telef,
	p_dataini,
	p_datafin,
	p_createdate,
	p_createuser,
	p_tenantid,
	p_atual ) RETURNING cdacad INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_academia(character varying, character varying, character varying, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean)
  OWNER TO qat;
--------------------------------------------------------

-- Function: ins_academia(character varying, character varying, character varying, character varying, character varying, character varying, character varying, bigint, bigint, bigint, character varying, integer, integer, boolean)

-- DROP FUNCTION ins_academia(character varying, character varying, character varying, character varying, character varying, character varying, character varying, bigint, bigint, bigint, character varying, integer, integer, boolean);

CREATE OR REPLACE FUNCTION ins_academia(p_academ character varying, p_lograd character varying, p_numen character varying, p_bairr character varying, p_cidade character varying, p_cep character varying, p_telef character varying, p_dataini bigint, p_datafin bigint, p_createdate bigint, p_createuser character varying, p_tenantid integer, p_userid integer, p_atual boolean)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN
	INSERT INTO academia
(
		academ,
		lograd,
		bairr,
		cidade,
		cep,
		telef,
		dataini,
		datafin,
		create_date,
		create_user,
		tenant_id,
		atual)
	VALUES
(

	p_academ,
	p_lograd,
	p_bairr,
	p_cidade,
	p_cep,
	p_telef,
	p_dataini,
	p_datafin,
	p_createdate,
	p_createuser,
	p_tenantid,
	p_atual ) RETURNING cdacad INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_academia(character varying, character varying, character varying, character varying, character varying, character varying, character varying, bigint, bigint, bigint, character varying, integer, integer, boolean)
  OWNER TO qat;


  ----------------------------------

  -- Function: upd_academia(integer, character varying, character varying, integer, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean)

-- DROP FUNCTION upd_academia(integer, character varying, character varying, integer, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean);

CREATE OR REPLACE FUNCTION upd_academia(p_cdacad integer, p_academ character varying, p_lograd character varying, p_numen integer, p_bairr character varying, p_cidade character varying, p_cep character varying, p_telef character varying, p_dataini timestamp with time zone, p_datafin timestamp with time zone, p_createdate timestamp with time zone, p_createuser character varying, p_tenantid integer, p_userid integer, p_atual boolean)
  RETURNS void AS
$BODY$
BEGIN
UPDATE academia
SET
academ =  COALESCE(p_academ,academ),
lograd =  COALESCE(p_lograd,lograd),
bairr =  COALESCE(p_bairr,bairr),
cidade =  COALESCE(p_cidade,cidade),
cep =  COALESCE(p_cep,cep),
telef =  COALESCE(p_telef,telef),
datafin =  COALESCE(p_datafin,datafin),
atual =  COALESCE(p_atual,atual)
WHERE cdacad = p_cdacad;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION upd_academia(integer, character varying, character varying, integer, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean)
  OWNER TO qat;


  -------------------------------------
  -- Function: upd_academia(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean)

-- DROP FUNCTION upd_academia(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean);

CREATE OR REPLACE FUNCTION upd_academia(p_cdacad integer, p_academ character varying, p_lograd character varying, p_numen character varying, p_bairr character varying, p_cidade character varying, p_cep character varying, p_telef character varying, p_dataini timestamp with time zone, p_datafin timestamp with time zone, p_createdate timestamp with time zone, p_createuser character varying, p_tenantid integer, p_userid integer, p_atual boolean)
  RETURNS void AS
$BODY$
BEGIN
UPDATE academia
SET
academ =  COALESCE(p_academ,academ),
lograd =  COALESCE(p_lograd,lograd),
bairr =  COALESCE(p_bairr,bairr),
cidade =  COALESCE(p_cidade,cidade),
cep =  COALESCE(p_cep,cep),
telef =  COALESCE(p_telef,telef),
datafin =  COALESCE(p_datafin,datafin),
atual =  COALESCE(p_atual,atual)
WHERE cdacad = p_cdacad;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION upd_academia(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, timestamp with time zone, timestamp with time zone, timestamp with time zone, character varying, integer, integer, boolean)
  OWNER TO qat;



-----------------------------

  -- Table: exercicio

-- DROP TABLE exercicio;

CREATE TABLE exercicio
(
  cdexerc serial NOT NULL,
  nmexerc character varying(100) NOT NULL,
  dsexerc character varying(150),
  cdgrmusc integer,
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdexerc_pkey PRIMARY KEY (cdexerc),
  CONSTRAINT fk_tenant_cdacad FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE exercicio
  OWNER TO postgres;
---------------------------------------------

  -- Table: foto

-- DROP TABLE foto;

CREATE TABLE foto
(
  cdfoto serial NOT NULL,
  nmfoto character varying(100) NOT NULL,
  lcfoto character varying(150),
  ttfoto character varying(150),
  fototypeenun character varying(20) NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdfoto_pkey PRIMARY KEY (cdfoto),
  CONSTRAINT fk_tenant_cdacad FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE foto
  OWNER TO postgres;

  ------------------------------


CREATE TABLE histuser
(
  cdhistuser serial NOT NULL,
  cdtable integer NOT NULL,
  id integer NOT NULL,,
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdhistuser_pkey PRIMARY KEY (cdhistuser),
  CONSTRAINT fk_tenant_cdhistuser FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE histuser
  OWNER TO qat;

-- Function: ins_comentario(integer, integer, character varying, character varying, integer)

-- DROP FUNCTION ins_comentario(integer, integer, character varying, character varying, integer);

CREATE OR REPLACE FUNCTION ins_comentario(p_cdtable integer, p_id integer, p_texto character varying, p_create_user character varying, p_tenant_id integer)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN

INSERT INTO comentario(
            cdcomentario, cdtable, id, texto, create_date, create_user, tenant_id)
    VALUES ((select last_value from grupomuscular_cdgropomusc_seq), p_cdtable, p_id, p_texto,((SELECT TIMESTAMP 'now')), p_create_user, p_tenant_id) RETURNING cdcomentario INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_comentario(integer, integer, character varying, character varying, integer)
  OWNER TO qat;


  -- Function: ins_curtir(integer, integer, integer, character varying, integer)

-- DROP FUNCTION ins_curtir(integer, integer, integer, character varying, integer);

CREATE OR REPLACE FUNCTION ins_curtir(p_cdtable integer, p_id integer, p_status integer, p_create_user character varying, p_tenant_id integer)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN

INSERT INTO curtir(
            cdcurtir, cdtable, id, status, create_date, create_user, tenant_id)
     VALUES ((select last_value from grupomuscular_cdgropomusc_seq), p_cdtable, p_id, p_status,((SELECT TIMESTAMP 'now')), p_create_user, p_tenant_id) RETURNING cdcurtir INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_curtir(integer, integer, integer, character varying, integer)
  OWNER TO qat;


  -- Function: ins_foto(character varying, character varying, character varying, character varying, integer, integer, character varying, integer)

-- DROP FUNCTION ins_foto(character varying, character varying, character varying, character varying, integer, integer, character varying, integer);

CREATE OR REPLACE FUNCTION ins_foto(p_nmfoto character varying, p_lcfoto character varying, p_ttfoto character varying, p_comentario character varying, p_cdtable integer, p_id integer, p_create_user character varying, p_tenant_id integer)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN

INSERT INTO foto(cdfoto                                           , nmfoto  , lcfoto  , ttfoto  , comentario  , fototypeenun, id  ,create_date             , create_user  , tenant_id)
    VALUES ((select last_value from grupomuscular_cdgropomusc_seq), p_nmfoto, p_lcfoto, p_ttfoto, p_comentario, p_cdtable   , p_id,(SELECT TIMESTAMP 'now'), p_create_user, p_tenant_id) RETURNING cdfoto INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_foto(character varying, character varying, character varying, character varying, integer, integer, character varying, integer)
  OWNER TO postgres;


  -- Function: ins_foto(character varying, character varying, character varying, character varying, integer, integer, character varying, integer)

-- DROP FUNCTION ins_foto(character varying, character varying, character varying, character varying, integer, integer, character varying, integer);

CREATE OR REPLACE FUNCTION ins_foto(p_nmfoto character varying, p_lcfoto character varying, p_ttfoto character varying, p_comentario character varying, p_cdtable integer, p_id integer, p_create_user character varying, p_tenant_id integer)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN

INSERT INTO foto(cdfoto                                           , nmfoto  , lcfoto  , ttfoto  , comentario  , fototypeenun, id  ,create_date             , create_user  , tenant_id)
    VALUES ((select last_value from grupomuscular_cdgropomusc_seq), p_nmfoto, p_lcfoto, p_ttfoto, p_comentario, p_cdtable   , p_id,(SELECT TIMESTAMP 'now'), p_create_user, p_tenant_id) RETURNING cdfoto INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_foto(character varying, character varying, character varying, character varying, integer, integer, character varying, integer)
  OWNER TO postgres;


  -- Function: ins_histuser(integer, integer, integer, character varying, integer)

-- DROP FUNCTION ins_histuser(integer, integer, integer, character varying, integer);

CREATE OR REPLACE FUNCTION ins_histuser(p_cdtable integer, p_id integer, p_acao integer, p_create_user character varying, p_tenant_id integer)
  RETURNS integer AS
$BODY$
DECLARE
		id int;
BEGIN

INSERT INTO histuser(cdtable, id, acao, create_date, create_user, tenant_id)
    VALUES (p_cdtable, p_id, p_acao, (SELECT TIMESTAMP 'now'), p_create_user, p_tenant_id ) RETURNING cdhistuser INTO id;

RETURN id;
END
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ins_histuser(integer, integer, integer, character varying, integer)
  OWNER TO qat;


  -- Table: comentario

-- DROP TABLE comentario;

CREATE TABLE comentario
(
  cdcomentario integer NOT NULL,
  cdtable integer NOT NULL,
  id integer NOT NULL,
  texto integer NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdcomentario_pkey PRIMARY KEY (cdcomentario),
  CONSTRAINT fk_tenant_cdcurtir FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comentario
  OWNER TO qat;
-- Table: curtir

-- DROP TABLE curtir;

CREATE TABLE curtir
(
  cdcurtir integer NOT NULL,
  cdtable integer NOT NULL,
  id integer NOT NULL,
  status integer NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdcurtir_pkeyd PRIMARY KEY (cdcurtir),
  CONSTRAINT fk_tenant_cdcurtir FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE curtir
  OWNER TO qat;


  -- Table: foto

-- DROP TABLE foto;

CREATE TABLE foto
(
  cdfoto integer NOT NULL,
  id integer NOT NULL,
  nmfoto character varying(100) NOT NULL,
  lcfoto character varying(150),
  ttfoto character varying(150),
  comentario character varying(150),
  fototypeenun integer NOT NULL,
  create_date timestamp with time zone,
  create_user character varying(20),
  tenant_id integer,
  CONSTRAINT cdfoto_pkey1 PRIMARY KEY (cdfoto),
  CONSTRAINT fk_tenant_cdacad FOREIGN KEY (tenant_id)
      REFERENCES tenant (tenant_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE foto
  OWNER TO qat;

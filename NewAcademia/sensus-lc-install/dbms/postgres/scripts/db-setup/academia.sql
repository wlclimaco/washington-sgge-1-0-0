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

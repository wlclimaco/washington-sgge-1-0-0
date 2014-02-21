/* $PostgreSQL: pgsql/contrib/tablefunc/tablefunc.sql.in,v 1.12 2007/11/13 04:24:29 momjian Exp $ */

-- Adjust this setting to control where the objects get created.
SET search_path = public;

CREATE OR REPLACE FUNCTION normal_rand(int4, float8, float8)
RETURNS setof float8
AS '$libdir/tablefunc','normal_rand'
LANGUAGE C VOLATILE STRICT;

-- the generic crosstab function:
CREATE OR REPLACE FUNCTION crosstab(text)
RETURNS setof record
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

-- examples of building custom type-specific crosstab functions:
CREATE TYPE tablefunc_crosstab_2 AS
(
	row_name TEXT,
	category_1 TEXT,
	category_2 TEXT
);

CREATE TYPE tablefunc_crosstab_3 AS
(
	row_name TEXT,
	category_1 TEXT,
	category_2 TEXT,
	category_3 TEXT
);

CREATE TYPE tablefunc_crosstab_4 AS
(
	row_name TEXT,
	category_1 TEXT,
	category_2 TEXT,
	category_3 TEXT,
	category_4 TEXT
);

CREATE OR REPLACE FUNCTION crosstab2(text)
RETURNS setof tablefunc_crosstab_2
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION crosstab3(text)
RETURNS setof tablefunc_crosstab_3
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION crosstab4(text)
RETURNS setof tablefunc_crosstab_4
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

-- obsolete:
CREATE OR REPLACE FUNCTION crosstab(text,int)
RETURNS setof record
AS '$libdir/tablefunc','crosstab'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION crosstab(text,text)
RETURNS setof record
AS '$libdir/tablefunc','crosstab_hash'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,int,text)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,int)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text'
LANGUAGE C STABLE STRICT;

-- These 2 take the name of a field to ORDER BY as 4th arg (for sorting siblings)

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,text,int,text)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text_serial'
LANGUAGE C STABLE STRICT;

CREATE OR REPLACE FUNCTION connectby(text,text,text,text,text,int)
RETURNS setof record
AS '$libdir/tablefunc','connectby_text_serial'
LANGUAGE C STABLE STRICT;

//========================================

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
//=================]]]


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


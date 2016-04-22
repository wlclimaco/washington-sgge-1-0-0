 DROP SEQUENCE "PROC_SEQ";
 CREATE SEQUENCE PROC_SEQ  MINVALUE 1 MAXVALUE 2147483647 INCREMENT BY 1 START WITH 15 NOCACHE  ORDER  CYCLE;

 COMMIT;

 DROP TABLE  "PROCEDURE";
 CREATE TABLE  PROCEDURE
    (PROC_ID                           NUMBER(10,0) PRIMARY KEY,
     PROC_CODE                         VARCHAR2(10) NOT NULL,
     PROC_DESC                         VARCHAR2(50)         ,
     PROC_PRICE                        NUMBER(11,2)         ,
     VERSN_LOCK_NUM	                   NUMBER(10,0)         ,
 	 CONSTRAINT unique_proc_code UNIQUE (PROC_CODE)
 	 USING INDEX(CREATE INDEX PROCEDURE_CTX1 ON PROCEDURE (PROC_CODE))
	);

 COMMIT;

 CREATE OR REPLACE TRIGGER PROCEDURE_TR1
	BEFORE INSERT OR UPDATE
	ON PROCEDURE for each row

	BEGIN
	IF INSERTING THEN
		:new.VERSN_LOCK_NUM := 0;
	ELSE
		:new.VERSN_LOCK_NUM := :old.VERSN_LOCK_NUM + 1;
	END IF;
	END;
	/

 ALTER TRIGGER  PROCEDURE_TR1 ENABLE;

 COMMIT;

 DROP TABLE "COUNTY";
 CREATE TABLE COUNTY
    (COUNTY_ID	                         NUMBER(4,0)  PRIMARY KEY,
     COUNTY_DESC                         VARCHAR2(50)  	         ,
     VERSN_LOCK_NUM	                     NUMBER(10,0)
	);

 COMMIT;

 DROP TABLE "DRUG";
 CREATE TABLE DRUG
    (NDC_CODE                         VARCHAR2(10)  PRIMARY KEY,
     NDC_DESC                         VARCHAR2(50)             ,
     VERSN_LOCK_NUM	                  NUMBER(10,0)
	);

 COMMIT;

 DROP TABLE  "DRUG_PRICE";
 CREATE TABLE DRUG_PRICE
    (PARENT_KEY                       VARCHAR2(10) NOT NULL,
     NDC_SEX_IND                      CHAR(1)      NOT NULL,
     NDC_PRICE                        NUMBER(11,2)    ,
     EFFECTIVEDATE					  NUMBER(19)	,
     VERSN_LOCK_NUM	                  NUMBER(10,0)    ,
   	 CONSTRAINT drug_price_pk PRIMARY KEY (PARENT_KEY, NDC_SEX_IND)
    );

 COMMIT;

 insert into PROCEDURE values(1,'PC1', 'PC1DESC',10.0,0);
 insert into PROCEDURE values(2,'PC2', 'PC2DESC',20.0,0);
 insert into COUNTY values(1,'CountyDesc1',0);
 insert into COUNTY values(2,'CountyDesc2',0);
 insert into DRUG values('D123456','Protonix',0);
 insert into DRUG values('D123457','Tylenol',0);
 insert into DRUG_PRICE values('D123456','M',45.00, (to_date('1/1/2012 12:00:00','MM/DD/YYYY HH:MI:SS' ) - to_date('1/01/1970','MM/DD/RRRR'))*86400000 , 0);
 insert into DRUG_PRICE values('D123456','F',55.00, (to_date('12/1/2011 12:00:00','MM/DD/YYYY HH:MI:SS' ) - to_date('1/01/1970','MM/DD/RRRR'))*86400000 , 0);
 insert into DRUG_PRICE values('D123457','M',10.00, (to_date('11/1/2011 12:00:00','MM/DD/YYYY HH:MI:SS' ) - to_date('1/01/1970','MM/DD/RRRR'))*86400000 , 0);
 insert into DRUG_PRICE values('D123457','F',5.00, (to_date('2/1/2012 12:00:00','MM/DD/YYYY HH:MI:SS' ) - to_date('1/01/1970','MM/DD/RRRR'))*86400000 , 0);
 COMMIT;

  DROP TABLE  "procedure";
  CREATE TABLE procedure 
    (PROC_ID                           SERIAL PRIMARY KEY,
     PROC_CODE                         VARCHAR(10) NOT NULL UNIQUE, 
     PROC_DESC                         VARCHAR(50)       ,   
     PROC_PRICE                        NUMERIC(11,2)     ,
     VERSN_LOCK_NUM	                   INTEGER            
	);

  COMMIT; 
   
  DROP TABLE "county";
  CREATE TABLE county  
     (COUNTY_ID                        NUMERIC(4,0) PRIMARY KEY, 
      COUNTY_DESC                      VARCHAR(50)       , 
      VERSN_LOCK_NUM	               INTEGER                    
	 );
	 
  COMMIT;     
   	
  CREATE OR REPLACE FUNCTION procedure_version_number() RETURNS TRIGGER AS $procedure_tr1$
    BEGIN
        IF (TG_OP = 'INSERT') THEN
			new.VERSN_LOCK_NUM := 0;
		ELSE
			new.VERSN_LOCK_NUM := old.VERSN_LOCK_NUM + 1;	
        END IF;
        RETURN new;
    END;
 	$procedure_tr1$ LANGUAGE plpgsql; 
 	
 COMMIT;
	
 CREATE TRIGGER procedure_tr1
	BEFORE INSERT OR UPDATE
	ON PROCEDURE for each row EXECUTE PROCEDURE procedure_version_number();

 COMMIT;
 
 ALTER SEQUENCE procedure_proc_id_seq RESTART WITH 3;

 COMMIT;
	
 DROP TABLE "drug";
 CREATE TABLE drug
    (NDC_CODE                     VARCHAR(10) PRIMARY KEY,  
     NDC_DESC                     VARCHAR(50)            , 
     VERSN_LOCK_NUM	           	  INTEGER                     
	);
   
 COMMIT;
    
 DROP TABLE  "drug_price";
 CREATE TABLE drug_price 
    (PARENT_KEY                      VARCHAR(10) NOT NULL, 
     NDC_SEX_IND                     CHAR(1) 	 NOT NULL,   
     NDC_PRICE                       NUMERIC(11,2)   ,
     EFFECTIVEDATE					 DATE			 ,
     VERSN_LOCK_NUM	                 INTEGER 		 ,
     PRIMARY KEY (PARENT_KEY , NDC_SEX_IND)
    );

 COMMIT;
    
 insert into procedure values(1,'PC1', 'PC1DESC',10.0,0);
 insert into procedure values(2,'PC2', 'PC2DESC',20.0,0);
 insert into county values(1,'CountyDesc1',0);
 insert into county values(2,'CountyDesc2',0);	
 insert into drug values('D123456','Protonix',0);	
 insert into drug values('D123457','Tylenol',0);	
 insert into drug_price values('D123456','M',45.00,'01-Jan-12',0);
 insert into drug_price values('D123456','F',55.00,'01-Dec-11',0);
 insert into drug_price values('D123457','M',10.00,'01-Nov-11',0);
 insert into drug_price values('D123457','F',5.00,'01-Feb-12',0);		
	
 COMMIT;

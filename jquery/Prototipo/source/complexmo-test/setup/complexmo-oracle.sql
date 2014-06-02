DROP SEQUENCE "PERSON_SEQ";
CREATE SEQUENCE "PERSON_SEQ"  MINVALUE 1 MAXVALUE 99999 INCREMENT BY 1 START WITH 3 NOCACHE  ORDER  CYCLE;
commit;

DROP TABLE "PERSON";
CREATE TABLE PERSON
   (ID 					NUMBER(10,0) PRIMARY KEY,
	FIRST_NAME	 		VARCHAR2(25), 
	MIDDLE_NAME  		VARCHAR2(10), 	
	LAST_NAME			VARCHAR2(50), 
	EMPLOYEE_NUMBER 	NUMBER(10,0),
	VERSN_LOCK_NUM	  	NUMBER(10,0)     
   );
COMMIT;

CREATE OR REPLACE TRIGGER PERSON_TR1
	BEFORE INSERT OR UPDATE
	ON PERSON for each row

	BEGIN
	IF INSERTING THEN  
		:new.VERSN_LOCK_NUM := 0;
	ELSE
		:new.VERSN_LOCK_NUM := :old.VERSN_LOCK_NUM + 1;
	END IF;
	END;
	/
	
ALTER TRIGGER  PERSON_TR1 ENABLE;
	
COMMIT;  
   
DROP TABLE "EFT";
CREATE TABLE EFT
   (PARENTKEY 			NUMBER(10,0) PRIMARY KEY, 
	BANK 				VARCHAR2(100), 
	ACCOUNT_NUMBER 		VARCHAR2(16), 
	EFF_START_DATE 		DATE, 
	EFF_END_DATE 		DATE
   );
COMMIT;

DROP TABLE "CONTACT";
CREATE TABLE CONTACT
   (PARENTKEY 			NUMBER(10,0), 
	CONTACT_TYPE 		NUMBER(10,0), 
	PRIORITY 			NUMBER(10,0), 
	VERIFIED 			CHAR(1),	
	EFF_START_DATE 		DATE, 
	EFF_END_DATE 		DATE,
    CONSTRAINT contact_pk PRIMARY KEY (PARENTKEY, CONTACT_TYPE)
   );
COMMIT;

DROP TABLE "ADDRESS";
CREATE TABLE ADDRESS
   (PARENTKEY 		NUMBER(10,0), 
	CONTACT_TYPE 	NUMBER(10,0),    
	ADDRESSLINE1 	VARCHAR2(75),   
	ADDRESSLINE2 	VARCHAR2(75),   	
	CITY 			VARCHAR2(50), 
	STATE 			VARCHAR2(2), 
	COUNTY 			CHAR(2), 
	COUNTRY 		CHAR(2),	
	POSTAL_CODE 	VARCHAR2(9), 
	NOTE 			VARCHAR2(100),
    CONSTRAINT address_pk PRIMARY KEY (PARENTKEY, CONTACT_TYPE)	
   );
COMMIT;
   
DROP TABLE "PHONE";
CREATE TABLE PHONE
   (PARENTKEY 		NUMBER(10,0), 
	CONTACT_TYPE 	NUMBER(10,0), 
	COUNTRY_CODE 	VARCHAR2(3), 	
	AREA_CODE 		VARCHAR2(3), 
	BASE_NUMBER		VARCHAR2(16), 
	EXTENSION 		VARCHAR2(8),
    CONSTRAINT phone_pk PRIMARY KEY (PARENTKEY, CONTACT_TYPE)	
   );
COMMIT;
   
DROP TABLE "EMAIL";
CREATE TABLE EMAIL
   (PARENTKEY 		NUMBER(10,0), 
	CONTACT_TYPE 	NUMBER(10,0),
	EMAILADDRESS	VARCHAR2(150),   
    CONSTRAINT email_pk PRIMARY KEY (PARENTKEY, CONTACT_TYPE)	
   );
COMMIT;

DROP TABLE "COUNTY_COMPLEXMO";
CREATE TABLE COUNTY_COMPLEXMO
   (CODE 		CHAR(2) PRIMARY KEY, 
	DESCRIPTION VARCHAR2(100)
   );
COMMIT;
   
DROP TABLE "STATE";
CREATE TABLE STATE
   (CODE 		CHAR(2) PRIMARY KEY, 
	DESCRIPTION VARCHAR(2100)
   );
COMMIT;

DROP TABLE "COUNTRY";
CREATE TABLE COUNTRY
   (CODE 		CHAR(2) PRIMARY KEY, 
	DESCRIPTION VARCHAR2(100)
   );
COMMIT;
   
DROP TABLE "HELP";
CREATE TABLE HELP
   (SEQUENCE 	NUMBER(10,0) PRIMARY KEY, 	
   	TOPIC 		VARCHAR2(25),
	INFORMATION VARCHAR2(100) 
   );   
COMMIT;
   
insert into country values('US','United States of America');
insert into country values('BR','Brazil');
insert into county_complexmo values('SP','Sarpy');
insert into county_complexmo values('DO','Douglas');
insert into state values('NE','Nebraska');
insert into state values('FL','Florida');
insert into person values(1,'Richard','Allen', 'Barndt',100,0);
insert into person values(2,'Naomi','Sue Lee', 'Barndt',200,0);
insert into eft values(1,'CFCU1',123456,'1-Jan-11','30-Dec-52');
insert into eft values(2,'CFCU2',223456,'1-Jan-11','30-Dec-52');
insert into contact values(1,1,1,'Y','1-Jan-09','30-Dec-52');
insert into contact values(1,2,1,'Y','1-Jan-12','30-Dec-52');
insert into contact values(1,3,3,'N','1-Jan-12','30-Dec-52');
insert into contact values(1,4,2,'Y','1-Jan-12','30-Dec-52');
insert into contact values(1,5,1,'Y','1-Jan-12','30-Dec-52');
insert into contact values(1,6,2,'Y','1-Jan-12','30-Dec-52');
insert into contact values(2,3,2,'N','1-Jan-12','30-Dec-52');
insert into address values(1,3,'1122 Boogie Boogie Ave','','Omaha','NE','SP','US','68136','Test Note Rich');
insert into address values(1,6,'222 South 15th Street','Suite 1404','Omaha','NE','DO','US','68102','14th Floor South Building');
insert into address values(2,3,'1122 Boogie Boogie Ave','','Omaha','NE','SP','US','68136','Test Note Naomi');
insert into phone values(1,1,'001','678','3614388','');
insert into phone values(1,4,'001','402','4919200','');
insert into email values(1,2,'rich_barndt@yahoo.com');
insert into email values(1,5,'rich_barndt@qat.com');

COMMIT;
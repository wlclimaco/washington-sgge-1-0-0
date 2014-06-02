DROP TABLE  "person";
CREATE TABLE person
   (ID 					SERIAL PRIMARY KEY,
	FIRST_NAME	 		VARCHAR(25), 
	MIDDLE_NAME  		VARCHAR(10), 	
	LAST_NAME			VARCHAR(50), 
	EMPLOYEE_NUMBER 	INTEGER,
	VERSN_LOCK_NUM	  	INTEGER     
   );
COMMIT;

CREATE OR REPLACE FUNCTION person_version_number() RETURNS TRIGGER AS $person_tr1$
   BEGIN
       	IF (TG_OP = 'INSERT') THEN
			new.VERSN_LOCK_NUM := 0;
		ELSE
			new.VERSN_LOCK_NUM := old.VERSN_LOCK_NUM + 1;	
        END IF;
        RETURN new;
   END;
 $person_tr1$ LANGUAGE plpgsql; 
 	
COMMIT;
	
CREATE TRIGGER person_tr1
  BEFORE INSERT OR UPDATE
	ON PERSON for each row EXECUTE PROCEDURE person_version_number();

COMMIT;

ALTER SEQUENCE person_id_seq RESTART WITH 3;

COMMIT;
 
DROP TABLE "eft";
CREATE TABLE eft
   (PARENTKEY 			INTEGER PRIMARY KEY, 
	BANK 				VARCHAR(100), 
	ACCOUNT_NUMBER 		VARCHAR(16), 
	EFF_START_DATE 		DATE, 
	EFF_END_DATE 		DATE
   );
COMMIT;

DROP TABLE "contact";
CREATE TABLE contact
   (PARENTKEY 			INTEGER, 
	CONTACT_TYPE 		INTEGER, 
	PRIORITY 			INTEGER, 
	VERIFIED 			CHAR(1),	
	EFF_START_DATE 		DATE, 
	EFF_END_DATE 		DATE,
    PRIMARY KEY (PARENTKEY, CONTACT_TYPE)
   );
COMMIT;

DROP TABLE "address";
CREATE TABLE address
   (PARENTKEY 		INTEGER, 
	CONTACT_TYPE 	INTEGER,    
	ADDRESSLINE1 	VARCHAR(75),   
	ADDRESSLINE2 	VARCHAR(75),   	
	CITY 			VARCHAR(50), 
	STATE 			VARCHAR(2), 
	COUNTY 			CHAR(2), 
	COUNTRY 		CHAR(2),	
	POSTAL_CODE 	VARCHAR(9), 
	NOTE 			VARCHAR(100),
    PRIMARY KEY (PARENTKEY, CONTACT_TYPE)	
   );
COMMIT;
   
DROP TABLE "phone";
CREATE TABLE phone
   (PARENTKEY 		INTEGER, 
	CONTACT_TYPE 	INTEGER, 
	COUNTRY_CODE 	VARCHAR(3), 	
	AREA_CODE 		VARCHAR(3), 
	BASE_NUMBER		VARCHAR(16), 
	EXTENSION 		VARCHAR(8),
    PRIMARY KEY (PARENTKEY, CONTACT_TYPE)	
   );
COMMIT;
   
DROP TABLE "email";
CREATE TABLE email
   (PARENTKEY 		INTEGER, 
	CONTACT_TYPE 	INTEGER,
	EMAILADDRESS	VARCHAR(150),   
    PRIMARY KEY (PARENTKEY, CONTACT_TYPE)	
   );
COMMIT;

DROP TABLE "county_complexmo";
CREATE TABLE county_complexmo
   (CODE 		CHAR(2) PRIMARY KEY, 
	DESCRIPTION VARCHAR(100)
   );
COMMIT;
   
DROP TABLE "state";
CREATE TABLE state
   (CODE 		CHAR(2) PRIMARY KEY, 
	DESCRIPTION VARCHAR(100)
   );
COMMIT;

DROP TABLE "country";
CREATE TABLE country
   (CODE 		CHAR(2) PRIMARY KEY, 
	DESCRIPTION VARCHAR(100)
   );
COMMIT;
   
DROP TABLE "help";
CREATE TABLE help
   (SEQUENCE 	INTEGER PRIMARY KEY, 	
   	TOPIC 		VARCHAR(25),
	INFORMATION VARCHAR(100) 
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
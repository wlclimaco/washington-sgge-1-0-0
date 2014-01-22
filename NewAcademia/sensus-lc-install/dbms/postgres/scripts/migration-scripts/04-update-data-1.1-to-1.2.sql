 -------------------------------------------------------------------------------------------------------------------------
 -- VERSION 1.1.0
 -------------------------------------------------------------------------------------------------------------------------

INSERT INTO light_filter_type (light_filter_type_id,name,create_date,create_user) VALUES (1,'Lamp Type',CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_type (light_filter_type_id,name,create_date,create_user) VALUES (2,'Wattage Rating',CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_type (light_filter_type_id,name,create_date,create_user) VALUES (3,'Lamp Type Wattage Dimmable',CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_type (light_filter_type_id,name,create_date,create_user) VALUES (4,'Housing',CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_type (light_filter_type_id,name,create_date,create_user) VALUES (5,'Dimmable',CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_type (light_filter_type_id,name,create_date,create_user) VALUES (6,'Housing Color',CURRENT_TIMESTAMP,'sysuser');

-- LAMP TYPE FILTER VALUES
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Induction', 1,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('LED', 1,CURRENT_TIMESTAMP,'sysuser');

-- WATTAGE RATING VALUES
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('40W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('60W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('75W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('80W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('100W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('105W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('120W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('150W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('200W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('250W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('300W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('400W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('28W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('56W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('84W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('112W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('140W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('168W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('196W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('224W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('252W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('300W',2,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('300W',2,CURRENT_TIMESTAMP,'sysuser');

-- Lamp Type Wattage Dimmable Values
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('40W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('60W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('80W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('100W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('120W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('150W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('200W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('250W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('300W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('400W Induction', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('28W LED',3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('56W LED',3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('75W LED',3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('84W LED',3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('105W LED', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('112W LED', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('140W LED', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('168W LED', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('196W LED', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('224W LED', 3,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('252W LED', 3,CURRENT_TIMESTAMP,'sysuser');

-- HOUSING Values
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Street', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Decorative', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('High Bay',4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Low Bay',4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Tunnel', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Shoebox', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Flood', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Wall Pack', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Post Top', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Shepherd', 4,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('252W LED', 4,CURRENT_TIMESTAMP,'sysuser');

-- DIMMABLE Values
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('TRUE',5,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('FALSE',5,CURRENT_TIMESTAMP,'sysuser');

-- HOUSING Color Values
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Gray',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Black',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Silver',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Bronze',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('White',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Off-White / Cream',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Green',6,CURRENT_TIMESTAMP,'sysuser');
INSERT INTO light_filter_value (filter_value,light_filter_type_id,create_date,create_user) VALUES ('Aluminum',6,CURRENT_TIMESTAMP,'sysuser');

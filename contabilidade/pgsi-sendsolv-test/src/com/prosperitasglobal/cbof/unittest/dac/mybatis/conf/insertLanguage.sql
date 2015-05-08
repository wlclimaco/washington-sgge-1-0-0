--Disable the constraints
ALTER TABLE employment_information NOCHECK CONSTRAINT ALL

DELETE FROM person WHERE FK_preferred_language_id IS NOT NULL;
DELETE FROM language

--Active the constraints
ALTER TABLE employment_information CHECK CONSTRAINT ALL

INSERT INTO LANGUAGE VALUES ('Portuguese'),
							('English'),
							('Chinese'),
							('German');
--Disable the constraints
ALTER TABLE person_security_answer NOCHECK CONSTRAINT ALL

--Delete all records
DELETE FROM person_security_answer
DELETE FROM security_question

--Active the constraints
ALTER TABLE person_security_answer CHECK CONSTRAINT ALL

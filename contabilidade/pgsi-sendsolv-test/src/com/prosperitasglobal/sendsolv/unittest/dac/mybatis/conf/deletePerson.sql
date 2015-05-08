--Disable the constraints
ALTER TABLE money_transfer_batch NOCHECK CONSTRAINT ALL
ALTER TABLE employment_information NOCHECK CONSTRAINT ALL
ALTER TABLE person NOCHECK CONSTRAINT ALL

--Delete all records
DELETE FROM money_transfer_batch
DELETE FROM person
DELETE FROM employment_information

--Active the constraints
ALTER TABLE money_transfer_batch CHECK CONSTRAINT ALL
ALTER TABLE employment_information CHECK CONSTRAINT ALL
ALTER TABLE person CHECK CONSTRAINT ALL
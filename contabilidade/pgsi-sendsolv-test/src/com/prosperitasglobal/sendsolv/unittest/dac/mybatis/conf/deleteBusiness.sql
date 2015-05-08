
--Disable the constraints
ALTER TABLE business_sdn_history NOCHECK CONSTRAINT ALL
ALTER TABLE money_transfer_batch NOCHECK CONSTRAINT ALL
ALTER TABLE address NOCHECK CONSTRAINT ALL
ALTER TABLE person NOCHECK CONSTRAINT ALL
ALTER TABLE product_plan_applicability NOCHECK CONSTRAINT ALL
ALTER TABLE currency_purchase NOCHECK CONSTRAINT ALL
ALTER TABLE payer NOCHECK CONSTRAINT ALL
ALTER TABLE product_plan NOCHECK CONSTRAINT ALL
--ALTER TABLE state_province_region NOCHECK CONSTRAINT ALL
ALTER TABLE business_person NOCHECK CONSTRAINT ALL
ALTER TABLE business_note NOCHECK CONSTRAINT ALL
ALTER TABLE business_document NOCHECK CONSTRAINT ALL
ALTER TABLE business_contact NOCHECK CONSTRAINT ALL
ALTER TABLE business NOCHECK CONSTRAINT ALL
ALTER TABLE employment_information NOCHECK CONSTRAINT ALL

--Delete all records
DELETE FROM business_sdn_history;
DELETE FROM money_transfer_batch;
DELETE FROM address;
DELETE FROM person;
DELETE FROM product_plan_applicability;
DELETE FROM product_plan;
DELETE FROM currency_purchase;
DELETE FROM payer;
--DELETE FROM state_province_region;
DELETE FROM business_person;
DELETE FROM business_note;
DELETE FROM business_document;
DELETE FROM business_contact;
DELETE FROM business

--Active the constraints
ALTER TABLE business_sdn_history CHECK CONSTRAINT ALL
ALTER TABLE money_transfer_batch CHECK CONSTRAINT ALL
ALTER TABLE address CHECK CONSTRAINT ALL
ALTER TABLE person CHECK CONSTRAINT ALL
ALTER TABLE product_plan_applicability CHECK CONSTRAINT ALL
ALTER TABLE currency_purchase CHECK CONSTRAINT ALL
ALTER TABLE payer CHECK CONSTRAINT ALL
ALTER TABLE product_plan CHECK CONSTRAINT ALL
--ALTER TABLE state_province_region CHECK CONSTRAINT ALL
ALTER TABLE business_person CHECK CONSTRAINT ALL
ALTER TABLE business_note CHECK CONSTRAINT ALL
ALTER TABLE business_document CHECK CONSTRAINT ALL
ALTER TABLE business_contact CHECK CONSTRAINT ALL
ALTER TABLE business CHECK CONSTRAINT ALL
ALTER TABLE employment_information CHECK CONSTRAINT ALL
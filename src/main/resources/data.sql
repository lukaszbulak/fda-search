/* sample brands, models, parts and service_actions */

INSERT INTO drug(APPLICATION_NUMBER) VALUES ('BLA751');
INSERT INTO DRUG_MANUFACTURER_NAME(DRUG_APPLICATION_NUMBER, MANUFACTURER_NAME) VALUES ('BLA751', 'Gsk');
INSERT INTO drug_product_number(DRUG_APPLICATION_NUMBER   , product_number ) VALUES ('BLA751', '001');
INSERT INTO drug_product_number(DRUG_APPLICATION_NUMBER   , product_number ) VALUES ('BLA751', '003');
INSERT INTO drug_substance_name(DRUG_APPLICATION_NUMBER   , substance_name ) VALUES ('BLA751', 'paracetamol');

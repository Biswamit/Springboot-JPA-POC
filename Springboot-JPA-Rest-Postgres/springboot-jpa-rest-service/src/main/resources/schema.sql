-- DROP DATABASE "springboot-jpa-rest-db" WITH (FORCE);
-- CREATE DATABASE "springboot-jpa-rest-db"
--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
SET enable_seqscan = ON;
DROP TABLE IF EXISTS oto_address_table;
DROP TABLE IF EXISTS oto_employee_table;
CREATE TABLE IF NOT EXISTS oto_employee_table  (
	auto_id BIGSERIAL NOT NULL,
	employee_id uuid NOT NULL DEFAULT uuid_generate_v4(),
	employee_fname varchar(50) NOT NULL,
	employee_lname varchar(50) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_employee_table_pkey PRIMARY KEY (auto_id),
	CONSTRAINT oto_employee_table_eid_key UNIQUE (employee_id)
);
--DROP TABLE IF EXISTS oto_address_table;
CREATE TABLE IF NOT EXISTS oto_address_table  (
	auto_id INT NOT NULL,
	address varchar(50) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_address_table_pkey PRIMARY KEY (auto_id),
	FOREIGN KEY (auto_id) REFERENCES oto_employee_table(auto_id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- DROP DATABASE "springboot-jpa-rest-db" WITH (FORCE);
-- CREATE DATABASE "springboot-jpa-rest-db"
--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
SET enable_seqscan = ON;
------------------------------------------------------------------------------------------------------------------------
-- Unidirectional - C2P - Shared Primary Key with Address keeping auto_id as foreign references
------------------------------------------------------------------------------------------------------------------------
-- Shared Primary Key between Employee and Address
DROP TABLE IF EXISTS oto_c2p_uni_spk_address_table CASCADE;
DROP TABLE IF EXISTS oto_c2p_uni_spk_employee_table;
CREATE TABLE IF NOT EXISTS oto_c2p_uni_spk_employee_table  (
	auto_id BIGSERIAL NOT NULL,
	employee_id uuid NOT NULL DEFAULT uuid_generate_v4(),
	employee_fname varchar(50) NOT NULL,
	employee_lname varchar(50) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_c2p_uni_spk_employee_table_pkey PRIMARY KEY (auto_id),
	CONSTRAINT oto_c2p_uni_spk_employee_table_eid_key UNIQUE (employee_id)
);
CREATE TABLE IF NOT EXISTS oto_c2p_uni_spk_address_table  (
	auto_id INT NOT NULL,
	location varchar(100) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_c2p_uni_spk_address_table_pkey PRIMARY KEY (auto_id),
	FOREIGN KEY (auto_id) REFERENCES oto_c2p_uni_spk_employee_table(auto_id) ON UPDATE CASCADE ON DELETE CASCADE
);
------------------------------------------------------------------------------------------------------------------------
-- Unidirectional - P2C - Shared Primary Key with Address keeping auto_id as foreign references
------------------------------------------------------------------------------------------------------------------------
-- Shared Primary Key between Employee and Address
DROP TABLE IF EXISTS oto_p2c_uni_spk_address_table CASCADE;
DROP TABLE IF EXISTS oto_p2c_uni_spk_employee_table;
CREATE TABLE IF NOT EXISTS oto_p2c_uni_spk_employee_table  (
	auto_id BIGSERIAL NOT NULL,
	employee_id uuid NOT NULL DEFAULT uuid_generate_v4(),
	employee_fname varchar(50) NOT NULL,
	employee_lname varchar(50) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_p2c_uni_spk_employee_table_pkey PRIMARY KEY (auto_id),
	CONSTRAINT oto_p2c_uni_spk_employee_table_eid_key UNIQUE (employee_id)
);
CREATE TABLE IF NOT EXISTS oto_p2c_uni_spk_address_table  (
	auto_id INT NOT NULL,
	location varchar(100) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_p2c_uni_spk_address_table_pkey PRIMARY KEY (auto_id),
	FOREIGN KEY (auto_id) REFERENCES oto_p2c_uni_spk_employee_table(auto_id) ON UPDATE NO ACTION ON DELETE CASCADE
);
------------------------------------------------------------------------------------------------------------------------
-- Bidirectional - P2C - Shared Primary Key with Address keeping auto_id as foreign references
------------------------------------------------------------------------------------------------------------------------
-- Shared Primary Key between Employee and Address
DROP TABLE IF EXISTS oto_p2c_bi_spk_address_table CASCADE;
DROP TABLE IF EXISTS oto_p2c_bi_spk_employee_table;
CREATE TABLE IF NOT EXISTS oto_p2c_bi_spk_employee_table  (
	auto_id BIGSERIAL NOT NULL,
	employee_id uuid NOT NULL DEFAULT uuid_generate_v4(),
	employee_fname varchar(50) NOT NULL,
	employee_lname varchar(50) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_p2c_bi_spk_employee_table_pkey PRIMARY KEY (auto_id),
	CONSTRAINT oto_p2c_bi_spk_employee_table_eid_key UNIQUE (employee_id)
);
CREATE TABLE IF NOT EXISTS oto_p2c_bi_spk_address_table  (
	auto_id INT NOT NULL,
	location varchar(100) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_p2c_bi_spk_address_table_pkey PRIMARY KEY (auto_id),
	FOREIGN KEY (auto_id) REFERENCES oto_p2c_bi_spk_employee_table(auto_id) ON UPDATE NO ACTION ON DELETE CASCADE
);
------------------------------------------------------------------------------------------------------------------------
-- P2C - No Shared Primary Key. However Parent Employee keeping Address auto_id references
------------------------------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS oto_p2c_bi_pfk_employee_table CASCADE;
DROP TABLE IF EXISTS oto_p2c_bi_pfk_address_table;
CREATE TABLE IF NOT EXISTS oto_p2c_bi_pfk_address_table  (
	auto_id BIGSERIAL NOT NULL,
	location varchar(100) NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_p2c_bi_pfk_address_table_pkey PRIMARY KEY (auto_id)
);
CREATE TABLE IF NOT EXISTS oto_p2c_bi_pfk_employee_table  (
	auto_id BIGSERIAL NOT NULL,
	employee_id uuid NOT NULL DEFAULT uuid_generate_v4(),
	employee_fname varchar(50) NOT NULL,
	employee_lname varchar(50) NOT NULL,
	address_id INT NOT NULL,
	created_time timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamptz NULL,
	CONSTRAINT oto_p2c_bi_pfk_employee_table_pkey PRIMARY KEY (auto_id),
	CONSTRAINT oto_p2c_bi_pfk_employee_table_eid_key UNIQUE (employee_id),
	FOREIGN KEY (address_id) REFERENCES oto_p2c_bi_pfk_address_table(auto_id) ON UPDATE CASCADE ON DELETE CASCADE
);


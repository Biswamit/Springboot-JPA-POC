/*
O2OEmployee Uni-C2P-Shared Primary Key Table
*/
INSERT INTO oto_c2p_uni_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Raj', 'Sarkar');
INSERT INTO oto_c2p_uni_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Rahul', 'Roy');
INSERT INTO oto_c2p_uni_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Sameer', 'Dev');

/*
O2OAddress Uni-C2P-Shared Primary Key Table
*/
INSERT INTO oto_c2p_uni_spk_address_table(AUTO_ID,LOCATION) VALUES(1, 'Pacific Palisades,LA');
INSERT INTO oto_c2p_uni_spk_address_table(AUTO_ID,LOCATION) VALUES(2, 'Beverly Hills,LA');
INSERT INTO oto_c2p_uni_spk_address_table(AUTO_ID,LOCATION) VALUES(3, 'Orchid Avenue,LA');


/*
O2OEmployee Uni-P2C-Shared Primary Key Table
*/
INSERT INTO oto_p2c_uni_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Raj', 'Sarkar');
INSERT INTO oto_p2c_uni_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Rahul', 'Roy');
INSERT INTO oto_p2c_uni_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Sameer', 'Dev');

/*
O2OAddress Uni-P2C-Shared Primary Key Table
*/
INSERT INTO oto_p2c_uni_spk_address_table(AUTO_ID,LOCATION) VALUES(1, 'Pacific Palisades,LA');
INSERT INTO oto_p2c_uni_spk_address_table(AUTO_ID,LOCATION) VALUES(2, 'Beverly Hills,LA');
INSERT INTO oto_p2c_uni_spk_address_table(AUTO_ID,LOCATION) VALUES(3, 'Orchid Avenue,LA');

/*
O2OEmployee Bi-P2C-Shared Primary Key Table
*/
INSERT INTO oto_p2c_bi_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Raj', 'Sarkar');
INSERT INTO oto_p2c_bi_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Rahul', 'Roy');
INSERT INTO oto_p2c_bi_spk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME) VALUES('Sameer', 'Dev');

/*
O2OAddress Bi-P2C-Shared Primary Key Table
*/
INSERT INTO oto_p2c_bi_spk_address_table(AUTO_ID,LOCATION) VALUES(1, 'Pacific Palisades,LA');
INSERT INTO oto_p2c_bi_spk_address_table(AUTO_ID,LOCATION) VALUES(2, 'Beverly Hills,LA');
INSERT INTO oto_p2c_bi_spk_address_table(AUTO_ID,LOCATION) VALUES(3, 'Orchid Avenue,LA');

/*
O2OAddress Parent FK Table
*/
INSERT INTO oto_p2c_bi_pfk_address_table(LOCATION) VALUES('Pacific Palisades,LA');
INSERT INTO oto_p2c_bi_pfk_address_table(LOCATION) VALUES('Beverly Hills,LA');
INSERT INTO oto_p2c_bi_pfk_address_table(LOCATION) VALUES('Orchid Avenue,LA');

/*
O2OEmployee Parent FK Key Table
*/
INSERT INTO oto_p2c_bi_pfk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME,ADDRESS_ID) VALUES('Raj', 'Sarkar',1);
INSERT INTO oto_p2c_bi_pfk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME,ADDRESS_ID) VALUES('Rahul', 'Roy',2);
INSERT INTO oto_p2c_bi_pfk_employee_table(EMPLOYEE_FNAME, EMPLOYEE_LNAME,ADDRESS_ID) VALUES('Sameer', 'Dev',3);



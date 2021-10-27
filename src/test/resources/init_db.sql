 CREATE TABLE cart (
	cart_number varchar(60) NOT NULL,
	customer_name varchar(20) NOT NULL,
	total_amount int4 NOT NULL,
	current_version int4 NOT NULL,
	created_by varchar(60) NOT NULL,
	created_date timestamp NOT NULL,
	last_modified_by varchar(60) NOT NULL,
	last_modified_date timestamp NOT NULL
);  
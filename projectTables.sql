CREATE TABLE ACCOUNTS(username Varchar(30) NOT NULL,
hashed_password Varchar(30) NOT NULL,
first_name Varchar(30) NOT NULL,
last_name Varchar(30) NOT NULL,
email Varchar(30),
address Varchar(30) NOT NULL,
phone_number Varchar(30) NOT NULL,
province Varchar(30) NOT NULL,
country Varchar(30) NOT NULL,
billing_address Varchar(30) NOT NULL,
postal_code Varchar(30) NOT NULL,
permissions Enum("Customer", "admin") NOT NULL,
PRIMARY KEY(username));

CREATE TABLE ITEMS(item_iD varChar(10) NOT NULL,
item_name varChar(30) NOT NULL,
category varChar(30) NOT NULL,
description varChar(100) NOT NULL,
ammount_in_stock INT NOT NULL,
price double NOT NULL,
futureAvailability Date,
PRIMARY KEY(item_iD ));

CREATE TABLE ORDERS(order_id VARCHAR(20) NOT NULL,
customer_user_name VARCHAR(10) NOT NULL,
order_date DATE NOT NULL,
order_time TIME NOT NULL,
is_fulfilled BOOLEAN NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY(customer_user_name) REFERENCES  ACCOUNTS(username));

CREATE TABLE ORDER_CONTENT(order_id VARCHAR(10) NOT NULL,
item_iD VARCHAR(10) NOT NULL,
Quantity INT NOT NULL,
FOREIGN KEY(order_id) REFERENCES ORDERS(order_id),
FOREIGN KEY(item_iD) REFERENCES ITEMS(item_iD));

CREATE TABLE CURRENT_CART(costomer_username VARCHAR(10) NOT NULL,
item_id VARCHAR(10) NOT NULL,
Quantity INT NOT NULL,
FOREIGN KEY(costomer_username) REFERENCES ACCOUNTS(username),
FOREIGN KEY(item_iD) REFERENCES ITEMS(item_iD));












CREATE TABLE ACCOUNTS (username Varchar(30) NOT NULL,
hashed_password CHAR(128) NOT NULL, salt Varchar(32),
first_name Varchar(30) NOT NULL,
last_name Varchar(30) NOT NULL,
email Varchar(30),
address Varchar(30) NOT NULL,
phone_number Varchar(30) NOT NULL,
province Varchar(30) NOT NULL,
country Varchar(30) NOT NULL,
billing_address Varchar(30) NOT NULL,
postal_code Varchar(30) NOT NULL,
permissions Enum("Customer", "Admin") NOT NULL, 
PRIMARY KEY(username));


CREATE TABLE ITEMS(item_iD varChar(10) NOT NULL,
item_name varChar(30) NOT NULL,
category varChar(30) NOT NULL,
description varChar(100) NOT NULL, 
brand varChar(20) NOT NULL,
ammount_in_stock INT NOT NULL,
price double NOT NULL,
futureAvailability Date, 
image_path Varchar(50) NOT NULL,
PRIMARY KEY(item_iD ));

CREATE TABLE ORDERS(order_id INT NOT NULL AUTO_INCREMENT,
customer_user_name VARCHAR(10) NOT NULL,
order_date DATE NOT NULL,
order_time TIME NOT NULL,
is_fulfilled BOOLEAN NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY(customer_user_name) REFERENCES  ACCOUNTS(username));

CREATE TABLE ORDER_CONTENT(order_id INT NOT NULL,
item_iD VARCHAR(10) NOT NULL,
Quantity INT NOT NULL,
FOREIGN KEY(order_id) REFERENCES ORDERS(order_id),
FOREIGN KEY(item_iD) REFERENCES ITEMS(item_iD));

CREATE TABLE CURRENT_CART(costomer_username VARCHAR(10) NOT NULL,
item_id VARCHAR(10) NOT NULL,
Quantity INT NOT NULL,
FOREIGN KEY(costomer_username) REFERENCES ACCOUNTS(username),
FOREIGN KEY(item_iD) REFERENCES ITEMS(item_iD));

INSERT INTO `ITEMS` (`item_iD`, `item_name`, `category`, `description`, `ammount_in_stock`, `price`, `image_path`, `Brand`) VALUES ('456', 'Apple', 'Fruit', 'A red thing you eat', '1000', '2', '/asset/apple.jpg', 'FruitInc');
INSERT INTO `ITEMS` (`item_iD`, `item_name`, `category`, `description`, `ammount_in_stock`, `price`, `image_path`, `Brand`) VALUES ('563', 'Moscow Mule', 'Alchol', 'made form vodka, ginger, lime and a bit of mint', '400', '2.75', '/asset/moscowMule.jpeg', 'RussiaInc');
INSERT INTO `ITEMS` (`item_iD`, `item_name`, `category`, `description`, `ammount_in_stock`, `price`, `image_path`, `Brand`) VALUES ('567', 'Orange', 'Fruit', 'A food named after a colour', '1000', '3', '/asset/orange.jpg', 'FruitInc');
INSERT INTO `ITEMS` (`item_iD`, `item_name`, `category`, `description`, `ammount_in_stock`, `price`, `image_path`, `Brand`) VALUES ('678', 'Beer', 'Alchol', 'A drink to make you stupider', '1000', '2.75', '/asset/beer.jpg', 'Corona');
INSERT INTO `ITEMS` (`item_iD`, `item_name`, `category`, `description`, `ammount_in_stock`, `price`, `image_path`, `Brand`) VALUES ('890', 'Bread', 'Weat', 'A thing for sandwiches', '1000', '5', '/asset/bread.jpg', 'WheatInc');




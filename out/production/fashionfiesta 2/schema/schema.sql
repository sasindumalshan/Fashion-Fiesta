DROP DATABASE fashionfiesta;
CREATE DATABASE fashionfiesta;
USE fashionfiesta;

CREATE TABLE employee(
                         employee_id VARCHAR(20) PRIMARY KEY,
                         street TEXT NOT NULL,
                         city TEXT NOT NULL,
                         lane TEXT,
                         role ENUM('Admin','Cashier','Salsmen','Other'),
                         first_name VARCHAR(20) NOT NULL ,
                         last_name VARCHAR(20) NOT NULL ,
                         contact_number VARCHAR(18) UNIQUE NOT NULL

);

CREATE TABLE employee_attendance(
                                    employee_id VARCHAR(20),
                                    attendance_id VARCHAR(20) PRIMARY KEY ,
                                    time TIME,
                                    date DATE,
                                    CONSTRAINT FOREIGN KEY employee_attendance(employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE salary(
                       employee_id VARCHAR(20),
                       salary_id VARCHAR(20) PRIMARY KEY ,
                       date DATE,
                       salary DECIMAL(5,2) NOT NULL ,
                       employee_attandance_count INT,
                       CONSTRAINT FOREIGN KEY salary(employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE user(
                     employee_id VARCHAR(20),
                     user_name VARCHAR(12),
                     password VARCHAR(12),
                     role ENUM('Admin','cashier'),
                     CONSTRAINT FOREIGN KEY admin (employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE customer(
                         customer_id VARCHAR(20) PRIMARY KEY ,
                         first_name VARCHAR(20),
                         last_name VARCHAR(20),
                         street TEXT NOT NULL ,
                         city TEXT NOT NULL ,
                         lane TEXT,
                         contact_number VARCHAR(18) UNIQUE NOT NULL
                     /*CUSTOMER ID*/
);


CREATE TABLE customer_order(
            customer_id VARCHAR(20),
            customer_order_id VARCHAR(15) PRIMARY KEY,
            customer_order_date DATE,
            customer_order_time TIME,
            payment DECIMAL(11,2),
            CONSTRAINT FOREIGN KEY customer_order(customer_id) REFERENCES customer(customer_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE item(
                     item_id VARCHAR(20) PRIMARY KEY ,
                     item_name VARCHAR(20),
                     quantity INT(15),
                     price DECIMAL ,
                     model_color VARCHAR(20),
                     category VARCHAR(25)
);

CREATE TABLE customer_order_details(
                                       customer_order_id VARCHAR(15),
                                       item_id VARCHAR(20),
                                       quantity INT NOT NULL ,
                                       price DECIMAL,
    CONSTRAINT FOREIGN KEY (customer_order_id) REFERENCES customer_order(customer_order_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY ( item_id) REFERENCES item(item_id)  ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE supplier(
                         supplier_id VARCHAR(20) PRIMARY KEY ,
                         first_name VARCHAR(20),
                         last_name VARCHAR(20),
                         street TEXT NOT NULL ,
                         city TEXT NOT NULL ,
                         lane TEXT,
                         contact_number VARCHAR(18) UNIQUE NOT NULL

);

CREATE TABLE supplier_order(
                               supplier_id VARCHAR(20),
                               supplier_order_id VARCHAR(15),
                               supplier_order_date DATE,
                               supplier_order_time TIME,
                               payment DECIMAL(11,2),
                               CONSTRAINT PRIMARY KEY (supplier_order_id),
                               CONSTRAINT FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON UPDATE CASCADE ON DELETE CASCADE
/*              CONSTRAINT FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON UPDATE CASCADE ON DELETE CASCADE
ITEM ID*/


);

CREATE TABLE supplier_order_details(
    supplier_order_id VARCHAR(20),
    item_id VARCHAR(20) ,
    quantity int,
    price DECIMAL,
    CONSTRAINT PRIMARY KEY (supplier_order_id,item_id),
    CONSTRAINT FOREIGN KEY supplier_order_details(supplier_order_id) REFERENCES supplier_order(supplier_order_id) ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT FOREIGN KEY supplier_order_details(item_id) REFERENCES item(item_id)ON UPDATE CASCADE ON DELETE CASCADE

);


SELECT employee_id , first_name,last_name,date,time from employee_attendance as a inner join employee e on employee_id = employee_id



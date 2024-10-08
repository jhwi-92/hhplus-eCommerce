CREATE TABLE IF NOT EXISTS USERS (
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(255),
POINT INTEGER,
CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS PRODUCT (
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(255),
PRICE INTEGER,
QUANTITY INTEGER,
CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS PAYMENT (
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
ORDER_ID BIGINT,
PRICE INTEGER,
STATUS VARCHAR(255),
CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ORDER_HISTORY (
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
ORDER_ID BIGINT,
PRODUCT_ID BIGINT,
PRICE INTEGER,
STATUS VARCHAR(255),
YYYY_MM_DD VARCHAR(8),
CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ORDERS (
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
USER_ID BIGINT,
PRODUCT_ID BIGINT,
QUANTITY INTEGER,
PRICE INTEGER,
STATUS VARCHAR(255),
CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS CART (
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
USER_ID BIGINT,
PRODUCT_ID BIGINT,
QUANTITY INTEGER,
CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_order_history_date_product
ON ORDER_HISTORY(YYYY_MM_DD, PRODUCT_ID);
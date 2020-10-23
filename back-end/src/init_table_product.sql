CREATE TABLE product (
    id          BIGINT,
    name        VARCHAR(60) NOT NULL UNIQUE,
    unit        VARCHAR(20) NOT NULL,
    price       INTEGER NOT NULL,
    url         VARCHAR(500) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);
INSERT INTO product VALUES (1, '可乐', '瓶', 1, 'https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg');
INSERT INTO product VALUES (2, '雪碧', '听', 2, 'https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg');

CREATE TABLE product_id_seq (
    next_val    BIGINT
);
INSERT INTO product_id_seq VALUES (52);

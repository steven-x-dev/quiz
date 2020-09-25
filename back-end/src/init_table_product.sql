CREATE TABLE product (
    id          BIGINT,
    name        VARCHAR(60) NOT NULL,
    unit        VARCHAR(20) NOT NULL,
    price       INTEGER NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);
INSERT INTO product VALUES (1, '可乐', '瓶', 1);
INSERT INTO product VALUES (2, '雪碧', '听', 2);

CREATE TABLE product_id_seq (
    next_val    BIGINT
);
INSERT INTO product_id_seq VALUES (52);

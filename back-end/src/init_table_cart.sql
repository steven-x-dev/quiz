CREATE TABLE cart (
    id          BIGINT,
    product_id  BIGINT,
    quantity    INTEGER NOT NULL,
    CONSTRAINT cart_pk PRIMARY KEY (id),
    CONSTRAINT cart_fk FOREIGN KEY (product_id) REFERENCES product(id)
);
INSERT INTO cart VALUES (1, 1, 2);

CREATE TABLE cart_id_seq (
    next_val    BIGINT
);
INSERT INTO cart_id_seq VALUES (51);

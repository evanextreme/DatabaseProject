CREATE TABLE `transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `discount_id` INT NULL,
  `store_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discount_id`
    FOREIGN KEY (`discount_id`)
    REFERENCES `discount` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_id`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_customer_id_idx` OF customer(`customer_id` ASC);
CREATE INDEX `fk_discount_id_idx` OF discount(`discount_id` ASC);
CREATE INDEX `fk_store_id_idx` OF store(`store_id` ASC);
CREATE TABLE `transaction_product` (
  `transaction_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`transaction_id`, `product_id`),
  INDEX `fk_product_id_idx` (`product_id` ASC),
  INDEX `fk_transaction_id_idx` (`transaction_id` ASC),
  CONSTRAINT `fk_transaction_id`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `transaction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE TABLE `shipment_product` (
  `shipment_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`shipment_id`, `product_id`),
  INDEX `fk_product_id_idx` (`product_id` ASC),
  INDEX `fk_shipment_id_idx` (`shipment_id` ASC),
  CONSTRAINT `fk_shipment_id`
    FOREIGN KEY (`shipment_id`)
    REFERENCES `shipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
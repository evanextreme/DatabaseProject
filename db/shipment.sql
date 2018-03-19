CREATE TABLE `shipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  `placed_date` DATETIME NULL,
  `recieved_date` DATETIME NULL,
  `store_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vendor_id_idx` (`vendor_id` ASC),
  INDEX `fk_store_id_idx` (`store_id` ASC),
  CONSTRAINT `fk_vendor_id`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_id`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `brand_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  `product_type_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `regular_price` VARCHAR(45) NOT NULL,
  `sale_price` VARCHAR(45) NULL,
  `size` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_brand_id`
    FOREIGN KEY (`brand_id`)
    REFERENCES `brand` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendor_id`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_type_id`
    FOREIGN KEY (`product_type_id`)
    REFERENCES `product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_id`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_brand_id_idx` ON `brand`(`brand_id` ASC),
CREATE INDEX `fk_vendor_id_idx` ON `vendor`(`vendor_id` ASC),
CREATE INDEX `fk_product_type_id_idx` ON `product_type`(`product_type_id` ASC),
CREATE INDEX `fk_store_id_idx` ON `store`(`store_id` ASC),

DROP ALL OBJECTS;

CREATE TABLE `brand` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `designer` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) DEFAULT 'anonymous',
  `address` VARCHAR(100) NULL,
  `dob` DATE NULL,
  `gender` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `credit_card` VARCHAR(45) NULL,
  `frequent_shopper` BOOLEAN NULL,
  PRIMARY KEY (`id`, `email`));

CREATE TABLE `discount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `percentage` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `product_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `parent_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_type-product_type`
    FOREIGN KEY (`parent_id`)
    REFERENCES `product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX `fk_product_type-product_type_idx` ON `product_type`(`parent_id` ASC);

CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `brand_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  `product_type_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `regular_price` DECIMAL NOT NULL,
  `sale_price` DECIMAL NULL,
  `size` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product-brand`
    FOREIGN KEY (`brand_id`)
    REFERENCES `brand` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product-vendor`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product-product_type`
    FOREIGN KEY (`product_type_id`)
    REFERENCES `product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product-store`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX `fk_product-brand_idx` ON `product`(`brand_id` ASC);
CREATE INDEX `fk_product-vendor_idx` ON `product`(`vendor_id` ASC);
CREATE INDEX `fk_product-product_type_idx` ON `product`(`product_type_id` ASC);
CREATE INDEX `fk_product-store_idx` ON `product`(`store_id` ASC);

CREATE TABLE `shipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `placed_date` TIMESTAMP NULL,
  `received_date` TIMESTAMP NULL,
  `store_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_shipment-vendor`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shipment-store`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_shipment-vendor_idx` ON `shipment`(`vendor_id` ASC);
CREATE INDEX `fk_shipment-store_idx` ON `shipment`(`store_id` ASC);

CREATE TABLE `shipment_product` (
  `shipment_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`shipment_id`, `product_id`),
  CONSTRAINT `fk_shipment_product-shipment`
    FOREIGN KEY (`shipment_id`)
    REFERENCES `shipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shipment_product-product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_shipment_product-product_idx` ON `shipment_product`(`product_id` ASC);
CREATE INDEX `fk_shipment_product-shipment_idx` ON `shipment_product`(`shipment_id` ASC);

CREATE TABLE `transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `discount_id` INT NULL,
  `store_id` INT NULL,
  `total` DECIMAL NOT NULL,
  `original_transaction_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_transaction-customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction-discount`
    FOREIGN KEY (`discount_id`)
    REFERENCES `discount` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction-store`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction-original`
      FOREIGN KEY (`original_transaction_id`)
      REFERENCES `transaction` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

CREATE INDEX `fk_transaction-customer_idx` ON `transaction`(`customer_id` ASC);
CREATE INDEX `fk_transaction-discount_idx` ON `transaction`(`discount_id` ASC);
CREATE INDEX `fk_transaction-store_idx` ON `transaction`(`store_id` ASC);
CREATE INDEX `fk_transaction-original_idx` ON `transaction`(`original_transaction_id` ASC);

CREATE TABLE `transaction_product` (
  `transaction_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`transaction_id`, `product_id`),
  CONSTRAINT `fk_transaction_product-transaction`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `transaction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_product-product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `fk_transaction_product-product_idx` ON `transaction_product`(`product_id` ASC);
CREATE INDEX `fk_transaction_product-transaction_idx` ON `transaction_product`(`transaction_id` ASC);
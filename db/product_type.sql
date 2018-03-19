CREATE TABLE `product_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `parent_type_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_parent_type_id_idx` (`parent_type_id` ASC),
  CONSTRAINT `fk_parent_type_id`
    FOREIGN KEY (`parent_type_id`)
    REFERENCES `parent_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
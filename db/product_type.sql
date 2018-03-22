CREATE TABLE IF NOT EXISTS `product_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `parent_type_id` INT NULL,
  PRIMARY KEY (`id`));

CREATE INDEX `fk_parent_type_id_idx` ON product_type(`parent_type_id` ASC);
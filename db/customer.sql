CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `credit_card` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `address` VARCHAR(100) NULL,
  `dob` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `frequent_shopper` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `dob` DATE NULL,
  `gender` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `credit_card` VARCHAR(45) NULL,
  `frequent_shopper` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
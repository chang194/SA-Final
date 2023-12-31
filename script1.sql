-- MySQL Script generated by MySQL Workbench
-- Mon Dec 18 14:31:43 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tbl_Attraction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Attraction` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Attraction` (
  `attraction_id` INT NOT NULL AUTO_INCREMENT,
  `attraction_name` VARCHAR(30) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `bussiness_hours` VARCHAR(100) NOT NULL,
  `website` VARCHAR(200) NULL,
  `rating` VARCHAR(500) NULL,
  `intro` TEXT NULL,
  `image` VARCHAR(200) NULL,
  PRIMARY KEY (`attraction_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(30) NOT NULL,
  `customer_email` VARCHAR(50) NOT NULL,
  `customer_password` VARCHAR(30) NOT NULL,
  `modified_time` DATE NULL,
  `birthday` DATE NULL,
  `intro` TEXT NULL,
  `customer_point` INT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Community`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Community` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Community` (
  `community_id` INT NOT NULL AUTO_INCREMENT,
  `community_name` VARCHAR(30) NOT NULL,
  `intro` TEXT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`community_id`, `customer_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Journey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Journey` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Journey` (
  `journey_id` INT NOT NULL AUTO_INCREMENT,
  `journey_name` VARCHAR(30) NOT NULL,
  `intro` TEXT NOT NULL,
  `journey_day` DATE NOT NULL,
  `customer_id` INT NOT NULL,
  `privacy` TINYINT NOT NULL,
  PRIMARY KEY (`journey_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_FavoriteList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_FavoriteList` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_FavoriteList` (
  `favoriteList_id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NOT NULL,
  `community_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`favoriteList_id`, `customer_id`, `community_id`, `journey_id`),
  INDEX `community_id_idx` (`community_id` ASC) VISIBLE,
  INDEX `journey_id_idx` (`journey_id` ASC) VISIBLE,
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `community_id`
    FOREIGN KEY (`community_id`)
    REFERENCES `mydb`.`tbl_Community` (`community_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `journey_id`
    FOREIGN KEY (`journey_id`)
    REFERENCES `mydb`.`tbl_Journey` (`journey_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Hotel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Hotel` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Hotel` (
  `hotel_id` INT NOT NULL AUTO_INCREMENT,
  `hotel_name` VARCHAR(30) NOT NULL,
  `hotel_location` VARCHAR(30) NOT NULL,
  `hotel_image` VARCHAR(250) NULL,
  `hotel_facilities` TEXT NULL,
  `hotel_intro` TEXT NULL,
  PRIMARY KEY (`hotel_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Hotelowner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Hotelowner` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Hotelowner` (
  `hotelowner_id` INT NOT NULL AUTO_INCREMENT,
  `hotelowner _name` VARCHAR(30) NOT NULL,
  `hotelowner _email` VARCHAR(50) NOT NULL,
  `hotelowner_password` VARCHAR(30) NOT NULL,
  `modified_time` DATE NULL,
  `hotel_id` INT NOT NULL,
  PRIMARY KEY (`hotelowner_id`),
  INDEX `hotel_id_idx` (`hotel_id` ASC) VISIBLE,
  CONSTRAINT `hotel_id`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `mydb`.`tbl_Hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Room` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Room` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `hotel_id` INT NOT NULL,
  `room_type` VARCHAR(30) NOT NULL,
  `room_image` VARCHAR(200) NOT NULL,
  `room_price` INT NOT NULL,
  `max_capacity` INT NOT NULL,
  `room_number` INT NOT NULL,
  PRIMARY KEY (`room_id`),
  INDEX `hotel_id_idx` (`hotel_id` ASC) VISIBLE,
  CONSTRAINT `hotel_id`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `mydb`.`tbl_Hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_shoppingCart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_shoppingCart` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_shoppingCart` (
  `customer_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `order_number` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `room_id`),
  INDEX `room_id_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`tbl_Room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Review` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Review` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `hotel_id` INT NOT NULL,
  `review_date` DATETIME NOT NULL,
  `replied_to_review_id` INT NOT NULL,
  `review_content` TEXT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  INDEX `hotel_id_idx` (`hotel_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `hotel_id`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `mydb`.`tbl_Hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Follower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Follower` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Follower` (
  `follower_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`follower_id`, `customer_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_Order` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_Order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `order_number` INT NOT NULL,
  `order_price` INT NOT NULL,
  `guest_number` INT NOT NULL,
  `booking_date` DATE NOT NULL,
  `checkin_date` DATE NOT NULL,
  `checkout_date` DATE NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  INDEX `room_id_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`tbl_Room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_creditcard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_creditcard` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_creditcard` (
  `card_num` VARCHAR(10) NOT NULL,
  `card_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`card_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`attraction_journey_Linking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`attraction_journey_Linking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`attraction_journey_Linking` (
  `attraction_id` INT NOT NULL,
  `journey_id` INT NOT NULL,
  `attraction_time` DATETIME NULL,
  PRIMARY KEY (`attraction_id`, `journey_id`),
  INDEX `journey_idx` (`journey_id` ASC) VISIBLE,
  CONSTRAINT `attraction`
    FOREIGN KEY (`attraction_id`)
    REFERENCES `mydb`.`tbl_Attraction` (`attraction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `journey`
    FOREIGN KEY (`journey_id`)
    REFERENCES `mydb`.`tbl_Journey` (`journey_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`community_attraction_Linking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`community_attraction_Linking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`community_attraction_Linking` (
  `community_id` INT NOT NULL,
  `attraction_id` INT NOT NULL,
  PRIMARY KEY (`community_id`, `attraction_id`),
  INDEX `attraction_id_idx` (`attraction_id` ASC) VISIBLE,
  CONSTRAINT `community_id`
    FOREIGN KEY (`community_id`)
    REFERENCES `mydb`.`tbl_Community` (`community_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `attraction_id`
    FOREIGN KEY (`attraction_id`)
    REFERENCES `mydb`.`tbl_Attraction` (`attraction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_room_availability`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_room_availability` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_room_availability` (
  `room_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `available_quantity` INT NOT NULL,
  PRIMARY KEY (`room_id`, `date`),
  CONSTRAINT `room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`tbl_Room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

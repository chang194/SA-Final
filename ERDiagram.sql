-- MySQL Script generated by MySQL Workbench
-- Mon Dec 18 17:10:04 2023
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
  CONSTRAINT `Community_to_Customer`
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
  CONSTRAINT `Journey_to_Customer`
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
  `favoritelist_id` INT NOT NULL AUTO_INCREMENT,
  `journey_id` INT NOT NULL,
  `community_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`favoritelist_id`, `customer_id`, `community_id`, `journey_id`),
  CONSTRAINT `FavoriteList_to_Community`
    FOREIGN KEY (`community_id`)
    REFERENCES `mydb`.`tbl_Community` (`community_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FavoriteList_to_Journey`
    FOREIGN KEY (`journey_id`)
    REFERENCES `mydb`.`tbl_Journey` (`journey_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FavoriteList_to_Customer`
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
-- Table `mydb`.`tbl_HotelOwner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_HotelOwner` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_HotelOwner` (
  `hotelowner_id` INT NOT NULL AUTO_INCREMENT,
  `hotelowner _name` VARCHAR(30) NOT NULL,
  `hotelowner _email` VARCHAR(50) NOT NULL,
  `hotelowner_password` VARCHAR(30) NOT NULL,
  `modified_time` DATE NULL,
  `hotel_id` INT NOT NULL,
  PRIMARY KEY (`hotelowner_id`),
  CONSTRAINT `HotelOwner_to_Hotel`
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
  CONSTRAINT `Room_to_Hotel`
    FOREIGN KEY (`hotel_id`)
    REFERENCES `mydb`.`tbl_Hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_ShoppingCart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_ShoppingCart` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_ShoppingCart` (
  `customer_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `order_number` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `room_id`),
  CONSTRAINT `ShoppingCart_to_Customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ShoppingCart_to_Room`
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
  CONSTRAINT `Review_to_Customer`
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
  CONSTRAINT `Follower_to_Customer`
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
  CONSTRAINT `Order_to_Customer`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`tbl_Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Order_to_Room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`tbl_Room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_CreditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_CreditCard` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_CreditCard` (
  `card_num` VARCHAR(10) NOT NULL,
  `card_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`card_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tblLinking_AttractionJourney`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tblLinking_AttractionJourney` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tblLinking_AttractionJourney` (
  `attraction_id` INT NOT NULL,
  `journey_id` INT NOT NULL,
  `attraction_time` DATETIME NULL,
  PRIMARY KEY (`attraction_id`, `journey_id`),
  CONSTRAINT `Journey_to_Attration`
    FOREIGN KEY (`attraction_id`)
    REFERENCES `mydb`.`tbl_Attraction` (`attraction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Attraction_to_Journey`
    FOREIGN KEY (`journey_id`)
    REFERENCES `mydb`.`tbl_Journey` (`journey_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tblLinking_CommunityAttraction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tblLinking_CommunityAttraction` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tblLinking_CommunityAttraction` (
  `community_id` INT NOT NULL,
  `attraction_id` INT NOT NULL,
  PRIMARY KEY (`community_id`, `attraction_id`),
  CONSTRAINT `Attraction_to_Community`
    FOREIGN KEY (`community_id`)
    REFERENCES `mydb`.`tbl_Community` (`community_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Community_to_Attraction`
    FOREIGN KEY (`attraction_id`)
    REFERENCES `mydb`.`tbl_Attraction` (`attraction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_RoomAvailability`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_RoomAvailability` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_RoomAvailability` (
  `room_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `available_quantity` INT NOT NULL,
  PRIMARY KEY (`room_id`, `date`),
  CONSTRAINT `RoomAvailability_to_Room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`tbl_Room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

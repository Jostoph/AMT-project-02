SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS userdb;
CREATE SCHEMA userdb;
USE userdb;

--
-- Table structure for ENTITY `user`
--

CREATE TABLE user_entity (
  email VARCHAR(45) UNIQUE NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  is_admin BOOLEAN NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY  (email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

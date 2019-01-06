-- create new user 
CREATE USER 'lotto_user'@'localhost' IDENTIFIED BY 'lotto_user';
GRANT ALL PRIVILEGES ON * . * TO 'lotto_user'@'localhost';
ALTER USER 'lotto_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'lotto_user';

-- create database
CREATE DATABASE  IF NOT EXISTS `lotto_simulator_database`;
USE `lotto_simulator_database`;

-- crete tables
CREATE TABLE `draw_history` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`result_date` DATETIME DEFAULT NULL,
 PRIMARY KEY (`id`));
 
CREATE TABLE `results` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`number` SMALLINT DEFAULT NULL,
`draw_id` int(11) NOT NULL,
PRIMARY KEY(`id`),
CONSTRAINT 
	FOREIGN KEY(`draw_id`) REFERENCES `draw_history`(`id`)
	ON DELETE CASCADE);

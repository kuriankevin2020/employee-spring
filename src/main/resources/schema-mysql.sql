CREATE DATABASE  IF NOT EXISTS `employee_db`;
USE `employee_db`;

CREATE TABLE IF NOT EXISTS `employee`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `name`  varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

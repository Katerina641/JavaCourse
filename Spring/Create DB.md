CREATE DATABASE  IF NOT EXISTS `movies_directory`;
USE `movies_directory`;
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `film_name` varchar(45) DEFAULT NULL,
  `price` DECIMAL(10,0) DEFAULT NULL,
  `date` DATE,
  `place` INT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
INSERT INTO `movies` VALUES
	(1,'Simpsons',25.15,'2024-03-03',5),
	(2,'Gary Potter',24.30,'2024-05-08',8),
	(3,'Karlson',14.80,'2024-03-17',12),
	(4,'Elton John',12.30,'2024-03-03',11),
    (5,'Simpsons',25.15,'2024-03-03',8),
	(6,'Gary Potter',24.30,'2024-05-08',9),
	(7,'Karlson',14.80,'2024-03-17',14),
	(8,'Elton John',12.30,'2024-03-03',12),
	(9,'Moana',10.45,'2024-06-03',4);


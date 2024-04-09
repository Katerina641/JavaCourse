CREATE DATABASE  IF NOT EXISTS `teacher_directory`;
USE `teacher_directory`;

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `teacher` VALUES 
	(1,'Andrew','Deyes','andrew@example.com'),
	(2,'Carlos','Ortega','carlos@example.com'),
	(3,'Simona','Panshina','simona@example.com'),
	(4,'Elton','Petrov','elton@example.com'),
	(5,'Mary','Johnson','mary@example.com');


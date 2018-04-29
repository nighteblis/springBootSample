# springBootSample
springBoot Sample code (jpa + hibernate)

1. create database stock
2. create table stock

```
CREATE TABLE `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stockId` int(11) DEFAULT NULL,
  `stockName` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

```

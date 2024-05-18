CREATE DATABASE IF NOT EXISTS warehousesFX;

CREATE TABLE warehousesFX.warehouses_deal(
	`id` bigint NOT NULL,
  	`from_currency` varchar(45) NOT NULL,
  	`to_currency` varchar(45) NOT NULL,
  	`deal_amount` double NOT NULL,
  	`deal_timestamp` datetime NOT NULL,
  	PRIMARY KEY (`id`)
);
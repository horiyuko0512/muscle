DROP TABLE IF EXISTS muscle;

CREATE TABLE muscle (
	id int NOT NULL AUTO_INCREMENT,
	doTime datetime NOT NULL ,
	traName varchar(100) NOT NULL,
	traWei decimal(5,2) NOT NULL,
	traSet int NOT NULL,
	traTimes int NOT NULL,
	traType varchar(255) NOT NULL,
	PRIMARY KEY (id)
);	
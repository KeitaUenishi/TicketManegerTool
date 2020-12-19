CREATE TABLE IF NOT EXISTS live_list(
		dateId INT(10) PRIMARY KEY NOT NULL,
		place VARCHAR(100) NOT NULL ,
		remarks VARCHAR(200)
		);

CREATE TABLE IF NOT EXISTS customers_list(
		id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
		dateId INT(10) NOT NULL,
		name VARCHAR(50) NOT NULL ,
		number INT(5) NOT NULL ,
		comment VARCHAR(200)
		);

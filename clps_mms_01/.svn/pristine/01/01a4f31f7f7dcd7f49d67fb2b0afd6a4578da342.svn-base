#´´½¨user±í
DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user(u_id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
                          u_name VARCHAR(50) NOT NULL UNIQUE,
			  u_nickname VARCHAR(50) NOT NULL,
			  u_password VARCHAR(50) NOT NULL,
			  u_gender CHAR(2) NOT NULL,
			  u_mobnum VARCHAR(20) NOT NULL UNIQUE,
			  u_email VARCHAR(50) NOT NULL,
			  u_department INT NOT NULL,
			  u_position INT NOT NULL,
			  u_createDate VARCHAR(50) NOT NULL,
			  u_createName VARCHAR(50) NOT NULL,
			  u_updateDate VARCHAR(50) NOT NULL,
			  u_updateName VARCHAR(50) NOT NULL,
			  u_isEnable INT NOT NULL DEFAULT '1',
			  PRIMARY KEY(u_id));

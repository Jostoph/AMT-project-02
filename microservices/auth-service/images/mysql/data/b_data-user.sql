USE userdb;
LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/data-user.csv'
INTO TABLE user_entity
FIELDS TERMINATED BY ','
ENCLOSED BY '\"'
LINES TERMINATED BY '\n'
(email, first_name, is_admin, last_name, password);

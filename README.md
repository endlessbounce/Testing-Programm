# Testing-Programm
Simple console program for testing.
Used MySQL tables:

CREATE TABLE users (
`Num` INT NOT NULL AUTO_INCREMENT,
 `Username` VARCHAR(255) NOT NULL,
 `Login` VARCHAR(255) NOT NULL,
 `Password` VARCHAR(255) NOT NULL,
 PRIMARY KEY (`Num`));

CREATE TABLE testresults (
`Num` INT NOT NULL AUTO_INCREMENT,
 `Login` VARCHAR(255) NOT NULL,
 `TestName` VARCHAR(255) NOT NULL,
 `Date` VARCHAR(255),
 `Score` INT NOT NULL,
 PRIMARY KEY (`Num`));

CREATE TABLE questions (
num INT NOT NULL,
 question BLOB NOT NULL);

CREATE TABLE tests (
num INT NOT NULL,
 `testName` VARCHAR(255) NOT NULL);

CREATE TABLE answers (
answer1 VARCHAR(255) NOT NULL,
 answer2 VARCHAR(255) NOT NULL,
 answer3 VARCHAR(255) NOT NULL,
 answer4 VARCHAR(255) NOT NULL,
 `rightAnswer` INT NOT NULL,
 question INT NOT NULL,
 test INT NOT NULL);

CREATE SCHEMA `学生管理系统` ;

CREATE TABLE `学生管理系统`.`学生信息` (
  `学号` INT NOT NULL,
  `姓名` VARCHAR(8) NULL,
  `性别` ENUM('男', '女') NULL,
  `院系` VARCHAR(20) NULL,
  `专业` VARCHAR(20) NULL,
  `登入密码` VARCHAR(30) NULL,
  PRIMARY KEY (`学号`));

CREATE TABLE `学生管理系统`.`教师信息` (
  `工号` INT NOT NULL,
  `姓名` VARCHAR(8) NULL,
  `登入密码` VARCHAR(30) NULL,
  PRIMARY KEY (`工号`));

CREATE TABLE `学生管理系统`.`学生成绩` (
  `学号` INT NOT NULL,
  `姓名` VARCHAR(45) NULL,
  `成绩1` INT(3) NULL,
  `成绩2` INT(3) NULL,
  `成绩3` INT(3) NULL,
  `总分` INT(3) NULL,
  PRIMARY KEY (`学号`),
  CONSTRAINT `uk_学号`
    FOREIGN KEY (`学号`)
    REFERENCES `学生管理系统`.`学生信息` (`学号`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

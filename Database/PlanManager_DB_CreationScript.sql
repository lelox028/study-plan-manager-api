-- MySQL Script generated by MySQL Workbench
-- sáb 28 dic 2024 19:38:16
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

DROP DATABASE PlanManager;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema PlanManager
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PlanManager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PlanManager` ;
USE `PlanManager` ;

-- -----------------------------------------------------
-- Table `PlanManager`.`Universidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlanManager`.`Universidades` (
  `Nombre_U` VARCHAR(45) NOT NULL,
  `Id_U` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id_U`),
  UNIQUE INDEX `Nombre_U_UNIQUE` (`Nombre_U` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlanManager`.`Facultades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlanManager`.`Facultades` (
  `Id_F` INT NOT NULL AUTO_INCREMENT,
  `Nombre_F` VARCHAR(45) NOT NULL,
  `Pertenecen_Id_U` INT NOT NULL,
  PRIMARY KEY (`Id_F`),
  UNIQUE INDEX `ALTERNATIVE` (`Pertenecen_Id_U` ASC, `Nombre_F` ASC) VISIBLE,
  CONSTRAINT `fk_Facultades_1`
    FOREIGN KEY (`Pertenecen_Id_U`)
    REFERENCES `PlanManager`.`Universidades` (`Id_U`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlanManager`.`Carreras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlanManager`.`Carreras` (
  `Id_C` INT NOT NULL AUTO_INCREMENT,
  `Nombre_C` VARCHAR(45) NOT NULL,
  `FechaInscripcion` DATE NOT NULL,
  `Duracion` INT NOT NULL,
  `Plan` VARCHAR(45) NULL,
  `TituloIntermedio` VARCHAR(45) NULL,
  `Dicta_Id_F` INT NOT NULL,
  PRIMARY KEY (`Id_C`),
  INDEX `fk_Carreras_Facultades1_idx` (`Dicta_Id_F` ASC) VISIBLE,
  CONSTRAINT `fk_Carreras_Facultades1`
    FOREIGN KEY (`Dicta_Id_F`)
    REFERENCES `PlanManager`.`Facultades` (`Id_F`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlanManager`.`Materias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlanManager`.`Materias` (
  `Id_M` INT NOT NULL AUTO_INCREMENT,
  `Nombre_M` VARCHAR(45) NOT NULL,
  `Anio` INT NOT NULL,
  `Cuatrimestre` ENUM("1er Cuatrimestre", "2do Cuatrimestre", "Anual") NOT NULL,
  `Estado` ENUM("Pendiente", "Cursando", "Regular", "Aprobado", "Promocionado") NOT NULL,
  `FechaAprobacion` DATE NULL,
  `Calificacion` FLOAT NULL,
  `FechaRegularizacion` DATE NULL,
  `RequeridaPorTituloIntermedio` BIT(1) NOT NULL,
  `Corresponden_Id_C` INT NOT NULL,
  PRIMARY KEY (`Id_M`),
  INDEX `fk_Materias_Carreras1_idx` (`Corresponden_Id_C` ASC) VISIBLE,
  UNIQUE INDEX `Alternative` (`Nombre_M` ASC, `Corresponden_Id_C` ASC) VISIBLE,
  CONSTRAINT `fk_Materias_Carreras1`
    FOREIGN KEY (`Corresponden_Id_C`)
    REFERENCES `PlanManager`.`Carreras` (`Id_C`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PlanManager`.`Correlativas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlanManager`.`Correlativas` (
  `Materias_Id_Bloqueada` INT NOT NULL,
  `Materias_Id_Correlativa` INT NOT NULL,
  INDEX `fk_Materias_has_Materias_Materias2_idx` (`Materias_Id_Correlativa` ASC) VISIBLE,
  INDEX `fk_Materias_has_Materias_Materias1_idx` (`Materias_Id_Bloqueada` ASC) VISIBLE,
  PRIMARY KEY (`Materias_Id_Bloqueada`, `Materias_Id_Correlativa`),
  CONSTRAINT `fk_Materias_has_Materias_Materias1`
    FOREIGN KEY (`Materias_Id_Bloqueada`)
    REFERENCES `PlanManager`.`Materias` (`Id_M`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Materias_has_Materias_Materias2`
    FOREIGN KEY (`Materias_Id_Correlativa`)
    REFERENCES `PlanManager`.`Materias` (`Id_M`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

USE PlanManager;

/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.0.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: PlanManager
-- ------------------------------------------------------
-- Server version	12.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `FechaRegistro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Activo` TINYINT NOT NULL,
  `Rol` ENUM('ADMIN', 'USER') NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Usuarios` VALUES
(1, 'admin', 'admin@email.com', '$2a$12$0y1eisowgV999SQjDr5h/u3jxta/hOCzNN.1mPSI0/io6dcvrx5Q.', NOW(), 1, 'ADMIN'),
(2, 'lelox', 'lelox@email.com', '$2a$12$0y1eisowgV999SQjDr5h/u3jxta/hOCzNN.1mPSI0/io6dcvrx5Q.', NOW(), 1, 'USER');
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Universidades`
--

DROP TABLE IF EXISTS `Universidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Universidades` (
  `Nombre_U` varchar(45) NOT NULL,
  `Id_U` int(11) NOT NULL AUTO_INCREMENT,
  `Usuarios_idUsuarios` INT NOT NULL,
  PRIMARY KEY (`Id_U`),
  UNIQUE KEY `Nombre_U_UNIQUE` (`Nombre_U`, `Usuarios_idUsuarios`),
  INDEX `fk_Universidades_Usuarios1_idx` (`Usuarios_idUsuarios`),
  CONSTRAINT `fk_Universidades_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `Usuarios` (`idUsuarios`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Universidades`
--

LOCK TABLES `Universidades` WRITE;
/*!40000 ALTER TABLE `Universidades` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Universidades` VALUES
('Siglo 21', 1, 2);
/*!40000 ALTER TABLE `Universidades` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Facultades`
--

DROP TABLE IF EXISTS `Facultades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Facultades` (
  `Id_F` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_F` varchar(45) NOT NULL,
  `Pertenecen_Id_U` int(11) NOT NULL,
  PRIMARY KEY (`Id_F`),
  UNIQUE KEY `ALTERNATIVE` (`Pertenecen_Id_U`,`Nombre_F`),
  CONSTRAINT `fk_Facultades_1` FOREIGN KEY (`Pertenecen_Id_U`) REFERENCES `Universidades` (`Id_U`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Facultades`
--

LOCK TABLES `Facultades` WRITE;
/*!40000 ALTER TABLE `Facultades` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Facultades` VALUES
(2,'Default',1),
(1,'test',1);
/*!40000 ALTER TABLE `Facultades` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Carreras`
--

DROP TABLE IF EXISTS `Carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Carreras` (
  `Id_C` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_C` varchar(45) NOT NULL,
  `FechaInscripcion` datetime(6) NOT NULL,
  `Duracion` int(11) NOT NULL,
  `Plan` varchar(255) DEFAULT NULL,
  `TituloIntermedio` varchar(45) DEFAULT NULL,
  `Dicta_Id_F` int(11) NOT NULL,
  PRIMARY KEY (`Id_C`),
  KEY `fk_Carreras_Facultades1_idx` (`Dicta_Id_F`),
  CONSTRAINT `fk_Carreras_Facultades1` FOREIGN KEY (`Dicta_Id_F`) REFERENCES `Facultades` (`Id_F`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Carreras`
--

LOCK TABLES `Carreras` WRITE;
/*!40000 ALTER TABLE `Carreras` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Carreras` VALUES
(1,'lic infor','2025-08-08 00:00:00.000000',3,NULL,NULL,1),
(2,'Licenciatura en Informática','2024-08-01 00:00:00.000000',5,NULL,'Analista Universitario',2);
/*!40000 ALTER TABLE `Carreras` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Materias`
--

DROP TABLE IF EXISTS `Materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Materias` (
  `Id_M` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_M` varchar(90) NOT NULL,
  `Anio` int(11) NOT NULL,
  `Cuatrimestre` varchar(45) NOT NULL,
  `Estado` enum('Pendiente','Cursando','Regular','Aprobado','Promocionado') NOT NULL,
  `FechaAprobacion` date DEFAULT NULL,
  `Calificacion` float DEFAULT NULL,
  `FechaRegularizacion` date DEFAULT NULL,
  `RequeridaPorTituloIntermedio` bit(1) NOT NULL,
  `Corresponden_Id_C` int(11) NOT NULL,
  PRIMARY KEY (`Id_M`),
  UNIQUE KEY `Alternative` (`Nombre_M`,`Corresponden_Id_C`),
  KEY `fk_Materias_Carreras1_idx` (`Corresponden_Id_C`),
  CONSTRAINT `fk_Materias_Carreras1` FOREIGN KEY (`Corresponden_Id_C`) REFERENCES `Carreras` (`Id_C`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materias`
--

LOCK TABLES `Materias` WRITE;
/*!40000 ALTER TABLE `Materias` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Materias` VALUES
(1,'test',1,'1er Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,1),
(10,'Álgebra y Geometría',1,'1er Cuatrimestre','Aprobado','2024-06-28',8.5,'2024-06-27',0x01,2),
(11,'Análisis Matemático',1,'1er Cuatrimestre','Aprobado','2024-09-14',8,'2024-09-13',0x01,2),
(12,'Programación Lógica',1,'1er Cuatrimestre','Promocionado','2025-01-08',9,'2025-01-07',0x01,2),
(13,'Sistemas de Información',1,'1er Cuatrimestre','Promocionado','2024-11-24',10,'2024-11-22',0x01,2),
(14,'Idioma Extranjero I',1,'1er Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(15,'Lógica Simbólica',1,'1er Cuatrimestre','Promocionado','2025-07-09',9,'2025-07-08',0x01,2),
(16,'Arquitectura del Computador',1,'2do Cuatrimestre','Promocionado','2024-11-26',9,'2024-11-25',0x01,2),
(17,'Cálculo Avanzado',1,'2do Cuatrimestre','Aprobado','2024-11-14',8,'2024-11-13',0x01,2),
(18,'Matemática Discreta',1,'2do Cuatrimestre','Aprobado','2024-11-14',7,'2024-11-13',0x01,2),
(19,'Programación Orientada a Objetos',1,'2do Cuatrimestre','Aprobado','2024-11-14',9,'2024-11-13',0x01,2),
(20,'Idioma Extranjero II',1,'2do Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(21,'Estadística y Probabilidad',2,'1er Cuatrimestre','Aprobado','2024-11-14',7,'2024-11-13',0x01,2),
(22,'Algoritmos y Estructura de Datos I',2,'1er Cuatrimestre','Promocionado','2025-05-06',10,'2025-05-05',0x01,2),
(23,'Grupo y Liderazgo',2,'1er Cuatrimestre','Promocionado','2025-05-06',9,'2025-05-05',0x01,2),
(24,'Idioma Extranjero III',2,'1er Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(25,'Lenguajes Formales y Computabilidad',2,'1er Cuatrimestre','Promocionado','2025-05-06',9,'2025-05-05',0x01,2),
(26,'Práctica Solidaria',2,'1er Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,2),
(27,'Taller de Algoritmos y Estructura de Datos I',2,'1er Cuatrimestre','Promocionado','2025-05-04',10,'2025-05-03',0x01,2),
(28,'Base de Datos I',2,'2do Cuatrimestre','Aprobado','2024-12-16',10,'2024-12-18',0x01,2),
(29,'Administración',2,'2do Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,2),
(30,'Algoritmos y Estructura de Datos II',2,'2do Cuatrimestre','Promocionado','2025-09-26',10,'2025-09-24',0x01,2),
(31,'Idioma Extranjero IV',2,'2do Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(32,'Taller de Algoritmos y Estructuras de Datos II',2,'2do Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(33,'Análisis y Diseño de Software',3,'1er Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,2),
(34,'Idioma Extranjero V',3,'1er Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(35,'Principios de Economía',3,'1er Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,2),
(36,'Pruebas de Sistemas',3,'1er Cuatrimestre','Promocionado','2025-09-22',8,'2025-09-21',0x01,2),
(37,'Sistemas Operativos',3,'1er Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(38,'Ingeniería de Software',3,'2do Cuatrimestre','Aprobado','2024-11-13',8,'2024-11-12',0x01,2),
(39,'Algoritmos Concurrentes y Paralelos',3,'2do Cuatrimestre','Promocionado','2024-11-13',8,'2024-11-12',0x01,2),
(40,'Comunicaciones',3,'2do Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,2),
(41,'Desarrollo Emprendedor',3,'2do Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(42,'Idioma Extranjero VI',3,'2do Cuatrimestre','Cursando',NULL,NULL,NULL,0x01,2),
(43,'Seminario de Práctica de Informática',3,'2do Cuatrimestre','Pendiente',NULL,NULL,NULL,0x01,2),
(44,'Aprender en el Siglo 21',1,'1er Cuatrimestre','Promocionado','2024-09-05',10,'2024-09-05',0x01,2),
(45,'TECNOLOGÍA, HUMANIDADES Y MODELOS GLOBALES ',1,'1er Cuatrimestre','Promocionado','2025-11-04',10,'2025-11-04',0x01,2);
/*!40000 ALTER TABLE `Materias` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `Correlativas`
--

DROP TABLE IF EXISTS `Correlativas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Correlativas` (
  `Materias_Id_Bloqueada` int(11) NOT NULL,
  `Materias_Id_Correlativa` int(11) NOT NULL,
  PRIMARY KEY (`Materias_Id_Bloqueada`,`Materias_Id_Correlativa`),
  KEY `fk_Materias_has_Materias_Materias2_idx` (`Materias_Id_Correlativa`),
  KEY `fk_Materias_has_Materias_Materias1_idx` (`Materias_Id_Bloqueada`),
  CONSTRAINT `fk_Materias_has_Materias_Materias1` FOREIGN KEY (`Materias_Id_Bloqueada`) REFERENCES `Materias` (`Id_M`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Materias_has_Materias_Materias2` FOREIGN KEY (`Materias_Id_Correlativa`) REFERENCES `Materias` (`Id_M`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Correlativas`
--

LOCK TABLES `Correlativas` WRITE;
/*!40000 ALTER TABLE `Correlativas` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `Correlativas` VALUES
(17,11),
(36,15),
(37,16),
(22,19),
(27,19),
(30,22),
(32,22),
(30,27),
(32,27),
(43,28),
(33,30),
(33,32),
(43,33),
(43,36),
(40,37),
(45,44);
/*!40000 ALTER TABLE `Correlativas` ENABLE KEYS */;
UNLOCK TABLES;
commit;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-11-02 12:51:04

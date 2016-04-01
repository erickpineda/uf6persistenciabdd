-- MySQL dump 10.15  Distrib 10.0.23-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: colors
-- ------------------------------------------------------
-- Server version	10.0.23-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `angles` varchar(255) DEFAULT NULL,
  `castella` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `frances` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'red','rojo','vermell','rouge'),(2,'green','verde','verd','vert'),(3,'white','blanco','blanc','blanc'),(4,'yellow','amarillo','groc','jaune'),(5,'black','negro','negre','noir'),(6,'purple','morado','lila','violet'),(7,'orange','naranja','taronja','orange'),(8,'blue','azul','blau','bleu'),(9,'pink','rosa','rosa','rose'),(10,'grey','gris','gris','gris'),(11,'fuchsia','fucsia','fucsia','fuchsia'),(12,'brown','marrón','marró','marron'),(13,'red','rojo','vermell','rouge'),(14,'red','rojo','vermell','rouge'),(15,'green','verde','verd','vert'),(16,'white','blanco','blanc','blanc'),(17,'yellow','amarillo','groc','jaune'),(18,'black','negro','negre','noir'),(19,'purple','morado','lila','violet'),(20,'orange','naranja','taronja','orange'),(21,'blue','azul','blau','bleu'),(22,'pink','rosa','rosa','rose'),(23,'grey','gris','gris','gris'),(24,'fuchsia','fucsia','fucsia','fuchsia'),(25,'brown','marrón','marró','marron'),(26,'red','rojo','vermell','rouge'),(27,'green','verde','verd','vert'),(28,'white','blanco','blanc','blanc'),(29,'yellow','amarillo','groc','jaune'),(30,'black','negro','negre','noir'),(31,'purple','morado','lila','violet'),(32,'orange','naranja','taronja','orange'),(33,'blue','azul','blau','bleu'),(34,'pink','rosa','rosa','rose'),(35,'grey','gris','gris','gris'),(36,'fuchsia','fucsia','fucsia','fuchsia'),(37,'brown','marrón','marró','marron'),(38,'red','rojo','vermell','rouge'),(39,'green','verde','verd','vert'),(40,'white','blanco','blanc','blanc'),(41,'yellow','amarillo','groc','jaune'),(42,'black','negro','negre','noir'),(43,'purple','morado','lila','violet'),(44,'orange','naranja','taronja','orange'),(45,'blue','azul','blau','bleu'),(46,'pink','rosa','rosa','rose'),(47,'grey','gris','gris','gris'),(48,'fuchsia','fucsia','fucsia','fuchsia'),(49,'brown','marrón','marró','marron'),(50,'red','rojo','vermell','rouge'),(51,'green','verde','verd','vert'),(52,'white','blanco','blanc','blanc'),(53,'yellow','amarillo','groc','jaune'),(54,'black','negro','negre','noir'),(55,'purple','morado','lila','violet'),(56,'orange','naranja','taronja','orange'),(57,'blue','azul','blau','bleu'),(58,'pink','rosa','rosa','rose'),(59,'grey','gris','gris','gris'),(60,'fuchsia','fucsia','fucsia','fuchsia'),(61,'brown','marrón','marró','marron'),(62,'red','rojo','vermell','rouge'),(63,'green','verde','verd','vert'),(64,'white','blanco','blanc','blanc'),(65,'yellow','amarillo','groc','jaune'),(66,'black','negro','negre','noir'),(67,'purple','morado','lila','violet'),(68,'orange','naranja','taronja','orange'),(69,'blue','azul','blau','bleu'),(70,'pink','rosa','rosa','rose'),(71,'grey','gris','gris','gris'),(72,'fuchsia','fucsia','fucsia','fuchsia'),(73,'brown','marrón','marró','marron'),(74,'red','rojo','vermell','rouge'),(75,'green','verde','verd','vert'),(76,'white','blanco','blanc','blanc'),(77,'yellow','amarillo','groc','jaune'),(78,'black','negro','negre','noir'),(79,'purple','morado','lila','violet'),(80,'orange','naranja','taronja','orange'),(81,'blue','azul','blau','bleu'),(82,'pink','rosa','rosa','rose'),(83,'grey','gris','gris','gris'),(84,'fuchsia','fucsia','fucsia','fuchsia'),(85,'brown','marrón','marró','marron');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-14 12:12:19

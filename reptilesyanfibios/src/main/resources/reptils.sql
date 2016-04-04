-- MySQL dump 10.15  Distrib 10.0.23-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: reptilscatalans
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
-- Table structure for table `animals`
--

DROP TABLE IF EXISTS `animals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animals` (
  `codi` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) DEFAULT NULL,
  `ordre` int(5) DEFAULT NULL,
  `especie` varchar(100) DEFAULT NULL,
  `descripcio` varchar(1000) DEFAULT NULL,
  `estat` varchar(50) DEFAULT NULL,
  `imatge` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codi`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animals`
--

LOCK TABLES `animals` WRITE;
/*!40000 ALTER TABLE `animals` DISABLE KEYS */;
INSERT INTO `animals` VALUES (1,'Salamandra comuna',1,'Salamandra salamandra','Té una longitud mitjana de 18 cm i la pell llisa. És inconfusible pels seus colors vius, fons negre amb taques grogues molt variables, indicador de les secrecions irritants que pot desprendre, cosa que li serveix de defensa davant els depredadors.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Salamandra_salamandra_MHNT_1.jpg/130px-Salamandra_salamandra_MHNT_1.jpg'),(2,'Ofegabous',1,'Pleurodeles waltl','És el tritó més gran d\'Europa. Pot assolir els 30 cm de longitud, dels quals aproximadament la meitat corresponen a la cua. La coloració pot variar del gris fosc a gris clar en la zona dorsal, amb la zona ventral gairebé blanca.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/d/da/Pleorodeles_waltl.jpg/130px-Pleorodeles_waltl.jpg'),(3,'Tritó pirinenc',1,'Calotriton asper','Mesura de 10 a 15 cm de llarg dels quals gairebé la meitat corresponen a la cua, força aprimada. Té el dors color castany fosc i uniforme, de vegades amb alguna taca groga o una ratlla longitudinal groga a l\'esquena. A diferència d\'altres espècies de tritons, l\'adult no té cresta a l\'esquena ni la cua.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/e/e4/BennyTrapp_Calotriton_asper_Pyren%C3%A4en_Spanien.jpg/130px-BennyTrapp_Calotriton_asper_Pyren%C3%A4en_Spanien.jpg'),(4,'Tritó del Montseny',1,'Calotriton arnoldi','És considerada l\'única espècie de vertebrat endèmica de Catalunya. Es calcula que la població total no supera els 1.500 exemplars adults i l\'àrea de distribució és inferior als 10 quilòmetres quadrats.','En perill greu','https:////upload.wikimedia.org/wikipedia/commons/thumb/1/1b/BennyTrapp_Montseny-Gebirgsmolch_Calotriton_arnoldi_Montseny-Gebirge_Spanien.jpg/130px-BennyTrapp_Montseny-Gebirgsmolch_Calotriton_arnoldi_Montseny-Gebirge_Spanien.jpg'),(5,'Tritó verd',1,'Triturus marmoratus','Pot arribar a fer fins a 17 cm de llarg. Té la pell aspra i de color verd i amb jaspiat de color marró o negre. El ventre és gris fosc amb taquetes blanques o fosques i el cap és ample, pla i amb glàndules paròtides.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/3/33/Triturus_marmoratus.JPG/130px-Triturus_marmoratus.JPG'),(6,'Tritó palmat',1,'Lissotriton helveticus','És un tritó relativament petit, no arriba als 10 cm. El dors és de color és marró i el ventre groc clar. Té una franja fosca lateral que travessa els ulls de banda a banda del cap.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Triturus_helveticus%28male%29.jpg/130px-Triturus_helveticus%28male%29.jpg'),(7,'Tòtil',2,'Alytes obstetricans','No sol excedir dels 5 cm. El mascle és l\'encarregat de portar els ous fecundats durant tot un mes entre les potes posteriors. És rara la seva activitat durant el dia.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/f/f4/AlytesObstet.jpg/130px-AlytesObstet.jpg'),(8,'Granota pintada',2,'Discoglossus pictus','Mesura entre 3 a 6 cm de llargada. Té la pupil·la arrodonida, el musell força punxegut, la pell amb berrugues petites, el disseny dorsal llis i el ventre generalment blanquinós.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/2/26/Discoglossus_pictus.jpg/130px-Discoglossus_pictus.jpg'),(9,'Gripau d\'esperons',3,'Pelobates cultripes','Té un aspecte rabassut, morro arrodonit, pell llisa i ulls grossos, amb pupil·la vertical. Prefereix els terrenys sorrencs prop o força lluny de l\'aigua, on només acudeix durant l\'època de reproducció.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/e/ec/R%C3%A8plica_de_femella_de_Pelobates_cultripes.jpg/130px-R%C3%A8plica_de_femella_de_Pelobates_cultripes.jpg'),(10,'Granoteta de punts',4,'Pelodytes punctatus','Granota molt petita, d\'uns 4 cm, amb potes posteriors llargues, cap pla i pupil·les verticals. El dors és de color variable, generalment amb taques verdes irregulars en un color marró clar, gris o fons verd oliva lleuger.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Pelodytes_punctatus_side.jpg/130px-Pelodytes_punctatus_side.jpg'),(11,'Gripau comú',5,'Bufo bufo','És el gripau més gran de Catalunya, mesura uns 15 cm. El seu cos és compacte i té la pell farcida de berrugues d\'aspecte espinós. Els ulls tenen l\'iris de color coure vermellós i la pupil·la és horitzontal.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/1/16/Bufo_bufo_2_%282005_07_11%29.jpg/130px-Bufo_bufo_2_%282005_07_11%29.jpg'),(12,'Gripau corredor',5,'Epidalea calamita','Rarament supera els 10 cm. La coloració dorsal és verdosa o grisenca amb taques més fosques, disposades irregularment. S\'hi pot observar generalment una ratlla groguenca que va d\'un cap a l\'altre de l\'esquena.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Epidalea_calamita_02_by-dpc.jpg/130px-Epidalea_calamita_02_by-dpc.jpg'),(13,'Reineta meridional',6,'Hyla meridionalis','És un dels amfibis més petits d\'Europa, no passa dels 5 cm. Té la pell molt llisa i brillant podent presentar tons verds, groguencs i marrons. Té una gran capacitat de camuflatge.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/3/37/Hyla_meriodionalis_1.jpg/130px-Hyla_meriodionalis_1.jpg'),(14,'Granota roja',7,'Rana temporaria','De color bru rosat, sol medir uns 12 cm. És força aquàtica en època de reproducció, però fora d\'aquest temps se la pot trobar força lluny de l\'aigua.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/1/16/Common_frog.jpg/130px-Common_frog.jpg'),(15,'Granota verda',7,'Pelophylax perezi','Pot fer fins a 11 cm de longitud. Té un verd llampant amb alguns punts i ratlles negres. És una espècie estrictament aquàtica.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Pelophylax_perezi_by-dpc.jpg/130px-Pelophylax_perezi_by-dpc.jpg'),(16,'Granota híbrida de Graf',7,'Pelophylax kl. grafi','Degut el seu origen híbrid (entre la granota comuna i la verda, no té la categoria nomenclatural d\'espècie com a tal. És molt similar a la granota verda, tant en forma com color, tot i que lleugerament major.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Benny_Trapp_Pelophylax_kl_grafi_Grafscher_Hybridfrosch.jpg/130px-Benny_Trapp_Pelophylax_kl_grafi_Grafscher_Hybridfrosch.jpg'),(17,'Tortuga mediterrània',8,'Testudo hermanni','És herbívora i diürna. Generalment pot arribar a edats comparables a les dels humans. La destrucció dels seus hàbitats i la seva popularitat com a animals de companyia n\'han delmat significativament les poblacions salvatges.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Testudo_hermanni_hermanni_Mallorca_02.jpg/130px-Testudo_hermanni_hermanni_Mallorca_02.jpg'),(18,'Tortuga de rierol',9,'Mauremys leprosa','Té la closca de color verd terrós o marró i una longitud mitjana de 15 cm. És aplanada, amb la quilla vertebral lleugerament marcada. El plastró és groc, de vegades amb taques negres.','Vulnerable','https:////upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Mauremys_leprosa_03_by-dpc.jpg/130px-Mauremys_leprosa_03_by-dpc.jpg'),(19,'Tortuga d\'estany',10,'Emys orbicularis','Mesura uns 30 centímetres de llargada, arrodonida, fosca, de color gris, verdós o negre, amb taques i línies grogues. Compta amb 5 plaques vertebrals i 4 plaques costals, a vegades dividides o fragmentades.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/2/27/Emys_orbicularis_2009_G1.jpg/130px-Emys_orbicularis_2009_G1.jpg'),(20,'Tortuga careta',11,'Caretta caretta','Té un pes de 65 a 107 kg i una longitud de 82 a 109 cm. Els colors varien entre el cafè fosc i el groc ataronjat, el dors és de color crema. Té aletes de dues arpes especialitzades per nedar grans distàncies i el cap gran i arrodonit.','En perill','https:////upload.wikimedia.org/wikipedia/commons/thumb/1/13/Loggerhead_turtle.jpg/130px-Loggerhead_turtle.jpg'),(21,'Tortuga verda',11,'Chelonia mydas','Té un cos aplanat dorsoventralment, un cap amb coll curt, aletes amb forma de rem adaptades per la natació. La seva closa és coneguda per tenir diversos patrons de colors que canvien al llarg del temps.','En perill','https:////upload.wikimedia.org/wikipedia/commons/thumb/7/73/Cheloniidae2005.jpg/130px-Cheloniidae2005.jpg'),(22,'Tortuga carei',11,'Eretmochelys imbricata','Té la forma del cos aplanada, una closca que la protegeix i aletes adaptades per nedar per mar obert. És fàcil de distingir-la d\'altres tortugues marines gràcies al seu bec punxegut i corbat amb una prominent mandíbula superior.','En perill greu','https:////upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Hawksbill_Turtle.jpg/130px-Hawksbill_Turtle.jpg'),(23,'Tortuga llaüt',12,'Dermochelys coriacea','El seu tret més remarcable és l\'absència visible d\'una cuirassa dura, al contrari del que passa en la majoria de les altres tortugues. El dors de l\'animal presenta set crestes: la del mig és la cresta vertebral i les altres sis són les crestes laterals. Pot pesar fins a 600 kg i mesurar fins a 2 metres.','Vulnerable','https:////upload.wikimedia.org/wikipedia/commons/thumb/9/9b/LeatherbackTurtle.jpg/130px-LeatherbackTurtle.jpg'),(24,'Dragó rosat',13,'Hemidactylus turcicus','Mesura entre 40 i 50 mm del musell a la cloaca. Encara que la coloració és variable, generalment és gris clar, rosat o marronós, amb nombroses taques fosques. Té el dors i la cua coberts per petites escates granulars.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Hemidactylus_turcicus.jpg/130px-Hemidactylus_turcicus.jpg'),(25,'Dragó comú',13,'Tarentola mauritanica','És dragó gran i robust, pot arribar als 19 cm de longitud (inclosa la cua, que acostuma a suposar aproximadament un 50% de la llargada de l\'individu). La coloració és fosca, grisenca, amb 4 o 5 bandes transversals fosques de forma irregular en el dors.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Tarentola_mauritanica_01_by-dpc.jpg/130px-Tarentola_mauritanica_01_by-dpc.jpg'),(26,'Lludrió ibèrica',14,'Chalcides bedriagai','El tronc és molt allargat i el cap aixafat. Pot arribar a mesurar com a molt 16 cm. Les potes són tan petites que gairebé no són funcionals. Habita a la sorra preferentment en llocs amb vegetació dispersa d\'arboç i matoll sec.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Chalcides_bedriagai_01_by-dpc.jpg/130px-Chalcides_bedriagai_01_by-dpc.jpg'),(27,'Lludrió llitat',14,'Chalcides striatus','Té un cap petit, triangular, eixamplada i amb musell arrodonit. Cos curt i gruixut de secció arrodonida o quadrangular, recobert d\'escates llises i brillants.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Benny_Trapp_Chalcides_striatus_Spanien.jpg/130px-Benny_Trapp_Chalcides_striatus_Spanien.jpg'),(28,'Vidriol',15,'Anguis fragilis','Es tracta d\'un llangardaix sense potes que sovint es confon amb una serp. Pot assolir fins a 50 cm. La cua és llarga, sovint més llarga que el conjunt del cos i el cap, però com altres llangardaixos té la capacitat d\'automutilar-se per evadir-se d\'un predador.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Anguidae.jpg/130px-Anguidae.jpg'),(29,'Sargantana cua-roja',16,'Acanthodactylus erythrurus','Mesura un 7-8 cm des del cap a la cloaca, i uns 15 més de cua. El dors és grisós amb ratlles longitudinals blanques més o menys marcades i amb taques fosques. La part inferior de la cua és d\'un color rogenc molt característic.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/0/01/Benny_Trapp_Acanthodactylus_erythrurus.jpg/130px-Benny_Trapp_Acanthodactylus_erythrurus.jpg'),(30,'Sargantana cuallarga',16,'Psammodromus algirus','Pot arribar a mesurar fins a 27 cm, sent la cua dues terceres parts del total. Les escates que recobreixen el dors i els flancs són aquillades i alhora imbricades, tret que també s\'estén a les ventrals.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Psammodromus_algirus_juv.jpg/130px-Psammodromus_algirus_juv.jpg'),(31,'Sargantana corredora',16,'Psammodromus hispanicus','Pot arribar a mesurar fins a 15 cm. La seva coloració varia entre el marró i el cendrós amb dues ratlles longitudinals d\'un color groguenc a cada costat juntament amb taques marrons i negres.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/5/5b/Psammodromus_algirus_juv.jpg/130px-Psammodromus_algirus_juv.jpg'),(32,'Llangardaix ocel·lat',16,'Timon lepidus','És un llangardaix molt gran. Pot arribar a superar els 24 cm entre el cap i la cloaca i els 70 cm de longitud total. El cos és robust amb el cap gros i ample. És de color verd o bru, amb ocels blaus als costats del cos.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Timon_lepidus.jpg/130px-Timon_lepidus.jpg'),(33,'Lluert',16,'Lacerta bilineata','És un llangardaix àgil i ràpid que pot fer uns 40 cm de llargada màxima. És de color verd brillant amb punts marronosos, tret dels laterals del cap que són de color cian.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/1/13/Maggia_lacerta_viridis_viridis_fg05.jpg/130px-Maggia_lacerta_viridis_viridis_fg05.jpg'),(34,'Llangardaix pirinenc',16,'Lacerta agilis','Sol fer de 9 a 11 cm del cap a la cloaca, i 15 cm més de cua. La seva morfologia general s\'adiu a la d\'una sargantana de cap curt i alt, cos rabassut i potes força curtes. Un tret molt característic consisteix en la presència d\'una banda vertebral molt patent al dors.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/3/32/M_Zauneidechse1_Edit1.jpg/130px-M_Zauneidechse1_Edit1.jpg'),(35,'Sargantana vivípara',16,'Zootoca vivipara','La seva longitud és menor de 12 cm, excloent la cua que pot fer des d\'1,5 a dues vegades el seu cos. El color i patró d\'aquesta espècie és remarcablement variable. Normalment és marró, però es poden trobar exemplars de color gris, castany oliva o negre.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Lacerta_vivipara_1_%28Marek_Szczepanek%29.jpg/130px-Lacerta_vivipara_1_%28Marek_Szczepanek%29.jpg'),(36,'Sargantana pirinenca',16,'Iberolacerta bonnali','És una sargantana de grandària mitjana que mesura de 50 a 55 cm. El dors és de color grisenc, amb dues línies fosques al llarg del dors.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/4/41/Lezard_des_Pyrenees.jpg/130px-Lezard_des_Pyrenees.jpg'),(37,'Sargantana pallaresa',16,'Iberolacerta aurelioi','Habita entre els 2.000 i 3.000 metres a l\'eix prinenc que fa frontera amb Andorra i França. Se la pot trobar sobre roques ben abrigades i amb bona insolació.','En perill','https:////upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Benny_Trapp_Iberolacerta_aurelioi.jpg/130px-Benny_Trapp_Iberolacerta_aurelioi.jpg'),(38,'Sargantana aranesa',16,'Iberolacerta aranica','Sol mesurar entre 5 a 6 cm. És robusta, té extremitats curtes, dits llargs i la cua és dues vegades la mida de la resta del cos. És de color gris i amb dues línies paravertebrals de punts foscos.','En perill','https:////upload.wikimedia.org/wikipedia/commons/thumb/9/96/Benny_Trapp_Iberolacerta_aranica.jpg/130px-Benny_Trapp_Iberolacerta_aranica.jpg'),(39,'Sargantana roquera',16,'Podarcis muralis','Mesura entre 50 i 70 cm sense comptar la cua. Té una gran varietat de coloració que va de marró, gris i verd. En general es pot apreciar una línia de punts negres al dors i una banda fosca a cada costat.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/4/48/Podarcis_muralis01.jpg/130px-Podarcis_muralis01.jpg'),(40,'Sargantana de les Pitiüses',16,'Podarcis pityusensis','Endèmica de les Pitiüses, aquesta espècie de sargantana ha estat introduïda a les costes de Barcelona. De grandària mitjana i aspecte variable, és una espècie robusta, de potes fortes i cap curt.','Depèn de la conservació','https:////upload.wikimedia.org/wikipedia/commons/thumb/7/71/Sargantana_cam%C3%AD_de_sa_pujada_1.jpg/130px-Sargantana_cam%C3%AD_de_sa_pujada_1.jpg'),(41,'Serpeta cega',17,'Blanus cinereus','Pot arribar a mesurar fins a 25 cm. Té una variació de color que va des de colors foscos com el negre o gris a més clars com el rosat. Se sol ocultar sota les pedres i troncs caiguts.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Iberian_worm_lizard.jpg/130px-Iberian_worm_lizard.jpg'),(42,'Serp de collaret',18,'Natrix natrix','Sol fer entre 90 i 120 cm. Els exemplars ibèrics presenten el dors de color verd oliva, amb nombroses i petites taques negres disposades irregularment. El ventre, tret de la regió del coll, és gairebé negre.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/3/38/Natrix_natrix_%28Karl_L%29.jpg/130px-Natrix_natrix_%28Karl_L%29.jpg'),(43,'Serp d’aigua',18,'Natrix maura','Pot arribar a fer 70 cm de llargada. La coloració i el disseny són molt variables (hi ha exemplars que van del verd clar al vermell i d\'una línia patent en ziga-zaga que recorre el dors fins a dues conspícues línies longitudinals blanques o àdhuc grogues).','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Natrix_maura_01_by-dpc.jpg/130px-Natrix_maura_01_by-dpc.jpg'),(44,'Serp llisa septentrional',18,'Coronella austriaca','No sol sobrepassar els 60 cm de longitud i els 50 g de pes. El cap té una forma ovalada, en proporció al cos, és relativament petit. Generalment és de color grip, tot i que pot tenir tons rogencs.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/2/2d/CoronellaAustriacaMale.JPG/130px-CoronellaAustriacaMale.JPG'),(45,'Serp llisa meridional',18,'Coronella girondica','Pot arribar als 60 cm. És de color marró, grisenc o vermellós, amb barres transversals marrons o negres. Al clatell hi té una marca fosca en forma de U o un parell de taques allargades fosques.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/5/58/Coronella_girondica_Geres_2.JPG/130px-Coronella_girondica_Geres_2.JPG'),(46,'Serp blanca',18,'Rhinechis scalaris','És fàcilment identificable pequè té dues ratlles negres i paral·leles sobre el seu dors brunenc. Pot arribar a mesurar 180 cm, però normalment no sobrepassa els 150 cm.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/e/e8/ElapheScalaris-Young-199206-France.jpg/130px-ElapheScalaris-Young-199206-France.jpg'),(47,'Serp d\'Esculapi',18,'Zamenis longissimus','És una serp no verinosa, molt prima i llarga. Té molta destresa per pujar als arbres. De jove s\'alimenta de sargantanes petites. A mesura que creix va incorporant ratolins, talpons i musaranyes.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Zamenis_longissimus.jpg/130px-Zamenis_longissimus.jpg'),(48,'Serp de ferradura',18,'Hemorrhois hippocrepis','El dors és de color fosc i alguns individus són quasi negres. El color de fons està cobert de petites taques groguenques que formen dibuixos regular. No sol mesurar més de 175 cm.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Hemorrhois-hippocrepis-2.jpg/130px-Hemorrhois-hippocrepis-2.jpg'),(49,'Serp verd-groga',18,'Hierophis viridiflavus','És una serp de colors vius, amb taques i dibuixos, predominant el negre, el verd clar i el groc. Les serps joves tenen una coloració més clara amb tons verdosos i grisencs. Els exemplars adults poden arribar a una talla màxima de 170 cm.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Hierophis_viridiflavus.jpg/130px-Hierophis_viridiflavus.jpg'),(50,'Serp verda',18,'Malpolon monspessulanus','Pot arribar tranquil·lament als 2 metres, i se n\'han trobat alguns exemplars de 2 mestres i mig. Té els ulls grans, amb les escames supraoculars molt sortides en forma de cella prominent. És una serp verinosa.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Couleuvre_montpellier.jpg/130px-Couleuvre_montpellier.jpg'),(51,'Escurçó ibèric',19,'Vipera latastei','El dors sol ésser bru o grisenc, amb una ratlla vertebral en ziga-zaga de tons més foscos. Al mateix temps, també en els flancs hi ha un seguit de taques més o menys circulars que recorren tota la llargària de l\'ofidi. Pot mesurar fins a 75 cm.','Vulnerable','https:////upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Lataste%27s_viper.jpg/130px-Lataste%27s_viper.jpg'),(52,'Escurçó pirinenc',19,'Vipera aspis','El cap, de forma triangular, es diferencia notablement del tronc. El cos és aplanat, amb la cua curta, cònica i acabada en punta roma. Pot arribar a mesurar fins a 75 cm.','Gairebé amenaçada','https:////upload.wikimedia.org/wikipedia/commons/thumb/3/3c/ViperaAspis_1469AE.jpg/130px-ViperaAspis_1469AE.jpg');
/*!40000 ALTER TABLE `animals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `families`
--

DROP TABLE IF EXISTS `families`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `families` (
  `codi` int(11) NOT NULL,
  `nom` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`codi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `families`
--

LOCK TABLES `families` WRITE;
/*!40000 ALTER TABLE `families` DISABLE KEYS */;
INSERT INTO `families` VALUES (1,'Amfibis'),(2,'Rèptils');
/*!40000 ALTER TABLE `families` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordres`
--

DROP TABLE IF EXISTS `ordres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordres` (
  `codi` int(5) NOT NULL,
  `familia` int(5) DEFAULT NULL,
  `nom` varchar(40) DEFAULT NULL,
  `descripcio` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`codi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordres`
--

LOCK TABLES `ordres` WRITE;
/*!40000 ALTER TABLE `ordres` DISABLE KEYS */;
INSERT INTO `ordres` VALUES (1,1,'Salamàndrids','Els salamàndrids (Salamandridae) són una família d\'amfibis l\'ordre dels urodels.'),(2,1,'Discoglòssids','La família dels discoglòssids conté dos gèneres d\'amfibis: Alytes i Discoglossus. Tots dos gèneres es troben a Europa i al nord-oest d\'Àfrica.'),(3,1,'Pelobàtids','La família d\'anurs Pelobatidae té dos representants a la península Ibèrica que també es troben als Països Catalans i que, de vegades, es consideren famílies diferents.'),(4,1,'Pelodítids','Pelodytes caucasicus Pelodytes ibericus Pelodytes punctatus'),(5,1,'Bufònids','Els bufònids (Bufonidae) són una família d\'amfibis que agrupa els veritables gripaus.'),(6,1,'Hílids','Els hílids (Hylidae) són una família d\'amfibis anurs molt variables morfològicament i ecològicament. Alguns són verds, tot i que tenen una gran diversitat de pigmentacions. Generalment s\'alimenten d\'insectes, però algunes cacen petits vertebrats. D\'altres, com Cyclorana, s\'enterren al fang i es passen bona part de la vida sota terra.'),(7,1,'Rànids','Els rànids (Ranidae) són una família de granotes del subordre dels neobatracis que ocupa tota la Terra llevat d\'Austràlia i de l\'Antàrtida.'),(8,2,'Testudínids','Els testudínids (Testudinidae) són una família de tortugues terrestres herbívores de l\'ordre Testudines. Podem trobar tortugues d\'aquesta família a tots els continents excepte a Oceania i a l\'Antàrtida. Algunes de les seves espècies arriben a mides gegantines, com la tortuga gegant d\'Aldabra o la tortuga gegant de les Galápagos.'),(9,2,'Geoemídids','Els geoemídids (Geoemydidae) o batagúrids (Bataguridae) són una família de tortugues composta per unes 75 espècies.'),(10,2,'Emídids','Els emídids (Emydidae) són una família de tortugues aquàtiques i semiaquàtiques carnívores que inclou més de 80 espècies, entre les quals destaquen la tortuga d\'estany europea (Emys orbicularis), la tortuga d\'orelles vermelles, la tortuga d\'orelles grogues, la tortuga esquena diamant (Malaclemys terrapin), la tortuga pintada i el gènere Terrapene de les tortugues de caixa americanes.'),(11,2,'Quelònids','Els quelònids (Cheloniidae) són una família de tortugues que pertanyen a la superfamília de les tortugues marines Chelonioidea.'),(12,2,'Dermoquèlids','La tortuga llaüt o tortuga de cuir[1] (Dermochelys coriacea) és la més gran de les set espècies actuals de tortugues marines i de tortugues en general, i arriba a una longitud de 2 metres i a un pes de més de 600 quilos. No té una veritable closca dura, però el seu dors està protegit per una mena de cuirassa de pell endurida amb aspecte de cuir. És l\'únic representant actual del grup dels Dermochelyoidae, el clade de les tortugues amb dors amb aspecte de cuir, conegut també per diverses espècies fòssils, amb tortugues gegants com l\'archelon. La tortuga llaüt es troba en tots els oceans del planeta, però està greument amenaçada per les xarxes de pesca, la contaminació i la urbanització del litoral. Figura en la llista de la UICN de les espècies en vies de desaparició i és objectiu de convenis internacionals i de programes internacionals de protecció i de conservació.'),(13,2,'Gecònids','Els dragons[1] són rèptils de la família dels gecònids (Gekkonidae). N\'existeixen unes 950 espècies repartits en 51 gèneres,[2][3][4][5][6][7] distribuïdes per zones tropicals i subtropicals de tot el món incloent la conca mediterrània al límit nord de distribució. Només se\'n troben quatre espècies a Europa. Són principalment nocturns, tot i que a les regions temperades es poden veure actius durant el dia. Gairebé tots els dragons són capaços d\'emetre sons que poden anar des d\'un petit xiscle, en les espècies més petites, a un lladruc estrident. Són grans escaladors, ja que presenten uns coixinets adherents als dits. Aquests coixinets són recoberts per unes estructures microscòpiques semblants a pèls, anomenats setes, que els permeten d\'enfilar-se per superfícies llises.'),(14,2,'Escíncids','Els escíncids són una família de rèptils escatosos del subordre dels saures. Amb altres famílies de llangardaixos, com Lacertidae, formen l\'infraordre Scincomorpha. Aquesta família inclou nombrosos gèneres i espècies, difosos especialment a Àfrica, al sud d\'Àsia i a Austràlia. Hi ha pocs representants als Països Catalans, entre aquests hi ha la bívia ibèrica que viu al País Valencià. Normalment els escíncids estan proveïts d\'extremitats de poca longitud o bé molt febles, i algunes espècies no en tenen. Gairebé tots els escíncids són de dimensions petites o mitjanes. Tenen el tronc molt allargat i el cap aixafat. Són ovípars o vivípars, especialment diürns i d\'hàbitats molt diversos. Una gran part de les espècies habiten a la sorra on els hi agrada enterrar-se. Hi ha altres però que són arborícoles i tenen la cua prènsil. Els escíncids són generalment carnívors.'),(15,2,'Ànguids','La distribució d\'Anguidae abasta el vell i nou món, i falta completament absent solament a Austràlia. A Europa les espècies més conegudes són Anguis fragilis i Pseudopus apodus (Ophisaurus apodus). A Amèrica del Nord es coneixen diverses espècies d\'Ophisaurus, i a Amèrica Central moltes espècie de Celestus, Diploglossus i Abronia.'),(16,2,'Lacèrtids','Els lacèrtids (Lacertidae) són una família de rèptils escatosos del subordre del saures, oriünds d\'Europa, Àfrica i Àsia. Inclou diverses espècies popularment anomenades llangardaix o lluert, i sargantana o serenalla,[1] (sanglantana/singlantana a Catalunya del Nord, singratalla/sarnalla al Pallars).'),(17,2,'Blànids','Blanus és un gènere de rèptils escatosos de la família Amphisbaenidae, coneguts vulgarment com a sargantanes cegues, aquest gènere habita als voltants del Mar Mediterrani i comprèn a les úniques espècies d\'amfisbenis que habiten a Europa.'),(18,2,'Colúbrids','Un colúbrid o colobra[1] és una serp no verinosa per diferenciar-les dels escurçons, que sí que ho són. Tenen costums molt diversos, i se solen alimentar de petits mamífers, amfibis o rèptils. Formen part de la família de Colubridae. És una ampla classificació de serps que inclou força més de la meitat de totes les espècies de serp que hi ha a la Terra. Els colúbrids no són verinosos (o bé el seu verí no és perillós per als éssers humans) i normalment són inofensius, tot i que alguns poden fer mossegades força fortes. Posseeixen grans plaques al cap. L\'ull és de pupil·la rodona i té contacte amb les escates labials. La cua és relativament llarga. El musell és arrodonit. A la península Ibèrica hi ha unes 10 espècies, que viuen generalment en els llocs àrids:'),(19,2,'Vipèrids','Els vipèrids (Viperidae) constitueixen una família que inclou quatre subfamílies de serps verinoses (escurçons, cròtals):');
/*!40000 ALTER TABLE `ordres` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-16  0:21:43
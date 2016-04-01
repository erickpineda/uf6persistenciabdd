##Objetivos: Entender el funcionamiento de JDBC

##JDBC
JDBC es la API de Java que permite acceso a cualquier fuente de datos tabulados.
En los últimos tiempos se han popularizado las bases de datos portables que funcionan sin sevidor y que por tanto pueden ser distribuidas con el programa.

####Actividad

Tenemos una base de datos donde se encuentra el nombre de los colores en diferentes idiomas. La estructura de datos es esta:
```
MariaDB [colors]> desc colors;
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| id       | int(11)      | NO   | PRI | NULL    | auto_increment |
| angles   | varchar(255) | YES  |     | NULL    |                |
| castella | varchar(255) | YES  |     | NULL    |                |
| nom      | varchar(255) | NO   |     | NULL    |                |
| frances  | varchar(255) | YES  |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
5 rows in set (0.18 sec)

MariaDB [colors]>
```
#####Configurar el servidor MySQL

Base de datos [aquí](/traductorcolores/src/main/resources/colors.sql).

En la clase: <code>net.erickpineda.traductorcolores.util.ConexionBDD</code>
- <code>private String bd = "colors";</code>
- <code>private String user = "user";</code>
- <code>private String password = "password";</code>
- <code>private String host = "localhost";</code>

#####Generar jar

- <code>$ cd /ruta/a/repositorio</code>
- <code>$ gradle build</code>

#####Ejecutar jar

- <code>$ java -jar traductorcolores>java -jar build\libs\traductorcolores-sources.jar</code>

![Alt Ventana principal](/traductorcolores/src/main/resources/img/ventana-traductordecolores-01.PNG)

- Escoger uno de los idiomas

![Alt Selector de idioma](/traductorcolores/src/main/resources/img/ventana-traductordecolores-02.PNG)

- Pulsar ENTER o clic en el botón buscar para ver la traducción

![Alt Resultado](/traductorcolores/src/main/resources/img/ventana-traductordecolores-03.PNG)

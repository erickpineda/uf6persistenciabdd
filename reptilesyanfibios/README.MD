##Objetivos: Utilizar JDBC para consultar una base de datos relacional

###Reptiles y anfibios de Cataluña
Según la Wikipedia existen unas 6.350 especies de anfibios en todo el mundo, de las cuales sólo 16 habitan a Cataluña, y de todas estas sólo una, el tritón del Montseny, es puramente endémica de Cataluña.

En cuanto a reptiles, hay más de 8.200 especies de las cuales 35 habitan en Cataluña.

Habéis conseguido una base de datos con la información sobre estas especies y algunas de sus datos que pueden descargar desde [aquí](/reptilesyanfibios/src/main/resources/reptils.sql).

<code>Toda la información de esta base de datos se ha obtenido de la Wikipedia ([dirección](https://ca.wikipedia.org/wiki/Llista_d'amfibis_i_r%C3%A8ptils_de_Catalunya#Testud.C3.ADnids)) y por lo tanto está bajo sus [condiciones de uso](https://wikimediafoundation.org/wiki/Terms_of_Use/ca)</code>.

La estructura de datos de la base de datos es la siguiente:

```
MariaDB [reptils]> desc families;
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| codi  | int(11)     | NO   | PRI | NULL    |       |
| nom   | varchar(40) | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+
2 rows in set (0.01 sec)

MariaDB [reptils]> desc ordres;
+------------+---------------+------+-----+---------+-------+
| Field      | Type          | Null | Key | Default | Extra |
+------------+---------------+------+-----+---------+-------+
| codi       | int(5)        | NO   | PRI | NULL    |       |
| familia    | int(5)        | YES  |     | NULL    |       |
| nom        | varchar(40)   | YES  |     | NULL    |       |
| descripcio | varchar(1000) | YES  |     | NULL    |       |
+------------+---------------+------+-----+---------+-------+
4 rows in set (0.01 sec)

MariaDB [reptils]> desc animals;
+------------+---------------+------+-----+---------+----------------+
| Field      | Type          | Null | Key | Default | Extra          |
+------------+---------------+------+-----+---------+----------------+
| codi       | int(11)       | NO   | PRI | NULL    | auto_increment |
| nom        | varchar(40)   | YES  |     | NULL    |                |
| ordre      | int(5)        | YES  |     | NULL    |                |
| especie    | varchar(100)  | YES  |     | NULL    |                |
| descripcio | varchar(1000) | YES  |     | NULL    |                |
| estat      | varchar(50)   | YES  |     | NULL    |                |
| imatge     | varchar(255)  | YES  |     | NULL    |                |
+------------+---------------+------+-----+---------+----------------+
7 rows in set (0.01 sec)

MariaDB [reptils]>
```
#####Configurar el servidor MySQL

Base de datos [aquí](/reptilesyanfibios/src/main/resources/reptils.sql)

En la clase: <code>net.erickpineda.reptilesyanfibios.util.ConexionBDD</code>
- <code>private String bd = "reptils";</code>
- <code>private String user = "user";</code>
- <code>private String password = "password";</code>
- <code>private String host = "localhost";</code>

#####Generar jar

- <code>$ cd /ruta/a/repositorio</code>
- <code>$ gradle build</code>

#####Ejecutar jar

- <code>$ java -jar build\libs\reptilesyanfibios-sources.jar</code>

![Alt Ventana principal](/reptilesyanfibios/src/main/resources/img/ventana-reptilesyanfibios-01.PNG)

- Filtrar

![Alt Primero debe escogerse la familia](/reptilesyanfibios/src/main/resources/img/ventana-reptilesyanfibios-02.PNG)

- Cambiar el tema de la aplicación

![Alt Escoger tema](/reptilesyanfibios/src/main/resources/img/ventana-reptilesyanfibios-03.PNG)

- Al actualizar uno de los campos

![Alt Guardar los cambios](/reptilesyanfibios/src/main/resources/img/ventana-reptilesyanfibios-04.PNG)

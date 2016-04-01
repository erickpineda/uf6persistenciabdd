package net.erickpineda.reptilesyanfibios.util;

import java.util.ArrayList;
import java.util.List;

import net.erickpineda.reptilesyanfibios.models.Animal;
import net.erickpineda.reptilesyanfibios.models.Familia;
import net.erickpineda.reptilesyanfibios.models.Ordre;

public class Find {
  private ConexionBDD con;
  private static final String FAMILIA = "SELECT %s FROM families f %s";
  private static final String ORDRE = "select distinct %s from ordres o inner join families f on o.familia = f.codi where %s";
  private static final String ANIMAL = "select distinct %s from animals a inner join ordres o on a.ordre = o.codi where %s";
  private static final String UPDATE = "update animals a set %s where a.codi = %s";

  public Find(final ConexionBDD con) {
    this.con = con;
  }

  /**
   * Actualizar la base de datos la tabla animals, a través de un animal como parámetro.
   * 
   * @param a animal como parámetro a cambiar.
   */
  public void update(final Animal a) {
    String set = String.format("a.nom=\"%s\",a.especie=\"%s\",a.descripcio=\"%s\"", a.getNom(),
        a.getEspecie(), a.getDescripcio());
    String consulta = String.format(UPDATE, set, a.getCodi());
    // System.out.println(consulta);
    con.insertarDatos(consulta);
  }

  /**
   * 
   * @return retorna una lista de familias a través de una lista consulta SQL.
   */
  public List<Familia> allFamilias() {
    List<String> lista = con.consultar(String.format(FAMILIA, "f.codi, f.nom", ""));
    List<Familia> familias = new ArrayList<Familia>();
    for (int i = 0; i < lista.size(); i += 2) {
      int aCodi = Integer.valueOf(lista.get(i));
      String aNom = lista.get(i + 1);
      if (!String.valueOf(aCodi).isEmpty()) {
        familias.add(new Familia(aCodi, aNom));
      }
    }
    return familias;
  }

  /**
   * 
   * @param nom parámetro que será el nombre de la familia para hacer la búsqueda.
   * @return retorna una lista de familias a través de un nombre de familia en especifico que pasa
   *         como parámetro.
   */
  public List<Familia> allFamiliasByNom(String nom) {
    String where = String.format("where f.nom = \"%s\"", nom);
    List<String> lista = con.consultar(String.format(FAMILIA, "f.codi, f.nom", where));
    List<Familia> familias = new ArrayList<Familia>();
    for (int i = 0; i < lista.size(); i += 2) {
      int aCodi = Integer.valueOf(lista.get(i));
      String aNom = lista.get(i + 1);
      if (!String.valueOf(aCodi).isEmpty()) {
        familias.add(new Familia(aCodi, aNom));
      }
    }
    return familias;
  }

  /**
   * 
   * @param nom nombre de familia que buscará el ordre.
   * @return retorna una lista de Ordres a través de un nombre de familia en especifico que pasa por
   *         parámetro.
   */
  public List<Ordre> allOrdresByNomFamilia(String nom) {
    String columnas = "o.codi,o.familia,o.nom,o.descripcio";
    String where = String.format("f.nom = \"%s\"", nom);
    List<String> lista = con.consultar(String.format(ORDRE, columnas, where));
    List<Ordre> ordres = new ArrayList<Ordre>();
    for (int j = 0; j < lista.size(); j += 4) {
      int oCodi = Integer.valueOf(lista.get(j));
      int oFamilia = Integer.valueOf(lista.get(j + 1));
      String oNom = lista.get(j + 2);
      String oDescripcio = lista.get(j + 3);
      if (!String.valueOf(oCodi).isEmpty()) {
        ordres.add(new Ordre(oCodi, oFamilia, oNom, oDescripcio));
      }
    }
    return ordres;
  }

  /**
   * 
   * @param nom nombre del ordre que pasa por parámetro, para realizar la búsqueda.
   * @return retorna una lista de animales a través de un nombre de ordre en especificao que pasa
   *         por parámetro.
   */
  public List<Animal> allAnimalsByNomOrdre(String nom) {
    String columnas = "a.codi,a.nom,a.ordre,a.especie,a.descripcio,a.estat,a.imatge";
    String where = String.format("o.nom = \"%s\"", nom);
    List<String> lista = con.consultar(String.format(ANIMAL, columnas, where));
    List<Animal> ordres = new ArrayList<Animal>();
    for (int k = 0; k < lista.size(); k += 7) {
      int aCodi = Integer.valueOf(lista.get(k));
      String aNom = lista.get(k + 1);
      int aOrdre = Integer.valueOf(lista.get(k + 2));
      String aEspecie = lista.get(k + 3);
      String aDescripcio = lista.get(k + 4);
      String aEstat = lista.get(k + 5);
      String aImatge = lista.get(k + 6).replace("////", "//");
      if (!String.valueOf(aCodi).isEmpty()) {
        ordres.add(new Animal(aCodi, aNom, aOrdre, aEspecie, aDescripcio, aEstat, aImatge));
      }
    }
    return ordres;
  }
}

package net.erickpineda.reptilesyanfibios.models;

import java.util.List;

public class Ordre {
  private int codi;
  private int familia;
  private String nom;
  private String descripcio;
  private List<Animal> animals;

  public Ordre() {

  }

  public Ordre(final int codi, final int familia, final String nom, final String descripcio) {
    setCodi(codi);
    setFamilia(familia);
    setNom(nom);
    setDescripcio(descripcio);
  }

  public int getCodi() {
    return codi;
  }

  public void setCodi(int codi) {
    this.codi = codi;
  }

  public int getFamilia() {
    return familia;
  }

  public void setFamilia(int familia) {
    this.familia = familia;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getDescripcio() {
    return descripcio;
  }

  public void setDescripcio(String descripcio) {
    this.descripcio = descripcio;
  }

  public List<Animal> getAnimals() {
    return animals;
  }

  public void setAnimals(List<Animal> animals) {
    this.animals = animals;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Ordre))
      return false;
    if (obj == this)
      return true;
    return this.nom.equals(((Ordre) obj).nom);
  }

  @Override
  public String toString() {
    return "Ordre [getCodi()=" + getCodi() + ", getFamilia()=" + getFamilia() + ", getNom()="
        + getNom() + ", getDescripcio()=" + getDescripcio() + ", getAnimals()=" + getAnimals()
        + "]";
  }
}

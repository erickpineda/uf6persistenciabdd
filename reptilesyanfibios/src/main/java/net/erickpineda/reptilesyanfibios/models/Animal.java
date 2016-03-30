package net.erickpineda.reptilesyanfibios.models;

public class Animal {
  private int codi;
  private String nom;
  private int ordre;
  private String especie;
  private String descripcio;
  private String estat;
  private String imatge;

  public Animal() {

  }

  public Animal(int cod, String nom, int ord, String esp, String des, String est, String img) {
    setCodi(cod);
    setNom(nom);
    setOrdre(ord);
    setEspecie(esp);
    setDescripcio(des);
    setEstat(est);
    setImatge(img);
  }

  public int getCodi() {
    return codi;
  }

  public void setCodi(int codi) {
    this.codi = codi;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getOrdre() {
    return ordre;
  }

  public void setOrdre(int ordre) {
    this.ordre = ordre;
  }

  public String getEspecie() {
    return especie;
  }

  public void setEspecie(String especie) {
    this.especie = especie;
  }

  public String getDescripcio() {
    return descripcio;
  }

  public void setDescripcio(String descripcio) {
    this.descripcio = descripcio;
  }

  public String getEstat() {
    return estat;
  }

  public void setEstat(String estat) {
    this.estat = estat;
  }

  public String getImatge() {
    return imatge;
  }

  public void setImatge(String imatge) {
    this.imatge = imatge;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Animal))
      return false;
    if (obj == this)
      return true;
    return this.estat.equals(((Animal) obj).estat);
  }

  @Override
  public String toString() {
    return "Animal [getCodi()=" + getCodi() + ", getNom()=" + getNom() + ", getOrdre()="
        + getOrdre() + ", getEspecie()=" + getEspecie() + ", getDescripcio()=" + getDescripcio()
        + ", getEstat()=" + getEstat() + ", getImatge()=" + getImatge() + "]";
  }
}

package net.erickpineda.reptilesyanfibios.models;

import java.util.List;

public class Familia {
  private int codi;
  private String nom;
  private List<Ordre> ordres;

  public Familia() {

  }

  public Familia(final int codi, final String nom) {
    setCodi(codi);
    setNom(nom);
  }

  public Familia(final int codi, final String nom, final List<Ordre> ordres) {
    setCodi(codi);
    setNom(nom);
    setOrdres(ordres);
  }

  public void addFam(final Ordre ordre) {
    if (ordre != null) {
      ordres.add(ordre);
    }
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

  public List<Ordre> getOrdres() {
    return ordres;
  }

  public void setOrdres(List<Ordre> ordres) {
    this.ordres = ordres;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Familia))
      return false;
    if (obj == this)
      return true;
    return this.nom.equals(((Familia) obj).nom);
  }

  @Override
  public String toString() {
    return "Familia [getCodi()=" + getCodi() + ", getNom()=" + getNom() + ", getOrdres()="
        + getOrdres() + "]";
  }
}

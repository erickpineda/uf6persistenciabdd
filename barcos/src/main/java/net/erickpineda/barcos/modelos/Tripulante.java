package net.erickpineda.barcos.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tripulante")
public class Tripulante {
  @Id
  @Column(name = "dni")
  private String dni;
  @Column(name = "nom")
  private String nom;
  @Column(name = "rang")
  private String rang;
  @Column(name = "barco_id")
  private String barcoId;

  public Tripulante() {
  }

  public Tripulante(String dni, String nom, String rang) {
    setDni(dni);
    setNom(nom);
    setRang(rang);
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getRang() {
    return rang;
  }

  public void setRang(String rang) {
    this.rang = rang;
  }

  public String getBarcoId() {
    return barcoId;
  }

  public void setBarcoId(String barcoId) {
    this.barcoId = barcoId;
  }

  @Override
  public String toString() {
    return "Tripulante [getDni()=" + getDni() + ", getNom()=" + getNom() + ", getRang()="
        + getRang() + ", getBarcoId()=" + getBarcoId() + "]";
  }
}

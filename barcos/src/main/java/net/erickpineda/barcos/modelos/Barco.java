package net.erickpineda.barcos.modelos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "barco")
public class Barco {
  @Id
  @Column(name = "matricula")
  private String matricula;
  @Column(name = "nom")
  private String nom;
  @Column(name = "imagen")
  private String imagen;
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "barco_id")
  private List<Tripulante> tripulantes;

  public Barco() {
  }

  public Barco(String matricula, String nom) {
    setMatricula(matricula);
    setNom(nom);
    setImagen("SIN IMAGEN");
  }

  public String getCapitan() {
    for (Tripulante t : tripulantes) {
      if (t.getRang().equalsIgnoreCase("Capitan")) {
        return t.getNom();
      }
    }
    return "SIN CAPITAN";
  }

  public boolean puedeZarpar() {
    if (!getCapitan().equalsIgnoreCase("SIN CAPITAN")) {
      for (Tripulante t : getTripulantes()) {
        if (t.getRang().equalsIgnoreCase("Marinero")) {
          return true;
        }
      }
    }
    return false;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  public List<Tripulante> getTripulantes() {
    return tripulantes;
  }

  public void setTripulantes(List<Tripulante> tripulantes) {
    this.tripulantes = tripulantes;
  }

  @Override
  public String toString() {
    return "Barco [getMatricula()=" + getMatricula() + ", getNom()=" + getNom() + ", getImagen()=" + getImagen()
        + ", getTripulantes()=" + getTripulantes() + "]";
  }
}
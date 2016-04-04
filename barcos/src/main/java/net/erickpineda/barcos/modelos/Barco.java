package net.erickpineda.barcos.modelos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "barco")
public class Barco {
    @Id
    private String matricula;
    private String nom;
    @OneToMany
    @JoinColumn(name = "tripulante_id")
    private List<Tripulante> tripulantes;

    public Barco() {
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

    public List<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(List<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }
}

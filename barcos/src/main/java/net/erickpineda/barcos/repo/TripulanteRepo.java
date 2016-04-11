package net.erickpineda.barcos.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import net.erickpineda.barcos.modelos.Tripulante;

public class TripulanteRepo {
  private EntityManager em;
  private static final String BDD = "barcos";
  private EntityManagerFactory emf = Persistence.createEntityManagerFactory(BDD);

  public TripulanteRepo() {
    em = emf.createEntityManager();
  }

  public TripulanteRepo(EntityManager em) {
    setEntityManager(em);
  }

  public void setEntityManager(EntityManager em) {
    this.em = em;
  }

  public void reabrirConexion() {
    this.em = emf.createEntityManager();
  }

  public void cerrarConexion() {
    this.em.close();
  }

  public boolean crearTripulante(Tripulante tripulante) {
    Tripulante existe = em.find(Tripulante.class, tripulante.getDni());
    if (existe == null) {
      em.getTransaction().begin();
      em.persist(tripulante);
      em.getTransaction().commit();
      return true;
    }
    return false;
  }

  public void actualizarTripulante(Tripulante tripulante) {
    Tripulante existente = em.find(Tripulante.class, tripulante.getDni());
    System.out.println(existente);
    if (existente != null) {
      em.getTransaction().begin();
      existente.setDni(tripulante.getDni());
      existente.setNom(tripulante.getNom());
      existente.setRang(tripulante.getRang());
      existente.setBarcoId(tripulante.getBarcoId());
      em.merge(existente);
      em.getTransaction().commit();
    }
  }

  public Tripulante findByDni(String dni) {
    String consulta = "SELECT t FROM Tripulante t WHERE t.dni=:dni";
    TypedQuery<Tripulante> q = em.createQuery(consulta, Tripulante.class);
    q.setParameter("dni", dni);
    Tripulante tripulante = q.getSingleResult();
    if (tripulante != null) {
      return tripulante;
    }
    return null;
  }

  public Tripulante findByNom(String nom) {
    String consulta = "SELECT t FROM Tripulante t WHERE t.nom=:nom";
    TypedQuery<Tripulante> q = em.createQuery(consulta, Tripulante.class);
    q.setParameter("nom", nom);
    Tripulante tripulante = q.getSingleResult();
    if (tripulante != null) {
      return tripulante;
    }
    return null;
  }

  public Tripulante findByRang(String rang) {
    String consulta = "SELECT t FROM Tripulante t WHERE t.rang=:rang";
    TypedQuery<Tripulante> q = em.createQuery(consulta, Tripulante.class);
    q.setParameter("rang", rang);
    Tripulante tripulante = q.getSingleResult();
    if (tripulante != null) {
      return tripulante;
    }
    return null;
  }

  public List<Tripulante> findListByRang(String rang) {
    String consulta = "SELECT t FROM Tripulante t WHERE t.rang=:rang";
    TypedQuery<Tripulante> q = em.createQuery(consulta, Tripulante.class);
    q.setParameter("rang", rang);
    List<Tripulante> tripulantes = q.getResultList();
    if (tripulantes != null) {
      return tripulantes;
    }
    return null;
  }

  public List<Tripulante> findAll() {
    TypedQuery<Tripulante> q = em.createQuery("SELECT t FROM Tripulante t", Tripulante.class);
    List<Tripulante> tripulantes = q.getResultList();
    if (tripulantes != null) {
      return tripulantes;
    }
    return null;
  }
}

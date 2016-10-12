package net.erickpineda.barcos.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import net.erickpineda.barcos.modelos.Barco;

public class BarcoRepo {
  private EntityManager em;
  private static final String BDD = "barcos";
  private EntityManagerFactory emf = Persistence.createEntityManagerFactory(BDD);

  public BarcoRepo() {
	em = emf.createEntityManager();
  }

  public BarcoRepo(EntityManager em) {
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

  public void flush() {
	this.em.flush();
  }

  public boolean crearBarco(Barco barco) {
	Barco existe = em.find(Barco.class, barco.getMatricula());
	if (existe == null) {
	  em.getTransaction().begin();
	  em.persist(barco);
	  em.getTransaction().commit();
	  return true;
	}
	return false;
  }

  public void actualizarBarco(Barco barco) {
	Barco existente = em.find(Barco.class, barco.getMatricula());
	if (existente != null) {
	  em.getTransaction().begin();
	  existente.setMatricula(barco.getMatricula());
	  existente.setNom(barco.getNom());
	  existente.setImagen(barco.getImagen());
	  existente.setTripulantes(barco.getTripulantes());
	  em.merge(existente);
	  em.getTransaction().commit();
	}
  }

  public Barco findByMatricula(String matricula) {
	String consulta = "SELECT b FROM Barco b WHERE b.matricula=:matricula";
	TypedQuery<Barco> q = em.createQuery(consulta, Barco.class);
	q.setParameter("matricula", matricula);
	Barco barco = q.getSingleResult();
	if (barco != null) {
	  return barco;
	}
	return null;
  }

  public Barco findByNom(String nom) {
	String consulta = "SELECT b FROM Barco b WHERE b.nom=:nom";
	TypedQuery<Barco> q = em.createQuery(consulta, Barco.class);
	q.setParameter("nom", nom);
	Barco barco = q.getSingleResult();
	if (barco != null) {
	  return barco;
	}
	return null;
  }

  public List<Barco> findAll() {
	TypedQuery<Barco> q = em.createQuery("SELECT b FROM Barco b", Barco.class);
	List<Barco> barcos = q.getResultList();
	if (barcos != null) {
	  return barcos;
	}
	return null;
  }
}
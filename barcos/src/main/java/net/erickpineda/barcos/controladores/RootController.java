package net.erickpineda.barcos.controladores;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.BarcoRepo;
import net.erickpineda.barcos.repo.TripulanteRepo;
import net.erickpineda.barcos.util.Asistente;
import net.erickpineda.barcos.util.Importar;
import net.erickpineda.barcos.util.Msj;
import net.erickpineda.barcos.util.RandomString;

public class RootController {
  @FXML
  private TabPane tabPane;
  @FXML
  private TabPaneController tabPaneController;
  private BarcoRepo barcoRepo;
  private TripulanteRepo tripRepo;
  private List<Barco> barcos;
  private List<Tripulante> tripulantes;

  @FXML
  public void initialize() {
	try {
	  barcoRepo = new BarcoRepo();
	  tripRepo = new TripulanteRepo();
	  // setupBdd();
	} catch (Exception e) {
	  tabPaneController.desactivarPaneles();
	  Msj.err("Sin conexión", "No se ha podido establecer la conexión a la base de datos");
	}
  }

  private void setupBdd() {
	importarBarcos();
	importarTripulantes();
	agregarTripulantesABarcos();
  }

  private void importarBarcos() {
	Importar im = new Importar();
	im.procesarDatosExistentes(Barco.class);
	barcos = im.getBarcos();

	for (Barco b : barcos) {
	  barcoRepo.crearBarco(b);
	}

	if (im.isOk()) {
	  Msj.inf("Todo correcto", "Los barcos se han importado con éxito");
	}
  }

  private void importarTripulantes() {
	Importar im = new Importar();
	im.procesarDatosExistentes(Tripulante.class);
	tripulantes = im.getTripulantes();

	for (Tripulante t : tripulantes) {
	  tripRepo.crearTripulante(t);
	}

	if (im.isOk()) {
	  Msj.inf("Todo correcto", "Los tripulantes se han importado con éxito");
	}
  }

  private void agregarTripulantesABarcos() {
	barcos = barcoRepo.findAll();
	tripulantes = tripRepo.findAll();

	Barco b = barcos.get(0);
	Tripulante t = tripulantes.get(0);
	t.setBarcoId(b.getMatricula());
	b.getTripulantes().add(t);
	tripRepo.actualizarTripulante(t);
	barcoRepo.actualizarBarco(b);

	b = barcos.get(1);
	t = tripulantes.get(1);
	t.setBarcoId(b.getMatricula());
	b.getTripulantes().add(t);
	tripRepo.actualizarTripulante(t);
	barcoRepo.actualizarBarco(b);
  }
}

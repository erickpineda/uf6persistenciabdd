package net.erickpineda.barcos.controladores;

import java.io.File;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.erickpineda.barcos.App;
import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.BarcoRepo;
import net.erickpineda.barcos.repo.TripulanteRepo;
import net.erickpineda.barcos.util.Asistente;
import net.erickpineda.barcos.util.Importar;
import net.erickpineda.barcos.util.Msj;

public class TripulanteController {
  @FXML
  private TextField dni;
  @FXML
  private TextField nombre;
  @FXML
  private TextField rango;
  @FXML
  private ComboBox<String> cbBarco;
  @FXML
  private ComboBox<String> cbEditar;
  @FXML
  private ImageView imagen;
  @FXML
  private Pane panelTabla;
  @FXML
  private TableView<Tripulante> tabla;
  @FXML
  private Label indicador;
  @FXML
  private Button btnCrear;
  @FXML
  private Button btnEditar;
  private List<Tripulante> tripulantes;
  private TripulanteRepo triRepo;
  private BarcoRepo barcoRepo;
  private Tripulante actual;

  @FXML
  public void initialize() {
	try {
	  triRepo = new TripulanteRepo();
	  barcoRepo = new BarcoRepo();
	  actualizarValoresPorDefecto();
	} catch (Exception e) {}
  }

  @FXML
  public void imagenClick(MouseEvent event) {
	File img = getFileChooser("Importar tripulantes", "*.png", "*.jpg").showOpenDialog(App.PRIMARY_STAGE);
	if (img != null) {
	  cambiarImagen(img.toURI().toString());
	}
  }

  @FXML
  public void crearClicked(MouseEvent event) {
	if (!indicador.isVisible() && camposOk()) {
	  if (dni.getText().matches(Asistente.REGEXP_DNI)) {
		actual.setDni(dni.getText());
		actual.setNom(nombre.getText());
		actual.setRang(rango.getText());

		String barcoId = cbBarco.getSelectionModel().getSelectedItem();
		if (barcoId != null && !barcoId.isEmpty()) {
		  actual.setBarcoId(barcoId);
		  Barco barco = barcoRepo.findByMatricula(barcoId);
		  List<Tripulante> flota = barco.getTripulantes();

		  if (flota != null && !flota.isEmpty()) {
			String capitan = barco.getCapitan();
			// Tripulante capitan = flota.stream().filter(t -> t.getRang().equalsIgnoreCase("Capitan")).findAny().get();

			if (!capitan.equals("SIN CAPITAN") && !actual.getRang().equalsIgnoreCase("Capitan")) {
			  barco.getTripulantes().add(actual);
			  barcoRepo.actualizarBarco(barco);
			  triRepo.crearTripulante(actual);
			  Msj.inf("Tripulante creado correctamente", "El tripulante " + actual.getNom() + " creado correctamente");
			  actualizarValoresPorDefecto();
			} else {
			  Msj.warn("Ya hay un capitán", "No pueden haber 2 capitanes en un sólo barco");
			  actualizarBarcos();
			}
		  }

		} else {
		  triRepo.crearTripulante(actual);
		  Msj.inf("Tripulante creado correctamente", "El tripulante " + actual.getNom() + " creado correctamente");
		  actualizarValoresPorDefecto();
		}
	  } else {
		Msj.warn("DNI Inválido", "El DNI: " + dni.getText() + " no es correcto.\nPatrón ej: 48756287M");
	  }
	} else {
	  Msj.warn("Campos vacíos", "Los campos DNI, Nombre y Rango no pueden estar vacíos");
	}
  }

  @FXML
  public void editarClicked(MouseEvent event) {
	if (indicador.isVisible() && camposOk()) {
	  triRepo.actualizarTripulante(actual);
	  Msj.inf("Editado el tripulante", "El tripulante " + actual.getNom() + " se ha editado correctamente");
	  actualizarValoresPorDefecto();
	} else {
	  Msj.warn("Campos vacíos", "Los campos Nombre y Rango no pueden estar vacíos");
	}
  }

  @FXML
  public void btnCancelar(MouseEvent event) {
	actualizarCampos("", "", "", "");
	actualizarValoresPorDefecto();
  }

  @FXML
  public void btnImportar(MouseEvent event) {
	File txt = getFileChooser("Importar tripulantes", "*.txt").showOpenDialog(App.PRIMARY_STAGE);
	if (txt != null) {
	  Importar importa = new Importar(txt);
	  importa.procesarDatos(Tripulante.class);
	  if (importa.isOk()) {
		panelTabla.setVisible(true);
		actualizarTabla(importa.getTripulantes());
	  } else {
		Msj.warn("Problemas con el fichero", "Puede que el fichero está vacío o los datos no están completos");
	  }
	}
  }

  @FXML
  public void btnExportar(MouseEvent event) {
	File txt = getFileChooser("Exportar tripulantes", "*.txt").showSaveDialog(App.PRIMARY_STAGE);
	if (txt != null) {

	}
  }

  @FXML
  public void cbEditarClicked(MouseEvent event) {
	cbEditar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		if (newValue != null && !newValue.isEmpty()) {
		  clickEnComboboxEditar(newValue);
		}
	  }

	  private void clickEnComboboxEditar(String newValue) {
		tripulantes.forEach(t -> {
		  if (t.getNom().equals(newValue)) {
			actual = t;
			indicador.setVisible(true);
			dni.setEditable(false);
			cbBarco.setDisable(true);
			panelTabla.setVisible(false);
			btnEditar.setDisable(false);
			btnCrear.setDisable(true);
			actualizarBarcos();
			actualizarCampos(t.getDni(), t.getNom(), t.getRang(), "nullasd");
		  }
		});
	  }
	});
  }

  private boolean camposOk() {
	return (!dni.getText().isEmpty() && !nombre.getText().isEmpty() && !rango.getText().isEmpty());
  }

  @SuppressWarnings("unchecked")
  private void actualizarTabla(List<Tripulante> lista) {
	if (tabla != null) {
	  ObservableList<Tripulante> data = FXCollections.observableArrayList(lista);

	  // Se definen las columnas
	  TableColumn<Tripulante, String> c1 = (TableColumn<Tripulante, String>) tabla.getColumns().get(0);
	  TableColumn<Tripulante, String> c2 = (TableColumn<Tripulante, String>) tabla.getColumns().get(1);
	  TableColumn<Tripulante, String> c3 = (TableColumn<Tripulante, String>) tabla.getColumns().get(2);

	  for (int i = 0; i < lista.size(); i++) {
		tabla.getColumns().setAll(c1, c2, c3);
		// Se rellenan las celdas
		c1.setCellValueFactory(new PropertyValueFactory<Tripulante, String>("dni"));
		c2.setCellValueFactory(new PropertyValueFactory<Tripulante, String>("nom"));
		c3.setCellValueFactory(new PropertyValueFactory<Tripulante, String>("rang"));
	  }

	  tabla.setItems(data);
	}
  }

  /**
   * Método para actualizar cualquier valor cambiado por aquellos que son predeterminados.
   */
  private void actualizarValoresPorDefecto() {
	actualizarEditar();
	actualizarBarcos();
	actual = new Tripulante();
	dni.setEditable(true);
	cbBarco.setDisable(false);
	indicador.setVisible(false);
	btnEditar.setDisable(true);
	btnCrear.setDisable(false);
	panelTabla.setVisible(false);
  }

  private void actualizarEditar() {
	tripulantes = triRepo.findAll();
	if (tripulantes != null) {
	  vaciarCombobox(cbEditar);
	  tripulantes.forEach(t -> {
		addToCombobox(cbEditar, t.getNom());
	  });
	}
  }

  private void actualizarBarcos() {
	List<Barco> barcos = barcoRepo.findAll();
	if (barcos != null) {
	  vaciarCombobox(cbBarco);
	  barcos.forEach(b -> {
		addToCombobox(cbBarco, b.getMatricula());
	  });
	}
  }

  /**
   * Crea un FiliChoser (Provides support for standard platform file dialogs).
   * 
   * @param titulo título que llevará el FileChooser.
   * @return retorna un FileChooser que se usará para guardar o abrir los ficheros.
   */
  private FileChooser getFileChooser(final String titulo, final String... ext) {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle(titulo);

	ExtensionFilter soloTXT = new ExtensionFilter("Ficheros", ext);
	fileChooser.getExtensionFilters().add(soloTXT);
	fileChooser.setInitialDirectory(new File("."));
	return fileChooser;
  }

  /**
   * Agregar un valor a un combobox que pasa por parámetro, independientemente de cual sea.
   * 
   * @param cb combobox a agregar información.
   * @param data valor que se agregaá¡ al combobox.
   */
  private void addToCombobox(final ComboBox<String> cb, final String data) {
	if (!cb.getItems().contains(data)) {
	  cb.getItems().add(data);
	}
  }

  /**
   * Método que vacia un combobox que pasa como parámetro.
   * 
   * @param cb combobox a vaciar valores.
   */
  private void vaciarCombobox(final ComboBox<String> cb) {
	if (!cb.getItems().isEmpty() && cb.getItems() != null) {
	  cb.getItems().clear();
	}
  }

  private void actualizarCampos(String DNI, String nom, String rang, String img) {
	dni.setText(DNI);
	nombre.setText(nom);
	rango.setText(rang);
	cambiarImagen(img);
  }

  private void cambiarImagen(String img) {
	try {
	  imagen.setImage(new Image(img));
	} catch (Exception e) {
	  imagen.setImage(new Image(getClass().getResourceAsStream("/img/image.png")));
	}
  }
}

package net.erickpineda.barcos.controladores;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.TripulanteRepo;

public class TripulanteExploradorController {
  @FXML
  private ComboBox<String> cbDni;
  @FXML
  private ComboBox<String> cbNombre;
  @FXML
  private ComboBox<String> cbRango;
  @FXML
  private Label dniTripulante;
  @FXML
  private Label nombreTripulante;
  @FXML
  private Label rangoTripulante;
  @FXML
  private Label barcoTripulante;
  @FXML
  private Label dni;
  @FXML
  private Label nombre;
  @FXML
  private Label rango;
  @FXML
  private ImageView imagen;
  @FXML
  private Label enTripulacion;
  @FXML
  private TableView<Tripulante> tabla;
  @FXML
  private ProgressIndicator barraProgreso;
  /**
   * Lista de tripulantes que se está procesando en cada consulta.
   */
  private List<Tripulante> tripulantes;
  private TripulanteRepo triRepo;

  @FXML
  public void initialize() {
	try {
	  triRepo = new TripulanteRepo();
	  actualizarValoresPorDefecto();
	} catch (Exception e) {}
  }

  /**
   * Reúne los datos de otros métodos para actualizar todos los valores en una sola invocación.
   */
  private void actualizarValoresPorDefecto() {
	actualizarDni();
	actualizarNombre();
	actualizarRango();
	actualizarCampos("DNI", "Nombre", "Rango", "SIN IMAGEN", "Barco perteneciente");
  }

  @FXML
  public void cbDniClicked(MouseEvent event) {
	cbDni.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		cambiarNuevosValores(newValue, cbNombre, cbRango);
	  }
	});
  }

  @FXML
  public void cbNombreClicked(MouseEvent event) {
	cbNombre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		cambiarNuevosValores(newValue, cbDni, cbRango);
	  }
	});
  }

  @FXML
  public void cbRangoClicked(MouseEvent event) {
	cbRango.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		cambiarNuevosValores(newValue, cbNombre, cbDni);
	  }
	});
  }

  @FXML
  public void recargarClick(MouseEvent event) {
	actualizarValoresPorDefecto();
	tabla.getItems().clear();
	tabla.setVisible(false);
	mostrarCampos(true);
  }

  /**
   * A través del nuevo valor seleccionado en algún combobox, se rellenan los campos con la información dada.
   * 
   * @param newValue nuevo valor seleccionado para mostrar su información.
   * @param cb1 quitar el valor seleccionado del combobox 1.
   * @param cb2 quitar el valor seleccionado del combobox 2.
   */
  private void cambiarNuevosValores(String newValue, ComboBox<String> cb1, ComboBox<String> cb2) {
	if (newValue != null && !newValue.isEmpty() && tripulantes != null) {
	  // En esta lista se agregaran los tripulantes agrupados por rangos
	  List<Tripulante> porRangos = new ArrayList<Tripulante>();
	  tripulantes.forEach(t -> {
		if (t.getRang().equals(newValue)) {
		  porRangos.add(t);
		}
		// Siempre que la opción escogida no sea por rangos, se mostrarán los campos con la información dada sin tabla
		if (t.getNom().equals(newValue) || t.getDni().equals(newValue)) {
		  actualizarCampos(t.getDni(), t.getNom(), t.getRang(), "", t.getBarcoId());
		}
	  });
	  actualizarTabla(porRangos);
	  tabla.setVisible(porRangos != null && !porRangos.isEmpty());
	  mostrarCampos(porRangos == null || porRangos.isEmpty());
	  // Borrar los selectores de los otros combobox
	  cb1.getSelectionModel().clearSelection();
	  cb2.getSelectionModel().clearSelection();
	}
  }

  /**
   * Hace una petición a la base de datos y actualiza una lista con los tripulantes que se están tratando actualmente, y
   * rellena un combobox para usar la opción de: Búscar por DNI.
   */
  private void actualizarDni() {
	actualizarValoresDeCombobox(cbDni);
  }

  /**
   * Hace una petición a la base de datos y actualiza una lista con los tripulantes que se están tratando actualmente, y
   * rellena un combobox para usar la opción de: Búscar por Nombre.
   */
  private void actualizarNombre() {
	actualizarValoresDeCombobox(cbNombre);
  }

  /**
   * Hace una petición a la base de datos y actualiza una lista con los tripulantes que se están tratando actualmente, y
   * rellena un combobox para usar la opción de: Búscar por Rango.
   */
  private void actualizarRango() {
	actualizarValoresDeCombobox(cbRango);
  }

  /**
   * Hace una consulta a la BDD y a través de la lista {@link #tripulantes} que almacenará la información, se rellena el
   * combobox corespondiente de la selección de búsqueda.
   * 
   * @param cb combobox que se rellenará con la información de la lista {@link #tripulantes}.
   */
  private void actualizarValoresDeCombobox(ComboBox<String> cb) {
	tripulantes = triRepo.findAll();
	if (tripulantes != null) {
	  vaciarCombobox(cb);
	  tripulantes.forEach(t -> {
		if (cb.getId().equals("cbDni")) {
		  addToCombobox(cb, t.getDni());
		}
		if (cb.getId().equals("cbNombre")) {
		  addToCombobox(cb, t.getNom());
		}
		if (cb.getId().equals("cbRango")) {
		  addToCombobox(cb, t.getRang());
		}
	  });
	}
  }

  /**
   * Cuándo se filtra la búsqueda por rango, se rellena una tabla con los datos de los tripulantes y su barco
   * correspondiente si está en alguno.
   * 
   * @param lista Lista de tripulantes que pasa por parámetro para actualizar y rellenar la tabla.
   */
  @SuppressWarnings("unchecked")
  private void actualizarTabla(List<Tripulante> lista) {
	if (tabla != null && lista != null) {
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
   * Agregar un valor de tipo String a un combobox pasado por parámetro.
   * 
   * @param cb combobox que se le va a agregar el valor.
   * @param data valor que se agregará al combobox.
   */
  private void addToCombobox(final ComboBox<String> cb, final String data) {
	if (!cb.getItems().contains(data)) {
	  cb.getItems().add(data);
	}
  }

  /**
   * Método que vacía un combobox pasado como parámetro.
   * 
   * @param cb combobox a vaciar.
   */
  private void vaciarCombobox(final ComboBox<String> cb) {
	if (!cb.getItems().isEmpty() && cb.getItems() != null) {
	  cb.getItems().clear();
	}
  }

  /**
   * Actualizar cada campo de valor con información del tripulante.
   * 
   * @param DNI DNI identificativo.
   * @param nom Nombre del tripulante.
   * @param rang Que rango tiene.
   * @param img La ruta de la imágen para ese tripulante.
   * @param nomBarco Nombre del barco si pertenece a alguno.
   */
  private void actualizarCampos(String DNI, String nom, String rang, String img, String nomBarco) {
	dni.setText(DNI);
	nombre.setText(nom);
	rango.setText(rang);
	if (img.endsWith("png") || img.endsWith("jpg")) {
	  imagen.setImage(new Image(img));
	}
	if (nomBarco != null && !nomBarco.isEmpty()) {
	  enTripulacion.setText(nomBarco);
	} else {
	  enTripulacion.setText("NO ESTÁ EN NINGÚN BARCO");
	}
  }

  /**
   * Muestra u oculta los campos a rellenar según la orden dada que corresponda, true para que sea visible y false para
   * que se oculte.
   * 
   * @param yes semáforo para indicar si mostrar todos los campos o no.
   */
  private void mostrarCampos(boolean yes) {
	dniTripulante.setVisible(yes);
	nombreTripulante.setVisible(yes);
	rangoTripulante.setVisible(yes);
	barcoTripulante.setVisible(yes);

	dni.setVisible(yes);
	nombre.setVisible(yes);
	rango.setVisible(yes);
	imagen.setVisible(yes);
	enTripulacion.setVisible(yes);
  }
}
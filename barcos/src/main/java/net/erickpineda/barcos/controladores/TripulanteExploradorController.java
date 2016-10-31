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
   * Lista de tripulantes que se est� procesando en cada consulta.
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
   * Re�ne los datos de otros m�todos para actualizar todos los valores en una sola invocaci�n.
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
   * A trav�s del nuevo valor seleccionado en alg�n combobox, se rellenan los campos con la informaci�n dada.
   * 
   * @param newValue nuevo valor seleccionado para mostrar su informaci�n.
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
		// Siempre que la opci�n escogida no sea por rangos, se mostrar�n los campos con la informaci�n dada sin tabla
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
   * Hace una petici�n a la base de datos y actualiza una lista con los tripulantes que se est�n tratando actualmente, y
   * rellena un combobox para usar la opci�n de: B�scar por DNI.
   */
  private void actualizarDni() {
	actualizarValoresDeCombobox(cbDni);
  }

  /**
   * Hace una petici�n a la base de datos y actualiza una lista con los tripulantes que se est�n tratando actualmente, y
   * rellena un combobox para usar la opci�n de: B�scar por Nombre.
   */
  private void actualizarNombre() {
	actualizarValoresDeCombobox(cbNombre);
  }

  /**
   * Hace una petici�n a la base de datos y actualiza una lista con los tripulantes que se est�n tratando actualmente, y
   * rellena un combobox para usar la opci�n de: B�scar por Rango.
   */
  private void actualizarRango() {
	actualizarValoresDeCombobox(cbRango);
  }

  /**
   * Hace una consulta a la BDD y a trav�s de la lista {@link #tripulantes} que almacenar� la informaci�n, se rellena el
   * combobox corespondiente de la selecci�n de b�squeda.
   * 
   * @param cb combobox que se rellenar� con la informaci�n de la lista {@link #tripulantes}.
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
   * Cu�ndo se filtra la b�squeda por rango, se rellena una tabla con los datos de los tripulantes y su barco
   * correspondiente si est� en alguno.
   * 
   * @param lista Lista de tripulantes que pasa por par�metro para actualizar y rellenar la tabla.
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
   * Agregar un valor de tipo String a un combobox pasado por par�metro.
   * 
   * @param cb combobox que se le va a agregar el valor.
   * @param data valor que se agregar� al combobox.
   */
  private void addToCombobox(final ComboBox<String> cb, final String data) {
	if (!cb.getItems().contains(data)) {
	  cb.getItems().add(data);
	}
  }

  /**
   * M�todo que vac�a un combobox pasado como par�metro.
   * 
   * @param cb combobox a vaciar.
   */
  private void vaciarCombobox(final ComboBox<String> cb) {
	if (!cb.getItems().isEmpty() && cb.getItems() != null) {
	  cb.getItems().clear();
	}
  }

  /**
   * Actualizar cada campo de valor con informaci�n del tripulante.
   * 
   * @param DNI DNI identificativo.
   * @param nom Nombre del tripulante.
   * @param rang Que rango tiene.
   * @param img La ruta de la im�gen para ese tripulante.
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
	  enTripulacion.setText("NO EST� EN NING�N BARCO");
	}
  }

  /**
   * Muestra u oculta los campos a rellenar seg�n la orden dada que corresponda, true para que sea visible y false para
   * que se oculte.
   * 
   * @param yes sem�foro para indicar si mostrar todos los campos o no.
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
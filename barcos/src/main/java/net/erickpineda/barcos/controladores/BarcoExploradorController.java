package net.erickpineda.barcos.controladores;

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
import javafx.scene.paint.Color;
import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.BarcoRepo;

public class BarcoExploradorController {
  @FXML
  private ComboBox<String> cbNombre;
  @FXML
  private ComboBox<String> cbMatricula;
  @FXML
  private ComboBox<String> cbZarpan;
  @FXML
  private ProgressIndicator barraProgreso;
  @FXML
  private Label matricula;
  @FXML
  private Label nombre;
  @FXML
  private Label capitan;
  @FXML
  private TableView<Tripulante> tabla;
  @FXML
  private ImageView imagen;
  @FXML
  private Label luzVerde;
  private BarcoRepo barcoRepo;
  /**
   * Lista de barcos que se ha obtenido a trav�s de una consulta a la BDD.
   */
  private List<Barco> barcos;

  @FXML
  public void initialize() {
	try {
	  barcoRepo = new BarcoRepo();
	  actualizarValoresPorDefecto();
	} catch (Exception e) {}
  }

  /**
   * M�todo que re�ne los datos de otros m�todos para actualizar todos los valores en una sola invocaci�n.
   */
  private void actualizarValoresPorDefecto() {
	actualizarMatriculas();
	actualizarNombres();
	actualizarPuedenZarpar();
	actualizarCampos("Matr�cula", "Nombre", "Capitan", "SIN IMAGEN");
  }

  /**
   * Combobox para hacer b�squedas a trav�s del nombre del barco.
   */
  @FXML
  public void cbNombreClicked(MouseEvent event) {
	cbNombre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		cambiarNuevoValor(newValue, cbMatricula, cbZarpan);
	  }
	});
  }

  /**
   * Combobox para hacer b�squedas a trav�s de la matr�cula del barco.
   */
  @FXML
  public void cbMatriculaClicked(MouseEvent event) {
	cbMatricula.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		cambiarNuevoValor(newValue, cbNombre, cbZarpan);
	  }
	});
  }

  /**
   * Combobox para hacer b�squedas de los barcos que est�n listos para zarpar.
   */
  @FXML
  public void cbZarpanClick(MouseEvent event) {
	cbZarpan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	  public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		cambiarNuevoValor(newValue, cbMatricula, cbNombre);
	  }
	});
  }

  /**
   * Actualiza los combobox de b�squeda y restarua los valores de los campos por defecto.
   */
  @FXML
  public void recargarClick(MouseEvent event) {
	actualizarValoresPorDefecto();
	luzVerde.setVisible(false);
	tabla.getItems().clear();
  }

  /**
   * Recibe por par�metro un valor como String, que ser� el tipo de b�squeda efectuada a trav�s de uno de los tres
   * combobox que son: {@code cbMatricula}, {@code cbNombre} y {@code cbZarpan}. Recuperan la informaci�n de una lista
   * con los datos {@link #barcos} para finalmente mostrarlos en el panel.
   * 
   * @param newV par�metro del nuevo valor que se cambiar�.
   */
  private void cambiarNuevoValor(String newV, ComboBox<String> cb1, ComboBox<String> cb2) {
	if (newV != null && !newV.isEmpty() && barcos != null) {
	  barcos.forEach(b -> {
		if (b.getMatricula().equals(newV) || b.getNom().equals(newV) || (b.getNom().equals(newV) && b.puedeZarpar())) {
		  actualizarCampos(b.getMatricula(), b.getNom(), b.getCapitan(), b.getImagen());
		  actualizarTabla(b);
		  mensajeParaZarpar(b);
		  cb1.getSelectionModel().clearSelection();
		  cb2.getSelectionModel().clearSelection();
		}
	  });
	}
  }

  /**
   * Actualizar los valores del combobox de matr�culas.
   */
  private void actualizarMatriculas() {
	actualizarValoresDeCombobox(cbMatricula);
  }

  /**
   * Actualizar los valores del combobox de nombres.
   */
  private void actualizarNombres() {
	actualizarValoresDeCombobox(cbNombre);
  }

  /**
   * Actualizar los valores del combobox a aquellos barcos que pueden zarpar.
   */
  private void actualizarPuedenZarpar() {
	actualizarValoresDeCombobox(cbZarpan);
  }

  /**
   * M�todo que hace una petici�n a la BDD, rellena una lista de nombre {@link #barcos} y agrega los nuevos valores al
   * combobox correspondiente.
   * 
   * @param cb combobox a rellenar con informaci�n.
   */
  private void actualizarValoresDeCombobox(ComboBox<String> cb) {
	barcos = barcoRepo.findAll();
	if (barcos != null) {
	  vaciarCombobox(cb);
	  barcos.forEach(b -> {
		if (cb.getId().equals("cbMatricula")) {
		  addToCombobox(cb, b.getMatricula());
		}
		if (cb.getId().equals("cbNombre")) {
		  addToCombobox(cb, b.getNom());
		}
		if (cb.getId().equals("cbZarpan") && b.puedeZarpar()) {
		  addToCombobox(cb, b.getNom());
		}
	  });
	}
  }

  /**
   * Actualizar los valores de la tabla despu�s de efectuar la b�squeda.
   * 
   * @param barco barco que se est� tratando en el momento que se invoca la tabla.
   */
  @SuppressWarnings("unchecked")
  private void actualizarTabla(Barco barco) {
	if (tabla != null) {
	  ObservableList<Tripulante> data = FXCollections.observableArrayList(barco.getTripulantes());

	  // Se definen las columnas
	  TableColumn<Tripulante, String> c1 = (TableColumn<Tripulante, String>) tabla.getColumns().get(0);
	  TableColumn<Tripulante, String> c2 = (TableColumn<Tripulante, String>) tabla.getColumns().get(1);
	  TableColumn<Tripulante, String> c3 = (TableColumn<Tripulante, String>) tabla.getColumns().get(2);

	  for (int i = 0; i < barco.getTripulantes().size(); i++) {
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
   * Agregar un valor a un combobox que pasa por par�metro, independientemente de cual sea.
   * 
   * @param cb combobox a agregar informaci�n.
   * @param data valor que se agregar� al combobox.
   */
  private void addToCombobox(final ComboBox<String> cb, final String data) {
	if (!cb.getItems().contains(data)) {
	  cb.getItems().add(data);
	}
  }

  /**
   * M�todo que vacia un combobox que pasa como par�metro.
   * 
   * @param cb combobox a vaciar valores.
   */
  private void vaciarCombobox(final ComboBox<String> cb) {
	if (!cb.getItems().isEmpty() && cb.getItems() != null) {
	  cb.getItems().clear();
	}
  }

  /**
   * M�todo para actualizar los campos por defecto de la informaci�n del barco.
   * 
   * @param matr matr�cula del barco.
   * @param nom nombre del barco.
   * @param cap capit�n del barco.
   * @param img im�gen del barco.
   */
  private void actualizarCampos(String matr, String nom, String cap, String img) {
	matricula.setText(matr);
	nombre.setText(nom);
	capitan.setText(cap);
	if (img.endsWith("png") || img.endsWith("jpg")) {
	  imagen.setImage(new Image(img));
	}
  }

  /**
   * Muestra un mensaje en la parte superior del programa, indicando si el barco en b�squeda puede zarpar o no.
   * 
   * @param b barco que se verifica.
   */
  private void mensajeParaZarpar(Barco b) {
	if (b.puedeZarpar()) {
	  luzVerde.setText("�Leven Anclas!");
	  luzVerde.setTextFill(Color.GREEN);
	} else {
	  luzVerde.setText("No puede zarpar");
	  luzVerde.setTextFill(Color.RED);
	}
	luzVerde.setVisible(true);
  }
}

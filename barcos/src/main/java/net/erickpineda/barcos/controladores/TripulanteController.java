package net.erickpineda.barcos.controladores;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.BarcoRepo;
import net.erickpineda.barcos.repo.TripulanteRepo;

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
  private Label indicador;
  private List<Tripulante> tripulantes;
  private TripulanteRepo triRepo;
  private BarcoRepo barcoRepo;

  @FXML
  public void initialize() {
    try {
      triRepo = new TripulanteRepo();
      barcoRepo = new BarcoRepo();
      actualizarValoresPorDefecto();
    } catch (Exception e) {
    }
  }

  @FXML
  public void imagenClick(MouseEvent event) {

  }

  @FXML
  public void btnCrear(MouseEvent event) {

  }

  @FXML
  public void btnCancelar(MouseEvent event) {

  }

  @FXML
  public void btnImportar(MouseEvent event) {

  }

  @FXML
  public void btnExportar(MouseEvent event) {

  }

  @FXML
  public void cbEditarClicked(MouseEvent event) {
    cbEditar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty()) {

        }
      }
    });
  }

  private void actualizarValoresPorDefecto() {
    actualizarEditar();
    actualizarBarcos();
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
      barcos.forEach(t -> {
        addToCombobox(cbBarco, t.getMatricula());
      });
    }
  }

  /**
   * Agregar un valor a ub combobox que pasa por parámetro, independientemente de cual sea.
   * 
   * @param cb combobox a agregar información.
   * @param data valor que se agregará al combobox.
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
}

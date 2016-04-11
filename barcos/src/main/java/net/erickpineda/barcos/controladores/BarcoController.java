package net.erickpineda.barcos.controladores;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.BarcoRepo;
import net.erickpineda.barcos.repo.TripulanteRepo;

public class BarcoController {
  @FXML
  private ComboBox<String> cbEditar;
  @FXML
  private TextField matricula;
  @FXML
  private TextField nombre;
  @FXML
  private ComboBox<String> cbCapitan;
  @FXML
  private TextField tfCapitan;
  @FXML
  private TextField imagenURL;
  @FXML
  private ListView<String> listV1;
  @FXML
  private ListView<String> listV2;
  @FXML
  private AnchorPane barcoPanel;
  private BarcoRepo barcoRepo;
  private TripulanteRepo triRepo;
  private List<Barco> barcos;
  private List<Tripulante> tripulantes;
  private Barco actual;

  @FXML
  public void initialize() {
    try {
      barcoRepo = new BarcoRepo();
      triRepo = new TripulanteRepo();
      rellenarBarcosExistentes();
      rellenarCapitanesExistentes();
      actualizarListView1();
    } catch (Exception e) {
    }
  }

  @FXML
  public void cbEditarClick(MouseEvent event) {
    cbEditar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && barcos != null) {
          cambiar(newValue);
        }
      }

      private void cambiar(String newValue) {
        barcos.forEach(b -> {
          if (b.getMatricula().equals(newValue)) {
            actual = b;
            cambiarValoresDeLosCampos(b.getMatricula(), b.getNom(), b.getCapitan(), b.getImagen());
            actualizarListView2();
          }
        });
      }
    });
  }

  @FXML
  public void cbCapitanClick(MouseEvent event) {
    cbCapitan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty()) {

        }
      }
    });
  }

  @FXML
  public void add(MouseEvent event) {

  }

  @FXML
  public void remove(MouseEvent event) {

  }

  @FXML
  public void btnOpen(MouseEvent event) {

  }

  @FXML
  public void btnClick(MouseEvent event) {

  }

  @FXML
  public void btnCancelar(MouseEvent event) {
    rellenarBarcosExistentes();
    rellenarCapitanesExistentes();
    actualizarListView1();
    cambiarValoresDeLosCampos("", "", "", "");
  }

  private void rellenarBarcosExistentes() {
    barcos = barcoRepo.findAll();
    vaciarCombobox(cbEditar);
    if (!barcos.isEmpty() && barcos != null) {
      barcos.forEach(b -> {
        addToCombobox(cbEditar, b.getMatricula());
      });
    }
  }

  private void rellenarCapitanesExistentes() {
    tripulantes = triRepo.findAll();
    vaciarCombobox(cbCapitan);
    if (!tripulantes.isEmpty() && tripulantes != null) {
      tripulantes.forEach(t -> {
        if (t.getRang().equalsIgnoreCase("Capitan")) {
          addToCombobox(cbCapitan, t.getNom());
        }
      });
    }
  }

  private void actualizarListView1() {
    vaciarListView(listV1);
    tripulantes.forEach(t -> {
      addToListView(listV1, t.getNom());
    });
  }

  private void actualizarListView2() {
    if (actual != null) {
      vaciarListView(listV2);
      actual.getTripulantes().forEach(t -> {
        addToListView(listV2, t.getNom());
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

  private void addToListView(ListView<String> lv, String value) {
    if (lv != null) {
      lv.getItems().add(value);
    }
  }

  private void vaciarListView(final ListView<String> lv) {
    if (lv != null && !lv.getItems().isEmpty()) {
      lv.getItems().clear();
    }
  }

  private void cambiarValoresDeLosCampos(String matr, String nom, String capitan, String img) {
    matricula.setText(matr);
    nombre.setText(nom);
    tfCapitan.setText(capitan);
    imagenURL.setText(img);
  }

}
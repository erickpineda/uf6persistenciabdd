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
  private List<Tripulante> tripulantes;
  private TripulanteRepo triRepo;

  @FXML
  public void initialize() {
    try {
      triRepo = new TripulanteRepo();
      actualizarValoresPorDefecto();
    } catch (Exception e) {
    }
  }

  @FXML
  public void cbDniClicked(MouseEvent event) {
    cbDni.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && tripulantes != null) {
          tripulantes.forEach(t -> {
            if (t.getDni().equals(newValue)) {
              tabla.setVisible(false);
              mostrarCampos(true);
              actualizarCampos(t.getDni(), t.getNom(), t.getRang(), "", t.getBarcoId());
            }
          });
        }
      }
    });
  }

  @FXML
  public void cbNombreClicked(MouseEvent event) {
    cbNombre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && tripulantes != null) {
          tripulantes.forEach(t -> {
            if (t.getNom().equals(newValue)) {
              tabla.setVisible(false);
              mostrarCampos(true);
              actualizarCampos(t.getDni(), t.getNom(), t.getRang(), "", t.getBarcoId());
            }
          });
        }
      }
    });
  }

  @FXML
  public void cbRangoClicked(MouseEvent event) {
    cbRango.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && tripulantes != null) {
          List<Tripulante> rangos = triRepo.findListByRang(newValue);
          if (rangos != null) {
            mostrarCampos(false);
            tabla.setVisible(true);
            actualizarTabla(rangos);
          }
        }
      }
    });
  }

  private void actualizarValoresPorDefecto() {
    actualizarDni();
    actualizarNombre();
    actualizarRango();
  }

  private void actualizarDni() {
    tripulantes = triRepo.findAll();
    if (tripulantes != null) {
      vaciarCombobox(cbDni);
      tripulantes.forEach(t -> {
        addToCombobox(cbDni, t.getDni());
      });
    }
  }

  private void actualizarNombre() {
    tripulantes = triRepo.findAll();
    if (tripulantes != null) {
      vaciarCombobox(cbNombre);
      tripulantes.forEach(t -> {
        addToCombobox(cbNombre, t.getNom());
      });
    }
  }

  private void actualizarRango() {
    tripulantes = triRepo.findAll();
    if (tripulantes != null) {
      vaciarCombobox(cbRango);
      tripulantes.forEach(t -> {
        addToCombobox(cbRango, t.getRang());
      });
    }
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

  private void actualizarCampos(String DNI, String nom, String rang, String img, String nomBarco) {
    dni.setText(DNI);
    nombre.setText(nom);
    rango.setText(rang);
    if (img.endsWith("png") || img.endsWith("jpg ")) {
      imagen.setImage(new Image(img));
    }
    if (nomBarco != null && !nomBarco.isEmpty()) {
      enTripulacion.setText(nomBarco);
    } else {
      enTripulacion.setText("NO ESTÁ EN NINGÚN BARCO");
    }
  }

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
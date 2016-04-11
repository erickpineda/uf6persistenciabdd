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
  private List<Barco> barcos;

  @FXML
  public void initialize() {
    try {
      barcoRepo = new BarcoRepo();
      actualizarValoresPorDefecto();
    } catch (Exception e) {
    }
  }

  @FXML
  public void cbNombreClicked(MouseEvent event) {
    cbNombre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && barcos != null) {
          barcos.forEach(b -> {
            if (b.getNom().equals(newValue)) {
              actualizarCampos(b.getMatricula(), b.getNom(), b.getCapitan(), b.getImagen());
              actualizarTabla(b);
              luzVerde.setVisible(false);
            }
          });
        }
      }
    });
  }

  @FXML
  public void cbMatriculaClicked(MouseEvent event) {
    cbMatricula.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldV, String newV) {
        if (newV != null && !newV.isEmpty() && barcos != null) {
          cambiar(newV);
        }
      }

      private void cambiar(String newV) {
        barcos.forEach(b -> {
          if (b.getMatricula().equals(newV)) {
            actualizarCampos(b.getMatricula(), b.getNom(), b.getCapitan(), b.getImagen());
            actualizarTabla(b);
            luzVerde.setVisible(false);
          }
        });
      }
    });
  }

  @FXML
  public void cbZarpanClick(MouseEvent event) {
    cbZarpan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && barcos != null) {
          barcos.forEach(b -> {
            if (b.getNom().equals(newValue) && b.puedeZarpar()) {
              actualizarCampos(b.getMatricula(), b.getNom(), b.getCapitan(), b.getImagen());
              actualizarTabla(b);
              luzVerde.setVisible(true);
            }
          });
        }
      }
    });
  }

  private void actualizarValoresPorDefecto() {
    actualizarMatriculas();
    actualizarNombres();
    actualizarPuedenZarpar();
  }

  private void actualizarMatriculas() {
    barcos = barcoRepo.findAll();
    if (barcos != null) {
      vaciarCombobox(cbMatricula);
      barcos.forEach(b -> {
        addToCombobox(cbMatricula, b.getMatricula());
      });
    }
  }

  private void actualizarNombres() {
    barcos = barcoRepo.findAll();
    if (barcos != null) {
      vaciarCombobox(cbNombre);
      barcos.forEach(b -> {
        addToCombobox(cbNombre, b.getNom());
      });
    }
  }

  private void actualizarPuedenZarpar() {
    barcos = barcoRepo.findAll();
    if (barcos != null) {
      vaciarCombobox(cbZarpan);
      barcos.forEach(b -> {
        if (b.puedeZarpar()) {
          addToCombobox(cbZarpan, b.getNom());
        }
      });
    }
  }

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

  private void actualizarCampos(String matr, String nom, String cap, String img) {
    matricula.setText(matr);
    nombre.setText(nom);
    capitan.setText(cap);
    if (img.endsWith("png") || img.endsWith("jpg ")) {
      imagen.setImage(new Image(img));
    }
  }
}

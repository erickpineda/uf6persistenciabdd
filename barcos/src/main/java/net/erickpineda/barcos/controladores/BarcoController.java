package net.erickpineda.barcos.controladores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.erickpineda.barcos.App;
import net.erickpineda.barcos.modelos.Barco;
import net.erickpineda.barcos.modelos.Tripulante;
import net.erickpineda.barcos.repo.BarcoRepo;
import net.erickpineda.barcos.repo.TripulanteRepo;
import net.erickpineda.barcos.util.Asistente;
import net.erickpineda.barcos.util.Msj;

public class BarcoController {
  @FXML
  private ComboBox<String> cbEditar;
  @FXML
  private Label editandoExistente;
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
  private Button btnCrear;
  @FXML
  private Button btnEditar;
  @FXML
  private TreeView<String> sinBarco;
  @FXML
  private TreeView<String> enBarco;
  @FXML
  private AnchorPane barcoPanel;
  private BarcoRepo barcoRepo;
  private TripulanteRepo triRepo;
  private List<Barco> barcos;
  private List<Tripulante> tripulantes;
  private Barco editActual;

  @FXML
  public void initialize() {
    try {
      barcoRepo = new BarcoRepo();
      triRepo = new TripulanteRepo();
      // editActual = new Barco();
      actualizarDatosPorDefecto();
    } catch (Exception e) {
    }
  }

  @FXML
  public void cbEditarClick(MouseEvent event) {
    cbEditar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty() && barcos != null) {
          cambiar(newValue);
          editandoExistente.setVisible(true);
          cbCapitan.setDisable(true);
          btnCrear.setDisable(true);
          btnEditar.setDisable(false);
          matricula.setEditable(false);
        }
      }

      private void cambiar(String newValue) {
        barcos.forEach(b -> {
          if (b.getMatricula().equals(newValue)) {
            editActual = b;
            actualizarCampos(b.getMatricula(), b.getNom(), b.getCapitan(), b.getImagen());
            actualizarListaEnBarco();
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
          tfCapitan.setText(newValue);
        }
      }
    });
  }

  private int capitan;

  @FXML
  public void add(MouseEvent event) {
    TreeItem<String> listaSinBarco = sinBarco.getRoot();
    TreeItem<String> listaEnBarco = enBarco.getRoot();

    if (listaSinBarco != null && listaEnBarco != null) {
      TreeItem<String> seleccionado = sinBarco.getSelectionModel().getSelectedItem();
      if (!listaSinBarco.getChildren().isEmpty() && seleccionado != null) {
        if (!seleccionado.getChildren().isEmpty() && tripulantes != null) {
          agregar(seleccionado, listaEnBarco);
        }
      }
    }
  }

  private void agregar(final TreeItem<String> seleccionado, final TreeItem<String> listaEnBarco) {
    for (Tripulante t : tripulantes) {
      if (seleccionado.getValue().equals(t.getDni())) {
        for (TreeItem<String> rangoEnBarco : listaEnBarco.getChildren()) {
          if (rangoEnBarco.getValue().equals(t.getRang()) && !rangoEnBarco.getChildren().contains(seleccionado)) {
            if (rangoEnBarco.getValue().equalsIgnoreCase("Capitan") && rangoEnBarco.getChildren().isEmpty()) {
              capitan = 0;
            }
            if (capitan == 0) {
              rangoEnBarco.getChildren().add(seleccionado);
              if (t.getRang().equalsIgnoreCase("Capitan")) {
                tfCapitan.setText(t.getNom());
              }
              capitan++;
            } else {
              if (capitan == 1 && !rangoEnBarco.getValue().equalsIgnoreCase("Capitan")) {
                System.out.println(t);
                rangoEnBarco.getChildren().add(seleccionado);
              }
            }
          }
        }
      }
    }
  }

  @FXML
  public void remove(MouseEvent event) {
    TreeItem<String> listaSinBarco = sinBarco.getRoot();
    TreeItem<String> listaEnBarco = enBarco.getRoot();

    if (listaEnBarco != null && listaSinBarco != null) {
      TreeItem<String> seleccionado = enBarco.getSelectionModel().getSelectedItem();
      if (!listaEnBarco.getChildren().isEmpty() && seleccionado != null) {
        if (!seleccionado.getChildren().isEmpty() && tripulantes != null) {
          remover(seleccionado, listaSinBarco);
        }
      }
    }
  }

  private void remover(final TreeItem<String> seleccionado, final TreeItem<String> listaSinBarco) {
	TreeItem<String> padreDelSeleccionado = seleccionado.getParent();
	tripulantes.forEach(t -> {
	  if (seleccionado.getValue().equals(t.getDni())) {
		listaSinBarco.getChildren().forEach(rangoSinBarco -> {
		  if (rangoSinBarco.getValue().equals(t.getRang()) & rangoSinBarco.getChildren().contains(seleccionado)) {
			if (padreDelSeleccionado != null && !padreDelSeleccionado.getChildren().isEmpty()) {
			  padreDelSeleccionado.getChildren().remove(seleccionado);
			  rangoSinBarco.getChildren().remove(seleccionado);
			  rangoSinBarco.getChildren().add(seleccionado);
			}
		  }
		});
	  }
	});
  }

  private List<Tripulante> deTreeItemAList(TreeItem<String> tree) {
    List<Tripulante> flota = new ArrayList<Tripulante>();
    for (TreeItem<String> rang : tree.getChildren()) {
      for (TreeItem<String> dni : rang.getChildren()) {
        for (TreeItem<String> nom : dni.getChildren()) {
          flota.add(new Tripulante(dni.getValue(), nom.getValue(), rang.getValue()));
        }
      }
    }
    return flota;
  }

  @FXML
  public void btnVaciar(MouseEvent event) {
    enBarco.setRoot(null);
    tfCapitan.setText("");
    actualizarListaEnBarco();
  }

  @FXML
  public void btnOpen(MouseEvent event) {
    File img = getFileChooser("Importar Imágen", "*.png", "*.jpg").showOpenDialog(App.PRIMARY_STAGE);
    if (img != null) {
      imagenURL.setText(img.toURI().toString());
    }
  }

  @FXML
  public void btnCrear(MouseEvent event) {
    // Es una variable con el Barco que se se está editando o creando actualmente
    if (editActual == null) {
      editActual = new Barco();
    }

    /** VALIDANDO TODOS LOS CAMPOS PARA CREAR EL BARCO PASO 1 DE 3 **/

    // Los campos de matrícula y nombre son obligatorios a la hora de crear el barco
    if (!editandoExistente.isVisible() && matriculaYNombreOk()) {

      // VALIDAR QUE LA MATRÍCULA INSERTADA TENGA EL PRTRÓN CORRECTO
      if (Asistente.validarMatricula(matricula.getText())) {
        validandoBarcoPaso1De3();

        /** VALIDANDO LA TRIPULACIÓN QUE SE AGREGARÁ O NO AL BARCO PASO 2 DE 3 **/

        if (enBarco.getRoot() != null) {
          validandoBarcoPaso2De3();
        } else {
          Msj.err("Error al crear barco", "No se ha podido crear el barco: " + editActual.getMatricula());
        }
      } else {
        Msj.err("MatrÃ­cula inválida", "La matrícula " + matricula.getText() + " no es válida.\nPatrón: 3-AT-6-051-04");
      }
    } else {
      Msj.warn("Faltan campos", "Faltan campos por rellenar, recuerda que la matrícula y el nombre son obligatorios");
    }
  }

  private void validandoBarcoPaso1De3() {
    editActual.setMatricula(matricula.getText());
    editActual.setNom(nombre.getText());

    if (imagenURL.getText().isEmpty()) {
      editActual.setImagen("SIN IMAGEN");
    } else {
      editActual.setImagen(imagenURL.getText());
    }
  }

  @FXML
  public void btnEditar(MouseEvent event) {
    if (editActual != null) {
      // Los campos de matrícula y nombre son obligatorios a la hora de editar el barco
      if (editandoExistente.isVisible() && matriculaYNombreOk()) {
        validandoBarcoPaso1De3();

        /** VALIDANDO LA TRIPULACIÓN QUE SE AGREGARÁ O NO AL BARCO **/

        if (enBarco.getRoot() != null) {
          validandoBarcoPaso2De3();
        } else {
          Msj.err("Error al editar el barco", "No se ha podido editar el barco: " + editActual.getMatricula());
        }

      } else {
        Msj.warn("Campos vacíos", "El campo de nombre no puede quedar vacío");
      }
    }
  }

  @FXML
  public void btnReset(MouseEvent event) {
    reset();
  }

  private void validandoBarcoPaso2De3() {
    if (!enBarco.getRoot().getChildren().isEmpty()) {
      String msj = "";
      if (tfCapitan.getText().isEmpty()) {
        msj = "El barco " + nombre.getText() + " se creará sin capitán";
        Msj.warn("Barco sin capitán", msj);
      }

      if (editandoExistente.isVisible()) {
        barcoRepo.actualizarBarco(editActual);
        msj = String.format("Barco: %s editado correctamente", editActual.getMatricula());
      } else {
        barcoRepo.crearBarco(editActual);
        msj = String.format("Barco: %s creado correctamente", editActual.getMatricula());
      }

      /** ACTUALIZANDO LOS TRIPULANTES CON SU RESPECTIVO BARCO PASO 3 DE 3 **/

      validandoBarcoPaso3De3();

      Msj.inf("Todo correcto", msj);
      reset();
    }
  }

  private void validandoBarcoPaso3De3() {
    List<Tripulante> flota = deTreeItemAList(enBarco.getRoot());
    if (flota != null && !flota.isEmpty()) {
      // Actualizo el tripulante al nuevo barco que pertenece
      flota.forEach(t -> {
        t.setBarcoId(editActual.getMatricula());
        triRepo.actualizarTripulante(t);
      });
      editActual.setTripulantes(flota);
      barcoRepo.actualizarBarco(editActual);
    }
    if (flota.isEmpty()) {
      Msj.inf("Barco sin flota", "El barco se creará sin flota alguna");
    }
  }

  private boolean matriculaYNombreOk() {
    return (matricula != null && nombre != null && !matricula.getText().isEmpty() && !nombre.getText().isEmpty());
  }

  private void reset() {
    editandoExistente.setVisible(false);
    cbCapitan.setDisable(false);
    btnCrear.setDisable(false);
    btnEditar.setDisable(true);
    matricula.setEditable(true);
    sinBarco.setRoot(null);
    enBarco.setRoot(null);
    editActual = new Barco();
    actualizarDatosPorDefecto();
    actualizarCampos("", "", "", "");
  }

  /**
   * Crea un FiliChoser.
   * 
   * @param titulo titulo que llevará el FileChooser.
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

  private void actualizarDatosPorDefecto() {
    actualizarBarcos();
    actualizarCapitanes();

    actualizarListaSinBarco();
    actualizarListaEnBarco();
  }

  private void actualizarBarcos() {
    barcos = barcoRepo.findAll();
    vaciarCombobox(cbEditar);
    if (!barcos.isEmpty() && barcos != null) {
      barcos.forEach(b -> {
        addToCombobox(cbEditar, b.getMatricula());
      });
    }
  }

  private void actualizarCapitanes() {
    tripulantes = triRepo.findAll();
    vaciarCombobox(cbCapitan);
    if (!tripulantes.isEmpty() && tripulantes != null) {
      tripulantes.forEach(t -> {
        if (t.getRang().equalsIgnoreCase("Capitan") && t.getBarcoId() == null) {
          addToCombobox(cbCapitan, t.getNom());
        }
      });
    }
  }

  private void actualizarListaSinBarco() {
    this.sinBarco.setRoot(null);
    TreeItem<String> rootNode = new TreeItem<String>("Tripulantes sin barco");
    rootNode.setExpanded(true);

    List<String> listaRangos = triRepo.getListRang();
    for (String rango : listaRangos) {
      // if (!rango.equalsIgnoreCase("Capitan")) {
      TreeItem<String> rangs = new TreeItem<String>(rango);
      rootNode.getChildren().add(rangs);
      List<Tripulante> sinBarco = new ArrayList<Tripulante>();
      tripulantes.forEach(t -> {
        if (t.getBarcoId() == null) {
          sinBarco.add(t);
        }
      });
      if (sinBarco != null && !sinBarco.isEmpty()) {
        agregarDatosTreeView(rangs, sinBarco);
      }
      // }
    }
    this.sinBarco.setRoot(rootNode);
    this.sinBarco.setEditable(true);
  }

  private void actualizarListaEnBarco() {
    TreeItem<String> rootNode = new TreeItem<String>("Tripulantes en barco");
    rootNode.setExpanded(true);

    List<String> listaRangos = triRepo.getListRang();
    for (String rango : listaRangos) {
      TreeItem<String> rangs = new TreeItem<String>(rango);
      rootNode.getChildren().add(rangs);
      if (editActual != null && editActual.getTripulantes() != null && !editActual.getTripulantes().isEmpty()) {
        agregarDatosTreeView(rangs, editActual.getTripulantes());
      }
    }
    this.enBarco.setRoot(rootNode);
    this.enBarco.setEditable(true);
  }

  private void agregarDatosTreeView(TreeItem<String> rangs, List<Tripulante> lista) {
	lista.forEach(t -> {
	  if (t.getRang().equals(rangs.getValue())) {
		if (rangs.getChildren().contains(t.getNom())) {
		  rangs.getChildren().remove(t.getNom());
		} else {
		  TreeItem<String> nombres = new TreeItem<String>(t.getNom());
		  TreeItem<String> dniS = new TreeItem<String>(t.getDni());
		  rangs.getChildren().add(dniS);
		  dniS.getChildren().add(nombres);
		}
		// }
	  }
	});
  }

  /**
   * Agregar un valor a un combobox que pasa por parámetro, independientemente de cual sea.
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
   * MÃ©todo que vacia un combobox que pasa como parámetro.
   * 
   * @param cb combobox a vaciar valores.
   */
  private void vaciarCombobox(final ComboBox<String> cb) {
	if (!cb.getItems().isEmpty() && cb.getItems() != null) {
	  cb.getItems().clear();
	}
  }

  private void actualizarCampos(String matr, String nom, String capitan, String img) {
	matricula.setText(matr);
	nombre.setText(nom);
	tfCapitan.setText(capitan);
	imagenURL.setText(img);
  }
}
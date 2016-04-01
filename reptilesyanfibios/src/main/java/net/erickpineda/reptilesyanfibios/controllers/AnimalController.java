package net.erickpineda.reptilesyanfibios.controllers;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import net.erickpineda.reptilesyanfibios.models.Animal;
import net.erickpineda.reptilesyanfibios.models.Familia;
import net.erickpineda.reptilesyanfibios.models.Ordre;
import net.erickpineda.reptilesyanfibios.util.ConexionBDD;
import net.erickpineda.reptilesyanfibios.util.Find;
import net.erickpineda.reptilesyanfibios.util.Msj;

public class AnimalController {
  @FXML
  private BorderPane borderpane;
  @FXML
  private ComboBox<String> cbFamilia;
  @FXML
  private ComboBox<String> cbOrden;
  @FXML
  private ComboBox<String> cbEstado;
  @FXML
  private ComboBox<String> cbTema;
  @FXML
  private Button btnAnterior;
  @FXML
  private Button btnSiguiente;
  @FXML
  private TextArea textarea;
  @FXML
  private Button btnGuardar;
  @FXML
  private ImageView imagen;
  @FXML
  private TextField nombreAnimal;
  @FXML
  private TextField especieAnimal;
  /**
   * Conector a la base de datos.
   */
  private ConexionBDD con;
  /**
   * Buscador que hace consultas a la base de datos a través del conector ConexionBDD.
   */
  private Find find;
  /**
   * Lista de familias que se obtiene de la base de datos.
   */
  private List<Familia> families;
  /**
   * Lista de ordres que se obtiene de la base de datos.
   */
  private List<Ordre> ordres;
  /**
   * Lista de animals que se obtiene de la base de datos.
   */
  private List<Animal> animals;
  /**
   * Temas de css del diseño de software.
   */
  private String[] temas = new String[] { "/css/LightTheme.css", "/css/DarkTheme.css" };
  /**
   * Animal actual que se esta procesando.
   */
  private Animal actual;

  @FXML
  public void initialize() {
    agregarTemas();
    con = new ConexionBDD();
    if (con.conectarBD() != null) {
      find = new Find(con);
      rellenarValoresPorDefecto();
    } else {
      desactivarTipos(true);
      Msj.err("Error", "Sin conexión", "No se ha podido conectar a la base de datos");
    }
  }

  @FXML
  public void cbFamiliaClick(MouseEvent event) {
    setFamilies();
    cbFamilia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty()) {
          setOrdres(newValue, true);
          cbOrden.setDisable(false);
        }
      }
    });
  }

  @FXML
  public void cbOrdenClick(MouseEvent event) {
    cbOrden.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty()) {
          ordres.forEach(o -> {
            if (newValue.equals(o.getNom())) {
              setAnimals(newValue);
              cbEstado.setDisable(false);
            }
          });
        }
      }
    });
  }

  @FXML
  public void cbEstadoClick(MouseEvent event) {
    cbEstado.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        animals.forEach(a -> {
          if (newValue != null && !newValue.isEmpty()) {
            if (newValue.equals(a.getEstat())) {
              actual = a;
              setTipos(a.getNom(), a.getEspecie(), a.getDescripcio(), a.getImatge());
              desactivarTiposYComboboxExceptoFamilia(false);
            }
          }
        });
      }
    });
  }

  @FXML
  public void cbTemaClicked(MouseEvent event) {
    cbTema.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
        if (newValue != null && !newValue.isEmpty()) {
          if (newValue.equals("Dark Theme")) {
            borderpane.getStylesheets().setAll(getClass().getResource(temas[1]).toExternalForm());
          }
          if (newValue.equals("Light Theme")) {
            borderpane.getStylesheets().setAll(getClass().getResource(temas[0]).toExternalForm());
          }
        }
      }
    });
  }

  @FXML
  public void anteriorClicked(MouseEvent event) {
    if (actual != null) {
      cont--;
      if (cont <= -1) {
        cont = 0;
      }
      if (cont >= animals.size()) {
        cont = animals.size() - 1;
      }
      //System.out.println("anterior=" + cont);
      if ((cont < animals.size() - 1) && cont >= 0) {
        Animal a = animals.get(cont);
        if (a != null) {
          setTipos(a.getNom(), a.getEspecie(), a.getDescripcio(), a.getImatge());
        }
      }
    }
  }

  private int cont = -1;

  @FXML
  public void siguienteClicked(MouseEvent event) {
    if (actual != null) {
      cont++;
      if (cont <= -1) {
        cont = 0;
      }
      if (cont >= animals.size()) {
        cont = animals.size() - 1;
      }
      //System.out.println("siguiente=" + cont);
      if ((cont < animals.size() - 1) && cont >= 0) {
        Animal a = animals.get(cont);
        if (a != null) {
          setTipos(a.getNom(), a.getEspecie(), a.getDescripcio(), a.getImatge());
        }
      }
    }
  }

  @FXML
  public void guardarClicked(MouseEvent event) {
    if (!animals.isEmpty() && animals != null) {
      if (actual != null) {
        if (!nombreAnimal.getText().isEmpty() && !especieAnimal.getText().isEmpty()
            && !textarea.getText().isEmpty()) {

          if (!nombreAnimal.getText().equals(actual.getNom())
              || !especieAnimal.getText().equals(actual.getEspecie())
              || !textarea.getText().equals(actual.getDescripcio())) {

            actualiza(nombreAnimal.getText(), especieAnimal.getText(), textarea.getText());
          } else {
            Msj.warn("Warning", "Sin cambios", "No se han hecho cambios");
          }
        } else {
          Msj.warn("Warning", "Nulos", "Nombre, especie y descripción no pueden estar vacios");
        }
      }
    }
  }

  /**
   * Método de actualizar los datos a la base de datos.
   * 
   * @param nom nombre a cambiar.
   * @param especie especie a cambiar.
   * @param descripcio descripcion a cambiar.
   */
  private void actualiza(final String nom, final String especie, final String descripcio) {
    actual.setNom(nom);
    actual.setEspecie(especie);
    actual.setDescripcio(descripcio);
    find.update(actual);
    Msj.inf("Actualizado", "Animal Actualizado", "Datos actualizados correctamente");
  }

  /**
   * Crear valores por defecto, cuando se inicia el programa.
   */
  private void rellenarValoresPorDefecto() {
    agregarAccionesDeMouseManualmente(nombreAnimal);
    agregarAccionesDeMouseManualmente(especieAnimal);
    agregarAccionesDeMouseManualmente(textarea);
    rellenarDatos();
  }

  /**
   * Rellenar los datos de los combobox.
   */
  private void rellenarDatos() {
    setFamilies();
    setOrdres(cbFamilia.getSelectionModel().getSelectedItem(), false);
    setAnimals(cbEstado.getSelectionModel().getSelectedItem());
    desactivarTiposYComboboxExceptoFamilia(true);
  }

  /**
   * Cambiar los datos del combobox de <b><i>cbFamilia</i></b>.
   */
  private void setFamilies() {
    vaciarCombobox(cbFamilia);
    families = find.allFamilias();
    if (families != null) {
      families.forEach(f -> addToCombobox(cbFamilia, f.getNom()));
    }
  }

  /**
   * Cambiar los datos del combobox de <b><i>cbOrden</i></b>.
   * 
   * @param familia nombre de la familia a buscar.
   * @param setAnimals si es true, se llenarán los datos de animales consecutivamente al agregar
   *          datos en <i>cbOrden</i>. Si es false sólo se cambiarán los datos de <i>cbOdren</i>.
   */
  private void setOrdres(final String familia, final boolean setAnimals) {
    vaciarCombobox(cbOrden);
    ordres = find.allOrdresByNomFamilia(familia);
    if (ordres != null) {
      ordres.forEach(o -> {
        addToCombobox(cbOrden, o.getNom());
        if (setAnimals) {
          setAnimals(o.getNom());
        }
      });
    }
  }

  /**
   * Cambiar los datos del combobox <b><i>cbEstado</i></b>.
   * 
   * @param oNom nombre del orden a buscar.
   */
  private void setAnimals(final String oNom) {
    vaciarCombobox(cbEstado);
    animals = find.allAnimalsByNomOrdre(oNom);
    if (animals != null) {
      animals.forEach(a -> {
        addToCombobox(cbEstado, a.getEstat());
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

  /**
   * Método que permite cambiar los campos del animal actual en la aplicación.
   * 
   * @param nombre nombre del animal.
   * @param especie especie del animal.
   * @param descripcion descripción del animal.
   * @param url ruta de la imágen a cambiar.
   */
  private void setTipos(String nombre, String especie, String descripcion, String url) {
    nombreAnimal.setText(nombre);
    especieAnimal.setText(especie);
    textarea.setText(descripcion);
    imagen.setImage(new Image(url));
  }

  /**
   * Método que agrega acciones de mouse, de forma manual a un Control que pasa como parámetro,
   * puede ser un Button, TextField, Label etc.
   * 
   * @param control control para agregar el evento de mouse.
   */
  private void agregarAccionesDeMouseManualmente(Control control) {
    control.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
          controlesComoEditables(true);
        }
      }
    });
  }

  /**
   * Método que agrega temas establecidos CSS, para el cambio de aspecto del software.
   */
  private void agregarTemas() {
    cbTema.getItems().add("Dark Theme");
    cbTema.getItems().add("Light Theme");
  }

  /**
   * Cambiar los campos de <b>nombreAnimal</b>, <b>especieAnimal</b>, <b>textarea</b> a EDITABLE si
   * el parámetro es true; Si es false, dichos campos serán NO EDITABLES.
   * 
   * @param yes parámetro que servirá de semáforo para cambiar los campos a editables.
   */
  private void controlesComoEditables(final boolean yes) {
    nombreAnimal.setEditable(yes);
    especieAnimal.setEditable(yes);
    textarea.setEditable(yes);
  }

  /**
   * Cambiar los campos de <b>nombreAnimal</b>, <b>especieAnimal</b>, <b>textarea</b> a DISABLE
   * (deshabilitado) si el parámetro es true; Si es false, dichos campos serán NO DISABLE
   * (habilitados).
   * 
   * @param yes parámetro que servirá de semáforo para cambiar los campos a disable.
   */
  private void desactivarTipos(final boolean yes) {
    nombreAnimal.setDisable(yes);
    especieAnimal.setDisable(yes);
    textarea.setDisable(yes);
  }

  /**
   * Método para necesidades de desactivación.
   * 
   * @param yes parámetro que servirá de semáforo para cambiar los campos a disable.
   */
  private void desactivarTiposYComboboxExceptoFamilia(boolean yes) {
    desactivarTipos(yes);
    cbOrden.setDisable(yes);
    cbEstado.setDisable(yes);
  }
}
package net.erickpineda.traductorcolores.controllers;

import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.erickpineda.traductorcolores.App;
import net.erickpineda.traductorcolores.util.ConexionBDD;
import net.erickpineda.traductorcolores.util.Msj;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TraductorController {
  @FXML
  private TextField buscador;
  @FXML
  private ComboBox<String> combobox;
  @FXML
  private Label idioma1;
  @FXML
  private Label traduccion1;
  @FXML
  private Label traduccion3;
  @FXML
  private Label idioma3;
  @FXML
  private Label idioma2;
  @FXML
  private Label traduccion2;
  private ConexionBDD conecta;
  private static final String SQL = "SELECT DISTINCT nom, castella, angles, frances FROM colors";
  private Stage stage;

  @FXML
  public void initialize() {
    this.stage = App.PRIMARY_STAGE;
    conecta = new ConexionBDD().conectarBD();
    if (conecta != null) {
      rellenarDatosIniciales();
      agregarAccionesABuscador();
      alSalirCerrarConexion();
    } else {
      bloquearTodosLosCampos(true);
      Msj.err("Error", "Base de datos no conectada", "No se ha podido establecer la conexión");
    }
  }

  @FXML
  public void buscar(MouseEvent event) {
    Button b = (Button) event.getSource();
    if (!buscador.isDisabled()) {
      busca();
    } else {
      b.setDisable(true);
    }
  }

  /**
   * A través de una conexión a un servidor MySQL, rellena los campos del combobox, los label's de
   * idioma y traducción, con datos por defecto.
   */
  private void rellenarDatosIniciales() {
    if (combobox != null) {
      String[] columnas = obtenerColumnas(SQL + ";");
      Arrays.stream(columnas).forEach(c -> { combobox.getItems().add(c); });
      combobox.getSelectionModel().select(0);
      rellenarCampos(columnas, consultar(SQL + " c where c.nom = 'vermell';"));
      cambiarTitulo(combobox.getSelectionModel().getSelectedItem());
    }
  }

  /**
   * Agrega un evento de teclado para buscar los datos a través de la tecla ENTER.
   */
  private void agregarAccionesABuscador() {
    buscador.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
          busca();
        }
      }
    });
  }

  /**
   * Método que recoge el texto insetado en el buscador y a través de la opción de idioma escogida,
   * hacer una consulta SQL para después rellenar los campos de los Label's.
   */
  private void busca() {
    if (!buscador.getText().isEmpty()) {
      String item = combobox.getSelectionModel().getSelectedItem();
      String consulta = SQL + " c where c." + item + " = '" + buscador.getText() + "';";
      try {
        rellenarCampos(obtenerColumnas(SQL + ";"), consultar(consulta));
        cambiarTitulo(item);
      } catch (Exception e) {
        Msj.warn("No encontrado",
            "Comprueba que la slección de idioma sea correcta con el color buscado",
            "Idioma seleccionado: " + item + "\n" + "Color buscado: " + buscador.getText());
      }
    } else {
      Msj.warn("Warning", "Escribe el nombre del color", "Escribe el nombre del color que buscas");
    }
  }

  /**
   * Rellena los campos de Label's a través de un array con los nombres de la columna y una lista
   * que tiene los datos como resultado de una consulta SQL.
   * 
   * @param cols array con los nombres de las columnas de una tabla resultante.
   * @param list lista con los datos obtenidos a través de una consulta SQL.
   */
  private void rellenarCampos(final String[] cols, final List<String> list) {
    String seleccionado = combobox.getSelectionModel().getSelectedItem();

    /*
     * Ya que las columnas son fijas nom, castella, angles y frances; Las capturo de manera fija
     * siendo: cols[0] = nom, cols[1] = castella, cols[2] = angles, cols[3] = frances. De esta forma
     * no se necesitan comprobaciones extras al rellenar los idiomas y traducciones.
     */

    if (cols.length == 4) {
      if (seleccionado.equals(cols[0])) {
        rellenarIdiomas(cols[1], cols[2], cols[3]);
        rellenarTraduccion(list.get(1), list.get(2), list.get(3));
      }
      if (seleccionado.equals(cols[1])) {
        rellenarIdiomas(cols[0], cols[2], cols[3]);
        rellenarTraduccion(list.get(0), list.get(2), list.get(3));
      }
      if (seleccionado.equals(cols[2])) {
        rellenarIdiomas(cols[1], cols[0], cols[3]);
        rellenarTraduccion(list.get(1), list.get(0), list.get(3));
      }
      if (seleccionado.equals(cols[3])) {
        rellenarIdiomas(cols[1], cols[2], cols[0]);
        rellenarTraduccion(list.get(1), list.get(2), list.get(0));
      }
    }
  }

  /**
   * Método para cambiar los valores de los Label's que definen el nombre del idioma.
   * 
   * @param t1 primer idioma.
   * @param t2 segundo idioma.
   * @param t3 tercer idioma.
   */
  private void rellenarIdiomas(final String t1, final String t2, final String t3) {
    idioma1.setText(t1);
    idioma2.setText(t2);
    idioma3.setText(t3);
  }

  /**
   * Método para cambiar los valores de los Label's con la traducción del color según su idioma.
   * 
   * @param t1 primer idioma.
   * @param t2 segundo idioma.
   * @param t3 tercer idioma.
   */
  private void rellenarTraduccion(final String t1, final String t2, final String t3) {
    traduccion1.setText(t1);
    traduccion2.setText(t2);
    traduccion3.setText(t3);
  }

  /**
   * Cambiar el título de la ventana principal.
   * 
   * @param item texto del último idioma funcional que fue escogido.
   */
  private void cambiarTitulo(final String item) {
    stage.setTitle("Color: " + buscador.getText() + " - Idioma: " + item);
  }

  /**
   * 
   * @param miConsulta consulta SQL a efectuar.
   * @return retorna una lista de String's con los valores de la consulta efectuada.
   */
  private List<String> consultar(final String miConsulta) {
    return conecta.consultar(miConsulta);
  }

  /**
   * 
   * @param consulta consulta SQL.
   * @return retorna un array de String's con los nombres de las columnas según la consulta SQL.
   */
  private String[] obtenerColumnas(final String consulta) {
    return conecta.obtenerColumnasTabla(consulta);
  }

  /**
   * 
   * @param yes si es true bloqueará los campos de busqueda y selección de idioma.
   */
  public void bloquearTodosLosCampos(final boolean yes) {
    combobox.setDisable(yes);
    buscador.setDisable(yes);
  }

  /**
   * Método para detectar cuando se hace clic en el botón de cerrar ventana (X) y cerrar la conexión
   * con el servidor MySQL.
   */
  private void alSalirCerrarConexion() {
    stage.setOnHiding(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent event) {
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            conecta.desconectarDB();
            System.exit(0);
          }
        });
      }
    });
  }
}

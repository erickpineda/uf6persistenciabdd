package net.erickpineda.nombressantos.controladores;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoTimeoutException;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import net.erickpineda.nombressantos.util.Msj;

public class BuscadorController {
  @FXML
  private RadioButton castellano;
  @FXML
  private RadioButton catalan;
  @FXML
  private RadioButton porFecha;
  @FXML
  private TextField buscador;
  @FXML
  private TextArea diasSantos;
  @FXML
  private TextArea observaciones;
  private final ToggleGroup group = new ToggleGroup();
  private MongoCollection<Document> coll;

  @SuppressWarnings("deprecation")
  @FXML
  public void initialize() {

    @SuppressWarnings("resource")
    MongoClient client = new MongoClient(
                          asList(
                              new ServerAddress("localhost"),
                              new ServerAddress("localhost:27017")),
                          MongoClientOptions
                            .builder()
                              .serverSelectionTimeout(1000).build());

    try {
      client.getDB("classe").command("ping");

      MongoDatabase db = client.getDatabase("classe");
      coll = db.getCollection("noms2");

    } catch (MongoTimeoutException e) {
      Msj.err("No hay conexión", "No hay conexión con la base de datos");
      coll = null;
    }

    castellano.setToggleGroup(group);
    catalan.setToggleGroup(group);
    porFecha.setToggleGroup(group);

  }

  @FXML
  public void buscarClicked(MouseEvent event) {
    if (coll != null && buscador != null && !buscador.getText().isEmpty()) {
      if (castellano.isSelected()) {
        buscar(coll.find(new Document("castella", buscador.getText())));
      }
      if (catalan.isSelected()) {
        buscar(coll.find(new Document("catala", buscador.getText())));
      }
      if (porFecha.isSelected()) {
        buscar(coll.find(new Document("sants", asList(buscador.getText()))));
      }
    }
  }

  /**
   * 
   * @param persona persona a consultar.
   */
  private void buscar(FindIterable<Document> persona) {
    if (persona.first() != null) {
      diasSantos.setText("");
      observaciones.setText("");
      rellenarDatos(persona);
    } else {
      diasSantos.setText("No hay días santos para:\n-> " + buscador.getText() + "\nEn el filtro asignado");
      observaciones.setText("Sin observaciones de nombre");
    }
  }

  private void rellenarDatos(FindIterable<Document> persona) {
    persona.forEach(new Block<Document>() {

      @SuppressWarnings("unchecked")
      @Override
      public void apply(Document t) {
        agregarDiasSantos((List<String>) t.get("sants"));
        if (t.containsKey("observacions")) {
          agregarObservaciones(t.getString("observacions"));
        } else {
          observaciones.setText("NO HAY OBSERVACIONES DE NOMBRE\n");
        }
      }

    });
  }

  /**
   * 
   * @param santos lista con los nombres santos según el nombre a buscar.
   */
  private void agregarDiasSantos(List<String> santos) {
    if (santos != null && !santos.isEmpty()) {
      diasSantos.setText(Arrays.toString(santos.toArray()).replace("[", "").replace("]", ""));
      // santos.forEach(s -> diasSantos.appendText(s + "\n"));
    }
    if (santos.get(0).length() == 0) {
      diasSantos.setText("NO HAY DIAS SANTOS PARA EL NOMBRE:\n-> " + buscador.getText());
    }
  }

  /**
   * 
   * @param observacion es un String con los datos de observación según el nombre buscado.
   */
  private void agregarObservaciones(String observacion) {
    if (observacion != null && !observacion.isEmpty()) {
      observaciones.appendText(observacion.concat("\n"));
    }
  }
}
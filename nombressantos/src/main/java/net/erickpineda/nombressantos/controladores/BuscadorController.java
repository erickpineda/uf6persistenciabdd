package net.erickpineda.nombressantos.controladores;

import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

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

  @FXML
  public void initialize() {
    try {
      @SuppressWarnings("resource")
      MongoDatabase database = new MongoClient().getDatabase("classe");
      coll = database.getCollection("noms2");

      castellano.setToggleGroup(group);
      catalan.setToggleGroup(group);
      porFecha.setToggleGroup(group);

    } catch (Exception e) {

    }
  }

  @FXML
  public void buscarClicked(MouseEvent event) {
    if (buscador != null && !buscador.getText().isEmpty()) {
      if (castellano.isSelected()) {
        buscar(coll.find(new Document("castella", buscador.getText())));
      }
      if (catalan.isSelected()) {
        buscar(coll.find(new Document("catala", buscador.getText())));
      }
      if (porFecha.isSelected()) {

      }
    }
  }

  /**
   * 
   * @param persona persona a consultar.
   */
  private void buscar(FindIterable<Document> persona) {
    persona.forEach(new Block<Document>() {

      @SuppressWarnings("unchecked")
      @Override
      public void apply(Document t) {
        agregarDiasSantos((List<String>) t.get("sants"));
        if (t.containsKey("observacions")) {
          agregarObservaciones(t.getString("observacions"));
        } else {
          observaciones.setText("NO HAY OBSERVACIONES");
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
      diasSantos.setText("");
      santos.forEach(s -> diasSantos.appendText(s + "\n"));
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
      observaciones.setText(observacion);
    }
  }
}

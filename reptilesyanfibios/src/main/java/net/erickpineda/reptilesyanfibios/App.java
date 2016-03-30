package net.erickpineda.reptilesyanfibios;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
  public static Stage PRIMARY_STAGE;

  @Override
  public void start(Stage stage) {

    try {
      PRIMARY_STAGE = stage;
      BorderPane loader = FXMLLoader.load(getClass().getResource("/fxml/Animal.fxml"));
      Scene scene = new Scene(loader, Color.BLACK);
      stage.setScene(scene);
      stage.setTitle("¡Animales y Anfibios!");
      stage.getIcons().add(new Image(getClass().getResource("/img/mantis.png").toExternalForm()));
      stage.setScene(scene);
      stage.setMaxHeight(560);
      stage.setMinHeight(560);
      stage.setMaxWidth(720);
      stage.setMinWidth(720);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /*
   * @Override public void start(Stage stage) throws Exception { BorderPane pane = new BorderPane();
   * ImageView img = new ImageView("http://...");
   * 
   * img.fitWidthProperty().bind(stage.widthProperty());
   * 
   * pane.setCenter(img);
   * 
   * Scene scene = new Scene(pane); stage.setScene(scene); stage.show(); }
   */

  public static void main(String[] args) {
    Application.launch(App.class, (java.lang.String[]) null);
  }
}
package net.erickpineda.nombressantos;

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
      BorderPane bp = FXMLLoader.load(getClass().getResource("/fxml/Buscador.fxml"));
      Scene scene = new Scene(bp, Color.BLACK);
      stage.setScene(scene);
      stage.setTitle("Nombres Santos");
      stage.getIcons().add(new Image(getClass().getResource("/img/mantis.png").toExternalForm()));
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      //e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
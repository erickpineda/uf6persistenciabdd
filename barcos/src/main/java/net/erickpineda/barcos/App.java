package net.erickpineda.barcos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

  @Override
  public void start(Stage stage) {
    try {
      BorderPane bp = FXMLLoader.load(getClass().getResource("/fxml/Root.fxml"));
      Scene scene = new Scene(bp, Color.BLACK);
      stage.setScene(scene);
      stage.setTitle("Barcos y Tripulación");
      stage.getIcons().add(new Image(getClass().getResource("/img/mantis.png").toExternalForm()));
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}

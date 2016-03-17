package net.erickpineda.traductorcolores;

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
      BorderPane loader = FXMLLoader.load(getClass().getResource("/fxml/Traductor.fxml"));
      Scene scene = new Scene(loader, Color.BLACK);
      scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
      stage.setScene(scene);
      stage.setTitle("¡Traductor de colores!");
      stage.getIcons().add(new Image(getClass().getResource("/img/mantis.png").toExternalForm()));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Application.launch(App.class, (java.lang.String[]) null);
  }
}

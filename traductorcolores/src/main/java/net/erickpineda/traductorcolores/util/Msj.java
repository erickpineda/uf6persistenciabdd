package net.erickpineda.traductorcolores.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase con métodos estáticos que permiten abrir ventanas de tipo Dialog, según su conveniencia
 * INFORMATION, WARNING, ERROR y CONFIRMATION.
 * 
 * @author Erick Pineda.
 *
 */
public class Msj {
  public static void inf(String title, String header, String content) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
    alert.showAndWait();
  }

  public static void warn(String title, String header, String content) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
    alert.showAndWait();
  }

  public static void err(String title, String header, String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
    alert.showAndWait();
  }

  public static Optional<ButtonType> optional(String title, String header, String content) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
    return alert.showAndWait();
  }
}

package net.erickpineda.barcos.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
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
  public static void inf(String header, String content) {
	Alert alert = new Alert(AlertType.INFORMATION);
	DialogPane dialogPane = alert.getDialogPane();
	dialogPane.getStylesheets().add(Msj.class.getResource("/css/LightTheme.css").toExternalForm());
	alert.setTitle("Información");
	alert.setHeaderText(header);
	alert.setContentText(content);
	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
	alert.showAndWait();
  }

  public static void warn(String header, String content) {
	Alert alert = new Alert(AlertType.WARNING);
	DialogPane dialogPane = alert.getDialogPane();
	dialogPane.getStylesheets().add(Msj.class.getResource("/css/LightTheme.css").toExternalForm());
	alert.setTitle("Advertencia");
	alert.setHeaderText(header);
	alert.setContentText(content);
	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
	alert.showAndWait();
  }

  public static void err(String header, String content) {
	Alert alert = new Alert(AlertType.ERROR);
	DialogPane dialogPane = alert.getDialogPane();
	dialogPane.getStylesheets().add(Msj.class.getResource("/css/LightTheme.css").toExternalForm());
	alert.setTitle("Error");
	alert.setHeaderText(header);
	alert.setContentText(content);
	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
	alert.showAndWait();
  }

  public static Optional<ButtonType> optional(String title, String header, String content) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	DialogPane dialogPane = alert.getDialogPane();
	dialogPane.getStylesheets().add(Msj.class.getResource("/css/LightTheme.css").toExternalForm());
	alert.setTitle(title);
	alert.setHeaderText(header);
	alert.setContentText(content);
	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	stage.getIcons().add(new Image(Msj.class.getResource("/img/mantis.png").toExternalForm()));
	return alert.showAndWait();
  }
}

package net.erickpineda.barcos.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class TabPaneController {
  @FXML
  private SplitPane barcoExplorador;
  @FXML
  private SplitPane tripulanteExplorador;
  @FXML
  private AnchorPane apBarco;
  @FXML
  private AnchorPane apTripulante;

  @FXML
  private void initialize() {
	try {
	  
	} catch (Exception e) {
	  
	}
  }
  
  public void desactivarPaneles() {
	apBarco.setDisable(true);
	apTripulante.setDisable(true);
	tripulanteExplorador.setDisable(true);
	barcoExplorador.setDisable(true);
  }
}

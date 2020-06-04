package risco.vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import risco.HistorialFX;

public class ImportacionController implements Initializable {
  
  //Historial
  private HistorialFX history;
  @FXML
  private Label imNombre;
  @FXML
  private Label imHechas;
  @FXML
  private Label imMedia;
  @FXML
  private Label imPrimero;
  @FXML
  private Label imPuesto;
  @FXML
  private Button imcerrar;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    history = riscoController.getHistory();
    imNombre.setText(history.getNombreJugador());
    imHechas.setText(Integer.toString(history.getNumeroPartidas()));
    imMedia.setText(Double.toString(history.getMediaPuntos()));
    imPrimero.setText(Integer.toString(history.getPartidasPrimero()));
    imPuesto.setText(Double.toString(history.getPuestoMedio()));
    imcerrar.setOnAction(e -> {Stage stageImportacion = (Stage) imcerrar.getScene().getWindow();
                               stageImportacion.close();});
    

    
  }

}

package risco.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import risco.Partida;

public class riscoController implements Initializable {

  Partida partida;

  // risco.fxml //////
  // MenuItem
  @FXML
  private MenuItem exportar;
  @FXML
  private MenuItem importar;
  @FXML
  private MenuItem ayuda;
  // TextField
  @FXML
  private TextField jugador;
  // ImageView
  @FXML
  private ImageView imgDado1;
  @FXML
  private ImageView imgDado2;
  @FXML
  private ImageView imgDado3;

  // NumeroDeJugadores.fxml //////
  // TextField
  @FXML
  private TextField nJugadores;
  @FXML
  private Button aceptarNJugadores;

  // NumDados.fxml //////
  // TextField
  @FXML
  private TextField nDadosCambiar;

  @FXML
  void ventanaNombreJugadores(ActionEvent event) throws IOException {

    // Cierro la ventana previa (Número de jugadores)
    Stage stagenJugadores = (Stage) aceptarNJugadores.getScene().getWindow();
    stagenJugadores.close();

    List<TextField> textfields = new ArrayList<TextField>();

    int numJugadores = Integer.parseInt(nJugadores.getText());

    // DECLARAR PARTIDA TRAS VER EL NUMERO DE JUGADORES.
    partida = new Partida(numJugadores);

    Stage stgnomJugadores = new Stage();
    stgnomJugadores.setTitle("Risco: Introduce el número de jugadores");
    stgnomJugadores.setAlwaysOnTop(true);

    // Crear TextField y añadirlos a la lista de TextFields
    for (int i = 1; i <= numJugadores; i++) {
      TextField textF = new TextField("Jugador " + i);
      textfields.add(textF);
    }

    Pane panel = new Pane();
    panel.getChildren().add(new Label("Introduce el nombre de los jugadores: "));

    Button btnAc = new Button("Comenzar");
    btnAc.relocate(0, 20);
    panel.getChildren().add(btnAc);

    int contador = 1;
    for (TextField tf : textfields) {
      tf.relocate(0, 50 * contador);
      panel.getChildren().add(tf);
      contador++;
    }

    panel.setPadding(new Insets(20, 20, 20, 20));

    stgnomJugadores.setScene(new Scene(panel));
    stgnomJugadores.show();

    btnAc.setOnAction(actionEvent -> {
      int tmpCrearJugadores = 0;
      for (TextField tf : textfields) {
        tmpCrearJugadores++;
        partida.crearJugadores(tmpCrearJugadores, tf.getText());
      }
      stgnomJugadores.close();
    });

  }

  // Event Listener on Button.onAction
  @FXML
  public void ventanaDados(ActionEvent event) throws IOException {
    Stage stage = new Stage();
    stage.setTitle("Alta");

    FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("NumDados.fxml"));
    Pane root = fxml.<Pane>load();
    stage.setScene(new Scene(root));
    stage.showAndWait();
  }

  @FXML
  void cambiarDados(ActionEvent event) {

  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
  }

}

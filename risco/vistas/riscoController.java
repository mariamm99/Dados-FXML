package risco.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import risco.Jugador;
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

  // Tabla

  @FXML
  private TableView<Partida> table;

  @FXML
  private TableColumn<Partida, String> nombre;

  @FXML
  private TableColumn<Partida, Integer> risco;

  @FXML
  private TableColumn<Partida, Integer> trece;

  @FXML
  private TableColumn<Partida, Integer> escMayor;

  @FXML
  private TableColumn<Partida, Integer> escMenor;

  @FXML
  private TableColumn<Partida, Integer> escPar;

  @FXML
  private TableColumn<Partida, Integer> escImpar;

  @FXML
  private TableColumn<Partida, Integer> trio;

  @FXML
  private TableColumn<Partida, Integer> seis;

  @FXML
  private TableColumn<Partida, Integer> cinco;

  @FXML
  private TableColumn<Partida, Integer> cuatro;

  @FXML
  private TableColumn<Partida, Integer> tres;

  @FXML
  private TableColumn<Partida, Integer> dos;

  @FXML
  private TableColumn<Partida, Integer> ases;

  @FXML
  private TableColumn<Partida, Integer> total;

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

  @FXML
  public void actualizarTalba(ActionEvent event) {

    nombre.setCellValueFactory(new PropertyValueFactory<>("nJugadores"));

    risco.setCellValueFactory(new PropertyValueFactory<>("risco"));

    trece.setCellValueFactory(new PropertyValueFactory<>("trece"));

    escMayor.setCellValueFactory(new PropertyValueFactory<>("escMayor"));

    escMenor.setCellValueFactory(new PropertyValueFactory<>("escMenor"));

    escPar.setCellValueFactory(new PropertyValueFactory<>("escPar"));

    escImpar.setCellValueFactory(new PropertyValueFactory<>("escImpar"));

    trio.setCellValueFactory(new PropertyValueFactory<>("trio"));

    seis.setCellValueFactory(new PropertyValueFactory<>("seis"));

    cinco.setCellValueFactory(new PropertyValueFactory<>("cinco"));

    cuatro.setCellValueFactory(new PropertyValueFactory<>("cuatro"));

    tres.setCellValueFactory(new PropertyValueFactory<>("tres"));

    dos.setCellValueFactory(new PropertyValueFactory<>("dos"));

    ases.setCellValueFactory(new PropertyValueFactory<>("ases"));

    total.setCellValueFactory(new PropertyValueFactory<>("total"));

    int numeroJugadores = Integer.parseInt(nJugadores.getText());
    for (int i = 0; i < numeroJugadores; i++) {

    }

  }

  @FXML
  public void meterPuntos() {

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

  @FXML
  void tirarDados(ActionEvent event) {
    Jugador player = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(1)));
    System.out.println(partida.tirarDados(player));
  }

  private void cerrarPantalla() {
    // Stage stage = (Stage) boton.getScene().getWindow();
    // stage.close();
  }

  @FXML
  void salir(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void help(ActionEvent event) {
    Stage stageListar = new Stage();

    BorderPane root = new BorderPane();

    root.setCenter(new Label("Risco es un juego de dados en el cual gana el que mas puntos tiene. \n"
        + "El numero de jugadores puede ser el que quieras, y siempre habrá trece rondas para cada jugador. \n"
        + "según tus dados debes indicar si has echo risco, trece, seis, cinco. \n"
        + "Mostramos una tabla de las puntuaciones maximas de cada opción, debes rellanarlas todas\n"
        + "y no puedes repetir la opcion"));
    stageListar.setScene(new Scene(root, 400, 400));
    stageListar.show();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

}

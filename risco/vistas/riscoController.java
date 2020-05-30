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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

  // RADIO BUTTON
  @FXML
  private RadioButton rbRisco;

  @FXML
  private ToggleGroup grupo;

  @FXML
  private RadioButton rbTrece;

  @FXML
  private RadioButton rbEscMayor;

  @FXML
  private RadioButton rbEscMenor;

  @FXML
  private RadioButton rbEscPar;

  @FXML
  private RadioButton rbEscImpar;

  @FXML
  private RadioButton rbTrio;

  @FXML
  private RadioButton rbSeis;

  @FXML
  private RadioButton rbCinco;

  @FXML
  private RadioButton rbCuatro;

  @FXML
  private RadioButton rbTres;

  @FXML
  private RadioButton rbDos;

  @FXML
  private RadioButton rbAses;

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

  int numeroJugadores;
  Jugador player;

  // Ventana donde aparecerán los textFields para indicar el nombre de los
  // jugadores
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

    // creamos el pane
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

    // creamos los jugadores cuando tenemos los nombres
    btnAc.setOnAction(actionEvent -> {
      int tmpCrearJugadores = 0;
      for (TextField tf : textfields) {
        tmpCrearJugadores++;
        partida.crearJugadores(tmpCrearJugadores, tf.getText());
      }
      stgnomJugadores.close();
    });

  }

  // actualizar tabla

  @FXML
  public void actualizarTabla(ActionEvent event) {

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

    for (int i = 0; i < numeroJugadores; i++) {

    }

//    table.edit(1, total);

  }

  @FXML
  public void meterPuntos() {
    int n;
    boolean casillaOcupada = false;

    if (rbRisco.isSelected()) {
      n = Partida.Risco(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Has completado la casilla Risco: 50 puntos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbTrece.isSelected()) {
      n = Partida.Trece(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Casilla Trece completada: 26 ptos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscMayor.isSelected()) {
      n = Partida.EscaleraMayor(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Esta combinación es una Escalera Mayor, 20 ptos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscMenor.isSelected()) {
      n = Partida.EscaleraMenor(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Esta combinación es una Escalera Menor, 20 ptos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscPar.isSelected()) {
      n = Partida.EscaleraPar(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Esta combinación es una Escalera Par, 20 ptos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscImpar.isSelected()) {
      n = Partida.EscaleraImpar(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Esta combinación es una Escalera Impar, 20 ptos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbTrio.isSelected()) {
      n = Partida.Trio(player);
      if (n == 0) {
        System.out.println("Ha obtenido 0 puntos");
      } else if (n == 1) {
        System.out.println("Casilla Trío completada, 25 ptos");
      } else {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbSeis.isSelected()) {
      n = Partida.numero(player, 6);
      if (n == 50) {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        System.out.println("Casilla seises completada con " + n);
      }
    } else if (rbCinco.isSelected()) {
      n = Partida.numero(player, 5);
      if (n == 50) {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        System.out.println("Casilla cincos completada con " + n);

      }
    } else if (rbCuatro.isSelected()) {
      n = Partida.numero(player, 4);
      if (n == 50) {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        System.out.println("Casilla cuatros completada con " + n);

      }
    } else if (rbTres.isSelected()) {
      n = Partida.numero(player, 3);
      if (n == 50) {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        System.out.println("Casilla treses completada con " + n);
      }
    } else if (rbDos.isSelected()) {
      n = Partida.numero(player, 2);
      if (n == 50) {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        System.out.println("Casilla doses completada con " + n);
      }
    } else if (rbAses.isSelected()) {
      n = Partida.numero(player, 1);
      if (n == 50) {
        System.out.println("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        System.out.println("Casilla Ases completada con " + n);
      }
    }

  }

  // ventana donde se mostrará el resultado de los dados
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

  // primeros dados que se lanzan
  @FXML
  void tirarDados(ActionEvent event) {
    System.out.println(player);
    System.out.println(Partida.tirarDados(player));
  }

  // para cerrar la pantalla
  @FXML
  private void cerrarPantalla(ActionEvent event) {
    // Stage stage = (Stage) boton.getScene().getWindow();
    // stage.close();
  }

  // salir
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

  @FXML
  public void exportar(ActionEvent event) {
    int pos = partida.posicion(player);
    player.guardaDatos(numeroJugadores, pos);
  }

  @FXML
  public void importa(ActionEvent event) {
    // int pos = partida.posicion(player);
    // player.guardaDatos(nJugadores, pos);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    for (int i = 1; i <= numeroJugadores; i++) {

      player = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(i)));
      jugador.setText(player.toString());
    }
  }

}

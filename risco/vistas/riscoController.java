package risco.vistas;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import risco.Jugador;
import risco.Partida;

public class riscoController implements Initializable {

  static Partida partida;

  // risco.fxml //////
  // MenuItem
  @FXML
  private MenuItem exportar;
  @FXML
  private MenuItem importar;
  @FXML
  private MenuItem ayuda;

  @FXML
  private Menu mOpciones;

  @FXML
  private Menu mHelp;

  @FXML
  private Menu mExit;

  // TextField
  @FXML
  private TextField jugador;
  // TextArea de abajo
  @FXML
  private TextArea resultado;
  // Button
  @FXML
  private Button btnSiguiente;
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

  boolean primero = true; // Boolean para controlar si es el primer turno del primer jugador
                          // para variar el texto del botón Comenzar/Siguiente Turno
  static int numJugadores; // Número de jugadores en la partida
  int jugadorJugando = 0; // Número de jugador que está jugando en este momento
  static Jugador player;

  // Ventana donde aparecerán los textFields para indicar el nombre de los
  // jugadores
  @FXML
  public void ventanaNombreJugadores(ActionEvent event) throws IOException {

    // Cierro la ventana previa (Número de jugadores)
    Stage stagenJugadores = (Stage) aceptarNJugadores.getScene().getWindow();
    stagenJugadores.close();

    List<TextField> textfields = new ArrayList<TextField>();

    numJugadores = Integer.parseInt(nJugadores.getText());

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

  public void botonComenzar(ActionEvent e) {
    if (primero) {
      primero = false;
      btnSiguiente.setText("Siguiente Turno");
    }
    siguienteJug();
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

    for (int i = 0; i < numJugadores; i++) {

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
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Has completado la casilla Risco: 50 puntos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbTrece.isSelected()) {
      n = Partida.Trece(player);
      if (n == 0) {
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Casilla Trece completada: 26 ptos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscMayor.isSelected()) {
      n = Partida.EscaleraMayor(player);
      if (n == 0) {
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Esta combinación es una Escalera Mayor, 20 ptos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscMenor.isSelected()) {
      n = Partida.EscaleraMenor(player);
      if (n == 0) {
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Esta combinación es una Escalera Menor, 20 ptos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscPar.isSelected()) {
      n = Partida.EscaleraPar(player);
      if (n == 0) {
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Esta combinación es una Escalera Par, 20 ptos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbEscImpar.isSelected()) {
      n = Partida.EscaleraImpar(player);

      if (n == 0) {
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Esta combinación es una Escalera Impar, 20 ptos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbTrio.isSelected()) {
      n = Partida.Trio(player);
      if (n == 0) {
        resultado.setText("Ha obtenido 0 puntos");
      } else if (n == 1) {
        resultado.setText("Casilla Trío completada, 25 ptos");
      } else {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      }
    } else if (rbSeis.isSelected()) {
      n = Partida.numero(player, 6);
      if (n == 50) {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        resultado.setText("Casilla seises completada con " + n);
      }
    } else if (rbCinco.isSelected()) {
      n = Partida.numero(player, 5);
      if (n == 50) {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        resultado.setText("Casilla cincos completada con " + n);

      }
    } else if (rbCuatro.isSelected()) {
      n = Partida.numero(player, 4);
      if (n == 50) {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        resultado.setText("Casilla cuatros completada con " + n);
      }
    } else if (rbTres.isSelected()) {
      n = Partida.numero(player, 3);
      if (n == 50) {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        resultado.setText("Casilla treses completada con " + n);
      }
    } else if (rbDos.isSelected()) {
      n = Partida.numero(player, 2);
      if (n == 50) {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        resultado.setText("Casilla doses completada con " + n);
      }
    } else if (rbAses.isSelected()) {
      n = Partida.numero(player, 1);
      if (n == 50) {
        resultado.setText("Casilla ocupada indique una casilla vacia");
        casillaOcupada = true;
      } else {
        resultado.setText("Casilla Ases completada con " + n);
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

    int d;
    ImageView img;
    Image file = null;
    for (int i = 0; i < 3; i++) {

      if (i == 0) {
        d = Partida.tirarDados(player).getD1();
        img = imgDado1;
      } else if (i == 1) {
        d = Partida.tirarDados(player).getD2();
        img = imgDado2;
      } else {
        d = Partida.tirarDados(player).getD3();
        img = imgDado3;
      }

      if (d == 1) {
        file = new Image(".\\risco\\dados\\Dado1.png");
      } else if (d == 2) {
        file = new Image(".\\risco\\dados\\Dado2.png");
      } else if (d == 3) {
        file = new Image(".\\risco\\dados\\Dado3.png");
      } else if (d == 4) {
        file = new Image(".\\risco\\dados\\Dado4.png");
      } else if (d == 5) {
        file = new Image(".\\risco\\dados\\Dado5.png");
      } else if (d == 6) {
        file = new Image(".\\risco\\dados\\Dado6.png");
      }
      img.setImage(file);



    }
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
    player.guardaDatos(numJugadores, pos);
  }

  @FXML
  public void importa(ActionEvent event) {
    // int pos = partida.posicion(player);
    // player.guardaDatos(nJugadores, pos);
  }

  /**
   * Método para poner el nombre del jugador en el TextField de la ventana
   * principal después de alguna operación, como jugar un turno por otro jugador o
   * al inicio de la partida.
   */
  public void siguienteJug() {
    if (jugadorJugando >= numJugadores) {
      jugadorJugando = 1;
    } else {
      jugadorJugando++;
    }
    player = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(jugadorJugando)));
    String nombrejugador = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(jugadorJugando))).getNombre();
    jugador.setText(nombrejugador);
  }

  @FXML
  void menuTexto(Event event) {
    String id = ((Menu) event.getSource()).getId();

    if (id.equals(mExit.getId())) {
      resultado.setText("Cierra y termina el programa");

    } else if (id.equals(mOpciones.getId())) {
      resultado.setText(
          "Tiene la opción de exportar los datos de la partida y guardarlas en un archivo e importar datos de otras partidas");

    } else if (id.equals(mHelp.getId())) {
      resultado.setText("Muestra como es el juego");
    }
  }

  @FXML
  void limpiarResultado(Event event) {
    resultado.setText("");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

}

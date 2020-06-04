package risco.vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.control.Alert;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import risco.HistorialFX;
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
  private MenuItem salir;

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

  @FXML
  private Button btnAceptar;

  @FXML
  private Button tirarD;

  @FXML
  private Button cambiarD1;
  @FXML
  private Button cambiarD2;
  @FXML
  private Button cambiarD3;

  // ImageView
  @FXML
  private ImageView imgDado1;
  @FXML
  private ImageView imgDado2;
  @FXML
  private ImageView imgDado3;

  // Tabla

  @FXML
  private TableView<Jugador> table;

  @FXML
  private TableColumn<Jugador, String> nombre;

  @FXML
  private TableColumn<Jugador, Integer> risco;

  @FXML
  private TableColumn<Jugador, Integer> trece;

  @FXML
  private TableColumn<Jugador, Integer> escMayor;

  @FXML
  private TableColumn<Jugador, Integer> escMenor;

  @FXML
  private TableColumn<Jugador, Integer> escPar;

  @FXML
  private TableColumn<Jugador, Integer> escImpar;

  @FXML
  private TableColumn<Jugador, Integer> trio;

  @FXML
  private TableColumn<Jugador, Integer> seis;

  @FXML
  private TableColumn<Jugador, Integer> cinco;

  @FXML
  private TableColumn<Jugador, Integer> cuatro;

  @FXML
  private TableColumn<Jugador, Integer> tres;

  @FXML
  private TableColumn<Jugador, Integer> dos;

  @FXML
  private TableColumn<Jugador, Integer> ases;

  @FXML
  private TableColumn<Jugador, Integer> total;

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
  boolean casillaOcupada; // Boolean que controla si la casilla estaba ocupada
  static Jugador player;

  // Para guardar fichero
  private FileChooser fileSaver;
  private File ficheroGuardar;
  private BufferedWriter ficheroGuardador;

  // Para cargar fichero
  private FileChooser fileChooser;
  private File fichero;
  private BufferedReader ficheroLeer;
  // Para método importa
  private static HistorialFX history;

  public static HistorialFX getHistory() {
    return history;
  }

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

  /**
   * Para activar y desctivar los botones durante la partida
   */
  public void botonComenzar() {
    if (casillaOcupada) {

    } else {
      tirarD.setDisable(false);
    }
    cambiarD1.setDisable(true);
    cambiarD2.setDisable(true);
    cambiarD3.setDisable(true);
    if (primero) {
      primero = false;
      btnSiguiente.setVisible(false);
    }
    siguienteJug();
  }

  // actualizar tabla

  public void actualizarTabla() {

    nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    risco.setCellValueFactory(new PropertyValueFactory<>("p0"));

    trece.setCellValueFactory(new PropertyValueFactory<>("p1"));

    escMayor.setCellValueFactory(new PropertyValueFactory<>("p2"));

    escMenor.setCellValueFactory(new PropertyValueFactory<>("p3"));

    escPar.setCellValueFactory(new PropertyValueFactory<>("p4"));

    escImpar.setCellValueFactory(new PropertyValueFactory<>("p5"));

    trio.setCellValueFactory(new PropertyValueFactory<>("p6"));

    seis.setCellValueFactory(new PropertyValueFactory<>("p7"));

    cinco.setCellValueFactory(new PropertyValueFactory<>("p8"));

    cuatro.setCellValueFactory(new PropertyValueFactory<>("p9"));

    tres.setCellValueFactory(new PropertyValueFactory<>("p10"));

    dos.setCellValueFactory(new PropertyValueFactory<>("p11"));

    ases.setCellValueFactory(new PropertyValueFactory<>("p12"));

    total.setCellValueFactory(new PropertyValueFactory<>("p13"));
  }

  /**
   * Al pulsar el boton aceptar con un radio button seleccionado añade los puntos
   * obtenidos al jugadores. El resultado de esta acción se muestra en el TextArea
   * de la pantalla principal.
   * 
   * @param Event
   */
  @FXML
  public void meterPuntos(ActionEvent Event) {
    int n;
    casillaOcupada = false;

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
    table.getItems().clear();

    actualizarTabla();
    for (Jugador jug : partida.jugadores) {
      table.getItems().add(jug);
    }

    // si casilla está ocupada, realizamos eto para que el jugador no pierda el
    // turno
    if (!casillaOcupada) {
      btnAceptar.setDisable(true);
    }
    botonComenzar();

  }

  /**
   * Al pulsar para cambiar el dado 1
   * 
   * @param event
   */
  @FXML
  void cambiarDado1(ActionEvent event) {
    player.setDadoJugador(1);
    tirarUnDado(1);
    cambiarD1.setDisable(true);
  }

  /**
   * Al pulsar para cambiar el dado 2
   * 
   * @param event
   */
  @FXML
  void cambiarDado2(ActionEvent event) {
    player.setDadoJugador(2);
    tirarUnDado(2);
    cambiarD2.setDisable(true);
  }

  /**
   * Al pulsar para cambiar el dado 3
   * 
   * @param event
   */
  @FXML
  void cambiarDado3(ActionEvent event) {
    player.setDadoJugador(3);
    tirarUnDado(3);
    cambiarD3.setDisable(true);
  }

  /**
   * Función que se encarga de tirar los 3 dados y según el dado obtenido te
   * muestra la imagen de ese dado
   * 
   * @param nDado
   */
  void tirarUnDado(int nDado) {
    int d;
    ImageView img;
    Image file = null;

    if (nDado == 1) {
      d = player.getDadosJugador().getD1();
      img = imgDado1;
    } else if (nDado == 2) {
      d = player.getDadosJugador().getD2();
      img = imgDado2;
    } else {
      d = player.getDadosJugador().getD3();
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

  /**
   * Al pulsar el boton tirar dados, para lanzar los 3 dados
   * 
   * @param event
   */
  @FXML
  void tirarDados(ActionEvent event) {
    for (int i = 1; i <= 3; i++) {
      tirarUnDado(i);
    }
    cambiarD1.setDisable(false);
    cambiarD2.setDisable(false);
    cambiarD3.setDisable(false);
    tirarD.setDisable(true);
    btnAceptar.setDisable(false); // Activo aceptar para jugar el turno
  }

  /**
   * Metodo usado para cerrar la aplicación y terminar el juego
   * 
   * @param event
   */
  @FXML
  void salir(ActionEvent event) {
    System.exit(0);
  }

  /**
   * Boton de ayuda
   * 
   * @param event
   */
  @FXML
  void help(ActionEvent event) {
    Stage stageListar = new Stage();

    BorderPane root = new BorderPane();

    root.setCenter(new Label("Risco es un juego de dados en el cual gana el que mas puntos tiene. \n"
        + "El numero de jugadores puede ser el que quieras, y siempre habrá trece rondas para cada jugador. \n"
        + "según tus dados debes indicar si has echo risco, trece, seis, cinco. \n"
        + "Mostramos una tabla de las puntuaciones maximas de cada opción, debes rellanarlas todas\n"
        + "y no puedes repetir la opcion"));
    stageListar.setScene(new Scene(root, 600, 300));
    stageListar.show();
  }

  /**
   * Para exportar los datos de la partida
   * 
   * @param event
   */
  @FXML
  public void exportar(ActionEvent event) {
    int pos = partida.posicion(player);
    try {
      fileSaver = new FileChooser();
      fileSaver.setInitialFileName("risco_" + player.getNombre() + ".txt");
      fileSaver.getExtensionFilters().addAll(new ExtensionFilter("Archivos de Texto", "*.txt"));
      ficheroGuardar = fileSaver.showSaveDialog(new Stage());
      ficheroGuardador = new BufferedWriter(new FileWriter(ficheroGuardar.getAbsolutePath()));
      ficheroGuardador.write(player.guardaDatosFX(numJugadores, pos));
      ficheroGuardador.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Permite importar el historial de un jugador
   * 
   * @param event
   * @throws IOException
   */
  @FXML
  public void importa(ActionEvent event) throws IOException {
    try {
      fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Archivos de Texto", "*.txt"));
      fichero = fileChooser.showOpenDialog(new Stage());
      ficheroLeer = new BufferedReader(new FileReader(fichero.getAbsolutePath()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    history = new HistorialFX(player, fichero);

    Stage stage = new Stage();

    FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("Importacion.fxml"));
    Pane root = fxml.<Pane>load();
    stage.setScene(new Scene(root));

    stage.setTitle("Datos importados");

    stage.show();

    System.out.println(history);

    ficheroLeer.close();
  }

  /**
   * Método para poner el nombre del jugador en el TextField de la ventana
   * principal después de alguna operación, como jugar un turno por otro jugador o
   * al inicio de la partida.
   */
  public void siguienteJug() {

    if (casillaOcupada) {

    } else if (jugadorJugando >= numJugadores) {
      jugadorJugando = 1;
      partida.setRonda();
      if (partida.getRonda() > 13) {
        alerta();
      }
      System.out.println(partida.getRonda());
    } else {
      jugadorJugando++;
    }
    player = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(jugadorJugando)));
    String nombrejugador = partida.jugadores.get(partida.jugadores.indexOf(new Jugador(jugadorJugando))).getNombre();
    jugador.setText(nombrejugador);
    if (!casillaOcupada) {
      for (int i = 1; i < 4; i++) {
        player.setDadoJugador(i);
      }
    }

    btnSiguiente.setDisable(false);
  }

  /**
   * Método para mostrar información del menú cuando pulsas sobre el. Aparece en
   * el TextArea de la pantalla princial.
   * 
   * @param event
   */
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

  /**
   * Para limpiar el TextArea principal.
   * 
   * @param event
   */
  @FXML
  void limpiarResultado(Event event) {
    resultado.setText("");
  }

  /**
   * Alerta que muestra quien es el ganador al terminar la partida
   */
  private void alerta() {

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Fin de la partida");
    alert.setHeaderText("Fin de la partida");
    alert.setContentText("El ganador de la partida ha sido" + jugadorGanador());
    alert.showAndWait();
  }

  private String jugadorGanador() {
    int pos = 10;
    for (int i = 0; i < numJugadores; i++) {
      if (pos != 1) {
        pos = partida.posicion(player);

      } else {
        return player.getNombre();
      }
      siguienteJug();

    }
    return null;

  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

}

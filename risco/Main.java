package risco;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {

  /**
   * Pantalla principal para pedir el número de jugadores del juego
   * @throws IOException
   */
  public void pedirJugadores() throws IOException {
    Stage stgJugadores = new Stage();
    FXMLLoader fxmlJugadores = new FXMLLoader(this.getClass().getResource("vistas/NumeroDeJugadores.fxml"));
    stgJugadores.setTitle("Risco: Introduce el número de jugadores");
    Pane rootJugadores = fxmlJugadores.<Pane>load();
    stgJugadores.setScene(new Scene(rootJugadores));
    stgJugadores.setAlwaysOnTop(true);
    stgJugadores.show();
  }

  @Override
  public void start(Stage primaryStage) {

    try {
      /**
       * Creamos la pantalla principal del juego.
       */
      FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("vistas/risco.fxml"));
      primaryStage.setTitle("Risco");
      BorderPane root = fxml.<BorderPane>load();

      primaryStage.setScene(new Scene(root));
      pedirJugadores();
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}

package risco.vistas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.awt.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gestisimal.Articulo;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import risco.Partida;

public class riscoController {
	@FXML
	private MenuItem exportar;
	@FXML
	private MenuItem importar;
	@FXML
	private MenuItem ayuda;
	@FXML
	private TextField Jugador;
	@FXML
	private ImageView imgDado1;
	@FXML
	private ImageView imgDado2;
	@FXML
	private ImageView imgDado3;
	
  @FXML
  private TextField nJugadores;
  
  @FXML
  private TextField nDadosCambiar;

  
  Partida partida;

  @FXML
  void ventanaNombreJugadores(ActionEvent event) {
   
    ArrayList<String> nombres=new ArrayList<String>();

    List<TextField> text = new ArrayList<>();
   
   int numJugadores=Integer.parseInt(nJugadores.getText());
   
   //DECLARAR PARTIDA TRAS VER EL NUMERO DE JUGADORES. NO SE SI HACERLO ASI PERMITIRÁ LUEGO EL ACCESO DESDE OTRAS FUNCIONES
   partida=new Partida(numJugadores);
   
   Stage stage = new Stage();
   stage.setTitle("Nombre Jugadores");

//   FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("baja.fxml"));
//
//   Pane root = fxml.<Pane>load();
   
   
   BorderPane root = new BorderPane();
   VBox vbox = new VBox();
   
  for (int i = 0; i < numJugadores; i++) {
    TextField textF = new TextField();
    text.add(textF);
  } 
  

  //asi recorremos todos los textFields q necesitamos, podriamos utilizarlo de nombre textField0, textField1, pero no se como crearlos
  for (TextField TextF : text) {
    nombres.add(TextF.getText());
    
  }
  
  Button boton = new Button("iniciar partida");
  
  
   //root.setCenter(vbox);
   stage.setScene(new Scene(root));
   stage.showAndWait();
   
    
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
	
}

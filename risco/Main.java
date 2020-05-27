package risco;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		  FXMLLoader fxml = new FXMLLoader(this.getClass().getResource("vistas/risco.fxml"));
	    primaryStage.setTitle("Buscar un patrón");
	    Pane root = fxml.<Pane>load();
	    
	    primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

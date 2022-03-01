package application;
	
import java.sql.SQLException;
import dbConnection.DBManagerConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Principal extends Application
{
	
	public void start(Stage primaryStage) {
		
		if(_intentarConexion())
		{
			try {
				
				Scene es = null;
				
				/*
				 * Cargamos el disenio 
				 */
				
				Parent root = FXMLLoader.load(getClass().getResource("/scene/menuPrincipal.fxml"));
					
				es = new Scene(root);
				
				/*
				 * Configuramos la escena
				 */
				primaryStage.getIcons().add(new Image(getClass().getResource("/img/buquealo.png").toString()));
				primaryStage.setScene(es);
				primaryStage.show();
				
				
				} 
				
				catch(Exception e) 
				{
					e.printStackTrace();
				}
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public boolean _intentarConexion()
	{
		
		boolean resB = true;
		
		try {
			DBManagerConnection.upConnection();
		}
		catch(SQLException | ClassNotFoundException ex2)
		{
			System.out.println("No conecta base de datos");
			ex2.printStackTrace();
			resB = false;
		}
		
		return resB;
	}

}
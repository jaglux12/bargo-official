package Controlador.menuPrincipal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SobreBargo implements Initializable
{
	
	@FXML
	public WebView paginaWeb = new WebView();
	
	public void iniciarVentana()
	{
		new NuevoLibro_Controlador().iniciarVentana();
	}
	
	class NuevoLibro_Controlador 
	{
		private Scene escena;
		private Stage escenario;
		
		public NuevoLibro_Controlador() 
		{
			escenario = new Stage();
			escena = null;
			_cargarDisenio();
		}
		
		private void _cargarDisenio()
		{
			
			try
			{
				Parent root = FXMLLoader.load(getClass().getResource("/scene/sobreBargo.fxml"));
				escena = new Scene(root);
				escenario.setScene(escena);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
			
		public void iniciarVentana()
		{
			escenario.setResizable(false);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.getIcons().add(new Image(getClass().getResource("/img/buquealo.png").toString()));
			escenario.setTitle("Nuevo Libro");
			escenario.showAndWait();
		}
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		paginaWeb.getEngine().load(getClass().getResource("/paginasWeb/creador.html").toString());
		paginaWeb.setZoom(0.70);
	}
	
}
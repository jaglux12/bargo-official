package Controlador.menuPrincipal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuInformes implements Initializable
{
	
	@FXML
	public BorderPane panelPrincipal = new BorderPane();
	
	private Informes_Controlador cFrame;
	
	/*
	 * Estados de los paneles
	 */
	public String estadoPanelBusqueda = "none";
	
	public void click_infoLibros(ActionEvent e)
	{
		_cambioPanel_Libro();
	}
	
	public void iniciarVentana()
	{
		cFrame = new Informes_Controlador();
		cFrame.iniciarVentana();
	}
	
	class Informes_Controlador
	{
		private Scene escena;
		private Stage escenario;
		
		public Informes_Controlador() 
		{
			escenario = new Stage();
			escena = null;
			_cargarDisenio();
		}
		
		private void _cargarDisenio()
		{
			
			try
			{
				Parent root = FXMLLoader.load(getClass().getResource("/scene/informes.fxml"));
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
			escenario.showAndWait();
		}
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//_cambioPanel_Informes();
		_cambioPanel_Libro();
	}
	
	public void _cambioPanel_Libro()
	{
		if(estadoPanelBusqueda != "Libros") {
		panelPrincipal.getChildren().clear();
		try {
			BorderPane panelBusquedaLibros = FXMLLoader.load(
			getClass().getResource("/scene/informe_enobra.fxml"));
			panelPrincipal.getChildren().add(panelBusquedaLibros);
			estadoPanelBusqueda = "Libros";
				
		}catch(Exception ex)
		{
			System.out.println("Error cargando");
			ex.printStackTrace();
		}
		}
	}
	
	public void _cambioPanel_Informes()
	{
		if(estadoPanelBusqueda != "Informes") {
		panelPrincipal.getChildren().clear();
		try {
			BorderPane panelBusquedaLibros = FXMLLoader.load(
			getClass().getResource("/scene/informe_datos.fxml"));
			panelPrincipal.getChildren().add(panelBusquedaLibros);
			estadoPanelBusqueda = "Informes";
				
		}catch(Exception ex)
		{
			System.out.println("Error cargando");
			ex.printStackTrace();
		}
		}
	}
	
}
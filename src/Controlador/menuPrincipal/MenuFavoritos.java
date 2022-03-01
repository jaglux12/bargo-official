package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dbQuerys.ManagerFavoritos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelosTablas.Favoritos;

public class MenuFavoritos implements Initializable
{
	@FXML
	public TableView<Object> tRegistros = new TableView<Object>();
	@FXML
	public TableColumn<Object,String> cID = new TableColumn<>();
	@FXML
	public TableColumn<Object,String> cNombre = new TableColumn<>();
	@FXML
	public TableColumn<Object,String> cAutor = new TableColumn<>();
	@FXML
	public TableColumn<Object,String> cEditorial = new TableColumn<>();
	@FXML
	public Button bBuscar = new Button();
	@FXML
	public Button bRecargar = new Button();
	@FXML
	public Button bBorrar = new Button();
	@FXML
	public TextField tfBusqueda = new TextField();
	
	
	public void iniciarVentana()
	{
		new NuevoLibro_Controlador().iniciarVentana();
	}
	
	public void recargar()
	{
		bRecargar.setDisable(true);
		
		try
		{
			tRegistros.getItems().clear();
			
			ManagerFavoritos tLibros = new ManagerFavoritos();
				
			Favoritos[] lista = tLibros.getFavoritos();
				
			for(int i = 0; i < lista.length; i++)
			{
				tRegistros.getItems().add(lista[i]);
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Error buscando registros");
		}
		
		bRecargar.setDisable(false);
	}
	
	public void borrar()
	{
		Favoritos itemSelec = (Favoritos)tRegistros.getSelectionModel().getSelectedItem();
		
		if(itemSelec!=null)
		{
			bBorrar.setDisable(true);
			
			try
			{

				ManagerFavoritos tLibros = new ManagerFavoritos();
				
				boolean res = tLibros.elimRegistro(itemSelec.getId());
					
				if(res)
				{
					recargar();
				}
				
			}
			catch(SQLException ex)
			{
				System.out.println("Error buscando registros");
			}
			
			bBorrar.setDisable(false);
		}
		
	}
	
	public void buscar()
	{
		
		bBuscar.setDisable(true);
		
		try
		{
			tRegistros.getItems().clear();
			
			ManagerFavoritos tLibros = new ManagerFavoritos();
				
			Favoritos[] lista = tLibros.getFavoritos_iniciaCon(tfBusqueda.getText());
				
			for(int i = 0; i < lista.length; i++)
			{
				tRegistros.getItems().add(lista[i]);
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Error buscando registros");
		}
		
		bBuscar.setDisable(false);
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
				Parent root = FXMLLoader.load(getClass().getResource("/scene/verFavoritos.fxml"));
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
			escenario.setTitle("Favoritos");
			escenario.showAndWait();
		}
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		cID.setCellValueFactory(new PropertyValueFactory<>("id"));
		cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		cAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		cEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
	}
	
}
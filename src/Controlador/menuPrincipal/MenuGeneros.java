package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dbQuerys.ManagerGeneros;
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
import modelosTablas.Generos;

public class MenuGeneros implements Initializable
{
	@FXML
	public TableView<Object> tRegistros = new TableView<Object>();
	@FXML
	public TableColumn<Object,String> nombre_genero = new TableColumn<>();
	@FXML
	public Button bBuscar = new Button();
	@FXML
	public Button bRecargar = new Button();
	@FXML
	public Button bBorrar = new Button();
	@FXML
	public TextField tfBusqueda = new TextField();
	@FXML
	public Button bNuevo = new Button();
	
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
			
			ManagerGeneros mg = new ManagerGeneros();
				
			Generos[] lista = mg.getGeneros();
				
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
		Generos itemSelec = (Generos)tRegistros.getSelectionModel().getSelectedItem();
		
		if(itemSelec!=null)
		{
			bBorrar.setDisable(true);
			
			try
			{

				ManagerGeneros tLibros = new ManagerGeneros();
				
				boolean res = tLibros.elimRegistro(itemSelec.getNombre_genero());
					
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
	
	public void nuevoGenero()
	{		
		NuevoGenero ng = new NuevoGenero();
		ng.iniciarVentana(null);
	}
	
	public void editarGenero()
	{
		NuevoGenero ng = new NuevoGenero();
		Generos g = (Generos)tRegistros.getSelectionModel().getSelectedItem();
		
		ng.iniciarVentana(g);
	}
	
	public void buscar()
	{
		
		bBuscar.setDisable(true);
		
		try
		{
			tRegistros.getItems().clear();
			
			ManagerGeneros mg = new ManagerGeneros();
				
			Generos[] lista = mg.buscarGenero(tfBusqueda.getText());
				
			for(int i = 0; i < lista.length; i++)
			{
				tRegistros.getItems().add(lista[i]);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
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
				Parent root = FXMLLoader.load(getClass().getResource("/scene/menuGeneros.fxml"));
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
		nombre_genero.setCellValueFactory(new PropertyValueFactory<>("nombre_genero"));
	}
	
}
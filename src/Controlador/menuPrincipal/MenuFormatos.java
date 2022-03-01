package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dbQuerys.ManagerFormatos;
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
import modelosTablas.Formatos;

public class MenuFormatos implements Initializable
{
	@FXML
	public TableView<Object> tRegistros = new TableView<Object>();
	@FXML
	public TableColumn<Object,String> nFormato = new TableColumn<>();
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
			
			ManagerFormatos mg = new ManagerFormatos();
				
			Formatos[] lista = mg.getFormatos();
				
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
		Formatos itemSelec = (Formatos)tRegistros.getSelectionModel().getSelectedItem();
		
		if(itemSelec!=null)
		{
			bBorrar.setDisable(true);
			
			try
			{

				ManagerFormatos tLibros = new ManagerFormatos();
				
				boolean res = tLibros.elimRegistro(itemSelec.getTipo_formato());
					
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
	
	public void nuevoFormato()
	{		
		NuevoFormato ne = new NuevoFormato();
		ne.iniciarVentana(null);
	}
	
	public void editarEditorial()
	{
		NuevoFormato ng = new NuevoFormato();
		Formatos g = (Formatos)tRegistros.getSelectionModel().getSelectedItem();
		
		ng.iniciarVentana(g);
	}
	
	public void buscar()
	{
		
		bBuscar.setDisable(true);
		
		try
		{
			tRegistros.getItems().clear();
			
			ManagerFormatos mg = new ManagerFormatos();
				
			Formatos[] lista = mg.tabla_buscarEmpiezaPor(tfBusqueda.getText().toLowerCase());
				
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
				Parent root = FXMLLoader.load(getClass().getResource("/scene/menuFormatos.fxml"));
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
			escenario.setTitle("Editoriales");
			escenario.showAndWait();
		}
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		nFormato.setCellValueFactory(new PropertyValueFactory<>("tipo_formato"));
	}
	
}
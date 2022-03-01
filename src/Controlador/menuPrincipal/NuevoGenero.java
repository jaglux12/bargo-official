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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelosTablas.Generos;

public class NuevoGenero implements Initializable
{
	
	@FXML
	public TextField tfNombre = new TextField();
	
	@FXML
	public Button bGuardar = new Button();
	
	@FXML
	public Button bBorrar = new Button();
	
	@FXML
	public ImageView imgAgregado = new ImageView();
	
	/*
	 * En caso de que se inicie con un objeto(libro) se cargan
	 * sus datos desde la instancia.
	 */
	private static Generos datos = null;
	
	public void iniciarVentana(Generos l)
	{
		datos = l;
		new NuevoGenero_Controlador().iniciarVentana();
	}
	
	public void guardar()
	{
		new agregarRegistro().start();
	}
	
	public void limpiarContenido()
	{
		tfNombre.setText("");
	}
	
	class NuevoGenero_Controlador 
	{
		private Scene escena;
		private Stage escenario;
		
		public NuevoGenero_Controlador() 
		{
			escenario = new Stage();
			escena = null;
			_cargarDisenio();
		}
		
		private void _cargarDisenio()
		{
			
			try
			{
				Parent root = FXMLLoader.load(getClass().getResource("/scene/nuevoGenero.fxml"));
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
	
	class agregarRegistro extends Thread 
	{
		
		public void run() 
		{
			
			bGuardar.setDisable(true);
			
			if(verificarTextFields())
			{
				
				String nombre = tfNombre.getText().toLowerCase();
				
				Generos l = new Generos();
				
				l.setNombre_genero(nombre);
				
			   try
			   {	
					ManagerGeneros ml = new  ManagerGeneros();
					if(datos!=null)
					{
						boolean res = ml.editarGeneros(l, datos.getNombre_genero());
						
						if(res)
						{
							Image img = new Image(getClass().getResource("/img/gifs/agregado.gif").toString());
							imgAgregado.setImage(img);
							imgAgregado.setVisible(true);
							sleep(2000);
							imgAgregado.setVisible(false);
						}
						
					}
					else
					{
						boolean res = ml.addGenero(l);
						
						if(res)
						{
							Image img = new Image(getClass().getResource("/img/gifs/agregado.gif").toString());
							imgAgregado.setImage(img);
							imgAgregado.setVisible(true);
							sleep(2000);
							imgAgregado.setVisible(false);
						}
					}
					
				}catch(SQLException ex)
				{
					bGuardar.setDisable(false);
					System.out.println("Error");
				} catch (InterruptedException e) {
					e.printStackTrace();
					bGuardar.setDisable(false);
				}
				
				bGuardar.setDisable(false);
			}
			else
			{
				try
				{
					Image img = new Image(getClass().getResource("/img/gifs/error.gif").toString());
					imgAgregado.setImage(img);
					imgAgregado.setVisible(true);
					sleep(2000);
					imgAgregado.setVisible(false);
				}catch(Exception ex)	
				{
					System.out.println("Error esperando");
					bGuardar.setDisable(false);
				}
				
			}
			bGuardar.setDisable(false);
			
		}
		
		public boolean verificarTextFields()
		{
			boolean res = true;
			
			if(tfNombre.getText().equals(""))
			{
				res = false;
			}
		
			return res;
		}
		
	}


	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		if(datos!=null)
		{
			tfNombre.setText(datos.getNombre_genero());
		}
		
	}
	
}
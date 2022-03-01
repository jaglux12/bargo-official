package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dbQuerys.ManagerEditoriales;
import dbQuerys.ManagerFormatos;
import dbQuerys.ManagerGeneros;
import dbQuerys.ManagerLIbros;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelosTablas.Editoriales;
import modelosTablas.Formatos;
import modelosTablas.Generos;
import modelosTablas.Libro;

public class NuevoLibro implements Initializable
{
	
	@FXML
	public TextField tfNombre = new TextField();
	
	@FXML
	public TextField tfAutor = new TextField();
	
	@FXML
	public TextField tfNpag = new TextField();
	
	@FXML
	public TextField tfCantidad = new TextField();
	
	@FXML
	public ComboBox<String> cbGenero = new ComboBox<String>();
	
	@FXML
	public ComboBox<String> cbEditorial = new ComboBox<String>();
	
	@FXML
	public ComboBox<String> cbFormato = new ComboBox<String>();
	
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
	private static Libro datos = null;
	
	public void iniciarVentana(Libro l)
	{
		datos = l;
		new NuevoLibro_Controlador().iniciarVentana();
	}
	
	public void guardar()
	{
		new agregarRegistro().start();
	}
	
	public void limpiarContenido()
	{
		tfNombre.setText("");
		tfAutor.setText("");
		cbGenero.setValue("");
		cbEditorial.setValue("");
		cbFormato.setValue("");
		tfNpag.setText("");
		tfCantidad.setText("");
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
				Parent root = FXMLLoader.load(getClass().getResource("/scene/nuevoLibro.fxml"));
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
				String autor = tfAutor.getText().toLowerCase();
				String genero = cbGenero.getValue();
				String editorial = cbEditorial.getValue();
				String formato = cbFormato.getValue();
				int npag = Integer.parseInt(tfNpag.getText());
				int cant = Integer.parseInt(tfCantidad.getText());
				
				Libro l = new Libro();
				
				l.setNombre(nombre);
				l.setAutor(autor);
				l.setGenero(genero);
				l.setEditorial(editorial);
				l.setFormato(formato);
				l.setN_pag(npag);
				l.setCantidad(cant);
				
			   try
			   {	
					ManagerLIbros ml = new  ManagerLIbros();
					if(datos!=null)
					{
						l.setId(datos.getId());
						boolean res = ml.editarLibro(l);
						
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
						boolean res = ml.addLibro(l);
						
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
			
			if(tfNombre.getText().equals("") ||
			   tfAutor.getText().equals(""))
			{
				res = false;
			}
			if(cbGenero.getValue() == null || cbGenero.getValue().equals("") ||
			   cbEditorial.getValue() == null || cbEditorial.getValue().equals("") ||
			   cbFormato.getValue() == null || cbFormato.getValue().equals("")) 
			{
				res = false;
			}
			try
			{
				int npag = Integer.parseInt(tfNpag.getText());
				int cant = Integer.parseInt(tfCantidad.getText());
			}catch(NumberFormatException ex)
			{
				res = false;
			}
			return res;
		}
		
	}


	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		try {
			
			Generos[] listaGeneros = new ManagerGeneros().getGeneros();
			
			for(int i = 0; i < listaGeneros.length; i++)
			{
				cbGenero.getItems().add(listaGeneros[i].getNombre_genero());
			}
			
			Editoriales[] listaEditoriales = new ManagerEditoriales().getEditoriales();
			
			for(int i = 0; i < listaEditoriales.length; i++)
			{
				cbEditorial.getItems().add(listaEditoriales[i].getNombre_editorial());
			}
			
			Formatos[] listaFormatos = new ManagerFormatos().getFormatos();
			
			for(int i = 0; i < listaFormatos.length; i++)
			{
				cbFormato.getItems().add(listaFormatos[i].getTipo_formato());
			}			
			
		}catch(SQLException ex)
		{
			System.out.println("Error cargando datos");
		}
		
		if(datos!=null)
		{
			tfNombre.setText(datos.getNombre());
			tfAutor.setText(datos.getAutor());
			cbGenero.setValue(datos.getGenero());
			cbEditorial.setValue(datos.getEditorial());
			cbFormato.setValue(datos.getFormato());
			tfNpag.setText(Integer.toString(datos.getN_pag()));
			tfCantidad.setText(Integer.toString(datos.getCantidad()));
		}
		
	}
	
}
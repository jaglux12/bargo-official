package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dbQuerys.ManagerEditoriales;
import dbQuerys.ManagerProxLecturas;
import javafx.event.ActionEvent;
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
import modelosTablas.Prox_Lecturas;

public class NuevaProxLectura implements Initializable
{

	@FXML
	public TextField tfNombre = new TextField();
	
	@FXML
	public TextField tfAutor = new TextField();
	
	@FXML
	public TextField tfEditorial = new TextField();
	
	@FXML
	public Button bEditorial = new Button();
	
	@FXML
	public Button bGuardar = new Button();
	
	@FXML
	public Button bBorrar = new Button();
	
	@FXML
	public ComboBox<String> cbEditoriales = new ComboBox<String>();
	
	@FXML
	public ImageView imgAgregado = new ImageView();
	
	/*
	 * En caso de que se inicie con un objeto(libro) se cargan
	 * sus datos desde la instancia.
	 */
	private static Prox_Lecturas datos = null;
	
	/*
	 * Estados
	 */
	private boolean estadoLista = false;
	
	public void guardar(ActionEvent e)
	{
		new agregarProxLectura().start();
	}
	
	public void limpiarContenido(ActionEvent e)
	{
		tfNombre.setText("");
		tfAutor.setText("");
		tfEditorial.setText("");
		
	}
	
	public void click_elegirEditorial(ActionEvent e)
	{
		if(estadoLista)
		{
			cbEditoriales.setVisible(false);
			estadoLista = false;
		}
		else
		{
			cbEditoriales.setVisible(true);
			estadoLista = true;
		}
	}
	
	public void clic_cambioEditorial(ActionEvent e)
	{
		tfEditorial.setText(cbEditoriales.getValue());
	}
	
	public void iniciarVentana(Prox_Lecturas pl)
	{
		datos = pl;
		new NuevaProxLectura_Ventana().iniciarVentana();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		cbEditoriales.setVisible(false);
		
		ManagerEditoriales ed = new ManagerEditoriales();
		
		try
		{
			
			Editoriales[] editoriales = ed.getEditoriales();
			
			for(int i = 0; i < editoriales.length; i++)
			{
				cbEditoriales.getItems().add(editoriales[i].getNombre_editorial());
			}
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(datos!=null)
		{
			tfNombre.setText(datos.getNombre_libro());
			tfAutor.setText(datos.getAutor());
			tfEditorial.setText(datos.getEditorial());
		}
		
	}

	class agregarProxLectura extends Thread
	{
		
		public void run() 
		{
			bGuardar.setDisable(true);
			
			if(verificarCampos())
			{
				String nombreLibro = tfNombre.getText().toLowerCase();
				String autorLibro = tfAutor.getText().toLowerCase();
				String editorialLibro = tfEditorial.getText();
				
				Prox_Lecturas proxLectura = new Prox_Lecturas();
				
				proxLectura.setAutor(autorLibro);
				proxLectura.setEditorial(editorialLibro);
				proxLectura.setNombre_libro(nombreLibro);
				
				try {
					
					ManagerProxLecturas mpl = new ManagerProxLecturas();
					
					if(datos!=null)
					{
						proxLectura.setId(datos.getId());
						boolean res =mpl.editarProxLectura(proxLectura);
						
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
						boolean res = mpl.addProxLectura(proxLectura);
						
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
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
					bGuardar.setDisable(false);
				}
				
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
		
		public boolean verificarCampos()
		{
			boolean res = true;
			
			if(tfNombre.getText().equals(""))
			{
				res = false;
			}
			if(tfAutor.getText().equals(""))
			{
				res = false;
			}
			if(tfEditorial.getText().equals(""))
			{
				res = false;
			}
			
			return res;
		}
	}
	
	class NuevaProxLectura_Ventana 
	{
		private Scene escena;
		private Stage escenario;
		
		public NuevaProxLectura_Ventana() 
		{
			escenario = new Stage();
			escena = null;
			_cargarDisenio();
		}
		
		private void _cargarDisenio()
		{
			
			try
			{
				Parent root = FXMLLoader.load(getClass().getResource("/scene/nuevaProxLectura.fxml"));
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
			escenario.setTitle("Nueva próxima lectura");
			escenario.showAndWait();
		}
		
	}
	
}

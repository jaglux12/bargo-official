package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import dbQuerys.ManagerLIbros;
import dbQuerys.ManagerOpiniones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import modelosTablas.LibrOP;
import modelosTablas.Opiniones;

public class NuevaOpinion implements Initializable
{

	@FXML
	public TextField tfLibro = new TextField();
	
	@FXML
	public DatePicker dpFecha = new DatePicker(LocalDate.now());
	
	@FXML
	public Button bGuardar = new Button();
	
	@FXML
	public Button bBorrar = new Button();
	
	@FXML
	public TextArea taCuerpo = new TextArea();
	
	@FXML
	public Slider sCalificacion = new Slider();
	
	@FXML
	public Button bLibros = new Button();
	
	@FXML
	public ImageView imgAgregado = new ImageView();
	
	/*
	 *  En caso de que se inicie con un objeto(Opinion) se cargan
	 *  sus datos desde la instancia.
	 */
	private static Opiniones datos = null;
	
	public void borrar(ActionEvent e)
	{
		tfLibro.setText("");
		dpFecha.setValue(null);
		taCuerpo.setText("");
		sCalificacion.setValue(5);
	}
	
	public void iniciarVentana(Opiniones op)
	{
		datos = op;
		new NuevaOpinion_Ventana().iniciarVentana();
	}
	
	class NuevaOpinion_Ventana 
	{
		private Scene escena;
		private Stage escenario;
		
		public NuevaOpinion_Ventana() 
		{
			escenario = new Stage();
			escena = null;
			_cargarDisenio();
		}
		
		private void _cargarDisenio()
		{
			
			try
			{
				Parent root = FXMLLoader.load(getClass().getResource("/scene/nuevaOpinion.fxml"));
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
			escenario.setTitle("Nueva opinión");
			escenario.showAndWait();
		}
		
	}
	
	public void bBuscarLibro(ActionEvent e)
	{
		try
		{
			
			String nLibro = tfLibro.getText().toLowerCase();
			
			if(!nLibro.isEmpty())
			{
				
				ArrayList<LibrOP> lista = new ManagerLIbros().lista_busquedaEmpiezaPor(nLibro);
				LibrOP item;
				
				ChoiceDialog<LibrOP>  dialogo = new ChoiceDialog<LibrOP>();
				
				dialogo.setTitle("Resultados ");
				dialogo.setContentText("Elige una opción ");
				dialogo.setHeaderText("Busqueda de libros ");
				
				for(int i = 0; i < lista.size(); i++)
				{
					item = lista.get(i);
					dialogo.getItems().add(item);
				}
				
				Optional<LibrOP> res = dialogo.showAndWait();
				
				if(res.isPresent())
				{
					String resultado = Integer.toString(dialogo.getSelectedItem().getId());
					tfLibro.setText(resultado);
				}
				
			}
			
				
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void guardar(ActionEvent e)
	{
		new agregarOpinion().start();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		dpFecha.setConverter(new LocalDateStringConverter(ftf,null));
		if(datos!=null)
		{
			tfLibro.setText(Integer.toString(datos.getId_libro()));
			StringTokenizer ftok = new StringTokenizer(datos.getFecha(), "-");
			dpFecha.setValue(LocalDate.of(
										   Integer.parseInt(ftok.nextToken()),
										   Integer.parseInt(ftok.nextToken()),
					                       Integer.parseInt(ftok.nextToken())));
			sCalificacion.setValue(datos.getCalificacion());
			taCuerpo.setText(datos.getCuerpo());
		}
	}
	
	class agregarOpinion extends Thread
	{
		
		public void run() 
		{
			
			bGuardar.setDisable(true);
			
			if(verificarDatos())
			{
				
				int id = Integer.parseInt(tfLibro.getText());
				String fecha = dpFecha.getValue().toString();
				int calificacion = (int) sCalificacion.getValue();
				String cad = taCuerpo.getText();
				
				Opiniones op = new Opiniones();
				
				op.setId_libro(id);
				op.setFecha(fecha);
				op.setCalificacion(calificacion);
				op.setCuerpo(cad);
				
				try
				{
					ManagerOpiniones mop = new ManagerOpiniones();
					
					if(datos!=null)
					{
						op.setId(datos.getId());
						boolean res = mop.editarOpinion(op);
						
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
						boolean res = mop.addOpinion(op);
						if(res)
						{
							Image img = new Image(getClass().getResource("/img/gifs/agregado.gif").toString());
							imgAgregado.setImage(img);
							imgAgregado.setVisible(true);
							sleep(2000);
							imgAgregado.setVisible(false);
						}
					}
					
				}catch(SQLException ex1)
				{
					bGuardar.setDisable(false);
					System.out.println("Error");
				}catch(InterruptedException ex2)
				{
					bGuardar.setDisable(false);
					ex2.printStackTrace();
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
		
		public boolean verificarDatos()
		{
			
			boolean res = true;
			
			if(tfLibro.getText().isEmpty())
			{
				res = false;
			}
			if(dpFecha.getValue() == null || dpFecha.getValue().toString().equals(""))
			{
				res = false;
			}
			if(taCuerpo.getText().isEmpty())
			{
				res = false;
			}
			try {
				int verif = Integer.parseInt(tfLibro.getText());
			}catch(NumberFormatException ex)
			{
				res = false;
			}
			
			return res;
		}
		
	}
	
}
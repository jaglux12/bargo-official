package Controlador.menuPrincipal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Controlador.tablas.Tabla_Libros;
import Controlador.tablas.Tabla_Opiniones;
import Controlador.tablas.Tabla_ProxLectura;
import Controlador.tooltipAyudas.Ayudas_BarraHerramientas;
import dbQuerys.ManagerFavoritos;
import dbQuerys.ManagerLIbros;
import dbQuerys.ManagerOpiniones;
import dbQuerys.ManagerProxLecturas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import modelosTablas.Libro;
import modelosTablas.Opiniones;
import modelosTablas.Prox_Lecturas;

public class MenuPrincipal implements Initializable
{
	/*
	 * Barra de estado
	 */
	// Labels que muestran la cantidad de registros cargados
	@FXML
	public Label lbCantLibros = new Label();
	
	@FXML
	public Label lbCantOpiniones = new Label();
	
	@FXML
	public Label lbCantProxLecturas = new Label();
	
	/*
	 * Barra de herramientas
	 */
	@FXML
	public Button bNuevoLibro = new Button();
	
	@FXML
	public Button bNuevaOpinion = new Button();

	@FXML
	public Button bBorrar = new Button();
	
	@FXML
	public Button bNuevaProxLectura = new Button();
	
	@FXML
	public Button bNuevoFavorito = new Button();
	
	@FXML
	public ComboBox<String> cbBusquedas = new ComboBox<String>();
	
	/*
	 * Tabla principal
	 */
	
	@FXML
	public TableView<Object> tRegistros = new TableView<Object>();
	
	// Estados de la tabla principal
	
	public String estadoBarra = "";
	
	/*
	 * Menu de herramientas - Eventos
	 */
	
	@FXML
	public Menu mNuevo = new Menu();
	
	public void sobreBargo(ActionEvent e)
	{
		SobreBargo mr = new SobreBargo();
		mr.iniciarVentana();
	}
	
	/*
	 * Barra de herramientas - Eventos
	 */
	
	public void nuevoLibro(ActionEvent e)
	{
		NuevoLibro nl = new NuevoLibro();
		nl.iniciarVentana(null);
	}
	
	public void nuevaOpinion(ActionEvent e)
	{
		NuevaOpinion nop = new NuevaOpinion();
		nop.iniciarVentana(null);
	}
	
	public void nuevaProxLectura(ActionEvent e)
	{
		NuevaProxLectura npl = new NuevaProxLectura();
		npl.iniciarVentana(null);
	}

	public void clic_cambioFiltro(ActionEvent e)
	{
		actualizar(e);
	}
	
	public void nuevoFavorito(ActionEvent e)
	{
		 if(estadoBarra!= "")
		   {
			   switch(estadoBarra)
			   {
			     	case "Libros":
			     		
			     		Libro l = (Libro)tRegistros.getSelectionModel().getSelectedItem();
			     		
			     		ManagerFavoritos ml = new ManagerFavoritos();
			     		
			     		bNuevoFavorito.setDisable(true);
			     		try {
			     			
			     			boolean res = ml.addFavorito(l.getId());
			     		
			     			if(res)
			     			{
			     				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			     				alerta.setTitle("Registro agregado");
			     				alerta.setHeaderText("Cambios en la tabla: FAVORITOS");
			     				alerta.setContentText("Registro agregado como favorito");
			     				alerta.showAndWait();
			     			}
			     			
			     		}catch(SQLException ex)
			     		{
			     			ex.printStackTrace();
			     		}
			     		bNuevoFavorito.setDisable(false);
			     	break;
			   }
		   }
	}
	
	public void actualizar(ActionEvent e)
	{
		String opcion = cbBusquedas.getValue();
		
		if(opcion != null)
		{
			switch(opcion)
			{
				
				case "Libros":
					
					Tabla_Libros tLibros = new Tabla_Libros();
					
					try
					{
						tLibros.actualizarTabla_Libros(tRegistros);
					}
					catch(SQLException ex)
					{
						System.out.println("Error buscando registros");
					}
					estadoBarra = "Libros";
					
				break;
				
				case "Opiniones":
					
					Tabla_Opiniones tOpiniones = new Tabla_Opiniones();
					
					try
					{
						tOpiniones.actualizarTabla_Libros(tRegistros);
					}
					catch(SQLException ex)
					{
						System.out.println("Error buscando registros");
					}
					estadoBarra = "Opiniones";
					
				break;
				
				case "Próximas Lecturas":
					
					Tabla_ProxLectura tProxLecturas = new Tabla_ProxLectura();
					
					try
					{
						tProxLecturas.actualizarTabla_Libros(tRegistros);
					}
					catch(SQLException ex)
					{
						System.out.println("Error buscando registros");
					}
					estadoBarra = "Próximas Lecturas";
					
				break;
			
			}
		}
		
	}
	
	public void agregar_generos(ActionEvent e)
	{
		MenuGeneros mg = new MenuGeneros();
		mg.iniciarVentana();
	}
	
	public void nuevaEditorial()
	{
		MenuEditoriales mr = new MenuEditoriales();
		mr.iniciarVentana();
	}
	
	public void nuevoFormato()
	{
		MenuFormatos mf = new MenuFormatos();
		mf.iniciarVentana();
	}
	
	public void borraRegistro(ActionEvent e)
	{
	   if(estadoBarra != "" && tRegistros.getSelectionModel().getSelectedItem() != null)
	   {
		   switch(estadoBarra)
		   {
		     	case "Libros":
		     		
		     		Libro l = (Libro)tRegistros.getSelectionModel().getSelectedItem();
		     		
		     		ManagerLIbros ml = new ManagerLIbros();
		     		
		     		bBorrar.setDisable(true);
		     		try {
		     			
		     			boolean res = ml.elimRegistro(l.getId());
		     		
		     			if(res)
		     			{
		     				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		     				alerta.setTitle("Registro borrado");
		     				alerta.setHeaderText("Cambios");
		     				alerta.setContentText("<! Registro borrado correctamente");
		     				alerta.showAndWait();
		     			}
		     			
		     		}catch(SQLException ex)
		     		{
		     			ex.printStackTrace();
		     		}
		     		actualizar(null);
		     		bBorrar.setDisable(false);
		     	break;
		     	
		     	case "Opiniones":
		     		
		     		Opiniones op = (Opiniones)tRegistros.getSelectionModel().getSelectedItem();
		     		
		     		ManagerOpiniones mo = new ManagerOpiniones();
		     		
		     		bBorrar.setDisable(true);
		     		try {
		     			
		     			boolean res = mo.elimRegistro(op.getId());
		     		
		     			if(res)
		     			{
		     				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		     				alerta.setTitle("Registro borrado");
		     				alerta.setHeaderText("Cambios");
		     				alerta.setContentText("<! Registro borrado correctamente");
		     				alerta.showAndWait();
		     			}
		     			
		     		}catch(SQLException ex)
		     		{
		     			ex.printStackTrace();
		     		}
		     		actualizar(null);
		     		bBorrar.setDisable(false);
		     		
		     	break;
		     	case "Próximas Lecturas":
		     		
		     		Prox_Lecturas pl = (Prox_Lecturas)tRegistros.getSelectionModel().getSelectedItem();
		     		
		     		ManagerProxLecturas mpl = new ManagerProxLecturas();
		     		
		     		bBorrar.setDisable(true);
		     		try {
		     			
		     			boolean res = mpl.elimRegistro(pl.getId());
		     		
		     			if(res)
		     			{
		     				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		     				alerta.setTitle("Registro borrado");
		     				alerta.setHeaderText("Cambios");
		     				alerta.setContentText("<! Registro borrado correctamente");
		     				alerta.showAndWait();
		     			}
		     			
		     		}catch(SQLException ex)
		     		{
		     			ex.printStackTrace();
		     		}
		     		actualizar(null);
		     		bBorrar.setDisable(false);
		     		
		     	break;
		   }
	   }
	}
	
	public void click_menuInformes(ActionEvent e)
	{
		new MenuInformes().iniciarVentana();
	}
	
	public void verFavoritos(ActionEvent e)
	{
		MenuFavoritos fl = new MenuFavoritos();
		fl.iniciarVentana();
	}
	
	public void editaRegistro(ActionEvent e)
	{
		 if(estadoBarra!= "")
		   {
			   switch(estadoBarra)
			   {
			     	case "Libros":
			     		
			     		NuevoLibro nl = new NuevoLibro();
			     		Libro libro = (Libro)tRegistros.getSelectionModel().getSelectedItem();

			     		nl.iniciarVentana(libro);
			     		
			     		actualizar(null);
			     	break;
			     	
			     	case "Opiniones":
			     		
			     		NuevaOpinion mo = new NuevaOpinion();
			     		Opiniones op = (Opiniones)tRegistros.getSelectionModel().getSelectedItem();
			     		
			     		mo.iniciarVentana(op);
			     		
			     		actualizar(null);
			     	break;
			     	case "Próximas Lecturas":
			     		
			     		NuevaProxLectura nuevaProx = new NuevaProxLectura();
			     		Prox_Lecturas pl = (Prox_Lecturas)tRegistros.getSelectionModel().getSelectedItem();
			     		nuevaProx.iniciarVentana(pl);
			     		
			     		actualizar(null);
			     		
			     	break;
			   }
		   }
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		/*
		 * Tooltip text para los botones
		 */
		
		Ayudas_BarraHerramientas ayudasB = new Ayudas_BarraHerramientas();
		
		/*
		 * Cantidad de registros
		 */
		_confCantRegistros();
		
		/*
		 * Opciones de filtro de búsqueda
		 */
		cbBusquedas.getItems().add("Libros");
		cbBusquedas.getItems().add("Opiniones");
		cbBusquedas.getItems().add("Próximas Lecturas");
		
		/*
		 * Mensajes de ayuda
		 */
		bNuevoLibro.setTooltip(ayudasB.gettTNuevoLibro());
		bNuevaOpinion.setTooltip(ayudasB.gettTOpinion());
		
	}
	
	/*
	 * Cree estos metodos para simplificar las acciones en los demas metodos
	 * mas importantes que hacen el trabajo principal. 
	 */
	
	private void _confCantRegistros()
	{
		ManagerLIbros ml = new ManagerLIbros();
		ManagerOpiniones mo = new ManagerOpiniones();
		ManagerProxLecturas mpl = new ManagerProxLecturas();
		
		try
		{
				
			int cant_Libros = ml.getCantLibros();
			int cant_Opiniones = mo.getCountOpiniones();
			int cant_ProxLecturas = mpl.getCountProxLecturas();
			
			/*
			 * Agregamos los valores obtenidos
			 */
			lbCantLibros.setText(cant_Libros+"");
			lbCantOpiniones.setText(cant_Opiniones+"");
			lbCantProxLecturas.setText(cant_ProxLecturas+"");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}	
	
}
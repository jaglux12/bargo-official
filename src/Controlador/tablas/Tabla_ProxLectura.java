package Controlador.tablas;

import java.sql.SQLException;
import dbQuerys.ManagerProxLecturas;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelosTablas.Prox_Lecturas;

public class Tabla_ProxLectura 
{

	/*
	 * Columnas - Libros
	 */
	
	public TableColumn<Object,String> cID = new TableColumn<>();
	public TableColumn<Object,String> cNombre = new TableColumn<>();
	public TableColumn<Object,String> cAutor = new TableColumn<>();
	public TableColumn<Object,String> cEditorial = new TableColumn<>();
	
	
	public Tabla_ProxLectura() 
	{
		//Cargamos las propiedades y agregamos los campos
		cID.setCellValueFactory(new PropertyValueFactory<>("id"));
		cID.setText("ID");
		cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_libro"));
		cNombre.setText("Nombre");
		cAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		cAutor.setText("Autor");
		cEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		cEditorial.setText("Editorial");
	}
	
	
	public void actualizarTabla_Libros(TableView<Object> t) throws SQLException
	{
		t.getItems().clear();
		t.getColumns().clear();
		
		_agregarColumnas(t);
		
		ManagerProxLecturas mp = new ManagerProxLecturas();
			
		Prox_Lecturas[] lista = mp.getProx_Lecturas();
			
		for(int i = 0; i < lista.length; i++)
		{
			t.getItems().add(lista[i]);
		}
		
	}
	
	private void _agregarColumnas(TableView<Object> t)
	{
		t.getColumns().add(cID);
		t.getColumns().add(cNombre);
		t.getColumns().add(cAutor);
		t.getColumns().add(cEditorial);
	}
	
}

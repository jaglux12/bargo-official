package Controlador.tablas;

import java.sql.SQLException;

import dbQuerys.ManagerLIbros;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelosTablas.Libro;

public class Tabla_Libros 
{

	/*
	 * Columnas - Libros
	 */
	
	public TableColumn<Object,String> cID = new TableColumn<>();
	public TableColumn<Object,String> cNombre = new TableColumn<>();
	public TableColumn<Object,String> cAutor = new TableColumn<>();
	public TableColumn<Object,String> cGenero = new TableColumn<>();
	public TableColumn<Object,String> cEditorial = new TableColumn<>();
	public TableColumn<Object,String> cFormato = new TableColumn<>();
	public TableColumn<Object,String> cN_pag= new TableColumn<>();
	public TableColumn<Object,String> cCantidad = new TableColumn<>();
	
	
	public Tabla_Libros() 
	{
		//Cargamos las propiedades y agregamos los campos
		cID.setCellValueFactory(new PropertyValueFactory<>("id"));
		cID.setText("ID");
		cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		cNombre.setText("Nombre");
		cAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		cAutor.setText("Autor");
		cGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		cGenero.setText("Genero");
		cEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		cEditorial.setText("Editorial");
		cFormato.setCellValueFactory(new PropertyValueFactory<>("formato"));
		cFormato.setText("Formato");
		cN_pag.setCellValueFactory(new PropertyValueFactory<>("n_pag"));
		cN_pag.setText("Cant. Páginas");
		cCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		cCantidad.setText("Cantidad");
	}
	
	
	public void actualizarTabla_Libros(TableView<Object> t) throws SQLException
	{
		t.getItems().clear();
		t.getColumns().clear();
		
		_agregarColumnas(t);
		
		ManagerLIbros mo = new ManagerLIbros();
			
		Libro[] lista = mo.getLibros();
			
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
		t.getColumns().add(cGenero);
		t.getColumns().add(cEditorial);
		t.getColumns().add(cFormato);
		t.getColumns().add(cN_pag);
		t.getColumns().add(cCantidad);
	}
	
}

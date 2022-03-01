package Controlador.tablas;

import java.sql.SQLException;
import dbQuerys.ManagerOpiniones;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelosTablas.Opiniones;

public class Tabla_Opiniones 
{
	
	public TableColumn<Object, String> cID = new TableColumn<>();
	public TableColumn<Object, String> cLIbro_id = new TableColumn<>();
	public TableColumn<Object, String> cFecha = new TableColumn<>();
	public TableColumn<Object, String> cCalif = new TableColumn<>();
	public TableColumn<Object, String> cCuerpo = new TableColumn<>();
	
	
	public Tabla_Opiniones() 
	{
		cID.setCellValueFactory(new PropertyValueFactory<>("id"));
		cID.setText("ID");
		cLIbro_id.setCellValueFactory(new PropertyValueFactory<>("id_libro"));
		cLIbro_id.setText("Nombre");
		cFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		cFecha.setText("Autor");
		cCalif.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
		cCalif.setText("Calificación");
		cCuerpo.setCellValueFactory(new PropertyValueFactory<>("cuerpo"));
		cCuerpo.setText("Cuerpo");
	}
	
	public void actualizarTabla_Libros(TableView<Object> t) throws SQLException
	{
		t.getItems().clear();
		t.getColumns().clear();
		
		_agregarColumnas(t);
		
		ManagerOpiniones mo = new ManagerOpiniones();
		
		Opiniones[] lista = mo.getOpiniones();
		
		for(int i = 0; i < lista.length; i++)
		{
			t.getItems().add(lista[i]);
		}
		
	}
	
	private void _agregarColumnas(TableView<Object> t)
	{
		t.getColumns().add(cID);
		t.getColumns().add(cLIbro_id);
		t.getColumns().add(cFecha);
		t.getColumns().add(cCalif);
		t.getColumns().add(cCuerpo);
	}
	
}

package Controlador.itemsOpciones;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class Items_menuConfiguracion
{
	
	/*
	 * Item root
	 */
	private TreeItem<String> root;
	/*
	 * Cabeceras - Apariencia
	 */
	private TreeItem<String> itemApariencia;
	private TreeItem<String> itemBaseDatos;
	
	/*
	 * Opciones - Apariencia
	 */
	private TreeItem<String> opcionColores;
	private TreeItem<String> opcionTemas;
	
	public Items_menuConfiguracion() 
	{
		root = new TreeItem<String>();
		
		_config_ItemApariencia();
		/*  ->  */ 	_config_opcionColores();
		/* ->  */  _config_opcionTemas();
		/*  -><-   */ _config_Apariencia();
		_config_ItemBaseDatos();
		
		root.getChildren().add(itemApariencia);
		root.getChildren().add(itemBaseDatos);
	}
	
	public TreeItem<String> getItemRoot()
	{
		return root;
	}
	
	private void _config_ItemApariencia()
	{
		itemApariencia = new TreeItem<String>("Apariencia");
		ImageView img =  new ImageView(getClass().getResource("/img/menuConfiguracion/apariencia.png").toString());
		img.setFitWidth(20);
		img.setFitHeight(20);
		itemApariencia.setGraphic(img);;
	}
	
	private void _config_ItemBaseDatos()
	{
		itemBaseDatos = new TreeItem<String>("Base de datos");
		ImageView img =  new ImageView(getClass().getResource("/img/menuConfiguracion/base_datos.png").toString());
		img.setFitWidth(20);
		img.setFitHeight(20);
		itemBaseDatos.setGraphic(img);;
	}
	
	private void _config_opcionColores()
	{
		opcionColores = new TreeItem<String>("Colores");
		ImageView img =  new ImageView(getClass().getResource("/img/menuConfiguracion/colores.png").toString());
		img.setFitWidth(20);
		img.setFitHeight(20);
		opcionColores.setGraphic(img);
	}
	private void _config_opcionTemas()
	{
		opcionTemas = new TreeItem<String>("Temas");
		ImageView img =  new ImageView(getClass().getResource("/img/menuConfiguracion/temas.png").toString());
		img.setFitWidth(20);
		img.setFitHeight(20);
		opcionTemas.setGraphic(img);
	}
	
	private void _config_Apariencia()
	{
		itemApariencia.getChildren().add(opcionColores);
		itemApariencia.getChildren().add(opcionTemas);
	}
	
	/*
	 * Getters y setters
	 */
	
	public TreeItem<String> getItemApariencia() {
		return itemApariencia;
	}

	public void setItemApariencia(TreeItem<String> itemApariencia) {
		this.itemApariencia = itemApariencia;
	}

	public TreeItem<String> getRoot() {
		return root;
	}

	public void setRoot(TreeItem<String> root) {
		this.root = root;
	}

	public TreeItem<String> getItemBaseDatos() {
		return itemBaseDatos;
	}

	public void setItemBaseDatos(TreeItem<String> itemBaseDatos) {
		this.itemBaseDatos = itemBaseDatos;
	}

	public TreeItem<String> getOpcionColores() {
		return opcionColores;
	}

	public void setOpcionColores(TreeItem<String> opcionColores) {
		this.opcionColores = opcionColores;
	}

	public TreeItem<String> getOpcionTemas() {
		return opcionTemas;
	}

	public void setOpcionTemas(TreeItem<String> opcionTemas) {
		this.opcionTemas = opcionTemas;
	}
	
	
}
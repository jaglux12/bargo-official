package Controlador.tooltipAyudas;

import javafx.scene.control.Tooltip;

public class Ayudas_BarraHerramientas 
{
	
	/*
	 * Mensajes de ayuda
	 */
	private Tooltip tTNuevoLibro;	
	private Tooltip tTOpinion; 
	private Tooltip tTNuevaProxLectura;
	private Tooltip tBorrar;
	private Tooltip tEditar;
	private Tooltip tNuevoFavorito;
	private Tooltip tVerFavoritos;
	private Tooltip tInformes;
	
	public Ayudas_BarraHerramientas() 
	{
		tTNuevoLibro = new Tooltip("Agregar un nuevo libro");
		tTOpinion = new Tooltip("Agregar una nueva opinión");
		tTNuevaProxLectura = new Tooltip("Agregar una lectura pendiente");
		tBorrar = new Tooltip("Borrar registro seleccionado");
		tEditar = new Tooltip("Editar registro seleccionado");
		tNuevoFavorito = new Tooltip("Agregar como favorito");
		tVerFavoritos = new Tooltip("Ver favoritos");
		tInformes = new Tooltip("Ver Informes");
	}


	public Tooltip gettTNuevoLibro() 
	{
		return tTNuevoLibro;
	}

	public Tooltip gettTOpinion() 
	{
		return tTOpinion;
	}


	public Tooltip gettTNuevaProxLectura() {
		return tTNuevaProxLectura;
	}


	public Tooltip gettBorrar() {
		return tBorrar;
	}


	public Tooltip gettEditar() {
		return tEditar;
	}


	public Tooltip gettNuevoFavorito() {
		return tNuevoFavorito;
	}


	public Tooltip gettVerFavoritos() {
		return tVerFavoritos;
	}
	
	public Tooltip gettInformes() {
		return tInformes;
	}
	
		
}
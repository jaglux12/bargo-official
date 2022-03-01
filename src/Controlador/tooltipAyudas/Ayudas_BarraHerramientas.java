package Controlador.tooltipAyudas;

import javafx.scene.control.Tooltip;

public class Ayudas_BarraHerramientas 
{
	
	/*
	 * Mensajes de ayuda
	 */
	private Tooltip tTNuevoLibro;	
	private Tooltip tTOpinion; 
	
	
	public Ayudas_BarraHerramientas() 
	{
		tTNuevoLibro = new Tooltip("Agregar un nuevo libro");
		tTOpinion = new Tooltip("Agregar una nueva opinión");
	}


	public Tooltip gettTNuevoLibro() 
	{
		return tTNuevoLibro;
	}

	public Tooltip gettTOpinion() 
	{
		return tTOpinion;
	}
		
}
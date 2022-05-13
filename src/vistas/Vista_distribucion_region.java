package vistas;

public class Vista_distribucion_region {
	private int idDistribucion;
	private String nombre;
	private String descripcion;
	private String nombre_region;
	
	
	public Vista_distribucion_region() {
		super();
	}


	public Vista_distribucion_region(int idDistribucion, String nombre, String descripcion, String nombre_region) {
		super();
		this.idDistribucion = idDistribucion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nombre_region = nombre_region;
	}


	public int getIdDistribucion() {
		return idDistribucion;
	}


	public void setIdDistribucion(int idDistribucion) {
		this.idDistribucion = idDistribucion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getNombre_region() {
		return nombre_region;
	}


	public void setNombre_region(String nombre_region) {
		this.nombre_region = nombre_region;
	}
	
	

}

package vistas;

public class Vista_arbol {
	private int id;
	private String nombreComun;
	private String nombreCientifico;
	private String descripcion;
	private String foto;
	private String nombre_Genero;
	private String nombre_Familia;
	private String nombre_Flor;
	private String nombre_Distribucion;
	
	
	public Vista_arbol() {
		super();
	}


	public Vista_arbol(int id, String nombreComun, String nombreCientifico, String descripcion, String foto, String nombre_Genero,
			String nombre_Familia, String nombre_Flor, String nombre_Distribucion) {
		super();
		this.id = id;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.descripcion = descripcion;
		this.foto = foto;
		this.nombre_Genero = nombre_Genero;
		this.nombre_Familia = nombre_Familia;
		this.nombre_Flor = nombre_Flor;
		this.nombre_Distribucion = nombre_Distribucion;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombreComun() {
		return nombreComun;
	}


	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}


	public String getNombreCientifico() {
		return nombreCientifico;
	}


	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getNombre_Genero() {
		return nombre_Genero;
	}


	public void setNombre_Genero(String nombre_Genero) {
		this.nombre_Genero = nombre_Genero;
	}


	public String getNombre_Familia() {
		return nombre_Familia;
	}


	public void setNombre_Familia(String nombre_Familia) {
		this.nombre_Familia = nombre_Familia;
	}


	public String getNombre_Flor() {
		return nombre_Flor;
	}


	public void setNombre_Flor(String nombre_Flor) {
		this.nombre_Flor = nombre_Flor;
	}


	public String getNombre_Distribucion() {
		return nombre_Distribucion;
	}


	public void setNombre_Distribucion(String nombre_Distribucion) {
		this.nombre_Distribucion = nombre_Distribucion;
	}
	
	
	
	

}

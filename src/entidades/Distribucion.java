package entidades;

public class Distribucion {
	private int idDistribucion;
	private String nombre;
	private String descripcion;
	private int estado;
	private int idRegion;
	
	public Distribucion() {
		super();
	}

	
	public Distribucion(int idDistribucion, String nombre, String descripcion, int estado, int idRegion) {
		super();
		this.idDistribucion = idDistribucion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.idRegion = idRegion;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	
	
	
}

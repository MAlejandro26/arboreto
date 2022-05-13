package entidades;

public class Opciones {
	private int idOPciones;
	private String nombre;
	private String descripcion;
	private int estado;
	
	public Opciones() {
		super();
	}

	public Opciones(int idOPciones, String nombre, String descripcion, int estado) {
		super();
		this.idOPciones = idOPciones;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public int getIdOPciones() {
		return idOPciones;
	}

	public void setIdOPciones(int idOPciones) {
		this.idOPciones = idOPciones;
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
	
	
}

package entidades;

public class Familiar {
	private int idFamilia;
	private String nombre;
	private String descripcion;
	private int estado;
	public Familiar() {
		super();
	}
	public Familiar(int idFamilia, String nombre, String descripcion, int estado) {
		super();
		this.idFamilia = idFamilia;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	public int getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(int idFamilia) {
		this.idFamilia = idFamilia;
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

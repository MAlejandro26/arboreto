package entidades;

public class Region {
	private int idRegion;
	private String nombre;
	private String descripcion;
	private int idPais;
	private int estado;
	public Region() {
		super();
	}
	public Region(int idRegion, String nombre, String descripcion, int idPais, int estado) {
		super();
		this.idRegion = idRegion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.idPais = idPais;
		this.estado = estado;
	}
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
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
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

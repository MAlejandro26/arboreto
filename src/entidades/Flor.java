package entidades;

public class Flor {
	private int idFlor;
	private String nombreComun;
	private String nombreCientifico;
	private String descripcion;
	private String temporadaFloracion;
	private int estado;
	public Flor() {
		super();
	}
	public Flor(int idFlor, String nombreComun, String nombreCientifico, String descripcion,
			String temporadaFloracion, int estado) {
		super();
		this.idFlor = idFlor;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.descripcion = descripcion;
		this.temporadaFloracion = temporadaFloracion;
		this.estado = estado;
	}
	public int getIdFlor() {
		return idFlor;
	}
	public void setIdFlor(int idFlor) {
		this.idFlor = idFlor;
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
	public String getTemporadaFloracion() {
		return temporadaFloracion;
	}
	public void setTemporadaFloracion(String temporadaFloracion) {
		this.temporadaFloracion = temporadaFloracion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}

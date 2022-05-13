package entidades;

public class Eventos {
	private int idEvento;
	private String nombre;
	private String descripcion;
	private String fechaInicio;
	private String fechaFin;
	private String tipoEvento;
	private String ubicacion;
	private String hipervinculo;
	private int estado;
	public Eventos() {
		super();
	}
	
	public Eventos(int idEvento, String nombre, String descripcion, String fechaInicio, String fechaFin,
			String tipoEvento, String ubicacion, String hipervinculo, int estado) {
		super();
		this.idEvento = idEvento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoEvento = tipoEvento;
		this.ubicacion = ubicacion;
		this.hipervinculo = hipervinculo;
		this.estado = estado;
	}

	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
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
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getHipervinculo() {
		return hipervinculo;
	}
	public void setHipervinculo(String hipervinculo) {
		this.hipervinculo = hipervinculo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}

package entidades;

public class Publicacion {
	private int idPublicacion;
	private String titulo;
	private String descripcion;
	private String multimedia;
	private String hipervinculo;
	private String fechaPublicacion;
	private int estado;
	public Publicacion() {
		super();
	}
	
	public Publicacion(int idPublicacion, String titulo, String descripcion, String multimedia, String hipervinculo,
			String fechaPublicacion, int estado) {
		super();
		this.idPublicacion = idPublicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.multimedia = multimedia;
		this.hipervinculo = hipervinculo;
		this.fechaPublicacion = fechaPublicacion;
		this.estado = estado;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getMultimedia() {
		return multimedia;
	}
	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}
	
	public String getHipervinculo() {
		return hipervinculo;
	}

	public void setHipervinculo(String hipervinculo) {
		this.hipervinculo = hipervinculo;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

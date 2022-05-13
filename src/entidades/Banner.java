package entidades;

public class Banner {
	private int idBanner;
	private String titulo;
	private String descripcion;
	private String foto;
	private int posicion;
	private int estado;
	public Banner() {
		super();
	}
	public Banner(int idBanner, String titulo, String descripcion, String foto, int posicion, int estado) {
		super();
		this.idBanner = idBanner;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.foto = foto;
		this.posicion = posicion;
		this.estado = estado;
	}
	public int getIdBanner() {
		return idBanner;
	}
	public void setIdBanner(int idBanner) {
		this.idBanner = idBanner;
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}

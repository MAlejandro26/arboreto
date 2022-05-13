package entidades;

public class PiePagina {
	private int idPiePagina;
	private String direccion;
	private String telefono;
	private String ext;
	private String email;
	public PiePagina() {
		super();
	}
	public PiePagina(int idPiePagina, String direccion, String telefono, String ext, String email) {
		super();
		this.idPiePagina = idPiePagina;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ext = ext;
		this.email = email;
	}
	public int getIdPiePagina() {
		return idPiePagina;
	}
	public void setIdPiePagina(int idPiePagina) {
		this.idPiePagina = idPiePagina;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

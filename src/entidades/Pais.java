package entidades;

public class Pais {
	private int idPais;
	private String nombre;
	private int estado;
	
	public Pais() {
		super();
	}
	

	public Pais(int idPais, String nombre, int estado) {
		super();
		this.idPais = idPais;
		this.nombre = nombre;
		this.estado = estado;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}

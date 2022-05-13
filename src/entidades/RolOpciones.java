package entidades;

public class RolOpciones {
	private int idRolOpciones;
	private String descripcion;
	private int idRol;
	private int idOpciones;
	
	public RolOpciones() {
		super();
	}

	public RolOpciones(int idRolOpciones, String descripcion, int idRol, int idOpciones) {
		super();
		this.idRolOpciones = idRolOpciones;
		this.descripcion = descripcion;
		this.idRol = idRol;
		this.idOpciones = idOpciones;
	}

	public int getIdRolOpciones() {
		return idRolOpciones;
	}

	public void setIdRolOpciones(int idRolOpciones) {
		this.idRolOpciones = idRolOpciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdOpciones() {
		return idOpciones;
	}

	public void setIdOpciones(int idOpciones) {
		this.idOpciones = idOpciones;
	}

	
}

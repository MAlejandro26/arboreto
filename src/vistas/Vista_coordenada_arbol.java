package vistas;

public class Vista_coordenada_arbol {
	
	private int idCoordenadaArbol;
	private int idarbol;
	private String nombreComun;
	private String nombreCientifico;
	private double latitud;
	private double longitud;
	private String foto;
	
	public Vista_coordenada_arbol() {
		super();
	}

	public Vista_coordenada_arbol(int idCoordenadaArbol, int idarbol, String nombreComun, String nombreCientifico, double latitud,
			double longitud, String foto) {
		super();
		this.idCoordenadaArbol = idCoordenadaArbol;
		this.idarbol = idarbol;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.latitud = latitud;
		this.longitud = longitud;
		this.foto = foto;
	}

	public int getIdCoordenadaArbol() {
		return idCoordenadaArbol;
	}

	public void setIdCoordenadaArbol(int idCoordenadaArbol) {
		this.idCoordenadaArbol = idCoordenadaArbol;
	}
	
	public int getIdarbol() {
		return idarbol;
	}

	public void setIdarbol(int idarbol) {
		this.idarbol = idarbol;
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

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}

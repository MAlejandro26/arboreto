package entidades;

public class CoordenadaArbol {
	private int idCoordenadaArbol;
	private double latitud;
	private double longitud;
	private int idArbol;
	public CoordenadaArbol() {
		super();
	}
	public CoordenadaArbol(int idCoordenadaArbol, double latitud, double longitud, int idArbol) {
		super();
		this.idCoordenadaArbol = idCoordenadaArbol;
		this.latitud = latitud;
		this.longitud = longitud;
		this.idArbol = idArbol;
	}
	public int getIdCoordenadaArbol() {
		return idCoordenadaArbol;
	}
	public void setIdCoordenadaArbol(int idCoordenadaArbol) {
		this.idCoordenadaArbol = idCoordenadaArbol;
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
	public int getIdArbol() {
		return idArbol;
	}
	public void setIdArbol(int idArbol) {
		this.idArbol = idArbol;
	}
	
	
}

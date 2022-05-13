package entidades;

public class Inicio {
	private int idInicio;
	private String historia;
	private String fotoHistoria;
	private String mision;
	private String fotoMision;
	private String vision;
	private String fotoVision;
	private int estado;
	public Inicio() {
		super();
	}
	public Inicio(int idInicio, String historia, String fotoHistoria, String mision, String fotoMision, String vision,
			String fotoVision, int estado) {
		super();
		this.idInicio = idInicio;
		this.historia = historia;
		this.fotoHistoria = fotoHistoria;
		this.mision = mision;
		this.fotoMision = fotoMision;
		this.vision = vision;
		this.fotoVision = fotoVision;
		this.estado = estado;
	}
	public int getIdInicio() {
		return idInicio;
	}
	public void setIdInicio(int idInicio) {
		this.idInicio = idInicio;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public String getFotoHistoria() {
		return fotoHistoria;
	}
	public void setFotoHistoria(String fotoHistoria) {
		this.fotoHistoria = fotoHistoria;
	}
	public String getMision() {
		return mision;
	}
	public void setMision(String mision) {
		this.mision = mision;
	}
	public String getFotoMision() {
		return fotoMision;
	}
	public void setFotoMision(String fotoMision) {
		this.fotoMision = fotoMision;
	}
	public String getVision() {
		return vision;
	}
	public void setVision(String vision) {
		this.vision = vision;
	}
	public String getFotoVision() {
		return fotoVision;
	}
	public void setFotoVision(String fotoVision) {
		this.fotoVision = fotoVision;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

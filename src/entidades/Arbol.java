package entidades;

public class Arbol {
	private int id;
	private String nombreComun;
	private String nombreCientifico;
	private String descripcion;
	private int idGenero;
	private int idFamilia;
	private int idFlor;
	private int idDistribucion;
	private String img;
	
	public Arbol() {
		super();
	}

	public Arbol(int id, String nombreComun, String nombreCientifico, String descripcion, int idGenero, int idFamilia,
			int idFlor, int idDistribucion, String img) {
		super();
		this.id = id;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.descripcion = descripcion;
		this.idGenero = idGenero;
		this.idFamilia = idFamilia;
		this.idFlor = idFlor;
		this.idDistribucion = idDistribucion;
		this.img = img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public int getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(int idFamilia) {
		this.idFamilia = idFamilia;
	}

	public int getIdFlor() {
		return idFlor;
	}

	public void setIdFlor(int idFlor) {
		this.idFlor = idFlor;
	}

	public int getIdDistribucion() {
		return idDistribucion;
	}

	public void setIdDistribucion(int idDistribucion) {
		this.idDistribucion = idDistribucion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}

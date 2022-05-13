package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import entidades.*;

public class DTArbol {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsArbol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarArbol(Connection c) {
		try {
			ps = c.prepareStatement("select * from arbol where idArbol <> 3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsArbol = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("Error en listar arbol "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Arbol> listarArbol(){
		ArrayList<Arbol> listaArbol = new ArrayList<Arbol>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.arbol", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Arbol arbol = new Arbol();
				arbol.setId(rs.getInt("idArbol")); 
				arbol.setNombreComun(rs.getString("nombreComun"));
				arbol.setNombreCientifico(rs.getString("nombreCientifico"));
				arbol.setDescripcion(rs.getString("descripcion"));
				arbol.setIdFamilia(rs.getInt("idFamilia"));
				arbol.setIdGenero(rs.getInt("idGenero"));
				arbol.setIdDistribucion(rs.getInt("idDistribucion"));
				arbol.setIdFlor(rs.getInt("idFlor"));
				arbol.setImg("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				//System.out.println(rs.getString("img"));
				listaArbol.add(arbol);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA CARRERA "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listaArbol;
	}
	
	public boolean guardarArbol(Arbol ar, InputStream fi) {
		boolean resp = false;
		//PreparedStatement ps;
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("INSERT INTO arbol(nombreComun, nombreCientifico, descripcion, idFamilia, idGenero, idDistribucion, idFlor, foto) "
									+ "VALUES(?,?,?,?,?,?,?,?)");
			
			ps.setString(1, ar.getNombreComun());
			ps.setString(2, ar.getNombreCientifico());
			ps.setString(3, ar.getDescripcion());
			ps.setInt(4, ar.getIdFamilia());
			ps.setInt(5, ar.getIdGenero());
			ps.setInt(6, ar.getIdDistribucion());
			ps.setInt(7, ar.getIdFlor());
			ps.setBinaryStream(8, fi);
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				resp = true;
			}else {
				resp = false;
			}
			
						
		}	catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return resp;

	}
	
	public boolean eliminarArbol(int id) {
		boolean eliminado = false;
		
		
		try {
			c = PoolConexion.getConnection();
			this.llenarArbol(c);
			rsArbol.beforeFirst();
			while (rsArbol.next())
			{
				if(rsArbol.getInt(1)==id)
				{
					rsArbol.deleteRow();
					eliminado=true;
				}
			}
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rsArbol.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return eliminado;
	}
	
	public Arbol buscarArbol(int id) {
		Arbol arbol = new Arbol();
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.arbol where idArbol = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				arbol.setId(rs.getInt("idArbol")); 
				arbol.setNombreComun(rs.getString("nombreComun"));
				arbol.setNombreCientifico(rs.getString("nombreCientifico"));
				arbol.setDescripcion(rs.getString("descripcion"));
				arbol.setIdFamilia(rs.getInt("idFamilia"));
				arbol.setIdGenero(rs.getInt("idGenero"));
				arbol.setIdDistribucion(rs.getInt("idDistribucion"));
				arbol.setIdFlor(rs.getInt("idFlor"));
				arbol.setImg("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
			}
			
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA CARRERA "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return arbol;
	}
	
	public DetalleArbol buscarDetalleArbol(int id) {
		DetalleArbol arbol = new DetalleArbol();
		
		String consulta = "SELECT a.idarbol,\r\n"
				+ "    a.nombrecomun,\r\n"
				+ "    a.nombrecientifico,\r\n"
				+ "    a.descripcion,\r\n"
				+ "    a.foto,\r\n"
				+ "    g.nombre AS nombreGenero,\r\n"
				+ "	g.descripcion AS descripcionGenero,\r\n"
				+ "    f.nombre AS nombrefamilia,\r\n"
				+ "	f.descripcion AS descripcionFamilia,\r\n"
				+ "    fl.nombrecomun AS nombreflor,\r\n"
				+ "	fl.nombrecientifico AS nombrecientificoFlor,\r\n"
				+ "	fl.descripcion AS descripcionFlor,\r\n"
				+ "	fl.temporadafloracion AS temporadaFloracion,\r\n"
				+ "    d.nombre AS nombreDistribucion,\r\n"
				+ "	d.descripcion AS descripcionDistri,\r\n"
				+ "	r.nombre as nombreRegion,\r\n"
				+ "	r.descripcion as descripcionReg,\r\n"
				+ "	p.nombre as pais\r\n"
				+ "   FROM ((((arbol a\r\n"
				+ "     JOIN genero g ON ((a.idgenero = g.idgenero)))\r\n"
				+ "     JOIN familia f ON ((a.idfamilia = f.idfamilia)))\r\n"
				+ "     JOIN flor fl ON ((a.idflor = fl.idflor)))\r\n"
				+ "     JOIN distribucion d ON ((a.iddistribucion = d.iddistribucion))\r\n"
				+ "	JOIN region r on((d.idregion = r.idregion))\r\n"
				+ "		JOIN pais p on ((r.idpais = p.idpais)))\r\n"
				+ "	 Where a.idarbol="+id;
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				arbol.setIdArbol(rs.getInt("idarbol")); 
				arbol.setNombreComunArbol(rs.getString("nombrecomun"));
				arbol.setNombreCientificoArbol(rs.getString("nombrecientifico"));
				arbol.setDescripcionArbol(rs.getString("descripcion"));
				arbol.setImgArbol("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				arbol.setNombreGenero(rs.getString("nombreGenero"));
				arbol.setDescripcionGenero(rs.getString("descripcionGenero"));
				arbol.setNombreFamilia(rs.getString("nombreFamilia"));
				arbol.setDescripcionFamilia(rs.getString("descripcionFamilia"));
				arbol.setNombreComunFlor(rs.getString("nombreflor"));
				arbol.setNombreCientificoFlor(rs.getString("nombrecientificoflor"));
				arbol.setDescripcionFlor(rs.getString("descripcionFlor"));
				arbol.setTemporadaFloracion(rs.getString("temporadaFloracion"));
				arbol.setNombreDistribucion(rs.getString("nombreDistribucion"));
				arbol.setDescripcionDistribucion(rs.getString("descripcionDistri"));
				arbol.setNombreRegion(rs.getString("nombreRegion"));
				arbol.setDescripcionRegion(rs.getString("descripcionReg"));
				arbol.setNombrePais(rs.getString("pais"));
			}
			
		} 
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA CARRERA "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return arbol;
	}
	
	public boolean editarArConImg(Arbol ar, InputStream fi) {
		boolean editado = false;
		
		//PreparedStatement ps;
		String sql = "Update arbol set nombreComun = ?, nombreCientifico = ?, descripcion = ?, idFamilia = ?, idGenero = ?, idDistribucion = ?, idFlor = ?, foto = ?, estado = 2 where idArbol = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, ar.getNombreComun());
			ps.setString(2, ar.getNombreCientifico());
			ps.setString(3, ar.getDescripcion());
			ps.setInt(4, ar.getIdFamilia());
			ps.setInt(5, ar.getIdGenero());
			ps.setInt(6, ar.getIdDistribucion());
			ps.setInt(7, ar.getIdFlor());
			ps.setBinaryStream(8, fi);
			ps.setInt(9, ar.getId());
			
			ps.executeUpdate();
			
			editado = true;
			
			
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return editado;
	}
	
	public boolean editarArSinImg(Arbol ar) {
		boolean editado = false;
		
		//PreparedStatement ps;
		String sql = "Update arbol set nombreComun = ?, nombreCientifico = ?, descripcion = ?, idFamilia = ?, idGenero = ?, idDistribucion = ?, idFlor = ?, estado = 2 where idArbol = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, ar.getNombreComun());
			ps.setString(2, ar.getNombreCientifico());
			ps.setString(3, ar.getDescripcion());
			ps.setInt(4, ar.getIdFamilia());
			ps.setInt(5, ar.getIdGenero());
			ps.setInt(6, ar.getIdDistribucion());
			ps.setInt(7, ar.getIdFlor());
			ps.setInt(8, ar.getId());
			
			ps.executeUpdate();
			editado = true;
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return editado;
	}
}

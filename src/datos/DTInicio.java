package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import entidades.Inicio;

public class DTInicio {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public Inicio llenarInicio() {
		Inicio in = new Inicio();
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.inicio where estado <>3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			if(rs.last()) {
				in.setIdInicio(rs.getInt("idInicio"));
				in.setHistoria(rs.getString("historia"));
				in.setMision(rs.getString("mision"));
				in.setVision(rs.getString("vision"));
				in.setEstado(rs.getInt("estado"));
				in.setFotoHistoria("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("historiaFoto")));
				in.setFotoMision("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("misionFoto")));
				in.setFotoVision("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("visionFoto")));
			}
			
		} catch (Exception e){
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
		
		
		return in;
	}
	
	public boolean guardarInicio(Inicio in, InputStream inH, InputStream inM, InputStream inV) {
		boolean guardado = false;
		//PreparedStatement ps;
		String sql  = "Insert into public.inicio(historia, historiaFoto, mision, misionFoto, vision, visionFoto, estado) Values(?,?,?,?,?,?,1)";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getHistoria());
			ps.setBinaryStream(2, inH);
			ps.setString(3, in.getMision());
			ps.setBinaryStream(4, inM);
			ps.setString(5, in.getVision());
			ps.setBinaryStream(6, inV);
			
			ps.executeUpdate();
			
			guardado = true;
			
			
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
		
		return guardado;
	}
	
	public boolean editarConImgHistoria(Inicio in, InputStream inH) {
		boolean editado = false;
		String sql = "Update inicio set historia = ?, historiaFoto = ? where idInicio = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getHistoria());
			ps.setBinaryStream(2, inH);
			ps.setInt(3, in.getIdInicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
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
	
	public boolean editarSinImgHistoria(Inicio in) {
		boolean editado = false;
		String sql = "Update inicio set historia = ? where idInicio = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getHistoria());
			ps.setInt(2, in.getIdInicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
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
	
	public boolean editarConImgMision(Inicio in, InputStream inH) {
		boolean editado = false;
		String sql = "Update inicio set mision = ?, misionFoto = ? where idInicio = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getMision());
			ps.setBinaryStream(2, inH);
			ps.setInt(3, in.getIdInicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
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
	
	public boolean editarSinImgMision(Inicio in) {
		boolean editado = false;
		String sql = "Update inicio set mision = ? where idInicio = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getMision());
			ps.setInt(2, in.getIdInicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
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
	
	public boolean editarConImgVision(Inicio in, InputStream inH) {
		boolean editado = false;
		String sql = "Update inicio set vision = ?, visionFoto = ? where idInicio = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getVision());
			ps.setBinaryStream(2, inH);
			ps.setInt(3, in.getIdInicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
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
	
	public boolean editarSinImgVision(Inicio in) {
		boolean editado = false;
		String sql = "Update inicio set vision = ? where idInicio = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1,in.getVision());
			ps.setInt(2, in.getIdInicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
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

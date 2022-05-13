package datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import entidades.*;

public class DTPublicacion {
	
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPu = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsFamilia(Connection c) {
		String sql = "SELECT * FROM public.publicaciones where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPu = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar las publicaciones " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Publicacion> listarPublicacion(){
		ArrayList<Publicacion> listaPublicaciones = new ArrayList<Publicacion>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.publicaciones where estado <> 3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Publicacion pu = new Publicacion();
				pu.setIdPublicacion(rs.getInt("idPublicaciones"));
				pu.setTitulo(rs.getString("titulo"));
				pu.setDescripcion(rs.getString("descripcion"));
				pu.setMultimedia("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("multimedia")));
				pu.setFechaPublicacion(rs.getDate("fechaPublicacion").toString());
				pu.setHipervinculo(rs.getString("hipervinculo"));
				pu.setEstado(rs.getInt("estado"));
				
				listaPublicaciones.add(pu);
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
		return listaPublicaciones;
	}
	
	public ArrayList<Publicacion> listarPublicacionV(){
		ArrayList<Publicacion> listaPublicaciones = new ArrayList<Publicacion>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.publicaciones where estado = 1", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Publicacion pu = new Publicacion();
				pu.setIdPublicacion(rs.getInt("idPublicaciones"));
				pu.setTitulo(rs.getString("titulo"));
				pu.setDescripcion(rs.getString("descripcion"));
				pu.setMultimedia("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("multimedia")));
				pu.setFechaPublicacion(rs.getDate("fechaPublicacion").toString());
				pu.setHipervinculo(rs.getString("hipervinculo"));
				pu.setEstado(rs.getInt("estado"));
				
				listaPublicaciones.add(pu);
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
		return listaPublicaciones;
	}
	
	public boolean guardarPublicacion(Publicacion pu, InputStream fi) {
		boolean resp = false;
		//PreparedStatement ps;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("INSERT INTO publicaciones(titulo, descripcion, multimedia, estado, hipervinculo, fechapublicacion) VALUES(?,?,?,?,?,current_date)");
			ps.setString(1, pu.getTitulo());
			ps.setString(2, pu.getDescripcion());
			ps.setBinaryStream(3, fi);
			ps.setInt(4, pu.getEstado());
			ps.setString(5, pu.getHipervinculo());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				resp = true;
			}else {
				resp = false;
			}
			
			ps.close();
			
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
		
		return resp;
	}
	
	public boolean eliminarPublicacion(int id) {
		boolean eliminado = false;
		
		try {
			c = PoolConexion.getConnection();
			this.llenarRsFamilia(c);;
			rsPu.beforeFirst();
			while (rsPu.next())
			{
				if(rsPu.getInt(1)==id)
				{
					rsPu.deleteRow();
					eliminado=true;
					break;
				}
			}
			
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsPu != null){
					rsPu.close();
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
		
		return eliminado;
	}

	public Publicacion buscarPublicacion(int id) {
		Publicacion pu = new Publicacion();
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.publicaciones where idPublicaciones = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				pu.setIdPublicacion(rs.getInt("idPublicaciones"));
				pu.setTitulo(rs.getString("titulo"));
				pu.setDescripcion(rs.getString("descripcion"));
				pu.setMultimedia("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("multimedia")));
				pu.setFechaPublicacion(rs.getDate("fechaPublicacion").toString());
				pu.setHipervinculo(rs.getString("hipervinculo"));
				pu.setEstado(rs.getInt("estado"));
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
				
		return pu;
	}
	
	public boolean modificarPuConImg(Publicacion pu, InputStream fi) {
		boolean modificado = false;
		
		//PreparedStatement ps;
		String sql = "Update publicaciones set titulo = ?, descripcion = ?, hipervinculo = ?, multimedia = ?, fechaPublicacion = current_date, estado = ? where idPublicaciones = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, pu.getTitulo());
			ps.setString(2, pu.getDescripcion());
			ps.setString(3, pu.getHipervinculo());
			ps.setBinaryStream(4, fi);
			
			ps.setInt(5, pu.getEstado());
			ps.setInt(6, pu.getIdPublicacion());
			
			ps.executeUpdate();
			
			modificado = true;
			
			
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
		
		return modificado;
	}
	
	public boolean modificarPuSinImg(Publicacion pu) {
		boolean modificado = false;
		
		//PreparedStatement ps;
		String sql = "Update publicaciones set titulo = ?, descripcion = ?, hipervinculo = ?, fechaPublicacion = current_date, estado = ? where idPublicaciones = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, pu.getTitulo());
			ps.setString(2, pu.getDescripcion());
			ps.setString(3, pu.getHipervinculo());
			
			ps.setInt(4, pu.getEstado());
			ps.setInt(5, pu.getIdPublicacion());
			
			
			ps.executeUpdate();
			
			modificado = true;
			
			ps.close();
			
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
		
		return modificado;
	}
	
}

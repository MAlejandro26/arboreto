package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.*;

public class DTEvento {
	
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsEvento = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsEvento(Connection c) {
		String sql = "SELECT * FROM public.eventos where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsEvento = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar los eventos " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Eventos> listarEventos(){
		ArrayList<Eventos> listaEventos = new ArrayList<Eventos>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.eventos where estado<>3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Eventos evento = new Eventos();
				evento.setIdEvento(rs.getInt("idEventos")); 
				evento.setNombre(rs.getString("nombre"));
				evento.setDescripcion(rs.getString("descripcion"));
				evento.setTipoEvento(rs.getString("tipoEvento"));
				evento.setHipervinculo(rs.getString("hipervinculo"));
				evento.setFechaInicio(rs.getString("fechaInicio"));
				evento.setFechaFin(rs.getString("fechaFin"));
				evento.setUbicacion(rs.getString("ubicacion"));
				evento.setEstado(rs.getInt("estado"));
				//System.out.println(rs.getString("fechaInicio"));
				listaEventos.add(evento);
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
		return listaEventos;
	}
	
	public ArrayList<Eventos> listarEventosV(){
		ArrayList<Eventos> listaEventos = new ArrayList<Eventos>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.eventos where tipoevento = 'Agenda Visible'", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Eventos evento = new Eventos();
				evento.setIdEvento(rs.getInt("idEventos")); 
				evento.setNombre(rs.getString("nombre"));
				evento.setDescripcion(rs.getString("descripcion"));
				evento.setTipoEvento(rs.getString("tipoEvento"));
				evento.setHipervinculo(rs.getString("hipervinculo"));
				evento.setFechaInicio(rs.getString("fechaInicio"));
				evento.setFechaFin(rs.getString("fechaFin"));
				evento.setUbicacion(rs.getString("ubicacion"));
				evento.setEstado(rs.getInt("estado"));
				//System.out.println(rs.getString("fechaInicio"));
				listaEventos.add(evento);
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
		return listaEventos;
	}
	
	
	public boolean guardarEvento(Eventos ev) {
		boolean guardado = false;
		
		//PreparedStatement ps;
		String sql = "Insert into public.eventos(nombre, descripcion, fechaInicio, fechaFin, tipoEvento, ubicacion, hipervinculo, estado) Values(?,?,?,?,?,?,?,1)";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, ev.getNombre());
			ps.setString(2, ev.getDescripcion());
			ps.setString(3, ev.getFechaInicio());
			ps.setString(4, ev.getFechaFin());
			ps.setString(5, ev.getTipoEvento());
			ps.setString(6, ev.getUbicacion());
			ps.setString(7, ev.getHipervinculo());
			
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
	
	public boolean eliminarEvento(int id) {
		boolean eliminado = false;
		
		try {
			c = PoolConexion.getConnection();
			this.llenarRsEvento(c);;
			rsEvento.beforeFirst();
			while (rsEvento.next())
			{
				if(rsEvento.getInt(1)==id)
				{
					rsEvento.deleteRow();
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
				if(rsEvento != null){
					rsEvento.close();
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
	
	public Eventos obtenerEvento(int id) {
		Eventos evento = new Eventos();
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.eventos where idEventos = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				evento.setIdEvento(rs.getInt("idEventos")); 
				evento.setNombre(rs.getString("nombre"));
				evento.setDescripcion(rs.getString("descripcion"));
				evento.setTipoEvento(rs.getString("tipoEvento"));
				evento.setHipervinculo(rs.getString("hipervinculo"));
				evento.setFechaInicio(rs.getString("fechaInicio"));
				evento.setFechaFin(rs.getString("fechaFin"));
				evento.setUbicacion(rs.getString("ubicacion"));
				evento.setEstado(rs.getInt("estado"));
			}
			
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
		
		return evento;
	}
	
	public boolean editarEvento(Eventos ev) {
		boolean editado = false;
		String sql = "Update eventos set nombre = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, tipoEvento = ?, ubicacion = ?, hipervinculo = ?, estado = 2 where idEventos = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, ev.getNombre());
			ps.setString(2, ev.getDescripcion());
			ps.setString(3, ev.getFechaInicio());
			ps.setString(4, ev.getFechaFin());
			ps.setString(5, ev.getTipoEvento());
			ps.setString(6, ev.getUbicacion());
			ps.setString(7, ev.getHipervinculo());
			ps.setInt(8, ev.getIdEvento());
			
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

package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import entidades.Pais;
import entidades.Producto;
import entidades.Servicios;

public class DTServicio {
	
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsServicios = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsServicio(Connection c){
		try{
			ps = c.prepareStatement("select * from public.servicios", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsServicios = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DT SERVICIO: ERROR LISTAR RS"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Servicios> listarServicios(){
		ArrayList<Servicios> listaServicios = new ArrayList<Servicios>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.servicios where estado<>3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Servicios s = new Servicios();
				s.setIdServicio(Integer.parseInt(rs.getString("idServicio")));
				s.setNombre(rs.getString("nombre"));
				s.setDescripcion(rs.getString("descripcion"));
				s.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				s.setEstado(Integer.parseInt(rs.getString("estado")));
				
				listaServicios.add(s);
			}
		}
		catch (Exception e){
			System.out.println("DT SERVICIO: ERROR EN LISTAR"+ e.getMessage());
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
		return listaServicios;
	}
	
	public ArrayList<Servicios> listarServiciosV(){
		ArrayList<Servicios> listaServicios = new ArrayList<Servicios>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.servicios where estado = 1", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Servicios s = new Servicios();
				s.setIdServicio(Integer.parseInt(rs.getString("idServicio")));
				s.setNombre(rs.getString("nombre"));
				s.setDescripcion(rs.getString("descripcion"));
				s.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				s.setEstado(Integer.parseInt(rs.getString("estado")));
				
				listaServicios.add(s);
			}
		}
		catch (Exception e){
			System.out.println("DT SERVICIO: ERROR EN LISTAR"+ e.getMessage());
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
		return listaServicios;
	}
	
	
	public boolean guardarServicio(Servicios s, InputStream fis)
	{
		boolean guardado = false;
		PreparedStatement ps;
		String sql = "INSERT INTO public.servicios (nombre, descripcion, foto, estado) VALUES (?,?,?,?)";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getDescripcion());
			ps.setBinaryStream(3, fis);;
			ps.setInt(4, s.getEstado());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				guardado = true;
			}else {
				guardado = false;
			}
			
			ps.close();

		} 
		catch (Exception e) 
		{
			System.err.println("DT Servicio: Error al guardar un servicio " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsServicios != null)
				{
					rsServicios.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Servicio: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean eliminarServicio(int id)
	{
		boolean eliminado=false;	
		try
		{
			c = PoolConexion.getConnection();
			this.llenarRsServicio(c);;
			rsServicios.beforeFirst();
			while (rsServicios.next())
			{
				if(rsServicios.getInt(1)==id)
				{
					rsServicios.deleteRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("DT SERVICIO: ERROR EN ELIMINAR"+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsServicios != null){
					rsServicios.close();
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
	
	public Servicios getServicio(int id) {
		Servicios s = new Servicios();
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.servicios where idServicio = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				s.setIdServicio(rs.getInt("idServicio"));
				s.setNombre(rs.getString("nombre"));
				s.setDescripcion(rs.getString("descripcion"));
				s.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				s.setEstado(rs.getInt("estado"));
			}
			
		} catch (Exception e){
			System.out.println("DT SERVICIO: ERROR EN OBTENER"+ e.getMessage());
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
		
		return s;
	}
	
	public boolean editarSerConImagen(Servicios s, InputStream in) {
		boolean editado = false;
		//PreparedStatement ps;
		String sql = "Update public.servicios set nombre = ?, descripcion = ?, foto = ?, estado = ? where idServicio = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getDescripcion());
			ps.setBinaryStream(3, in);
			ps.setInt(4, s.getEstado());
			ps.setInt(5, s.getIdServicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
			System.out.println("DT REGION: ERROR EN ACTUALIZAR CON IMAGEN"+ e.getMessage());
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
	
	public boolean editarSerSinImagen(Servicios s) {
		boolean editado = false;
		//PreparedStatement ps;
		String sql = "Update public.servicios set nombre = ?, descripcion = ?, estado = ? where idServicio = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getDescripcion());
			ps.setInt(3, s.getEstado());
			ps.setInt(4, s.getIdServicio());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
			System.out.println("DT SERVICIO: ERROR EN ACTUALIZAR SIN IMAGEN"+ e.getMessage());
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

package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import entidades.Distribucion;
import entidades.Producto;
import entidades.Region;

public class DTDistribucion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsDistribucion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsDistri(Connection c)
	{
		String sql = "SELECT * FROM public.distribucion where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsDistribucion = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Distribucion: Error en listar las distribuciones " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Distribucion> listarDistribucion(){
		ArrayList<Distribucion> listarD = new ArrayList<Distribucion>();
		
		String sql = "SELECT * FROM public.distribucion where estado <> 3";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Distribucion d = new Distribucion();
				
				d.setIdDistribucion(rs.getInt("idDistribucion"));
				d.setNombre(rs.getString("nombre"));
				d.setDescripcion(rs.getString("descripcion"));
				d.setIdRegion(rs.getInt("idRegion"));
				d.setEstado(rs.getInt("estado"));
				
				listarD.add(d);
				
			}
			
		} catch (Exception e) 
		{
			System.err.println("DT Distribucion: Error en listar las distribuciones " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null)
					rs.close();
				
				if(ps != null)
					ps.close();
				
				if(c != null)
					PoolConexion.closeConnection(c);
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Distribucion: Error en listar las distribuciones " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listarD;
	}
	
	public boolean guardarDistribucion(Distribucion d)
	{
		boolean guardado = false;
		//PreparedStatement ps;
		String sql = "INSERT INTO public.distribucion (nombre, descripcion, estado, idregion) VALUES (?,?,?,?);";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, d.getNombre());
			ps.setString(2, d.getDescripcion());
			ps.setInt(3, d.getEstado());
			ps.setInt(4, d.getIdRegion());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				guardado = true;
			}else {
				guardado = false;
			}

		}catch (Exception e){
			System.out.println("DT DISTRIBUCION: ERROR EN GUARDAR"+ e.getMessage());
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
	
	public boolean eliminarDistri(int id)
		{
			boolean eliminado=false;	
			try
			{
				c = PoolConexion.getConnection();
				this.llenarRsDistri(c);;
				rsDistribucion.beforeFirst();
				while (rsDistribucion.next())
				{
					if(rsDistribucion.getInt(1)==id)
					{
						rsDistribucion.deleteRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("DT DISTRIBUCION: ERROR EN ELIMINAR"+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rsDistribucion != null){
						rsDistribucion.close();
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
	
		public Distribucion getDistribucion(int id) {
			Distribucion d = new Distribucion();
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from public.distribucion where iddistribucion = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				if(rs.first()) {
					d.setIdDistribucion(rs.getInt("idDistribucion"));
					d.setNombre(rs.getString("nombre"));
					d.setDescripcion(rs.getString("descripcion"));
					d.setEstado(rs.getInt("estado"));
					d.setIdRegion(rs.getInt("idRegion"));
				}
				
			} catch (Exception e){
				System.out.println("DT DISTRIBUCION: ERROR EN OBTENER"+ e.getMessage());
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
			
			return d;
		}
		
		public boolean editarDistribucion(Distribucion d) {
			boolean editado = false;
			//PreparedStatement ps;
			String sql = "Update distribucion set nombre = ?, descripcion = ?, estado = 2, idregion = ? where iddistribucion = ?";
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql);
				
				ps.setString(1, d.getNombre());
				ps.setString(2, d.getDescripcion());
				ps.setInt(3, d.getIdRegion());
				ps.setInt(4, d.getIdDistribucion());
				
				ps.executeUpdate();
				
				editado = true;
				
			}catch (Exception e){
				System.out.println("DT DISTRIBUCION: ERROR EN ACTUALIZAR"+ e.getMessage());
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

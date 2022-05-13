package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import entidades.Pais;
import entidades.Region;

public class DTRegion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRegion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsRegion(Connection c)
	{
		String sql = "SELECT * FROM public.region where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRegion = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Region: Error en listar las regiones " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Region> listarRegiones()
	{
		ArrayList<Region> listaR = new ArrayList<Region>();
		
		String sql = "SELECT * FROM public.region where estado <> 3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Region r = new Region();
				r.setIdRegion(rs.getInt("idregion"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setEstado(Integer.parseInt(rs.getString("estado")));
				r.setIdPais(Integer.parseInt(rs.getString("idpais")));
				
				listaR.add(r);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Region: Error en listar los regiones " + e.getMessage());
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
				System.err.println("DT REGION: Error en listar los regiones " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaR;
	}
	
	
	public boolean guardarRegion(Region r)
	{
		boolean guardado = false;
		//PreparedStatement ps;
		String sql = "INSERT INTO public.region (nombre, descripcion, estado, idpais) VALUES (?,?,?,?);";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, r.getNombre());
			ps.setString(2, r.getDescripcion());
			ps.setInt(3, r.getEstado());
			ps.setInt(4, r.getIdPais());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				guardado = true;
			}else {
				guardado = false;
			}

		} catch (Exception e){
			System.out.println("DR REGION: ERROR EN GUARDAR"+ e.getMessage());
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
	
	public boolean eliminarRegion(int id)
		{
			boolean eliminado=false;	
			try
			{
				c = PoolConexion.getConnection();
				this.llenarRsRegion(c);
				rsRegion.beforeFirst();
				while (rsRegion.next())
				{
					if(rsRegion.getInt(1)==id)
					{
						rsRegion.deleteRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("DT Region: Error al eliminar una Región "+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rsRegion != null){
						rsRegion.close();
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
	
	public Region getRegion(int id) {
		Region r = new Region();
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.region where idregion = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				r.setIdRegion(rs.getInt("idRegion"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setEstado(rs.getInt("estado"));
				r.setIdPais(rs.getInt("idPais"));
			}
			
		} catch (Exception e){
			System.out.println("DT REGION: ERROR EN OBTENER"+ e.getMessage());
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
		
		return r;
	}
	
	public boolean editarRegion(Region r) {
		boolean editado = false;
		//PreparedStatement ps;
		String sql = "Update region set nombre = ?, descripcion = ?, estado = 2, idpais = ? where idregion = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, r.getNombre());
			ps.setString(2, r.getDescripcion());
			ps.setInt(3, r.getIdPais());
			ps.setInt(4, r.getIdRegion());
			
			ps.executeUpdate();
			
			editado = true;
			
		}catch (Exception e){
			System.out.println("DT REGION: ERROR EN ACTUALIZAR"+ e.getMessage());
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

	
	/*
	
	//Método para encriptar con MD5
	public String md5(String input)
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) 
			{
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			throw new RuntimeException(e);
		}
	}
	*/

}

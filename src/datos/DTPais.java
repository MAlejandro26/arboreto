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

public class DTPais {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPais = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsPais(Connection c)
	{
		String sql = "SELECT * FROM public.pais where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPais = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar los paises " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	public ArrayList<Pais> listarPaises()
	{
		ArrayList<Pais> listaPaises = new ArrayList<Pais>();
		
		String sql = "SELECT * FROM public.pais where estado <> 3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Pais p = new Pais();
				p.setIdPais(rs.getInt("idpais"));
				p.setNombre(rs.getString("nombre"));
				
				listaPaises.add(p);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar los paises " + e.getMessage());
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
				System.err.println("DT Pais: Error en listar los paises " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaPaises;
	}
	
	
	public boolean guardarPais(Pais p)
	{
		boolean guardado = false;
		//PreparedStatement ps;
		String sql = "INSERT INTO public.pais (nombre, estado) VALUES (?,?);";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEstado());
			int i = ps.executeUpdate();
			
			if(i==1) {
				guardado = true;
			}else {
				guardado = false;
			}

		}catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Pais "+ e.getMessage());
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
	
	public boolean eliminarPais(int id)
	{
		boolean eliminado=false;	
		try
		{
			c = PoolConexion.getConnection();
			this.llenarRsPais(c);;
			rsPais.beforeFirst();
			while (rsPais.next())
			{
				if(rsPais.getInt(1)==id)
				{
					rsPais.deleteRow();
					eliminado=true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Erro al eliminar el País "+e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rsPais != null){
					rsPais.close();
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
	
	public Pais getPais(int id) {
		Pais p = new Pais();
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.pais where idpais = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				p.setIdPais(rs.getInt("idPais"));
				p.setNombre(rs.getString("nombre"));
				p.setEstado(rs.getInt("estado"));
			}
			
		} catch (Exception e){
			System.out.println("DT PAIS: ERROR EN LISTAR LOS PAISES"+ e.getMessage());
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
		
		return p;
	}
	
	public boolean editarPais(Pais p) {
		boolean editado = false;
		//PreparedStatement ps;
		String sql = "Update public.pais set nombre = ?, estado = 2 where idpais = ?";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getIdPais());
			
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

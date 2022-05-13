package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidades.Pais;
import entidades.Rol;

public class DTRol {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsRol(Connection c)
	{
		String sql = "SELECT * FROM public.rol where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRol = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Rol: Error en listar los roles " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	public ArrayList<Rol> listarRol()
	{
		ArrayList<Rol> listaRol = new ArrayList<Rol>();
		
		String sql = "SELECT * FROM public.rol where estado <> 3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Rol r = new Rol();
				r.setIdRol(rs.getInt("idRol"));
				r.setNombre(rs.getString("nombre"));
				listaRol.add(r);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT ROL: Error en listar los roles " + e.getMessage());
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
				System.err.println("DT ROL: Error en listar los roles " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaRol;
	}
}

package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.Vista_region_pais;

public class DTVista_region_pais {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Vista_region_pais> listarRP()
	{
		ArrayList<Vista_region_pais> listaRegion_Pais = new ArrayList<Vista_region_pais>();
		
		String sql = "SELECT * FROM PUBLIC.VISTA_REGION_PAIS";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Vista_region_pais vrp = new Vista_region_pais();
				vrp.setIdRegion(Integer.parseInt(rs.getString("idRegion")));
				vrp.setNombre(rs.getString("nombre"));
				vrp.setDescripcion(rs.getString("descripcion"));
				vrp.setNombre_pais(rs.getString("nombre_pais"));
				
				listaRegion_Pais.add(vrp);
				
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
				System.err.println("DT Vista_region_pais: Error en listar las regiones con sus paises " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaRegion_Pais;
	}

}

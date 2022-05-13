package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.Vista_distribucion_region;
import vistas.Vista_region_pais;

public class DTVista_distribucion_region {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Vista_distribucion_region> listarRP()
	{
		ArrayList<Vista_distribucion_region> listaRegion_Pais = new ArrayList<Vista_distribucion_region>();
		
		String sql = "SELECT * FROM PUBLIC.VISTA_DISTRIBUCION_REGION";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Vista_distribucion_region vw = new Vista_distribucion_region();
				vw.setIdDistribucion(Integer.parseInt(rs.getString("idDistribucion")));
				vw.setNombre(rs.getString("nombre"));
				vw.setDescripcion(rs.getString("descripcion"));
				vw.setNombre_region(rs.getString("nombre_region"));
				
				listaRegion_Pais.add(vw);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT DTVista_distribucion_region: Error en listar las distribuciones con sus regiones " + e.getMessage());
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
				System.err.println("DT DTVista_distribucion_region: Error en listar las distribuciones con sus regiones " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaRegion_Pais;
	}
}

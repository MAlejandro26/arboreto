package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import vistas.Vista_coordenada_arbol;

public class DTVista_coordenada_arbol {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Vista_coordenada_arbol> listarCA()
	{
		ArrayList<Vista_coordenada_arbol> listaVC = new ArrayList<Vista_coordenada_arbol>();
		
		String sql = "SELECT * FROM public.vista_coordenada_arbol";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Vista_coordenada_arbol vw = new Vista_coordenada_arbol();
				vw.setIdCoordenadaArbol(rs.getInt("idcoordenada_arbol"));
				vw.setIdarbol(rs.getInt("idarbol"));
				vw.setNombreComun(rs.getString("nombrecomun"));
				vw.setNombreCientifico(rs.getString("nombrecientifico"));
				vw.setLatitud(rs.getDouble("latitud"));
				vw.setLongitud(rs.getDouble("longitud"));
				vw.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				
				listaVC.add(vw);
				
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
			catch (SQLException e) 
			{
				System.err.println("DT DTVista_distribucion_region: Error en listar las distribuciones con sus regiones " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return listaVC;
	}
}

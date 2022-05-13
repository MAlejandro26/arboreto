package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import vistas.Vista_arbol;


public class DTVista_arbol {
	
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Vista_arbol> listarAR()
	{
		ArrayList<Vista_arbol> listaArbol = new ArrayList<Vista_arbol>();
		
		String sql = "SELECT * FROM PUBLIC.VISTA_ARBOL";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Vista_arbol va = new Vista_arbol();
				va.setId(Integer.parseInt(rs.getString("idArbol")));
				va.setNombreComun(rs.getString("nombreComun"));
				va.setNombreCientifico(rs.getString("nombreCientifico"));
				va.setDescripcion(rs.getString("descripcion"));
				va.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
				va.setNombre_Genero(rs.getString("genero"));
				va.setNombre_Familia(rs.getString("familia"));
				va.setNombre_Flor(rs.getString("floracion"));
				va.setNombre_Distribucion(rs.getString("distribucion"));
				
				listaArbol.add(va);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar arbol " + e.getMessage());
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
				System.err.println("DT Vista_arbol: Error en listar arbol " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaArbol;
	}
	
	
	
	
	
	
}

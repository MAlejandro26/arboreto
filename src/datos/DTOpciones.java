package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import entidades.Opciones;

public class DTOpciones {

	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsBanner = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Opciones> listarOpciones(int rol){
		ArrayList<Opciones> listBanner = new ArrayList<Opciones>();
		
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("Select o.nombre from rol r, opciones o, rol_opciones ro\r\n"
					+ "where ro.idrol = r.idrol and ro.idopciones = o.idopciones\r\n"
					+ "and r.idrol = "+rol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				Opciones on = new Opciones();
				on.setNombre(rs.getString("nombre"));
				
				listBanner.add(on);
			}
		}
		catch (Exception e){
			System.out.println("DT BANNER: ERROR EN LISTAR"+ e.getMessage());
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
		return listBanner;
	}
}

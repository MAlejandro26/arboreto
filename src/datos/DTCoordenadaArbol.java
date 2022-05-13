package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.CoordenadaArbol;
import entidades.Flor;

public class DTCoordenadaArbol {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRegion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<CoordenadaArbol> listarCoordenada(){
		ArrayList<CoordenadaArbol> listaCa = new ArrayList<CoordenadaArbol>();
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.coordenada_arbol", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CoordenadaArbol ca = new CoordenadaArbol();
				
				ca.setIdCoordenadaArbol(rs.getInt("idcoordenada_arbol"));
				ca.setLatitud(rs.getDouble("latitud"));
				ca.setLongitud(rs.getDouble("longitud"));
				ca.setIdArbol(rs.getInt("idArbol"));
				
				listaCa.add(ca);
				
			}
			
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA CARRERA "+ e.getMessage());
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
		
		return listaCa;
	}
	
	public boolean guardarCoordenadaArbol(CoordenadaArbol ca) {
		boolean guardado = false;
		String sql = "INSERT INTO public.coordenada_arbol (latitud, longitud, idArbol) VALUES (?,?,?);";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			ps.setDouble(1, ca.getLatitud());
			ps.setDouble(2, ca.getLongitud());
			ps.setInt(3, ca.getIdArbol());
			
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
}

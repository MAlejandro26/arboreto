package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.Vista_usuario_rol;

public class DTVista_usuario_rol {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Vista_usuario_rol> listarUR()
	{
		ArrayList<Vista_usuario_rol> listaUsuarioRol = new ArrayList<Vista_usuario_rol>();
		
		String sql = "SELECT * FROM PUBLIC.VISTA_USUARIO_ROL";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Vista_usuario_rol vur = new Vista_usuario_rol();
				vur.setIdusuario(Integer.parseInt(rs.getString("idUsuario")));
				vur.setNombres(rs.getString("nombres"));
				vur.setApellidos(rs.getString("apellidos"));
				vur.setUsuario(rs.getString("usuario"));
				vur.setEmail(rs.getString("email"));
				vur.setContrasenia(rs.getString("contrasenia"));
				vur.setRol(rs.getString("rol"));
				vur.setEstado(Integer.parseInt(rs.getString("estado")));
				
				listaUsuarioRol.add(vur);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Vista_Usuario_Rol: Error en listar los usuarios " + e.getMessage());
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
				System.err.println("DT Vista_Usuario_Rol : Error en listar los usuarios" + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaUsuarioRol;
	}
}

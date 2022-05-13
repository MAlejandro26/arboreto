package datos;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import entidades.Usuario;

public class DTUsuario {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
		public void llenarUsuario(Connection c){
			try{
				ps = c.prepareStatement("select * from public.usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsUsuario = ps.executeQuery();
			}
			catch (Exception e){
				System.out.println("DT Usuario: Error al listar elementos del Usuario "+ e.getMessage());
				e.printStackTrace();
			}
		}
	
		public boolean guardarUsuario(Usuario u){
			boolean resp = false;
			
			if(validarUsario_Email(u.getUsuario(), u.getEmail())) {
				resp = false;
			}else {
				try{
					c = PoolConexion.getConnection();
					ps = c.prepareStatement("INSERT INTO public.usuario(nombres, apellidos, usuario, email, contrasenia, idrol, estado, codv) "
											+ "VALUES(?,?,?,?,?,?,?,?)");
					
					ps.setString(1, u.getNombres());
					ps.setString(2, u.getApellidos());
					ps.setString(3, u.getUsuario());
					ps.setString(4, u.getEmail());
					String contrasenia = getMD5(u.getContrasenia());
					ps.setString(5, contrasenia);
					ps.setInt(6, u.getIdRol());
					ps.setInt(7, u.getEstado());
					//GUARDAMOS EL CODIGO DE VERIFICACION
					ps.setString(8, u.getCodV());
					
					int i = ps.executeUpdate();
					
					if(i==1) {
						resp = true;
					}else {
						resp = false;
					}
				}
				catch (Exception e){
					System.out.println("DATOS: ERROR EN LISTAR Elementos del Usuario "+ e.getMessage());
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
			}
			
			return resp;
		}
		
		public boolean validarUsario_Email(String usuario, String correo) {
			boolean existe = false;
			
			//PreparedStatement ps;
			String sql = "Select * from public.usuario where usuario = ? OR email = ?";
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, usuario);
				ps.setString(2, correo);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					existe = true;
				}else {
					existe = false;
				}
				
				rs.close();
				
			} catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR Elementos del Usuario "+ e.getMessage());
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
			
			return existe;
		}
		
		
		public boolean eliminarUsuario(int id)
		{
			boolean eliminado=false;	
			try
			{
				c = PoolConexion.getConnection();
				this.llenarUsuario(c);;
				rsUsuario.beforeFirst();
				while (rsUsuario.next())
				{
					if(rsUsuario.getInt(1)==id)
					{
						rsUsuario.deleteRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("ERROR AL ELIMINAR USUARIO "+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rsUsuario != null){
						rsUsuario.close();
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
		
		public boolean loginUsuario(Usuario u) {
			boolean encontrado = false;
			String contrasenia = getMD5(u.getContrasenia());
			
			//PreparedStatement ps;
			String sql = "Select * from public.usuario where usuario = ? and contrasenia = ? and estado <> 0";
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, u.getUsuario());
				ps.setString(2, contrasenia);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					encontrado = true;
				}else {
					encontrado = false;
				}
				
			} catch (Exception e){
				System.out.println("DATOS: ERROR AL VERIFICAR EL LOGIN"+ e.getMessage());
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
			
			return encontrado;
		}
		
		// Metodo para verificar usuario, contrasena, rol y codigo verificacion
		public boolean loginUsuario2(String login, String clave, String codigo)
		{
			boolean existe=false;
			String contrasenia = getMD5(clave);
			String SQL = ("SELECT * FROM USUARIO WHERE usuario=? AND contrasenia=? AND codv=?");
			try{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(SQL);
				ps.setString(1, login);
				ps.setString(2, contrasenia);
				ps.setString(3, codigo);
				rs = ps.executeQuery();
				if(rs.next()){
					existe=true;
					actualizarEstadoUsuario(login);
				}
			}
			catch (Exception e){
				System.out.println("DATOS: ERROR AL VERIFICAR EL LOGIN "+ e.getMessage());
				e.printStackTrace();
			}
			finally {
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
			return existe;
		}

			// Metodo para actualizar estado del Usuario
		public boolean actualizarEstadoUsuario(String login)
		{
			boolean actualizado = false;
			
			try
			{
				c = PoolConexion.getConnection();
				this.llenarUsuario(c);	
				rsUsuario.beforeFirst();
				while(rsUsuario.next())
				{
					if(rsUsuario.getString("usuario").equals(login))
					{						
						rsUsuario.updateInt("estado", 1);
						rsUsuario.updateRow();
						actualizado = true;
						break;
					}
				}
			}
			catch (Exception e) 
			{
				System.err.println("ERROR AL ACTUALIZAR ESTADO USUARIO "+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rsUsuario != null){
						rsUsuario.close();
					}
					if(c != null){
						PoolConexion.closeConnection(c);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return actualizado;
		}
				
		public String getMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] encBytes = md.digest(input.getBytes());
	            BigInteger numero = new BigInteger(1, encBytes);
	            String encString = numero.toString(16);
	            while (encString.length() < 32) {
	                encString = "0" + encString;
	            }
	            return encString;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
		
		
		public ArrayList<Usuario> listarU()
		{
			ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
			
			String sql = "SELECT * FROM PUBLIC.usurio where estado <> 3";
			
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while(rs.next())
				{
					Usuario u = new Usuario();
					u.setIdUsuario(Integer.parseInt(rs.getString("idUsuario")));
					u.setNombres(rs.getString("nombres"));
					u.setApellidos(rs.getString("apellidos"));
					u.setUsuario(rs.getString("usuario"));
					u.setEmail(rs.getString("email"));
					u.setContrasenia(rs.getString("contrasenia"));
					u.setIdRol(Integer.parseInt(rs.getString("idRol")));
					
					listaUsuario.add(u);
					
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
			
			return listaUsuario;
		}
		
		public boolean validarEmail(String email){
			boolean existe = false;
			
			String sql = "select * from public.usuario where email = ?";
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, email);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					existe = true;
					
					
				}else {
					existe = false;
				}
				
			}catch (Exception e) 
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
			
			return existe;
		}
		
		public boolean cambiarPass(String email, String password) {
			String sql = "Update usuario set contrasenia = ? where email = ?";
			boolean modificado = false;
			
			String enc = getMD5(password);
			
			
			try {
				
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql);
				
				ps.setString(1, enc);
				ps.setString(2, email);
				
				ps.executeUpdate();
				
				modificado = true;
				
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
			
			return modificado;
		}
		
		
		public boolean cambiarContrasenia(String user, String pass) {
			boolean verificado = false;
			
			String enc = getMD5(pass);
			String sql = ("Update usuario set contrasenia = ? where usuario = ?");
			
			try {
				
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql);
				
				ps.setString(1, enc);
				ps.setString(2, user);
				
				
				
				if(ps.executeUpdate()==1) {
					verificado = true;
				}
				
				
				
			} catch (Exception e){
				System.out.println("DATOS: ERROR EN dtGetRU "+ e.getMessage());
				e.printStackTrace();
			}
			finally {
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
			
			return verificado;
		}
		
		//Metodo para obtener un objeto de la vista rol usuario
		public Usuario dtGetUsuario(String login)
		{
		Usuario u = new Usuario();
		String SQL = ("SELECT * FROM usuario where usuario=?");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, login);
			rs = ps.executeQuery();
			if(rs.next()){
				u.setIdUsuario(rs.getInt("idusuario"));
				u.setNombres(rs.getString("nombres"));
				u.setApellidos(rs.getString("apellidos"));
				u.setUsuario(rs.getString("usuario"));
				u.setEmail(rs.getString("email"));
				u.setContrasenia(rs.getString("contrasenia"));
				u.setIdRol(rs.getInt("idrol"));
				u.setEstado(rs.getInt("estado"));
				u.setCodV(rs.getString("codv"));
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN dtGetRU "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		return u;
	}
		
		
		//METODO PARA GENERAR UN CODIGO DE VERIFICACION //	
		private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		public static String randomAlphaNumeric(int count) 
		{
			StringBuilder builder = new StringBuilder();
			while (count-- != 0) 
			{
				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			return builder.toString();
		}
}

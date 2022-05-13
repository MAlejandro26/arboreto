package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import entidades.Banner;
import entidades.Producto;
import entidades.Servicios;

public class DTBanner {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsBanner = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
		public void llenarBanner(Connection c){
			try{
				ps = c.prepareStatement("select * from public.banner", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsBanner = ps.executeQuery();
			}
			catch (Exception e){
				System.out.println("DT BANNER: ERROR EN LISTAR RS"+ e.getMessage());
				e.printStackTrace();
			}
		}

		public ArrayList<Banner> listarBanner(){
			ArrayList<Banner> listBanner = new ArrayList<Banner>();
			
			try{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("SELECT * FROM banner b where b.estado <> 3 order by b.posicion asc", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while(rs.next()){
					Banner bn = new Banner();
					bn.setIdBanner(rs.getInt("idBanner"));
					bn.setTitulo(rs.getString("titulo"));
					bn.setDescripcion(rs.getString("descripcion"));
					bn.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
					bn.setPosicion(rs.getInt("posicion"));
					bn.setEstado(rs.getInt("estado"));
					
					listBanner.add(bn);
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
	
		public boolean guardarBanner(Banner bn, InputStream fi){
			boolean resp = false;
			
			try{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("INSERT INTO public.banner(titulo,descripcion, foto, posicion, estado) "
										+ "VALUES(?,?,?,?,?)");
				
				ps.setString(1, bn.getTitulo());
				ps.setString(2, bn.getDescripcion());
				ps.setBinaryStream(3, fi);
				ps.setInt(4, bn.getPosicion() + 1);
				ps.setInt(5, bn.getEstado());
				
				int i = ps.executeUpdate();
				
				if(i==1) {
					resp = true;
				}else {
					resp = false;
				}
			}
			catch (Exception e){
				System.out.println("DT BANNER: ERROR EN GUARDAR"+ e.getMessage());
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
			return resp;
		}
		
		public boolean eliminaBanner(int id)
		{
			boolean eliminado=false;	
			try
			{
				c = PoolConexion.getConnection();
				this.llenarBanner(c);;
				rsBanner.beforeFirst();
				while (rsBanner.next())
				{
					if(rsBanner.getInt(1)==id)
					{
						rsBanner.deleteRow();
						eliminado=true;
						break;
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("DTBANNER: ERROR EN ELIMINAR"+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rsBanner != null){
						rsBanner.close();
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
		
		public Banner getBanner(int id) {
			Banner b = new Banner();
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from public.banner where idbanner = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				if(rs.first()) {
					b.setIdBanner(rs.getInt("idbanner"));
					b.setTitulo(rs.getString("titulo"));
					b.setDescripcion(rs.getString("descripcion"));
					b.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(rs.getBytes("foto")));
					b.setPosicion(rs.getInt("posicion"));
					b.setEstado(rs.getInt("estado"));
				}
				
			} catch (Exception e){
				System.out.println("DT BANNER: ERROR EN OBTENER"+ e.getMessage());
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
			
			return b;
		}
		
		public boolean editarBaConImagen(Banner b, InputStream in) {
			boolean editado = false;
			//PreparedStatement ps;
			String sql = "Update banner set titulo = ?, descripcion = ?, foto = ?, posicion = ?, estado = 2 where idbanner = ?";
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql);
				
				ps.setString(1, b.getTitulo());
				ps.setString(2, b.getDescripcion());
				ps.setBinaryStream(3, in);				
				ps.setInt(4, b.getPosicion());
				ps.setInt(5, b.getIdBanner());
				
				ps.executeUpdate();
				
				editado = true;
				
			}catch (Exception e){
				System.out.println("DT BANNER: ERROR EN ACTUALIZAR CON IMAGEN"+ e.getMessage());
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
		
		public boolean editarBaSinImagen(Banner b) {
			boolean editado = false;
			//PreparedStatement ps;
			String sql = "Update banner set titulo = ?, descripcion = ?, posicion = ?, estado = 2 where idbanner = ?";
			
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql);
				
				ps.setString(1, b.getTitulo());
				ps.setString(2, b.getDescripcion());
				ps.setDouble(3, b.getPosicion());
				ps.setInt(4, b.getIdBanner());
				
				ps.executeUpdate();
				
				editado = true;
				
			}catch (Exception e){
				System.out.println("DT BANER: ERROR EN ACTUALIZAR SIN IMAGEN"+ e.getMessage());
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
}

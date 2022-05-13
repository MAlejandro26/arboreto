package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoProducto;

public class DTTipo_producto {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsTp = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarRsFamilia(Connection c) {
		String sql = "SELECT * FROM public.tipo_producto where estado <> 3";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsTp = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT USUARIO: Error en listar los tipos de productos " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<TipoProducto> listarTipoP(){
		ArrayList<TipoProducto> listarTP = new ArrayList<TipoProducto>();
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.tipo_producto where estado<>3", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				TipoProducto tp = new TipoProducto();
				tp.setIdTipoProducto(rs.getInt("idTipo_producto"));
				tp.setNombreTipo(rs.getString("nombretipo"));
				tp.setDescripcion(rs.getString("descripcion"));
				tp.setEstado(rs.getInt("estado"));
				
				listarTP.add(tp);
				
			}
			
			
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR El TIPO DE PRODUCTO "+ e.getMessage());
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
		
		return listarTP;
	}
	
	public boolean guardarTipoProducto(TipoProducto tp) {
		boolean guardado = false;
		//PreparedStatement ps;
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("Insert into tipo_producto(nombretipo, descripcion, estado) Values(?,?,1)");
			
			ps.setString(1, tp.getNombreTipo());
			ps.setString(2, tp.getDescripcion());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				guardado = true;
			}else {
				guardado = false;
			}
			
		} catch (Exception e){
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
		
		return guardado;
	}
	
	public boolean eliminarTipoProducto(int id) {
		boolean eliminado = false;
		
		try {
			c = PoolConexion.getConnection();
			this.llenarRsFamilia(c);;
			rsTp.beforeFirst();
			while (rsTp.next())
			{
				if(rsTp.getInt(1)==id)
				{
					rsTp.deleteRow();
					eliminado=true;
					break;
				}
			}
			
		} catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Elementos del Banner "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsTp != null){
					rsTp.close();
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
		
		return eliminado;
	}
	
	public TipoProducto buscarTP(int id) {
		TipoProducto tp = new TipoProducto();
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from public.tipo_producto where idTipo_producto = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.first()) {
				tp.setIdTipoProducto(rs.getInt("idTipo_producto"));
				tp.setNombreTipo(rs.getString("nombretipo"));
				tp.setDescripcion(rs.getString("descripcion"));
				tp.setEstado(rs.getInt("estado"));
			}
			
		} catch (Exception e){
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
		
		return tp;
	}
	
	public boolean editarTP(TipoProducto tp) {
		boolean editado = false;
		
		String sql = "Update tipo_producto set nombretipo = ?, descripcion = ?, estado = 2 where idTipo_producto = ?";
		
		try {
			
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql);
			
			ps.setString(1, tp.getNombreTipo());
			ps.setString(2, tp.getDescripcion());
			ps.setInt(3, tp.getIdTipoProducto());
			
			ps.executeUpdate();
			
			editado = true;
			
		} catch (Exception e){
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
		
		return editado;
		
		
	}
}

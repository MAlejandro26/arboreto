package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.NotFoundException;

import datos.DTProducto;
import entidades.Producto;

/**
 * Servlet implementation class SLEditarProducto
 */
@WebServlet("/SLEditarProducto")
@MultipartConfig
public class SLEditarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cambio = request.getParameter("cambio");
		String v = "true";
		
		int idP = Integer.parseInt(request.getParameter("id"));
		Producto p = new Producto();
		DTProducto dt = new DTProducto();
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion1");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		int idTipoP = Integer.parseInt(request.getParameter("tipoP").trim());
		
		if(nombre.length()==0||descripcion.length()==0||precio.equals(null) || idTipoP == 0) {
			response.sendRedirect("productgestion.jsp?msj=error");
		}else {
		
			p.setIdProducto(idP);
			p.setNombre(nombre);
			p.setDescripcion(descripcion);
			p.setPrecio(precio);
			p.setIdTipoProducto(idTipoP);
			
			if(cambio.equals(v)) {
				
				Part part = request.getPart("foto");
				InputStream in = part.getInputStream();
				
				if(dt.editarPrConImagen(p, in)) {
					response.sendRedirect("productgestion.jsp?msj=3");
				}else {
					response.sendRedirect("productgestion.jsp?msj=error");
				}
				
			}else {
				if(dt.editarPrSinImagen(p)) {
					response.sendRedirect("productgestion.jsp?msj=3");
				}else {
					response.sendRedirect("productgestion.jsp?msj=error");
				}
			}
		}
		
	}

}

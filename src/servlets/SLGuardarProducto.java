package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.servlet.http.Part;

import entidades.Producto;
import datos.DTProducto;

/**
 * Servlet implementation class SLGuardarProducto
 */
@WebServlet(name="SLGuardarProducto", urlPatterns="/SLGuardarProducto")
@MultipartConfig
public class SLGuardarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarProducto() {
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
		Producto p = new Producto();
		DTProducto dt = new DTProducto();
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		int idTipoP = Integer.parseInt(request.getParameter("tipoP").trim());
		Part part = request.getPart("foto");
		InputStream in = part.getInputStream();
		
		if(nombre.length()==0||descripcion.length()==0||precio.equals(null) || idTipoP == 0 || in.equals(null)) {
			response.sendRedirect("productgestion.jsp?msj=error");
		}else {
		
			try {
				
				p.setNombre(nombre);
				p.setDescripcion(descripcion);
				p.setPrecio(precio);
				p.setIdTipoProducto(idTipoP);
				
				if(dt.guardarProducto(p, in))
				{
					response.sendRedirect("productgestion.jsp?msj=1");
				}
				else
				{
					response.sendRedirect("productgestion.jsp?msj=error");
				}
				
			} catch (NotFoundException e) {
				System.out.println(e);
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}

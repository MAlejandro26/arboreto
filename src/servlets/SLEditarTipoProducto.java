package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTTipo_producto;
import entidades.TipoProducto;

/**
 * Servlet implementation class SLEditarTipoProducto
 */
@WebServlet("/SLEditarTipoProducto")
public class SLEditarTipoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarTipoProducto() {
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
		int id = Integer.parseInt(request.getParameter("id").trim());
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		
		TipoProducto tp = new TipoProducto();
		DTTipo_producto dtp = new DTTipo_producto();
		
		if(nombre.length()==0||descripcion.length()==0 || id == 0) {
			response.sendRedirect("typeproductgestion.jsp?msj=error");
		}else {
		
			tp.setIdTipoProducto(id);
			tp.setNombreTipo(nombre);
			tp.setDescripcion(descripcion);
			
			if(dtp.editarTP(tp)) {
				response.sendRedirect("typeproductgestion.jsp?msj=3");
			}else {
				response.sendRedirect("typeproductgestion.jsp?msj=error");
			}
		}
	}

}

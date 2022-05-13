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

import datos.DTRegion;
import entidades.Region;

/**
 * Servlet implementation class SLEditarRegion
 */
@WebServlet("/SLEditarRegion")
@MultipartConfig
public class SLEditarRegion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarRegion() {
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
		
		int idR = Integer.parseInt(request.getParameter("id").trim());
		Region r = new Region();
		DTRegion dt = new DTRegion();
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int idPais = Integer.parseInt(request.getParameter("idPais").trim());
		
		r.setIdRegion(idR);
		r.setNombre(nombre);
		r.setDescripcion(descripcion);
	    r.setIdPais(idPais);

		if(dt.editarRegion(r)) {
			response.sendRedirect("regiongestion.jsp?msj=1");
		}else {
			response.sendRedirect("regiongestion.jsp?msj=2");
		}
			
	}

}

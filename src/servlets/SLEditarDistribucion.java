package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTDistribucion;
import entidades.Distribucion;


/**
 * Servlet implementation class SLEditarDistribucion
 */
@WebServlet("/SLEditarDistribucion")
@MultipartConfig
public class SLEditarDistribucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarDistribucion() {
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

		
		int idD = Integer.parseInt(request.getParameter("idD").trim());
		
		Distribucion d = new Distribucion();
		DTDistribucion dt = new DTDistribucion();
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int idRegion = Integer.parseInt(request.getParameter("idRegion").trim());
		
		if(nombre.length()==0 || descripcion.length()==0 || idRegion == 0 ) {
			response.sendRedirect("distributiongestion.jsp?msj=error");
		}else {
		
			d.setIdDistribucion(idD);
			d.setNombre(nombre);
			d.setDescripcion(descripcion);
			d.setIdRegion(idRegion);
					
			if(dt.editarDistribucion(d)) {
				response.sendRedirect("distributiongestion.jsp?msj=3");
			}else {
				response.sendRedirect("distributiongestion.jsp?msj=4");
			}
		}
			
		
	}

}

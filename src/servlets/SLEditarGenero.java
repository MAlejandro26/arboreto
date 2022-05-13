package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTFamilia;
import datos.DTGenero;
import entidades.Familiar;
import entidades.Genero;

/**
 * Servlet implementation class SLEditarGenero
 */
@WebServlet("/SLEditarGenero")
public class SLEditarGenero extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarGenero() {
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
		
		Genero g = new Genero();
		DTGenero dt = new DTGenero();
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		
		if(id == 0 || nombre.length()==0 || descripcion.length()==0) {	
			response.sendRedirect("gendergestion.jsp?id="+id);
		}else {
			
			g.setIdGenero(id);
			g.setNombre(nombre);
			g.setDescripcion(descripcion);
			
			if(dt.editarGenero(g)) {
				response.sendRedirect("gendergestion.jsp?msj=1");
			}else {
				response.sendRedirect("gendergestion.jsp?msj=2");
			}
		}
	}

}

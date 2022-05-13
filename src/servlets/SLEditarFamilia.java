package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTFamilia;
import entidades.Familiar;

/**
 * Servlet implementation class SLEditarFamilia
 */
@WebServlet("/SLEditarFamilia")
public class SLEditarFamilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarFamilia() {
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
		
		Familiar f = new Familiar();
		DTFamilia dt = new DTFamilia();
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		
		if(id == 0 || nombre.length()==0 || descripcion.length()==0) {
			response.sendRedirect("familygestion.jsp?msj=error");
		}else {
			f.setIdFamilia(id);
			f.setNombre(nombre);
			f.setDescripcion(descripcion);
			
			if(dt.editarFamilia(f)) {
				response.sendRedirect("familygestion.jsp?msj=3");
			}else {
				response.sendRedirect("familygestion.jsp?msj=4");
			}
		}
		
	}

}

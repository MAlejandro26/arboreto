package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.*;
import entidades.*;


/**
 * Servlet implementation class SLGuardarFamilia
 */
@WebServlet(name="SLGuardarFamilia", urlPatterns="/SLGuardarFamilia")
public class SLGuardarFamilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarFamilia() {
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
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		boolean resp = false;
		
		if(nombre.length() == 0 || descripcion.length() == 0) {
			
			response.sendRedirect("formfamily.jsp?msj=error");
			
		}else {
			
			Familiar fa = new Familiar();
			DTFamilia dt = new DTFamilia();
			
			fa.setNombre(nombre);
			fa.setDescripcion(descripcion);
			
			resp = dt.guardarFamiliar(fa);
			
			if(resp==true) {
				response.sendRedirect("familygestion.jsp?msj=1");
			}else {
				response.sendRedirect("familygestion.jsp?msj=2");
			}
		}
		
	}

}

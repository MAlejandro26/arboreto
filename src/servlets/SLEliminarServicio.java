package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTServicio;
import entidades.Servicios;

/**
 * Servlet implementation class SLEliminarServicio
 */
@WebServlet(name="SLEliminarServicio", urlPatterns="/SLEliminarServicio")
public class SLEliminarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEliminarServicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id =0;
		id = Integer.parseInt(request.getParameter("id"));
		DTServicio dtb = new DTServicio(); 
		
		if(dtb.eliminarServicio(id)) {
        	response.sendRedirect("servicegestion.jsp?msj=5");
        }
        else {
        	response.sendRedirect("servicegestion.jsp?msj=6");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

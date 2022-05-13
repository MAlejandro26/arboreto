package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTBanner;
import datos.DTDistribucion;

/**
 * Servlet implementation class SLEliminarDistribucion
 */
@WebServlet(name="SLEliminarDistribucion", urlPatterns="/SLEliminarDistribucion")
public class SLEliminarDistribucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEliminarDistribucion() {
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
		DTDistribucion dtb = new DTDistribucion(); 
		
		if(dtb.eliminarDistri(id)) {
        	response.sendRedirect("distributiongestion.jsp?msj=5");
        }
        else {
        	response.sendRedirect("distributiongestion.jsp?msj=6");
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

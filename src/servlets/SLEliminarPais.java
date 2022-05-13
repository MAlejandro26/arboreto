package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTBanner;
import datos.DTPais;
import entidades.Pais;


/**
 * Servlet implementation class SLEliminarPais
 */
@WebServlet(name="SLEliminarPais" , urlPatterns="/SLEliminarPais")
public class SLEliminarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEliminarPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String idpais = "";
		idpais = request.getParameter("id").trim();
		
		try {
			DTPais dt = new DTPais();
			Pais p = new Pais();
			
			p.setIdPais(Integer.parseInt(idpais));
			
			if(dt.eliminarPais(p)) {
				response.sendRedirect("countrygestion.jsp");
			}else {
				response.sendRedirect("countrygestion.jsp?error");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		int id =0;
		id = Integer.parseInt(request.getParameter("id"));
		DTPais dtb = new DTPais(); 
		
		if(dtb.eliminarPais(id)) {
        	response.sendRedirect("countrygestion.jsp?msj=5");
        }
        else {
        	response.sendRedirect("countrygestion.jsp?msj=6");
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

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTRegion;
import entidades.Region;

/**
 * Servlet implementation class SLGuardarRegion
 */
@WebServlet("/SLGuardarRegion")
public class SLGuardarRegion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarRegion() {
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
		doGet(request, response);
		
		try 
		{
			Region r = new Region();
			DTRegion dt = new DTRegion();
			
			String nombre, descripcion;
			int estado = 1;
			String idpais;
			
			nombre = request.getParameter("nombreR");
			descripcion = request.getParameter("descripcionR");
			idpais = request.getParameter("pais").trim();
			int id = Integer.parseInt(idpais);
		
			r.setNombre(nombre);
			r.setDescripcion(descripcion);
			r.setEstado(estado);
			r.setIdPais(id);

			if(dt.guardarRegion(r))
			{
				response.sendRedirect("regiongestion.jsp");
			}
			else
			{
				response.sendRedirect("regiongestion.jsp?error");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Region: Error al guardar la region " +e.getMessage());
			e.printStackTrace();
		}

	}

}

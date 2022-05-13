package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTDistribucion;
import datos.DTRegion;
import entidades.Distribucion;
import entidades.Region;

/**
 * Servlet implementation class SLGuardarDistribucion
 */
@WebServlet("/SLGuardarDistribucion")
public class SLGuardarDistribucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarDistribucion() {
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
		
			Distribucion d = new Distribucion();
			DTDistribucion dt = new DTDistribucion();
			
			String nombre, descripcion;
			int estado = 1;
			String idregion;
		
			nombre = request.getParameter("nombreD");
			descripcion = request.getParameter("descripcionD");
			idregion = request.getParameter("region").trim();
			int id = Integer.parseInt(idregion);
				
			if(nombre.length()==0 || descripcion.length()==0 || idregion == null || id == 0 ) {
				response.sendRedirect("distributiongestion.jsp?msj=error");
			}else {
				
			   try 
			   {
					d.setNombre(nombre);
					d.setDescripcion(descripcion);
					d.setEstado(estado);
					d.setIdRegion(id);
		
					if(dt.guardarDistribucion(d))
					{
						response.sendRedirect("distributiongestion.jsp?msj=1");
					}
					else
					{
						response.sendRedirect("distributiongestion.jsp?msj=2");
					}
				} 
				catch (Exception e) 
				{
					System.err.println("SL Distribucion: Error al guardar la distribucion " +e.getMessage());
					e.printStackTrace();
				}
			}
	}

}

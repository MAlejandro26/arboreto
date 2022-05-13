package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCoordenadaArbol;
import entidades.CoordenadaArbol;

/**
 * Servlet implementation class SLGuardarCoord
 */
@WebServlet("/SLGuardarCoord")
public class SLGuardarCoord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarCoord() {
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
		
		try {
			CoordenadaArbol ca = new CoordenadaArbol();
			DTCoordenadaArbol dt = new DTCoordenadaArbol();
			
			int idArbol = Integer.parseInt(request.getParameter("cmbArbol").trim());
			double lat = Double.parseDouble(request.getParameter("txtLat"));
			double lon = Double.parseDouble(request.getParameter("txtLon"));
			
			ca.setIdArbol(idArbol);
			ca.setLatitud(lat);
			ca.setLongitud(lon);
			
			if(dt.guardarCoordenadaArbol(ca))
			{
				response.sendRedirect("mapgestion.jsp");
			}
			else
			{
				response.sendRedirect("mapgestion.jsp?error=1");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println("SL Coordenada: Error al guardar la Coordenada " +e.getMessage());
			e.printStackTrace();
		}
	}

}

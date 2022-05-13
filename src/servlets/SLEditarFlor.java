package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTFlor;
import entidades.Flor;

/**
 * Servlet implementation class SLEditarFlor
 */
@WebServlet("/SLEditarFlor")
public class SLEditarFlor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarFlor() {
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
		String nombreComun = request.getParameter("nombreCo");
		String nombreCientifico = request.getParameter("nombreCi");
		String descripcion = request.getParameter("descripcion");
		String temporada = request.getParameter("temporadaF");
		
		
		if(id == 0 || nombreComun.length()==0 || nombreCientifico.length()==0 || descripcion.length()==0 || temporada.length()==0) {
			
			response.sendRedirect("editflower.jsp?msj=error");
			
		}else {
		
			Flor fl = new Flor();
			DTFlor dt = new DTFlor();
			
			fl.setIdFlor(id);
			fl.setNombreComun(nombreComun);
			fl.setNombreCientifico(nombreCientifico);
			fl.setDescripcion(descripcion);
			fl.setTemporadaFloracion(temporada);
			
			if(dt.editarFlor(fl)){
				response.sendRedirect("flowergestion.jsp?msj=3");
			}else {
				response.sendRedirect("flowergestion.jsp?msj=error");
			}
		}
	}

}

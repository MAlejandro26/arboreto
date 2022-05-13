package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.*;
import datos.*;

/**
 * Servlet implementation class SLGuardarFlor
 */
@WebServlet(name="SLGuardarFlor", urlPatterns="/SLGuardarFlor")
public class SLGuardarFlor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarFlor() {
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
		String nombreComun = request.getParameter("nombreCo");
		String nombreCientifico = request.getParameter("nombreCi");
		String descripcion = request.getParameter("descripcion");
		String temporada = request.getParameter("temporadaF");
		boolean resp = false;
		
		if(nombreComun.length()==0 || nombreCientifico.length()==0 || descripcion.length()==0 || temporada.length()==0) {
			
			response.sendRedirect("flowergestion.jsp?msj=error");
			
		}else {
			
			Flor fl = new Flor();
			DTFlor dt = new DTFlor();
			
			fl.setNombreComun(nombreComun);
			fl.setNombreCientifico(nombreCientifico);
			fl.setDescripcion(descripcion);
			fl.setTemporadaFloracion(temporada);
			
			resp = dt.guardarFlor(fl);
			
			if(resp==true) {
				response.sendRedirect("flowergestion.jsp?msj=1");
			}else {
				response.sendRedirect("flowergestion.jsp?msj=error");
			}
			
			
		}
		
	}

}

package servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import datos.DTArbol;
import entidades.Arbol;

/**
 * Servlet implementation class SLEditarArbol
 */
@WebServlet("/SLEditarArbol")
@MultipartConfig
public class SLEditarArbol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarArbol() {
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
		String cambio = request.getParameter("cambio");
		String v = "true";
		
		Arbol ar = new Arbol();
		DTArbol dta = new DTArbol();
				
		int idA = Integer.parseInt(request.getParameter("id"));
		String nombreCo = request.getParameter("nombreCo");
		String nombreCi = request.getParameter("nombreCi");
		String descripcion = request.getParameter("descripcion");
		int idGenero = Integer.parseInt(request.getParameter("genero").trim());
		int idFamilia = Integer.parseInt(request.getParameter("familia").trim());
		int idFlor = Integer.parseInt(request.getParameter("flor").trim());
		int idDistribucion = Integer.parseInt(request.getParameter("distribucion").trim());
		
		ar.setId(idA);
		ar.setNombreComun(nombreCo);
		ar.setNombreCientifico(nombreCi);
		ar.setDescripcion(descripcion);
		ar.setIdGenero(idGenero);
		ar.setIdFamilia(idFamilia);
		ar.setIdFlor(idFlor);
		ar.setIdDistribucion(idDistribucion);	
		
		if(cambio.equals(v)) {
			
			Part part = request.getPart("imagen");
			InputStream in = part.getInputStream();
			
			if(dta.editarArConImg(ar, in)) {
				response.sendRedirect("treegestion.jsp?msj=3");
			}else {
				response.sendRedirect("treegestion.jsp?msj=error");
			}
			
		}else {
			if(dta.editarArSinImg(ar)) {
				response.sendRedirect("treegestion.jsp?msj=3");
			}else {
				response.sendRedirect("treegestion.jsp?msj=error");
			}
		}
	}

}

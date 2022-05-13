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
import javax.ws.rs.NotFoundException;

import datos.DTArbol;
import entidades.Arbol;



/**
 * Servlet implementation class SLGuardarArbol
 */
@WebServlet(name="SLGuardarArbol", urlPatterns="/SLGuardarArbol")
@MultipartConfig
public class SLGuardarArbol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarArbol() {
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
		
		try {
			
			Arbol ar = new Arbol();
			DTArbol dta = new DTArbol();
					
			String nombreCo = request.getParameter("nombreCo");
			String nombreCi = request.getParameter("nombreCi");
			String descripcion = request.getParameter("descripcion");
			int idGenero = Integer.parseInt(request.getParameter("genero").trim());
			int idFamilia = Integer.parseInt(request.getParameter("familia").trim());
			int idFlor = Integer.parseInt(request.getParameter("flor").trim());
			int idDistribucion = Integer.parseInt(request.getParameter("distribucion").trim());
			Part part = request.getPart("imagen");
			InputStream in = part.getInputStream();
			
			ar.setNombreComun(nombreCo);
			ar.setNombreCientifico(nombreCi);
			ar.setDescripcion(descripcion);
			ar.setIdGenero(idGenero);
			ar.setIdFamilia(idFamilia);
			ar.setIdFlor(idFlor);
			ar.setIdDistribucion(idDistribucion);	
			
			if(dta.guardarArbol(ar, in))
			{
				response.sendRedirect("treegestion.jsp?msj=1");
			}
			else
			{
				response.sendRedirect("treegestion.jsp?msj=error");
			}
			
		} catch (NotFoundException e) {
			System.out.println(e);
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}

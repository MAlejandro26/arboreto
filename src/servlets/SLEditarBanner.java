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

import datos.DTBanner;
import datos.DTProducto;
import entidades.Banner;
import entidades.Producto;

/**
 * Servlet implementation class SLEditarBanner
 */
@WebServlet("/SLEditarBanner")
@MultipartConfig
public class SLEditarBanner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarBanner() {
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
		
		int idB = Integer.parseInt(request.getParameter("id").trim());
		Banner b = new Banner();
		DTBanner dt = new DTBanner();
		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion1");
		int posicion = Integer.parseInt(request.getParameter("posicion").trim());
		
		b.setIdBanner(idB);
		b.setTitulo(titulo);
		b.setDescripcion(descripcion);
		b.setPosicion(posicion);
		
		if(cambio.equals(v)) {
			
			Part part = request.getPart("foto");
			InputStream in = part.getInputStream();
			
			if(dt.editarBaConImagen(b, in)) {
				response.sendRedirect("bannergestions.jsp?msj=3");
			}else {
				response.sendRedirect("bannergestions.jsp?msj=4");
			}
			
		}else {
			if(dt.editarBaSinImagen(b)) {
				response.sendRedirect("bannergestions.jsp?msj=3");
			}else {
				response.sendRedirect("bannergestions.jsp?msj=4");
			}
		}
	}
}

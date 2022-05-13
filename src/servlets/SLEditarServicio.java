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

import datos.DTServicio;
import entidades.Servicios;

/**
 * Servlet implementation class SLEditarServicio
 */
@WebServlet(name="SLEditarServicio", urlPatterns="/SLEditarServicio")
@MultipartConfig
public class SLEditarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarServicio() {
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
			
			int idS = Integer.parseInt(request.getParameter("id").trim());
			Servicios s = new Servicios();
			DTServicio dt = new DTServicio();
			String nombre = request.getParameter("nombre").trim();
			String descripcion = request.getParameter("descripcion").trim();
			String est = request.getParameter("estado").trim();
			int estado = Integer.parseInt(est);
			
			s.setIdServicio(idS);
			s.setNombre(nombre);
			s.setDescripcion(descripcion);
			s.setEstado(estado);
			
			if(cambio.equals(v)) {
				
				Part part = request.getPart("foto");
				InputStream in = part.getInputStream();
				
				if(dt.editarSerConImagen(s, in)) {
					response.sendRedirect("servicegestion.jsp?msj=3");
				}else {
					response.sendRedirect("servicegestion.jsp?msj=4");
				}
				
			}else {
				if(dt.editarSerSinImagen(s)) {
					response.sendRedirect("servicegestion.jsp?msj=3");
				}else {
					response.sendRedirect("servicegestion.jsp??msj=4");
				}
			}

	}

}

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

import datos.DTBanner;
import datos.DTServicio;
import entidades.Banner;
import entidades.Servicios;

/**
 * Servlet implementation class SLGuardarBanner
 */
@WebServlet(name="SLGuardarBanner" ,urlPatterns="/SLGuardarBanner")
@MultipartConfig
public class SLGuardarBanner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarBanner() {
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
	
			String titulo = request.getParameter("txtTitulo");
			String descripcion = request.getParameter("txtDescripcion");
			int posicion = Integer.parseInt(request.getParameter("posicion").trim());
			Part part = request.getPart("foto");			
			
			InputStream fin = part.getInputStream(); //Como jodido validar esto xd
			
			int estado = 1;
			boolean resp = false;
			
			Banner b = new Banner();
			DTBanner dt = new DTBanner();
			
			if(titulo.length()==0 || descripcion.length()==0 || fin.equals(null) ) {
				response.sendRedirect("bannergestions.jsp?msj=error");
			}
			else {
			
				b.setTitulo(titulo);
				b.setDescripcion(descripcion);
				//pu.setImgBytea(imgBytea);
				b.setPosicion(posicion);
				b.setEstado(estado);
	
				resp = dt.guardarBanner(b,fin);
				
				if(resp == true) {
					response.sendRedirect("bannergestions.jsp?msj=1");
				}else{
					response.sendRedirect("bannergestions.jsp?msj=2");
				};
			}
		
	}

}

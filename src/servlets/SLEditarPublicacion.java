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

import datos.DTPublicacion;
import entidades.Publicacion;

/**
 * Servlet implementation class SLEditarPublicacion
 */
@WebServlet("/SLEditarPublicacion")
@MultipartConfig
public class SLEditarPublicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarPublicacion() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion");
		String hipervinculo = request.getParameter("hipervinculo");
		String est = request.getParameter("estado").trim();
		int estado = Integer.parseInt(est);
		
		Publicacion pu = new Publicacion();
		DTPublicacion dt = new DTPublicacion();
		
		if(titulo.length()==0||descripcion.length()==0||hipervinculo.length()==0) {
			response.sendRedirect("publicationgestion.jsp?msj=error");
		}else {
				pu.setIdPublicacion(id);
				pu.setTitulo(titulo);
				pu.setDescripcion(descripcion);
				pu.setHipervinculo(hipervinculo);
				pu.setEstado(estado);
				
				if(cambio.equals(v)) {
					
					Part part = request.getPart("imagen");
					InputStream fin = part.getInputStream();
					
					if(dt.modificarPuConImg(pu, fin)) {
						response.sendRedirect("publicationgestion.jsp?msj=3");
					}else {
						response.sendRedirect("publicationgestion.jsp?msj=error");
					}
					
				}else {
					if(dt.modificarPuSinImg(pu)) {
						response.sendRedirect("publicationgestion.jsp?msj=3");
					}else {
						response.sendRedirect("publicationgestion.jsp?msj=error");
					}
				}
		}
	}

}

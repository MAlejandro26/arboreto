package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;
import javax.ws.rs.NotFoundException;

import datos.DTPublicacion;
import datos.DTServicio;
import entidades.Publicacion;
import entidades.Servicios;

/**
 * Servlet implementation class SLGuardarServicio
 */
@WebServlet(name="SLGuardarServicio", urlPatterns="/SLGuardarServicio")
@MultipartConfig
public class SLGuardarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarServicio() {
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
		
			String nombre = request.getParameter("nombreS");
			String descripcion = request.getParameter("descripcionS");
			Part part = request.getPart("foto");
			
			InputStream fin = part.getInputStream();
			
			String est = request.getParameter("estado").trim();
			int estado = Integer.parseInt(est);
			boolean resp = false;
			
			Servicios s = new Servicios();
			
			if(nombre.length()==0 || descripcion.length() ==0) {
				response.sendRedirect("servicegestion.jsp?msj=error");
			}
			else {
				try {
					
					s.setNombre(nombre);
					s.setDescripcion(descripcion);
					//pu.setImgBytea(imgBytea);
					s.setEstado(estado);
					
					DTServicio dt = new DTServicio();
					
					resp = dt.guardarServicio(s,fin);
					
					if(resp == true) {
						response.sendRedirect("servicegestion.jsp?msj=1");
					}else{
						response.sendRedirect("servicegestion.jsp?msj=2");
					};
				} catch (NotFoundException e) {
					System.out.println(e);
				}catch (Exception e) {
					System.out.println(e);
				}
			}
		
	}

}

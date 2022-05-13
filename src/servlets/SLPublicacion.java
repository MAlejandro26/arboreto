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

import datos.*;
import entidades.*;

/**
 * Servlet implementation class SLPublicacion
 */

@WebServlet(name ="SLPublicacion", urlPatterns = "/SLPublicacion")
@MultipartConfig
public class SLPublicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLPublicacion() {
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
		
		Publicacion pu = new Publicacion();
		DTPublicacion dt = new DTPublicacion();
		
		String titulo = request.getParameter("titulo");
		String descripcion = request.getParameter("descripcion");
		String hipervinculo = request.getParameter("hipervinculo");
		Part part = request.getPart("imagen");
		
		InputStream fin = part.getInputStream();
		//byte [] imgBytea = Files.readAllBytes(file.toPath());
		
		int estado = 1;
		boolean resp = false;
		
		
		if(titulo.length()==0||descripcion.length()==0) {
			response.sendRedirect("publicationgestion.jsp?msj=error");
		}else {
			
			try {
	
				pu.setTitulo(titulo);
				pu.setDescripcion(descripcion);
				pu.setHipervinculo(hipervinculo);
				pu.setEstado(estado);
				
				resp = dt.guardarPublicacion(pu,fin);
				
				if(resp == true) {
					response.sendRedirect("publicationgestion.jsp?msj=1");
				}else{
					response.sendRedirect("publicationgestion.jsp?msj=error");
				};
			} catch (NotFoundException e) {
				System.out.println(e);
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		
		
		
		//System.out.println(""+imgBytea);
		
		
		//response.sendRedirect("index.jsp");
		
	}
	

}

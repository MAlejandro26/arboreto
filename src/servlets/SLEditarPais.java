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

import datos.DTPais;
import datos.DTServicio;
import entidades.Pais;
import entidades.Servicios;

/**
 * Servlet implementation class SLEditarPais
 */
@WebServlet("/SLEditarPais")
@MultipartConfig
public class SLEditarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarPais() {
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
	
		int idP = Integer.parseInt(request.getParameter("id").trim());
		Pais p = new Pais();
		DTPais dt = new DTPais();
		String nombre = request.getParameter("nombre").trim();
		
		p.setIdPais(idP);
		p.setNombre(nombre);
		
			
		if(dt.editarPais(p)) {
			response.sendRedirect("countrygestion.jsp?msj=3");
		}else {
			response.sendRedirect("countrygestion.jsp?msj=4");
		}

	}

}

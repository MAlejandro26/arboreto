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

import datos.DTInicio;
import entidades.Inicio;

/**
 * Servlet implementation class SLEditarHistoria
 */
@WebServlet("/SLEditarHistoria")
@MultipartConfig
public class SLEditarHistoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEditarHistoria() {
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
		
		Inicio in = new Inicio();
		DTInicio dt = new DTInicio();
		
		int idInicio = Integer.parseInt(request.getParameter("id"));
		String historia = request.getParameter("descripcion1");
		System.out.println("nose"+historia);
		
		
		if(historia.length()==0 || (cambio.length()==0 && idInicio == 0)) {
			response.sendRedirect("formstart.jsp?msj=error");	
		}else {
			in.setIdInicio(idInicio);
			in.setHistoria(historia);
			
			if(cambio.equals(v)) {
				Part partH = request.getPart("foto");
				InputStream inH = partH.getInputStream();
				
				if(dt.editarConImgHistoria(in, inH)) {
					response.sendRedirect("formstart.jsp?msj=1");
				}else {
					response.sendRedirect("formstart.jsp?error");
				}
				
			}else {
				if(dt.editarSinImgHistoria(in)) {
					response.sendRedirect("formstart.jsp?msj=1");
				}else {
					response.sendRedirect("formstart.jsp?error");
				}
			}
		}
			
	}

}

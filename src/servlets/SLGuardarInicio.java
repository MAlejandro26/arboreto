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
 * Servlet implementation class SLGuardarInicio
 */
@WebServlet(name="SLGuardarInicio", urlPatterns="/SLGuardarInicio")
@MultipartConfig
public class SLGuardarInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarInicio() {
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
			Inicio in = new Inicio();
			DTInicio dt = new DTInicio();
			
			String historia = request.getParameter("historia");
			String mision = request.getParameter("mision");
			String vision = request.getParameter("vision");
			Part partH = request.getPart("hFoto");
			Part partV = request.getPart("vFoto");
			Part partM = request.getPart("mFoto");
			
			InputStream inH = partH.getInputStream();
			InputStream inM = partM.getInputStream();
			InputStream inV = partV.getInputStream();
			
			in.setHistoria(historia);
			in.setMision(mision);
			in.setVision(vision);
			
			if(dt.guardarInicio(in, inH, inM, inV)) {
				response.sendRedirect("index.jsp");
			}else {
				response.sendRedirect("index.jsp?error");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}

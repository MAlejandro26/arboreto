package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTPiePagina;
import entidades.PiePagina;

/**
 * Servlet implementation class SLGuardarPP
 */
@WebServlet("/SLGuardarPP")
public class SLGuardarPP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarPP() {
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
		
			PiePagina pp = new PiePagina();
			DTPiePagina dt = new DTPiePagina();
			
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			String email = request.getParameter("email");
			String ext = request.getParameter("ext");
			
			if(direccion.length()==0||telefono.length()==0||email.length()==0||ext.length()==0) {
				response.sendRedirect("formfooter.jsp?msj=error");
			}
			else {
				try {
					pp.setDireccion(direccion);
					pp.setEmail(email);
					pp.setTelefono(telefono);
					pp.setExt(ext);
					
					if(dt.guardarPP(pp)) {
						response.sendRedirect("formfooter.jsp?msj=1");
					}else {
						response.sendRedirect("formfooter.jsp?msj=error");
					}
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
	}

}

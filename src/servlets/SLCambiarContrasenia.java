package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class SLCambiarContrasenia
 */
@WebServlet("/SLCambiarContrasenia")
public class SLCambiarContrasenia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLCambiarContrasenia() {
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
		
		String usuario = request.getParameter("user");
		String passCon = request.getParameter("vpass");
		String passNue = request.getParameter("npass");
		
		Usuario u = new Usuario();
		DTUsuario dt = new DTUsuario();
		
		u.setUsuario(usuario);
		u.setContrasenia(passCon);
		
		
		if(dt.loginUsuario(u)) {
			
			if(dt.cambiarContrasenia(usuario, passNue)) {
				response.sendRedirect("login.jsp?msj=4");
			}else {
				response.sendRedirect("changepassword.jsp?msj=1");
			}
			
		}else {
			response.sendRedirect("changepassword.jsp?msj=2");
		}
		
	}

}

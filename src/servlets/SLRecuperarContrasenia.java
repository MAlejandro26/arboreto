package servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import datos.DTEnviarCambioPass;
import datos.DTUsuario;

/**
 * Servlet implementation class SLRecuperarContrasenia
 */
@WebServlet("/SLRecuperarContrasenia")
public class SLRecuperarContrasenia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLRecuperarContrasenia() {
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
		
		String email = request.getParameter("email");
		DTUsuario dt = new DTUsuario();
		DTEnviarCambioPass dte = new DTEnviarCambioPass();
		String password = RandomStringUtils.randomAlphanumeric(8);
		
		
		try {
			
			if(dt.validarEmail(email)) {
				
				if(dt.cambiarPass(email, password)) {
					if(dte.enviarCamPass(email, password)) {
						response.sendRedirect("login.jsp?msj=1");
					}else {
						response.sendRedirect("password.jsp?msj=4");
					}
					
					
				}else {
					response.sendRedirect("password.jsp?msj=2");
				}
				
			} else {
				response.sendRedirect("password.jsp?msj=3");
			}
			
			
		} catch (MessagingException | IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect("password.jsp?msj=1");
		}
		
		
	}

}

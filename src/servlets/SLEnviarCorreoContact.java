package servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTEnviarEmailContact;

/**
 * Servlet implementation class SLEnviarCorreoContact
 */
@WebServlet("/SLEnviarCorreoContact")
public class SLEnviarCorreoContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEnviarCorreoContact() {
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
		
		DTEnviarEmailContact dte = new DTEnviarEmailContact();
		
		String nombres = request.getParameter("nombre");
		String apellidos = request.getParameter("apellido");
		String email = request.getParameter("correo");
		String msj = request.getParameter("mensaje");
		
			try {
				if(dte.enviarEmailContact(nombres,apellidos,email,msj)){
					response.sendRedirect("contact.jsp?msj=1");
				}
				else {
					response.sendRedirect("contact.jsp?msj=2");
				}
			} catch (MessagingException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect("contact.jsp?msj=2");
			}
	}

}

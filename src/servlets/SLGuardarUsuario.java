package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTEnviarEmailUsuario;
import datos.DTRegion;
import datos.DTUsuario;
import entidades.Region;
import entidades.Usuario;

/**
 * Servlet implementation class SLGuardarUsuario
 */
@WebServlet("/SLGuardarUsuario")
public class SLGuardarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLGuardarUsuario() {
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
		try 
		{
			Usuario u = new Usuario();
			DTUsuario dt = new DTUsuario();
			DTEnviarEmailUsuario dte = new DTEnviarEmailUsuario();
			
			String nombres, apellidos, usuario, email, pass;
			int estado = 0;
			String idrol;
			
			nombres = request.getParameter("txtNombres");
			apellidos = request.getParameter("txtApellidos");
			usuario = request.getParameter("txtUsuario");
			email = request.getParameter("txtCorreo");
			pass = request.getParameter("txtPassword");
			idrol = request.getParameter("cmbRol").trim();
			int id = Integer.parseInt(idrol);
		
			u.setNombres(nombres);
			u.setApellidos(apellidos);
			u.setUsuario(usuario);
			u.setEmail(email);
			u.setContrasenia(pass);
			u.setIdRol(id);
			u.setEstado(estado);
			u.setCodV(dt.randomAlphaNumeric(10));

			if(dt.guardarUsuario(u))
			{
				if(dte.enviarEmailVerificacion(u.getUsuario(), u.getContrasenia(), u.getEmail(), u.getCodV())) {
					response.sendRedirect("usergestion.jsp?msj=1");
				}else {
					response.sendRedirect("usergestion.jsp?msj=error");
				}
				
			}
			else
			{
				response.sendRedirect("usergestion.jsp?error=1");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Usuario: Error al guardar el usuario " +e.getMessage());
			e.printStackTrace();
		}
	}

}

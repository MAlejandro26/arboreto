package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class SL_login
 */
@WebServlet("/SL_login")
public class SL_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_login() {
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
			
			Usuario u = new Usuario();
			Usuario u1 = new Usuario();
			DTUsuario dt = new DTUsuario();
			
			String usuario = request.getParameter("usuario");
			String contrasenia = request.getParameter("password");
			String codigoV = request.getParameter("codverificacion");
			
			System.out.println(usuario + contrasenia);
			
			u.setUsuario(usuario);
			u.setContrasenia(contrasenia);
			
			if(dt.loginUsuario(u)) {
				System.out.println("EL USUARIO ES CORRECTO");
				u1 = dt.dtGetUsuario(usuario);
				String user = u1.getUsuario();
				
				
				String rol = "";
				
				rol = String.valueOf(u1.getIdRol());
				
				HttpSession hts = request.getSession(true);
				//HttpSession hts1 = request.getSession(true);
				hts.setAttribute("login", user);
				hts.setAttribute("rol", rol);
				//hts1.setAttribute("loginRol", idrol);
				response.sendRedirect("management.jsp?msj=1");
			}else {
				if(dt.loginUsuario2(usuario,contrasenia,codigoV)) {
					u1 = dt.dtGetUsuario(usuario);
					String user = u1.getUsuario();
					String rol = "";
					rol = String.valueOf(u1.getIdRol());
					
					HttpSession hts = request.getSession(true);
					hts.setAttribute("login", user);
					hts.setAttribute("rol", rol);
					response.sendRedirect("management.jsp?msj=1");
				}else {
					response.sendRedirect("login.jsp?msj=2");
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("ERROR EN SL_LOGIN: "+e.getMessage());
		}
	}

}

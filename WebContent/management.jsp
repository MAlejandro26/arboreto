<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, vistas.*, java.util.*"%>
<% String varMsj = request.getParameter("msj")==null?"":request.getParameter("msj");%>

<%
   //Limpia la CACHE del navegador
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setDateHeader("Expires", -1);
      
	
	String loginUser = "";
	loginUser = (String)session.getAttribute("login");
	loginUser = loginUser==null?"":loginUser;
	
	if(loginUser.equals(""))
	{
		response.sendRedirect("login.jsp");
	}
	
%>
    <!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Arboreto Carmelo Palma</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed" style="background: #39603D;">
        <jsp:include page="components/mainMenu.jsp"></jsp:include>
        <jsp:include page="components/navBar.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    
                </main>
                <!-- Footer -->
					<jsp:include page="components/adminFooter.jsp"></jsp:include>
				<!-- Fin Footer -->
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js " crossorigin="anonymous "></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="js/simple-datatables-latest.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script src="plugins/jAlert/dist/jAlert.min.js"></script>
	    <script src="plugins/jAlert/dist/jAlert-functions.min.js"></script>
	    <script src="js/alertify.min.js" type="text/javascript"></script>
	    
	    <script>
         window.addEventListener('DOMContentLoaded', event => {

            // Toggle the side navigation
            const sidebarToggle = document.body.querySelector('#sidebarToggle');
            if (sidebarToggle) {
                // Uncomment Below to persist sidebar toggle between refreshes
                // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
                //     document.body.classList.toggle('sb-sidenav-toggled');
                // }
                sidebarToggle.addEventListener('click', event => {
                    event.preventDefault();
                    document.body.classList.toggle('sb-sidenav-toggled');
                    localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
                });
            }

        })
	    
        $(document).ready(function ()
        	    {
        	        var mensaje = "";
        	        mensaje = "<%=varMsj%>";
        	        
        	        if(mensaje == "1")
        	        {
             			alertify.success("Usuario autenticado correctamente");
        	        }
        	        if(mensaje == "9"){
        	        	alertify.alert('Alerta','No puede ingresar a esta p??gina, su rol no tiene permiso');
        	        }
        	        if(mensaje == "error")
        	        {
        	            alertify.alert('Alerta','Ha ocurrido un error. Intente nuevamente.');
        	        }
        	       
        	    });
         </script>
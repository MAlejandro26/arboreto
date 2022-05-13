<%@page import = "entidades.*, datos.*, java.util.*;"%>
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
 
 <% String varMsj = request.getParameter("msj")==null?"":request.getParameter("msj");%>
    <!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Inicio</title>
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
                    <%
			Inicio in = new Inicio();
			DTInicio dt = new DTInicio();
			
			in = dt.llenarInicio();
		%>

        <!-- Summary -->
        

    <!--Form-->
    <div class="container">
        <header class="text-center text-white">
            <script src="https://kit.fontawesome.com/a41f4b8198.js" crossorigin="anonymous"></script>
        </header>
        
        <div class="row py-3">
            <div class="col-lg-10 mx-auto mt-5">
                <div class="card rounded shadow border-0">
					<div class="container mb-5">
            <h1 class="my-4">Nuestra historia</h1>
            <hr class="bg-dark w-25 ml-0">
            <div class="row">
                <div class="col-md-5 mt-7">
                    <img class="img-fluid rounded-3"
                        src="<%=in.getFotoHistoria() %>"
                        alt="trees">
                </div>
                <div class="col-md-7">
                    <h2 class="my-3">Historia</h2>
                    <p class="paragraph"><%=in.getHistoria() %></p>
                </div>
            </div>
        </div>
        <!-- End of Summary -->

        <!-- Mision -->
        <div class="container mb-5">
            <h1 class="my-4">Misión</h1>
            <hr class="bg-dark w-25 ml-0">
            <div class="row">
                <div class="col-md-7">
                    <p class="paragraph">
                        <%=in.getMision() %>
                    </p>
                </div>
                <div class="col-md-5">
                    <img class="img-fluid rounded-3"
                        src="<%=in.getFotoMision() %>"
                        alt="">
                </div>
            </div>
        </div>
        
        <div class="container mb-5">
            <h1 class="my-4">Visión</h1>
            <hr class="bg-dark w-25 ml-0">
            <div class="row">
                <div class="col-md-5 mt-7">
                    <img class="img-fluid rounded-3"
                        src="<%=in.getFotoVision() %>"
                        alt="Card image cap">
                </div>
                <div class="col-md-7">
                    <p class="paragraph">
                        <%=in.getVision() %>
                    </p>
                </div>
            </div>
            
    </div>
    <div class="container mb-3">
                <a href="edithistory.jsp" type="button" class="btn btn-primary" style="width: 33%;">Editar Historia</a>
                <a href="editmision.jsp" type="button" class="btn btn-primary" style="width: 33%;">Editar Misión</a>
                <a href="editvision.jsp" type="button" class="btn btn-primary" style="width: 33%;">Editar Visión</a>
            </div>
    </div>
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
             			alertify.success("Se actualizó correctamente la Historia");
        	        }
        	        if(mensaje == "2")
        	        {
             			alertify.success("Se actualizó correctamente la Misión");
        	        }
        	        if(mensaje == "3")
        	        {
        	            alertify.success('Se actualizó correctamente la Visión');
        	        }
        	        if(mensaje == "error")
        	        {
        	            alertify.alert('Alerta','Se produjo un error. Intente nuevamente');
        	        }
        	       
        	    });
         </script>
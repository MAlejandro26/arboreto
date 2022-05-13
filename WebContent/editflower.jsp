<%@page import="entidades.*, datos.*, java.util.*" %>
<%
 	
	String id = request.getParameter("id")==null?"":request.getParameter("id");
	int idF = Integer.parseInt(id);
	
	DTFlor dt = new DTFlor();
	Flor f = dt.obtenerFlor(idF);
	
	
	
%>
<%
    //Limpia la CACHE del navegador
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-store");
	    response.setDateHeader("Expires", 0);
	    response.setDateHeader("Expires", -1);
	      
		
	    String loginUser = "";
		loginUser = (String)session.getAttribute("login");
		loginUser = loginUser==null?"":loginUser;
		
		Opciones op = new Opciones();
		DTOpciones dtpo = new DTOpciones();
		ArrayList<Opciones> listarOp = new ArrayList<Opciones>();
		String code = "";
		
		String rol = "";
		rol = (String)session.getAttribute("rol");
		rol = rol==null?"":rol;
		
		if(loginUser.equals("") || rol.equals(""))
		{
			response.sendRedirect("login.jsp");
		}else{
			int rolUser = Integer.parseInt(rol);
			
			listarOp = dtpo.listarOpciones(rolUser);
			

			for(Opciones o: listarOp){
				if(o.getNombre().equals("Crear")){
					code+="1";
				}
				if(o.getNombre().equals("Editar")){
					code+="2";
				}
				if(o.getNombre().equals("Eliminar")){
					code+="3";
				}
			}
			
			if(!code.contains("2")){
				response.sendRedirect("management.jsp?msj=9");
			}
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
        <title>Crear Flor</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body onload="load();" class="sb-nav-fixed" style="background: #39603D;">
        <jsp:include page="components/mainMenu.jsp"></jsp:include>
        <jsp:include page="components/navBar.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
        <div class="row py-3">
            <div class="col-lg-10 mx-auto mt-5">
                <div class="card rounded shadow border-0">
                    <div class="card-header">
                        <h2>
                            Flor
                        </h2>
                    </div>
                    <div class="card-body bg-white rounded">
                        <form action="SLEditarFlor" method="Post" role="form">
                        	<input hidden="true" value="<%=f.getIdFlor()%>" name="id">
                            <div class="form-group">
                                <label>Nombre Común:</label>
                                <input value="<%=f.getNombreComun() %>" name="nombreCo" class="form-control" minlength="1" maxlength="50" required>
                                <small id= "mensaje" style="color:red"></small>
                            </div>
                            <div class="form-group">
                                <label>Nombre Cientifico:</label>
                                <textarea name="nombreCi" class="form-control" rows="3" minlength="1" maxlength="50" required><%=f.getNombreCientifico() %></textarea>
  								<small id= "mensaje1" style="color:red"></small>
                            	
                            </div>
                            <div class="form-group">
                                <label>Descripción:</label>
                                <textarea id="descripcion" name="descripcion" class="form-control" rows="3" hidden="true"></textarea>
                                <textarea id="descripcion1" name="descripcion1" class="form-control" rows="3" minlength="1" maxlength=180" required></textarea>
							 	<small id= "mensaje2" style="color:red"></small>                           
                            </div> 
                            <div class="form-group">
                                <label>Temporada de Floración:</label>
                                <textarea id="temporadaF" name="temporadaF" class="form-control" rows="3" minlength="1" maxlength="40" required><%=f.getTemporadaFloracion() %></textarea>
                            	<small id= "mensaje3" style="color:red"></small>
                            </div>
                            <div class="mb-3">
                                <button id="btn" class="btn btn-primary" style="width: 100%;">Editar</button>
                            </div>
                            <div style="text-align:center;"><a href="flowergestion.jsp"><i
                                        class="fas fa-undo"></i>&nbsp;Volver a la tabla</a></div>
                        </form>
                    </div>
                </div>
            </div>
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
         </script>
         <script type="text/javascript">
			$(function()
					{
						$("#btn").click(function(){
			    			textarea = $("#descripcion1").val();
			    			textarea_line = textarea.replace(new RegExp("\n","g"), "<br>");
			    			$("#descripcion").html(textarea_line);
			   			});
					});
			
			function load(){
				var descripcion = "<%=f.getDescripcion()%>";
				var desp = descripcion.replaceAll("<br>", ("\n"));
				$("#descripcion1").html(desp);
			}
		</script>
		
		<script>
        $('#nombreCo').on("keydown", function(e) {
	        var textLength = $('#nombreCo').val().replace(' ', '1').length + 1;
	        var maxValue = 50;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#nombreCo').on("keyup", function(e) {
	        var textLength = $('#nombreCo').val().replace(' ', '1').length;
	        var maxValue = 50;

	        $("#mensaje").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
	    
        $('#nombreCi').on("keydown", function(e) {
	        var textLength = $('#nombreCi').val().replace(' ', '1').length + 1;
	        var maxValue = 50;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#nombreCi').on("keyup", function(e) {
	        var textLength = $('#nombreCi').val().replace(' ', '1').length;
	        var maxValue = 50;

	        $("#mensaje1").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
	    
        $('#descripcion1').on("keydown", function(e) {
	        var textLength = $('#descripcion1').val().replace(' ', '1').length + 1;
	        var maxValue = 180;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#descripcion1').on("keyup", function(e) {
	        var textLength = $('#descripcion1').val().replace(' ', '1').length;
	        var maxValue = 180;

	        $("#mensaje2").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
	    
        $('#temporadaF').on("keydown", function(e) {
	        var textLength = $('#temporadaF').val().replace(' ', '1').length + 1;
	        var maxValue = 40;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#temporadaF').on("keyup", function(e) {
	        var textLength = $('#temporadaF').val().replace(' ', '1').length;
	        var maxValue = 40;

	        $("#mensaje3").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
		
		</script>
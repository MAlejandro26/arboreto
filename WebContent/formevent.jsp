<%@page import="entidades.*, datos.*, java.util.*"%>
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
		
		if(!code.contains("1")){
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
        <title>Crear Evento</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed" style="background: #39603D;">
        <jsp:include page="components/mainMenu.jsp"></jsp:include>
        <jsp:include page="components/navBar.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
        <header class="text-center text-white">
            <script src="https://kit.fontawesome.com/a41f4b8198.js" crossorigin="anonymous"></script>
        </header>
        <div class="row py-3">
            <div class="col-lg-10 mx-auto mt-3">
                <div class="card rounded shadow border-0">

                    <div class="card-header">
                        <h2>Evento</h2>
                    </div>
                    <div class="card-body bg-white rounded">
                        <form action="SLGuardarEvento" method="post">
                            <div class="form-group">
                                <label for="formGroupExampleInput">Nombre:</label>
                                <input id="nombre" name="nombre" type="text" class="form-control" id="formGroupExampleInput" minlength="1" maxlength="100" required>
                            	<small id= "mensaje" style="color:red"></small>
                            </div>

                            <div class="form-group">
                                <div class="form-group">
                                    <label>Descripción:</label>
                                    <textarea id="descripcion1" name="descripcion1" class="form-control" rows="3" minlength="1" maxlength="260" required></textarea>
                                    <textarea id="descripcion" name="descripcion" class="form-control" rows="3" hidden="true"></textarea>
                                	<small id= "mensaje1" style="color:red"></small>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="formGroupExampleInput">Fecha Inicio:</label>
                                <input name="fechaInicio" type="date" class="form-control" id="formGroupExampleInput" required>
                            </div>

                            <div class="form-group">
                                <label for="formGroupExampleInput">Fecha Final:</label>
                                <input name="fechaFin" type="date" class="form-control" id="formGroupExampleInput" required>
                            </div>

                            <div class="form-group">

                                <div class="form-group">
                                    <label for="formGroupExampleInput">Tipo de Evento</label>
                                    <select name="tipoEvento" class="form-control" required>
                                     	<option value="" selected disabled>Seleccionar...</option>
                                        <option value="Agenda Visible">Agenda Pública</option>
                                        <option value="Agenda Privada">Agenda Privada</option>
                                    </select>
                                </div>


                            </div>

                            <div class="form-group ">
                                <label for="formGroupExampleInput ">Ubicación</label>
                                <input id="ubicacion" name="ubicacion" type="text" class="form-control " id="formGroupExampleInput " minlength="1" maxlength="170" required>
                            	<small id= "mensaje2" style="color:red"></small>
                            </div>

                            <div class="form-group ">
                                <label for="formGroupExampleInput ">Hipervinculo</label>
                                <input id="hipervinculo" name="hipervinculo" type="url" class="form-control " id="formGroupExampleInput " minlength="1" maxlength="170" required>
                            	<small id= "mensaje3" style="color:red"></small>
                            </div>
                            <div class="mb-3">
                                <button id="btn" type="submit" class="btn btn-primary" style="width: 100%;">Guardar</button>
                            </div>
                        </form>

                        <div style="text-align: center; "><a href="eventgestion.jsp"><i
                                    class="fas fa-undo "></i>&nbsp;Volver a la tabla</div>
                    </div>
                </div>
            </div>
            <!--termina formulario-->
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
	        $('#nombre').on("keydown", function(e) {
		        var textLength = $('#nombre').val().replace(' ', '1').length + 1;
		        var maxValue = 100;
		        
		        console.log(e.keyCode);
		        if (textLength > maxValue) {
					if(e.keyCode != 8){
					e.preventDefault();
					}                     	
		        }
		     });
		    $('#nombre').on("keyup", function(e) {
		        var textLength = $('#nombre').val().replace(' ', '1').length;
		        var maxValue = 100;
		        $("#mensaje").text(textLength+" de "+maxValue+" carácteres permitidos");
		       
		    });
		    
	        $('#descripcion1').on("keydown", function(e) {
		        var textLength = $('#descripcion1').val().replace(' ', '1').length + 1;
		        var maxValue = 260;
		        
		        console.log(e.keyCode);
		        if (textLength > maxValue) {
					if(e.keyCode != 8){
					e.preventDefault();
					}                     	
		        }
		     });
		    $('#descripcion1').on("keyup", function(e) {
		        var textLength = $('#descripcion1').val().replace(' ', '1').length;
		        var maxValue = 260;
		        $("#mensaje1").text(textLength+" de "+maxValue+" carácteres permitidos");
		       
		    });
		    
	        $('#ubicacion').on("keydown", function(e) {
		        var textLength = $('#ubicacion').val().replace(' ', '1').length + 1;
		        var maxValue = 170;
		        
		        console.log(e.keyCode);
		        if (textLength > maxValue) {
					if(e.keyCode != 8){
					e.preventDefault();
					}                     	
		        }
		     });
		    $('#ubicacion').on("keyup", function(e) {
		        var textLength = $('#ubicacion').val().replace(' ', '1').length;
		        var maxValue = 170;
		        $("#mensaje2").text(textLength+" de "+maxValue+" carácteres permitidos");
		       
		    });
		    
	        $('#hipervinvulo').on("keydown", function(e) {
		        var textLength = $('#hipervinvulo').val().replace(' ', '1').length + 1;
		        var maxValue = 170;
		        
		        console.log(e.keyCode);
		        if (textLength > maxValue) {
					if(e.keyCode != 8){
					e.preventDefault();
					}                     	
		        }
		     });
		    $('#hipervinvulo').on("keyup", function(e) {
		        var textLength = $('#hipervinvulo').val().replace(' ', '1').length;
		        var maxValue = 170;
		        $("#mensaje3").text(textLength+" de "+maxValue+" carácteres permitidos");
		       
		    });
		</script>
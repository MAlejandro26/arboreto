<%@page import="entidades.*, datos.*, vistas.*,java.util.*" %>
<%
	ArrayList<Genero> listarG = new ArrayList<Genero>();
	DTGenero dtg = new DTGenero();
	listarG = dtg.listarGenero();
	
	ArrayList<Familiar> listarFa = new ArrayList<Familiar>();
	DTFamilia dt = new DTFamilia();
	listarFa = dt.listarFamilia();
	
	ArrayList<Distribucion> listarD = new ArrayList<Distribucion>();
	DTDistribucion dtd = new DTDistribucion();
	listarD = dtd.listarDistribucion();
	
	ArrayList<Flor> listarFl = new ArrayList<Flor>();
	DTFlor dtf = new DTFlor();
	listarFl = dtf.listarFlor();
	
	String id = request.getParameter("id")==null?"":request.getParameter("id");
	int idT = Integer.parseInt(id);
	
	DTArbol dtt = new DTArbol();
	Arbol a = dtt.buscarArbol(idT);
	
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
        <title>Crear ?rbol</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body onload="load();" class="sb-nav-fixed" style="background: #39603D;">
        <jsp:include page="components/mainMenu.jsp"></jsp:include>
        <jsp:include page="components/navBar.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container py-1">
        <div class="row py-5">
            <div class="col-lg-10 mx-auto mt-5">
                <div class="card rounded shadow border-0">

                    <div class="card-header">
                        <h2>
                            Editar ?rbol
                        </h2>

                    </div>
                    <div class="card-body bg-white rounded">


                        <form action="SLEditarArbol" method="Post" enctype="multipart/form-data">
                            <input hidden="true" name="id" id="id" value="<%=a.getId()%>">
                            <input hidden="true" value="false" id="cambio" name="cambio">
                            <div class="form-group">
                                <label>Nombre com?n:</label>
                                <input value="<%=a.getNombreComun() %>" name="nombreCo" id="nombreCo" class="form-control" minlength="1" maxlength="100" required>
								<small id= "mensaje" style="color:red"></small>
                            </div>
                            <div class="form-group">
                                <label>Nombre cient?fico:</label>
                                <input value="<%=a.getNombreCientifico() %>" name="nombreCi" id="nombreCi" class="form-control" minlength="1" maxlength="100" required>
								<small id= "mensaje1" style="color:red"></small>
                            </div>

                            <div class="form-group">
                                <label>Descripci?n:</label>
                                <textarea name="descripcion1" id="descripcion1" class="form-control" rows="3" minlength="10" maxlength="260" required></textarea>
                                <textarea name="descripcion" id="descripcion" class="form-control" rows="3" hidden="true"></textarea>
                            	<small id= "mensaje2" style="color:red"></small>
                            </div>
                            <div class="form-group">
                                <label>G?nero del ?rbol:</label>
                                <option value="" selected disabled>Seleccionar...</option>
                                <select name="genero" id="genero" class="form-control" required>
                                	<%
                                		for(Genero g: listarG){
                                			if(a.getIdGenero()==g.getIdGenero()){
                                	%>
                                    			<option selected="true" value="<%=g.getIdGenero()%>"><%=g.getNombre() %></option>
                                    	<%
                                			}else{
                                    	%>
                                    			<option value="<%=g.getIdGenero()%>"><%=g.getNombre() %></option>
                                   	<%
                                			}
                                		}
                                   	%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Familia del ?rbol:</label>
                                <option value="" selected disabled>Seleccionar...</option>
                                <select name="familia" id="familia" class="form-control" required>
                                	<%
                                		for(Familiar f: listarFa){
                                			if(a.getIdFamilia()==f.getIdFamilia()){
                                	%>
                                    			<option selected="true" value="<%=f.getIdFamilia()%>"><%=f.getNombre() %></option>
                                    	<%
                                			}else{
                                    	%>
                                    			<option value="<%=f.getIdFamilia()%>"><%=f.getNombre() %></option>
                                    <%
                                			}
                                		}
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Floracion del ?rbol:</label>
                                <option value="" selected disabled>Seleccionar...</option>
                                <select name="flor" id="flor" class="form-control" required>
                                	<%
                                		for(Flor fl: listarFl){
                                			if(a.getIdFlor()==fl.getIdFlor()){
                                	%>
                                    			<option selected="true" value="<%=fl.getIdFlor()%>"><%=fl.getNombreComun() %> : <%=fl.getTemporadaFloracion() %> </option>
                                    	<%
                                			}else{
                                    	%>
                                    			<option value="<%=fl.getIdFlor()%>"><%=fl.getNombreComun() %> : <%=fl.getTemporadaFloracion() %> </option>
                                    <%
                                			}
                                		}
                                	%>
                                </select>
                            </div>


                            <div class="form-group">
                                <label>Distribuci?n del ?rbol:</label>
                                <option value="" selected disabled>Seleccionar...</option>
                                <select name="distribucion" id="distribucion" class="form-control" required>
                                	<%
                                		for(Distribucion d: listarD){
                                			if(a.getIdDistribucion()==d.getIdDistribucion()){
                                	%>
                                				<option selected="true" value="<%=d.getIdDistribucion()%>"><%=d.getNombre() %></option>
                                		<%
                                			}else{
                                    	%>
                                    			<option value="<%=d.getIdDistribucion()%>"><%=d.getNombre() %></option>
                                    <%
                                			}
                                		}
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="custom-file">Imagen:</label>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Subir</span>
                                    </div>
                                    <div class="custom-file">
                                        <input name="imagen" type="file" class="custom-file-input" id="inputGroupFile01" onchange="readUrl(this);">
                                        <label class="custom-file-label" for="inputGroupFile01">Seleccionar el
                                            archivo</label>
                                    </div>
                                </div>
                                <div class="text-center">
                                	<img class="rounded img-fluid" alt="nose" src="<%=a.getImg() %>" name="foto" id="foto">
                                </div>
                            </div>
                            <div class="mb-3">
                                <button id="btn" class="btn btn-primary" style="width: 100%;">Editar</button>
                            </div>
                            <div style="text-align:center;"><a href="treegestion.jsp"><i
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
		
    	function readUrl(input) {
			if(input.files && input.files[0]){
				var reader = new FileReader();
				
				reader.onload = function (e) {
					$('#foto')
						.attr('src', e.target.result);
				};
				reader.readAsDataURL(input.files[0]);
				var inputNombre = document.getElementById('cambio');
	    	    inputNombre.value = "true";
			}
		}	
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
	    		var descripcion = "<%=a.getDescripcion()%>";
				var desp = descripcion.replaceAll("<br>", ("\n"));
				$("#descripcion1").html(desp);
	    	}
	</script>
	
		<script>
        $('#nombreCo').on("keydown", function(e) {
	        var textLength = $('#nombreCo').val().replace(' ', '1').length + 1;
	        var maxValue = 100;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
        
	    $('#nombreCo').on("keyup", function(e) {
	        var textLength = $('#nombreCo').val().replace(' ', '1').length;
	        var maxValue = 100;

	        $("#mensaje").text(textLength+" de "+maxValue+" car?cteres permitidos");
	       
	    });
	    
        $('#nombreCi').on("keydown", function(e) {
	        var textLength = $('#nombreCi').val().replace(' ', '1').length + 1;
	        var maxValue = 100;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#nombreCi').on("keyup", function(e) {
	        var textLength = $('#nombreCi').val().replace(' ', '1').length;
	        var maxValue = 100;

	        $("#mensaje1").text(textLength+" de "+maxValue+" car?cteres permitidos");
	       
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

	        $("#mensaje2").text(textLength+" de "+maxValue+" car?cteres permitidos");
	       
	    });
		
		</script>
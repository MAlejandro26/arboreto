<%@page import="entidades.*" %>
<%@page import="datos.*" %>
<%@page import="java.util.*" %>
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
        <title>Crear Regi?n</title>
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
        <div class="row py-3">
            <div class="col-lg-10 mx-auto mt-5">
                <div class="card rounded shadow border-0">

                    <div class="card-header">
                        <h2>
                            Regi?n
                        </h2>

                    </div>
                    <div class="card-body bg-white rounded">
                        <form action="SLGuardarRegion" method="Post">
                            <div class="form-group">
                                <label>Nombre de la regi?n:</label>
                                <input id="nombreR" name="nombreR" class="form-control">
                            </div>
                            <div class="form-group">
                            	<label>Descripci?n: </label>
                            	<textarea id="descripcion" name="descripcionR" class="form-control" rows="3" hidden="true"></textarea>
                            	<textarea id="descripcion1" name="descripcionR1" class="form-control" rows="3"></textarea>
                            </div>
                            
                            <%
	                            DTPais dtp = new DTPais();
								ArrayList<Pais> listaPaises = new ArrayList<Pais>();
	
								listaPaises = dtp.listarPaises();
                            %>
                            <div class="form-group">
                                <label>Pa?s:</label>
                                <select id="pais" name="pais" class="form-control">
                                
                                <%for (Pais p : listaPaises) { %>
                                    <option value=" <%= p.getIdPais() %> "><%= p.getNombre()%></option>
                                <% } %>
                                </select>
                            </div>
                               

                            <div class="mb-3">
                                <button id="btn" class="btn btn-primary" style="width: 100%;">Guardar</button>
                            </div>
                            <div style="text-align:center;"><a href="regiongestion.jsp"><i
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
		$(function()
				{
					$("#btn").click(function(){
		    			textarea = $("#descripcion1").val();
		    			textarea_line = textarea.replace(new RegExp("\n","g"), "<br>");
		    			$("#descripcion").html(textarea_line);
		   			});
				});
		</script>
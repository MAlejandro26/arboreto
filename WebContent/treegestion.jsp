<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, vistas.*, java.util.*" %>
<%
	ArrayList<Vista_arbol> listarArbol = new ArrayList<Vista_arbol>();
	DTVista_arbol dt = new DTVista_arbol();
	listarArbol = dt.listarAR();
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
        <title>Árbol</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="plugins/jAlert/dist/jAlert.css">
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.datatables.net/responsive/2.2.9/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css">
        
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed" style="background: #39603D;">
        <jsp:include page="components/mainMenu.jsp"></jsp:include>
        <jsp:include page="components/navBar.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4 mb-4" style="color:white">Árbol</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                
                                <h3>Tabla Árbol</h3>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered" id="example1">
                                <div class="dropdown" align="right">
							        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
							            <span class="glyphicon glyphicon-download-alt" aria-hidden="true" style="float: left;  color: white"></span>
							            &nbsp;
							            Exportar a
							            <span class="caret"></span>
							        </button>
							        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							            <li><a class="dropdown-item" target="_blank" href="SLArbolReport?export=PDF">PDF</a></li>
							            <li><a class="dropdown-item" href="#">Excel</a></li>
							            <li><a class="dropdown-item" href="#">Word</a></li>
							
							        </ul>
							        
							    </div>
							    &nbsp;
                                	<div style="text-align:right;"><a class="disabled" href="formtrees.jsp"><i class="fas fa-plus-square"></i>&nbsp; Nueva Árbol</div>

                                <thead>
                                    <tr>
                                    	<th></th>
                                        <th>Nombre común</th>
                                        <th>Nombre científico</th>
                                        <th>Descripción</th>
                                        <th>Genero</th>
                                        <th>Familia</th>
                                        <th>Floración</th>
                                        <th>Distribución</th>
                                        <th></th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    <th></th>
                                        <th>Nombre común</th>
                                        <th>Nombre científico</th>
                                        <th>Descripción</th>
                                        <th>Genero</th>
                                        <th>Familia</th>
                                        <th>Floración</th>
                                        <th>Distribución</th>
                                        <th>Imagen</th>
                                        <th>Opciones</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                <%
					           		for(Vista_arbol u: listarArbol){
					           	%>
                                    <tr>
                                    <td></td>
                                        <td><%=u.getNombreComun() %></td>
                                        <td><%=u.getNombreCientifico() %></td>
                                        <td><%=u.getDescripcion() %></td>
                                        <td><%=u.getNombre_Genero() %></td>
                                        <td><%=u.getNombre_Familia() %></td>
                                        <td><%=u.getNombre_Flor() %></td>
                                        <td><%=u.getNombre_Distribucion() %></td>
                                        <td><img alt="Arbol" src="<%=u.getFoto()%>" width="200px" height="200px" class="img-thumbnail"></td>
                                        <td>&nbsp;&nbsp;<a class="disabled2" href="edittree.jsp?id=<%=u.getId()%>"><i
                                                    class="fas fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
                                                class="disabled1" href="#" onclick="myDeleteTree(<%=u.getId() %>)"><i class="far fa-trash-alt"></i></td>
                                    </tr>
                                <%
					           		}
                                %>
                                
                                </tbody>
                                </table>
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
	    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
	    <script src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"></script>
	    
	    
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
      <script>
	
       function myDeleteTree(idArbol)
       {
       	$.fn.jAlert.defaults.confirmQuestion = '¿Estás Seguro?';
       	$.fn.jAlert.defaults.confirmBtnText = 'Si';
           confirm(function(e, btn){
               e.preventDefault();
               window.location.href = "SLEliminarArbol?id="+idArbol
           },
           function(e,btn){
               e.preventDefault();
           });
       }
       
       function exportTree(typeExport){
    	   console.log(typeExport);
    	   window.location.href = "SLArbolReport?export="+typeExport
       }
       
       $(document).ready(function ()
       	    {
       	        var mensaje = "";
       	        mensaje = "<%=varMsj%>";
       	        
       	        if(mensaje == "1")
       	        {
            			alertify.success("Árbol registrado");
       	        }
       	        if(mensaje == "3")
       	        {
            			alertify.success("Árbol actualizado");
       	        }
       	        if(mensaje == "5")
       	        {
       	            alertify.error('Se elimino correctamente');
       	        }
       	        if(mensaje == "error")
       	        {
       	            alertify.alert('Alerta','Ha ocurrido un error. Intente nuevamente.');
       	        }
       	       
       	    });

	</script>
   <script type="text/javascript">
	    $(function () {
        	$("#example1").DataTable({
      	      "responsive": true, "lengthChange": false, "autoWidth": false,
      	      "language": {    	
      		      "search": "Buscar:",
      		      "zeroRecords": "No hay registros disponibles.",
      		      "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
      		      "infoEmpty": "Mostrando 0 de 0 registros",
      		      paginate: {
      		            previous: 'Atrás',
      		            next:     'Siguiente'
      		        }
      	      }
      }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
            $('#example2').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
        });
	    </script>
	    
	    <script type="text/javascript">
		    var code = "<%=code%>";
		    if(!code.includes("3")){
	        	$('.disabled1').css({'pointer-events':'none', 'cursor': 'not-allowed', 'color':'gray'});
	        }
	       	if(!code.includes("2")){
	        	$('.disabled2').css({'pointer-events':'none', 'cursor': 'not-allowed', 'color':'gray'});
	        }
	       	if(!code.includes("1")){
	        	$('.disabled').css({'pointer-events':'none', 'cursor': 'not-allowed', 'color':'gray'});
	        }
	    </script>
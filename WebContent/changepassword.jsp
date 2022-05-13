<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
	
	if(loginUser.equals(""))
	{
		response.sendRedirect("login.jsp");
	}
	
%>
    <%
    Calendar cal=Calendar.getInstance();
    int year=cal.get(Calendar.YEAR);
    %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Cambiar Contraseña</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
            crossorigin="anonymous"></script>
    </head>

    <body style="background-color:#001B36">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Cambiar contraseña</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="small mb-3 text-muted">Ingresa los siguientes datos</div>
                                        
                                        <form action="SLCambiarContrasenia" method="Post">
                                        <input name="user" value="<%=loginUser%>" type="hidden">
                                            <div class="form-group">
                                                <label class="small mb-1" >Contraseña actual:</label>
                                                <input class="form-control py-4" type="password" name="vpass"
   													id="myInput"
                                                    placeholder="Ingrese contraseña anterior" />
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1" >Contraseña nueva:</label>
                                                <input class="form-control py-4"  type="password" name="npass"
   													id="myInput1"
                                                    placeholder="Ingrese contraseña nueva" />
                                            </div>
                                            <div class="form-group">
                                            	<input type="checkbox" onclick="myFunction()"> Mostrar Contraseñas
                                            </div>
                                            <div
                                                class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small text-dark" href="management.jsp">Regresar</a>
                                                <button class="btn btn-primary bg-primary" type="submit">Cambiar
                                                    Contraseña</button>
                                                    
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted ">Copyright &copy; Arboreto Carmelo Palma <%=year %></div>
                            <div>
                                <a class="text-dark" href="#">Privacy Policy</a> &middot;
                                <a class="text-dark" href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>

    </html>
    
    <script>
	    function myFunction() {
	    	  var x = document.getElementById("myInput1");
	    	  if (x.type === "password") {
	    	    x.type = "text";
	    	  } else {
	    	    x.type = "password";
	    	  }
	    	  
	    	  var y = document.getElementById("myInput");
	    	  if (y.type === "password") {
	    	    y.type = "text";
	    	  } else {
	    	    y.type = "password";
	    	  }
	    	  
	    	  console.log("<%=loginUser%>");
	    	}
	    
	    
	    
    </script>
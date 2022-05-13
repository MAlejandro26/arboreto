<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">
    <% 
	String varMsj = request.getParameter("msj")==null?"":request.getParameter("msj");
%>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Recuperar contraseña</title>
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
            crossorigin="anonymous"></script>
    </head>

    <body class="" style="background-color:#001B36">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Recuperar contraseña</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="small mb-3 text-muted">Ingresa tu correo, el cual revisará una
                                            notificación para recuperar contraseña.</div>
                                        <form action="SLRecuperarContrasenia" method="Post">
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">Correo:</label>
                                                <input class="form-control py-4" id="inputEmailAddress" type="email" name="email"
                                                    aria-describedby="emailHelp"
                                                    placeholder="Ingrese correo electronico" required />
                                            </div>
                                            <div
                                                class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small text-dark" href="login.jsp">Regresar</a>
                                                <button class="btn btn-primary bg-primary" type="submit">Restaurar
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
                            <div class="text-muted ">Copyright &copy; Arboreto Carmelo Palma 2021</div>
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
        <script src="js/alertify.min.js" type="text/javascript"></script>
    </body>
        <script type="text/javascript">
	        $(document).ready(function ()
	        	    {
	        	        var mensaje = "";
	        	        mensaje = "<%=varMsj%>";
	        	        
	        	        if(mensaje == "3")
	        	        {
	        	            alertify.alert('Este correo no corresponde con un usuario');
	        	        }

	        	        
	        	    });
        </script>
    </html>
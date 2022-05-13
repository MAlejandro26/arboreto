<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <% String varMsj = request.getParameter("msj")==null?"":request.getParameter("msj");%>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contacto - Arboreto Carmelo Palma</title>
        
        <link href="css/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/default.min.css" rel="stylesheet" type="text/css"/>

        <!--CSS boostrap-->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/styles2.css">
        <link rel="stylesheet" href="css/styles.css">

        <!--font awesome-->
        <script src="https://kit.fontawesome.com/78a455df4c.js" crossorigin="anonymous"></script>

        <!-- Scrollbar -->
        <link rel="stylesheet" href="https://unpkg.com/simplebar@latest/dist/simplebar.css">
    </head>

    <body data-simplebar>
        <jsp:include page="components/mainHeader.jsp" />
        
        <!-- Contact -->
        <div class="container">
        
            <!-- Header -->
            <h1 class="my-4">
                Contacto
                <div class="float-end">
                    <img src="https://i.ibb.co/Kzs0FYc/correo-electronico-vacio-1.png" alt="image" />
                </div>
            </h1>

            <hr class="bg-dark">
            <!-- End of Header -->

            <div class="container contact">
                <div class="col-md-12">
                    <form id = "formContact" name = "formContact" class = "Contacto"  method="Post" action="./SLEnviarCorreoContact" onsubmit="return validar_campos()"> 
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="fname">Nombres:</label>
                            <div class="col-sm-10">
                                <input id="nombre" name="nombre" class="form-control" placeholder=""  minlength="3" maxlength="80">
                            	<small id= "mensaje" style="color:red"></small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="lname">Apellidos:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="apellido" placeholder="" name="apellido" minlength="3" maxlength="80">
                            	<small id= "mensaje1" style="color:red"></small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="email">Correo:</label>
                            <div class="col-sm-10">
                                <input id="correo" type="email" class="form-control" id="correo" placeholder="ejemplo@gmail.com" name="correo" minlength="3" maxlength="150">
                            	<small id= "mensaje2" style="color:red"></small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="Descripción">Mensaje:</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="5" id="mensaje" name="mensaje" minlength="3" maxlength="500"></textarea>
                            	<small id= "mensaje3" style="color:red"></small>
                            </div>
                        </div>
                        <div class="form-group"><br>
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Enviar</button>
                            </div>
                        </div>
                     </form>
                   </div>
              </div>
        </div>


        <!-- End of Contact -->

        <jsp:include page="components/mainFooter.jsp" />

        <!-- Javascript -->
        <link rel="stylesheet" href="js/bootstrap.min.js">
        <script defer src="./js/index.js"></script>

        <!-- BOOTSTRAP V.4 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>

        <!-- Scrollbar -->
        <script src="https://unpkg.com/simplebar@latest/dist/simplebar.js"></script>
        
        <script src="js/alertify.min.js" type="text/javascript"></script>
		
		<script>

	        function validar_campos(){
         		nom = document.formContact.nombre.value;
         		ape = document.formContact.apellido.value;
         		email = document.formContact.correo.value;
         		men = document.formContact.mensaje.value;
         		
         		if (nom.length == 0 || ape.length == 0 || email.length == 0 || men.length == 0){
         			alertify.alert("Alerta", "Tiene algunos campos vacios").set('label', 'Ok');
         			return false;
         			
         		}else{
         			alertify.success("Correo enviado exitosamente");
         			return true;
         		}
	         }
	        
        </script>
        
        <script>
        $('#nombre').on("keydown", function(e) {
	        var textLength = $('#nombre').val().replace(' ', '1').length + 1;
	        var maxValue = 80;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#nombre').on("keyup", function(e) {
	        var textLength = $('#nombre').val().replace(' ', '1').length;
	        var maxValue = 80;

	        $("#mensaje").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
	    
        $('#apellido').on("keydown", function(e) {
	        var textLength = $('#apellido').val().replace(' ', '1').length + 1;
	        var maxValue = 80;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#apellido').on("keyup", function(e) {
	        var textLength = $('#apellido').val().replace(' ', '1').length;
	        var maxValue = 80;

	        $("#mensaje1").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
	    
        $('#correo').on("keydown", function(e) {
	        var textLength = $('#correo').val().replace(' ', '1').length + 1;
	        var maxValue = 150;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#correo').on("keyup", function(e) {
	        var textLength = $('#correo').val().replace(' ', '1').length;
	        var maxValue = 150;

	        $("#mensaje2").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
	    
        $('#mensaje').on("keydown", function(e) {
	        var textLength = $('#mensaje').val().replace(' ', '1').length + 1;
	        var maxValue = 500;
	        
	        console.log(e.keyCode);
	        if (textLength > maxValue) {
				if(e.keyCode != 8){
				e.preventDefault();
				}                     	
	        }

	     });
	    $('#mensaje').on("keyup", function(e) {
	        var textLength = $('#mensaje').val().replace(' ', '1').length;
	        var maxValue = 500;

	        $("#mensaje3").text(textLength+" de "+maxValue+" carácteres permitidos");
	       
	    });
		
		</script>
    </body>


    </html>
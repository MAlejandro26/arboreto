<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, vistas.*, datos.*, java.util.*" %>
<%
	ArrayList<Vista_arbol> listarArbol = new ArrayList<Vista_arbol>();
	DTVista_arbol dt = new DTVista_arbol();
	listarArbol = dt.listarAR();
	String msj = "hidden";
	
	if(listarArbol.isEmpty()){
		msj = "";
		System.out.println("El ArrayList de Arboles esta vacio");
		
	}else{
		msj= "hidden";
		System.out.println("El ArrayList de Arboles esta lleno");
		
	}
%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Árboles - Arboreto Carmelo Palma</title>

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

    <body data-simplebar style="background-color:#fff">
        <jsp:include page="components/mainHeader.jsp" />

        <!-- Trees -->
        <div class="container">
            <!-- Header -->
            <h1 class="my-4">
                Árboles
                <div class="form-check form-switch float-right">
                    <input class="form-check-input fs-2 bg-success" type="checkbox" id="show-hide"
                        onchange="show_hide()" unchecked>
                </div>

            </h1>

            <hr class="bg-dark">
            <!-- End of Header -->
            
            

			<div class="col-md-7">
                  <h5 class="<%=msj%>">En este momento el catálogo de árboles se encuentra vacío.</h5>
            </div>
            <!-- Trees Cards -->
            <div id="cards-container" class="card-group">
            		<jsp:include page="components/treeCard.jsp"></jsp:include>
   		
            </div>
            
            <!-- End of Trees Cards -->
        </div>
        <!-- End of Trees -->

        <!-- Pagination -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center my-5">
                <li class="page-item"><a class="page-link" href="#">Anterior</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Siguiente</a></li>
            </ul>
        </nav>
        <!-- End of Pagination -->

        <!-- Footer -->
        <jsp:include page="components/mainFooter.jsp" />
        <!-- End of Footer -->

        <!-- Javascript -->
        <link rel="stylesheet" href="js/bootstrap.min.js">
        <script defer src="./js/index.js"></script>
        <!-- <script src="js/page/trees.js"></script> -->

        <!-- BOOTSTRAP V.4 -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
            integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
            integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
            crossorigin="anonymous"></script>

        <!-- Scrollbar -->
        <script src="https://unpkg.com/simplebar@latest/dist/simplebar.js"></script>
        
        <script type="text/javascript">
        var str = "Hello world!";
        var res = str.substring(0, 2);
        var final = res +"...";
        console.log(final);
        </script>

    </body>

    </html>
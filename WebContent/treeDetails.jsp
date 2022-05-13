<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, vistas.*, java.util.*"%>
<%
	String id = request.getParameter("idArbol")==null?"":request.getParameter("idArbol");
	int idT = Integer.parseInt(id);

	DTArbol dtt = new DTArbol();
	DetalleArbol a = dtt.buscarDetalleArbol(idT);

    
%>
<!DOCTYPE html>
<html>
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
<style>
	#nav-tabContent{
		border-radius: 0px 0px 5px 5px;
	}

</style>
<body data-simplebar style="background-color:#fff">

	<jsp:include page="components/mainHeader.jsp" />
		<div class="container my-4 mb-4">
            <!-- Header -->
            <h1 class="my-4">
                Árbol
            </h1>

            <hr class="bg-dark">
            <!-- End of Header -->

           <div class="card mb-3">
			  <div class="row g-0">
			    <div class="col-md-4">
			      <img src="<%=a.getImgArbol() %>" class="img-fluid rounded" alt="...">
			    </div>
			    <div class="col-md-8">
			      <div class="card-body">
			        <h3 class="card-title"><%=a.getNombreComunArbol() %></h3>
			        <h5 class="card-text"><%=a.getNombreCientificoArbol() %></h5>
			        <p class="card-text"><%=a.getDescripcionArbol() %></p>
			        
			      </div>
			      
			    </div>
			  </div>
			</div>
						
			
		</div>
		
		
		
		<div class="container mb-5" >
		<h4 class="my-4 text-muted">
                Información específica
            </h4>

            <hr class="bg-dark">
			<nav>
					  <div class="nav nav-tabs" id="nav-tab" role="tablist">
					    <button class="nav-link active" id="nav-familia-tab" data-bs-toggle="tab" data-bs-target="#nav-familia" type="button" role="tab" aria-controls="nav-familia" aria-selected="true">Familia</button>
					    <button class="nav-link" id="nav-genero-tab" data-bs-toggle="tab" data-bs-target="#nav-genero" type="button" role="tab" aria-controls="nav-genero" aria-selected="false">Género</button>
					    <button class="nav-link" id="nav-floracion-tab" data-bs-toggle="tab" data-bs-target="#nav-floracion" type="button" role="tab" aria-controls="nav-floracion" aria-selected="false">Floración</button>
					    <button class="nav-link" id="nav-distribucion-tab" data-bs-toggle="tab" data-bs-target="#nav-distribucion" type="button" role="tab" aria-controls="nav-distribucion" aria-selected="false">Distribución</button>
					  </div>
					</nav>
					<div class="tab-content p-3" id="nav-tabContent" style="background-color:white">
					  <div class="tab-pane fade show active" id="nav-familia" role="tabpanel" aria-labelledby="nav-familia-tab">
							<h5>Nombre:</h5>
					  		<p><%=a.getNombreFamilia() %></p>
					  		<h5>Descripción:</h5>
					  		<p><%=a.getDescripcionFamilia() %></p>
					  </div>
					  <div class="tab-pane fade" id="nav-genero" role="tabpanel" aria-labelledby="nav-genero-tab">
					  		<h5>Nombre:</h5>
					  		<p><%=a.getNombreGenero() %></p>
					  		<h5>Descripción:</h5>
					  		<p><%=a.getDescripcionGenero() %></p>
				
					  </div>
					  <div class="tab-pane fade" id="nav-floracion" role="tabpanel" aria-labelledby="nav-floracion-tab">
					  
					  		<h5>Nombre Comun:</h5>
					  		<p><%=a.getNombreComunFlor() %></p>
					  		<h5>Nombre Comun:</h5>
					  		<p><%=a.getNombreCientificoFlor() %></p>
					  		<h5>Descripción:</h5>
					  		<p><%=a.getDescripcionFlor() %></p>
					  		<h5>Temporada de Floración:</h5>
					  		<p><%=a.getTemporadaFloracion() %></p>
					  
					  </div>
					  <div class="tab-pane fade" id="nav-distribucion" role="tabpanel" aria-labelledby="nav-distribucion-tab">
					  
					  		<h5>Nombre:</h5>
					  		<p><%=a.getNombreDistribucion() %></p>
					  		<h5>Descripción:</h5>
					  		<p><%=a.getDescripcionDistribucion() %></p>
					  		<hr class="bg-dark">
					  		<h5>Nombre de Región:</h5>
					  		<p><%=a.getNombreRegion() %></p>
					  		<h5>Descripción:</h5>
					  		<p><%=a.getDescripcionRegion() %></p>
					  		<hr class="bg-dark">
					  		<h5>País:</h5>
					  		<p><%=a.getNombrePais() %></p>
					  </div>
					</div>
		</div>

	<jsp:include page="components/mainFooter.jsp" />
	
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
</body>
</html>
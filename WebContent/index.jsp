<%@page contentType="text/html" pageEncoding="UTF-8" import = "entidades.*, datos.*, java.util.*;"
    %>
    
    
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Página de inicio - Arboreto Carmelo Palma</title>

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
        
	       <%
                ArrayList<Banner> listBanner = new ArrayList<Banner>();
                DTBanner dtb = new DTBanner();
               	listBanner = dtb.listarBanner();                                	
            %>
		
        <!-- Banner -->
        <div id="mycarousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#mycarousel" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
                     <%
			 for (int counter = 1; counter < listBanner.size(); counter++) {
	  	 	%>
                 <button type="button" data-bs-target="#mycarousel" data-bs-slide-to="<%=counter %>>" aria-label="Slide <%=counter %>"></button>
                <%
			 }
        	 %>    
            </div>
            <div class="carousel-inner">
            <%
				String imagen1 = null;
				Banner bn1 = new Banner();
				bn1 = listBanner.get(0);
			%>
                <div class="carousel-item active">
                <img src="<%=bn1.getFoto() %>" class="d-block w-100 h-50 " alt="imagen 1">
                   <div class="carousel-caption d-none d-md-block">
			        <h3 class= "fw-light"><%=bn1.getTitulo() %></h3>
			         <h5  class= "fw-light"><%=bn1.getDescripcion()%></h5>
				   </div>
                </div>
                
                <%
				for (int counter = 1; counter < listBanner.size(); counter++) {
			    Banner bn = listBanner.get(counter);		   
            %>
            <div class="carousel-item">
                <img src="<%=bn.getFoto() %>" class="d-block w-100 h-50" alt="imagen <%=counter %>>">
                 <div class="carousel-caption d-none d-md-block">
			        <h3 class= "fw-light"><%=bn.getTitulo() %></h3>
					<h5 class= "fw-light"><%=bn.getDescripcion()%></h5>
		
				  </div>
            </div>
            <% }
            %>
            </div>
            <button class="carousel-control-prev " type="button" data-bs-target="#mycarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="false"></span>
                <span class="visually-hidden">Anterior</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#mycarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon position" aria-hidden="false"></span>
                <span class="visually-hidden">Siguiente</span>
            </button>
        </div>
        <!-- End of Banner -->

		<%
			Inicio in = new Inicio();
			DTInicio dt = new DTInicio();
			
			in = dt.llenarInicio();
		%>

        <!-- Summary -->
        <div class="container mb-5">
            <h1 class="my-4">Nuestra historia</h1>
            <hr class="bg-dark w-25 ml-0">
            <div class="row">
                <div class="col-md-5 mt-7">
                    <img class="img-fluid rounded-3"
                        src="<%=in.getFotoHistoria() %>"
                        alt="trees">
                </div>
                <div class="col-md-7">
                    <h2 class="my-3">Historia</h2>
                    <p class="paragraph"><%=in.getHistoria() %></p>
                </div>
            </div>
        </div>
        <!-- End of Summary -->

        <!-- Mision -->
        <div class="container mb-5">
            <h1 class="my-4">Misión</h1>
            <hr class="bg-dark w-25 ml-0">
            <div class="row">
                <div class="col-md-7">
                    <p class="paragraph">
                        <%=in.getMision() %>
                    </p>
                </div>
                <div class="col-md-5">
                    <img class="img-fluid rounded-3"
                        src="<%=in.getFotoMision() %>"
                        alt="">
                </div>
            </div>
        </div>
        <!-- End of Mision -->

        <!-- Vision -->
        <div class="container mb-5">
            <h1 class="my-4">Visión</h1>
            <hr class="bg-dark w-25 ml-0">
            <div class="row">
                <div class="col-md-5 mt-7">
                    <img class="img-fluid rounded-3"
                        src="<%=in.getFotoVision() %>"
                        alt="Card image cap">
                </div>
                <div class="col-md-7">
                    <p class="paragraph">
                        <%=in.getVision() %>
                    </p>
                </div>
            </div>
        </div>
        <!-- End of Vision -->

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
        
        
    </body>

    </html>
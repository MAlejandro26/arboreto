<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import= "entidades.*" %>
<%@page import= "datos.*"%>
<%@page import= "java.util.*"%>
    
    <%
    //Limpia la CACHE del navegador
	    response.setHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-store");
	    response.setDateHeader("Expires", 0);
	    response.setDateHeader("Expires", -1);
	      
		
		String loginUser = "";
		loginUser = (String)session.getAttribute("login");
		loginUser = loginUser==null?"":loginUser;
		String sesion, titulo, imagen = "";
		
		if(loginUser.equals(""))
		{
			sesion = "login.jsp";
			titulo = "Inicio sesión";
		}else{
		
			sesion = "management.jsp";
			titulo = "Administración";
		}
			
    %>
    <!-- Navbar -->
    <nav id="navbar" class="navbar navbar-expand-lg navbar-dark" style="background-color:#001B36">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">Arboreto Carmelo Palma</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="posts.jsp">Publicaciones</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="trees.jsp">Árboles</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="events.jsp">Eventos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="map.jsp">Mapa</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="services.jsp">Servicios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="products.jsp">Productos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="contact.jsp">Contactos</a>
                    </li>
                    <li class="nav-item">
                        <span title="<%=titulo%>"><a class="nav-link" href="<%=sesion%>"><%=titulo%></a></span>
                    </li>

            </div>
        </div>
    </nav>
    
    <!-- End of Navbar -->
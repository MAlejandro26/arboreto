<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, vistas.*, java.util.*"%>
<%
	ArrayList<Vista_arbol> listarArbol = new ArrayList<Vista_arbol>();
	DTVista_arbol dt = new DTVista_arbol();
	listarArbol = dt.listarAR();
	
    for(Vista_arbol u: listarArbol){
%>
    <div class="col-lg-4 mb-4">
        <div class="card border-dark rounded-3">
            <div class="figure">
                <img class="card-img-top"
                    src="<%=u.getFoto() %>"
                    alt="Abies alba">
            </div>

            <div class="card-body">
                <h2 class="card-title my-2">
                     <%=u.getNombreComun()%>
                </h2>
                <h5 class="card-subtitle my-2 mb-2">
                    <%=u.getNombreCientifico()%>
                </h5>
                
                <div class="details">
                <p class="card-text paragraph">
                    <%=u.getDescripcion()%>
                </p>
	                <ul class="list-group list-group-flush rounded-3">
	                    <li class="list-group-item"><b>Género: </b><%=u.getNombre_Genero() %></li>
	                    <li class="list-group-item"><b>Flor: </b><%=u.getNombre_Flor() %></li>
	                    <li class="list-group-item"><b>Familia: </b><%=u.getNombre_Familia() %></li>
	                    <li class="list-group-item"><b>Distribución: </b><%=u.getNombre_Distribucion() %></li>
	                </ul>
	            </div>
                <a href="treeDetails.jsp?idArbol=<%=u.getId() %>" class="btn btn-outline-primary">
                    Ver más
                </a>
            </div>
            
        </div>
    </div>
<%
	}
%>
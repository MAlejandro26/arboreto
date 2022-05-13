<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, java.util.*" %>
<%
	ArrayList<Publicacion> listarPu = new ArrayList<Publicacion>();
	DTPublicacion dt = new DTPublicacion();
	listarPu = dt.listarPublicacionV();
%>
    <div class="card-group">
            	<%
            		for(Publicacion u: listarPu){
            	%>
                <div class="col-lg-4 mb-4">
                    <div class="card border-dark rounded-3">
                        <div class="figure">
                            <img class="card-img-top" src="<%=u.getMultimedia() %>" alt="">
                        </div>
                        <div class="card-body">
                            <h2 class="card-title my-2">
                                <%=u.getTitulo() %>
                            </h2>
                            <p class="card-text paragraph">
                                <%=u.getDescripcion() %>
                            </p>
                            <p class="card-text paragraph">
                                <a href="<%=u.getHipervinculo()%>" class="btn btn-primary btn-lg" role="button" aria-disabled="true">Hipervinculo</a>
                            </p>
                            <a href="" class="btn btn-outline-primary">
                                Ver más
                            </a>
                        </div>
                        <div class="card-footer">Publicado el <a class="card-link" href=""><%=u.getFechaPublicacion()%></a>
                        </div>
                    </div>
                </div>
                <%
                	}
                %>
                
        </div>
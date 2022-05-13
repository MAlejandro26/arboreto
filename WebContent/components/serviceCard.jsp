<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, java.util.*"%>
<%
	ArrayList<Servicios> listarServicios = new ArrayList<Servicios>();
	DTServicio dt = new DTServicio();
	listarServicios = dt.listarServiciosV();

	for(Servicios u: listarServicios){
%>
    <div class="row mb-3">
        <div class="col-md-6">
            <a href="">
                <div class="larger-figure">
                    <img class="card-img-top rounded-3" src="<%=u.getFoto() %>" alt="">
                </div>
            </a>
        </div>
        <div class="col-md-6">
            <h3 class="my-2">
                <%=u.getNombre() %>
            </h3>
            <p class="paragraph">
                <%=u.getDescripcion() %>
            </p>
            <a href="contact.jsp" class="btn btn-primary mb-2">Contactar</a>
        </div>
    </div>
<%
	}
%>
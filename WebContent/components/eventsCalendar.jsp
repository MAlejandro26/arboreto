<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, java.util.*" %>
<% 
	ArrayList<Eventos> listarEventos = new ArrayList<Eventos>();
	DTEvento dt = new DTEvento();
	listarEventos = dt.listarEventosV();
%>
<script>
  	var fecha = "05/11/2021";
  	$("#calendar").evoCalendar({
  	  theme: "Royal Navy",
  	  language: "es",
  	  format:"mm/dd/yyyy",
  	  titleFormat: "MM yyyy",
  	  firstDayOfWeek: "1",
  	  calendarEvents: [
 		  <%
 			for(Eventos u: listarEventos){
 		  %>
  		{
 	      id: "<%=u.getIdEvento()%>", // Event's ID (required)
 	      name: "<%=u.getNombre()%>", // Event name (required)
 	      date: ["<%=u.getFechaInicio()%>","<%=u.getFechaFin()%>"], // Event date (required)
 	      type: "event",
 	      description: "Descripcion: <%=u.getDescripcion()%> <br><br>Ubicación: <%=u.getUbicacion()%>",// Event type (required)
 	      link: "<%=u.getHipervinculo()%>",
 	      //everyYear: true, // Same event every year (optional)
 	    },
 		  <%
 		  	}
 		  %>
 		  /*
  	    {
  	      id: "bHay68s", // Event's ID (required)
  	      name: "Conferencia 1", // Event name (required)
  	      date: fecha, // Event date (required)
  	      type: "holiday", // Event type (required)
  	      everyYear: true, // Same event every year (optional)
  	    },
  	    {
  	      name: "Siembra de rosas",
  	      badge: "Plantas", // Event badge (optional)
  	      date: ["04/13/2021", "04/15/2021"], // Date range
  	      description: "Siembra de rosas en el arboreto Carmelo Palma", // Event description (optional)
  	      type: "event",
  	      color: "#a8a8a8", // Event custom color (optional)
  	      link: "https://google.com",
  	    },*/
  	  ],
  	});
</script>

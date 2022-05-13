<%@page import="java.util.*, vistas.Vista_coordenada_arbol, datos.DTVista_coordenada_arbol" %>
<%
	ArrayList<Vista_coordenada_arbol> listaCA = new ArrayList<Vista_coordenada_arbol>();
	DTVista_coordenada_arbol dt = new DTVista_coordenada_arbol();
	listaCA = dt.listarCA();
	
	String nombre = "dd";
	
%>

<script>
	

	const tilesProvider = "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
	let mymap = L.map("mapid").setView(
	  [12.126939908034066, -86.27038300037383],
	  20,
	);
	
	L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
	  id: 'mapbox/streets-v11',
	  attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	  maxZoom: 25,
	  
	  accessToken: 'pk.eyJ1IjoiemNsZXkiLCJhIjoiY2t1dW52c3RpMGh0ZzJvbGZtb2ppNnJtYSJ9.vC7yqD_oAnUnF7f5TL4g0g'
	}).addTo(mymap);
	
	var myStyle = {
	    "color": "#2bd97f",
	    "opacity": 0.65
	};
	
	fetch('js/page/arboreto.geojson')
	.then(function(response) {
		return response.json();
	})
	.then(function(data) {
		L.geoJSON(data,{
			style : myStyle
		}).bindPopup("Arboreto Carmelo Palma")
		.addTo(mymap);
	});

	let icon = L.icon({
	  iconUrl:
	    "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fpurepng.com%2Fpublic%2Fuploads%2Flarge%2Fpurepng.com-treenaturetreegreensummer-8315240022162idmu.png&f=1&nofb=1",
	  iconSize: [40, 40],
	});

	
	
	<%
		for(Vista_coordenada_arbol ca: listaCA){
	%>
	var html = `
		<div class="card border-dark rounded-3">
		<div class="figure">
		    <img class="card-img-top"
		        src="<%=ca.getFoto()%>"
		        alt="Abies alba">
		</div>

		<div class="card-body">
		    <h2 class="card-title my-2">
		        <%=ca.getNombreComun()%>
		    </h2>
		    <h5 class="card-subtitle my-2 mb-2">
		        <%=ca.getNombreCientifico()%>
		    </h5>
		    
		    <a href="treeDetails.jsp?idArbol=<%=ca.getIdarbol()%>" class="btn btn-outline-primary">
		        Ver más
		    </a>
		</div>
		
		</div>`;
	
	
	var pop = L.popup({
		  maxHeight: 300,
		  maxWidth: 300,
		  autoClose: true,
	}).setContent(html);
	
	
	
	var marker = L.marker(
	  [<%=ca.getLatitud()%>, <%=ca.getLongitud()%>],
	  { icon },
	  {
	    title: "Acacia",
	  }
	).addTo(mymap)
	.bindPopup(pop);
	<%
		}
	%>
</script>


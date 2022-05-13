<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, java.util.*"%>
<%
	DTPiePagina dt = new DTPiePagina();
	PiePagina pp = dt.obtenerPP();
%>
    <%
    Calendar cal=Calendar.getInstance();
    int year=cal.get(Calendar.YEAR);
    %>
<!-- Footer -->
<div id="footer" class="footer-clean" style="background-color:#001B36">
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-md-4 item">
                    <h3>Dirección</h3>
                    <ul>
                        <li>
                            <a href="">Universidad Centroamericana (UCA), Managua, Nicaragua <br>
                                <%=pp.getDireccion() %></a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-4 item">
                    <h3>Contacto</h3>
                    <ul>
                        <li>
                            Planta Telefónica: (505) <%=pp.getTelefono() %> hasta el <%=pp.getExt() %>
                        </li>
                        <li>
                            Correo Electrónico: <a href="mailto:<%=pp.getEmail()%>"><%=pp.getEmail() %></a>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-4 item">
                    <img src="img/logo_jesuita.png" alt="logo jesuita" class="img-fluid rounded mx-auto"
                        id="img-logo">
                </div>
            </div>
        </div>
        <div>
            <p class="copyright">
                Arboreto Carmelo Palma © <%=year %>
            </p>
        </div>
    </footer>
</div>
<!-- End of Footer -->
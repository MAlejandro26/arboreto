<%@page contentType="text/html" pageEncoding="UTF-8" import="entidades.*, datos.*, java.util.*" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Eventos - Arboreto Carmelo Palma</title>

        <!--CSS boostrap-->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/styles2.css">
        <link rel="stylesheet" href="css/styles.css">

        <!--font awesome-->
        <script src="https://kit.fontawesome.com/78a455df4c.js" crossorigin="anonymous"></script>

        <!-- Scrollbar -->
        <link rel="stylesheet" href="https://unpkg.com/simplebar@latest/dist/simplebar.css">

        <!-- Calendar -->
        <link rel="stylesheet" type="text/css" href="css/calendar/evo-calendar.css" />
        <link rel="stylesheet" type="text/css" href="css/calendar/evo-calendar.royal-navy.css" />
    </head>

    <body data-simplebar style="background-color:#fff">
        <jsp:include page="components/mainHeader.jsp" />

        <!-- Calendar -->
        <div class="container my-4 mb-4">
            <!-- Header -->
            <h1 class="my-4">
                Eventos
            </h1>

            <hr class="bg-dark">
            <!-- End of Header -->

            <div id="calendar"></div>

        </div>
        <!-- End of Calendar -->

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

        <!-- Calendar -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="js/calendar/evo-calendar.js"></script>
        <!-- <script src="js/calendar/calendar.js"></script> -->
        <jsp:include page="components/eventsCalendar.jsp"></jsp:include>

    </html>
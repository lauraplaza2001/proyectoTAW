<%-- 
    Document   : editorCategorias
    Created on : 28-mar-2022, 15:13:09
    Author     : juanm
--%>

<%@page import="proyectoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>GUI Test 1</title>
    </head>
    <body>
        <header class="p-3 mb-3 border-bottom">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                        <img src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="#" class="nav-link px-2 link-secondary">Página Principal</a></li>
                        <li><a href="#" class="nav-link px-2 link-dark">Inventario</a></li>
                        <li><a href="#" class="nav-link px-2 link-dark">Clientes</a></li>
                        <li><a href="#" class="nav-link px-2 link-dark">Productos</a></li>
                        <li><a href="#" class="nav-link px-2 link-primary">Categorías</a></li>
                    </ul>

                    <div class="dropdown text-end">
                        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="<%= request.getContextPath()%>/Images/list.svg" alt="No File" width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" href="#">Configuración</a></li>
                            <li><a class="dropdown-item" href="#">Perfil</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Cerrar Sesión</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="row col col-6">
                <div class="container rows-2">
                    <div class="container align-items-right">
                        <form class="d-flex" action="${pageContext.request.contextPath}/BusquedaCategoriaServlet" method="get">
                            <input class="form-control me-2" type="search" placeholder="Busqueda..." aria-label="Search" name="busqueda">
                            <button class="btn btn-outline-success" type="submit">Buscar</button>
                        </form>
                    </div>
                    <%    List<Categoria> categorias = (List) request.getAttribute("categorias");
                    %>
                    <nav class="container align-items-right p-3"

                         <ul class="list-group col-4">
                            <%    for (Categoria categoria : categorias) {
                            %>
                            <li class="list-group-item"value="<%=categoria.getIdCategoria()%>"><%=categoria.getNombre()%></li>
                                <% }
                                %>
                        </ul>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


    </body>
</html>
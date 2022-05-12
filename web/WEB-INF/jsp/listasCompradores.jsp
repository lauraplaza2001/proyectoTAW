<%-- 
    Document   : listasCompradores
    Created on : 25 abr 2022, 11:31:57
    Author     : Agustín
--%>

<%@page import="proyectoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Listas de Compradores</title>
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
                        <li><a href="<%= request.getContextPath()%>/ListasCategoriasServlet" class="nav-link px-2 link-dark">Listas de Categorias</a></li>
                    </ul>

                    <div class="dropdown text-end">
                        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="<%= request.getContextPath()%>/Images/list.svg" alt="No File" width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" href="#">Configuración</a></li>
                            <li><a class="dropdown-item" href="#">Perfil</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<%= request.getContextPath()%>/CerrarSesionServlet"">Cerrar Sesión</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="row">
                <div class="col col-6">
                    <div class="container rows-2">
                        <div class="container align-items-right">
                            <form class="d-flex" action="${pageContext.request.contextPath}/BusquedaCategoriaServlet" method="get">
                                <input class="form-control me-2" type="search" autocomplete="off" placeholder="Busqueda..." aria-label="Search" name="busqueda">
                                <button class="btn btn-outline-success" type="submit">Buscar</button>
                            </form>
                        </div>
                        <%    List<Categoria> categorias = (List) request.getAttribute("categorias");
                        %>
                        <div class="container p-3">
                            <div class="row">
                                <%
                                    for (Categoria categoria : categorias) {
                                %>
                                <form action="${pageContext.request.contextPath}/EditorCategoriasServlet">
                                    <ul class="col col-12 list-group list-group-horizontal">
                                        <li name="edit" id="myInputID" class="list-group-item col-8"><%=categoria.getNombre()%></li>  
                                        <li class="p-2">
                                            <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/ListaCompradoresCategoria?id=<%=categoria.getIdCategoria()%>">Ver lista de compradores</a>
                                        </li>
                                    </ul>
                                </form>

                                <% }%>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>
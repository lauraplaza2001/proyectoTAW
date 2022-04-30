<%-- 
    Document   : listaProductosEnVenta
    Created on : 20-abr-2022, 12:01:08
    Author     : amigo
--%>

<%@page import="proyectoTAW.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Categorías</title>
    </head>
    <body>
        <header class="p-3 mb-3 border-bottom">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                        <img src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="<%= request.getContextPath()%>/NuevoProductoServlet?id=1" class="nav-link px-2 link-secondary">Página Principal</a></li>
                        <li><a href="#" class="nav-link px-2 link-dark">Inventario</a></li>
                        <li><a href="<%= request.getContextPath()%>/ListaUsuariosServlet?filtro=1" class="nav-link px-2 link-dark">Clientes</a></li>
                        <li><a href="<%= request.getContextPath()%>/ListaProductosServlet" class="nav-link px-2 link-primary">Productos</a></li>
                        <li><a href="<%= request.getContextPath()%>/EditorCategoriasServlet" class="nav-link px-2 link-dark">Categorías</a></li>
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
            <div class="row">
                <div class="col col-6">
                    <div class="container rows-2">
                        <div class="input-group-prepend">
                            <form class="d-flex" action="${pageContext.request.contextPath}/BusquedaProductosVendedorServlet?id=1" method="get">
                                <select class="custom-select" name="filtro" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <option selected value="1">Tipo de Búsqueda<option
                                <option value="1">Nombre</option>
                                <option value="2">Categoría</option>
                            </select>

                            <input class="form-control me-2" type="search" autocomplete="off" placeholder="Busqueda..." aria-label="Search" name="busqueda">
                            <button class="btn btn-outline-success" type="submit">Buscar</button>
                            
                            <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/SubastarProductoServlet?id=1&usuario=1">Subastar producto</a>
                            
                           
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <%
                List<Producto> productos = (List) request.getAttribute("productos");
                for (Producto producto : productos) {
            %>
            <div class="col col-4 p-3">
                <form class="border"action="${pageContext.request.contextPath}/EditorSubastaServlet?idUser=1&id=<%= producto.getIdProducto()%>" method="get">
                    <ul class="list-group list-group-vertical">
                        <li class="list-group-item"><%= producto.getTitulo()%></li>
                        <img  class="fluid" src="<%= producto.getFoto()%>"
                             <li class="p-2">
                          
                            <a type="button"class="btn btn-warning" href="${pageContext.request.contextPath}/EditorSubastaServlet?idUser=1&id=<%= producto.getIdProducto()%>"> Editar</a>
                            <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/EliminarProductoVendedorServlet?id=<%=producto.getIdProducto()%>&idUser=1">Eliminar</a>
                        </li>
                    </ul>
                </form>
            </div>
            <% }%>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>
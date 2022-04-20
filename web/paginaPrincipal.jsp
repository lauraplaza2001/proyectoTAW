<%-- 
    Document   : paginaPrincipal
    Created on : 20-abr-2022, 11:29:30
    Author     : 34636
--%>

<%@page import="proyectoTAW.entity.Producto"%>
<%@page import="proyectoTAW.entity.Usuario"%>
<%@page import="proyectoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Principal</title>
    </head>
    <%
        List<Categoria> categorias = (List)request.getAttribute("categorias");
        
        Usuario user = (Usuario)session.getAttribute("usuario");
        if (user == null) {
            response.sendRedirect(request.getContextPath());
        }
%>
<body>
    <header>
         <div class="container">
                    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                            <img src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="No File" width="32" height="32" class="rounded-circle">
                        </a>

                        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                            <li><a href="#" class="nav-link px-2 link-secondary">Bienvenido <%= user.getNombreUsuario()  %></a></li>
                        </ul>

                        <div class="dropdown text-end">
                            <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="<%= request.getContextPath()%>/Images/list.svg" alt="No File" width="32" height="32" class="rounded-circle">
                            </a>
                            <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                                <li><a class="dropdown-item" href="#">Configuración</a></li>
                                <li><a class="dropdown-item" href="#">Perfil</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="inicioSesion.jsp">Cerrar Sesión</a></li>
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
                                <form class="d-flex" action="${pageContext.request.contextPath}/PaginaPrincipalServlet" method="get">
                                     <input type="hidden"name="id" value ="<%= user ==null?"": user.getIdUsuario() %>"/>
                                    <select class="custom-select" name="filtro" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        
                                        <option selected value ="1"> Todos los productos </option>
                                        <option  value="1">Favoritos</option>
                                        <option value="1">Comprados</option>
                                        <% 
                                         for (Categoria c: categorias){
                                             
                                         
                                         %>
                                         <option value="1"> <%= c.getNombre() %> </option>
                                         <%
                                         }
                                         %>
                                        
                                        
                                    </select>

                                <input class="form-control me-2" type="search" autocomplete="off" placeholder="Busqueda..." aria-label="Search" name="busqueda">
                                <button class="btn btn-outline-success" type="submit">Buscar</button>
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
                    <form class="border"action="${pageContext.request.contextPath}/EditorProductosServlet">
                        <ul class="list-group list-group-vertical">
                            <li class="list-group-item"><%= producto.getTitulo()%></li>
                            <img  class="fluid" src="<%= producto.getFoto()%>"
                                 <li class="p-2">
                                
                                
                               
                                <%
                                Boolean fav = (Boolean)request.getAttribute("fav");
                                Boolean comp = (Boolean)request.getAttribute("comp");
                                if (!fav){
                         
                     
                             %>
                            
                                <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/PonerFavorito?id=<%= producto.getIdProducto()%>">Poner a favoritos</a>
                            <%
                             }else{
                            %>
                 
                             <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/QuitarFavorito?id=<%= producto.getIdProducto()%>"Quitar de Favoritos</a>
                            <%
                            } if (comp){
                  
                             %>
                   
                             <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/QuitarComprado?id=<%= producto.getIdProducto()%>"Quitar de Favoritos</a>
                            <%
                              }
                           %>
                 <%-- Mas información del producto, participar en la puja... --%>
                            <button  type="submit" name="id" value="<%= producto.getIdProducto()%>" class="btn btn-warning" >Comprar</button>
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

<%-- 
    Document   : paginaPrincipal
    Created on : 26-abr-2022, 10:44:54
    Author     : 34636
--%>


<%@page import="proyectoTAW.entity.Producto"%>
<%@page import="proyectoTAW.dto.SubastaDTO"%>
<%@page import="proyectoTAW.entity.Usuario"%>
<%@page import="proyectoTAW.dto.ProductoDTO"%>
<%@page import="proyectoTAW.dto.UsuarioDTO"%>
<%@page import="proyectoTAW.dto.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Pagina principal</title>
    </head>
    <%
        List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
        
        
       Usuario user = (Usuario)session.getAttribute("usuario");
        if (user == null) {
            response.sendRedirect(request.getContextPath());
        }
        
        // UsuarioDTO user = (UsuarioDTO)request.getAttribute("usuario");
      %>
      
    <body>
      
      <header class="p-3 mb-3 border-bottom">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                        <img src="/proyectoTAW/Images/logoipsum-logo-50.svg" alt="..." width="32" height="32" class="rounded-circle">
                    </a>
                        
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        
                        <li><a href="/proyectoTAW/PaginaPrincipalServlet?id=<%=user.getIdUsuario()%>" class="nav-link px-2 link-primary">Página Principal</a></li>
                        <% if (user.getTipoUsuario().getTipoUsuario().equals("admin")){%>
                            
                        
                        <li><a href="<%= request.getContextPath()%>/ListaUsuariosServlet?filtro=1" class="nav-link px-2 link-dark">Clientes</a></li> <%-- solo puede ir si es admin --%>
                        <li><a href="<%= request.getContextPath()%>/ListaProductosServlet" class="nav-link px-2 link-dark">Productos</a></li> <%-- solo puede ir si es admin --%>
                        <li><a href="<%= request.getContextPath()%>/EditorCategoriasServlet" class="nav-link px-2 link-dark">Categorías</a></li> <%-- solo puede ir si es admin --%>
                         <li><a href="<%= request.getContextPath()%>/NuevoProductoServlet" class="nav-link px-2 link-dark">Mis productos</a></li> <%-- solo puede ir si es admin --%>
                        <li> <p class="text-success">Bienvenido <%= user.getNombreUsuario() %></p> </li>
                        <%}%>
                    </ul>

                    <div class="dropdown text-end">
                        <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="/proyectoTAW/Images/list.svg" alt="..." width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" href="#">Configuración</a></li>
                            <li><a class="dropdown-item" href="#">Perfil</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<%= request.getContextPath()%>/CerrarSesionServlet">Cerrar Sesión</a></li> <%--servlet log out --%>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
                                
       
           
        <div class="container">
            <div><h5>FILTRO</h5></div>
             
                <div class="row">
                    <div class="col col-6">
                        <div class="container rows-2">
                            <div class="input-group-prepend">
                                 <form class="d-flex" action="${pageContext.request.contextPath}/FiltroPaginaPrincipalServlet" method="get">
                                     
                                     <input type="hidden"name="id" value ="1"/>
                                        <select class="custom-select" name="filtro" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        
                                            <option selected value ="todos"> Todos los productos </option>
                                            <option  value="favoritos">Favoritos</option>
                                            <option value="comprados">Comprados</option>
                                           

                                        
                                    </select>
                                    <select class="custom-select" name="categoria" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <option value="">Todas las categorias</option>
                                         <% 
                                             for (CategoriaDTO c: categorias){


                                             %>
                                                <option value="<%=c.getNombre()%>"> <%= c.getNombre() %> </option>
                                             <%
                                             }
                                             %>
                                    </select> 
                                    
                                                
                                    <input type="search" autocomplete="off" placeholder="Nombre de producto" aria-label="Search" name="busqueda">
                                                              
                                    <button class="btn btn-outline-success" type="submit">Filtrar</button>
                                                                   
                                </form>
                        </div>
                    </div>
                </div>
            </div>
                                             
                                  
           <div class="row">
               <% String tit = (String)request.getAttribute("listaTipo"); %>
               <div><h5><%= tit %></h5> </div><%-- Poner que lista estas pasando --%>
                          
            <%
                Boolean fav = (Boolean)request.getAttribute("fav");
                Boolean comp = (Boolean)request.getAttribute("comp");
                
                List<SubastaDTO> subastas = (List) request.getAttribute("subastas");
                if (subastas == null || subastas.isEmpty()){%>
                    <div class="alert alert-danger" role="alert">
                   NO HAY PRODUCTOS, PRUEBA CON OTRO FILTRO
                   </div>
                <%}else{
                    
                 for (SubastaDTO subasta : subastas) { 
                    
            %>
            <div class="col col-4 p-3">
                <h3> Precio actual: <%= subasta.getPredioActual() %> $</h3>  
                    <ul class="list-group list-group-vertical">
                        <li class="list-group-item"><%= subasta.getProducto().getTitulo()%></li>
                        <img  class="fluid" src="<%= subasta.getProducto().getFoto()%>"
                             <li class="p-2">
                             <a type="button" class="btn btn-danger" 
                                 <%if (!fav && !comp){ %> href="${pageContext.request.contextPath}/PonerFavoritoServlet?idProducto=<%= subasta.getProducto().getIdProducto()%>&idUsuario=<%=user.getIdUsuario()%>">Poner a favoritos
                                 <%}else if (fav && !comp){ %>  href="${pageContext.request.contextPath}/QuitarFavoritoServlet?idProducto=<%= subasta.getProducto().getIdProducto()%>&idUsuario=<%=user.getIdUsuario()%>">Quitar de favoritos
                                   
                                 <%}else{%>  hidden> <%}%>
                            
                             </a>
                           <a type="button" class="btn btn-outline-success" 
                           <% if (!comp){ %>     href="${pageContext.request.contextPath}/PujaCompradorServlet?idProducto=<%=subasta.getProducto().getIdProducto()%>"> Participar en subasta
                           <% } else{ %>         href="${pageContext.request.contextPath}/QuitarCompradoServlet?idProducto=<%= subasta.getProducto().getIdProducto()%>&idUsuario=<%=user.getIdUsuario() %>" class="btn btn-warning" >Quitar de comprados</button> 
                           <%} %>
                           </a>
                             
                             
                        </li>
                    </ul>
                
            </div>
            <%
                }
            }
            %>
        </div>
   </div>
     
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
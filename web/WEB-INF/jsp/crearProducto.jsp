<%-- 
    Document   : crearProducto
    Created on : 20-abr-2022, 22:02:01
    Author     : Laura Plaza
--%>

<%@page import="proyectoTAW.dto.UsuarioDTO"%>
<%@page import="java.util.Date"%>
<%@page import="proyectoTAW.entity.Usuario"%>
<%@page import="proyectoTAW.entity.Categoria"%>
<%@page import="proyectoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="proyectoTAW.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subasta nuevo producto</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    </head>
     <body class="bg-light">

        <%
           
          //  Usuario usuario = (Usuario) request.getAttribute("usuario");
           // Producto producto = (Producto) request.getAttribute("producto");
            List<Categoria> categorias = (List) request.getAttribute("categorias");
              String error = (String)request.getAttribute("errorCategorias");
          
             UsuarioDTO usuario = (UsuarioDTO)session.getAttribute("usuario");
        

        %>

        <div class="container">
            <main class="row justify-content-md-center">

                <div class="py-5 text-center">
                    <h2>Creación de productos</h2>
                    <p class="lead">Por favor, introduzca todos los campos</p>

                </div>
                <div class="col-md-7 col-lg-8">
                    <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/GuardarProductoSubastaServlet">
                        <input type="hidden" name="idUser" id= "idUser" value="<%=usuario.getIdUsuario()%>" />
                        <div class="row g-3">
                            <div class="col-sm-12">
                                <label for="name" class="form-label">Titulo del Producto</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" name="name" id="name" value="" required></input>
                                    <div class="invalid-feedback">
                                        Título de producto obligatorio.
                                    </div>
                                </div>
                            </div> 

                            <div class="col-sm-12">
                                <label for="descripcion" class="form-label">Descripción del Producto</label>
                                <div class="input-group has-validation">
                                    <textarea type="text" class="comment" name="descripcion" id="descripcion" rows="10" cols='120' required></textarea>
                                    <div class="invalid-feedback">
                                        Descripción del producto obligatorio.
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-12">
                                <label for="image" class="form-label">URL de imagen</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" name="image" id="image" value="" required></input>
                                    <div class="invalid-feedback">
                                        Imagen necesaria.
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-5">
                                <label for="price" class="form-label">Precio Inicial</label>
                                <input type="number" class="form-control" name="price" id="price" min="0" value="" required></input>
                                <div class="invalid-feedback">
                                    Precio inicial obligatorio.
                                </div>
                            </div> 
                            
                            <div class="col-md-8">
                                <label for="fecha" class="form-label">Fecha de cierre de subasta</label>
                                <input type="date" name="fecha" id="fecha" value ="" min="2001-01-01" required> </input>
                             
                                <div class="invalid-feedback">
                                    Fecha de cierre de subasta obligatorio
                                </div>

                            <div class="col-md-12" name="categorias">
                                <label for="categorias" class="form-label">Categorias</label></br>
                                <%
                                    for (Categoria c : categorias) {
                                %>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" name="categorias" id="categorias" type="checkbox" value="<%= c.getIdCategoria()%>"  >
                                    <label class="form-check-label" for="<%= c.getIdCategoria()%>">
                                        <%= c.getNombre()%>
                                    </label>
                                </div>

                                <% } %>
                                
                         
                            </div>
                              
                                   <% if (error!=null && !error.isEmpty()) {%>
                                    
                                <h4 style="color:#FF0000">  <%=error%> </h4>
                               <% }%>

                            <hr class="my-4">
                            
                             
                                

                            <button class="w-100 btn btn-primary btn-lg" type="submit" name="idFinalizar" value="Finalizar Edición" >Finalizar Edición</button>
                    </form>
                </div>
        </div>
    </main>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2021–2022 Proyecto Tecnologías Aplicaciones Web</p>
    </footer>
</div>

<script>// Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict'
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')
        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }
                        form.classList.add('was-validated')
                    }, false)
                })
    })()</script>
<script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>

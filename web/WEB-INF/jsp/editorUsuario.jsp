<%-- 
    Document   : editorUsuario
    Created on : 15-may-2022, 23:58:36
    Author     : juanm
--%>

<%@page import="proyectoTAW.dto.UsuarioDTO"%>
<%@page import="proyectoTAW.dto.TipousuarioDTO"%>
<%@page import="proyectoTAW.dto.GeneroDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Registro de usuario</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    </head>
    <body class="bg-light">
        
        <%
            UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
        %>

        <div class="container">
            <main class="row justify-content-md-center">

                <div class="py-5 text-center">
                    <img class="d-block mx-auto mb-4" src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="" width="72" height="57">
                    <h2>Registro de nuevos usuarios</h2>
                    <p class="lead">Por favor, introduzca todos los campos para completar la edicion</p>
                </div>
                <div class="col-md-7 col-lg-8">
                    <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/GuardarUsuarioServlet">
                        <div class="row g-3">
                            <div class="col-sm-3">
                                <label for="username" class="form-label">Nombre de usuario</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" name="username" id="username" value="<%= usuario.getNombreUsuario() %>" required>
                                    <div class="invalid-feedback">
                                        Nombre de usuario obligatorio.
                                    </div>
                                </div>
                            </div> 
                            
                            <div class="col-sm-9">
                                <label for="password" class="form-label">Contraseña</label>
                                <div class="input-group has-validation">
                                    <input type="password" class="form-control" name="password" id="password" required>
                                    <div class="invalid-feedback">
                                        Contraseña obligatoria.
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">Nombre</label>
                                <input type="text" class="form-control" name="firstName" id="firstName" value="<%= usuario.getNombre() %>"required>
                                <div class="invalid-feedback">
                                    Primer nombre obligatorio.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Apellidos</label>
                                <input type="text" class="form-control" name="lastName" id="lastName" value="<%= usuario.getApellidos() %>"required>
                                <div class="invalid-feedback">
                                    Apellidos obligatorios.
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <label for="city" class="form-label">Ciudad</label>
                                <input type="text" class="form-control" name="city" id="city" value="<%= usuario.getCiudad() %>"required>
                                <div class="invalid-feedback">
                                    Ciudad obligatoria.
                                </div>
                            </div>
                            
                            <div class="col-sm-4">
                                <label for="address" class="form-label">Dirección</label>
                                <input type="text" class="form-control" name="address" id="address" value="<%= usuario.getDomicilio() %>"required>
                                <div class="invalid-feedback">
                                    Dirección obligatoria.
                                </div>
                            </div>

                            <div class="col-md-5">
                                <label for="age" class="form-label">Edad</label>
                                <input type="number" class="form-control" name="age" id="age" min="0" max="130" value="<%= usuario.getEdad() %>"required>
                                <div class="invalid-feedback">
                                    Edad obligatoria.
                                </div>
                            </div>

                            <%
                                List<GeneroDTO> generos = (List) request.getAttribute("generos");
                                List<TipousuarioDTO> tUsuarios = (List) request.getAttribute("tUsuarios");
                            %>

                            <div class="col-md-3">
                                <label for="gender" class="form-label">Género</label>
                                <select class="form-select" name="gender" id="gender" required>
                                    <option value="">Elige</option>
                                    <% for (GeneroDTO g : generos) {%>
                                    <option value="<%=g.getGenero() %>"><%=g.getGenero()%></option>
                                    <% } %>
                                </select>
                                <div class="invalid-feedback">
                                    Género requerido.
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label for="usertype" class="form-label">Tipo de Usuario</label>
                                <select class="form-select" name="usertype" id="usertype" required>
                                    <option value="">Elige</option>
                                    <% for (TipousuarioDTO tu : tUsuarios) {%>
                                    <option value="<%=tu.getTipoUsuario()%>"><%=tu.getTipoUsuario()%></option>
                                    <% }%>
                                </select>
                                <div class="invalid-feedback">
                                    Tipo de usuario requerido.
                                </div>
                            </div>
                        </div>

                        <hr class="my-4">

                        <button class="w-100 btn btn-primary btn-lg" name ="id" value="<%= usuario.getIdUsuario() %>" type="submit">Finalizar registro</button>
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

<script src="form-validation.js"></script>
</body>
</html>


<%-- 
    Document   : registroUsuario.jsp
    Created on : 07-abr-2022, 12:18:49
    Author     : juanm
--%>

<%@page import="proyectoTAW.entity.Genero"%>
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

        <div class="container">
            <main>
                
                <div class="py-5 text-center">
                    <img class="d-block mx-auto mb-4" src="<%= request.getContextPath()%>/Images/logoipsum-logo-50.svg" alt="" width="72" height="57">
                    <h2>Registro de nuevos usuarios</h2>
                    <p class="lead">Por favor, introduzca los datos necesarios para completar el registro</p>
                </div>
                <div class="col-md-7 col-lg-8">
                    <form class="needs-validation" novalidate>
                        <div class="row g-3">
                            <div class="col-12">
                                <label for="username" class="form-label">Nombre de usuario</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" id="username"  required>
                                    <div class="invalid-feedback">
                                        Nombre de usuario obligatorio.
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                                <div class="invalid-feedback">
                                    Primer nombre obligatorio.
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Apellidos</label>
                                <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                                <div class="invalid-feedback">
                                    Apellidos obligatorios.
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="address" class="form-label">Dirección</label>
                                <input type="text" class="form-control" id="address" required>
                                <div class="invalid-feedback">
                                    Dirección obligatoria.
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="address" class="form-label">Ciudad</label>
                                <input type="text" class="form-control" id="city" required>
                                <div class="invalid-feedback">
                                    Ciudad obligatoria.
                                </div>
                            </div>

                            <div class="col-md-5">
                                <label for="country" class="form-label">Edad</label>
                                <input type="number" class="form-control" id="age" required>
                                <div class="invalid-feedback">
                                    Edad obligatoria.
                                </div>
                            </div>

                            <%
                                List<Genero> generos = (List) request.getAttribute("generos");
                                int i = 0;
                            %>

                            <div class="col-md-3">
                                <label for="zip" class="form-label">Género</label>
                                <select class="form-select" id="gender" required>
                                    <option value="">Elige</option>
                                    <% for(Genero g:generos){ %>
                                    <option value="<%=g.getGenero() %>"><%=g.getGenero() %></option>
                                    <% } %>
                                </select>
                                <div class="invalid-feedback">
                                    Género requerido.
                                </div>
                            </div>
                        </div>

                        <hr class="my-4">

                        <button class="w-100 btn btn-primary btn-lg" type="submit">Finalizar registro</button>
                    </form>
                </div>
        </div>
    </main>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2021–2022 Proyecto Tecnologías Aplicaciones Web</p>
    </footer>
</div>


<script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<script src="form-validation.js"></script>
</body>
</html>

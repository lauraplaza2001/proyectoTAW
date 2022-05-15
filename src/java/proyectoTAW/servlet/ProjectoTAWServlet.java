/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proyectoTAW.dto.UsuarioDTO;
import proyectoTAW.service.UsuarioService;

/**
 *
 * @author Chris
 */
@WebServlet(name = "ProjectoTAWServlet", urlPatterns = {"/ProjectoTAWServlet"})
public abstract class ProjectoTAWServlet extends HttpServlet {
    @EJB UsuarioService usuarioService;
@Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    


    @Override
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected boolean redirigirUsuario (HttpServletRequest request, HttpServletResponse response, String tipoUsuario, HttpSession sesion) 
                throws ServletException, IOException {
        if (!this.usuarioService.comprobarPermisos(sesion, tipoUsuario)){
            response.sendRedirect(request.getContextPath() + "/PaginaPrincipalServlet");
            return false;
        }  else {
            return true;
        }
        
    }
}

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
 * @author Agustín
 */
@WebServlet(name = "iniciarSesionServlet", urlPatterns = {"/iniciarSesionServlet"})
public class iniciarSesionServlet extends HttpServlet {
    
    @EJB UsuarioService usuarioService;
            
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String username = (String) request.getParameter("userName");
       String psw = (String) request.getParameter("inputPassword");
       
       UsuarioDTO usuario = this.usuarioService.comprobarUsuario(username,psw);
       
       if (usuario == null){
         String strError = "El usuario o la clave son incorrectos";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);                
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("tipoUsuario", usuario.getTipoUsuario().getTipoUsuario());
            switch(usuario.getTipoUsuario().getTipoUsuario()){
                case "Administrador":
                    response.sendRedirect(request.getContextPath() + "/ListaProductosServlet");
                    break;
                case "Marketing":
                    response.sendRedirect(request.getContextPath() + "/ListasMarketingServlet");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/PaginaPrincipalServlet");
                    break;
            }               
        }   
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

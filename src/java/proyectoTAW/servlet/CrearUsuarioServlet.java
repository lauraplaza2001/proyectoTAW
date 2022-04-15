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
import proyectoTAW.dao.GeneroFacade;
import proyectoTAW.dao.TipousuarioFacade;
import proyectoTAW.dao.UsuarioFacade;
import proyectoTAW.entity.Genero;
import proyectoTAW.entity.Tipousuario;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author juanm
 */
@WebServlet(name = "CrearUsuarioServlet", urlPatterns = {"/CrearUsuarioServlet"})
public class CrearUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB GeneroFacade gFacade;
    @EJB TipousuarioFacade tuFacade;
    @EJB UsuarioFacade uFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String username = (String) request.getParameter("username");
        String pass = (String) request.getParameter("password");
        String name = (String) request.getParameter("firstName");
        String surname = (String) request.getParameter("lastName");
        String city = (String) request.getParameter("city");
        String address = (String) request.getParameter("address");
        int age = Integer.parseInt(request.getParameter("age"));
        
        String genero = request.getParameter("gender");
        Genero gender = this.gFacade.find(genero);
        
        String userParameter = (String) request.getParameter("usertype");
        Tipousuario userType;
        
        if(userParameter != null){
            userType  = this.tuFacade.find(userParameter);
        }else{
            userType = this.tuFacade.find("Estandar");
        }
        
        
        Usuario usuario = new Usuario();
        
        usuario.setNombreUsuario(username);
        usuario.setContrasena(pass);
        usuario.setNombre(name);
        usuario.setApellidos(surname);
        usuario.setCiudad(city);
        usuario.setDomicilio(address);
        usuario.setEdad(age);
        usuario.setGenero(gender);
        usuario.setTipoUsuario(userType);
        
        this.uFacade.create(usuario);
        
        response.sendRedirect(request.getContextPath()+"/ListaUsuariosServlet?filtro=1");        
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
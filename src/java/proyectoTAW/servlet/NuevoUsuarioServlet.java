/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyectoTAW.dao.GeneroFacade;
import proyectoTAW.dao.TipousuarioFacade;
import proyectoTAW.dto.GeneroDTO;
import proyectoTAW.dto.TipousuarioDTO;
import proyectoTAW.entity.Genero;
import proyectoTAW.entity.Tipousuario;
import proyectoTAW.service.GeneroService;
import proyectoTAW.service.TipousuarioService;

/**
 *
 * @author juanm
 */
@WebServlet(name = "NuevoUsuarioServlet", urlPatterns = {"/NuevoUsuarioServlet"})
public class NuevoUsuarioServlet extends ProjectoTAWServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB GeneroService gService;
    @EJB TipousuarioService tuService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<GeneroDTO> generos = (List) this.gService.findAll();
        List<TipousuarioDTO> tUsuarios = (List) this.tuService.findAll();
        
        boolean admin = Boolean.parseBoolean((String)request.getParameter("admin"));

        request.setAttribute("generos", generos);
        request.setAttribute("tUsuarios", tUsuarios);
        
        if(admin){
            request.setAttribute("admin", "true");
        }else{
            request.setAttribute("admin", "false");
        }
        
        request.getRequestDispatcher("/WEB-INF/jsp/registroUsuario.jsp").forward(request, response);
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

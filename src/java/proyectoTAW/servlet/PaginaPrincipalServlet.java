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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyectoTAW.dto.CategoriaDTO;
import proyectoTAW.dto.ProductoDTO;
import proyectoTAW.dto.UsuarioDTO;
import proyectoTAW.service.CategoriaService;
import proyectoTAW.service.ProductoService;
import proyectoTAW.service.UsuarioService;

/**
 *
 * @author 34636
 */
@WebServlet(name = "PaginaPrincipalServlet", urlPatterns = {"/PaginaPrincipalServlet"})
public class PaginaPrincipalServlet extends ProjectoTAWServlet {
   
    @EJB CategoriaService cs;
    @EJB ProductoService ps;
    @EJB UsuarioService us;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //if (comprobarSession()){
        
             
      //Datos ###############################
      Boolean fav=false,comp=false;
      String listaTipo = "PRODUCTOS EN SUBASTA: ";
      List <CategoriaDTO> categorias = this.cs.findAll();
    
      
      String id = request.getParameter("id");
     
      UsuarioDTO user = this.us.find(Integer.parseInt(id)); 
      List <ProductoDTO> productos = this.ps.productosSubastaActiva("","");

      
      
      request.setAttribute("usuario",user); //Quitar después
      request.setAttribute("categorias",categorias);
      
      request.setAttribute("listaTipo",listaTipo);
      request.setAttribute("fav",fav);
      request.setAttribute("comp",comp);      
      
      request.setAttribute("productos",productos);
      request.getRequestDispatcher("/WEB-INF/jsp/paginaPrincipal.jsp").forward(request,response);

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

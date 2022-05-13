/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyectoTAW.dto.CategoriaDTO;
import proyectoTAW.dto.ProductoDTO;
import proyectoTAW.dto.SubastaDTO;
import proyectoTAW.dto.UsuarioDTO;
import proyectoTAW.service.CategoriaService;
import proyectoTAW.service.ProductoService;
import proyectoTAW.service.SubastaService;
import proyectoTAW.service.UsuarioService;

/**
 *
 * @author 34636
 */
@WebServlet(name = "FiltroPaginaPrincipalServlet", urlPatterns = {"/FiltroPaginaPrincipalServlet"})
public class FiltroPaginaPrincipalServlet extends ProjectoTAWServlet {
  @EJB CategoriaService cs;
    @EJB UsuarioService us;
    @EJB SubastaService ss;

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
      if (super.comprobarSession(request,response)){
          //DATOS 
          //-----------------------------------------------------------------
      Boolean fav=false,comp=false;
      String listaTipo = "PRODUCTOS EN SUBASTA ";
      List <CategoriaDTO> categorias = this.cs.findAll();
     
      String filtro = request.getParameter("filtro");
      
      String categoria = request.getParameter("categoria");
      if (categoria == null)categoria="";
      
      String titulo = request.getParameter("busqueda");
      if (titulo == null)titulo = "";
      
      String id = request.getParameter("id");
      
      
      List <SubastaDTO> subastas = this.ss.SubastaActiva(titulo,categoria);

      
      //FILTROS
      //-----------------------------------------------------------------
        
        if (filtro.equals("favoritos")){
           subastas = this.ss.SubastaProductosFavoritos(new Integer (id),titulo,categoria);
           listaTipo = "PRODUCTOS DE SUBASTAS EN FAVORITO " ;
           fav=true;
       } else if (filtro.equals("comprados")){
           subastas = this.ss.SubastaProductosComprados(new Integer (id),titulo,categoria);
           listaTipo = "PRODUCTOS YA COMPRADOS " + categoria; 
           comp=true;
       }
        if (!categoria.equals("") ) listaTipo+= " DE CATEGORIA '"+ categoria.toUpperCase() +"'";
        if(!titulo.equals(""))listaTipo+= " QUE CONTENGAN  '"  + titulo.toUpperCase() +"' ";
      
    //-----------------------------------------------------------------
      request.setAttribute("categorias",categorias);
      
      request.setAttribute("listaTipo",listaTipo);
      request.setAttribute("fav",fav);
      request.setAttribute("comp",comp);      
      
      request.setAttribute("subastas",subastas);
      request.getRequestDispatcher("/WEB-INF/jsp/paginaPrincipal.jsp").forward(request,response);
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

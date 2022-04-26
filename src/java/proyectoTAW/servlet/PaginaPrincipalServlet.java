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
import proyectoTAW.dao.CategoriaFacade;
import proyectoTAW.dao.ProductoFacade;
import proyectoTAW.dao.SubastaFacade;
import proyectoTAW.dao.UsuarioFacade;
import proyectoTAW.entity.Categoria;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author 34636
 */
@WebServlet(name = "PaginaPrincipalServlet", urlPatterns = {"/PaginaPrincipalServlet"})
public class PaginaPrincipalServlet extends HttpServlet {
   
    @EJB CategoriaFacade cf;
    @EJB SubastaFacade sf;
    @EJB ProductoFacade pf;
    @EJB UsuarioFacade uf;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //Esto borrar despues
       
        Usuario user = this.uf.find(1);
        request.setAttribute("usuario",user);

        //----------------
             
       
     
      List <Categoria> categorias = this.cf.findAll();
       
             
     
       request.setAttribute("usuario",user); 
       request.setAttribute("categorias",categorias);
      

      
        
      List <Producto> productos = this.pf.findAll();
      
      //List <Subasta> subastas = this.sf.findAll();
      
      /*
      long millis = System.currentTimeMillis();
      java.util.Date now = new java.util.Date(millis);
      
       //si la subasta está activa, lo enseñamos como producto
      for (Subasta s: subastas){
           if (s.getFechaCierre().compareTo(now) <=1 ){
               productos.add(s.getProducto());
           }
       }
       

     */ 
        
      
       
      request.setAttribute("fav",false);
      request.setAttribute("comp",false);      
      request.setAttribute("categorias",categorias);
      request.setAttribute("productos",productos);
      request.getRequestDispatcher("paginaPrincipal.jsp").forward(request,response);

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

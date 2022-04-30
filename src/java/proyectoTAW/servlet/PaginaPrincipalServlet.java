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
public class PaginaPrincipalServlet extends ProjectoTAWServlet {
   
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
        //if (comprobarSession()){
        
             
      //Datos ###############################
      Boolean fav=false,comp=false;
      String listaTipo = "PRODUCTOS EN SUBASTA";
      List <Categoria> categorias = this.cf.findAll();
     
      String filtro = request.getParameter("filtro");
      String titulo = request.getParameter("busqueda");
      if (titulo == null)titulo = "";
      
     //String id = request.getParameter("id"); 
      String id ="1";//borrar después
      Usuario user = this.uf.find(Integer.parseInt(id)); 
      List <Producto> productos = null;
      
      //FILTROS ####################################
      if ( filtro == null || filtro.equals("todos")  ){
          
          productos = this.pf.findProductsSubastaActiva("",titulo);
         
       }else if (filtro.equals("favoritos")){
           productos = this.pf.findFavouriteProductList(id);
           listaTipo = "PRODUCTOS DE SUBASTAS EN FAVORITO";
           fav=true;
       } else if (filtro.equals("comprados")){
           productos = this.pf.findProductsComprados(id, titulo);
           listaTipo = "PRODUCTOS YA COMPRADOS"; 
           comp=true;
       }else{ //Filtlrado por una categoría
           
           productos = this.pf.findProductsSubastaActiva(filtro,titulo);
           listaTipo = "PRODUCTOS DE SUBASTA DE LA CATEGORÍA: " + filtro;       
                  
      }
      
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

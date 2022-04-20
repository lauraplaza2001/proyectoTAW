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
import proyectoTAW.dao.UsuarioFacade;
import proyectoTAW.entity.Categoria;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author 34636
 */
@WebServlet(name = "PaginaPrincipal", urlPatterns = {"/PaginaPrincipal"})
public class PaginaPrincipalServlet extends HttpServlet {
    
    @EJB CategoriaFacade cf;
    @EJB ProductoFacade  pf;
    @EJB UsuarioFacade uf;
    
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
        
       String id = request.getParameter("id");
       Usuario user = this.uf.find(Integer.parseInt(id));
       
       String filtro = request.getParameter("filtro");
       String titulo = request.getParameter("busqueda");
       if (titulo == null)titulo="";
       
       Boolean fav=false,comp = false;
       List <Producto> productos = null;
       
       if (filtro.equals("Todos los productos")){
           productos=this.pf.findAll();
       }else if (filtro.equals("Favoritos")){
           //Falta Query
          fav=true;
       } else if (filtro.equals("Comprados")){
           //Falta Query
          comp=true;
       }else{ //Filtlrado por una categoría
           
           //Supongo que no hay 2 categorías con el mismo nombre
           Categoria categoria = this.cf.getCategoriasLike(filtro).get(0);
           //FaltaQuery
           
       }
       
       
        if (!titulo.equals("") && productos!= null){
            for (Producto p: productos){
                if (!p.getTitulo().equals(titulo)){
                    productos.remove(p);
                }
            }
        }
        
       request.setAttribute("productos",productos);
       request.setAttribute("fav",fav);
       request.setAttribute("comp",comp);
       request.getRequestDispatcher("/paginaPrincipal.jsp").forward(request,response);
       
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
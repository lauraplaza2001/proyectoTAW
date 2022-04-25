/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyectoTAW.dao.ProductoFacade;
import proyectoTAW.dao.SubastaFacade;
import proyectoTAW.dao.UsuarioFacade;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author amigo
 */
@WebServlet(name = "BusquedaProductosVendedorServlet", urlPatterns = {"/BusquedaProductosVendedorServlet"})
public class BusquedaProductosVendedorServlet extends HttpServlet {
    @EJB ProductoFacade pFacade;
    @EJB SubastaFacade sFacade;
    @EJB UsuarioFacade uFacade;
    
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
    
      
      List<Producto> productos;
      String like = (String) request.getParameter("busqueda");
      Integer filtro = Integer.parseInt(request.getParameter("filtro"));
  
      if(like != null){
          
          productos = this.pFacade.findFiltered(filtro, like);       
          
      }else{
          productos = this.pFacade.findAll();
      }
       
        String idd = (String) request.getParameter("id");
      List<Subasta> subastas ;
      subastas = this.uFacade.getSubastasVendedor("1"); // aqui realmente deberia pasarle el id, pero me da NumberFormatException
      List<Producto> productosFiltrados  = new ArrayList<>();
      
      
      for(int i = 0 ; i<productos.size() ; i++){
          for(int j = 0 ; j< subastas.size();j++){
              if(productos.get(i).getIdProducto() == subastas.get(j).getProducto().getIdProducto()){
                  productosFiltrados.add(productos.get(i));
              }
          }
      }
     
      
      request.setAttribute("productos", productosFiltrados);
      request.getRequestDispatcher("listaProductosEnVenta.jsp").forward(request, response);
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

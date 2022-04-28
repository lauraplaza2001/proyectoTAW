/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyectoTAW.dao.ProductoFacade;
import proyectoTAW.dao.SubastaFacade;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;

/**
 *
 * @author amigo
 */
@WebServlet(name = "PujaCompradorServlet", urlPatterns = {"/PujaCompradorServlet"})
public class PujaCompradorServlet extends HttpServlet {
     @EJB SubastaFacade sFacade;
     @EJB ProductoFacade pFacade;
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
      // String idSubasta= request.getParameter("idSubasta");
       //String idProducto = request.getParameter("idProducto");
      
      // Subasta subasta = sFacade.find(Integer.parseInt(idSubasta));
     //  Producto producto = pFacade.find(Integer.parseInt(idProducto));
        
        
       Subasta subasta = sFacade.find(2);
       Producto producto = pFacade.find(2);
        
       
       
        request.setAttribute("subasta",subasta);
        request.setAttribute("producto", producto);
       // request.setAttribute("idUsuario", request.getParameter("idUsuario"));
        request.setAttribute("idUsuario", "1");
        
        
   
        
        String error = (String) request.getAttribute("error");
        if( error == null || error.isEmpty()){
            error = "";
        }
        
        request.setAttribute("error", error);
        
        
        
        request.getRequestDispatcher("pujas.jsp").forward(request, response);
        
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

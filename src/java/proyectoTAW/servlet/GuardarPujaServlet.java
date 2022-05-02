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
import proyectoTAW.dao.UsuarioFacade;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author amigo
 */
@WebServlet(name = "GuardarPujaServlet", urlPatterns = {"/GuardarPujaServlet"})
public class GuardarPujaServlet extends HttpServlet {
       @EJB SubastaFacade sFacade;
       @EJB UsuarioFacade uFacade;
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
        
        int idSubasta = Integer.parseInt(request.getParameter("idSubasta"));
        int idMayorPostor=Integer.parseInt(request.getParameter("mayorPostor"));
     
        String strError;
        Usuario u = this.uFacade.find(idMayorPostor);
        Subasta s = this.sFacade.find(idSubasta);
        
        
        
        String precioPuja = request.getParameter("precioPuja");
        if(precioPuja!=null && !precioPuja.isEmpty()){
             double apuesta = Double.parseDouble(precioPuja);
             if( s.getPredioActual() < apuesta){ //
                 s.setPredioActual(apuesta); // solo debo poder pujar
                 s.setMayorPostor(u);
                     sFacade.edit(s);
                 strError = "Enhorabuena, has pujado " + precioPuja + " euros";
                 
             }else{
            strError = "No puedes pujar un precio menor a la puja mayor";
        }
            
        }else{
            strError = "*Indique cantidad a pujar";            
        }
        
        
        Subasta subasta = s;
        Producto producto = s.getProducto();
    
        
        request.setAttribute("subasta",subasta);
        request.setAttribute("producto", producto);
       // request.setAttribute("idUsuario", request.getParameter("idUsuario"));
        request.setAttribute("idUsuario", "1");
        
        
        request.setAttribute("error", strError);
    request.getRequestDispatcher("/WEB-INF/jsp/pujas.jsp").forward(request, response);
       
        
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

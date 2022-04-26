/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
@WebServlet(name = "GuardarProductoSubastaServlet", urlPatterns = {"/GuardarProductoSubastaServlet"})
public class GuardarProductoSubastaServlet extends HttpServlet {
    @EJB ProductoFacade pFacade;
    @EJB UsuarioFacade uFacade;
    @EJB SubastaFacade sFacade;
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
       
        //int id = Integer.parseInt(request.getParameter("id")); // id del usuario
        int id = 1;
        String title = (String) request.getParameter("name");
        String desc = (String) request.getParameter("descripcion");
        String foto = (String) request.getParameter("image");
        double precio = Double.parseDouble(request.getParameter("price"));
        Usuario user = this.uFacade.find(id);
        
        String idProducto= (String) request.getParameter("id");
       // String idProducto = "2";
        
        /*List<Categoria> categoriasTotales = this.cFacade.findAll();
        List<Categoria> categoriasFinales = new ArrayList<Categoria>();
        
        for(Categoria c : categoriasTotales){
            String categoria = ((String) request.getParameter(c.getIdCategoria()+""));
            if(categoria != null && (categoria.equalsIgnoreCase("true"))){
                System.out.println(c.getNombre());
                categoriasFinales.add(c);
            }
        }*/

       
     
  
        if(idProducto == null  || idProducto.isEmpty()){ // si es nulo quiere decir que estamos creandolo
            Producto producto = new Producto();

            producto.setTitulo(title);
            producto.setDescripcion(desc);
            producto.setFoto(foto);
            producto.setPrecioSalida(precio);

   
            //producto.setCategoriaList(categoriasFinales);
       
        
            Subasta s = new Subasta();
            s.setCreador(user);
            s.setPredioActual(precio);
            s.setProducto(producto);
            Date d =new Date();
            s.setFechaCierre(d);
            
            
           this.pFacade.create(producto);
            this.sFacade.create(s);
        }else { // si no es nulo, estamos editandolo
            Producto producto = this.pFacade.find(Integer.parseInt(idProducto));

            producto.setTitulo(title);
            producto.setDescripcion(desc);
            producto.setFoto(foto);
            producto.setPrecioSalida(precio);

   
        //producto.setCategoriaList(categoriasFinales);
       // Subasta s = this.sFacade. // saco esa subasta 
        
      //  s.setPredioActual(precio);
       // Date d =new Date();
     //   s.setFechaCierre(d);
            
            
            this.pFacade.edit(producto);
          //  this.sFacade.edit(s);
        } // si no es nuevo quiere decir que lo estamos editando
        

   
        
        
        

        response.sendRedirect(request.getContextPath() + "/NuevoProductoServlet?id=1");

       
        
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

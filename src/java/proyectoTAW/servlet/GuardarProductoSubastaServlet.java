/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import proyectoTAW.entity.Subasta;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author Laura Plaza
 */
@WebServlet(name = "GuardarProductoSubastaServlet", urlPatterns = {"/GuardarProductoSubastaServlet"})
public class GuardarProductoSubastaServlet extends ProjectoTAWServlet {
    @EJB ProductoFacade pFacade;
    @EJB UsuarioFacade uFacade;
    @EJB SubastaFacade sFacade;
    @EJB CategoriaFacade cFacade;
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
        
        if(super.comprobarSession(request, response)){
            
        try {
            int id = Integer.parseInt(request.getParameter("idUser")); // id del usuario
            String title = (String) request.getParameter("name");
            String desc = (String) request.getParameter("descripcion");
            String foto = (String) request.getParameter("image");
            double precio = Double.parseDouble(request.getParameter("price"));
            String idProducto= (String) request.getParameter("id");
            Usuario user = this.uFacade.find(id);
             String strFecha = (String) request.getParameter("fecha");
            //String strFecha = "2022-12-12";
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(strFecha);
            
            
            
            String [] categorias = request.getParameterValues("categorias");
            
            List<Categoria> categoriasTotales = this.cFacade.findAll();
            List<Categoria> categoriasFinales = new ArrayList<Categoria>();
        
            
    
            
            if(categorias==null && idProducto!=null && !idProducto.isEmpty()){ // caso en el que las categorias son nulas y estamos creando un producto
                String strError = "Error, debes seleccionar al menos una categoria";
                request.setAttribute("errorCategorias", strError);
                
                Producto producto = this.pFacade.find(Integer.parseInt(idProducto));
                 request.setAttribute("producto", producto);
                 
                 
                 String idSubasta = request.getParameter("subastaId");
                 Subasta subasta = this.sFacade.find(Integer.parseInt(idSubasta));
                 request.setAttribute("subasta", subasta);
                 
                request.setAttribute("categorias", categoriasTotales);
                request.getRequestDispatcher("/WEB-INF/jsp/editorProductoSubasta.jsp").forward(request, response); // necesita, producto,categoria y subasta
                
                
                
            }else if (categorias == null &&( idProducto ==null || idProducto.isEmpty())){ // caso en el que las categorias son nulas  y estamos editando un producto
                String strError = "**Error, debes seleccionar al menos una categoria";
                request.setAttribute("errorCategorias", strError);
                request.setAttribute("categorias", categoriasTotales);
                request.getRequestDispatcher("/WEB-INF/jsp/crearProducto.jsp").forward(request, response);
            }else { // casi en el que todos los datos son correctos 
                
                
            // de aqui sacamos las categorias seleccionadas    
               for(Categoria c : categoriasTotales){
                for(String ci : categorias){
                    if(c.getIdCategoria().toString().equals(ci)){
                       categoriasFinales.add(c);      
                    }
                }    
            }
                       
                 
            
            if(idProducto == null  || idProducto.isEmpty()){ // si es nulo quiere decir que estamos creandolo
                Producto producto = new Producto();
                
                producto.setTitulo(title);
                producto.setDescripcion(desc);
                producto.setFoto(foto);
                producto.setPrecioSalida(precio);
                producto.setCategoriaList(categoriasFinales);
                
             
                
            
                
                Subasta s = new Subasta();
                s.setCreador(user);
                s.setPredioActual(precio);
                s.setProducto(producto);
                s.setFechaCierre(fecha);
                
                
                
                this.pFacade.create(producto);
                this.sFacade.create(s);
                
                
                
              
                for(Categoria c : categoriasFinales){
                    List<Producto> p = new ArrayList<>();
                    p = c.getProductoList();
                    p.add(producto);
                    c.setProductoList(p);
                    this.cFacade.edit(c);
                }
              
                
            
                
                
                
            }else { // si no es nulo, estamos editandolo
                Producto producto = this.pFacade.find(Integer.parseInt(idProducto));
                
                List<Categoria> categoriasAntiguas = producto.getCategoriaList();
                
                
                producto.setTitulo(title);
                producto.setDescripcion(desc);
                producto.setFoto(foto);
                producto.setPrecioSalida(precio); 
                producto.setCategoriaList(categoriasFinales);
               
      
    
             String idSubasta = request.getParameter("subastaId");
              Subasta subasta = this.sFacade.find(Integer.parseInt(idSubasta));
      
              //  Subasta s = sFacade.findSubastasDelProducto(producto.getIdProducto()).get(0);
                
                  subasta.setPredioActual(precio);
                  subasta.setFechaCierre(fecha);
                this.pFacade.edit(producto);
                this.sFacade.edit(subasta);
              
                
                
                
                
                
                
                // si la categoria antes no estaba y ahroa sí, lo introducimos ene la lsita, sino , lo borramos
           for(Categoria c : categoriasTotales){
               if(!categoriasAntiguas.contains(c)  && categoriasFinales.contains(c)){
                    List<Producto> p = new ArrayList<>();
                    p = c.getProductoList();
                    p.add(producto);
                    c.setProductoList(p);
                    this.cFacade.edit(c);
                   
                   
               }else if (categoriasAntiguas.contains(c)  && !categoriasFinales.contains(c)){
                     List<Producto> p = new ArrayList<>();
                    p = c.getProductoList();
                    p.remove(producto);
                    c.setProductoList(p);
                    this.cFacade.edit(c);
               }
               
               
               
           }
              
                
                
                
                
            } 
            
            
            
            
            response.sendRedirect(request.getContextPath() + "/NuevoProductoServlet");
                
                
                
                
            }
            
            
       
          
        } catch (ParseException ex) {
            Logger.getLogger(GuardarProductoSubastaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
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

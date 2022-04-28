/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author juanm
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> findFiltered(Integer filtro, String busqueda) {

        Query q;

        switch (filtro) {
            case (2): //nombre
                q = this.getEntityManager().createQuery("select a from Usuario a where a.nombre like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            case (3)://apellido
                q = this.getEntityManager().createQuery("select a from Usuario a where a.apellidos like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            case (4)://domicilio
                q = this.getEntityManager().createQuery("select a from Usuario a where a.domicilio like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            case (5)://ciudad
                q = this.getEntityManager().createQuery("select a from Usuario a where a.ciudad like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            case (6)://edad
                q = this.getEntityManager().createQuery("select a from Usuario a where a.edad = :busqueda");
                q.setParameter("busqueda", Integer.parseInt(busqueda));
                break;
            case (7)://genero
                q = this.getEntityManager().createQuery("select a from Usuario a where a.genero.genero like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            case (8)://tipoUsuario
                q = this.getEntityManager().createQuery("select a from Usuario a where a.tipoUsuario.tipoUsuario like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            default://nombreUsuario
                q = this.getEntityManager().createQuery("select a from Usuario a where a.nombreUsuario like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
        }

        return q.getResultList();
    }
   public Usuario comprobarUsuario(String usuario, String psw) {
        Query q;
        
        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombreUsuario  = :nombreUsuario and "
                + "u.contrasena = :contrasena");
        
        q.setParameter("usuario",usuario);
        q.setParameter("contrasena", psw);
        
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        
        }
   }
   //Todavía no se si funciona
    public void favouriteList(int add, String idUsuario, String idProducto) {
          
        Query q;
        switch (add){
            case(1):
             //Query para añadir el producto a favoritos
             
              q = this.getEntityManager().createQuery("INSERT INTO PRDOUCTOS_FAVORITOS VALUES ( :idUsuario, :idProducto");
              q.setParameter("idUsuario",idUsuario);
              q.setParameter("idProducto",idProducto);
              
              
             break;
              //Query para quitar el producto de la lista de 
             default:  
             
              q = this.getEntityManager().createQuery("DELETE FROM PRDOUCTOS_FAVORITOS WHERE Usuario_idUsuario = :idUsuario AND Producto_idProducto = :idProducto");
              q.setParameter("idUsuario",idUsuario);
              q.setParameter("idProducto",idProducto);
             break;
             
        }
    }   

   public List<Producto> getProductosVendedor(String idUser){
       Query q;
       int id = Integer.parseInt(idUser);
        q = this.getEntityManager().createQuery("select p from Subasta s join s.producto p where s.creador.idUsuario =:id");
        q.setParameter("id", id);
    
        return q.getResultList();
   }
   
   public List<Subasta> getSubastasVendedor(String idUser){
       Query q ;
       //int id = Integer.parseInt(idUser);
       int id= 1;
       q=this.getEntityManager().createQuery("select s from Subasta s where s.creador.idUsuario = :id");
                                              
       q.setParameter("id", id);
       return q.getResultList();
   }
   
   
   
}
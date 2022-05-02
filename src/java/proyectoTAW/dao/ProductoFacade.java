/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dao;


import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyectoTAW.entity.Categoria;
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author juanm
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }


    public List<Producto> findFiltered(Integer filtro, String busqueda) {

        Query q;

        switch (filtro) {
            case (1): 
                q = this.getEntityManager().createQuery("select a from Producto a where a.titulo like :busqueda");
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;
            
            default:
                q = this.getEntityManager().createQuery("select distinct a from Producto a JOIN a.categoriaList c WHERE c.nombre like :busqueda", Categoria.class);
                q.setParameter("busqueda", '%' + busqueda + '%');
                break;

            /*
                SELECT *
                FROM producto a
                JOIN categoriasproducto relation 
                ON a.idProducto = relation.idProducto
                JOIN categoria b 
                ON relation.idCategoria = b.idCategoria
                where b.nombre like :busqueda
             */
        }

        return q.getResultList();
    }

    //Todavía no se si funciona
    
    public List<Producto> findFavouriteProductList(String id) {
        //SELECT p.* from Producto p JOIN productos_favoritos pf ON p.idProducto = pf.Producto_idProducto WHERE pf.Usuario_idUsuario = 1;
       Query q;
       
       q = this.getEntityManager().createQuery("SELECT p FROM  productos_favoritos  pf JOIN pf.Producto_idProducto p  WHERE pf.Usuario_idUsuario = :idUser ",Producto.class);
       q.setParameter("idUser",id);
        
       return q.getResultList(); 
    }
    
    public List <Producto> findProductsComprados(String idUsuario, String titulo){
        Query q;
        //
        q = this.getEntityManager().createQuery("SELECT p FROM Subasta s JOIN s.producto p WHERE s.fechaCierre <= :today  AND s.mayorPostor = :user AND s.fechaCierre != null AND p.titulo like :busqueda");
        
        q.setParameter("today",new Date());
        q.setParameter("busqueda","%" + titulo +"%");
        q.setParameter("user",idUsuario);
        
        return q.getResultList();
    }


    public List<Producto> findProductsSubastaActiva(String titulo) {     
        /*
         SELECT p.* from Producto p 
            JOIN subasta s  ON p.idProducto = s.producto
            JOIN categoriasproducto cp ON cp.idProducto= p.idProducto
            JOIN categoria c ON cp.idCategoria = cp.idCategoria
            WHERE c.nombre LIKE "%%" AND s.fechaCierre >= sysdate() AND p.titulo LIKE "%%"
        */
        
       Query q;
       
       q = this.getEntityManager().createQuery("SELECT p FROM Subasta s JOIN s.producto p  WHERE s.fechaCierre >= :today AND p.titulo LIKE :busqueda",Producto.class);
       
       q.setParameter("today",new Date());
       q.setParameter("busqueda","%" + titulo +"%");
       
      
       return q.getResultList();
 
    }
    
    
   
}

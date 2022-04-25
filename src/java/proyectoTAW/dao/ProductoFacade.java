/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dao;


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

    public List<Producto> findProductList(int filtro, String idUsuario) {
       Query q;
       List <Producto> res = null;
        switch (filtro) {
            case (1): //devoler productos favoritos de usuario con id idUsuario
                
                //Obtengo una lisra de id de productos favotios por el usuario
                q = this.getEntityManager().createQuery("select Producto_idProducto from productos_favoritos WHERE Usuario_idUsuario = :idUsuario"); 
                q.setParameter("idUsuario", '%' + idUsuario + '%');
                
                List <String> idProductosFavoritos = q.getResultList();
                //Busco a que productos corresponden y lo añado a mi lisra
                for (String id : idProductosFavoritos){
                   
                    res.add(this.find(Integer.parseInt(id)));
                }
                
                break;
            default: //devolver productos comprados de usuario con id idUsuario
                
                //busco las subastas en las que ha participado y es el mayor postor
                q = this.getEntityManager().createQuery("select * from subasta WHERE mayorPostor = :idUsuario",Subasta.class);
                q.setParameter("idUsuario", '%' + idUsuario + '%');
                
                List <Subasta> subastasMayorPostor = q.getResultList();
                //Busco a que productos corresponden y lo añado a mi lista si la fecha está finalizada
                
                for (Subasta s : subastasMayorPostor){
                    long millis = System.currentTimeMillis();
                    java.util.Date now = new java.util.Date(millis);
                    
                    if (s.getFechaCierre().compareTo(now) >=1 ){
                        Producto producto = s.getProducto(); //obtengo el producto
                        res.add(producto);
                    }
                    
                }
                break;
        }
         return res;
    }

   
  

   
 

 
}

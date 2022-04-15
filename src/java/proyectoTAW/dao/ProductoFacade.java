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
                q = this.getEntityManager().createQuery("select distinct a from Producto a, Categoria c WHERE c.nombre like :busqueda");
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
}

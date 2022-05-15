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

/**
 *
 * @author juanm
 * getCategoriasLike
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public List<Categoria> getCategoriasLike(String busqueda){
        
        Query q;
        q = this.getEntityManager().createQuery("select a from Categoria a where a.nombre like :busqueda");
        q.setParameter("busqueda", '%'+busqueda+'%');
        
        return q.getResultList();
    }
}

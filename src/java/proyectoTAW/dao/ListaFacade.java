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
import proyectoTAW.entity.Lista;

/**
 *
 * @author Ferni
 */
@Stateless
public class ListaFacade extends AbstractFacade<Lista> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaFacade() {
        super(Lista.class);
    }

    public List<Lista> getListasLike(int busqueda) {
        Query q;
        q = this.getEntityManager().createQuery("select a from Lista a where a.categoriaidCategoria like :busqueda");
        q.setParameter("busqueda", '%'+busqueda+'%');
        
        return q.getResultList();
    }
    
}

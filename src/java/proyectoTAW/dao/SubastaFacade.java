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
import proyectoTAW.entity.Producto;
import proyectoTAW.entity.Subasta;

/**
 *
 * @author juanm
 */
@Stateless
public class SubastaFacade extends AbstractFacade<Subasta> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubastaFacade() {
        super(Subasta.class);
    }

    public List<Subasta> findSubastaActiva() {
       Query q;
             
       q = this.getEntityManager().createQuery("SELECT s FROM Subasta s  WHERE s.fechaCierre >= :today",Subasta.class);
       q.setParameter("today",new Date());
       
       return q.getResultList();
        
    }
   

    
    
}

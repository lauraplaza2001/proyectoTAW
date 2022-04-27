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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    
    
}

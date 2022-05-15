/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import proyectoTAW.entity.Tipousuario;


@Stateless
public class TipousuarioFacade extends AbstractFacade<Tipousuario> {

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipousuarioFacade() {
        super(Tipousuario.class);
    }
    
}

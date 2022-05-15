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
import proyectoTAW.entity.Notificacion;
import proyectoTAW.entity.Usuario;
/*
@autor Agustín
*/

@Stateless
public class NotificacionFacade extends AbstractFacade<Notificacion> {
    

    @PersistenceContext(unitName = "proyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }
    
    public List<Notificacion> findNotificacionesByUsuario(int idUsuario){
        Query q;
        q = this.getEntityManager().createQuery("Select a from Notificacion a where a.dueno.idUsuario=:idUsuario");
        q.setParameter("idUsuario", idUsuario);
        
        return q.getResultList();
    }
    
}

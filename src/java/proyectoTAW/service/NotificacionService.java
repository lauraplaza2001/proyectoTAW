/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import proyectoTAW.dao.NotificacionFacade;
import proyectoTAW.dto.ListaDTO;
import proyectoTAW.dto.NotificacionDTO;
import proyectoTAW.entity.Notificacion;

/**
 *
 * @author Agustín
 */
@Stateless
public class NotificacionService {
    @EJB NotificacionFacade notificacionFacade;
    
    public List<NotificacionDTO> toDTOList(List<Notificacion> notificacion) {

        List<NotificacionDTO> result = null;

        if (notificacion != null) {
            result = new ArrayList<>();
            for (Notificacion n : notificacion) {
                result.add(n.toDTO());
            }
        }

        return result;
    }
    
    public List<NotificacionDTO> findNotificacionesByUsuario(int idUsuario){
        return this.toDTOList(notificacionFacade.findNotificacionesByUsuario(idUsuario));
    }

    public void remove(int id) {
        Notificacion n = this.notificacionFacade.find(id);
        this.notificacionFacade.remove(n);
    }
}

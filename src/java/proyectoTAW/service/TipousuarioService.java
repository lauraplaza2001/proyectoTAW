/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import proyectoTAW.dto.TipousuarioDTO;
import proyectoTAW.entity.Tipousuario;

/**
 *
 * @author juanm
 */
@Stateless
public class TipousuarioService {

    public List<TipousuarioDTO> toDTOList(List<Tipousuario> tipoUsuario) {
        List<TipousuarioDTO> result = null;

        if (result != null) {
            result = new ArrayList<>();
            for (Tipousuario c : tipoUsuario) {
                result.add(c.toDTO());
            }
        }

        return result;
    }
  
}

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
import proyectoTAW.dao.GeneroFacade;
import proyectoTAW.dto.GeneroDTO;
import proyectoTAW.entity.Genero;

/**
 *
 * @author juanm
 */
@Stateless
public class GeneroService {

    @EJB GeneroFacade gFacade;
    
    public List<GeneroDTO> findAll() {
        return this.toDTOList(this.gFacade.findAll());
    }

    private List<GeneroDTO> toDTOList(List<Genero> generos) {
        List<GeneroDTO> result = null;

        if (generos != null) {
            result = new ArrayList<>();
            for (Genero c : generos) {
                result.add(c.toDTO());
            }
        }

        return result;
    }

}

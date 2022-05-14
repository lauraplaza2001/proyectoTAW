/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.service;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import proyectoTAW.dao.ListaFacade;
import proyectoTAW.dto.ListaDTO;
import proyectoTAW.entity.Categoria;
import proyectoTAW.entity.Lista;

/**
 *
 * @author Agustín
 */
@Stateless
public class ListaService {
    @EJB ListaFacade listaFacade;
    
    public List<ListaDTO> toDTOList(List<Lista> lista) {

        List<ListaDTO> result = null;

        if (lista != null) {
            result = new ArrayList<>();
            for (Lista c : lista) {
                result.add(c.toDTO());
            }
        }

        return result;
    }
    
    public List<ListaDTO> getListasLike(String busqueda) {

        List<Lista> result;

        result = this.listaFacade.getListasLike(parseInt(busqueda));

        return this.toDTOList(result);

    }

    public List<ListaDTO> findAll() {
        return this.toDTOList(this.listaFacade.findAll());
    }
    
    
}

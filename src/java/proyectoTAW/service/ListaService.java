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
import proyectoTAW.dao.CategoriaFacade;
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
    @EJB CategoriaFacade categoriaFacade;
    
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

        List<ListaDTO> listas;

        if (busqueda != null) {
            listas = this.toDTOList(this.listaFacade.getListasLike(busqueda));
        } else {
            listas = this.toDTOList(this.listaFacade.findAll());
        }

        return listas;
    }

    public List<ListaDTO> findAll() {
        return this.toDTOList(this.listaFacade.findAll());
    }

    public void editarLista(String id, String edit) {
        if (id != null && edit != null) {
            Lista lista = this.listaFacade.find(Integer.parseInt(id));
            lista.setNombre(edit);
            this.listaFacade.edit(lista);
        }
    }

    public void crearLista(String nombre, String categoria) {
        if (null != nombre) {
            Lista lista = new Lista();
            lista.setNombre(nombre);
            Categoria nuevaCategoria = this.categoriaFacade.getCategoriasLike(categoria).get(0);
            lista.setCategoriaidCategoria(nuevaCategoria);
            this.listaFacade.create(lista);
        }
    }
    
    
}

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
import proyectoTAW.dao.CategoriaFacade;
import proyectoTAW.dto.CategoriaDTO;
import proyectoTAW.entity.Categoria;

/**
 *
 * @author juanm
 */
@Stateless
public class CategoriaService {

    @EJB
    CategoriaFacade cFacade;

    public List<CategoriaDTO> toDTOList(List<Categoria> lista) {

        List<CategoriaDTO> result = null;

        if (lista != null) {
            result = new ArrayList<>();
            for (Categoria c : lista) {
                result.add(c.toDTO());
            }
        }

        return result;
    }

    public List<CategoriaDTO> getCategoriasLike(String busqueda) {

        List<Categoria> result;

        result = this.cFacade.getCategoriasLike(busqueda);

        return this.toDTOList(result);

    }

    public void crearCategoria(String str) {

        if (null != str) {
            Categoria categoria = new Categoria();
            categoria.setNombre(str);

            this.cFacade.create(categoria);
        }
    }

    public List<CategoriaDTO> findAll() {
        return this.toDTOList(this.cFacade.findAll());
    }

    public void editarCategoria(String id, String edit) {

        if (id != null && edit != null) {
            Categoria cat = this.cFacade.find(Integer.parseInt(id));
            cat.setNombre(edit);
            this.cFacade.edit(cat);
        }
    }

    public CategoriaDTO find(int id) {
        return this.cFacade.find(id).toDTO();
    }

    public void remove(int id) {
        this.cFacade.remove((this.cFacade.find(id)));
    }

    public List<Categoria> createlistFromIds(String[] categorias) {

        
         
        List<Categoria> categoriasFinales = new ArrayList<>();
        if (categorias != null) {
            for (String categoria : categorias) {
                categoriasFinales.add(this.cFacade.find(Integer.parseInt(categoria)));
            }
        }
    
        return categoriasFinales;
    }

}

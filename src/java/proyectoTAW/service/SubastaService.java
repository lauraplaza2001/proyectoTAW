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
import proyectoTAW.dao.SubastaFacade;
import proyectoTAW.dto.ProductoDTO;
import proyectoTAW.dto.SubastaDTO;
import proyectoTAW.entity.Subasta;

/**
 *
 * @author juanm
 */
@Stateless
public class SubastaService {
    
    
    @EJB SubastaFacade sFacade;

    
    public List<SubastaDTO> toDTOList(List<Subasta> lista){
        
        List<SubastaDTO> result = null;
        
        if(lista != null){
            result = new ArrayList<>();
            for(Subasta c :lista){
                result.add(c.toDTO(c));
            }
        }
        
        return result;
    }
    
    public SubastaDTO findByProduct(int idProducto){
        Subasta subasta =  (this.sFacade.findSubastasDelProducto(idProducto).get(0));
       
        return subasta.toDTO(subasta);
    }
    
      public List <SubastaDTO> SubastaActiva(String titulo,String categoria){
        List <Subasta> result = this.sFacade.findSubastaActivaFiltro(titulo,categoria);
        return this.toDTOList(result);
    }
    public List <SubastaDTO> SubastaProductosFavoritos (int id, String titulo,String categoria){
         List <Subasta> result = this.sFacade.findSubastaWithFavouriteProductList(id, titulo,categoria);
        return this.toDTOList(result);
    }
     public List <SubastaDTO> SubastaProductosComprados (int id, String titulo,String categoria){
         List <Subasta> result = this.sFacade.findSubastaWithProductsComprados(id, titulo,categoria);
        return this.toDTOList(result);
    }

}

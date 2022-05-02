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
import proyectoTAW.dao.ProductoFacade;
import proyectoTAW.dto.ProductoDTO;
import proyectoTAW.entity.Categoria;
import proyectoTAW.entity.Producto;

/**
 *
 * @author juanm
 */
@Stateless
public class ProductoService {

    @EJB ProductoFacade pFacade;
    @EJB CategoriaFacade cFacade;
    

    public List<ProductoDTO> toDTOList(List<Producto> lista) {

        List<ProductoDTO> result = null;

        if (lista != null) {
            result = new ArrayList<>();
            for (Producto p : lista) {
                result.add(p.toDTO());
            }
        }

        return result;
    }

    public List<ProductoDTO> findFiltered(Integer filtro, String like) {

        List<ProductoDTO> productos;
        if (like != null) {

            productos = this.toDTOList(this.pFacade.findFiltered(filtro, like));

        } else {
            productos = this.toDTOList(this.pFacade.findAll());
        }

        return productos;
    }

    public ProductoDTO find(int id) {
        return this.pFacade.find(id).toDTO();
    }
    
    public List<ProductoDTO> findAll() {
        return this.toDTOList(this.pFacade.findAll());
    }

    public void remove(int id) {
        this.pFacade.remove((this.pFacade.find(id)));
    }

    public void edit(int id,String title, String desc, String foto, double precio, List<Categoria> categoriasFinales) {
        Producto producto = this.pFacade.find(id);
              
        producto.setTitulo(title);
        producto.setDescripcion(desc);
        producto.setFoto(foto);
        producto.setPrecioSalida(precio);
        producto.setCategoriaList(categoriasFinales);
        
        this.pFacade.edit(producto);
        
    }
    public List <ProductoDTO> productosSubastaActiva(String titulo){
        List <Producto> result = this.pFacade.findProductsSubastaActiva(titulo);
        return this.toDTOList(result);
    }
    public List <ProductoDTO> productosFavoritos (int id, String titulo){
         List <Producto> result = this.pFacade.findFavouriteProductList(id, titulo);
        return this.toDTOList(result);
    }
     public List <ProductoDTO> productosComprados (int id, String titulo){
         List <Producto> result = this.pFacade.findProductsComprados(id, titulo);
        return this.toDTOList(result);
    }
    
    
    
    
}

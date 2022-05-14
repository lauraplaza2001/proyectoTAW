/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dto;

import java.util.List;
import proyectoTAW.entity.Categoria;
import proyectoTAW.entity.Usuario;

/**
 *
 * @author Ferni
 */
public class ListaDTO {
    private Integer idlista;
    private List<UsuarioDTO> usuarioList;
    private CategoriaDTO categoriaidCategoria;
    
    public ListaDTO(){
    }
    
    public ListaDTO(Integer idlista, List<UsuarioDTO> usuarioList, CategoriaDTO categoriaidCategoria){
        this.idlista = idlista;
        this.usuarioList = usuarioList;
        this.categoriaidCategoria = categoriaidCategoria;
    }
    
    public void setidlista(Integer id){
        this.idlista = id;
    }
    
    public Integer getidlista(){
        return this.idlista;
    }
    
    public void setusuarioList(List<UsuarioDTO> ul){
        this.usuarioList = ul;
    }
    
    public List<UsuarioDTO> getusuarioList(){
        return this.usuarioList;
    }
    
    public void setcategoriaidCategoria(CategoriaDTO cic){
        this.categoriaidCategoria = cic;
    }
    
    public CategoriaDTO getcategoriaidCategoria(){
        return this.categoriaidCategoria;
    }
}

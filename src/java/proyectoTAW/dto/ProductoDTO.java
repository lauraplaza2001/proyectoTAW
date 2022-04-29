/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.dto;

import java.util.List;

/**
 *
 * @author juanm
 */
public class ProductoDTO {
    
    private List<UsuarioDTO> usuarioList;
    private static final long serialVersionUID = 1L;
    private Integer idProducto;
    private String titulo;
    private String descripcion;
    private String foto;
    private double precioSalida;
    private List<CategoriaDTO> categoriaList;

    public ProductoDTO() {
    }

    public ProductoDTO(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public ProductoDTO(Integer idProducto, String titulo, String descripcion, String foto, double precioSalida) {
        this.idProducto = idProducto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precioSalida = precioSalida;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }
    
    public List<UsuarioDTO> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioDTO> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    public List<CategoriaDTO> getCategoriaList(){
        return categoriaList;
    }
    
    public void setCategoriaList(List<CategoriaDTO> categoriaList){
        this.categoriaList = categoriaList;
    }
}

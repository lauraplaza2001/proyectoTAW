/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import proyectoTAW.dto.ProductoDTO;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByFoto", query = "SELECT p FROM Producto p WHERE p.foto = :foto")
    , @NamedQuery(name = "Producto.findByPrecioSalida", query = "SELECT p FROM Producto p WHERE p.precioSalida = :precioSalida")})
public class Producto implements Serializable {

    @JoinColumn(name = "idComprador", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idComprador;
    

    @JoinTable(name = "productos_favoritos", joinColumns = {
        @JoinColumn(name = "Producto_idProducto", referencedColumnName = "idProducto")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<Usuario> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioSalida")
    private double precioSalida;
    @ManyToMany(mappedBy = "productoList")
    private List<Categoria> categoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Subasta> subastaList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String titulo, String descripcion, String foto, double precioSalida) {
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

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @XmlTransient
    public List<Subasta> getSubastaList() {
        return subastaList;
    }

    public void setSubastaList(List<Subasta> subastaList) {
        this.subastaList = subastaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Producto[ idProducto=" + idProducto + " ]";
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    public ProductoDTO toDTO() {
        ProductoDTO pr = new ProductoDTO();
        pr.setDescripcion(this.getDescripcion());
        pr.setFoto(this.getFoto());
        pr.setIdProducto(this.getIdProducto());
        pr.setPrecioSalida(this.getPrecioSalida());
        pr.setTitulo(this.getTitulo());
        pr.setCategoriaList(Categoria.toDTOList(this.getCategoriaList()));
        if (this.getIdComprador() != null){
            pr.setComprador(this.getIdComprador().toDTO());
        }
        
        
        return pr;
    }

    public Usuario getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Usuario idComprador) {
        this.idComprador = idComprador;
    }
}

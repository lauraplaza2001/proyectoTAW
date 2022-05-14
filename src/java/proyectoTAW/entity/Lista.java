/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import proyectoTAW.dto.ListaDTO;

/**
 *
 * @author Agustín
 */
@Entity
@Table(name = "lista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l")
    , @NamedQuery(name = "Lista.findByIdlista", query = "SELECT l FROM Lista l WHERE l.idlista = :idlista")})
public class Lista implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlista")
    private Integer idlista;
    @JoinTable(name = "listausuario", joinColumns = {
        @JoinColumn(name = "idlista", referencedColumnName = "idlista")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinColumn(name = "categoria_idCategoria", referencedColumnName = "idCategoria")
    @ManyToOne(optional = false)
    private Categoria categoriaidCategoria;

    public Lista() {
    }

    public Lista(Integer idlista) {
        this.idlista = idlista;
    }

    public Integer getIdlista() {
        return idlista;
    }

    public void setIdlista(Integer idlista) {
        this.idlista = idlista;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Categoria getCategoriaidCategoria() {
        return categoriaidCategoria;
    }

    public void setCategoriaidCategoria(Categoria categoriaidCategoria) {
        this.categoriaidCategoria = categoriaidCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlista != null ? idlista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.idlista == null && other.idlista != null) || (this.idlista != null && !this.idlista.equals(other.idlista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Lista[ idlista=" + idlista + " ]";
    }
    
    public ListaDTO toDTO(){
        ListaDTO ld = new ListaDTO();
        ld.setidlista(this.getIdlista());
        ld.setusuarioList(Usuario.toDTOList(this.getUsuarioList()));
        ld.setcategoriaidCategoria(this.getCategoriaidCategoria().toDTO());
        return ld;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

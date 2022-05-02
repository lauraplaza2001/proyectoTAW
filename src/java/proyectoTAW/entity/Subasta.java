/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoTAW.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import proyectoTAW.dto.SubastaDTO;

/**
 *
 * @author juanm
 */
@Entity
@Table(name = "subasta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subasta.findAll", query = "SELECT s FROM Subasta s")
    , @NamedQuery(name = "Subasta.findByIdSubasta", query = "SELECT s FROM Subasta s WHERE s.idSubasta = :idSubasta")
    , @NamedQuery(name = "Subasta.findByPredioActual", query = "SELECT s FROM Subasta s WHERE s.predioActual = :predioActual")
    , @NamedQuery(name = "Subasta.findByFechaCierre", query = "SELECT s FROM Subasta s WHERE s.fechaCierre = :fechaCierre")})
public class Subasta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSubasta")
    private Integer idSubasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "predioActual")
    private double predioActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaCierre")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @JoinColumn(name = "producto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "mayorPostor", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario mayorPostor;
    @JoinColumn(name = "creador", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario creador;

    public Subasta() {
    }

    public Subasta(Integer idSubasta) {
        this.idSubasta = idSubasta;
    }

    public Subasta(Integer idSubasta, double predioActual, Date fechaCierre) {
        this.idSubasta = idSubasta;
        this.predioActual = predioActual;
        this.fechaCierre = fechaCierre;
    }

    public Integer getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(Integer idSubasta) {
        this.idSubasta = idSubasta;
    }

    public double getPredioActual() {
        return predioActual;
    }

    public void setPredioActual(double predioActual) {
        this.predioActual = predioActual;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getMayorPostor() {
        return mayorPostor;
    }

    public void setMayorPostor(Usuario mayorPostor) {
        this.mayorPostor = mayorPostor;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubasta != null ? idSubasta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subasta)) {
            return false;
        }
        Subasta other = (Subasta) object;
        if ((this.idSubasta == null && other.idSubasta != null) || (this.idSubasta != null && !this.idSubasta.equals(other.idSubasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Subasta[ idSubasta=" + idSubasta + " ]";
    }

    public SubastaDTO toDTO(Subasta sub) {
        SubastaDTO s = new SubastaDTO();
        s.setCreador(sub.getCreador().toDTO());
        s.setFechaCierre(sub.getFechaCierre());
        s.setIdSubasta(sub.getIdSubasta());
        s.setMayorPostor(sub.getMayorPostor().toDTO());
        s.setPredioActual(sub.getPredioActual());
        s.setProducto(sub.getProducto().toDTO());

        return s;
    }
}

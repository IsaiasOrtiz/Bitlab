/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author elcon
 */
@Entity
@Table(name = "BL_IT_INTERMEDICACION", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "EstadoIntermedicacion.findAll", query = "SELECT e FROM EstadoIntermedicacion e"),
    @NamedQuery(name = "EstadoIntermedicacion.findByItId", query = "SELECT e FROM EstadoIntermedicacion e WHERE e.itId = :itId"),
    @NamedQuery(name = "EstadoIntermedicacion.findByItNombre", query = "SELECT e FROM EstadoIntermedicacion e WHERE e.itNombre = :itNombre"),
    @NamedQuery(name = "EstadoIntermedicacion.findByAUsuarioCrea", query = "SELECT e FROM EstadoIntermedicacion e WHERE e.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "EstadoIntermedicacion.findByAFechaCreacion", query = "SELECT e FROM EstadoIntermedicacion e WHERE e.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "EstadoIntermedicacion.findByAFechaModificacion", query = "SELECT e FROM EstadoIntermedicacion e WHERE e.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "EstadoIntermedicacion.findByAUsuarioModifica", query = "SELECT e FROM EstadoIntermedicacion e WHERE e.aUsuarioModifica = :aUsuarioModifica")})
public class EstadoIntermedicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IT_ID", nullable = false)
    private Integer itId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IT_NOMBRE", nullable = false, length = 50)
    private String itNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "A_USUARIO_CREA", nullable = false, length = 50)
    private String aUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "A_FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date aFechaCreacion;
    @Column(name = "A_FECHA_MODIFICACION")
    @Temporal(TemporalType.DATE)
    private Date aFechaModificacion;
    @Size(max = 50)
    @Column(name = "A_USUARIO_MODIFICA", length = 50)
    private String aUsuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itId", fetch = FetchType.LAZY)
    private List<IntermediacionLaboral> intermediacionLaboralList;

    public EstadoIntermedicacion() {
    }

    public EstadoIntermedicacion(Integer itId) {
        this.itId = itId;
    }

    public EstadoIntermedicacion(Integer itId, String itNombre, String aUsuarioCrea, Date aFechaCreacion) {
        this.itId = itId;
        this.itNombre = itNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getItId() {
        return itId;
    }

    public void setItId(Integer itId) {
        this.itId = itId;
    }

    public String getItNombre() {
        return itNombre;
    }

    public void setItNombre(String itNombre) {
        this.itNombre = itNombre;
    }

    public String getAUsuarioCrea() {
        return aUsuarioCrea;
    }

    public void setAUsuarioCrea(String aUsuarioCrea) {
        this.aUsuarioCrea = aUsuarioCrea;
    }

    public Date getAFechaCreacion() {
        return aFechaCreacion;
    }

    public void setAFechaCreacion(Date aFechaCreacion) {
        this.aFechaCreacion = aFechaCreacion;
    }

    public Date getAFechaModificacion() {
        return aFechaModificacion;
    }

    public void setAFechaModificacion(Date aFechaModificacion) {
        this.aFechaModificacion = aFechaModificacion;
    }

    public String getAUsuarioModifica() {
        return aUsuarioModifica;
    }

    public void setAUsuarioModifica(String aUsuarioModifica) {
        this.aUsuarioModifica = aUsuarioModifica;
    }

    public List<IntermediacionLaboral> getIntermediacionLaboralList() {
        return intermediacionLaboralList;
    }

    public void setIntermediacionLaboralList(List<IntermediacionLaboral> intermediacionLaboralList) {
        this.intermediacionLaboralList = intermediacionLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itId != null ? itId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoIntermedicacion)) {
            return false;
        }
        EstadoIntermedicacion other = (EstadoIntermedicacion) object;
        if ((this.itId == null && other.itId != null) || (this.itId != null && !this.itId.equals(other.itId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.EstadoIntermedicacion[ itId=" + itId + " ]";
    }
    
}

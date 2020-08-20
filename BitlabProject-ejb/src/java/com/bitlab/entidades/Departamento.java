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
@Table(name = "BL_DP_DEPARTAMENTO", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByDpId", query = "SELECT d FROM Departamento d WHERE d.dpId = :dpId"),
    @NamedQuery(name = "Departamento.findByDpNombre", query = "SELECT d FROM Departamento d WHERE d.dpNombre = :dpNombre"),
    @NamedQuery(name = "Departamento.findByAUsuarioCrea", query = "SELECT d FROM Departamento d WHERE d.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Departamento.findByAFechaCreacion", query = "SELECT d FROM Departamento d WHERE d.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Departamento.findByAFechaModificacion", query = "SELECT d FROM Departamento d WHERE d.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Departamento.findByAUsuarioModifica", query = "SELECT d FROM Departamento d WHERE d.aUsuarioModifica = :aUsuarioModifica")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DP_ID", nullable = false)
    private Integer dpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DP_NOMBRE", nullable = false, length = 50)
    private String dpNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "A_USUARIO_CREA", nullable = false, length = 100)
    private String aUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "A_FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date aFechaCreacion;
    @Column(name = "A_FECHA_MODIFICACION")
    @Temporal(TemporalType.DATE)
    private Date aFechaModificacion;
    @Size(max = 100)
    @Column(name = "A_USUARIO_MODIFICA", length = 100)
    private String aUsuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dpId", fetch = FetchType.LAZY)
    private List<Municipio> municipioList;

    public Departamento() {
    }

    public Departamento(Integer dpId) {
        this.dpId = dpId;
    }

    public Departamento(Integer dpId, String dpNombre, String aUsuarioCrea, Date aFechaCreacion) {
        this.dpId = dpId;
        this.dpNombre = dpNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public String getDpNombre() {
        return dpNombre;
    }

    public void setDpNombre(String dpNombre) {
        this.dpNombre = dpNombre;
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

    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dpId != null ? dpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.dpId == null && other.dpId != null) || (this.dpId != null && !this.dpId.equals(other.dpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Departamento[ dpId=" + dpId + " ]";
    }
    
}

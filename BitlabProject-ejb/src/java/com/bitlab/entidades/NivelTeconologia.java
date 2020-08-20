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
@Table(name = "BL_NV_NIVEL", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "NivelTeconologia.findAll", query = "SELECT n FROM NivelTeconologia n"),
    @NamedQuery(name = "NivelTeconologia.findByNvId", query = "SELECT n FROM NivelTeconologia n WHERE n.nvId = :nvId"),
    @NamedQuery(name = "NivelTeconologia.findByNvNombre", query = "SELECT n FROM NivelTeconologia n WHERE n.nvNombre = :nvNombre"),
    @NamedQuery(name = "NivelTeconologia.findByAUsuarioCrea", query = "SELECT n FROM NivelTeconologia n WHERE n.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "NivelTeconologia.findByAFechaCreacion", query = "SELECT n FROM NivelTeconologia n WHERE n.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "NivelTeconologia.findByAFechaModificacion", query = "SELECT n FROM NivelTeconologia n WHERE n.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "NivelTeconologia.findByAUsuarioModifica", query = "SELECT n FROM NivelTeconologia n WHERE n.aUsuarioModifica = :aUsuarioModifica")})
public class NivelTeconologia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NV_ID", nullable = false)
    private Integer nvId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NV_NOMBRE", nullable = false, length = 50)
    private String nvNombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvId", fetch = FetchType.LAZY)
    private List<TecnologiasManejadas> tecnologiasManejadasList;

    public NivelTeconologia() {
    }

    public NivelTeconologia(Integer nvId) {
        this.nvId = nvId;
    }

    public NivelTeconologia(Integer nvId, String nvNombre, String aUsuarioCrea, Date aFechaCreacion) {
        this.nvId = nvId;
        this.nvNombre = nvNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getNvId() {
        return nvId;
    }

    public void setNvId(Integer nvId) {
        this.nvId = nvId;
    }

    public String getNvNombre() {
        return nvNombre;
    }

    public void setNvNombre(String nvNombre) {
        this.nvNombre = nvNombre;
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

    public List<TecnologiasManejadas> getTecnologiasManejadasList() {
        return tecnologiasManejadasList;
    }

    public void setTecnologiasManejadasList(List<TecnologiasManejadas> tecnologiasManejadasList) {
        this.tecnologiasManejadasList = tecnologiasManejadasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvId != null ? nvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelTeconologia)) {
            return false;
        }
        NivelTeconologia other = (NivelTeconologia) object;
        if ((this.nvId == null && other.nvId != null) || (this.nvId != null && !this.nvId.equals(other.nvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.NivelTeconologia[ nvId=" + nvId + " ]";
    }
    
}

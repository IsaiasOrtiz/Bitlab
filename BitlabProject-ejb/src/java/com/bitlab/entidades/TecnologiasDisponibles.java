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
@Table(name = "BL_TGD_TECNOLOGIAS_DISPONIBLES", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "TecnologiasDisponibles.findAll", query = "SELECT t FROM TecnologiasDisponibles t"),
    @NamedQuery(name = "TecnologiasDisponibles.findByTgdId", query = "SELECT t FROM TecnologiasDisponibles t WHERE t.tgdId = :tgdId"),
    @NamedQuery(name = "TecnologiasDisponibles.findByTgdNombre", query = "SELECT t FROM TecnologiasDisponibles t WHERE t.tgdNombre = :tgdNombre"),
    @NamedQuery(name = "TecnologiasDisponibles.findByAUsuarioCrea", query = "SELECT t FROM TecnologiasDisponibles t WHERE t.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "TecnologiasDisponibles.findByAFechaCreacion", query = "SELECT t FROM TecnologiasDisponibles t WHERE t.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "TecnologiasDisponibles.findByAFechaModificacion", query = "SELECT t FROM TecnologiasDisponibles t WHERE t.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "TecnologiasDisponibles.findByAUsuarioModifica", query = "SELECT t FROM TecnologiasDisponibles t WHERE t.aUsuarioModifica = :aUsuarioModifica")})
public class TecnologiasDisponibles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TGD_ID", nullable = false)
    private Integer tgdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TGD_NOMBRE", nullable = false, length = 100)
    private String tgdNombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tgdId", fetch = FetchType.LAZY)
    private List<TecnologiasManejadas> tecnologiasManejadasList;

    public TecnologiasDisponibles() {
    }

    public TecnologiasDisponibles(Integer tgdId) {
        this.tgdId = tgdId;
    }

    public TecnologiasDisponibles(Integer tgdId, String tgdNombre, String aUsuarioCrea, Date aFechaCreacion) {
        this.tgdId = tgdId;
        this.tgdNombre = tgdNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getTgdId() {
        return tgdId;
    }

    public void setTgdId(Integer tgdId) {
        this.tgdId = tgdId;
    }

    public String getTgdNombre() {
        return tgdNombre;
    }

    public void setTgdNombre(String tgdNombre) {
        this.tgdNombre = tgdNombre;
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
        hash += (tgdId != null ? tgdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnologiasDisponibles)) {
            return false;
        }
        TecnologiasDisponibles other = (TecnologiasDisponibles) object;
        if ((this.tgdId == null && other.tgdId != null) || (this.tgdId != null && !this.tgdId.equals(other.tgdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.TecnologiasDisponibles[ tgdId=" + tgdId + " ]";
    }
    
}

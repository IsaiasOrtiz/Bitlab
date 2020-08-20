/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.Size;

/**
 *
 * @author elcon
 */
@Entity
@Table(name = "BL_TG_TECNOLOGIAS_MANEJADAS", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "TecnologiasManejadas.findAll", query = "SELECT t FROM TecnologiasManejadas t"),
    @NamedQuery(name = "TecnologiasManejadas.findByTgId", query = "SELECT t FROM TecnologiasManejadas t WHERE t.tgId = :tgId"),
    @NamedQuery(name = "TecnologiasManejadas.findByAUsuarioCrea", query = "SELECT t FROM TecnologiasManejadas t WHERE t.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "TecnologiasManejadas.findByAFechaCreacion", query = "SELECT t FROM TecnologiasManejadas t WHERE t.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "TecnologiasManejadas.findByAFechaModificacion", query = "SELECT t FROM TecnologiasManejadas t WHERE t.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "TecnologiasManejadas.findByAUsuarioModifica", query = "SELECT t FROM TecnologiasManejadas t WHERE t.aUsuarioModifica = :aUsuarioModifica")})
public class TecnologiasManejadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TG_ID", nullable = false)
    private Integer tgId;
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
    @JoinColumn(name = "ES_ID", referencedColumnName = "ES_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante esId;
    @JoinColumn(name = "NV_ID", referencedColumnName = "NV_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NivelTecnologia nvId;
    @JoinColumn(name = "TGD_ID", referencedColumnName = "TGD_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TecnologiasDisponibles tgdId;

    public TecnologiasManejadas() {
    }

    public TecnologiasManejadas(Integer tgId) {
        this.tgId = tgId;
    }

    public TecnologiasManejadas(Integer tgId, String aUsuarioCrea, Date aFechaCreacion) {
        this.tgId = tgId;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getTgId() {
        return tgId;
    }

    public void setTgId(Integer tgId) {
        this.tgId = tgId;
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

    public Estudiante getEsId() {
        return esId;
    }

    public void setEsId(Estudiante esId) {
        this.esId = esId;
    }

    public NivelTecnologia getNvId() {
        return nvId;
    }

    public void setNvId(NivelTecnologia nvId) {
        this.nvId = nvId;
    }

    public TecnologiasDisponibles getTgdId() {
        return tgdId;
    }

    public void setTgdId(TecnologiasDisponibles tgdId) {
        this.tgdId = tgdId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tgId != null ? tgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TecnologiasManejadas)) {
            return false;
        }
        TecnologiasManejadas other = (TecnologiasManejadas) object;
        if ((this.tgId == null && other.tgId != null) || (this.tgId != null && !this.tgId.equals(other.tgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.TecnologiasManejadas[ tgId=" + tgId + " ]";
    }
    
}

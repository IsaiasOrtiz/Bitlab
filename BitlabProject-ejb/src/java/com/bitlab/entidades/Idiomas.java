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
@Table(name = "BL_NE_NIVEL_IDIOMA", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Idiomas.findAll", query = "SELECT i FROM Idiomas i"),
    @NamedQuery(name = "Idiomas.findByNeId", query = "SELECT i FROM Idiomas i WHERE i.neId = :neId"),
    @NamedQuery(name = "Idiomas.findByAUsuarioCrea", query = "SELECT i FROM Idiomas i WHERE i.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Idiomas.findByAFechaCreacion", query = "SELECT i FROM Idiomas i WHERE i.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Idiomas.findByAFechaModificacion", query = "SELECT i FROM Idiomas i WHERE i.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Idiomas.findByAUsuarioModifica", query = "SELECT i FROM Idiomas i WHERE i.aUsuarioModifica = :aUsuarioModifica")})
public class Idiomas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NE_ID", nullable = false)
    private Integer neId;
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
    @JoinColumn(name = "IDM_ID", referencedColumnName = "IDM_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Idioma idmId;
    @JoinColumn(name = "ES_ID", referencedColumnName = "ES_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante esId;
    @JoinColumn(name = "N_ID", referencedColumnName = "N_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NivelIdioma nId;

    public Idiomas() {
    }

    public Idiomas(Integer neId) {
        this.neId = neId;
    }

    public Idiomas(Integer neId, String aUsuarioCrea, Date aFechaCreacion) {
        this.neId = neId;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getNeId() {
        return neId;
    }

    public void setNeId(Integer neId) {
        this.neId = neId;
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

    public Idioma getIdmId() {
        return idmId;
    }

    public void setIdmId(Idioma idmId) {
        this.idmId = idmId;
    }

    public Estudiante getEsId() {
        return esId;
    }

    public void setEsId(Estudiante esId) {
        this.esId = esId;
    }

    public NivelIdioma getNId() {
        return nId;
    }

    public void setNId(NivelIdioma nId) {
        this.nId = nId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (neId != null ? neId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idiomas)) {
            return false;
        }
        Idiomas other = (Idiomas) object;
        if ((this.neId == null && other.neId != null) || (this.neId != null && !this.neId.equals(other.neId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Idiomas[ neId=" + neId + " ]";
    }
    
}

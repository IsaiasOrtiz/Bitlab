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
import javax.persistence.Lob;
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
@Table(name = "BL_FC_FICHEROS", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ficheros.findAll", query = "SELECT f FROM Ficheros f"),
    @NamedQuery(name = "Ficheros.findByFcId", query = "SELECT f FROM Ficheros f WHERE f.fcId = :fcId"),
    @NamedQuery(name = "Ficheros.findByAUsuarioCrea", query = "SELECT f FROM Ficheros f WHERE f.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Ficheros.findByAFechaCreacion", query = "SELECT f FROM Ficheros f WHERE f.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Ficheros.findByAFechaModificacion", query = "SELECT f FROM Ficheros f WHERE f.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Ficheros.findByAUsuarioModifica", query = "SELECT f FROM Ficheros f WHERE f.aUsuarioModifica = :aUsuarioModifica")})
public class Ficheros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FC_ID", nullable = false)
    private Integer fcId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "FICHERO", nullable = false)
    private byte[] fichero;
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
    @JoinColumn(name = "PR_ID", referencedColumnName = "PR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Preseleccion prId;

    public Ficheros() {
    }

    public Ficheros(Integer fcId) {
        this.fcId = fcId;
    }

    public Ficheros(Integer fcId, byte[] fichero, String aUsuarioCrea, Date aFechaCreacion) {
        this.fcId = fcId;
        this.fichero = fichero;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getFcId() {
        return fcId;
    }

    public void setFcId(Integer fcId) {
        this.fcId = fcId;
    }

    public byte[] getFichero() {
        return fichero;
    }

    public void setFichero(byte[] fichero) {
        this.fichero = fichero;
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

    public Preseleccion getPrId() {
        return prId;
    }

    public void setPrId(Preseleccion prId) {
        this.prId = prId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fcId != null ? fcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficheros)) {
            return false;
        }
        Ficheros other = (Ficheros) object;
        if ((this.fcId == null && other.fcId != null) || (this.fcId != null && !this.fcId.equals(other.fcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Ficheros[ fcId=" + fcId + " ]";
    }
    
}

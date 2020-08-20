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
@Table(name = "BL_ESD_ESCOLARIDAD", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Escolaridad.findAll", query = "SELECT e FROM Escolaridad e"),
    @NamedQuery(name = "Escolaridad.findByEsdId", query = "SELECT e FROM Escolaridad e WHERE e.esdId = :esdId"),
    @NamedQuery(name = "Escolaridad.findByEsNombre", query = "SELECT e FROM Escolaridad e WHERE e.esNombre = :esNombre"),
    @NamedQuery(name = "Escolaridad.findByAUsuarioCrea", query = "SELECT e FROM Escolaridad e WHERE e.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Escolaridad.findByAFechaCreacion", query = "SELECT e FROM Escolaridad e WHERE e.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Escolaridad.findByAFechaModificacion", query = "SELECT e FROM Escolaridad e WHERE e.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Escolaridad.findByAUsuarioModifica", query = "SELECT e FROM Escolaridad e WHERE e.aUsuarioModifica = :aUsuarioModifica")})
public class Escolaridad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESD_ID", nullable = false)
    private Integer esdId;
    @Size(max = 50)
    @Column(name = "ES_NOMBRE", length = 50)
    private String esNombre;
    @Size(max = 100)
    @Column(name = "A_USUARIO_CREA", length = 100)
    private String aUsuarioCrea;
    @Column(name = "A_FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date aFechaCreacion;
    @Column(name = "A_FECHA_MODIFICACION")
    @Temporal(TemporalType.DATE)
    private Date aFechaModificacion;
    @Size(max = 100)
    @Column(name = "A_USUARIO_MODIFICA", length = 100)
    private String aUsuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esdId", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;

    public Escolaridad() {
    }

    public Escolaridad(Integer esdId) {
        this.esdId = esdId;
    }

    public Integer getEsdId() {
        return esdId;
    }

    public void setEsdId(Integer esdId) {
        this.esdId = esdId;
    }

    public String getEsNombre() {
        return esNombre;
    }

    public void setEsNombre(String esNombre) {
        this.esNombre = esNombre;
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

    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esdId != null ? esdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escolaridad)) {
            return false;
        }
        Escolaridad other = (Escolaridad) object;
        if ((this.esdId == null && other.esdId != null) || (this.esdId != null && !this.esdId.equals(other.esdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Escolaridad[ esdId=" + esdId + " ]";
    }
    
}

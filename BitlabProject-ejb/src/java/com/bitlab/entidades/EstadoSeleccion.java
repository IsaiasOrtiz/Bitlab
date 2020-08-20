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
@Table(name = "BL_ESN_ESTADO_SELECCION", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "EstadoSeleccion.findAll", query = "SELECT e FROM EstadoSeleccion e"),
    @NamedQuery(name = "EstadoSeleccion.findByEsnId", query = "SELECT e FROM EstadoSeleccion e WHERE e.esnId = :esnId"),
    @NamedQuery(name = "EstadoSeleccion.findByEsnNombre", query = "SELECT e FROM EstadoSeleccion e WHERE e.esnNombre = :esnNombre"),
    @NamedQuery(name = "EstadoSeleccion.findByAUsuarioCrea", query = "SELECT e FROM EstadoSeleccion e WHERE e.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "EstadoSeleccion.findByAFechaModificacion", query = "SELECT e FROM EstadoSeleccion e WHERE e.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "EstadoSeleccion.findByAFechaCreacion", query = "SELECT e FROM EstadoSeleccion e WHERE e.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "EstadoSeleccion.findByAUsuarioModifica", query = "SELECT e FROM EstadoSeleccion e WHERE e.aUsuarioModifica = :aUsuarioModifica")})
public class EstadoSeleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESN_ID", nullable = false)
    private Integer esnId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ESN_NOMBRE", nullable = false, length = 150)
    private String esnNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "A_USUARIO_CREA", nullable = false, length = 100)
    private String aUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "A_FECHA_MODIFICACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date aFechaModificacion;
    @Column(name = "A_FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date aFechaCreacion;
    @Size(max = 100)
    @Column(name = "A_USUARIO_MODIFICA", length = 100)
    private String aUsuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esnId", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;

    public EstadoSeleccion() {
    }

    public EstadoSeleccion(Integer esnId) {
        this.esnId = esnId;
    }

    public EstadoSeleccion(Integer esnId, String esnNombre, String aUsuarioCrea, Date aFechaModificacion) {
        this.esnId = esnId;
        this.esnNombre = esnNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaModificacion = aFechaModificacion;
    }

    public Integer getEsnId() {
        return esnId;
    }

    public void setEsnId(Integer esnId) {
        this.esnId = esnId;
    }

    public String getEsnNombre() {
        return esnNombre;
    }

    public void setEsnNombre(String esnNombre) {
        this.esnNombre = esnNombre;
    }

    public String getAUsuarioCrea() {
        return aUsuarioCrea;
    }

    public void setAUsuarioCrea(String aUsuarioCrea) {
        this.aUsuarioCrea = aUsuarioCrea;
    }

    public Date getAFechaModificacion() {
        return aFechaModificacion;
    }

    public void setAFechaModificacion(Date aFechaModificacion) {
        this.aFechaModificacion = aFechaModificacion;
    }

    public Date getAFechaCreacion() {
        return aFechaCreacion;
    }

    public void setAFechaCreacion(Date aFechaCreacion) {
        this.aFechaCreacion = aFechaCreacion;
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
        hash += (esnId != null ? esnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSeleccion)) {
            return false;
        }
        EstadoSeleccion other = (EstadoSeleccion) object;
        if ((this.esnId == null && other.esnId != null) || (this.esnId != null && !this.esnId.equals(other.esnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.EstadoSeleccion[ esnId=" + esnId + " ]";
    }
    
}

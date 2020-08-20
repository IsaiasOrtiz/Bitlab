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
@Table(name = "BL_RL_ROL", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByRlId", query = "SELECT r FROM Rol r WHERE r.rlId = :rlId"),
    @NamedQuery(name = "Rol.findByRlNombre", query = "SELECT r FROM Rol r WHERE r.rlNombre = :rlNombre"),
    @NamedQuery(name = "Rol.findByAUsuarioCrea", query = "SELECT r FROM Rol r WHERE r.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Rol.findByAFechaModificacion", query = "SELECT r FROM Rol r WHERE r.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Rol.findByAFechaCreacion", query = "SELECT r FROM Rol r WHERE r.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Rol.findByAUsuarioModifica", query = "SELECT r FROM Rol r WHERE r.aUsuarioModifica = :aUsuarioModifica")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RL_ID", nullable = false)
    private Integer rlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "RL_NOMBRE", nullable = false, length = 35)
    private String rlNombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rlId", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;

    public Rol() {
    }

    public Rol(Integer rlId) {
        this.rlId = rlId;
    }

    public Rol(Integer rlId, String rlNombre, String aUsuarioCrea, Date aFechaModificacion) {
        this.rlId = rlId;
        this.rlNombre = rlNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaModificacion = aFechaModificacion;
    }

    public Integer getRlId() {
        return rlId;
    }

    public void setRlId(Integer rlId) {
        this.rlId = rlId;
    }

    public String getRlNombre() {
        return rlNombre;
    }

    public void setRlNombre(String rlNombre) {
        this.rlNombre = rlNombre;
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
        hash += (rlId != null ? rlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rlId == null && other.rlId != null) || (this.rlId != null && !this.rlId.equals(other.rlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Rol[ rlId=" + rlId + " ]";
    }
    
}

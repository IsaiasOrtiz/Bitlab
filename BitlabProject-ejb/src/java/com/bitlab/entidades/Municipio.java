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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BL_MP_MUNICIPIO", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByMpId", query = "SELECT m FROM Municipio m WHERE m.mpId = :mpId"),
    @NamedQuery(name = "Municipio.findByMpNombre", query = "SELECT m FROM Municipio m WHERE m.mpNombre = :mpNombre"),
    @NamedQuery(name = "Municipio.findByAUsuarioCrea", query = "SELECT m FROM Municipio m WHERE m.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Municipio.findByAFechaCreacion", query = "SELECT m FROM Municipio m WHERE m.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Municipio.findByAFechaModificacion", query = "SELECT m FROM Municipio m WHERE m.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Municipio.findByAUsuarioModifica", query = "SELECT m FROM Municipio m WHERE m.aUsuarioModifica = :aUsuarioModifica")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MP_ID", nullable = false)
    private Integer mpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MP_NOMBRE", nullable = false, length = 50)
    private String mpNombre;
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
    @JoinColumn(name = "DP_ID", referencedColumnName = "DP_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamento dpId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpId", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;

    public Municipio() {
    }

    public Municipio(Integer mpId) {
        this.mpId = mpId;
    }

    public Municipio(Integer mpId, String mpNombre, String aUsuarioCrea, Date aFechaCreacion) {
        this.mpId = mpId;
        this.mpNombre = mpNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public String getMpNombre() {
        return mpNombre;
    }

    public void setMpNombre(String mpNombre) {
        this.mpNombre = mpNombre;
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

    public Departamento getDpId() {
        return dpId;
    }

    public void setDpId(Departamento dpId) {
        this.dpId = dpId;
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
        hash += (mpId != null ? mpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.mpId == null && other.mpId != null) || (this.mpId != null && !this.mpId.equals(other.mpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Municipio[ mpId=" + mpId + " ]";
    }
    
}

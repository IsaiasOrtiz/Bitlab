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
@Table(name = "BL_HR_HORARIO", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHrId", query = "SELECT h FROM Horario h WHERE h.hrId = :hrId"),
    @NamedQuery(name = "Horario.findByHrDias", query = "SELECT h FROM Horario h WHERE h.hrDias = :hrDias"),
    @NamedQuery(name = "Horario.findByHrHoraInicio", query = "SELECT h FROM Horario h WHERE h.hrHoraInicio = :hrHoraInicio"),
    @NamedQuery(name = "Horario.findByHrHoraFin", query = "SELECT h FROM Horario h WHERE h.hrHoraFin = :hrHoraFin"),
    @NamedQuery(name = "Horario.findByAUsuarioCrea", query = "SELECT h FROM Horario h WHERE h.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Horario.findByAFechaModificacion", query = "SELECT h FROM Horario h WHERE h.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Horario.findByAFechaCreacion", query = "SELECT h FROM Horario h WHERE h.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Horario.findByAUsuarioModifica", query = "SELECT h FROM Horario h WHERE h.aUsuarioModifica = :aUsuarioModifica")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HR_ID", nullable = false)
    private Integer hrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "HR_DIAS", nullable = false, length = 25)
    private String hrDias;
    @Column(name = "HR_HORA_INICIO")
    @Temporal(TemporalType.TIME)
    private Date hrHoraInicio;
    @Column(name = "HR_HORA_FIN")
    @Temporal(TemporalType.TIME)
    private Date hrHoraFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "A_USUARIO_CREA", nullable = false, length = 100)
    private String aUsuarioCrea;
    @Column(name = "A_FECHA_MODIFICACION")
    @Temporal(TemporalType.DATE)
    private Date aFechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "A_FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date aFechaCreacion;
    @Size(max = 100)
    @Column(name = "A_USUARIO_MODIFICA", length = 100)
    private String aUsuarioModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hrId", fetch = FetchType.LAZY)
    private List<Curso> cursoList;

    public Horario() {
    }

    public Horario(Integer hrId) {
        this.hrId = hrId;
    }

    public Horario(Integer hrId, String hrDias, String aUsuarioCrea, Date aFechaCreacion) {
        this.hrId = hrId;
        this.hrDias = hrDias;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public String getHrDias() {
        return hrDias;
    }

    public void setHrDias(String hrDias) {
        this.hrDias = hrDias;
    }

    public Date getHrHoraInicio() {
        return hrHoraInicio;
    }

    public void setHrHoraInicio(Date hrHoraInicio) {
        this.hrHoraInicio = hrHoraInicio;
    }

    public Date getHrHoraFin() {
        return hrHoraFin;
    }

    public void setHrHoraFin(Date hrHoraFin) {
        this.hrHoraFin = hrHoraFin;
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

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrId != null ? hrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.hrId == null && other.hrId != null) || (this.hrId != null && !this.hrId.equals(other.hrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Horario[ hrId=" + hrId + " ]";
    }
    
}

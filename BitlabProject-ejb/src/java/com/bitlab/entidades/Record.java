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
@Table(name = "BL_RC_RECORD", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"),
    @NamedQuery(name = "Record.findByRcId", query = "SELECT r FROM Record r WHERE r.rcId = :rcId"),
    @NamedQuery(name = "Record.findByRcPromedioFinal", query = "SELECT r FROM Record r WHERE r.rcPromedioFinal = :rcPromedioFinal"),
    @NamedQuery(name = "Record.findByRcComentario", query = "SELECT r FROM Record r WHERE r.rcComentario = :rcComentario"),
    @NamedQuery(name = "Record.findByRcProfesor", query = "SELECT r FROM Record r WHERE r.rcProfesor = :rcProfesor"),
    @NamedQuery(name = "Record.findByAUsuarioCrea", query = "SELECT r FROM Record r WHERE r.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Record.findByAFechaModificacion", query = "SELECT r FROM Record r WHERE r.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Record.findByAFechaCreacion", query = "SELECT r FROM Record r WHERE r.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Record.findByAUsuarioModifica", query = "SELECT r FROM Record r WHERE r.aUsuarioModifica = :aUsuarioModifica")})
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RC_ID", nullable = false)
    private Integer rcId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RC_PROMEDIO_FINAL", nullable = false)
    private double rcPromedioFinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "RC_COMENTARIO", nullable = false, length = 250)
    private String rcComentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "RC_PROFESOR", nullable = false, length = 150)
    private String rcProfesor;
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
    @JoinColumn(name = "CS_ID", referencedColumnName = "CS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso csId;
    @JoinColumn(name = "ES_ID", referencedColumnName = "ES_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante esId;

    public Record() {
    }

    public Record(Integer rcId) {
        this.rcId = rcId;
    }

    public Record(Integer rcId, double rcPromedioFinal, String rcComentario, String rcProfesor, String aUsuarioCrea, Date aFechaCreacion) {
        this.rcId = rcId;
        this.rcPromedioFinal = rcPromedioFinal;
        this.rcComentario = rcComentario;
        this.rcProfesor = rcProfesor;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getRcId() {
        return rcId;
    }

    public void setRcId(Integer rcId) {
        this.rcId = rcId;
    }

    public double getRcPromedioFinal() {
        return rcPromedioFinal;
    }

    public void setRcPromedioFinal(double rcPromedioFinal) {
        this.rcPromedioFinal = rcPromedioFinal;
    }

    public String getRcComentario() {
        return rcComentario;
    }

    public void setRcComentario(String rcComentario) {
        this.rcComentario = rcComentario;
    }

    public String getRcProfesor() {
        return rcProfesor;
    }

    public void setRcProfesor(String rcProfesor) {
        this.rcProfesor = rcProfesor;
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

    public Curso getCsId() {
        return csId;
    }

    public void setCsId(Curso csId) {
        this.csId = csId;
    }

    public Estudiante getEsId() {
        return esId;
    }

    public void setEsId(Estudiante esId) {
        this.esId = esId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rcId != null ? rcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.rcId == null && other.rcId != null) || (this.rcId != null && !this.rcId.equals(other.rcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Record[ rcId=" + rcId + " ]";
    }
    
}

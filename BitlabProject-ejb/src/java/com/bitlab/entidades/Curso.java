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
@Table(name = "BL_CS_CURSO", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCsId", query = "SELECT c FROM Curso c WHERE c.csId = :csId"),
    @NamedQuery(name = "Curso.findByCsCodCurso", query = "SELECT c FROM Curso c WHERE c.csCodCurso = :csCodCurso"),
    @NamedQuery(name = "Curso.findByCsNombre", query = "SELECT c FROM Curso c WHERE c.csNombre = :csNombre"),
    @NamedQuery(name = "Curso.findByCsDescripcion", query = "SELECT c FROM Curso c WHERE c.csDescripcion = :csDescripcion"),
    @NamedQuery(name = "Curso.findByCsEstado", query = "SELECT c FROM Curso c WHERE c.csEstado = :csEstado"),
    @NamedQuery(name = "Curso.findByCsCantidadBecados", query = "SELECT c FROM Curso c WHERE c.csCantidadBecados = :csCantidadBecados"),
    @NamedQuery(name = "Curso.findByCsFinalizacion", query = "SELECT c FROM Curso c WHERE c.csFinalizacion = :csFinalizacion"),
    @NamedQuery(name = "Curso.findByCsInicio", query = "SELECT c FROM Curso c WHERE c.csInicio = :csInicio"),
    @NamedQuery(name = "Curso.findByAUsuarioCrea", query = "SELECT c FROM Curso c WHERE c.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Curso.findByAFechaModificacion", query = "SELECT c FROM Curso c WHERE c.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Curso.findByAFechaCreacion", query = "SELECT c FROM Curso c WHERE c.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Curso.findByAUsuarioModifica", query = "SELECT c FROM Curso c WHERE c.aUsuarioModifica = :aUsuarioModifica")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CS_ID", nullable = false)
    private Integer csId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CS_COD_CURSO", nullable = false, length = 15)
    private String csCodCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CS_NOMBRE", nullable = false, length = 50)
    private String csNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CS_DESCRIPCION", nullable = false, length = 100)
    private String csDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CS_ESTADO", nullable = false)
    private boolean csEstado;
    @Column(name = "CS_CANTIDAD_BECADOS")
    private Integer csCantidadBecados;
    @Column(name = "CS_FINALIZACION")
    @Temporal(TemporalType.DATE)
    private Date csFinalizacion;
    @Column(name = "CS_INICIO")
    @Temporal(TemporalType.DATE)
    private Date csInicio;
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
    @JoinColumn(name = "HR_ID", referencedColumnName = "HR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Horario hrId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "csId", fetch = FetchType.LAZY)
    private List<Record> recordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "csId", fetch = FetchType.LAZY)
    private List<Preseleccion> preseleccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "csId", fetch = FetchType.LAZY)
    private List<Estudiante> estudianteList;

    public Curso() {
    }

    public Curso(Integer csId) {
        this.csId = csId;
    }

    public Curso(Integer csId, String csCodCurso, String csNombre, String csDescripcion, boolean csEstado, String aUsuarioCrea, Date aFechaCreacion) {
        this.csId = csId;
        this.csCodCurso = csCodCurso;
        this.csNombre = csNombre;
        this.csDescripcion = csDescripcion;
        this.csEstado = csEstado;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public String getCsCodCurso() {
        return csCodCurso;
    }

    public void setCsCodCurso(String csCodCurso) {
        this.csCodCurso = csCodCurso;
    }

    public String getCsNombre() {
        return csNombre;
    }

    public void setCsNombre(String csNombre) {
        this.csNombre = csNombre;
    }

    public String getCsDescripcion() {
        return csDescripcion;
    }

    public void setCsDescripcion(String csDescripcion) {
        this.csDescripcion = csDescripcion;
    }

    public boolean getCsEstado() {
        return csEstado;
    }

    public void setCsEstado(boolean csEstado) {
        this.csEstado = csEstado;
    }

    public Integer getCsCantidadBecados() {
        return csCantidadBecados;
    }

    public void setCsCantidadBecados(Integer csCantidadBecados) {
        this.csCantidadBecados = csCantidadBecados;
    }

    public Date getCsFinalizacion() {
        return csFinalizacion;
    }

    public void setCsFinalizacion(Date csFinalizacion) {
        this.csFinalizacion = csFinalizacion;
    }

    public Date getCsInicio() {
        return csInicio;
    }

    public void setCsInicio(Date csInicio) {
        this.csInicio = csInicio;
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

    public Horario getHrId() {
        return hrId;
    }

    public void setHrId(Horario hrId) {
        this.hrId = hrId;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<Preseleccion> getPreseleccionList() {
        return preseleccionList;
    }

    public void setPreseleccionList(List<Preseleccion> preseleccionList) {
        this.preseleccionList = preseleccionList;
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
        hash += (csId != null ? csId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.csId == null && other.csId != null) || (this.csId != null && !this.csId.equals(other.csId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Curso[ csId=" + csId + " ]";
    }
    
}

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
@Table(name = "BL_ILB_INTERMEDIACION_LABORAL", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "IntermediacionLaboral.findAll", query = "SELECT i FROM IntermediacionLaboral i"),
    @NamedQuery(name = "IntermediacionLaboral.findByIlbId", query = "SELECT i FROM IntermediacionLaboral i WHERE i.ilbId = :ilbId"),
    @NamedQuery(name = "IntermediacionLaboral.findByAUsuarioCrea", query = "SELECT i FROM IntermediacionLaboral i WHERE i.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "IntermediacionLaboral.findByAFechaCreacion", query = "SELECT i FROM IntermediacionLaboral i WHERE i.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "IntermediacionLaboral.findByAFechaModificacion", query = "SELECT i FROM IntermediacionLaboral i WHERE i.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "IntermediacionLaboral.findByAUsuarioModifica", query = "SELECT i FROM IntermediacionLaboral i WHERE i.aUsuarioModifica = :aUsuarioModifica")})
public class IntermediacionLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ILB_ID", nullable = false)
    private Integer ilbId;
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
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresa empId;
    @JoinColumn(name = "ES_ID", referencedColumnName = "ES_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante esId;
    @JoinColumn(name = "IT_ID", referencedColumnName = "IT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoIntermedicacion itId;

    public IntermediacionLaboral() {
    }

    public IntermediacionLaboral(Integer ilbId) {
        this.ilbId = ilbId;
    }

    public IntermediacionLaboral(Integer ilbId, String aUsuarioCrea, Date aFechaCreacion) {
        this.ilbId = ilbId;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getIlbId() {
        return ilbId;
    }

    public void setIlbId(Integer ilbId) {
        this.ilbId = ilbId;
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

    public Empresa getEmpId() {
        return empId;
    }

    public void setEmpId(Empresa empId) {
        this.empId = empId;
    }

    public Estudiante getEsId() {
        return esId;
    }

    public void setEsId(Estudiante esId) {
        this.esId = esId;
    }

    public EstadoIntermedicacion getItId() {
        return itId;
    }

    public void setItId(EstadoIntermedicacion itId) {
        this.itId = itId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ilbId != null ? ilbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntermediacionLaboral)) {
            return false;
        }
        IntermediacionLaboral other = (IntermediacionLaboral) object;
        if ((this.ilbId == null && other.ilbId != null) || (this.ilbId != null && !this.ilbId.equals(other.ilbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.IntermediacionLaboral[ ilbId=" + ilbId + " ]";
    }
    
}

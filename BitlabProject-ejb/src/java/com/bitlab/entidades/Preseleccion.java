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
@Table(name = "BL_PR_PRESELECCION", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Preseleccion.findAll", query = "SELECT p FROM Preseleccion p"),
    @NamedQuery(name = "Preseleccion.findByPrId", query = "SELECT p FROM Preseleccion p WHERE p.prId = :prId"),
    @NamedQuery(name = "Preseleccion.findByPrNombre", query = "SELECT p FROM Preseleccion p WHERE p.prNombre = :prNombre"),
    @NamedQuery(name = "Preseleccion.findByPrNota", query = "SELECT p FROM Preseleccion p WHERE p.prNota = :prNota"),
    @NamedQuery(name = "Preseleccion.findByPrPonderacion", query = "SELECT p FROM Preseleccion p WHERE p.prPonderacion = :prPonderacion"),
    @NamedQuery(name = "Preseleccion.findByPrComentario", query = "SELECT p FROM Preseleccion p WHERE p.prComentario = :prComentario"),
    @NamedQuery(name = "Preseleccion.findByAUsuarioCrea", query = "SELECT p FROM Preseleccion p WHERE p.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Preseleccion.findByAFechaModificacion", query = "SELECT p FROM Preseleccion p WHERE p.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Preseleccion.findByAFechaCreacion", query = "SELECT p FROM Preseleccion p WHERE p.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Preseleccion.findByAUsuarioModifica", query = "SELECT p FROM Preseleccion p WHERE p.aUsuarioModifica = :aUsuarioModifica")})
public class Preseleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PR_ID", nullable = false)
    private Integer prId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PR_NOMBRE", nullable = false, length = 100)
    private String prNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PR_NOTA", nullable = false)
    private double prNota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PR_PONDERACION", nullable = false)
    private double prPonderacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PR_COMENTARIO", nullable = false, length = 200)
    private String prComentario;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prId", fetch = FetchType.LAZY)
    private List<Ficheros> ficherosList;
    @JoinColumn(name = "CS_ID", referencedColumnName = "CS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso csId;
    @JoinColumn(name = "ES_ID", referencedColumnName = "ES_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante esId;

    public Preseleccion() {
    }

    public Preseleccion(Integer prId) {
        this.prId = prId;
    }

    public Preseleccion(Integer prId, String prNombre, double prNota, double prPonderacion, String prComentario, String aUsuarioCrea, Date aFechaCreacion) {
        this.prId = prId;
        this.prNombre = prNombre;
        this.prNota = prNota;
        this.prPonderacion = prPonderacion;
        this.prComentario = prComentario;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    public String getPrNombre() {
        return prNombre;
    }

    public void setPrNombre(String prNombre) {
        this.prNombre = prNombre;
    }

    public double getPrNota() {
        return prNota;
    }

    public void setPrNota(double prNota) {
        this.prNota = prNota;
    }

    public double getPrPonderacion() {
        return prPonderacion;
    }

    public void setPrPonderacion(double prPonderacion) {
        this.prPonderacion = prPonderacion;
    }

    public String getPrComentario() {
        return prComentario;
    }

    public void setPrComentario(String prComentario) {
        this.prComentario = prComentario;
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

    public List<Ficheros> getFicherosList() {
        return ficherosList;
    }

    public void setFicherosList(List<Ficheros> ficherosList) {
        this.ficherosList = ficherosList;
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
        hash += (prId != null ? prId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preseleccion)) {
            return false;
        }
        Preseleccion other = (Preseleccion) object;
        if ((this.prId == null && other.prId != null) || (this.prId != null && !this.prId.equals(other.prId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Preseleccion[ prId=" + prId + " ]";
    }
    
}

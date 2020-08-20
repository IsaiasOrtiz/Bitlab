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
@Table(name = "BL_IDM_IDIOMA", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i"),
    @NamedQuery(name = "Idioma.findByIdmId", query = "SELECT i FROM Idioma i WHERE i.idmId = :idmId"),
    @NamedQuery(name = "Idioma.findByIdmNombre", query = "SELECT i FROM Idioma i WHERE i.idmNombre = :idmNombre"),
    @NamedQuery(name = "Idioma.findByAUsuarioCrea", query = "SELECT i FROM Idioma i WHERE i.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Idioma.findByAFechaCreacion", query = "SELECT i FROM Idioma i WHERE i.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Idioma.findByAFechaModificacion", query = "SELECT i FROM Idioma i WHERE i.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Idioma.findByAUsuarioModifica", query = "SELECT i FROM Idioma i WHERE i.aUsuarioModifica = :aUsuarioModifica")})
public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDM_ID", nullable = false)
    private Integer idmId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IDM_NOMBRE", nullable = false, length = 50)
    private String idmNombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmId", fetch = FetchType.LAZY)
    private List<Idiomas> idiomasList;

    public Idioma() {
    }

    public Idioma(Integer idmId) {
        this.idmId = idmId;
    }

    public Idioma(Integer idmId, String idmNombre, String aUsuarioCrea, Date aFechaCreacion) {
        this.idmId = idmId;
        this.idmNombre = idmNombre;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getIdmId() {
        return idmId;
    }

    public void setIdmId(Integer idmId) {
        this.idmId = idmId;
    }

    public String getIdmNombre() {
        return idmNombre;
    }

    public void setIdmNombre(String idmNombre) {
        this.idmNombre = idmNombre;
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

    public List<Idiomas> getIdiomasList() {
        return idiomasList;
    }

    public void setIdiomasList(List<Idiomas> idiomasList) {
        this.idiomasList = idiomasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmId != null ? idmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.idmId == null && other.idmId != null) || (this.idmId != null && !this.idmId.equals(other.idmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Idioma[ idmId=" + idmId + " ]";
    }
    
}

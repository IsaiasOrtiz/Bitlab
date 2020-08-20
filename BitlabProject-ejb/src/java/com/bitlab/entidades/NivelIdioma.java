/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author elcon
 */
@Entity
@Table(name = "BL_N_NIVEL", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "NivelIdioma.findAll", query = "SELECT n FROM NivelIdioma n"),
    @NamedQuery(name = "NivelIdioma.findByNId", query = "SELECT n FROM NivelIdioma n WHERE n.nId = :nId"),
    @NamedQuery(name = "NivelIdioma.findByNNombre", query = "SELECT n FROM NivelIdioma n WHERE n.nNombre = :nNombre")})
public class NivelIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "N_ID", nullable = false)
    private Integer nId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "N_NOMBRE", nullable = false, length = 45)
    private String nNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nId", fetch = FetchType.LAZY)
    private List<Idiomas> idiomasList;

    public NivelIdioma() {
    }

    public NivelIdioma(Integer nId) {
        this.nId = nId;
    }

    public NivelIdioma(Integer nId, String nNombre) {
        this.nId = nId;
        this.nNombre = nNombre;
    }

    public Integer getNId() {
        return nId;
    }

    public void setNId(Integer nId) {
        this.nId = nId;
    }

    public String getNNombre() {
        return nNombre;
    }

    public void setNNombre(String nNombre) {
        this.nNombre = nNombre;
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
        hash += (nId != null ? nId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelIdioma)) {
            return false;
        }
        NivelIdioma other = (NivelIdioma) object;
        if ((this.nId == null && other.nId != null) || (this.nId != null && !this.nId.equals(other.nId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.NivelIdioma[ nId=" + nId + " ]";
    }
    
}

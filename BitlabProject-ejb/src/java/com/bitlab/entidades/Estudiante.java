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
import javax.persistence.Lob;
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
@Table(name = "BL_ES_ESTUDIANTE", catalog = "BD_BITLAB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByEsId", query = "SELECT e FROM Estudiante e WHERE e.esId = :esId"),
    @NamedQuery(name = "Estudiante.findByEsNombre", query = "SELECT e FROM Estudiante e WHERE e.esNombre = :esNombre"),
    @NamedQuery(name = "Estudiante.findByEsApellido", query = "SELECT e FROM Estudiante e WHERE e.esApellido = :esApellido"),
    @NamedQuery(name = "Estudiante.findByEsDui", query = "SELECT e FROM Estudiante e WHERE e.esDui = :esDui"),
    @NamedQuery(name = "Estudiante.findByEsFechaNacimiento", query = "SELECT e FROM Estudiante e WHERE e.esFechaNacimiento = :esFechaNacimiento"),
    @NamedQuery(name = "Estudiante.findByEsComputadora", query = "SELECT e FROM Estudiante e WHERE e.esComputadora = :esComputadora"),
    @NamedQuery(name = "Estudiante.findByEsDireccion", query = "SELECT e FROM Estudiante e WHERE e.esDireccion = :esDireccion"),
    @NamedQuery(name = "Estudiante.findByEsTelefono", query = "SELECT e FROM Estudiante e WHERE e.esTelefono = :esTelefono"),
    @NamedQuery(name = "Estudiante.findByEsCorreo", query = "SELECT e FROM Estudiante e WHERE e.esCorreo = :esCorreo"),
    @NamedQuery(name = "Estudiante.findByEsClave", query = "SELECT e FROM Estudiante e WHERE e.esClave = :esClave"),
    @NamedQuery(name = "Estudiante.findByEsLaborando", query = "SELECT e FROM Estudiante e WHERE e.esLaborando = :esLaborando"),
    @NamedQuery(name = "Estudiante.findByEsAspiraciones", query = "SELECT e FROM Estudiante e WHERE e.esAspiraciones = :esAspiraciones"),
    @NamedQuery(name = "Estudiante.findByEsInteres", query = "SELECT e FROM Estudiante e WHERE e.esInteres = :esInteres"),
    @NamedQuery(name = "Estudiante.findByAUsuarioCrea", query = "SELECT e FROM Estudiante e WHERE e.aUsuarioCrea = :aUsuarioCrea"),
    @NamedQuery(name = "Estudiante.findByAFechaModificacion", query = "SELECT e FROM Estudiante e WHERE e.aFechaModificacion = :aFechaModificacion"),
    @NamedQuery(name = "Estudiante.findByAFechaCreacion", query = "SELECT e FROM Estudiante e WHERE e.aFechaCreacion = :aFechaCreacion"),
    @NamedQuery(name = "Estudiante.findByAUsuarioModifica", query = "SELECT e FROM Estudiante e WHERE e.aUsuarioModifica = :aUsuarioModifica")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ES_ID", nullable = false)
    private Integer esId;
    @Lob
    @Column(name = "ES_FOTO")
    private byte[] esFoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ES_NOMBRE", nullable = false, length = 60)
    private String esNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ES_APELLIDO", nullable = false, length = 60)
    private String esApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ES_DUI", nullable = false, length = 10)
    private String esDui;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date esFechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_COMPUTADORA", nullable = false)
    private boolean esComputadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ES_DIRECCION", nullable = false, length = 100)
    private String esDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "ES_TELEFONO", nullable = false, length = 9)
    private String esTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ES_CORREO", nullable = false, length = 100)
    private String esCorreo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ES_CLAVE", nullable = false, length = 500)
    private String esClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_LABORANDO", nullable = false)
    private boolean esLaborando;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "ES_ASPIRACIONES", nullable = false, length = 250)
    private String esAspiraciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "ES_INTERES", nullable = false, length = 250)
    private String esInteres;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ES_CV", nullable = false)
    private byte[] esCv;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esId", fetch = FetchType.LAZY)
    private List<TecnologiasManejadas> tecnologiasManejadasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esId", fetch = FetchType.LAZY)
    private List<Idiomas> idiomasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esId", fetch = FetchType.LAZY)
    private List<Record> recordList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esId", fetch = FetchType.LAZY)
    private List<Preseleccion> preseleccionList;
    @JoinColumn(name = "CS_ID", referencedColumnName = "CS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso csId;
    @JoinColumn(name = "ESD_ID", referencedColumnName = "ESD_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Escolaridad esdId;
    @JoinColumn(name = "ESN_ID", referencedColumnName = "ESN_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoSeleccion esnId;
    @JoinColumn(name = "ILB_ID", referencedColumnName = "ILB_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private IntermediacionLaboral ilbId;
    @JoinColumn(name = "MP_ID", referencedColumnName = "MP_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Municipio mpId;
    @JoinColumn(name = "RL_ID", referencedColumnName = "RL_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rlId;

    public Estudiante() {
    }

    public Estudiante(Integer esId) {
        this.esId = esId;
    }

    public Estudiante(Integer esId, String esNombre, String esApellido, String esDui, Date esFechaNacimiento, boolean esComputadora, String esDireccion, String esTelefono, String esCorreo, String esClave, boolean esLaborando, String esAspiraciones, String esInteres, byte[] esCv, String aUsuarioCrea, Date aFechaCreacion) {
        this.esId = esId;
        this.esNombre = esNombre;
        this.esApellido = esApellido;
        this.esDui = esDui;
        this.esFechaNacimiento = esFechaNacimiento;
        this.esComputadora = esComputadora;
        this.esDireccion = esDireccion;
        this.esTelefono = esTelefono;
        this.esCorreo = esCorreo;
        this.esClave = esClave;
        this.esLaborando = esLaborando;
        this.esAspiraciones = esAspiraciones;
        this.esInteres = esInteres;
        this.esCv = esCv;
        this.aUsuarioCrea = aUsuarioCrea;
        this.aFechaCreacion = aFechaCreacion;
    }

    public Integer getEsId() {
        return esId;
    }

    public void setEsId(Integer esId) {
        this.esId = esId;
    }

    public byte[] getEsFoto() {
        return esFoto;
    }

    public void setEsFoto(byte[] esFoto) {
        this.esFoto = esFoto;
    }

    public String getEsNombre() {
        return esNombre;
    }

    public void setEsNombre(String esNombre) {
        this.esNombre = esNombre;
    }

    public String getEsApellido() {
        return esApellido;
    }

    public void setEsApellido(String esApellido) {
        this.esApellido = esApellido;
    }

    public String getEsDui() {
        return esDui;
    }

    public void setEsDui(String esDui) {
        this.esDui = esDui;
    }

    public Date getEsFechaNacimiento() {
        return esFechaNacimiento;
    }

    public void setEsFechaNacimiento(Date esFechaNacimiento) {
        this.esFechaNacimiento = esFechaNacimiento;
    }

    public boolean getEsComputadora() {
        return esComputadora;
    }

    public void setEsComputadora(boolean esComputadora) {
        this.esComputadora = esComputadora;
    }

    public String getEsDireccion() {
        return esDireccion;
    }

    public void setEsDireccion(String esDireccion) {
        this.esDireccion = esDireccion;
    }

    public String getEsTelefono() {
        return esTelefono;
    }

    public void setEsTelefono(String esTelefono) {
        this.esTelefono = esTelefono;
    }

    public String getEsCorreo() {
        return esCorreo;
    }

    public void setEsCorreo(String esCorreo) {
        this.esCorreo = esCorreo;
    }

    public String getEsClave() {
        return esClave;
    }

    public void setEsClave(String esClave) {
        this.esClave = esClave;
    }

    public boolean getEsLaborando() {
        return esLaborando;
    }

    public void setEsLaborando(boolean esLaborando) {
        this.esLaborando = esLaborando;
    }

    public String getEsAspiraciones() {
        return esAspiraciones;
    }

    public void setEsAspiraciones(String esAspiraciones) {
        this.esAspiraciones = esAspiraciones;
    }

    public String getEsInteres() {
        return esInteres;
    }

    public void setEsInteres(String esInteres) {
        this.esInteres = esInteres;
    }

    public byte[] getEsCv() {
        return esCv;
    }

    public void setEsCv(byte[] esCv) {
        this.esCv = esCv;
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

    public List<TecnologiasManejadas> getTecnologiasManejadasList() {
        return tecnologiasManejadasList;
    }

    public void setTecnologiasManejadasList(List<TecnologiasManejadas> tecnologiasManejadasList) {
        this.tecnologiasManejadasList = tecnologiasManejadasList;
    }

    public List<Idiomas> getIdiomasList() {
        return idiomasList;
    }

    public void setIdiomasList(List<Idiomas> idiomasList) {
        this.idiomasList = idiomasList;
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

    public Curso getCsId() {
        return csId;
    }

    public void setCsId(Curso csId) {
        this.csId = csId;
    }

    public Escolaridad getEsdId() {
        return esdId;
    }

    public void setEsdId(Escolaridad esdId) {
        this.esdId = esdId;
    }

    public EstadoSeleccion getEsnId() {
        return esnId;
    }

    public void setEsnId(EstadoSeleccion esnId) {
        this.esnId = esnId;
    }

    public IntermediacionLaboral getIlbId() {
        return ilbId;
    }

    public void setIlbId(IntermediacionLaboral ilbId) {
        this.ilbId = ilbId;
    }

    public Municipio getMpId() {
        return mpId;
    }

    public void setMpId(Municipio mpId) {
        this.mpId = mpId;
    }

    public Rol getRlId() {
        return rlId;
    }

    public void setRlId(Rol rlId) {
        this.rlId = rlId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (esId != null ? esId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.esId == null && other.esId != null) || (this.esId != null && !this.esId.equals(other.esId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitlab.entidades.Estudiante[ esId=" + esId + " ]";
    }
    
}

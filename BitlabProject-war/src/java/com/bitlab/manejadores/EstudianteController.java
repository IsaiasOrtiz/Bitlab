package com.bitlab.manejadores;

import com.bitlab.entidades.Curso;
import com.bitlab.entidades.EstadoSeleccion;
import com.bitlab.entidades.Estudiante;
import com.bitlab.entidades.Preseleccion;
import com.bitlab.entidades.Rol;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.propiedades.Encriptador;
import com.bitlab.session.CursoFacade;
import com.bitlab.session.EstadoSeleccionFacade;
import com.bitlab.session.EstudianteFacade;
import com.bitlab.session.RolFacade;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named("estudianteController")
@SessionScoped
public class EstudianteController implements Serializable {

    @EJB
    private CursoFacade cursoFacade;

    @EJB
    private EstadoSeleccionFacade estadoSeleccionFacade;

    @EJB
    private com.bitlab.session.EstudianteFacade ejbFacade;

    @EJB
    private RolFacade rolFacade;

    private List<Estudiante> items = null;
    private List<Estudiante> soloEstudiantes = null;
    private Estudiante selected;
    private UploadedFile file;
    private UploadedFile cvEs;
    private String flagRender = "cursos";
    private List<Estudiante> estudiantesPorCurso = null;
    private List<Preseleccion> preseleccionDetalle = null;
    private Curso curso = new Curso();

    public EstudianteController() {
    }

    public Estudiante getSelected() {
        return selected;
    }

    public CursoFacade getCursoFacade() {
        return cursoFacade;
    }

    public void setCursoFacade(CursoFacade cursoFacade) {
        this.cursoFacade = cursoFacade;
    }

    public EstadoSeleccionFacade getEstadoSeleccionFacade() {
        return estadoSeleccionFacade;
    }

    public void setEstadoSeleccionFacade(EstadoSeleccionFacade estadoSeleccionFacade) {
        this.estadoSeleccionFacade = estadoSeleccionFacade;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public void setRolFacade(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    public EstadoSeleccion findEstadoSeleccion(Integer id) {
        return getEstadoSeleccionFacade().find(id);
    }

    public Curso findCurso(Integer id) {
        return getCursoFacade().find(id);
    }

    public Rol findRol(Integer id) {
        return getRolFacade().find(id);
    }

    public void setSelected(Estudiante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstudianteFacade getFacade() {
        return ejbFacade;
    }

    public Estudiante prepareCreate() {
        selected = new Estudiante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EstudianteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     * Este metodo agrega los campos que el estudiante no puede modificar y
     * asigna al estudiante al curso no valido y tambien lo crea en el rol de
     * estudiante y agregamos el file convertido a tipo byte[]
     */
    public void createNewStudent() {
        selected.setEsFoto(file.getContent());
        selected.setCsId(findCurso(3));
        selected.setRlId(findRol(2));
        selected.setEsnId(findEstadoSeleccion(1));
        selected.setAFechaCreacion(new Date());
        selected.setAUsuarioCrea("SYSTEM");
        Encriptador encriptador = new Encriptador();
        selected.setEsClave(encriptador.encriptador(selected.getEsClave()));
        selected.setEsCv(cvEs.getContent());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EstudianteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void traerEstudiantesConEstado(Integer id)
    {
        items=getFacade().encontrarEstudiantesPorEstadoDeSeleccion(id);
    }
    public void editarEstudiante(Integer student) {
        if (file.getSize() > 0 || cvEs.getSize() > 0) {
            selected = getEstudiante(student);
            if (file.getSize() > 0) {
                selected.setEsFoto(file.getContent());
            }
            if (cvEs.getSize() > 0) {
                selected.setEsCv(cvEs.getContent());
            }
            selected.setAFechaModificacion(new Date());
            selected.setAUsuarioModifica(selected.getEsNombre());
            update();
        } else {
            JsfUtil.addErrorMessage("No selecciono ningun archivo");
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void inscribir(Curso curso, java.lang.Integer idEstudiante) {
        selected = getEstudiante(idEstudiante);
        if (getSelected().getCsId().getCsId() == 3) {
            if (getSelected().getEsnId().getEsnId() == 1) {
                selected.setCsId(curso);
                EstadoSeleccion es = new EstadoSeleccion();
                es.setEsnId(2);
                es.setEsnNombre("En proceso");
                es.setAFechaCreacion(new Date());
                es.setAFechaModificacion(new Date());
                es.setAUsuarioCrea("SYSTEM");
                selected.setEsnId(es);
                update();
                JsfUtil.addSuccessMessage("Usted se inscribio al curso: " + curso.getCsNombre());
            } else {
                JsfUtil.addErrorMessage("Usted ya esta en proceso con otro curso.");
            }
        } else {
            JsfUtil.addErrorMessage("Usted ya esta en otro curso.");
        }
    }

    public void verArchivo() {
        try {
            Estudiante es = ejbFacade.find(selected.getEsId());
            byte[] img = es.getEsFoto();
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.getOutputStream().write(img);
            respuesta.getOutputStream().close();

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
        }
    }

    public StreamedContent imagen(byte[] imgen) {
        try {
            InputStream imgStream = new ByteArrayInputStream(imgen);
            return new DefaultStreamedContent(imgStream);
        } catch (Exception e) {
            return null;
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EstudianteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EstudianteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Estudiante> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Estudiante> getSoloEstudiantes() {
        if (soloEstudiantes == null) {
            soloEstudiantes = getFacade().encontrarSoloEstudiantes();
        }
        return soloEstudiantes;
    }

    /*Metodo para obtener los estudiantes por curso*/
    public List<Estudiante> getEstudiantesPorCurso(int id) {
        estudiantesPorCurso = getFacade().encontrarEstudiantesPorCurso(id);
        curso = cursoFacade.find(id);
        flagRender = "estudiantes";
        return estudiantesPorCurso;
    }

    /*Metodo para obtener el listado de preseleccion por curso de los estudiantes*/
    public List<Preseleccion> getDetallePreseleccion(int id) {
        selected  = getFacade().find(id);
        Curso cs = selected.getCsId();
        preseleccionDetalle = getFacade().detallePreseleccion(id,cs.getCsId());
        
        curso = cs;
        
        flagRender = "proceso";
        
        return preseleccionDetalle;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Estudiante getEstudiante(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Estudiante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Estudiante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getCvEs() {
        return cvEs;
    }

    public void setCvEs(UploadedFile cvEs) {
        this.cvEs = cvEs;
    }

    @FacesConverter(forClass = Estudiante.class)
    public static class EstudianteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstudianteController controller = (EstudianteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estudianteController");
            return controller.getEstudiante(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Estudiante) {
                Estudiante o = (Estudiante) object;
                return getStringKey(o.getEsId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estudiante.class.getName()});
                return null;
            }
        }

    }

    public List<Estudiante> getEstudiantesPorCurso() {
        return estudiantesPorCurso;
    }

    public void setEstudiantesPorCurso(List<Estudiante> estudiantesPorCurso) {
        this.estudiantesPorCurso = estudiantesPorCurso;
    }

    public String getFlagRender() {
        return flagRender;
    }

    public void setFlagRender(String flagRender) {
        this.flagRender = flagRender;
    }

    public void vaciarLista() {
        estudiantesPorCurso = null;
    }

    public List<Preseleccion> getPreseleccionDetalle() {
        return preseleccionDetalle;
    }

    public void setPreseleccionDetalle(List<Preseleccion> preseleccionDetalle) {
        this.preseleccionDetalle = preseleccionDetalle;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}

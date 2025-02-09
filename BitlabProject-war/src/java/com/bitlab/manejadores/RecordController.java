package com.bitlab.manejadores;

import com.bitlab.entidades.Curso;
import com.bitlab.entidades.EstadoSeleccion;
import com.bitlab.entidades.Estudiante;
import com.bitlab.entidades.Record;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.session.CursoFacade;
import com.bitlab.session.EstadoSeleccionFacade;
import com.bitlab.session.EstudianteFacade;
import com.bitlab.session.RecordFacade;

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
import javax.faces.view.ViewScoped;

@Named("recordController")
@ViewScoped
public class RecordController implements Serializable {

    @EJB
    private com.bitlab.session.RecordFacade ejbFacade;
    @EJB
    private EstudianteFacade ejbEstudiante;
    @EJB
    private CursoFacade ebjCurso;
    @EJB
    private EstadoSeleccionFacade ebjEstadoSeleccion;
    
    private List<Record> items = null;
    private List<Record> record = null;
    private boolean flag;
    private Record selected;

    public RecordController() {
    }

    public Record getSelected() {
        return selected;
    }

    public void setSelected(Record selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RecordFacade getFacade() {
        return ejbFacade;
    }
    
    public void cursosPresenciados(int idEstudiante){
        record = getFacade().cursosAsistidos(idEstudiante);
        flag = true;
    }

    public Record prepareCreate() {
        selected = new Record();
        initializeEmbeddableKey();
        return selected;
    }
    public Record prepareCreate(Curso c, Estudiante es) {
        selected = new Record();
        selected.setCsId(c);
        selected.setRcPromedioFinal(8);
        selected.setEsId(es);
        initializeEmbeddableKey();
        return selected;
    }
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RecordCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RecordUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Record> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public EstadoSeleccionFacade getEbjEstadoSeleccion() {
        return ebjEstadoSeleccion;
    }

    public void setEbjEstadoSeleccion(EstadoSeleccionFacade ebjEstadoSeleccion) {
        this.ebjEstadoSeleccion = ebjEstadoSeleccion;
    }
    
    public EstadoSeleccion findEstadoSeleccion(Integer id)
    {
    return getEbjEstadoSeleccion().find(id);
    }
    
    public void record(Integer student)
    {
     items=getFacade().cursosAsistidos(student);
    }
    public Record getRecord(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Record> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Record> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
     public void asignarRecordAcademico(Estudiante est, String usuarioCrea)
    {
        selected.setAFechaCreacion(new Date());
        selected.setAUsuarioCrea(usuarioCrea);
        create();
        est.setEsnId(findEstadoSeleccion(4));
        getEjbEstudiante().edit(est);
    }

    public CursoFacade getEbjCurso() {
        return ebjCurso;
    }

    public void setEbjCurso(CursoFacade ebjCurso) {
        this.ebjCurso = ebjCurso;
    }
     
    public Curso findCurso(Integer id) {
        return getEbjCurso().find(id);
    }
    public EstudianteFacade getEjbEstudiante() {
        return ejbEstudiante;
    }

    public void setEjbEstudiante(EstudianteFacade ejbEstudiante) {
        this.ejbEstudiante = ejbEstudiante;
    }

    @FacesConverter(forClass = Record.class)
    public static class RecordControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecordController controller = (RecordController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recordController");
            return controller.getRecord(getKey(value));
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
            if (object instanceof Record) {
                Record o = (Record) object;
                return getStringKey(o.getRcId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Record.class.getName()});
                return null;
            }
        }

    }

    public List<Record> getRecord() {
        return record;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}

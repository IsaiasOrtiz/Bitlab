package com.bitlab.manejadores;

import com.bitlab.entidades.Preseleccion;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.session.PreseleccionFacade;

import java.io.Serializable;
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

@Named("preseleccionController")
@ViewScoped
public class PreseleccionController implements Serializable {

    @EJB
    private com.bitlab.session.PreseleccionFacade ejbFacade;
    private List<Preseleccion> items = null;
    private List<Preseleccion> cursosAplicados = null;
    private List<Preseleccion> detallesCurso = null;
    private Preseleccion selected;
    private String historialDetalle = "estudiantes";

    public PreseleccionController() {
    }

    public Preseleccion getSelected() {
        return selected;
    }

    public void setSelected(Preseleccion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PreseleccionFacade getFacade() {
        return ejbFacade;
    }
    
    public void aplicaciones(int idEstudiante){
        cursosAplicados = getFacade().cursosAplicadosPorEstudiante(idEstudiante);
        historialDetalle = "cursos";
    }
    
    public void detalleCurso(int idEstudiante, int idCurso){
        detallesCurso = getFacade().detallesCurso(idEstudiante, idCurso);
        historialDetalle = "detalles";
    }

    public Preseleccion prepareCreate() {
        selected = new Preseleccion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PreseleccionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PreseleccionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PreseleccionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Preseleccion> getItems() {
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

    public Preseleccion getPreseleccion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Preseleccion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Preseleccion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Preseleccion.class)
    public static class PreseleccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PreseleccionController controller = (PreseleccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "preseleccionController");
            return controller.getPreseleccion(getKey(value));
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
            if (object instanceof Preseleccion) {
                Preseleccion o = (Preseleccion) object;
                return getStringKey(o.getPrId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Preseleccion.class.getName()});
                return null;
            }
        }

    }

    public List<Preseleccion> getCursosAplicados() {
        return cursosAplicados;
    }

    public void setCursosAplicados(List<Preseleccion> cursosAplicados) {
        this.cursosAplicados = cursosAplicados;
    }

    public List<Preseleccion> getDetallesCurso() {
        return detallesCurso;
    }

    public void setDetallesCurso(List<Preseleccion> detallesCurso) {
        this.detallesCurso = detallesCurso;
    }

    public String getHistorialDetalle() {
        return historialDetalle;
    }

    public void setHistorialDetalle(String historialDetalle) {
        this.historialDetalle = historialDetalle;
    }

}

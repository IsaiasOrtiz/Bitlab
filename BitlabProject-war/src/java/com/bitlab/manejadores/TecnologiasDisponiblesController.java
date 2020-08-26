package com.bitlab.manejadores;

import com.bitlab.entidades.TecnologiasDisponibles;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.session.TecnologiasDisponiblesFacade;

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

@Named("tecnologiasDisponiblesController")
@SessionScoped
public class TecnologiasDisponiblesController implements Serializable {

    @EJB
    private com.bitlab.session.TecnologiasDisponiblesFacade ejbFacade;
    private List<TecnologiasDisponibles> items = null;
    private TecnologiasDisponibles selected;

    public TecnologiasDisponiblesController() {
    }

    public TecnologiasDisponibles getSelected() {
        return selected;
    }

    public void setSelected(TecnologiasDisponibles selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TecnologiasDisponiblesFacade getFacade() {
        return ejbFacade;
    }

    public TecnologiasDisponibles prepareCreate() {
        selected = new TecnologiasDisponibles();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TecnologiasDisponiblesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void crearTecnologia(String usuario) {
        selected.setAFechaCreacion(new Date());
        selected.setAUsuarioCrea(usuario);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TecnologiasDisponiblesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TecnologiasDisponiblesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TecnologiasDisponiblesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TecnologiasDisponibles> getItems() {
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

    public TecnologiasDisponibles getTecnologiasDisponibles(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TecnologiasDisponibles> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TecnologiasDisponibles> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TecnologiasDisponibles.class)
    public static class TecnologiasDisponiblesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TecnologiasDisponiblesController controller = (TecnologiasDisponiblesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tecnologiasDisponiblesController");
            return controller.getTecnologiasDisponibles(getKey(value));
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
            if (object instanceof TecnologiasDisponibles) {
                TecnologiasDisponibles o = (TecnologiasDisponibles) object;
                return getStringKey(o.getTgdId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TecnologiasDisponibles.class.getName()});
                return null;
            }
        }

    }

}

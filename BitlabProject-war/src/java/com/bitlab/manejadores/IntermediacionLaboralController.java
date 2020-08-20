package com.bitlab.manejadores;

import com.bitlab.entidades.IntermediacionLaboral;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.session.IntermediacionLaboralFacade;

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

@Named("intermediacionLaboralController")
@SessionScoped
public class IntermediacionLaboralController implements Serializable {

    @EJB
    private com.bitlab.session.IntermediacionLaboralFacade ejbFacade;
    private List<IntermediacionLaboral> items = null;
    private IntermediacionLaboral selected;

    public IntermediacionLaboralController() {
    }

    public IntermediacionLaboral getSelected() {
        return selected;
    }

    public void setSelected(IntermediacionLaboral selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IntermediacionLaboralFacade getFacade() {
        return ejbFacade;
    }

    public IntermediacionLaboral prepareCreate() {
        selected = new IntermediacionLaboral();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IntermediacionLaboralCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IntermediacionLaboralUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IntermediacionLaboralDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<IntermediacionLaboral> getItems() {
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

    public IntermediacionLaboral getIntermediacionLaboral(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<IntermediacionLaboral> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<IntermediacionLaboral> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = IntermediacionLaboral.class)
    public static class IntermediacionLaboralControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IntermediacionLaboralController controller = (IntermediacionLaboralController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "intermediacionLaboralController");
            return controller.getIntermediacionLaboral(getKey(value));
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
            if (object instanceof IntermediacionLaboral) {
                IntermediacionLaboral o = (IntermediacionLaboral) object;
                return getStringKey(o.getIlbId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), IntermediacionLaboral.class.getName()});
                return null;
            }
        }

    }

}

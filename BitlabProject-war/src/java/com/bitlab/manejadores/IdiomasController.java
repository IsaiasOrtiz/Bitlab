package com.bitlab.manejadores;

import com.bitlab.entidades.Estudiante;
import com.bitlab.entidades.Idiomas;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.session.EstudianteFacade;
import com.bitlab.session.IdiomasFacade;

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

@Named("idiomasController")
@SessionScoped
public class IdiomasController implements Serializable {

    @EJB
    private com.bitlab.session.IdiomasFacade ejbFacade;
    @EJB
    private com.bitlab.session.EstudianteFacade ejbEstudiante;
    private List<Idiomas> items = null;
    private Idiomas selected;

    public IdiomasController() {
    }

    public Idiomas getSelected() {
        return selected;
    }

    public void setSelected(Idiomas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IdiomasFacade getFacade() {
        return ejbFacade;
    }

    public Idiomas prepareCreate() {
        selected = new Idiomas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IdiomasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IdiomasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IdiomasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Idiomas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Estudiante getEstudiante(java.lang.Integer id) {
        return getEjbEstudiante().find(id);
    }

    public void agragarIdiomaAlPerfil(java.lang.Integer id) {
        Estudiante es;
        es = getEstudiante(id);
        if (selected != null) {
            selected.setEsId(es);
            selected.setAUsuarioCrea(es.getEsNombre());
            selected.setAFechaCreacion(new Date());
            create();
        } else {
            JsfUtil.addErrorMessage("Error");
        }
    }
    public void cargarIdiomasUsuarioSesion(Integer id)
    {
        items=getFacade().encontrarIdiomasUser(id);
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

    public EstudianteFacade getEjbEstudiante() {
        return ejbEstudiante;
    }

    public void setEjbEstudiante(EstudianteFacade ejbEstudiante) {
        this.ejbEstudiante = ejbEstudiante;
    }

    public Idiomas getIdiomas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Idiomas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Idiomas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Idiomas.class)
    public static class IdiomasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdiomasController controller = (IdiomasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "idiomasController");
            return controller.getIdiomas(getKey(value));
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
            if (object instanceof Idiomas) {
                Idiomas o = (Idiomas) object;
                return getStringKey(o.getNeId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Idiomas.class.getName()});
                return null;
            }
        }

    }

}

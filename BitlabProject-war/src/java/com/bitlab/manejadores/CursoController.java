package com.bitlab.manejadores;

import com.bitlab.entidades.Curso;
import com.bitlab.entidades.Estudiante;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.manejadores.util.JsfUtil.PersistAction;
import com.bitlab.session.CursoFacade;
import com.bitlab.session.EstudianteFacade;
import java.io.IOException;

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

@Named("cursoController")
@ViewScoped
public class CursoController implements Serializable {

    @EJB
    private com.bitlab.session.CursoFacade ejbFacade;
    @EJB
    private EstudianteFacade estudiante;
    private List<Curso> items = null;
    private Curso selected;
    private boolean flag;
    private String flagRender = "cursos";

    public String getFlagRender() {
        return flagRender;
    }

    public void setFlagRender(String flagRender) {
        this.flagRender = flagRender;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public CursoController() {
    }

    public Curso getSelected() {
        return selected;
    }

    public void setSelected(Curso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CursoFacade getFacade() {
        return ejbFacade;
    }

    public Curso prepareCreate() {
        selected = new Curso();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void cambiarEstado(Curso curso) {
        selected = curso;
        
        if(selected.getCsEstado()){
            selected.setCsEstado(false);
        }
        else{
            selected.setCsEstado(true);
        }
        update();
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void createCurso() throws IOException {
        List<Curso> cursos =  ejbFacade.findAll();
        if(selected.getCsInicio().after(selected.getCsFinalizacion()) || selected.getCsInicio().equals(selected.getCsFinalizacion())){
            JsfUtil.addErrorMessage("Fecha Inicio es igual o mayor que Fecha Fin");
        }
        else{
            selected.setCsId((cursos.get(cursos.size()-1).getCsId())+1);
            selected.setCsEstado(Boolean.TRUE);
            selected.setAUsuarioCrea("SYSTEM");
            selected.setAFechaCreacion(new Date());
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CursoCreated"));
            JsfUtil.redireccion("../faces/analista/cursos");
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CursoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CursoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Curso> getItems() {
        if (items == null) {
            items = getFacade().cursos();
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

    public Curso getCurso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Curso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Curso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Curso.class)
    public static class CursoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CursoController controller = (CursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cursoController");
            return controller.getCurso(getKey(value));
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
            if (object instanceof Curso) {
                Curso o = (Curso) object;
                return getStringKey(o.getCsId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Curso.class.getName()});
                return null;
            }
        }

    }
    

    /**
     * Recibe el id del estudiante que se matriculara al curso seleccionado.
     *
     * @param id
     */
    public void marticularCurso(Integer id) {
        estudiante = new EstudianteFacade();
        Estudiante es = estudiante.find(id);
        es.setCsId(selected);
        estudiante.edit(es);
    }

}

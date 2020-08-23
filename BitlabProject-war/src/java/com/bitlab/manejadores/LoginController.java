
package com.bitlab.manejadores;

import com.bitlab.entidades.Estudiante;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.session.EstudianteFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable{
    
    @EJB
    private com.bitlab.session.EstudianteFacade ejbFacade;
    private Estudiante estudiante;
    
    public LoginController(){
        estudiante = new Estudiante();
    }
    
    public void validarUsuario() throws IOException{
        Estudiante estudianteRegistrado = ejbFacade.encontrarUsuario(estudiante);
        
        if(estudianteRegistrado != null){
            if(estudiante.getEsClave().equals(estudianteRegistrado.getEsClave())){
                estudiante.setEsNombre(estudianteRegistrado.getEsNombre());
                JsfUtil.redireccion("index");
            }
            else{
                JsfUtil.addErrorMessage("Contraseña incorrecta");
            }
        }
        else{
            JsfUtil.addErrorMessage("Usuario/Email incorrecto");
        }
    }

    public EstudianteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EstudianteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

}

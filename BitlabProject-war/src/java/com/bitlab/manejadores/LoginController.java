
package com.bitlab.manejadores;

import com.bitlab.correo.CorreoElectronico;
import com.bitlab.entidades.Estudiante;
import com.bitlab.manejadores.util.JsfUtil;
import com.bitlab.session.EstudianteFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable{
    
    @EJB
    private com.bitlab.session.EstudianteFacade ejbFacade;
    private Estudiante estudiante;
    private CorreoElectronico correoElectronico = new CorreoElectronico();
    private int codigo;
    private int numero;
    private boolean flagEstudiante;
    private boolean flagCodigo;
    
    public LoginController(){
        estudiante = new Estudiante();
        flagCodigo = false;
        codigo = 0;
    }
    
    public void validarUsuario() throws IOException{
        Estudiante estudianteRegistrado = ejbFacade.encontrarUsuario(estudiante);
        
        if(estudianteRegistrado != null){
            if(estudiante.getEsClave().equals(estudianteRegistrado.getEsClave())){
                estudiante = estudianteRegistrado;
                if(flagEstudiante){
                    if(estudiante.getRlId().getRlId() == 2){
                        JsfUtil.redireccion("index");
                    }
                    else{
                        JsfUtil.addErrorMessage("No eres estudiante");
                    }
                }
                else{
                    if(estudiante.getRlId().getRlId() != 2){
                        mandarCorreo();
                    }
                    else{
                        JsfUtil.addErrorMessage("No eres empleado");
                    }
                }
                
            }
            else{
                JsfUtil.addErrorMessage("Contraseña incorrecta");
            }
        }
        else{
            JsfUtil.addErrorMessage("Email incorrecto");
        }
    }
    
    public void mandarCorreo(){
        numero = (int) (Math.random()*(999999-100000+1)+100000);
        try {
            correoElectronico.mandarCorreo(estudiante.getEsCorreo(), numero);
            flagCodigo = true;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("No se pudo enviar el correo electrónico");
        }
    }
    
    public void validarCodigo() throws IOException{
        if(numero == codigo){
            if(estudiante.getRlId().getRlId() == 1){
                JsfUtil.redireccion("index");
            }
//            else if(usuarioEncontrado.getRolId().getRolId() == 3){
//                avisoPagoPlanilla();
//                Utilidades.redireccion("catalogos/usuario");
//            }
            flagCodigo = false;
        }
        else{
            JsfUtil.addErrorMessage("Código incorrecto");
        }
    }
    
    //Valida la sesión del usuario, negando el acceso al sistema si aun no ha iniciado sesión
    public void validarSesion() throws IOException{
        if(estudiante.getRlId() == null || numero != codigo){
            cerrarSesion();
            JsfUtil.redireccion("login");
        }
    }
    
    public void cerrarSesion(){
        estudiante = new Estudiante();
        flagCodigo = false;
        codigo = 0;
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

    public boolean isFlagEstudiante() {
        return flagEstudiante;
    }

    public void setFlagEstudiante(boolean flagEstudiante) {
        this.flagEstudiante = flagEstudiante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isFlagCodigo() {
        return flagCodigo;
    }

    public void setFlagCodigo(boolean flagCodigo) {
        this.flagCodigo = flagCodigo;
    }
    
}

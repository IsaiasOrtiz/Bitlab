
package com.bitlab.correo;

import com.bitlab.propiedades.Encriptador;
import com.bitlab.propiedades.Propiedades;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class CorreoElectronico {
    
    //método para enviar un correo electrónico
    public void mandarCorreo(String correo, int numero) throws EmailException{
        Propiedades propiedades = new Propiedades();
        Encriptador enc = new Encriptador(); //Instancia para usar método de desencriptación
        HtmlEmail email = new HtmlEmail();
        
        URL url;
        String imagen = "";
        try {
            //Imágen a mostrar en mensaje de correo según el tipo de correo
            url = new URL("https://i.ytimg.com/vi/RuD3MJ0_Lr8/hqdefault.jpg");
            imagen = email.embed(url, "Bitlab-logo");
   
        }catch (MalformedURLException ex) { 
            Logger.getLogger(CorreoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }

        email.setHostName(enc.desencriptador(propiedades.cargarPropiedades().getProperty("smtp")));
        email.setSmtpPort(Integer.parseInt(enc.desencriptador(propiedades.cargarPropiedades().getProperty("port"))));
        email.setAuthenticator(new DefaultAuthenticator(enc.desencriptador(propiedades.cargarPropiedades().getProperty("us")), 
                enc.desencriptador(propiedades.cargarPropiedades().getProperty("pa"))));
        email.setSSLOnConnect(true); 
        email.setFrom(enc.desencriptador(propiedades.cargarPropiedades().getProperty("us")));
        email.setSubject("Autenticación");
        email.addTo(correo);
        //HTML del correo a enviar
        email.setHtmlMsg("<html><head>"
                + "<style>"
                + "div{"
                + "   margin: auto;"
                + "   padding: 10px;"
                + "   width: 100%;"
                + "   text-align: center;"
                + "   box-sizing: border-box;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "   <div>"
                + "      <h1>BITLAB</h1>"
                + "      <img src=\"cid:"+imagen+"\" height=\"150px\" width=\"150px\" style=\"margin:10px; border-radius:5px;\">"
                + "      <h2>Hola,</h2>"
                + "      <h4 style=\"text-align:justify;\">Te saludamos de BITLAB. "
                + "      Este es tu código de autenticación: <b>"+numero+"</b>, digítalo para poder ingresar al sistema.</h4>"
                + "   </div>"
                + "</body>"
                + "</html>");
        email.setTextMsg("Tu cliente de correo electrónico no soporta mensajes HTML");
        email.send();
    }
}

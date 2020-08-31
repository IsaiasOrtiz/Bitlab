/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.manejadores.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author Isaias
 */
@Named("archivos")
@RequestScoped
public class Archivos {
    /**
     * Recibe como parametro una imagen y visualiza un archivo.
     * @param img 
     */
    public void verArchivo(byte[] img) {
        try {
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.getOutputStream().write(img);
            respuesta.getOutputStream().close();

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
        }
    }
    /**
     * Recibe un archivo en bytes y los convierte a pdf para descargarlo
     * y damos el nombre con el que se descarga de string
     * @param archivo
     * @param nombre
     * @return 
     */
    public StreamedContent descargar(byte[] archivo,String nombre) {
        InputStream imgStream = new ByteArrayInputStream(archivo);
        return DefaultStreamedContent.builder()
                .name(nombre+".pdf")
                .contentType("application/pdf")
                .stream(() -> imgStream)
                .build();
    }
    /**
     * Retorna un Streamed Content para visualizar los archivos.
     * @param imgen
     * @return 
     */
    public StreamedContent imagen(byte[] imgen) {
        try {
            InputStream imgStream = new ByteArrayInputStream(imgen);
            return new DefaultStreamedContent(imgStream);
        } catch (Exception e) {
            return null;
        }

    }
    /**
     * Retorna verdadero o falso si el archivo tiene contenido o es nulo
     * @param archivo
     * @return 
     */
    public boolean renderedArchivo(byte[] archivo)
    {
        try {
            return archivo.length>0;
        } catch (Exception e) {
            return false;
        }
    
    }
}

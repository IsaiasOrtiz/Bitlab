
package com.bitlab.propiedades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Propiedades {
    
    private static final String CONFIGURACION = "configuraciones.properties";
    
    public InputStream getResourcesInputAsStream(String configuracion){
        return Propiedades.class.getResourceAsStream(configuracion);
    }
    
    //Método para cargar el archivo de propiedades
    public Properties cargarPropiedades(){
        
        Properties propiedades = new Properties();
        try {
            propiedades.load(getResourcesInputAsStream(CONFIGURACION));
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return propiedades;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.TecnologiasManejadas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author elcon
 */
@Stateless
public class TecnologiasManejadasFacade extends AbstractFacade<TecnologiasManejadas> {

    @PersistenceContext(unitName = "BitlabProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecnologiasManejadasFacade() {
        super(TecnologiasManejadas.class);
    }
    /**
     * Tener la lista de tecnologias que maneja 1 usuario del sistema por su ID
     * @param idUsuario
     * @return 
     */
     public List<TecnologiasManejadas> encontrarTecnologiasUser(Integer idUsuario){
        try {
            Query q = em.createQuery("SELECT t FROM TecnologiasManejadas t WHERE t.esId = :id");
            q.setParameter("id", idUsuario);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
        
    }
    
}

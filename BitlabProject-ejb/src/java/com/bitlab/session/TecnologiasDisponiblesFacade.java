/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.TecnologiasDisponibles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author elcon
 */
@Stateless
public class TecnologiasDisponiblesFacade extends AbstractFacade<TecnologiasDisponibles> {

    @PersistenceContext(unitName = "BitlabProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TecnologiasDisponiblesFacade() {
        super(TecnologiasDisponibles.class);
    }
    
}

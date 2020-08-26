/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.Idiomas;
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
public class IdiomasFacade extends AbstractFacade<Idiomas> {

    @PersistenceContext(unitName = "BitlabProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdiomasFacade() {
        super(Idiomas.class);
    }
     /**
     * Encuentra los idiomas que un usuario especifico sabe
     * por su id unico.
     * @param idUsuario
     * @return 
     */
     public List<Idiomas> encontrarIdiomasUser(Integer idUsuario){
        try {
            Query q = em.createQuery("SELECT i FROM Idiomas i WHERE i.esId.esId = :id");
            q.setParameter("id", idUsuario);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
        
    }
}

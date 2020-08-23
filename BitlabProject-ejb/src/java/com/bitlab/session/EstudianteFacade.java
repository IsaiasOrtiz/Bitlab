/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.Estudiante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author elcon
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "BitlabProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }
    
    public Estudiante encontrarUsuario(Estudiante estudiante){
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.esNombre = :username OR e.esCorreo = :username");
            q.setParameter("username", estudiante.getEsNombre());
        
            return (Estudiante) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
}

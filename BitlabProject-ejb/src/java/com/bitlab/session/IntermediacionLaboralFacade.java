/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.Estudiante;
import com.bitlab.entidades.IntermediacionLaboral;
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
public class IntermediacionLaboralFacade extends AbstractFacade<IntermediacionLaboral> {

    @PersistenceContext(unitName = "BitlabProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IntermediacionLaboralFacade() {
        super(IntermediacionLaboral.class);
    }
    
    public List<IntermediacionLaboral> estudiantesLaborandoPorCurso(int id){
        try {
            Query q = em.createQuery("SELECT i FROM IntermediacionLaboral i WHERE i.esId.csId.csId = :id");
            q.setParameter("id", id);

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Estudiante> estudiantesPorCurso(int id){
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.csId.csId = :id AND e.rlId.rlId = 2 AND e.esnId.esnId = 3");
            q.setParameter("id", id);

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}

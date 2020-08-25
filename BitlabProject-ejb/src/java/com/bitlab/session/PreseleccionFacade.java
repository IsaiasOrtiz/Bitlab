/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.Preseleccion;
import java.util.ArrayList;
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
public class PreseleccionFacade extends AbstractFacade<Preseleccion> {

    @PersistenceContext(unitName = "BitlabProject-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreseleccionFacade() {
        super(Preseleccion.class);
    }
    
    public List<Preseleccion> cursosAplicadosPorEstudiante(int id){
        List<Preseleccion> lista = new ArrayList<Preseleccion>();
        try {
            Query q = em.createQuery("SELECT DISTINCT p.csId.csId FROM Preseleccion p WHERE p.esId.esId = :id");
            q.setParameter("id", id);
            for(Object l:q.getResultList()){
                q = em.createQuery("SELECT p FROM Preseleccion p WHERE p.csId.csId = :id");
                q.setParameter("id", l);
                lista.add((Preseleccion) q.getResultList().get(0));
            }

            return lista;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Preseleccion> detallesCurso(int idEstudiante, int idCurso){
        try {
            Query q = em.createQuery("SELECT p FROM Preseleccion p WHERE p.esId.esId = :idEstudiante AND p.csId.csId = :idCurso");
            q.setParameter("idEstudiante", idEstudiante);
            q.setParameter("idCurso", idCurso);

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}

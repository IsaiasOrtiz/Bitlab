/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitlab.session;

import com.bitlab.entidades.Estudiante;
import com.bitlab.entidades.Preseleccion;
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

    public Estudiante encontrarUsuario(Estudiante estudiante) {
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.esCorreo = :email");
            q.setParameter("email", estudiante.getEsCorreo());

            return (Estudiante) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Estudiante> encontrarSoloEstudiantes() {
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.rlId.rlId = 2");

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Estudiante> encontrarEstudiantesPorEstadoDeSeleccion(Integer id) {
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.esnId.esnId  = :id");
            q.setParameter("id", id);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Estudiante> encontrarEstudiantesPorEstadoDeSeleccionAndCurso(Integer id, Integer idc) {
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.esnId.esnId  = :id AND e.csId.csId = :idC");
            q.setParameter("id", id);
            q.setParameter("idC", idc);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Estudiante> encontrarEstudiantesPorCurso(int id) {
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.csId.csId = :id");
            q.setParameter("id", id);

            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Preseleccion> detallePreseleccion(int id, int csId) {
        try {
            Query q = em.createQuery("SELECT e FROM Preseleccion e WHERE e.esId.esId = :id AND e.csId.csId =:csId");
            q.setParameter("id", id);
            q.setParameter("csId", csId);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}

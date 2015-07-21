/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.AreaOfStudy;
import Entity.Subject;
import helper.IDHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vinh
 */
@Stateless
public class SubjectFacade extends AbstractFacade<Subject> implements SubjectFacadeLocal {

    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubjectFacade() {
        super(Subject.class);
    }

    @Override
    public List<Subject> findByName(String name) {
        return em.createNamedQuery("Subject.findByName").setParameter("name", "%" + name + "%").getResultList();
    }

    @Override
    public void create(Subject entity) {
        String id = IDHelper.generateID();
        Date createdDate = new Date();
        entity.setId(id);
        entity.setCreatedDate(createdDate);
        super.create(entity);
    }

    @Override
    public String createExt(Subject entity) {
        String id = IDHelper.generateID();
        Date createdDate = new Date();
        entity.setId(id);
        entity.setCreatedDate(createdDate);
        super.create(entity);
        return id;
    }

    @Override
    public List<Subject> findByArea(AreaOfStudy area) {
        return new ArrayList<Subject>(area.getSubjectCollection());
    }

    @Override
    public List<Subject> findNameGroupArea(String query) {
        return em.createNamedQuery("Subject.FindNameGroupArea")
                .setParameter("name", "%" + query + "%")
                .getResultList();
    }

}

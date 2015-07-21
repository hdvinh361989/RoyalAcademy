/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.AreaOfStudy;
import Entity.Courses;
import helper.IDHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vinh
 */
@Stateless
public class CoursesFacade extends AbstractFacade<Courses> implements CoursesFacadeLocal {

    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoursesFacade() {
        super(Courses.class);
    }

    @Override
    public void create(Courses entity) {
        entity.setId(IDHelper.generateID());
        entity.setCreatedDate(new Date());
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Courses createExt(Courses entity) {
        String id = IDHelper.generateID();
        Date date = new Date();
        entity.setId(id);
        entity.setCreatedDate(date);
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
        return entity;
    }

    @Override
    public void remove(Courses entity) {
        try {
            Courses course = em.merge(entity);
            AreaOfStudy relatedArea = em.merge(course.getAreaOfStudy());
            relatedArea.getCoursesCollection().remove(course);
            em.remove(course);
        } catch (Exception e) {
        }
    }

    @Override
    public List<Courses> findByName(String name) {
        return em.createNamedQuery("Courses.findByName").setParameter("name", "%" + name + "%").getResultList();
    }

}

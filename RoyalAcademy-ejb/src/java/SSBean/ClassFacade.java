/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Class;
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
public class ClassFacade extends AbstractFacade<Class> implements ClassFacadeLocal {
    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;
private static final Integer MAX_RESULT = 10;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassFacade() {
        super(Class.class);
    }
    @Override
    public void create(Entity.Class entity) {
        String id = IDHelper.generateID();
        Date createdDate = new Date();
        entity.setId(id);
        entity.setCreatedDate(createdDate);
        super.create(entity);
    }
    

    @Override
    public List<String> findNameGroupCourse(String query) {
       return em.createNamedQuery("Subject.FindNameGroupCourse")
                .setParameter("name", "%" + query + "%")
                .getResultList();
    }

    @Override
    public List<Entity.Class> findByCourse_Name(Entity.Courses course, String query) {
         course = em.merge(course);
        return em.createNamedQuery("Entity.Class.findByCourse_Name")
                .setParameter("name", "%" + query + "%")
                .setParameter("course", course)
                .setMaxResults(MAX_RESULT)
                .getResultList();
    }
    
    

    @Override
    public List<Entity.Class> findByName(String course) {
         return em.createNamedQuery("Classes.findByName").setParameter("course", "%" + course + "%").getResultList();
    }
}

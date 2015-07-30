/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.AreaOfStudy;
import Entity.Assignment;
import Entity.ClassDetail;
import Entity.Subject;
import Entity.SubjectCourseDetail;
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

    private static final Integer MAX_RESULT = 10;

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
    public List<Subject> findByArea_Name(AreaOfStudy area, String query) {
        area = em.merge(area);
        return em.createNamedQuery("Subject.findByArea_Name")
                .setParameter("name", "%" + query + "%")
                .setParameter("area", area)
                .setMaxResults(MAX_RESULT)
                .getResultList();
    }

    @Override
    public List<String> findNameGroupArea(String query) {
        return em.createNamedQuery("Subject.FindNameGroupArea")
                .setParameter("name", "%" + query + "%")
                .getResultList();
    }

    @Override
    public void remove(Subject entity) {
        entity = em.merge(entity);
        AreaOfStudy relatedArea = entity.getAreaOfStudy();
        Collection<Assignment> assignmentColls = entity.getAssignmentCollection();
        Collection<Entity.Class> classColls = entity.getClassCollection();
        Collection<ClassDetail> classDetailColls = entity.getClassDetailCollection();
        Collection<SubjectCourseDetail> subjCourseDetailColls = entity.getSubjectCourseDetailCollection();

        if (relatedArea != null) {
            relatedArea = em.merge(relatedArea);
            em.remove(relatedArea);
        }
        
        if (assignmentColls != null) {
            for (Assignment assignment : assignmentColls) {
                assignment = em.merge(assignment);
                em.remove(assignment);
            }
        }
        
        if (classColls != null) {
            for (Entity.Class classColl : classColls) {
                classColl = em.merge(classColl);
                em.remove(classColl);
            }
        }
        
        if (classDetailColls != null) {
            for (ClassDetail classDetailColl : classDetailColls) {
                classDetailColl = em.merge(classDetailColl);
                em.remove(classDetailColl);
            }
        }
        
        if (subjCourseDetailColls != null) {
            for (SubjectCourseDetail subjCourseDetailColl : subjCourseDetailColls) {
                subjCourseDetailColl = em.merge(subjCourseDetailColl);
                em.remove(subjCourseDetailColl);
            }
        }

        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

}

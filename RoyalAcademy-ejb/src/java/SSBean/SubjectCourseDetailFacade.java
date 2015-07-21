/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Courses;
import Entity.Subject;
import Entity.SubjectCourseDetail;
import helper.IDHelper;
import java.util.ArrayList;
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
public class SubjectCourseDetailFacade extends AbstractFacade<SubjectCourseDetail> implements SubjectCourseDetailFacadeLocal {
    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubjectCourseDetailFacade() {
        super(SubjectCourseDetail.class);
    }

    @Override
    public void create(List<Subject> subjects, Courses courses) {
        for (Subject subject : subjects) {
            SubjectCourseDetail tempDetail = new SubjectCourseDetail(IDHelper.generateID());
            tempDetail.setCourse(courses);
            tempDetail.setSubject(subject);
            tempDetail.setCreatedDate(new Date());
            super.create(tempDetail);
        }
    }

    @Override
    public List<SubjectCourseDetail> findByCourse(Courses course) {
        return new ArrayList<SubjectCourseDetail>(course.getSubjectCourseDetailCollection());
//        return em.createNamedQuery("SubjectCourseDetail.findByCourse").setParameter("course", course).getResultList();
    }

    @Override
    public void remove(List<SubjectCourseDetail> oldEntities) {
        for (SubjectCourseDetail oldEntity : oldEntities) {
            oldEntity = em.merge(oldEntity);
            Courses relatedCourse = em.merge(oldEntity.getCourse());
            Subject relatedSubj = em.merge(oldEntity.getSubject());
            relatedCourse.getSubjectCourseDetailCollection().remove(oldEntity);
            relatedSubj.getSubjectCourseDetailCollection().remove(oldEntity);
            super.remove(oldEntity);
        }
    }
    
}

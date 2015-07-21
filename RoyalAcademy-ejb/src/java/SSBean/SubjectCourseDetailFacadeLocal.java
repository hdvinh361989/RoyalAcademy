/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Courses;
import Entity.Subject;
import Entity.SubjectCourseDetail;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface SubjectCourseDetailFacadeLocal {

    void create(SubjectCourseDetail subjectCourseDetail);
    
    void create(List<Subject> subjects, Courses courses);

    void edit(SubjectCourseDetail subjectCourseDetail);

    void remove(SubjectCourseDetail subjectCourseDetail);
    
    void remove(List<SubjectCourseDetail> oldEntities);

    SubjectCourseDetail find(Object id);
    
    List<SubjectCourseDetail> findByCourse(Courses course);

    List<SubjectCourseDetail> findAll();

    List<SubjectCourseDetail> findRange(int[] range);

    int count();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AreaOfStudy;
import Entity.Courses;
import Entity.Subject;
import Entity.SubjectCourseDetail;
import SSBean.AreaOfStudyFacadeLocal;
import SSBean.CoursesFacadeLocal;
import SSBean.SubjectCourseDetailFacadeLocal;
import SSBean.SubjectFacadeLocal;
import helper.MessageHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "courseMB")
@ViewScoped
public class course {

    @EJB
    private SubjectCourseDetailFacadeLocal subjectCourseDetailFacade;

    @EJB
    private SubjectFacadeLocal subjectFacade;

    @EJB
    private AreaOfStudyFacadeLocal areaOfStudyFacade;

    @EJB
    private CoursesFacadeLocal coursesFacade;

//    Global variable
    private Courses newCourse = null;
    private List<Courses> courses = null;
    private TimeZone timeZone = null;
    private List<Subject> selectedSubjects = null;
    private String searchTxt = null;

//    Local variable
    List<SubjectCourseDetail> oldSubjectCourseDetails = null;
    List<Subject> oldSelectedSubjects = null;

    /**
     * Creates a new instance of course
     */
    public course() {
        searchTxt = "";
    }

//    reset input
    private void resetInput() {
        newCourse = new Courses();
        selectedSubjects = null;
        oldSubjectCourseDetails = null;
    }
//    reset

    public void reset() {
        resetInput();
    }
//    Prepare name
//    public void prepareName(ActionEvent event){
//        String name = null;
//        if (newCourse.getAreaOfStudy() != null) {
//            name = newCourse.getAreaOfStudy().getId();
//        }
//        if (newCourse.getStartTime() != null) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(newCourse.getStartTime());
//            String year = String.valueOf(cal.get(Calendar.YEAR));
//            name += "-" + year;
//        }
//        newCourse.setName(name);
//    }

//    Create method
    public String create() {
        try {
            if (newCourse.getId() == null) {
                
                String name = "";
                if (newCourse.getAreaOfStudy() != null) {
                    name = newCourse.getAreaOfStudy().getId();
                }
                if (newCourse.getStartTime() != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(newCourse.getStartTime());
                    String year = String.valueOf(cal.get(Calendar.YEAR));
                    name += "-" + year;
                }
                newCourse.setName(name);

                //Create new course
                coursesFacade.createExt(newCourse);

                //Add record to subjectCoursesDetail
                subjectCourseDetailFacade.create(selectedSubjects, newCourse);

                MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Create Success");
            } else {
                //Update course
                coursesFacade.edit(newCourse);

                if (!oldSelectedSubjects.equals(selectedSubjects)) {
                    //Update subjectCourseDetail table
                    //Logic: remove old entities from subjectCourseDetail, then add new ones
                    subjectCourseDetailFacade.remove(oldSubjectCourseDetails);
                    subjectCourseDetailFacade.create(selectedSubjects, newCourse);
                }

                MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Update Success");
            }
            resetInput();
        } catch (Exception e) {
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Create course fail");
        }
        return null;
    }

//    Load courses
    private List<Courses> loadCourses(String query) {
        return coursesFacade.findByName(query);
    }

//    Edit
    public void edit(Courses course) {
        //Assign selected course for newCourse
        newCourse = course;

        //Load subjectCourseDetail by course
        oldSubjectCourseDetails = subjectCourseDetailFacade.findByCourse(newCourse);

        //Add subjects to selectedSubjects 
        selectedSubjects = new ArrayList<Subject>();
        for (SubjectCourseDetail tempDetail : oldSubjectCourseDetails) {
            Subject newSubject = tempDetail.getSubject();
            selectedSubjects.add(newSubject);
        }
        oldSelectedSubjects = new ArrayList<Subject>(selectedSubjects);

        //Scroll to top nav
        RequestContext.getCurrentInstance().scrollTo("top-nav");
    }

//    Delete course
    public String delete(Courses course) {
        coursesFacade.remove(course);
        MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Delete Success");
        return null;
    }

//    Autocomplete area of study suggest list
    public List<AreaOfStudy> suggestList(String querry) {
        List<AreaOfStudy> results = areaOfStudyFacade.findByName(querry);
        return results;
    }

//    Subject
//    Load subject
    public List<Subject> suggestSubject(String query) {
        return subjectFacade.findByName(query);
    }

    /**
     * @return the newCourse
     */
    public Courses getNewCourse() {
        if (newCourse == null) {
            newCourse = new Courses();
        }
        return newCourse;
    }

    /**
     * @param newCourse the newCourse to set
     */
    public void setNewCourse(Courses newCourse) {
        this.newCourse = newCourse;
    }

    /**
     * @return the courses
     */
    public List<Courses> getCourses() {
        courses = loadCourses(searchTxt);
        return courses;
    }

    /**
     * @return the timeZone
     */
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * @return the selectedSubjects
     */
    public List<Subject> getSelectedSubjects() {
        return selectedSubjects;
    }

    /**
     * @param selectedSubjects the selectedSubjects to set
     */
    public void setSelectedSubjects(List<Subject> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }

    /**
     * @return the searchTxt
     */
    public String getSearchTxt() {
        return searchTxt;
    }

    /**
     * @param searchTxt the searchTxt to set
     */
    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AreaOfStudy;
import Entity.Subject;
import SSBean.AreaOfStudyFacadeLocal;
import SSBean.SubjectFacadeLocal;
import helper.MessageHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "subjectMB")
@ViewScoped
public class subject {
    @EJB
    private AreaOfStudyFacadeLocal areaOfStudyFacade;

    @EJB
    private SubjectFacadeLocal subjectFacade;

//    Global variable
    private Subject newSubject = null;
    private List<Subject> subjects = null;
    private String searchTxt = null;

    /**
     * Creates a new instance of subject
     */
    public subject() {
        searchTxt = "";
    }

//    reset input
    private void resetInput() {
        newSubject = new Subject();
    }

    //    reset
    public void reset() {
        resetInput();
    }

    //Subject CRUD method
    public void save(Subject subj) {
        try {
            if (subj.getId() != null && !subj.getId().equalsIgnoreCase("")) {
                subjectFacade.edit(subj);
                MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Update Success");
            } else {
                subjectFacade.create(subj);
                MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Create Success");
            }
            resetInput();
        } catch (Exception e) {
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), e.getMessage());
        }
    }

    public void edit(Subject subj) {
        newSubject = subj;
        RequestContext.getCurrentInstance().scrollTo("top-nav");
    }

    public List<Subject> loadSubject(String query) {
        return subjectFacade.findByName(query);
    }
    
    public List<Subject> loadSubjectByArea_Name(String areaID, String query){
        AreaOfStudy tempArea = areaOfStudyFacade.find(areaID);
        return subjectFacade.findByArea_Name(tempArea, query);
    }
    
    public List<String> loadGroupArea(String query){
        return subjectFacade.findNameGroupArea(query);
    }

    public void delete(Subject subj) {
        try {
            subjectFacade.remove(subj);
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Delete Success");
        } catch (Exception e) {
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), e.getMessage());
        }
    }
    
    //Load area of study
    public List<AreaOfStudy> getAreaOfStudy(String query){
        return areaOfStudyFacade.findByName(query);
    }

    /**
     * @return the newSubject
     */
    public Subject getNewSubject() {
        if (newSubject == null) {
            newSubject = new Subject();
        }
        return newSubject;
    }

    /**
     * @param newSubject the newSubject to set
     */
    public void setNewSubject(Subject newSubject) {
        this.newSubject = newSubject;
    }

    /**
     * @return the subjects
     */
    public List<Subject> getSubjects() {
//        subjects = loadSubject(searchTxt);
        return subjects;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Assignment;
import SSBean.AssignmentFacadeLocal;
import SSBean.SubjectFacadeLocal;
import helper.MessageHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "assignmentMB")
@ViewScoped
public class assignment {
    @EJB
    private SubjectFacadeLocal subjectFacade;

    @EJB
    private AssignmentFacadeLocal assignmentFacade;
//  Global variable

    private List<Entity.Assignment> assignments;
    private List<Assignment> filteredAss;
    private List<Assignment> selectedAsses;
    private Assignment newAss;
    

//  Local Variable
    /////////////////////////////
    public List<Entity.Subject> loadSubjectInUse() {
        return assignmentFacade.loadSubInUse();
    }

    public void uploadHandler(FileUploadEvent uploadEvent) {
        UploadedFile uploadedFile = uploadEvent.getFile();
        File folder = new File("/var/royalacademy/uploaded/images");
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        File file = null;
        InputStream input = null;
        OutputStream output = null;
        try {
            file = File.createTempFile(filename + "_", "." + extension, folder);

            input = uploadedFile.getInputstream();
            output = new FileOutputStream(file);
            IOUtils.copy(input, output);
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Success");

            newAss.setFileUrl(file.getName());
            
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        } catch (IOException ex) {
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Fail");
            Logger.getLogger(assignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Entity.Assignment> loadAssignments() {
        return assignmentFacade.findAll();
    }

    
    public List<Entity.Subject> getSubjects(String query){
        return subjectFacade.findByName(query); 
    }
    
    
    /**
     * Delete Edit Save Reset
     *
     * @param ass
     */
    public void delete(Entity.Assignment ass) {

    }

    public void edit(Entity.Assignment ass) {

    }

    public void save(Assignment ass) {
        
        assignmentFacade.create(ass);
        newAss = new Assignment();
    }

    public void reset() {
        newAss = new Assignment();
    }

    /**
     * Creates a new instance of assignment
     */
    public assignment() {
    }

    /**
     * @return the assignments
     */
    public List<Entity.Assignment> getAssignments() {
        assignments = loadAssignments();
        return assignments;
    }

    /**
     * @param assignments the assignments to set
     */
    public void setAssignments(List<Entity.Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * @return the filteredAss
     */
    public List<Assignment> getFilteredAss() {
        return filteredAss;
    }

    /**
     * @param filteredAss the filteredAss to set
     */
    public void setFilteredAss(List<Assignment> filteredAss) {
        this.filteredAss = filteredAss;
    }

    /**
     * @return the selectedAsses
     */
    public List<Assignment> getSelectedAsses() {
        return selectedAsses;
    }

    /**
     * @param selectedAsses the selectedAsses to set
     */
    public void setSelectedAsses(List<Assignment> selectedAsses) {
        this.selectedAsses = selectedAsses;
    }

    /**
     * @return the newAss
     */
    public Assignment getNewAss() {
        if (newAss==null) {
            newAss = new Assignment();
        }
        return newAss;
    }

    /**
     * @param newAss the newAss to set
     */
    public void setNewAss(Assignment newAss) {
        this.newAss = newAss;
    }

}

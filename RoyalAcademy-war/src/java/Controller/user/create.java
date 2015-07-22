package Controller.user;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Courses;
import Entity.User;
import SSBean.CoursesFacadeLocal;
import SSBean.UserFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "user_createMB")
@ViewScoped
public class create {

    @EJB
    private CoursesFacadeLocal coursesFacade;
    @EJB
    private UserFacadeLocal userFacade;

    private User newUser = null;

    /**
     * Creates a new instance of create
     */
    public create() {

    }
    
    private User initUser(){
        User tempUser = new User();
        tempUser.setRole("student");
        tempUser.setGender(true);
        tempUser.setAvailable(true);
        return tempUser;
    }

    //Reset input
    private void resetInput() {
        newUser = initUser();
    }

    public void reset() {
        resetInput();
    }

    //CRUD user model
    public void save() {

    }

    //Load courses for autocomplete
    public List<Courses> courseAutos(String query) {
        return coursesFacade.findByName(query);
    }

    /**
     * @return the newUser
     */
    public User getNewUser() {
        if (newUser == null) {
            newUser = initUser();
        }
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

}

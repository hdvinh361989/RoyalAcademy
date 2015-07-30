/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.user;

import Entity.Courses;
import Entity.User;
import SSBean.CoursesFacadeLocal;
import SSBean.UserFacadeLocal;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author vinh
 */
public class User_Abstract {

    @EJB
    private CoursesFacadeLocal coursesFacade;
    @EJB
    private UserFacadeLocal userFacade;

    //Local variable
    /**
     * Creates a new instance of create
     */
    public User_Abstract() {
        
    }

    protected User initUser() {
        User tempUser = new User();
        tempUser.setUsername("");
        tempUser.setPassword("");
        tempUser.setRole("student");
        tempUser.setGender(true);
        tempUser.setAvailable(true);
        return tempUser;
    }   

    //Load courses for autocomplete
    public List<Courses> courseAutos(String query) {
        return coursesFacade.findByName(query);
    }    

    //Load users were created in day
    protected List<User> loadUsersCreatedInDay() {
        return userFacade.findByCreatedDate();
    }

//    Load Courses of users whom were created today
    public List<Courses> loadCoursesByUserCreatedDate() {
        return userFacade.findCoursesByUserCreatedDate();
    }

 

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author vinh
 */
@ManagedBean
@RequestScoped
public class navigator {

    private HashMap<String, String> titles;
    /**
     * Creates a new instance of navigator
     */
    public navigator() {
        titles = new HashMap<String, String>();
        titles.put("/protected/home.xhtml", "Home");
        titles.put("/protected/courses.xhtml", "Courses");
        titles.put("/protected/subjects.xhtml", "Subjects");
        titles.put("/protected/user_create.xhtml", "Create user");
        titles.put("/protected/user_view.xhtml", "View user");
    }

    /**
     * @return the titles
     */
    public HashMap<String, String> getTitles() {
        return titles;
    }

    /**
     * @param titles the titles to set
     */
    public void setTitles(HashMap<String, String> titles) {
        this.titles = titles;
    }
    
  
    
}

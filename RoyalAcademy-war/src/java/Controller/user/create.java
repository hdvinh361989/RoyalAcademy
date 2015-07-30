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
import helper.MessageHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sun.net.www.MessageHeader;

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

//    Global variable
    private User newUser = null;
    private String searchTxt = "";
    private List<User> filterList = null;
    private List<User> selectedUsers = null;
    private List<User> newUserList = null;

    //Local variable
    /**
     * Creates a new instance of create
     */
    public create() {
        init();
    }

    private User initUser() {
        User tempUser = new User();
        tempUser.setUsername("");
        tempUser.setPassword("");
        tempUser.setRole("student");
        tempUser.setGender(true);
        tempUser.setAvailable(true);
        return tempUser;
    }

    private void init() {
        if (newUser == null) {
            newUser = initUser();
        }
        searchTxt = "";
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
        if (newUser.getId() == null || newUser.getId().equalsIgnoreCase("")) {
            userFacade.create(newUser);
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Create Success");
        } else {
            userFacade.edit(newUser);
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Update Success");
        }
        resetInput();
    }

    public void edit(User selectedUser) {
        newUser = selectedUser;
        RequestContext.getCurrentInstance().scrollTo("top-nav");
    }

    public void delete(User selectedUser) {
        userFacade.remove(selectedUser);
        MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Delete success");

    }

    public void modifySelectedUsers() {
        
    }

    //Load courses for autocomplete
    public List<Courses> courseAutos(String query) {
        return coursesFacade.findByName(query);
    }

    //Search recent created user in newUserlist
    private List<User> search() {
        List<User> tempList = new ArrayList<User>();
        if (searchTxt.equals("")) {
            return getNewUserList();
        } else {
            if (getNewUserList() != null && !newUserList.isEmpty()) {
                for (User user : getNewUserList()) {
                    if (user.getFullName().contains(searchTxt) || user.getUsername().contains(searchTxt)) {
                        tempList.add(user);
                    }
                }
            }
        }
        return tempList;
    }

    //Load users were created in day
    private List<User> loadUsersCreatedInDay() {
        return userFacade.findByCreatedDate();
    }

//    Load Courses of users whom were created today
    public List<Courses> loadCoursesByUserCreatedDate() {
        return userFacade.findCoursesByUserCreatedDate();
    }

    /**
     * @return the newUser
     */
    public User getNewUser() {
        return newUser;
    }

    /**
     * @param newUser the newUser to set
     */
    public void setNewUser(User newUser) {
        this.newUser = newUser;
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

    /**
     * @return the filterList
     */
    public List<User> getFilterList() {
        return filterList;
    }

    /**
     * @param filterList the filterList to set
     */
    public void setFilterList(List<User> filterList) {
        this.filterList = filterList;
    }

    /**
     * @return the selectedUser
     */
    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    /**
     * @return the newUserList
     */
    public List<User> getNewUserList() {
        newUserList = loadUsersCreatedInDay();
        return newUserList;
    }

    /**
     * @param newUserList the newUserList to set
     */
    public void setNewUserList(List<User> newUserList) {
        this.newUserList = newUserList;
    }

}

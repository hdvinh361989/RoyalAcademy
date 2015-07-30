/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.user;

import Entity.User;
import SSBean.UserFacadeLocal;
import helper.MessageHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "user_viewMB")
@ViewScoped
public class view extends User_Abstract {

    @EJB
    private UserFacadeLocal userFacade;

//    Global variable
    private User newUserDialog = null;
    private List<User> users = null;
    private List<User> filterList = null;
    private List<User> selectedUsers = null;

//    Local variable
    

    /**
     * Creates a new instance of view
     */
    public view() {
        super();
        newUserDialog = initUser();
    }

//    CRUD user
    private List<User> loadAll() {
        return userFacade.findAll();
    }
    
    

    public void delete(User selectedUser) {
        userFacade.remove(selectedUser);
        MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Delete success");
    }

    public void edit(User selectedUser) {

    }

    public void modifySelectedUsers() {
        //Make sure newUserDialog not null
        if (newUserDialog != null && !selectedUsers.isEmpty()) {
            userFacade.edit(selectedUsers, newUserDialog);
        } else {
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), "newUserDialog must be init or no user is selected");
            return;
        }
        newUserDialog = initUser();
        MessageHelper.addMessage(FacesContext.getCurrentInstance(), "Modify Success");
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        users = loadAll();
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
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
     * @return the selectedUsers
     */
    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    /**
     * @param selectedUsers the selectedUsers to set
     */
    public void setSelectedUsers(List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    /**
     * @return the newUserDialog
     */
    public User getNewUserDialog() {
        return newUserDialog;
    }

    /**
     * @param newUserDialog the newUserDialog to set
     */
    public void setNewUserDialog(User newUserDialog) {
        this.newUserDialog = newUserDialog;
    }

}

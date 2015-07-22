/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import SSBean.UserFacadeLocal;
import helper.MessageHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "userMB")
@ViewScoped
public class User {
//    Local variable

    @EJB
    private UserFacadeLocal userFacade;
    private Entity.User user;
    private String LOGIN_ERR_MESSAGE = "Username or password is not correct";

    /**
     * Creates a new instance of User
     */
    public User() {
        
    }

    public String login() {
        Entity.User resultUsr = null;
        resultUsr = userFacade.findByUsrPss(user.getUsername(), user.getPassword());
        if (resultUsr != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
            if(session.getAttribute("user") == null){                
                session.setAttribute("user", resultUsr);                
            }
            return "protected/home.xhtml" + "?faces-redirect=true";
        } else {
            MessageHelper.addMessage(FacesContext.getCurrentInstance(), LOGIN_ERR_MESSAGE);
        }
        return null;
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "../login.xhtml" + "?faces-redirect=true";
    }

    /**
     * @return the user
     */
    public Entity.User getUser() {
        if (user == null) {
            user = new Entity.User();
        }
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Entity.User user) {
        this.user = user;
    }

}

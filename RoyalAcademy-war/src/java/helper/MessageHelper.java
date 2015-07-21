/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author vinh
 */
public class MessageHelper {
    public static void addMessage(FacesContext currentContext, String summary){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        currentContext.addMessage(null, message);
    }
    public static void addMessage(FacesContext currentContext,String summary, String detail){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        currentContext.addMessage(null, message);
    }
}

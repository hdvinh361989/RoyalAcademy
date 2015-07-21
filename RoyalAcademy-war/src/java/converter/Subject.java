/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import SSBean.SubjectFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author vinh
 */
@FacesConverter("subjectCvtr")
public class Subject implements Converter{
    SubjectFacadeLocal subjectFacade = lookupSubjectFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Entity.Subject subject = null;
        subject = subjectFacade.find(value);
        return subject;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Entity.Subject)value).getId();
    }

    private SubjectFacadeLocal lookupSubjectFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (SubjectFacadeLocal) c.lookup("java:global/RoyalAcademy/RoyalAcademy-ejb/SubjectFacade!SSBean.SubjectFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}

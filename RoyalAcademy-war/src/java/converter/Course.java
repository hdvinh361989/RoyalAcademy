/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import Entity.Courses;
import SSBean.CoursesFacadeLocal;
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
@FacesConverter("courseCvtr")
public class Course implements Converter {
    CoursesFacadeLocal coursesFacade = lookupCoursesFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Courses course = null;
        course = coursesFacade.find(value);
        return course;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Courses)value).getId();
    }

    private CoursesFacadeLocal lookupCoursesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CoursesFacadeLocal) c.lookup("java:global/RoyalAcademy/RoyalAcademy-ejb/CoursesFacade!SSBean.CoursesFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}

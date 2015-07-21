/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import SSBean.AreaOfStudyFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author vinh
 */
@FacesConverter("areaOfStudy")
public class AreaOfStudy implements Converter{
    AreaOfStudyFacadeLocal areaOfStudyFacade = lookupAreaOfStudyFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Entity.AreaOfStudy areaOfStudy;
        areaOfStudy = areaOfStudyFacade.find(value);
        return areaOfStudy;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Entity.AreaOfStudy areaOfStudy = (Entity.AreaOfStudy)value;
        return areaOfStudy.getId();
    }

    private AreaOfStudyFacadeLocal lookupAreaOfStudyFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (AreaOfStudyFacadeLocal) c.lookup("java:global/RoyalAcademy/RoyalAcademy-ejb/AreaOfStudyFacade!SSBean.AreaOfStudyFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}

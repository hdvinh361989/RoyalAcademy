/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author vinh
 */
@FacesConverter("dateTime")
public class DateTime implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Date date = new Date(value.trim());
        return date;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Date)value).toString();
    }
    
}

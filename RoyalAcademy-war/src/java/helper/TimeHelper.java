/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author vinh
 */
@ManagedBean(name = "TIMEZONE")
@RequestScoped
public class TimeHelper {

    public TimeHelper() {
    }
    
    public TimeZone getTimeZone(){
        return TimeZone.getDefault();
    }
}

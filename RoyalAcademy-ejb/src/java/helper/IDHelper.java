/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.Date;
import java.util.UUID;
/**
 *
 * @author vinh
 */
public class IDHelper {
    public static String generateID(){
        UUID ID = UUID.randomUUID();
        return String.valueOf(ID);
    }

}

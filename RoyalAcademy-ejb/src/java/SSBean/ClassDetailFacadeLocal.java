/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.ClassDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface ClassDetailFacadeLocal {

    void create(ClassDetail classDetail);

    void edit(ClassDetail classDetail);

    void remove(ClassDetail classDetail);

    ClassDetail find(Object id);

    List<ClassDetail> findAll();

    List<ClassDetail> findRange(int[] range);

    int count();
    
}

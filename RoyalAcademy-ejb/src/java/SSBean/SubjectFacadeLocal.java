/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.AreaOfStudy;
import Entity.Subject;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface SubjectFacadeLocal {

    void create(Subject subject);
    
    String createExt(Subject subject);

    void edit(Subject subject);

    void remove(Subject subject);

    Subject find(Object id);
    
    List<Subject> findByArea_Name(AreaOfStudy area, String query);
    
    List<String> findNameGroupArea(String query);

    List<Subject> findAll();

    List<Subject> findRange(int[] range);

    int count();

    List<Subject> findByName(String name);
    
}

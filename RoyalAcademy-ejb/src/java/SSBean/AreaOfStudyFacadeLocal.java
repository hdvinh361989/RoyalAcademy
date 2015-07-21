/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.AreaOfStudy;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface AreaOfStudyFacadeLocal {

    void create(AreaOfStudy areaOfStudy);

    void edit(AreaOfStudy areaOfStudy);

    void remove(AreaOfStudy areaOfStudy);

    AreaOfStudy find(Object id);

    List<AreaOfStudy> findAll();
    
    List<AreaOfStudy> findByName(String name);

    List<AreaOfStudy> findRange(int[] range);

    int count();
    
}

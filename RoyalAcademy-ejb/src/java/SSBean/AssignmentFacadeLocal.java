/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Assignment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface AssignmentFacadeLocal {

    void create(Assignment assignment);

    void edit(Assignment assignment);

    void remove(Assignment assignment);

    Assignment find(Object id);

    List<Assignment> findAll();

    List<Assignment> findRange(int[] range);

    int count();
    
}

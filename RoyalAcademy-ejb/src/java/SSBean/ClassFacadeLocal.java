/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Class;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface ClassFacadeLocal {

    void create(Class _class);

    void edit(Class _class);

    void remove(Class _class);

    Class find(Object id);

    List<Class> findAll();

    List<Class> findRange(int[] range);

    int count();

    List<String> findNameGroupCourse(String query);

    List<Entity.Class> findByCourse_Name(Entity.Courses course, String query);

    List<Entity.Class> findByName(String name);
    
}

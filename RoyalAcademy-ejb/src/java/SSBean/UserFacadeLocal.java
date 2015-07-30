/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Courses;
import Entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vinh
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);
    
    void edit(List<User> selectedUsers, User user);

    void remove(User user);

    User find(Object id);
    
    List<User> findByName(String query);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();

    User findByUsrPss(final String username, final String password);

    List<User> findByCreatedDate();

    List<Courses> findCoursesByUserCreatedDate();
    
}

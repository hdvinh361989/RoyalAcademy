/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Courses;
import Entity.User;
import helper.IDHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vinh
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByUsrPss(final String username, final String password) {
        User user;
        user = (User) em.createNamedQuery("User.findByUsernameAndPass")
                .setParameter("username", username.trim())
                .setParameter("password", password.trim())
                .getSingleResult();

        return user;
    }

    @Override
    public List<User> findByName(String query) {
        return em.createNamedQuery("User.findByFullName")
                .setParameter("fullName", query)
                .getResultList();
    }

    @Override
    public void create(User entity) {
        String id = IDHelper.generateID();
        Date createdDate = new Date();
        entity.setId(id);
        entity.setCreatedDate(createdDate);
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(User entity) {
        if (entity != null) {
            entity = em.merge(entity);
            Courses relatedCourse = entity.getCourse();
            if (relatedCourse != null) {
                relatedCourse = em.merge(relatedCourse);
                relatedCourse.getUserCollection().remove(entity);
            }
            super.remove(entity); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<User> findByCreatedDate() {
        Date today = new Date();
        return em.createNamedQuery("User.findByCreatedDate")
                .setParameter("createdDate", today)
                .getResultList();
    }

    @Override
    public List<Courses> findCoursesByUserCreatedDate() {
        Date today = new Date();
        return em.createNamedQuery("User.findCourseByUserCreatedDate")
                .setParameter("createdDate", today)
                .getResultList();
    }

    @Override
    public List<Courses> findAllCoursesInUse() {
        return em.createNamedQuery("User.findAllCourse")
                .getResultList();
    }

    @Override
    public void edit(List<User> selectedUsers, User user) {

        Boolean available = user.getAvailable();
        Courses course = user.getCourse();
        String username = user.getUsername();
        String password = user.getPassword();

        //Iterate selectedUsers to update available, course, username and password
        for (User selectedUser : selectedUsers) {

            if (course != null) {
                selectedUser.setCourse(course);
            }
            if (username != null && !username.equalsIgnoreCase("")) {
                selectedUser.setUsername(prepareUsername(username));
            }

            if (password != null && !password.equalsIgnoreCase("")) {
                selectedUser.setPassword(password);
            }

            selectedUser.setAvailable(available);

            //Update to database
            em.merge(selectedUser);
        }
    }

    private String prepareUsername(String prefix) {
        String id = IDHelper.generateID().substring(0, 4);
        String name = prefix + id;
        return name;
    }

}

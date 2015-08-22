/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.Assignment;
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
public class AssignmentFacade extends AbstractFacade<Assignment> implements AssignmentFacadeLocal {
    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssignmentFacade() {
        super(Assignment.class);
    }

    @Override
    public List<Entity.Subject> loadSubInUse() {
        return em.createNamedQuery("Assignment.loadSubInUse")
                .getResultList() ;
    }

    @Override
    public void create(Assignment entity) {
        String id = IDHelper.generateID();
        Date createDate = new Date();
        entity.setId(id);
        entity.setCreatedDate(createDate);
        super.create(entity); 
    }
    
    
}

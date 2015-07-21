/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SSBean;

import Entity.AreaOfStudy;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vinh
 */
@Stateless
public class AreaOfStudyFacade extends AbstractFacade<AreaOfStudy> implements AreaOfStudyFacadeLocal {
    @PersistenceContext(unitName = "RoyalAcademy-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaOfStudyFacade() {
        super(AreaOfStudy.class);
    }

    @Override
    public List<AreaOfStudy> findByName(String name) {
        return em.createNamedQuery("AreaOfStudy.findByName").setParameter("name", "%" + name + "%").getResultList();
    }
    
}

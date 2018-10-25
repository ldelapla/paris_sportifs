/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Paris;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Louis
 */
@Stateless
public class ParisFacade extends AbstractFacade<Paris> implements ParisFacadeLocal {

    @PersistenceContext(unitName = "paris_sportifsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParisFacade() {
        super(Paris.class);
    }
    
}

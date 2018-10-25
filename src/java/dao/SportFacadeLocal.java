/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Sport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Louis
 */
@Local
public interface SportFacadeLocal {

    void create(Sport sport);

    void edit(Sport sport);

    void remove(Sport sport);

    Sport find(Object id);

    List<Sport> findAll();

    List<Sport> findRange(int[] range);

    int count();
    
}

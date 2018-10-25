/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Rencontre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Louis
 */
@Local
public interface RencontreFacadeLocal {

    void create(Rencontre rencontre);

    void edit(Rencontre rencontre);

    void remove(Rencontre rencontre);

    Rencontre find(Object id);

    List<Rencontre> findAll();

    List<Rencontre> findRange(int[] range);

    int count();
    
}

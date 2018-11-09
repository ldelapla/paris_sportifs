/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RencontreFacadeLocal;
import entity.Rencontre;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;

/**
 *
 * @author alexandre
 */
@Named(value = "rencontreBean")
@SessionScoped
public class RencontreBean implements Serializable {
    @EJB
    RencontreFacadeLocal daoRencontre;
    /**
     * Creates a new instance of AccueilBean
     */
    public RencontreBean() {
    }
    
    public List<Rencontre> getRencontres(){
        return daoRencontre.findAll();
    }
    
    public List<Rencontre> getRencontresNonTermines(){
        List<Rencontre> rencontres = daoRencontre.findAll();
        List<Rencontre> nonTermines = new ArrayList<Rencontre>(); 
        for (Rencontre rencontre : rencontres){
            if(rencontre.getTermine()==0){
                nonTermines.add(rencontre);
            }
        }
        return nonTermines;
    }
}

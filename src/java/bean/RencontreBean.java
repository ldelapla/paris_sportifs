/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.RencontreFacadeLocal;
import dao.SportFacadeLocal;
import entity.Rencontre;
import entity.Sport;
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
    @EJB
    SportFacadeLocal daoSport;
    
    private boolean selective;
    private Sport sportSelected;

    /**
     * Creates a new instance of AccueilBean
     */
    public RencontreBean() {
        selective = false;
    }
    
    public boolean isSelective() {
        return selective;
    }

    public void setSelective(boolean selective) {
        this.selective = selective;
    }

    public Sport getSportSelected() {
        return sportSelected;
    }

    public void setSportSelected(Sport sportSelected) {
        this.sportSelected = sportSelected;
    }
    
    public void setSportSelectedRugby(){
        this.sportSelected = daoSport.find(1);
        this.selective = true;
    }
    
    public void setSportSelectedFoot(){
        this.sportSelected = daoSport.find(2);
        this.selective = true;
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
    
    public List<Rencontre> getSportRencontreNonTermines(){
        List<Rencontre> rencontres = daoRencontre.findAll();
        List<Rencontre> nonTerminesSport = new ArrayList<Rencontre>(); 
        for (Rencontre rencontre : rencontres){
            if(rencontre.getTermine()==0 && rencontre.getIdVisiteur().getIdS().equals(this.sportSelected)){
                nonTerminesSport.add(rencontre);
            }
        }
        return nonTerminesSport;
    }
    
    public List<Rencontre> getWantedRencontre(){
        if (selective){
            return getSportRencontreNonTermines();
        }else {
            return getRencontresNonTermines();
        }
    }
}

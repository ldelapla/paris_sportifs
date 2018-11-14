/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.EquipeFacadeLocal;
import dao.ParisFacadeLocal;
import dao.PredictionFacadeLocal;
import dao.RencontreFacadeLocal;
import dao.SportFacadeLocal;
import dao.UtilisateurFacadeLocal;
import entity.Equipe;
import entity.Rencontre;
import entity.Sport;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Louis
 */
@Named(value = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {

    @EJB
    UtilisateurFacadeLocal daoUser;    
    @EJB
    EquipeFacadeLocal daoEquipe;
    @EJB
    SportFacadeLocal daoSport;
    @EJB
    RencontreFacadeLocal daoRencontre;
    @EJB
    PredictionFacadeLocal daoPrediction;
    @EJB 
    ParisFacadeLocal daoParis;
    
    private Equipe saisieEquipe;
    private Sport saisieSport;
    private int idSSaisie;
    private int idESaisie;
    private Rencontre saisieRencontre;

    /**
     * Creates a new instance of adminBean
     */
    public AdminBean() {
        saisieEquipe = new Equipe();
        saisieSport = new Sport();
        saisieRencontre = new Rencontre();
    }

    public int getIdSSaisie() {
        return idSSaisie;
    }

    public void setIdSSaisie(int idSSaisie) {
        this.idSSaisie = idSSaisie;
    }

    public int getIdESaisie() {
        return idESaisie;
    }

    public void setIdESaisie(int idESaisie) {
        this.idESaisie = idESaisie;
    }
    
    public Equipe getSaisieEquipe() {
        return saisieEquipe;
    }
    
    public void setSaisieEquipe(Equipe saisieEquipe) {
        this.saisieEquipe = saisieEquipe;
    }

    public Sport getSaisieSport() {
        return saisieSport;
    }

    public void setSaisieSport(Sport saisieSport) {
        this.saisieSport = saisieSport;
    }

    public Rencontre getSaisieRencontre() {
        return saisieRencontre;
    }

    public void setSaisieRencontre(Rencontre saisieRencontre) {
        this.saisieRencontre = saisieRencontre;
    }
    
    public String getUser() {
        return daoUser.find("afousse").getNom();
    }
    
    public List<Equipe> getEquipes() {
        System.err.println("equipe");
        return daoEquipe.findAll();
    }
    
    public void createEquipe() {
        saisieEquipe.setIdS(daoSport.find(idSSaisie));
        daoEquipe.create(saisieEquipe);
    }
    
    public void createSport() {
        daoSport.create(saisieSport);
    }
    
    public List<Sport> getSports() {
        return daoSport.findAll();
    }
    
    public List<Rencontre> getRencontres (){
        return daoRencontre.findAll();
    }
    
    public void removeSport(){
        daoSport.remove(saisieSport);
    }
}


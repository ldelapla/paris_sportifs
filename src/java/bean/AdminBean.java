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
import entity.Utilisateur;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    ParisFacadeLocal daoParis;
    
    private Equipe saisieEquipe;
    private Sport saisieSport;
    private int idSSaisie;
    private int idESaisie;
    private int idESaisie2;
    private int coteV, coteD, coteL;
    private Date dateRencontre;
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

    public int getIdESaisie2() {
        return idESaisie2;
    }

    public void setIdESaisie2(int idESaisie2) {
        this.idESaisie2 = idESaisie2;
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
        return daoEquipe.findAll();
    }

    public Date getDateRencontre() {
        return dateRencontre;
    }

    public void setDateRencontre(Date dateRencontre) {
        this.dateRencontre = dateRencontre;
    }
    
    public int getCoteV() {
        return coteV;
    }

    public void setCoteV(int coteV) {
        this.coteV = coteV;
    }

    public int getCoteD() {
        return coteD;
    }

    public void setCoteD(int coteD) {
        this.coteD = coteD;
    }

    public int getCoteL() {
        return coteL;
    }

    public void setCoteL(int coteL) {
        this.coteL = coteL;
    }
    
    public List<Equipe> getEquipesFoot() {
    List<Equipe> equipes = daoEquipe.findAll();
    List<Equipe> liFoot = new ArrayList<Equipe>();
        for (Equipe equipe : equipes){
            if(equipe.getIdS().getIdS()==2){
                liFoot.add(equipe);
            }
        }
      return liFoot;
    } 
    
    public List<Equipe> getEquipesRugby() {
    List<Equipe> equipes = daoEquipe.findAll();
    List<Equipe> liRugby = new ArrayList<Equipe>();
        for (Equipe equipe : equipes){
            if(equipe.getIdS().getIdS()==1){
                liRugby.add(equipe);
            }
        }
      return liRugby;
    }     
    
    public void createEquipe() {
        String message;
        saisieEquipe.setIdS(daoSport.find(idSSaisie));
        daoEquipe.create(saisieEquipe);
                
        message = "L'équipe " + saisieEquipe.getNomE() + " à bien été ajoutée";
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Ajout ! ", message));
        
        saisieEquipe = new Equipe();
    }
    
    public void createSport() {
        daoSport.create(saisieSport);
    }

    public void createRencontre() {
        saisieRencontre.setIdLocal(daoEquipe.find(idESaisie));
        saisieRencontre.setIdVisiteur(daoEquipe.find(idESaisie2));
        saisieRencontre.setTermine(0);
        saisieRencontre.setDateR(dateRencontre);
        daoRencontre.create(saisieRencontre);
        saveMessage();
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
 
    public void removeEquipe(){
        String message;
        Equipe supprimeEquipe = daoEquipe.find(idESaisie);
        message = "L'équipe " + supprimeEquipe.getNomE() + " à bien été supprimée";
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Suppression ! ", message));
        
        saisieEquipe = new Equipe();
        daoEquipe.remove(supprimeEquipe);
    }
    
    public void changeStatut(int idRencontre){
       Rencontre maRencontre = daoRencontre.find(idRencontre);
       maRencontre.setTermine(1);
       daoRencontre.edit(maRencontre);
    }
;    
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "La rencontre a bien été ajoutée") );
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ParisFacadeLocal;
import dao.PredictionFacadeLocal;
import dao.RencontreFacadeLocal;
import dao.UtilisateurFacade;
import entity.Rencontre;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import entity.Paris;
import entity.Prediction;

/**
 *
 * @author alexandre
 */
@Named(value = "panier")
@SessionScoped
public class Panier implements Serializable {

    /**
     * Creates a new instance of Panier
     */
    
    @EJB
    RencontreFacadeLocal daoRencontre;
    @EJB
    ParisFacadeLocal daoParis;
    @EJB
    PredictionFacadeLocal daoPrediction;
    @EJB
    UtilisateurFacade daoUser;
    
    private Paris paris;
    private ArrayList<Prediction> predictions;
    private int mise;
    
    public Panier() {
        paris = new Paris();
        predictions = new ArrayList<>();
    }
    
    /**
     * 
     * @param idRencontre
     * @param choix 'v' => visiteur, 'd' => null, 'l' => local
     */
    public void ajouteRencontre (int idRencontre, Character choix){
        String message;
        String prediction;
        Prediction maPrediction;
        Rencontre rencontrePredite;
        rencontrePredite = daoRencontre.find(idRencontre);
        if (choix == 'd'){
            prediction = "match null";
        } else if (choix == 'v'){
            prediction = "victoire " + rencontrePredite.getIdVisiteur().getNomE();
        } else {
            prediction = "victoire " + rencontrePredite.getIdLocal().getNomE();
        }

        maPrediction = new Prediction(rencontrePredite.getIdR(), this.paris.getIdP());
        maPrediction.setRencontre(rencontrePredite);
        maPrediction.setParis(paris);
        maPrediction.setChoix(choix);
        
        this.predictions.add(maPrediction);
      
        
        message = "Votre prédiction '" + prediction + "' pour le match opposant " + 
                rencontrePredite.getIdVisiteur().getNomE() +
                " à " + rencontrePredite.getIdLocal().getNomE() + 
                " a été ajoutée à votre panier";
        
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Ajout", message));
    }
    
    public void validationPanier (String nomCompte){
        String message = "Votre paris est bien ajouté !";
        paris.setNomCompte(this.daoUser.find(nomCompte));
        paris.setMise(mise);
        paris.setPredictionCollection(predictions);
        
        for (Prediction prediction : predictions){
            daoPrediction.create(prediction);
        }
        
        daoParis.create(paris);
        
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Ajout", message));
        
        this.paris = new Paris();
        this.predictions = new ArrayList<>();
    }
}

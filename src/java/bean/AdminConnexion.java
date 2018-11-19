/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UtilisateurFacadeLocal;
import entity.Rencontre;
import entity.Utilisateur;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexandre
 */
@Named(value = "adminConnexion")
@SessionScoped
public class AdminConnexion implements Serializable {
@EJB
UtilisateurFacadeLocal daoUser;

private Utilisateur utilisateur;
private String sLogin;
private String sMdp;
private boolean connecte;
    /**
     * Creates a new instance of utilisateur
     */
    public AdminConnexion() {
        connecte = false;
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }
    

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getLogin() {
        return sLogin;
    }

    public void setLogin(String sLogin) {
        this.sLogin = sLogin;
    }

    public String getMdp() {
        return sMdp;
    }

    public void setMdp(String sMdp) {
        this.sMdp = sMdp;
    }
    
    public List<Utilisateur> getAllUser(){
        return daoUser.findAll();
    }
    
    public String tryConnect (){
        utilisateur = daoUser.find(sLogin);
        
        if (utilisateur == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erreur !", "Mot de passe ou nom de compte faux"));
            connecte = false;
            return "";
        } else {
            if (utilisateur.getMdp().equals(sMdp)){
                connecte = true;
                if (utilisateur.getAdmin()==1){
                    return "admin.xhtml";
                } else {
                    return "index.xhtml";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erreur !", "Mot de passe ou nom de compte faux"));
                connecte = false;
                return "";
            }
        }
    }
    
    public void deconnexion (){
        this.utilisateur= null;
        this.connecte= false;
        this.sLogin=null;
        this.sMdp=null;
    }
    
    public float getArgent (){
        return utilisateur.getSolde();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Louis
 */
@Entity
@Table(name = "Prediction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prediction.findAll", query = "SELECT p FROM Prediction p")
    , @NamedQuery(name = "Prediction.findByIdR", query = "SELECT p FROM Prediction p WHERE p.predictionPK.idR = :idR")
    , @NamedQuery(name = "Prediction.findByIdP", query = "SELECT p FROM Prediction p WHERE p.predictionPK.idP = :idP")
    , @NamedQuery(name = "Prediction.findByChoix", query = "SELECT p FROM Prediction p WHERE p.choix = :choix")})
public class Prediction implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PredictionPK predictionPK;
    @Column(name = "choix")
    private Character choix;
    @JoinColumn(name = "idP", referencedColumnName = "idP", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paris paris;
    @JoinColumn(name = "idR", referencedColumnName = "idR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rencontre rencontre;

    public Prediction() {
    }

    public Prediction(PredictionPK predictionPK) {
        this.predictionPK = predictionPK;
    }

    public Prediction(int idR, int idP) {
        this.predictionPK = new PredictionPK(idR, idP);
    }

    public PredictionPK getPredictionPK() {
        return predictionPK;
    }

    public void setPredictionPK(PredictionPK predictionPK) {
        this.predictionPK = predictionPK;
    }

    public Character getChoix() {
        return choix;
    }

    public void setChoix(Character choix) {
        this.choix = choix;
    }

    public Paris getParis() {
        return paris;
    }

    public void setParis(Paris paris) {
        this.paris = paris;
    }

    public Rencontre getRencontre() {
        return rencontre;
    }

    public void setRencontre(Rencontre rencontre) {
        this.rencontre = rencontre;
    }
    
    public String getImageNum (){
        return Integer.toString(this.rencontre.getIdVisiteur().getIdS().getIdS());
    }
    
    public String getPronostique (){
        String prono;
        switch (choix) {
        case 'v':  prono = "Victoire " + rencontre.getIdVisiteur().getNomE();
                 break;
        case 'd':  prono = "Match nul";
                 break;
        case 'l':  prono = "Victoire " + rencontre.getIdLocal().getNomE();
                 break;
        default :  prono = "Erreur";
                 break;
        }
        return prono;
    }
    
    public String getRencontreDescription(){
            return rencontre.getIdLocal().getNomE() + " VS " + rencontre.getIdVisiteur().getNomE();
    }
    
    public String coteSelectionnee (){
        String message;
        switch (choix) {
        case 'v':  message = Float.toString(rencontre.getCoteV());
                 break;
        case 'd':  message = Float.toString(rencontre.getCoteD());
                 break;
        case 'l':  message = Float.toString(rencontre.getCoteL());
                 break;
        default :  message = Float.toString(0);
                 break;
        }
        return message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predictionPK != null ? predictionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prediction)) {
            return false;
        }
        Prediction other = (Prediction) object;
        if ((this.predictionPK == null && other.predictionPK != null) || (this.predictionPK != null && !this.predictionPK.equals(other.predictionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prediction[ predictionPK=" + predictionPK + " ]";
    }
    
}

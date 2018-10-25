/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Louis
 */
@Entity
@Table(name = "Rencontre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rencontre.findAll", query = "SELECT r FROM Rencontre r")
    , @NamedQuery(name = "Rencontre.findByIdR", query = "SELECT r FROM Rencontre r WHERE r.idR = :idR")
    , @NamedQuery(name = "Rencontre.findByDateR", query = "SELECT r FROM Rencontre r WHERE r.dateR = :dateR")
    , @NamedQuery(name = "Rencontre.findByTermine", query = "SELECT r FROM Rencontre r WHERE r.termine = :termine")
    , @NamedQuery(name = "Rencontre.findByCoteV", query = "SELECT r FROM Rencontre r WHERE r.coteV = :coteV")
    , @NamedQuery(name = "Rencontre.findByCoteD", query = "SELECT r FROM Rencontre r WHERE r.coteD = :coteD")
    , @NamedQuery(name = "Rencontre.findByCoteL", query = "SELECT r FROM Rencontre r WHERE r.coteL = :coteL")})
public class Rencontre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idR")
    private Integer idR;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateR;
    @Basic(optional = false)
    @NotNull
    @Column(name = "termine")
    private int termine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coteV")
    private float coteV;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coteD")
    private float coteD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coteL")
    private float coteL;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rencontre")
    private Collection<Prediction> predictionCollection;
    @JoinColumn(name = "idLocal", referencedColumnName = "idE")
    @ManyToOne(optional = false)
    private Equipe idLocal;
    @JoinColumn(name = "idVisiteur", referencedColumnName = "idE")
    @ManyToOne(optional = false)
    private Equipe idVisiteur;

    public Rencontre() {
    }

    public Rencontre(Integer idR) {
        this.idR = idR;
    }

    public Rencontre(Integer idR, Date dateR, int termine, float coteV, float coteD, float coteL) {
        this.idR = idR;
        this.dateR = dateR;
        this.termine = termine;
        this.coteV = coteV;
        this.coteD = coteD;
        this.coteL = coteL;
    }

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public int getTermine() {
        return termine;
    }

    public void setTermine(int termine) {
        this.termine = termine;
    }

    public float getCoteV() {
        return coteV;
    }

    public void setCoteV(float coteV) {
        this.coteV = coteV;
    }

    public float getCoteD() {
        return coteD;
    }

    public void setCoteD(float coteD) {
        this.coteD = coteD;
    }

    public float getCoteL() {
        return coteL;
    }

    public void setCoteL(float coteL) {
        this.coteL = coteL;
    }

    @XmlTransient
    public Collection<Prediction> getPredictionCollection() {
        return predictionCollection;
    }

    public void setPredictionCollection(Collection<Prediction> predictionCollection) {
        this.predictionCollection = predictionCollection;
    }

    public Equipe getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Equipe idLocal) {
        this.idLocal = idLocal;
    }

    public Equipe getIdVisiteur() {
        return idVisiteur;
    }

    public void setIdVisiteur(Equipe idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idR != null ? idR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rencontre)) {
            return false;
        }
        Rencontre other = (Rencontre) object;
        if ((this.idR == null && other.idR != null) || (this.idR != null && !this.idR.equals(other.idR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rencontre[ idR=" + idR + " ]";
    }
    
}

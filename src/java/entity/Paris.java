/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Louis
 */
@Entity
@Table(name = "Paris")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paris.findAll", query = "SELECT p FROM Paris p")
    , @NamedQuery(name = "Paris.findByIdP", query = "SELECT p FROM Paris p WHERE p.idP = :idP")
    , @NamedQuery(name = "Paris.findByMise", query = "SELECT p FROM Paris p WHERE p.mise = :mise")})
public class Paris implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idP")
    private Integer idP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mise")
    private float mise;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paris")
    private Collection<Prediction> predictionCollection;
    @JoinColumn(name = "nomCompte", referencedColumnName = "nomCompte")
    @ManyToOne(optional = false)
    private Utilisateur nomCompte;

    public Paris() {
    }

    public Paris(Integer idP) {
        this.idP = idP;
    }

    public Paris(Integer idP, float mise) {
        this.idP = idP;
        this.mise = mise;
    }

    public Integer getIdP() {
        return idP;
    }

    public void setIdP(Integer idP) {
        this.idP = idP;
    }

    public float getMise() {
        return mise;
    }

    public void setMise(float mise) {
        this.mise = mise;
    }

    @XmlTransient
    public Collection<Prediction> getPredictionCollection() {
        return predictionCollection;
    }

    public void setPredictionCollection(Collection<Prediction> predictionCollection) {
        this.predictionCollection = predictionCollection;
    }

    public Utilisateur getNomCompte() {
        return nomCompte;
    }

    public void setNomCompte(Utilisateur nomCompte) {
        this.nomCompte = nomCompte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idP != null ? idP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paris)) {
            return false;
        }
        Paris other = (Paris) object;
        if ((this.idP == null && other.idP != null) || (this.idP != null && !this.idP.equals(other.idP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Paris[ idP=" + idP + " ]";
    }
    
}

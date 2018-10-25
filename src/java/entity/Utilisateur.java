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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Louis
 */
@Entity
@Table(name = "Utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
    , @NamedQuery(name = "Utilisateur.findByNomCompte", query = "SELECT u FROM Utilisateur u WHERE u.nomCompte = :nomCompte")
    , @NamedQuery(name = "Utilisateur.findByMdp", query = "SELECT u FROM Utilisateur u WHERE u.mdp = :mdp")
    , @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom")
    , @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "Utilisateur.findByAdmin", query = "SELECT u FROM Utilisateur u WHERE u.admin = :admin")
    , @NamedQuery(name = "Utilisateur.findBySolde", query = "SELECT u FROM Utilisateur u WHERE u.solde = :solde")})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nomCompte")
    private String nomCompte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "mdp")
    private String mdp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "admin")
    private int admin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solde")
    private float solde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nomCompte")
    private Collection<Paris> parisCollection;

    public Utilisateur() {
    }

    public Utilisateur(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public Utilisateur(String nomCompte, String mdp, String nom, String prenom, int admin, float solde) {
        this.nomCompte = nomCompte;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.admin = admin;
        this.solde = solde;
    }

    public String getNomCompte() {
        return nomCompte;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    @XmlTransient
    public Collection<Paris> getParisCollection() {
        return parisCollection;
    }

    public void setParisCollection(Collection<Paris> parisCollection) {
        this.parisCollection = parisCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomCompte != null ? nomCompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.nomCompte == null && other.nomCompte != null) || (this.nomCompte != null && !this.nomCompte.equals(other.nomCompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Utilisateur[ nomCompte=" + nomCompte + " ]";
    }
    
}

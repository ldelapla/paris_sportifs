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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Louis
 */
@Entity
@Table(name = "Equipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipe.findAll", query = "SELECT e FROM Equipe e")
    , @NamedQuery(name = "Equipe.findByIdE", query = "SELECT e FROM Equipe e WHERE e.idE = :idE")
    , @NamedQuery(name = "Equipe.findByNomE", query = "SELECT e FROM Equipe e WHERE e.nomE = :nomE")})
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idE")
    private Integer idE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nomE")
    private String nomE;
    @JoinColumn(name = "idS", referencedColumnName = "idS")
    @ManyToOne(optional = false)
    private Sport idS;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocal")
    private Collection<Rencontre> rencontreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVisiteur")
    private Collection<Rencontre> rencontreCollection1;

    public Equipe() {
    }

    public Equipe(Integer idE) {
        this.idE = idE;
    }

    public Equipe(Integer idE, String nomE) {
        this.idE = idE;
        this.nomE = nomE;
    }

    public Integer getIdE() {
        return idE;
    }

    public void setIdE(Integer idE) {
        this.idE = idE;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public Sport getIdS() {
        return idS;
    }

    public void setIdS(Sport idS) {
        this.idS = idS;
    }

    @XmlTransient
    public Collection<Rencontre> getRencontreCollection() {
        return rencontreCollection;
    }

    public void setRencontreCollection(Collection<Rencontre> rencontreCollection) {
        this.rencontreCollection = rencontreCollection;
    }

    @XmlTransient
    public Collection<Rencontre> getRencontreCollection1() {
        return rencontreCollection1;
    }

    public void setRencontreCollection1(Collection<Rencontre> rencontreCollection1) {
        this.rencontreCollection1 = rencontreCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idE != null ? idE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.idE == null && other.idE != null) || (this.idE != null && !this.idE.equals(other.idE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Equipe[ idE=" + idE + " ]";
    }
    
}

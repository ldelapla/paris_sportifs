/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Louis
 */
@Embeddable
public class PredictionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idR")
    private int idR;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idP")
    private int idP;

    public PredictionPK() {
    }

    public PredictionPK(int idR, int idP) {
        this.idR = idR;
        this.idP = idP;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idR;
        hash += (int) idP;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredictionPK)) {
            return false;
        }
        PredictionPK other = (PredictionPK) object;
        if (this.idR != other.idR) {
            return false;
        }
        if (this.idP != other.idP) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PredictionPK[ idR=" + idR + ", idP=" + idP + " ]";
    }
    
}

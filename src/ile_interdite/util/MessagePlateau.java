/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite.util;

import ile_interdite.Tresor.Tresor;
import java.io.Serializable;

/**
 *
 * @author IUT2-Dept Info
 */
public class MessagePlateau{// implements Serializable {
    private final Utils.Commandes commande ;
    private final Integer idAventurier ;
    private final Integer idCarte ;
    private final Tresor tresor ;
    private final Integer idTuile ;
    
    public MessagePlateau(Utils.Commandes commande, Integer idAventurier, Integer idCarte, Tresor tresor, Integer idTuile) {
        this.commande = commande ;
        this.idAventurier = idAventurier ;
        this.idCarte = idCarte ;
        this.tresor = tresor ;
        this.idTuile = idTuile ;
    }

    /**
     * @return the commande
     */
    public Boolean hasCommande() {
        return commande != null ;
    }
    public Utils.Commandes getCommande() {
        return commande;
    }

    /**
     * @return the idAventurier`
     */
    public Boolean hasIdAventurier() {
        return idAventurier != null ;
    }
    public Integer getIdAventurier() {
        return idAventurier;
    }

    /**
     * @return the idCarte
     */
    public Boolean hasIdCarte() {
        return idCarte != null ;
    }
    public Integer getIdCarte() {
        return idCarte;
    }

    /**
     * @return the tresor
     */
    public Boolean hasTresor() {
        return tresor != null ;
    }
    public Tresor getTresor() {
        return tresor;
    }

    /**
     * @return the idTuile
     */
    public Boolean hasIdTuile() {
        return idTuile != null;
    }
    public Integer getIdTuile() {
        return idTuile;
    }
    
    @Override
    public String toString() {
        String txt = "" ;
        txt += commande.toString() + " " ;
        if (hasIdAventurier()) {
            txt += " aventurier=" + idAventurier ;
        }
        if (hasIdCarte()) {
            txt += " carte=" + idCarte ;
        }
        if (hasIdTuile()) {
            txt += " tuile=" + idTuile ;
        }
        if (hasTresor()) {
            txt += " tresor=" + tresor.toString() ;
        }
        return txt ;
    }
}
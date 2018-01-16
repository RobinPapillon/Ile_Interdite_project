package ile_interdite.util;

import ile_interdite.Aventurier.Aventurier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;
//import model.aventuriers.Aventurier;

/**
 *
 * @author Eric
 */
public class Utils {
 
    public static enum EtatTuile {
        ASSECHEE("Asséchée"), 
        INONDEE("Inondée"),
        RETIREE("Retirée");

        String libelle ;
        
        EtatTuile(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
    }

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0)),
        VERT("Vert", new Color(0, 195, 0)),
        BLEU("Bleu", new Color(55,194,198)),
        ORANGE("Orange", new Color(255, 148, 0)),
        VIOLET("Violet", new Color(204, 94, 255)),
        JAUNE("Jaune", new Color(255, 255, 0)) ;    

        private final String libelle ;
        private final Color couleur ;


        Pion (String libelle, Color couleur) {
            this.libelle = libelle ;
            this.couleur = couleur ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }

        public static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) return ROUGE ;
            if (VERT.name().equals(name)) return VERT ;
            if (BLEU.name().equals(name)) return BLEU ;
            if (ORANGE.name().equals(name)) return ORANGE ;
            if (VIOLET.name().equals(name)) return VIOLET ;
            if (JAUNE.name().equals(name)) return JAUNE ;
            return null ;
        }
    }
    
        public static enum Role {
	EXPLORATEUR("Explorateur"),
	INGENIEUR("Ingenieur"),
	MESSAGER("Messager"),
	NAVIGATEUR("Navigateur"),
	PILOTE("Pilote"),
	PLONGEUR("Plongeur");
	

        private final String nom ;

        Role (String nom) {
            this.nom = nom ;
        }

        @Override
        public String toString() {
            return this.nom ;
        }

        public static Role getFromName(String name) {
		 if (EXPLORATEUR.name().equals(name)) return EXPLORATEUR ;
            else if (INGENIEUR.name().equals(name)) return INGENIEUR ;
            else if (MESSAGER.name().equals(name)) return MESSAGER ;
            else if (NAVIGATEUR.name().equals(name)) return NAVIGATEUR ;
            else if (PILOTE.name().equals(name)) return PILOTE ;
	    else if (PLONGEUR.name().equals(name)) return PLONGEUR ;
            else    return null ;
        }
    }
	
    public static enum Commandes {
        VALIDER_JOUEURS("Valider l'inscription des joueurs"),
        ASSECHER("Assécher une tuile"),
        DONNER("Donner une carte à un autre joueur"),
        RECUPERER_TRESOR("Récupérer le trésor de la tuile"),
        TERMINER("Terminer son tour"),
	NOP("Aucune action"),
        CHOISIR_CARTE("Utiliser une carte trésor"),
        CHOISIR_TUILE("Sélectionner une tuile"), 
        DEPLACER("Déplacer un pion"),
        VOIR_DEFAUSSE_TIRAGE("Un joueur souhaite voir la défausse de cartes Tirage"),
	VOIR_DEFAUSSE_MEAUX("Un joueur souhaite voir la défausse de cartes montée des eaux");

        private final String libelle ;

        Commandes(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
    }

    public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }
    
    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }    
    
    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher 
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
}

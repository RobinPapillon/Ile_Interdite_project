package ile_interdite.util;

import ile_interdite.util.Utils.Commandes;
import java.util.ArrayList;

/**
 *
 * @author guedona
 */
public class MessageInscription {
    private ArrayList<String> pseudos;
    private int difficulte;
    private Commandes cmd;
      public  MessageInscription(Commandes cmd, int difficulte, ArrayList<String> pseudos) {
	this.cmd = cmd;
        setPseudos(pseudos);
        this.setDifficulte(difficulte);
    }

    /**
     * @return the pseudos
     */
    public ArrayList<String> getPseudos() {
        return pseudos;
    }

    /**
     * @param pseudos the pseudos to set
     */
    public void setPseudos(ArrayList<String> pseudos) {
        this.pseudos = pseudos;
    }

    /**
     * @return the difficulte
     */
    public int getDifficulte() {
        return difficulte;
    }

    /**
     * @param difficulte the difficulte to set
     */
    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }
      
}
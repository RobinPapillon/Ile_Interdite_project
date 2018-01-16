package ile_interdite.Plateau;


import static ile_interdite.util.Utils.EtatTuile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class Grille {
        private HashMap<Coordonnee, Tuile> listeTuiles;
	
	public Grille()
	{
	    Coordonnee coordTmp;
	    listeTuiles = new HashMap<>();
	    //instanciation des tuiles a la mano
	    coordTmp = new Coordonnee(2, 5);
            listeTuiles.put(coordTmp,new Tuile("Le pont des Abîmes",coordTmp));
	    //listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE); // temp
	    
	    coordTmp = new Coordonnee(3, 5);
            listeTuiles.put(coordTmp,new Tuile("La porte de Bronze",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE);
	    
	    coordTmp = new Coordonnee(1, 4);
            listeTuiles.put(coordTmp,new Tuile("La Caverne des ombres",coordTmp));
	    
	    coordTmp = new Coordonnee(2, 4);
            listeTuiles.put(coordTmp,new Tuile("La porte de fer",coordTmp));
	    
	    coordTmp = new Coordonnee(3, 4);
            listeTuiles.put(coordTmp,new Tuile("La porte d'or",coordTmp));
	    //listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE); // temp
	    
	    coordTmp = new Coordonnee(4, 4);
            listeTuiles.put(coordTmp,new Tuile("Les falaises de l'oubli",coordTmp));
	    
	    coordTmp = new Coordonnee(0, 3);
            listeTuiles.put(coordTmp,new Tuile("Le palais de corail",coordTmp));
	    
	    coordTmp = new Coordonnee(1, 3);
            listeTuiles.put(coordTmp,new Tuile("La porte d'argent",coordTmp));
	    
	    coordTmp = new Coordonnee(2, 3);
            listeTuiles.put(coordTmp,new Tuile("Les dunes de l'illusion",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.RETIREE);
	    
	    coordTmp = new Coordonnee(3, 3);
            listeTuiles.put(coordTmp,new Tuile("Héliport",coordTmp));
	    
	    coordTmp = new Coordonnee(4, 3);
            listeTuiles.put(coordTmp,new Tuile("La porte de cuivre",coordTmp));
	    
	    coordTmp = new Coordonnee(5, 3);
            listeTuiles.put(coordTmp,new Tuile("Le jardin des hurlements",coordTmp));
	    
	    coordTmp = new Coordonnee(0, 2);
            listeTuiles.put(coordTmp,new Tuile("La forêt pourpre",coordTmp));
	    
	    coordTmp = new Coordonnee(1, 2);
            listeTuiles.put(coordTmp,new Tuile("Le lagon perdu",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE);
	    
	    coordTmp = new Coordonnee(2, 2);
            listeTuiles.put(coordTmp,new Tuile("Le marais brumeux",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.RETIREE);
	    
	    coordTmp = new Coordonnee(3, 2);
            listeTuiles.put(coordTmp,new Tuile("Observatoire",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE);
	    
	    coordTmp = new Coordonnee(4, 2);
            listeTuiles.put(coordTmp,new Tuile("Le rocher Fantome",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.RETIREE);
	    
	    coordTmp = new Coordonnee(5, 2);
            listeTuiles.put(coordTmp,new Tuile("La caverne du brasier",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE);
	    
	    coordTmp = new Coordonnee(1, 1);
            listeTuiles.put(coordTmp,new Tuile("Le temple du soleil",coordTmp));
	    
	    coordTmp = new Coordonnee(2, 1);
            listeTuiles.put(coordTmp,new Tuile("Le temple de la lune",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.RETIREE);
	    
	    coordTmp = new Coordonnee(3, 1);
            listeTuiles.put(coordTmp,new Tuile("Le palais des marees",coordTmp));
	    
	    coordTmp = new Coordonnee(4, 1);
            listeTuiles.put(coordTmp,new Tuile("Le val du crépuscule",coordTmp));
	    
	    coordTmp = new Coordonnee(2, 0);
            listeTuiles.put(coordTmp,new Tuile("La tour du guet",coordTmp));
	    
	    coordTmp = new Coordonnee(3, 0);
            listeTuiles.put(coordTmp,new Tuile("Le jardin des murmures",coordTmp));
	    listeTuiles.get(coordTmp).setEtat(EtatTuile.INONDEE);
	}

	/**
	 * Retourne les tuiles gauche/droit/haut/bas accessibles à l'aventurier
	 * 
	 * @param tuile
	 *  position de l'aventurier
	 */
	public HashSet<Tuile> getOrt(Tuile tuile)
	{
            HashSet<Tuile> tuilesOrthogonales = new HashSet<>();
	    int x = tuile.getCoordonnee().getX();
	    int y = tuile.getCoordonnee().getY();
	    Coordonnee tmpCoord;
	    
	    // Droite
	    tmpCoord = new Coordonnee(x+1, y);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Gauche
	    tmpCoord = new Coordonnee(x-1, y);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Haut
	    tmpCoord = new Coordonnee(x, y+1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Bas
	    tmpCoord = new Coordonnee(x, y-1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
                
                return tuilesOrthogonales;
	}

	/**
	 *  Retourne les tuiles accessibles aux quatre coins de l'aventurier
	 * 
	 * @param t
	 *  position de l'aventurier
	 */
	public HashSet<Tuile> getDiag(Tuile tuile)
	{
	    HashSet<Tuile> tuilesDiagonales = new HashSet<>();
            int x = tuile.getCoordonnee().getX();
	    int y = tuile.getCoordonnee().getY();
	    Coordonnee tmpCoord;
	    
	    // Haut/Droite
	    tmpCoord = new Coordonnee(x+1, y+1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
	    // Haut/Gauche
	    tmpCoord = new Coordonnee(x-1, y+1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
	    // Bas/Droite
	    tmpCoord = new Coordonnee(x+1, y-1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
	    // Bas/Gauche
	    tmpCoord = new Coordonnee(x-1, y-1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() != EtatTuile.RETIREE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));

            return tuilesDiagonales;
	}

	/**
	 * Retourne une tuile d'apres son nom
	 * 
	 * @param nomTuile
	 *  le nom de la tuile
	 * @return 
	 *  La tuile si trouvée, null sinon
	 */
	public Tuile chercherTuile(String nomTuile)
	{
	    Coordonnee clef = null;
	    Boolean trouve = false;
	    Iterator it = getListeTuiles().keySet().iterator();
	    
	    while(it.hasNext() && !trouve)
	    {
		clef = (Coordonnee) it.next();
		trouve = getListeTuiles().get(clef).getNom().toLowerCase().equals(nomTuile.toLowerCase());
	    }
	    
	    if(trouve)	return getListeTuiles().get(clef);
	    else	return null;
	    
	}

	/**
	 * Retourne toutes les tuiles accessibles par le pilote lors de son action spéciale
	 * 
	 */
	public HashSet<Tuile> getTuilesAccessibles()
	{
		HashSet<Tuile> tuilesAccessibles = new HashSet<>();
                for(HashMap.Entry<Coordonnee, Tuile> eTuile : getListeTuiles().entrySet())
		{
                    if (eTuile.getValue().getEtat()== EtatTuile.ASSECHEE || eTuile.getValue().getEtat()== EtatTuile.INONDEE ){
                        tuilesAccessibles.add(eTuile.getValue());
                    }
                }
            return tuilesAccessibles;
	}

	/**
	 * Retourne les tuiles gauche/droit/haut/bas/pos assechable par l'aventurier
	 * 
	 * @param pos
	 *  La position de l'aventurier
	 */
	public HashSet<Tuile> getOrtAssechables(Tuile pos)
	{
	    HashSet<Tuile> tuilesOrthogonales = new HashSet<>();
            int x = pos.getCoordonnee().getX();
	    int y = pos.getCoordonnee().getY();
	    Coordonnee tmpCoord;
	    
	    // Pos
	    if(pos.getEtat() == EtatTuile.INONDEE) tuilesOrthogonales.add(pos);
	    // Droite
	    tmpCoord = new Coordonnee(x+1, y);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Gauche
	    tmpCoord = new Coordonnee(x-1, y);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Haut
	    tmpCoord = new Coordonnee(x, y+1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Bas
	    tmpCoord = new Coordonnee(x, y-1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
                
            return tuilesOrthogonales;
	}

	/**
	 * Retourne les tuiles assecheble aux quatre coins de l'aventurier
	 * 
	 * @param pos
	 *  La position de l'aventurier
	 */
	public HashSet<Tuile> getDiagAssechables(Tuile pos) {
            HashSet<Tuile> tuilesDiagonales = new HashSet<>(); 
            int x = pos.getCoordonnee().getX();
	    int y = pos.getCoordonnee().getY();
	    Coordonnee tmpCoord;
	    
	    // Pos
	    if(pos.getEtat() == EtatTuile.INONDEE) tuilesDiagonales.add(pos);
	    // Haut/Droite
	    tmpCoord = new Coordonnee(x+1, y+1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
	    // Haut/Gauche
	    tmpCoord = new Coordonnee(x-1, y+1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
	    // Bas/Droite
	    tmpCoord = new Coordonnee(x+1, y-1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
	    // Bas/Gauche
	    tmpCoord = new Coordonnee(x-1, y-1);
	    if(getListeTuiles().get(tmpCoord) != null && getListeTuiles().get(tmpCoord).getEtat() == EtatTuile.INONDEE) tuilesDiagonales.add(getListeTuiles().get(tmpCoord));
   
            return tuilesDiagonales;
	}

	/**
	 * Retourne les tuiles accessible par le plongeur
	 * Le principe de l'algo est de determiner tout les chemins possibles lors du déplacement du plongeur 
	 * 
	 * @param pos
	 *  La position de l'aventurier
	 */
	public HashSet<Tuile> getTuilesPlongeur(Tuile pos) {
	    ArrayList<Tuile> collecTuileNage = new ArrayList<>();			    // Les tuiles que le plongeur peut virtuellement traverser | l'utilisation de l'ArrayList se justifie par la necessite d'un ordre
            HashSet<Tuile> collecTuileArret = new HashSet<>();				    // Les tuiles où le plongeur peut s'arreter
	    int i = 0;
	    
            collecTuileNage.add(pos);							    // La tuile d'où part le plongeur
            collecTuileArret.add(pos);
            
            Iterator it = collecTuileNage.iterator();
            Tuile clef;
	    
            while(i < collecTuileNage.size())						    // Tant que le plongeur peut se deplacer sur une tuile
	    {
                rangerTuileOrto(collecTuileNage.get(i), collecTuileNage, collecTuileArret); // On determine sur quelle tuile le plongeur peut se deplacer et celles où il peut s'arreter
                i++;
            }
            collecTuileArret.remove(pos);
            return collecTuileArret;
	}
	
	/**
	 * Retourne les tuiles gauche/droit/bas/haut de la position du plongeur, quelque soit l'etat de la tuile
	 * 
	 * @param tuile
	 */
	public HashSet<Tuile> getOrtPlongeur(Tuile tuile)
	{
            HashSet<Tuile> tuilesOrthogonales = new HashSet<>();
	    int x = tuile.getCoordonnee().getX();
	    int y = tuile.getCoordonnee().getY();
	    Coordonnee tmpCoord;
	    
	    // Droite
	    tmpCoord = new Coordonnee(x+1, y);
	    if(getListeTuiles().get(tmpCoord) != null) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Gauche
	    tmpCoord = new Coordonnee(x-1, y);
	    if(getListeTuiles().get(tmpCoord) != null) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Haut
	    tmpCoord = new Coordonnee(x, y+1);
	    if(getListeTuiles().get(tmpCoord) != null) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
	    // Bas
	    tmpCoord = new Coordonnee(x, y-1);
	    if(getListeTuiles().get(tmpCoord) != null) tuilesOrthogonales.add(getListeTuiles().get(tmpCoord));
                
            return tuilesOrthogonales;
	}

	

	/**
	 *  Ajoute dans le collections en parametre les tuiles qui sont assechable et celles où le plongeur peut s'arreter depuis une position virtuelle
	 * 
	 * @param pos
	 *  la position virtuelle du plongeur
	 * @param collecTuilesNage
	 *  les tuiles par où passe le plongeur
	 * @param collecTuileArret 
	 *  les tuiles où peut s'arreter le plongeur
	 */
	public void rangerTuileOrto(Tuile pos, ArrayList<Tuile> collecTuilesNage,HashSet<Tuile> collecTuileArret) {
		HashSet<Tuile> tuilesTemp = getOrtPlongeur(pos);
		
                for(Tuile tuile : tuilesTemp){
                    EtatTuile etat = tuile.getEtat();
                    if (etat == EtatTuile.ASSECHEE || etat == EtatTuile.INONDEE){
                        collecTuileArret.add(tuile);
                    }
                    if ((etat == EtatTuile.RETIREE || etat == EtatTuile.INONDEE) && !collecTuilesNage.contains(tuile)){
                        collecTuilesNage.add(tuile);
                    }
                }
	}

    /**
     * @return the listeTuiles
     */
    public HashMap<Coordonnee, Tuile> getListeTuiles()
    {
	return listeTuiles;
    }

}

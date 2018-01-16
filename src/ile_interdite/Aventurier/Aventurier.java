package ile_interdite.Aventurier;

import ile_interdite.CarteTirage.CarteTirage;
import ile_interdite.CarteTirage.CarteTresor;
import ile_interdite.CarteTirage.TypeCarte;
import ile_interdite.CarteTirage.TypeTresor;
import ile_interdite.Plateau.Tuile;
import ile_interdite.Plateau.Grille;
import ile_interdite.util.Utils.Pion;
import ile_interdite.util.Utils.Role;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class Aventurier {

	private String nomJoueur;
        private Tuile position;	// Position de l'aventurier
	private ArrayList<CarteTirage> inventaire;
        
        public Aventurier(String nomJoueur) {
	    this.nomJoueur = nomJoueur;
	    this.inventaire = new ArrayList<>();
	}

	public abstract Role getRole();

	public abstract Pion getPion();

	public Tuile getPosition() {
		return this.position;
        }
       
	/**
	 * Retourne la collection de tuile que l'aventurier peut assecher.
	 * 
	 * @param g
	 *  la grille de jeu
     * @return 
	 */
	public HashSet<Tuile> tuilesAssechables(Grille g)
	{
	    return g.getOrtAssechables(getPosition());
	}

	/**
	 * Retourne la collection de tuile où l'aventurier peut se deplacer.
	 * 
	 * @param g
	 *  la grille de jeu
     * @return 
	 */
	public HashSet<Tuile> getDeplacementsPossibles(Grille g)
	{
		return g.getOrt(getPosition());
	}

	public void setPosition(Tuile t) {
		this.position=t;
	}
    
    public String getNomJoueur()
    {
	return nomJoueur;
    }
    
    /**
     * Sert pour le pilote.
     * 
     * @param logique 
     */
    public void setDeplacementSpecialEffectue(boolean logique){
      // cette méthode ne sert à rien à part pour le pilote lol!
    }
    
     public boolean getDeplacementSpecialEffectue(){
      return true;
     }
    
     /**
      * Place l'aventurier sur la grille à sa position de départ.
      * 
      * @param g
      *	    la grille de jeu.
      */
    public abstract void placerAventurier(Grille g);

    /**
     * @return the inventaire
     */
    public ArrayList<CarteTirage> getInventaire()
    {
	return inventaire;
    }

    /**
     * @param inventaire the inventaire to set
     */
    public void addInventaire(CarteTirage carte)
    {
	this.inventaire.add(carte);
    }

    public HashSet<Aventurier> donnerCarte(ArrayList<Aventurier> joueurs)
    {
	HashSet<Aventurier> aventuriers = new HashSet<>();
	
	aventuriers.addAll(getPosition().getAventuriers());
	aventuriers.remove(this);
	
	// Si la main de l'aventurier est pleine
	for(Aventurier av : aventuriers) if(av.isMainPleine()) aventuriers.remove(av);
	
	return aventuriers;
    }
    
    public int compterCarteTresorDeType(TypeTresor type)
    {
	int count = 0;
	CarteTresor carteTresor;
	
	for(CarteTirage carte : inventaire)
	{
	    if(carte.getType() == TypeCarte.TRESOR)
	    {
		 carteTresor = (CarteTresor)carte;
		if(carteTresor.getTresor().getType() == type)
		{
		    count++;
		}
	    }
	}
	
	return count;
    }

    public boolean isMainPleine()
    {
	return inventaire.size() >= 9;
    }

    public void rmInventaire(CarteTresor carte)
    {
	inventaire.remove(carte);
    }
}

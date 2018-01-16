package ile_interdite.CarteTirage;

import ile_interdite.Aventurier.Aventurier;
import ile_interdite.Controler.ObjetIdentifie;

public abstract class CarteTirage extends ObjetIdentifie
{
    private Aventurier possesseur;
    
    public abstract void utiliser();
    public abstract TypeCarte getType();

    /**
     * @return the possesseur
     */
    public Aventurier getPossesseur()
    {
	return possesseur;
    }

    /**
     * @param possesseur the possesseur to set
     */
    public void setPossesseur(Aventurier possesseur)
    {
	this.possesseur = possesseur;
    }
}

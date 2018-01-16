package ile_interdite.Tresor;

import ile_interdite.CarteTirage.TypeTresor;
import ile_interdite.Plateau.Tuile;
import java.util.ArrayList;

public class Tresor
{
    private TypeTresor type;
    private Tuile[] emplacement;
    private boolean gagne;
    
    public Tresor(TypeTresor type, Tuile emplacement1, Tuile emplacement2)
    {
	this.type = type;
	this.gagne = false;
	this.emplacement = new Tuile[2];
	this.emplacement[0] = emplacement1;
	emplacement1.setTresor(this);
	this.emplacement[1] = emplacement2;
	emplacement2.setTresor(this);
    }

    public TypeTresor getType()
    {
	return type;
    }

    public Tuile[] getEmplacement()
    {
	return emplacement;
    }

    public boolean isGagne()
    {
	return gagne;
    }

    public void setGagne(boolean gagne)
    {
	this.gagne = gagne;
    }
    
    

}

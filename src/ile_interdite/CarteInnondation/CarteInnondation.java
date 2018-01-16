package ile_interdite.CarteInnondation;

import ile_interdite.Plateau.Tuile;

public class CarteInnondation
{
    private final Tuile tuile;

    public CarteInnondation(Tuile tuile)
    {
	this.tuile = tuile;
    }
    
    /**
     * @return the tuile
     */
    public Tuile getTuile()
    {
	return tuile;
    }
}

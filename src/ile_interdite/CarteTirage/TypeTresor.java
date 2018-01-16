/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite.CarteTirage;

import ile_interdite.util.*;
import static ile_interdite.util.Utils.Pion.*;
import java.awt.Color;

/**
 *
 * @author tosiv
 */
public enum TypeTresor
{
    PIERRE("Pierre", Color.DARK_GRAY),
    ZEPHYR("Zéphyr", Color.YELLOW),
    CRISTAL("Cristal", Color.RED),
    CALICE("Calice", Color.GREEN);
    
    private final String nom;
    private final Color couleur;
    
    private TypeTresor(String nom, Color couleur)
    {
	this.nom = nom;
	this.couleur = couleur;
    }
    
    @Override
    public String toString()
    {
        return this.nom ;
    }

    public Color getCouleur()
    {
        return this.couleur ;
    }

    public static Utils.Pion getFromName(String name)
    {
	     if (ROUGE.name().equals(name)) return ROUGE ;
	else if (VERT.name().equals(name))  return VERT ;
	else if (BLEU.name().equals(name))  return BLEU ;
	else if (ORANGE.name().equals(name))return ORANGE ;
        else				    return null ;
    }
}

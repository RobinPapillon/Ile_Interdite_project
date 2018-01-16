package ile_interdite.Controler;

import ile_interdite.CarteInnondation.CarteInnondation;
import ile_interdite.CarteTirage.CarteTirage;
import ile_interdite.Plateau.Tuile;
import ile_interdite.Plateau.Grille;
import ile_interdite.Aventurier.*;
import ile_interdite.CarteTirage.CarteHelico;
import ile_interdite.CarteTirage.CarteMEaux;
import ile_interdite.CarteTirage.CarteSable;
import ile_interdite.CarteTirage.CarteTresor;
import ile_interdite.CarteTirage.TypeCarte;
import ile_interdite.Tresor.Tresor;
import ile_interdite.util.Utils.EtatTuile;
import ile_interdite.util.Utils.Role;
import ile_interdite.util.Utils.Commandes;
import ile_interdite.CarteTirage.TypeTresor;
import ile_interdite.Plateau.Coordonnee;
import ile_interdite.Vue.VueInscription;
import ile_interdite.Vue.VueJoueurCarte;
import ile_interdite.Vue.VuePlateau;
import ile_interdite.util.MessageInscription;
import ile_interdite.util.MessagePlateau;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class Controleur implements Observer{

	private Grille grille;
	private VuePlateau vuePlateau;
	private VueInscription vueInscription;
	private VueJoueurCarte vueJoueurCarte;
	private ArrayList<Aventurier> joueurs;	// La liste des aventurier en jeu
        private int indexJoueurActuel;
	private int nbAction;			// Le nombre d'actions disponible pour un tour
	private ArrayList<CarteTirage> piocheCT;
	private ArrayList<CarteTirage> defausseCT;
	private ArrayList<CarteInnondation> piocheCI;
	private ArrayList<CarteInnondation> defausseCI;
	private final static int[] nbCarteMonteeEau_ATirer = {2,2,3,3,3,4,4,5,5,6};
	private int niveauEau;
        private ArrayList<Tresor> tresors;
	private MessagePlateau lastMsg;
	private MessagePlateau lastLastMsg;

        public Controleur()
	{
	    grille = new Grille();
	    vuePlateau = null;
	    joueurs = new ArrayList<>();
	    indexJoueurActuel = 0;
	    nbAction = 3;
	    piocheCT = new ArrayList<>();
	    defausseCT = new ArrayList<>();
	    piocheCI = new ArrayList<>();
	    defausseCI = new ArrayList<>();
	    niveauEau = 0;
	    tresors = new ArrayList<>();
	    initTresor();
	    lastMsg = new MessagePlateau(Commandes.NOP, null, null, null, null);;
	    lastLastMsg = new MessagePlateau(Commandes.NOP, null, null, null, null);;
	    
	    vueInscription = new VueInscription();
	    vueInscription.addObserver(this);
	}
        
	public void showDeplacementPossible()
	{
	    resetIHM();
	    
	    Aventurier avActuel = getJActuel();
	    HashSet<Tuile> deplacementsNormaux = grille.getOrt(avActuel.getPosition());		    // Collection de tuile qui sont accessibles par un deplacement 'normal'
	    HashSet<Tuile> tuilesDeplacement = avActuel.getDeplacementsPossibles(this.grille);	    // Collection de tuiles accessibles par l'aventurier

	    for(Tuile tui : tuilesDeplacement)
	    { 
		if(deplacementsNormaux.contains(tui))	// Affiche si la tuile est un déplacement 'normal' si le pilote n'a pas utilisé son action spéciale
		{
		    vuePlateau.setBorderTuile(tui.getId(), Color.GREEN);
		}
		else
		{
		    vuePlateau.setBorderTuile(tui.getId(), Color.MAGENTA);
		}
	    }
	}
	
	public boolean seDeplacer(Tuile tuileFin) {
	    Aventurier avActuel = getJActuel();
	    HashSet<Tuile> deplacementsNormaux = grille.getOrt(avActuel.getPosition());		    // Collection de tuile qui sont accessibles par un deplacement 'normal'
	    HashSet<Tuile> tuilesDeplacement = avActuel.getDeplacementsPossibles(this.grille);	    // Collection de tuiles accessibles par l'aventurier
	    
	    if(tuilesDeplacement.contains(tuileFin))
	    {
		avActuel.getPosition().rmJoueur(avActuel);						// retirer le joueur de sa tuile actuelle
		avActuel.setPosition(tuileFin);								// le mettre sur la nouvelle tuile
		tuileFin.addJoueur(avActuel);								// ajouter le joueur à la nouvelle tuile
		 
		if(!avActuel.getDeplacementSpecialEffectue())						// Si le joueur est un pilote et qu'il a effectué son deplacement special, il ne pourra plus l'utiliser avant son prochain tour
		{
		    if(!deplacementsNormaux.contains(tuileFin))
		    {
			avActuel.setDeplacementSpecialEffectue(true);
		    }
		}
		
		return true;
	    }
	    else return false;
	    
	}

	public void JoueurSuivant()
        {
	    if(!getJActuel().isMainPleine())
	    {
		tirerCarteTirage();
		if(!getJActuel().isMainPleine()) tirerCarteTirage();
		vueJoueurCarte.mettreAJour();
	    }
	    
	    if(indexJoueurActuel < joueurs.size()-1)    indexJoueurActuel++;
            else					indexJoueurActuel = 0;
	    
	    getJActuel().setDeplacementSpecialEffectue(false);	// Remet à jour l'action spéciale du pilote
	    actionsPossibles();
	    lastMsg = new MessagePlateau(Commandes.NOP, null, null, null, null);
	    lastLastMsg = new MessagePlateau(Commandes.NOP, null, null, null, null);
            nbAction = 3;
	    
	    tirerCarteTirage();
	    tirerCarteTirage();
	    
	    for(int i = 0 ; i < nbCarteMonteeEau_ATirer[niveauEau] ; i++) tirerCarteInnondation();
	    
	    isPerdu();
	    
	    resetIHM();
	}

	public void showAssechementPossible()
	{
	    resetIHM();
	    
	    Aventurier avActuel = getJActuel();
	    HashSet<Tuile> collecTuile = avActuel.tuilesAssechables(grille);	// Collection des tuiles assechables
	    
	    for(Tuile tuile : collecTuile)
            {
                vuePlateau.setBorderTuile(tuile.getId(), Color.YELLOW);
            }
		
	}
	
	public boolean assecherTuile(Tuile tuileFin)
	{
	    Aventurier avActuel = getJActuel();
	    HashSet<Tuile> collecTuile = avActuel.tuilesAssechables(grille);	// Collection des tuiles assechables
	    
	    if(collecTuile.contains(tuileFin))
	    {
		tuileFin.setEtat(EtatTuile.ASSECHEE);
		return true;
	    }
	    else return false;
	}
	
	public void inscrireJoueurs(ArrayList<String> pseudos)
	{
	    /*
	    //Joueurs predefini
	    //joueurs.add(new Ingenieur("R2D2"));
	    joueurs.add(new Pilote("Foehammer"));
	    //joueurs.add(new Explorateur("Indiana Jones"));
	    //joueurs.add(new Messager("Radar"));
	    //joueurs.add(new Navigateur("Cousteau"));
	    //joueurs.add(new Plongeur("Yellow submarine"));
	    
	    joueurs.get(0).placerAventurier(grille);
	    //joueurs.get(1).placerAventurier(grille);
	    //joueurs.get(2).placerAventurier(grille);
	    //joueurs.get(3).placerAventurier(grille);
	    */
	    
	    ArrayList<Role> rolesDispo = new ArrayList<>();
	    Tuile tuileDepart;
	    
	    for(Role role : Role.values()) rolesDispo.add(role);
	    Collections.shuffle(rolesDispo);
	    
	    for(int i = 0 ; i < pseudos.size() ; i++)
	    {
		     if(rolesDispo.get(0) == Role.EXPLORATEUR)  joueurs.add(new Explorateur(pseudos.get(i)));  // Ajout de l'aventurier dans la liste
		else if(rolesDispo.get(0) == Role.INGENIEUR)	joueurs.add(new Ingenieur(pseudos.get(i)));
		else if(rolesDispo.get(0) == Role.MESSAGER)	joueurs.add(new Messager(pseudos.get(i)));
		else if(rolesDispo.get(0) == Role.NAVIGATEUR)   joueurs.add(new Navigateur(pseudos.get(i)));
		else if(rolesDispo.get(0) == Role.PILOTE)	joueurs.add(new Pilote(pseudos.get(i)));
		else						joueurs.add(new Plongeur(pseudos.get(i)));
		
		joueurs.get(i).placerAventurier(grille);
		rolesDispo.remove(0);
	    }

	}
	
	
    public void initialiserCarte()
    {
	/* INNONDATION */
	Iterator it = grille.getListeTuiles().keySet().iterator();
	
	while(it.hasNext())
	{
	    Coordonnee clef = (Coordonnee)it.next();
	    Tuile tuile = grille.getListeTuiles().get(clef);
	    
	    piocheCI.add(new CarteInnondation(tuile));
	}
	Collections.shuffle(piocheCI);
	
	for(int i = 0 ; i < 6 ; i++) tirerCarteInnondation();
	
	/* TIRAGE */
	// On ajoute a la pioche toutes les cartes que peuvent avoir en debut de jeu les joueurs
	for(Tresor tresor : tresors) for(int i = 0 ; i < 5 ; i++) piocheCT.add(new CarteTresor(tresor));
	for(int i = 0 ; i < 3 ; i++) piocheCT.add(new CarteHelico());
	for(int i = 0 ; i < 2 ; i++) piocheCT.add(new CarteSable());
	Collections.shuffle(piocheCT);
	
	// tous les joeurs font 'un tour pour rien' afin de tirer leur cartes
	for(indexJoueurActuel = 0 ; indexJoueurActuel < joueurs.size() ; indexJoueurActuel++)
	{
	    tirerCarteTirage();
	    tirerCarteTirage();
	    System.out.println(getJActuel().getInventaire().size());
	}
	indexJoueurActuel = 0;
	
	// On fini de constituer la pioche
	for(int i = 0 ; i < 3 ; i++) piocheCT.add(new CarteMEaux());
	Collections.shuffle(piocheCT);
    }
	
    public void tirerCarteTirage()
    {
	if(!piocheCT.isEmpty())
	{
	    
	    CarteTirage carte = piocheCT.get(piocheCT.size()-1);
	    piocheCT.remove(carte);
	    defausseCT.add(carte);

	    if(carte.getType() == TypeCarte.MEAUX)
	    {
		
		niveauEau++;

		Collections.shuffle(defausseCI);
		piocheCI.addAll(defausseCI);
		defausseCI.clear();
		
		piocheCT.remove(carte);
		defausseCT.add(carte);
	    }
	    else if(!getJActuel().isMainPleine())
	    {
		getJActuel().addInventaire(carte);
		
		piocheCT.remove(carte);
		defausseCT.add(carte);
	    }
	}
	else
	{
	    
	    piocheCT.addAll(defausseCT);
	    defausseCT.clear();
	    Collections.shuffle(piocheCT);
	    tirerCarteTirage();
	}
    }

    public void tirerCarteInnondation()
    {
	if(!piocheCI.isEmpty())
	{
	    CarteInnondation carte = piocheCI.get(piocheCI.size()-1);
	    piocheCI.remove(carte);
	    
	    carte.getTuile().monteeDesEaux();
	    if(carte.getTuile().getEtat() != EtatTuile.RETIREE) defausseCI.add(carte);
	}
	else
	{
	    piocheCI.addAll(defausseCI);
	    defausseCI.clear();
	    Collections.shuffle(piocheCI);
	    tirerCarteInnondation();
	}
    }
    
    
	public boolean isPerdu()
	{
	    if(
		    niveauEau > 9 ||
		    // si le niveau de l'eau est trop haut
                    grille.chercherTuile("Héliport").getEtat() == EtatTuile.RETIREE ||
		    // Si l'héliport à sombré
		    (tresors.get(0).getEmplacement()[0].getEtat() == EtatTuile.RETIREE && tresors.get(0).getEmplacement()[1].getEtat() == EtatTuile.RETIREE) ||
		    (tresors.get(1).getEmplacement()[0].getEtat() == EtatTuile.RETIREE && tresors.get(1).getEmplacement()[1].getEtat() == EtatTuile.RETIREE) ||
		    (tresors.get(2).getEmplacement()[0].getEtat() == EtatTuile.RETIREE && tresors.get(2).getEmplacement()[1].getEtat() == EtatTuile.RETIREE) ||
		    (tresors.get(3).getEmplacement()[0].getEtat() == EtatTuile.RETIREE && tresors.get(3).getEmplacement()[1].getEtat() == EtatTuile.RETIREE) )
	    {
		vueJoueurCarte.getWindow().dispose();
		vuePlateau.getWindow().dispose();
		return true;
	    }
	    else return false;
		    // Si les emplacements d'un des trésor est englouti
	}
	
	public boolean isGagne()
	{
	    boolean tresorOK = true;
	    int i = 0;
	    
	    while( i < tresors.size() && tresorOK)
	    {
		tresorOK = tresors.get(i).isGagne();
		i++;
	    }
	    
	    if(tresorOK && grille.chercherTuile("Héliport").getAventuriers().size() >= 4)
	    {
		vueJoueurCarte.getWindow().dispose();
		vuePlateau.getWindow().dispose();
		return true;
	    }
	    else return false;
	}
		
	public void actionsPossibles()
	{
	    isDeplacementPossible();
	    isAssechementPossible();
	    isDonCartePossible();
	    isGagnerTresorPossible();
	}
	
	public boolean isDeplacementPossible()
	{
	    HashSet<Tuile> tuiles = getJActuel().getDeplacementsPossibles(grille);
	    
	    vuePlateau.setEnableButton_deplacement(!tuiles.isEmpty());
	    return !tuiles.isEmpty();
	}
	
	public boolean isAssechementPossible()
	{
	    HashSet<Tuile> tuiles = getJActuel().tuilesAssechables(grille);
	    
	    vuePlateau.setEnableButton_assecher(!tuiles.isEmpty());
	    return !tuiles.isEmpty();
	}
	
	public boolean isDonCartePossible()
	{
	    HashSet<Aventurier> aventuriers = getJActuel().donnerCarte(joueurs);
	    
	    vuePlateau.setEnableButton_donnerCarte(!aventuriers.isEmpty());
	    return !aventuriers.isEmpty();
	}
	
	public boolean isGagnerTresorPossible()
	{
	    boolean surLaTuile = false;   
	    int i = 0;
	    
	    while(i < tresors.size() && !surLaTuile)
	    // Pour tout les types de tresor ou jusqu'à qu'un tresor puisse être recupere
	    {
		if(!tresors.get(i).isGagne())
		// Si le tresor n'est pas encore gagne
		{
		    if(getJActuel().compterCarteTresorDeType(tresors.get(i).getType()) >= 4)
		    // Si le joueur a assez d'un type de carte
		    {
			if(getJActuel().getPosition() == tresors.get(i).getEmplacement()[1] || getJActuel().getPosition() == tresors.get(i).getEmplacement()[0])
			// si le joueur est sur l'une des deux carte où il peut recuperer un tresor
			{
			    surLaTuile = true;
			}
		    }
		}
		i++;
	    }
	    
	    vuePlateau.setEnableButton_gagnerTresor(surLaTuile);
	    return surLaTuile;
	}
	
	
    private void showAventurierReceveur()
    {
	
    }
	
    public void donnerCarte(Aventurier avChoisi, CarteTresor carte)
    {
        HashSet<Aventurier> aventuriers = getJActuel().donnerCarte(joueurs);
        if(aventuriers.contains(avChoisi))
        {
	    getJActuel().rmInventaire(carte);
	    avChoisi.addInventaire(carte);
	}
    }
	
	
    private void gagnerTresor()
    {
	getJActuel().getPosition().getTresor().setGagne(true);
    }
    
    private void resetIHM()
    {
	Iterator it = grille.getListeTuiles().keySet().iterator();
	Tuile t;
	Coordonnee c;
	
	while(it.hasNext())
	{
	    c = (Coordonnee)it.next();
	    t = (Tuile)grille.getListeTuiles().get(c);
	    
	    vuePlateau.setDefaultTuile(t.getId());
	}
	
	vuePlateau.setBorderTuile(getJActuel().getPosition().getId(), Color.RED);
    }
    
    @Override
    public void update(Observable VueAventurier, Object action)
    {
	if(action.getClass() == MessagePlateau.class)
	{
	    MessagePlateau message = (MessagePlateau)action;

	    switch(message.getCommande())
	    {
		case DEPLACER:
		    showDeplacementPossible();
		    break;

		case ASSECHER:
		    showAssechementPossible();
		    break;

		case DONNER:
		    showAventurierReceveur();
		    break;

		case RECUPERER_TRESOR:
		    gagnerTresor();
		    vuePlateau.setEnableButton_gagnerTresor(false);
		    nbAction--;
		    break;

		case TERMINER:
		    nbAction = 0;
		    break;

		case VOIR_DEFAUSSE_TIRAGE:
		    break;

		case VOIR_DEFAUSSE_MEAUX:
		    break;

		case CHOISIR_TUILE:
		    Tuile tuileChoix = (Tuile)ObjetIdentifie.getFromId(message.getIdTuile());
		    switch(lastMsg.getCommande())
		    {
			case DEPLACER:
			    if(seDeplacer(tuileChoix))
			    {
				isGagne();
				nbAction--;
				actionsPossibles();
			    }
			    resetIHM();
			    break;
			case ASSECHER:
			    if(assecherTuile(tuileChoix))
			    {
				nbAction--;
				isAssechementPossible();
				if(getJActuel().getRole() == Role.INGENIEUR)
				{
				    showAssechementPossible();
				}
				else resetIHM();
			    }
			    break;
			case CHOISIR_TUILE:
			    if(lastLastMsg.getCommande() == Commandes.ASSECHER && getJActuel().getRole() == Role.INGENIEUR)
			    {
				if(assecherTuile(tuileChoix)) isAssechementPossible();
			    }
			    resetIHM();
			    break;
			default:break;
		    }
		    break;
		    
		case CHOISIR_CARTE:
		    System.out.println("!");
		    switch(lastMsg.getCommande())
		    {
			case VALIDER_JOUEURS:
			    System.out.println("!");
			    if(lastLastMsg.getCommande() == Commandes.DONNER)
			    {
				
				CarteTirage carte = (CarteTirage)ObjetIdentifie.getFromId(message.getIdCarte());
				Aventurier av = (Aventurier)ObjetIdentifie.getFromId(lastMsg.getIdAventurier());
				if(carte.getType() == TypeCarte.TRESOR)
				{
				    donnerCarte(av, (CarteTresor)carte);
				    vueJoueurCarte.mettreAJour();
				}
			    }
			    break;
			default:break;
		    }
		    break;
	    }

	    if(nbAction <= 0)
	    {
		JoueurSuivant();
	    }
	    else
	    {
		lastLastMsg = lastMsg;
		lastMsg = message;
	    }
	}
	else
	{
	    MessageInscription message = (MessageInscription)action;
	    niveauEau = message.getDifficulte();
	    inscrireJoueurs(message.getPseudos());

	    vueInscription.getWindow().dispose();
	    lancerPartie();
	}
    }
	
    public void initTresor()
    {
	tresors.add(new Tresor(TypeTresor.PIERRE, grille.chercherTuile("Le temple de la lune"), grille.chercherTuile("Le temple du soleil")));
	tresors.add(new Tresor(TypeTresor.ZEPHYR, grille.chercherTuile("Le jardin des hurlements"), grille.chercherTuile("Le jardin des murmures")));
	tresors.add(new Tresor(TypeTresor.CRISTAL, grille.chercherTuile("La caverne des ombres"), grille.chercherTuile("La caverne du brasier")));
	tresors.add(new Tresor(TypeTresor.CALICE, grille.chercherTuile("Le palais de corail"), grille.chercherTuile("Le palais des marees")));
    }
	
    public void lancerPartie()
    {
	initialiserCarte();
	vuePlateau = new VuePlateau(grille);
	vuePlateau.addObserver(this);
	vueJoueurCarte = new VueJoueurCarte(joueurs);
	vueJoueurCarte.addObserver(this);
	
	getJActuel().setDeplacementSpecialEffectue(false);
	resetIHM();
    }

    public Grille getGrille()
    {
	return grille;
    }
 
    public ArrayList<Aventurier> getJoueurs()
    {
	return joueurs;
    }

    
    public int getIndexJoueurActuel()
    {
	return indexJoueurActuel;
    }
    
    public Aventurier getJActuel()
    {
	return joueurs.get(indexJoueurActuel);
    }


}
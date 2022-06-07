package Ressources;

public abstract class Constantes {

	/*******************FENETRE*******************/
	// Dimensions de la fenetre d'animation
	public static final int LARGEUR_FENETRE = 1500;
	public static final int HAUTEUR_FENETRE = 600;
	public static final int MARGE_FENETRE = 50;
	
	// Dimensions du frame(tout interface)
	public static final int LARGEUR_FRAME = 600;
	public static final int HAUTEUR_FRAME = 600;
	
	// Dimensions des lignes
	public static final int EPAISSEUR_LIGNE = 10;
	
	/*******************UTILISATEUR******************/
	// Dimensions de la voiture de l'utilisateur
	public static final int LARGEUR_USER = 40;
	public static final int HAUTEUR_USER = 60;
	
	// Position initiale de la voiture de l'utilisateur
	public final static int X_POS_INIT_USER = (LARGEUR_FENETRE - LARGEUR_USER)/ 2;
	public final static int Y_POS_USER = 250;	
	
	// Unit de dplacement de la voiture de l'utilisateur
	public final static int DX_USER = 2;
	
	// Limite de d�placement de la voiture de l'utilisateur
	public final static int LIMITE_GAUCHE_USER = 30;
	public final static int LIMITE_DROITE_USER = 220;
	
	/***************VOITURES TRAFIC*************/
	// Dimensions du moto
	public static final int LARGEUR_MOTO = 20;
	public static final int HAUTEUR_MOTO = 30;
	
	// Dimensions du camion
	public static final int LARGEUR_CAMION = 40;
	public static final int HAUTEUR_CAMION = 120;
	
	// Dimension de la voiture
	public static final int LARGEUR_VOITURE = 40;
	public static final int HAUTEUR_VOITURE = 60;
	

	/******************TRAFIC******************/
	public static final float FREQUENCE_APPARITION = 2; //2sec
	
	public static int VITESSE_RELATIVE_VOITURE_MIN = 20;
	public static int VITESSE_RELATIVE_VOITURE_MAX = -20;
	 
	public static int VITESSE_RELATIVE_MOTO_MIN = -10;
	public static int VITESSE_RELATIVE_MOTO_MAX = -30;
	
	public static int VITESSE_RELATIVE_CAMION_MIN = 30;
	public static int VITESSE_RELATIVE_CAMION_MAX = 10;
	
	
	/******************TEMPS*******************/
	// Temps de la boucle
	public static final int TEMPS_BOUCLE = 30; // 5 ms
	public static final int NOMBRE_BOUCLES = 250;
	
	
	// zommer
	public static double ZOOM = 0.1;
	
}
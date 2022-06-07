package vehicules;


import Ressources.Constantes;
import entites.Entites;
import entites.RandomValeur;

public class Camion extends Entites{
/**** VARIABLES ****/
	public static int vitesseRelativeCamion = RandomValeur.randomVitesse(Constantes.VITESSE_RELATIVE_CAMION_MIN, Constantes.VITESSE_RELATIVE_CAMION_MAX);
	public static int VitesseCamion =90 + 10*vitesseRelativeCamion;
	
	/**** CONSTRUCTEUR ****/
		
	public Camion(int X_POS_INIT, int Y_POS_INIT) {
			
		// Initialisation des variables de la super classe
		super.xPos = X_POS_INIT;
		super.yPos = Y_POS_INIT;
		super.largeur = Constantes.LARGEUR_CAMION;
		super.hauteur = Constantes.HAUTEUR_CAMION;
		super.dy = 0;
		super.dx = vitesseRelativeCamion;
		super.dxinit =vitesseRelativeCamion;

		}
}
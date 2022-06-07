package vehicules;


import Ressources.Constantes;
import entites.Entites;
import entites.RandomValeur;

public class Moto extends Entites{
/**** VARIABLES ****/
	public static int vitesseRelativeMoto = RandomValeur.randomVitesse(Constantes.VITESSE_RELATIVE_MOTO_MIN, Constantes.VITESSE_RELATIVE_MOTO_MAX);
	public static int VitesseMoto =90 + 10*vitesseRelativeMoto;
	
	/**** CONSTRUCTEUR ****/
		
	public Moto(int X_POS_INIT,int Y_POS_INIT) {
			
		// Initialisation des variables de la super classe
		super.xPos = X_POS_INIT;
		super.yPos = Y_POS_INIT;
		super.largeur = Constantes.LARGEUR_MOTO;
		super.hauteur = Constantes.HAUTEUR_MOTO;
		super.dy = 0;
		super.dx = vitesseRelativeMoto;
		super.dxinit = vitesseRelativeMoto;

		}
}

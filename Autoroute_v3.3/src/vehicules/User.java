package vehicules;


import Ressources.Constantes;
import entites.Entites;

public class User extends Entites{
/**** VARIABLES ****/
	
	
	/**** CONSTRUCTEUR ****/
		
	public User() {
			
		// Initialisation des variables de la super classe
		super.xPos = Constantes.X_POS_INIT_USER;
		super.yPos = Constantes.Y_POS_USER;
		super.largeur = Constantes.LARGEUR_USER;
		super.hauteur = Constantes.HAUTEUR_USER;
		super.dx = 0;
		super.dy = 0;

		}
		
		
	/**** METHODES ****/
	public int deplacementUser() {
		// Renvoie la nouvelle position de l'utilisateur aprs dplacement ventuel
		if(this.dx < 0){if(this.xPos > Constantes.LIMITE_GAUCHE_USER) {this.xPos = this.xPos + this.dx;}
		}else if(dx > 0) {if(this.xPos + this.dx < Constantes.LIMITE_DROITE_USER) {this.xPos = this.xPos + this.dx;}}
		return this.xPos;
	}

}
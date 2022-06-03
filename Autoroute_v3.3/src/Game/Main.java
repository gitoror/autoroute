package Game;

import javax.swing.JFrame;

import Game.Scene;
import Ressources.Constantes;

public class Main {
	
	/**** VARIABLES ****/
	
	public static Scene scene;

	/**** METHODES ****/
	
	public static void main(String[] args) {
		
		// Creation de la fenêtre
		JFrame fenetre = new JFrame("Simulation");
		
		// attention au changement hauteur fenêtre
		fenetre.setSize(Constantes.LARGEUR_FENETRE , Constantes.HAUTEUR_FENETRE + 200);
		fenetre.setResizable(false);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		
		// Association du panneau Scene à la fenêtre
		scene = new Scene();		
		fenetre.setContentPane(scene);
		
		
				
		fenetre.setVisible(true);

	}
}

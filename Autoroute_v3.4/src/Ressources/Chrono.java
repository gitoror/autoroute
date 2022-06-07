package Ressources;
import Game.Main;

public class Chrono implements Runnable{
	private final int PAUSE = Constantes.TEMPS_BOUCLE;
	public static int compteTours = 0;
	public static int ajout_compte_tour=2;
	
/**** METHODES ****/
	
	@Override
	public void run() {
		while(true){ 
			Main.scene.repaint(); // Appel de la méthode PaintComponent de l'objet scene
			try {Thread.sleep(PAUSE);} // temps de pause (5 ms)
			catch (InterruptedException e) {}
			
			this.compteTours = this.compteTours + this.ajout_compte_tour ;
		}
	}
}


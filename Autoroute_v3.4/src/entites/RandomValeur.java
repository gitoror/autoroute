package entites;

public class RandomValeur {
	protected int RandomVitesse;

	public static int randomVitesse(int vitesseRelativeMin, int vitesseRelativeMax) {
		// renvoie une vitesse int qui est entre vitesseMin et vitesseMax
		return vitesseRelativeMin + (int)(Math.random())*(vitesseRelativeMax - vitesseRelativeMin +1);
	}
}
package entites;



public abstract class Entites {
/**** VARIABLES ****/	
	
	protected int largeur, hauteur, xPos, yPos, dx, dy, dxinit;
	protected boolean vivant;
	
		
	
/**** CONSTRUCTEUR ****/
	
	// pas de constructeur
	
	
/**** METHODES ****/
	public int getLargeur() {return largeur;}
	
	public int getDxinit() {
		return dxinit;
}
	public void setDxinit(int dxinit) {
		this.dxinit = dxinit;
}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public boolean isVivant() {
		return vivant;
	}
	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}

	
	public int deplacement() {
		// Renvoie la nouvelle position de la voiture 
		this.xPos = this.xPos + this.dx;
		return this.xPos;
	}
/**	public int freinage() {
		this.dx = this.dx - 1;
		return this.dx;
	}**/
	
}
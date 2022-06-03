package vehicules;

import Ressources.Constantes;
import entites.Entites;

public class Recentrer {
	public void recentrer(Entites A)
	{
		int posY = A.getyPos();
		if (posY < Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE - A.getLargeur()/2 && posY > 50)
		{
			A.setDy(2);
		}
		if (posY > 2*Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE + A.getLargeur()/2 && posY < 450)
		{
			A.setDy(2);
		}
		if (posY <= 50 || posY >= 450 )
		{
			A.setDy(0);
		}
	}

}

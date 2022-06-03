package Simulation;

import java.util.LinkedList;
import java.util.List;

import Ressources.Constantes;
import entites.Entites;
import vehicules.Camion;
import vehicules.Moto;
import vehicules.User;
import vehicules.Voiture;

public class Trafic {
	
	/* Variables */	
	public LinkedList<Entites> listev1;
	public LinkedList<Entites> listev2;
	public LinkedList<Entites> listev3;
    public static int randfile;
    public static double randvehi;
    public Entites vehicule;
    public static double randdep;
    public User user;
    

	
	/* Constructeur */
	public Trafic() {
		listev1= new LinkedList<Entites>();
		listev2= new LinkedList<Entites>();
		listev3= new LinkedList<Entites>();
		for (int i=1; i<1000; i++) {
			randfile = (int)(Math.random()*3);
			randvehi = Math.random()*3;
			user= new User();
			listev1.add(user);
			if (randfile==0) {
				if (randvehi<0.3) {vehicule =new Moto(125000-250*i,50);}
				else if (randvehi < 1.5) {vehicule= new Voiture(125000-250*i,50);}
				else {vehicule =new Camion(125000-250*i,50);}
				listev1.add(vehicule);	
			}
			else if (randfile==1) {
				if (125000-250*i < 800 || 125000-250*i > 1100) {
					if (randvehi<1.15) {vehicule =new Moto(125000-250*i,250);}
					else if (randvehi < 2.3) {vehicule =new Voiture(125000-250*i,250);}
					else {vehicule =new Camion(125000-250*i,250);}
					listev2.add(vehicule);
				}
			}
			else {
				if (randvehi<1.48) {vehicule =new Moto(125000-250*i,450);}
				else if (randvehi < 2.96) {vehicule =new Voiture(125000-250*i,450);}
				else {vehicule =new Camion(125000-250*i,450);}
				listev3.add(vehicule);
				}	
			}
		
		
	}


	
	@SuppressWarnings("rawtypes")
	public LinkedList voie(int i) {
		if (i>0 && i<150) {
			return listev1;
		} else if (i>=150 && i<350) {
			return listev2;
		} else {
			return listev3;
		}
	}
	
	
	
	
	public void freinage(Entites A, Entites B) {
		//programme qui déteremine si la voiture A doit freiner pour ne pas rentrer en collision avec la voiture B
		if (A.getxPos()-B.getxPos()< 1.2*B.getHauteur() && A.getDx()<B.getDx()) /*freinage d'urgence*/ {
			A.setDx(A.getDx()+5);
		} else if (A.getxPos()-B.getxPos()< 1.7*B.getHauteur() && A.getDx()<B.getDx())/*freinage normal*/ {
			A.setDx(A.getDx()+2);
		} else if (A.getxPos()-B.getxPos()> 2*B.getHauteur() && A.getDx()>A.getDxinit()) {
			A.setDx(A.getDx()-1); // Interessant si on pouvait mettre +0.2 ou alors augmenter échelle des possibilités de vitesse
		}
		
	}
	public static int binarySearchWide(LinkedList<Entites> L, int f, int l, int val1, int val2){
	    if (l >= f){
	      int mid = (int)f + (l - f)/2;
	      if (L.get(mid).getxPos() >val1 && L.get(mid).getxPos() < val2){
	        return 15000;
	      }
	      if (L.get(mid).getxPos() > val2){
	        //recherche dans le sous-tableau à gauche
	        return binarySearchWide(L, mid+1, l, val1, val2); 
	      }else{
	        //recherche dans le sous-tableau à droit
	        return binarySearchWide(L, f, mid-1, val1, val2); 
	      }
	    }
	    return (int)f + (l - f)/2;
	   }
	
	public static int binarySearch(LinkedList<Entites> L1, int first, int last, int key){  
        if (last>=first){  
            int mid = first + (last - first)/2;  
            if (L1.get(mid).getxPos() == key){  
            return mid;  
            }  
            if (L1.get(mid).getxPos() > key){  
            return binarySearch(L1, first, mid-1, key);//search in left subarray  
            }else{  
            return binarySearch(L1, mid+1, last, key);//search in right subarray  
            }  
        }  
        return -1;
	}
	
	
	
	public void gestion() {
	
	// gestion distance inter-vehicules + gestion dépassements
		
		for (int i=1;i<listev3.size()-1;i++) {
			freinage(listev3.get(i),listev3.get(i+1));
			}
	}
	
	
	
	
	
	
	
	public void recentrer(Entites A)
	{
		/**programme qui sert à recentrer Entitie A une fois qu'il dépasse le fil de la voie */
		int posY = A.getyPos();
		int filHaut = Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE; 
		int filBas = 2*Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE;
		
		// recentrer Entites A sur voie milieu après depassement 
		if (posY < filBas - A.getLargeur()/2 && posY > 250 )
		{
			A.setDy(-2);
		}
		
		// recentrer Entities A sur voie haute après depassement
		if (posY < filHaut - A.getLargeur()/2 && posY > 50)
		{
			A.setDy(-2);
		}
		
		// recentrer Entities A sur voie millieu après rabattre
		if (posY > filHaut + A.getLargeur()/2 && posY < 250)
		{
			A.setDy(2);
		}
		
		// recentrer Entities A sur voie basse après rabattre
		if (posY > filBas + A.getLargeur()/2 && posY < 450)
		{
			A.setDy(2);
		}
		else
		{
			A.setDy(0);
		}
	}
	

	
	public void depasser(Entites A, int indice)
	{
		/**programme qui sert à 
		 * déplacer Entites A vers le haut justqu'à Entites A situe au millieu 
		 * supprimer Entites A sur la liste ancienne
		 * ajouter Entites A sur la nouvelle liste, avec un nouveau indice
		 */
		int posY = A.getyPos();
		int filHaut = Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE; 
		int filBas = 2*Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE;
		
		A.setDy(-2);
		
		
		// dépassement de la voie du millieu à la voie en haut
		if (posY > filHaut - A.getLargeur()/2 - 2 && posY <= 250)
		{
			if (posY <= filHaut - A.getLargeur()/2)
			{
				listev2.remove(A);
				listev1.add(indice, A);
			}			
		}
		
		// dépassement de la voie en bas à la voie du millieu
		if (posY > filBas - A.getLargeur()/2 - 2 && posY <= 450)
		{
			if(posY <= filBas - A.getLargeur()/2 - 2)
			{
				listev3.remove(A);
				listev2.add(indice, A);
			}		
		}
		
		
		
	}

	
	public void rabattre(Entites A, int indice)
	{
		/**programme qui sert à 
		 * déplacer Entites A vers le bas justqu'à Entites A situe au millieu 
		 * supprimer Entites A sur la liste ancienne
		 * ajouter Entites A sur la nouvelle liste, avec un nouveau indice
		 */
		int posY = A.getyPos();
		int filHaut = Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE; 
		int filBas = 2*Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE;
		
		A.setDy(2);
		
		
		// la voiture se rabat de la voie en haut à la voie du milleu
		if (posY < filHaut - A.getLargeur()/2 + 2 && posY >= 50)
		{
			if (posY >= filHaut - A.getLargeur()/2)
			{
				listev1.remove(A);
				listev2.add(indice, A);
			}			
		}
		
		// la voiture se rabat de la voie du milleu à la voie en bas
		if (posY < filBas - A.getLargeur()/2 + 2 && posY >= 250)
		{
			if(posY >= filBas - A.getLargeur()/2 )
			{
				listev2.remove(A);
				listev3.add(indice, A);
			}		
		}
		


}
}

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
    public Entites A;
    public Entites B;
    public Entites C;
    public int[] res;
    public int[] res2;
    

	
	/* Constructeur */
	public Trafic() {
		listev1= new LinkedList<Entites>();
		listev2= new LinkedList<Entites>();
		listev3= new LinkedList<Entites>();
		for (int i=1; i<1000; i++) {
			randfile = (int)(Math.random()*3);
			randvehi = Math.random()*3;
			if (i==500) {
			user= new User();
			listev2.add(user);
			}
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
		
		public void gestion() {
			
			// file de droite 
			
			int longueur1 = listev1.size();
			for (int i=0; i<longueur1-1 ;i++ ) {
				A = this.listev1.get(i);
				B = this.listev1.get(i+1);
				res = this.voiture_gauche(A);
				if (devant(A,B)== 1 && plus_lente(A, B) == 1) {
					if (res[0] == 0) { // pas de voiture à gauche
						this.depasser(A,res[1]); // indice de la voiture derriere
						vitesse_croisière(A);
					}
					else {
						freiner(A, B);
						recentrer(A);
					}
				}
				else {
					if (res[0]==1) { // Si voiture à gauche
						C = this.listev2.get(res[1]);
						if (plus_lente(A, C) == 1) {
							freiner(A,C);
							recentrer(A);
							}	
						}
					recentrer(A);
					} 
				}
			
			// file du milieu 
			
			int longueur2 = listev2.size();
			for (int i=0; i<longueur2-1 ;i++ ) {
				A = this.listev2.get(i);
				B = this.listev2.get(i+1);
				res = this.voiture_gauche(A);
				res2 = this.voiture_droite(A);
				if (devant(A,B)== 1 && plus_lente(A, B) == 1) {
					if (res[0] == 0) { // pas de voiture à gauche
						this.depasser(A,res[1]); // indice de la voiture derriere
						vitesse_croisière(A);
					}
					else {
						freiner(A, B);
						recentrer(A);
					}
				}
				else {
					if (res[0]==1) { // Si voiture à gauche
						C = this.listev2.get(res[1]);
						if (plus_lente(A, C) == 1 ) {
							freiner(A,C);
							recentrer(A);
							}	
						}
					else if (res2[0]==0) {//si pas de voiture à droite
						rabattre(A, res2[1]);
						vitesse_croisière(A);
					}
					else {
						recentrer(A);
						vitesse_croisière(A);
					}
					} 
				}
			
			// file de gauche
			
			int longueur3= listev3.size();
			for (int i=0; i<longueur3-1 ;i++ ) {
				A = this.listev3.get(i);
				B = this.listev3.get(i+1);
				res2 = this.voiture_droite(A);
				if (devant(A, B)== 1 && plus_lente(A, B) == 1) {
						freiner(A, B);
						recentrer(A);
					}
				else {
					if (res2[0]==0) {//si pas de voiture à droite
						rabattre(A, res2[1]);
						vitesse_croisière(A);
					}
					else {
						recentrer(A);
						vitesse_croisière(A);
					}
					} 
				}
					
			}

		
		
	
	
	
	


		public LinkedList<Entites> voie(int i) {
			if (i>0 && i<150) {
				return listev1;
			} else if (i>=150 && i<350) {
				return listev2;
			} else {
				return listev3;
			}
		}
		
		public static int[] binarySearchWide(LinkedList<Entites> L, int f, int l, int pos, int val1, int val2){
		int[] L1 =new int[2];
		if (l >= f){
	      int mid = (int)f + (l - f)/2;
	      if (L.get(mid).getxPos() > pos && L.get(mid +1).getxPos() < pos){
	    	  if(L.get(mid).getxPos()> val1 && L.get(mid).getxPos()< val2) {
	    		  L1[0]=1;
	    		  L1[1]=mid;
	    		  System.out.println("******** "+L1[0]+" - "+L1[1]);
	    		  return L1;
	    		}
	      }
	      if (L.get(mid+1).getxPos() < pos){
	        //recherche dans le sous-tableau ? gauche
	        return binarySearchWide(L, mid+1, l, pos, val1, val2); 
	      }else{
	        //recherche dans le sous-tableau ? droit
	        return binarySearchWide(L, f, mid-1, pos, val1, val2); 
	      }
	    }
	    L1[0]=0;
	    L1[1]=(int)f + (l - f)/2;
	    System.out.println("******** "+L1[0]+" - "+L1[1]);
	    //System.out.println(L1);
		return L1;
	   }

	
		public int[] voiture_gauche(Entites A) {
		int[] L =new int[2];
		if (A.getyPos()>350) {
			L[0]=1;
			L[1]=-1;
		}
		else {
			L= binarySearchWide(voie(A.getyPos()+200),0,voie(A.getyPos()+200).size(),A.getxPos(),A.getxPos()+2*A.getHauteur(),A.getxPos()-3*A.getHauteur());
		}
		return L;
	}
	
		public int[] voiture_droite(Entites A) {
		int[] L=new int[2];
		if (A.getyPos() <150) {
			L[0]=1;
			L[1]=-1;
			return L;
		}
	    return binarySearchWide(voie(A.getyPos()-200),0,voie(A.getyPos()-200).size(),A.getxPos(),A.getxPos()+2*A.getHauteur(),A.getxPos()-3*A.getHauteur());
}
	
	
		public void vitesse_croisière(Entites A) {
			if (A.getDx()>A.getDxinit()) {A.setDx(A.getDx()-1);}; 
		}
	
	
	
		public int devant(Entites A, Entites B) {
			// B devant A
			if (B.getxPos() < A.getxPos()) {
				return 1;
			
			}
			return 0;
		}
			
		public int plus_lente(Entites A, Entites B) {
			// B plus lente que A
			if (B.getDx() < A.getDx()) {
				return 1;
			}
			return 0;
		}
			
		public void freiner(Entites A, Entites B) {
			// A freine  
			A.setDx((int)(Math.ceil(A.getDx() - (1/(B.getDx()-A.getDx())*0.01))));
		}
	
	
	
	
	
	
	
	
		public void recentrer(Entites A)
{
	/**programme qui sert à recentrer Entitie A une fois qu'il dépasse le fil de la voie */
	int posY = A.getyPos();
	int filHaut = Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE; 
	int filBas = 2*Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE;
	
	// recentrer Entites A sur voie milieu après il se rabat 
	if (posY < filBas - A.getLargeur()/2 && posY > 250 )
	{
		A.setDy(-2);
	}
	
	// recentrer Entities A sur voie haute après il se rabat
	if (posY < filHaut - A.getLargeur()/2 && posY > 50)
	{
		A.setDy(-2);
	}
	
	// recentrer Entities A sur voie millieu après depassement
	if (posY > filHaut + A.getLargeur()/2 && posY < 250)
	{
		A.setDy(2);
	}
	
	// recentrer Entities A sur voie basse après depassement
	if (posY > filBas + A.getLargeur()/2 && posY < 450)
	{
		A.setDy(2);
	}
	else
	{
		A.setDy(0);
	}
}



		public void rabattre(Entites A, int indice)
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
	
	
	// deplacement de la voie du millieu à la voie en haut
	if (posY > filHaut - A.getLargeur()/2 - 2 && posY <= 250)
	{
		if (posY <= filHaut - A.getLargeur()/2)
		{
			listev2.remove(A);
			listev1.add(indice, A);
		}			
	}
	
	// deplacement de la voie en bas à la voie du millieu
	if (posY > filBas - A.getLargeur()/2 - 2 && posY <= 450)
	{
		if(posY <= filBas - A.getLargeur()/2 - 2)
		{
			listev3.remove(A);
			listev2.add(indice, A);
		}		
	}
	
	
	
}


		public void depasser(Entites A, int indice)
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
	
	
	// la voiture déplace de la voie en haut à la voie du milleu
	if (posY < filHaut - A.getLargeur()/2 + 2 && posY >= 50)
	{
		if (posY >= filHaut - A.getLargeur()/2)
		{
			listev1.remove(A);
			listev2.add(indice, A);
		}			
	}
	
	// la voiture déplace de la voie du milleu à la voie en bas
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

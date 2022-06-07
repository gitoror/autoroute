package Simulation;

import java.util.LinkedList;

import entites.Entites;

public class test {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trafic trafic = new Trafic();
		
		
		//System.out.println(binarySearchWide());
		
}
	public static int[] binarySearchWide(LinkedList<Entites> L, int f, int l, int pos, int val1, int val2){
		int[] L1 =new int[2];
		if (l >= f){
	      int mid = (int)f + (l - f)/2;
	      if (L.get(mid).getxPos() > pos && L.get(mid +1).getxPos() < pos){
	    	  if((L.get(mid).getxPos()> val1 && L.get(mid).getxPos()< val2) || (L.get(mid+1).getxPos()> val1 && L.get(mid+1).getxPos()< val2)) {
	    		  L1[0]=1;
	    		  L1[1]=mid;
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
		return L1;
	   }
}

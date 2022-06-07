package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Game.Main;
import Ressources.Chrono;
import Ressources.Constantes;
import entites.Entites;
import Simulation.Trafic;

// importer entités, trafic



public class Scene extends JPanel implements MouseListener, ActionListener{
	
/**** VARIABLES ****/
public Trafic trafic;
public vehicules.User user = new vehicules.User();
private Font police;
private JButton bouton;
private JButton bouton1;
private JButton bouton2;
private JButton bouton3;
private JButton bouton4;
private JButton bouton5;
public boolean status=true;
private JSlider slider;
private JTextField zonetxt;
	
/**** CONSTRUCTEUR ****/
	
	public Scene() {
	
	/**** ATTRIBUTS ****/
		super();
		
		//Instanciation des boutons et label
		Proprietes_Contenant();
		
		// Instanciation trafic
		trafic = new Trafic();
		
		// Instanciation du chrono, ATTTENTION toujours l'instancier à la fin
		Thread chronoEcran = new Thread(new Chrono());
		chronoEcran.start();

	}
	
	/**** METHODES ****/
	
	public void paintComponent(Graphics g) {
		
		Graphics g2 = (Graphics2D) g;
		this.setFocusable(true);
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
		
		if(status == true)
		{
		// Dessin du fond d'écran
			
			
		g2.setColor(Color.BLACK);
		g2.fillRect(0, (int)(((1-Constantes.ZOOM)*Constantes.HAUTEUR_FENETRE)/2), Constantes.LARGEUR_FENETRE , (int)(Constantes.HAUTEUR_FENETRE*Constantes.ZOOM));
		
		// Dessin des lignes blacnhes 
		g2.setColor(Color.WHITE);
		g2.fillRect(0, (int)((Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE)*Constantes.ZOOM)+(int)(((1-Constantes.ZOOM)*Constantes.HAUTEUR_FENETRE)/2), Constantes.LARGEUR_FENETRE, (int)(Constantes.EPAISSEUR_LIGNE*Constantes.ZOOM));
		g2.fillRect(0 ,(int)((2*Constantes.HAUTEUR_FENETRE/3-2*Constantes.EPAISSEUR_LIGNE)*Constantes.ZOOM) +(int)(((1-Constantes.ZOOM)*Constantes.HAUTEUR_FENETRE)/2), Constantes.LARGEUR_FENETRE, (int)(Constantes.EPAISSEUR_LIGNE*Constantes.ZOOM));
		
		// Dessin trafic
		trafic.gestion();
		
		g2.setColor(Color.RED);
		int l1 = trafic.listev1.size();
		for(int i=0; i<l1 ; i++) {
			Entites vehicule = trafic.listev1.get(i);
			g2.fillRect((int)((vehicule.deplacement()*Constantes.ZOOM) + Constantes.X_POS_INIT_USER*(1 - Constantes.ZOOM)), (int)(vehicule.getyPos()*Constantes.ZOOM) + (int)(((1-Constantes.ZOOM)*Constantes.HAUTEUR_FENETRE)/2), (int)(vehicule.getHauteur()*Constantes.ZOOM), (int)(vehicule.getLargeur()*Constantes.ZOOM));
		}
		int l2 = trafic.listev2.size();
		for(int i=0; i<l2 ; i++) {
			Entites vehicule = trafic.listev2.get(i);
			g2.fillRect((int)((vehicule.deplacement()*Constantes.ZOOM) + Constantes.X_POS_INIT_USER*(1 - Constantes.ZOOM)), (int)(vehicule.getyPos()*Constantes.ZOOM) + (int)(((1-Constantes.ZOOM)*Constantes.HAUTEUR_FENETRE)/2), (int)(vehicule.getHauteur()*Constantes.ZOOM), (int)(vehicule.getLargeur()*Constantes.ZOOM));
		}
		int l3 = trafic.listev3.size();
		for(int i=0; i<l3 ; i++) {
			Entites vehicule = trafic.listev3.get(i);
			g2.fillRect((int)((vehicule.deplacement()*Constantes.ZOOM) + Constantes.X_POS_INIT_USER*(1 - Constantes.ZOOM)), (int)(vehicule.getyPos()*Constantes.ZOOM) + (int)(((1-Constantes.ZOOM)*Constantes.HAUTEUR_FENETRE)/2), (int)(vehicule.getHauteur()*Constantes.ZOOM), (int)(vehicule.getLargeur()*Constantes.ZOOM));
		}
		
		}
		
		else if (status == false)
		{
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
		}
	}
	
	
	
	
	
	private void Proprietes_Contenant() {			
		// ajout des boutons
		this.setLayout(null);
		this.bouton = new JButton();
		this.bouton.setText("Afficher vitesse/ Actualiser");
		this.bouton.setBounds(530,650,200,15);
		this.add(bouton);
		this.bouton.addMouseListener(this);
		
		this.bouton1 = new JButton("Reprendre");
		this.bouton1.setBounds(300,650,100,20);
		this.add(bouton1);
		this.bouton1.addMouseListener(this);
		
		this.bouton2 = new JButton("Quitter");
		this.bouton2.setBounds(350,675,100,20); 
		this.add(bouton2);
		this.bouton2.addMouseListener(this);
		
		this.bouton3 = new JButton("Pause");
		this.bouton3.setBounds(400,650,100,20);
		this.add(bouton3);
		this.bouton3.addMouseListener(this);
		
		this.bouton4 = new JButton("Zoomer");
		this.bouton4.setBounds(1000,650,100,20);
		this.add(bouton4);
		this.bouton4.addMouseListener(this);
		
		this.bouton5 = new JButton("Dézoomer");
		this.bouton5.setBounds(1100,650,100,20);
		this.add(bouton5);
		this.bouton5.addMouseListener(this);
		
		// instanciation du slider
		slider= new JSlider(0,5);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setValue(2);
		
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.requestFocusInWindow();
		
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(slider.getValue());
				slider.requestFocus();
				slider.setFocusable(false);
				Chrono.ajout_compte_tour=slider.getValue();
				//Constantes.ZOOM=slider.getValue();
				//slider.removeChangeListener(this);
				}
		});
		this.add(slider);
		slider.setBounds(750, 650, 180, 50);
		
		zonetxt = new JTextField();
		zonetxt.setBounds(530, 675, 170, 30);
		zonetxt.setText("vitesse (dizaines seulement)");
		this.zonetxt.addActionListener(this);
		this.add(zonetxt);
	}
		
	@Override
	public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		if (e.getSource() == this.bouton){
			//Voitures.Vitesse= 90+ 10*Constantes.VITESSE_RELATIVE_USER;
			//this.lab1.setText(Vitesse);
			this.requestFocusInWindow();}
		if (e.getSource()==this.bouton2) {
			System.exit(0);
			this.requestFocusInWindow();}
		if (e.getSource()==this.bouton3) {
			status=false;
			this.requestFocusInWindow();}
		if (e.getSource()==this.bouton1) {
			status=true;
			this.requestFocusInWindow();}
		if (e.getSource() == this.bouton4)
		{
			Constantes.ZOOM += 0.1;
		}
		if (e.getSource() == this.bouton5)
		{
			Constantes.ZOOM -= 0.1;
		}
		
		/**if (e.getSource()==this.zonetxt) {
			status=false;
			this.requestFocusInWindow();
		}**/
		
		}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.zonetxt) {
			//Constantes.VITESSE_RELATIVE=(Integer.parseInt(this.zonetxt.getText())-90)/10;
			//int longueur=Main1.scene.trafic.tabVoitures.size();
			//for (int i=0; i<longueur; i++) {
				//Main1.scene.trafic.tabVoitures.get(i).setDy(Constantes.VITESSE_RELATIVE) // mettre le jeu en pause quannd saisi du texte
						;}
		//Constantes.ZOOM=(Integer.parseInt(this.zonetxt.getText()));
			this.requestFocusInWindow();
		}
		
	}

package presentation;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.rmi.Naming;

import domaine.DataInterface;


/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
@SuppressWarnings("serial")
public class Client extends JFrame implements ActionListener
{
	private DataInterface objetDistant;
	private JButton gAdmin, gSalle, gServeur, gServeurBySalle, gServeurByAdmin;
	private JLabel label;
	
	public Client(DataInterface objetServeur) 
	{
		this.objetDistant = objetServeur;
		this.label = new JLabel("==> Gestion du Datacenter :");
		label.setForeground(Color.white);
		Panel pan = new Panel("images/2.jpg");
		
		Font police = new Font("Tahoma", Font.BOLD, 30);
		Font po = new Font("Arial", Font.ITALIC, 20);
		
		this.gAdmin = new JButton("Gestion des admins");
		this.gSalle = new JButton("Gestion des salles");
		this.gServeur = new JButton("Gestion des serveurs");
		this.gServeurBySalle = new JButton("Liste des serveurs par salle");
		this.gServeurByAdmin = new JButton("Liste des serveurs par admin");
		
		label.setFont(police); 
		label.setLocation(30, 20);
		label.setSize(500, 40);
		gAdmin.setBounds(20, 80, 350, 50);
		gAdmin.setFont(po);
		gSalle.setFont(po); 
		gSalle.setBounds(60, 200, 350, 50);
		gServeur.setFont(po); 
		gServeur.setBounds(100, 320, 350, 50);
		gServeurByAdmin.setFont(po); 
		gServeurByAdmin.setBounds(140, 440, 350, 50);
		gServeurBySalle.setFont(po); 
		gServeurBySalle.setBounds(180, 560, 350, 50);
		
		gAdmin.addActionListener(this);
		gSalle.addActionListener(this);
		gServeur.addActionListener(this);
		gServeurByAdmin.addActionListener(this);
		gServeurBySalle.addActionListener(this);
		
		pan.add(label); pan.add(gAdmin); pan.add(gSalle); pan.add(gServeur);
		pan.add(gServeurBySalle); pan.add(gServeurByAdmin);
		
		this.setTitle("Gestion Datacenter");
		pan.setLayout(null);
		this.setContentPane(pan);
		this.setLocationRelativeTo(this);
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource() == this.gAdmin) 
		{
			GestionAdmin gestionAdmin = new GestionAdmin(this.objetDistant);
			gestionAdmin.setVisible(true);
		}
		
		if(event.getSource() == this.gSalle) 
		{
			GestionSalle gestionSalle = new GestionSalle(this.objetDistant);
			gestionSalle.setVisible(true);
		}
		
		if(event.getSource() == this.gServeur) 
		{
			GestionServeur gestionServeur = new GestionServeur(this.objetDistant);
			gestionServeur.setVisible(true);
		}
		
		if(event.getSource() == this.gServeurByAdmin) 
		{
			ListeServeurParAdmin listeServeurParAdmin = new ListeServeurParAdmin(this.objetDistant);
			listeServeurParAdmin.setVisible(true);
		}
		
		if(event.getSource() == this.gServeurBySalle) 
		{
			ListeServeurParSalle listeServeurParSalle = new ListeServeurParSalle(this.objetDistant);
			listeServeurParSalle.setVisible(true);
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		/*SplashScreen.getInstance("images/ibm-serveur-bis.png");
		
		try 
		{
			Thread.sleep(4000);
			SplashScreen.getInstance().end();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}*/
		
		try 
		{
			DataInterface objetServeur = (DataInterface) Naming.lookup("rmi://localhost:1099/DAO");
			new Client(objetServeur).setVisible(true);
		}
		catch(Exception ex) 
		{
			System.out.println("Impossible de se connecter !");
			System.out.println(ex.getMessage());
		}
	}

}

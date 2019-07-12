package presentation;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import domaine.*;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
@SuppressWarnings("serial")
public class ListeServeur extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Serveur> liste;
	private JScrollPane sc;
	private JPanel pan1, pan2;
	private JButton qt;
    private DataInterface objetDistant;
    
    public ListeServeur(ArrayList <Serveur> liste, DataInterface objetDistant)
    {
    	this.objetDistant = objetDistant;
    	this.pan1 = new JPanel();
    	this.pan2 = new JPanel();
		this.qt = new JButton("Quitter");
		this.liste = liste;
		this.sc  = new JScrollPane();
		this.table = new JTable();
		this.sc.setViewportView(table);
		DefaultTableModel modele = (DefaultTableModel)table.getModel();
		modele.addColumn("N° serveur");
		modele.addColumn("Nom serveur");
		modele.addColumn("Admin");
		modele.addColumn("Salle");
		
		int ligne = 0;
		
		  for (Serveur serveur : this.liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(serveur.getNumServ()), ligne, 0);
			  modele.setValueAt(serveur.getNomServ(), ligne, 1);
			  modele.setValueAt(serveur.getAdmin().getPrenom() + " " + serveur.getAdmin().getNom(), ligne, 2);
			  modele.setValueAt(serveur.getSalle().getNumSalle(), ligne, 3);
			  ligne++;
		  }
		 
		  setTitle("Client RMI : liste des salles");
		  setSize(550,500);
		  qt.addActionListener(this); 
		  pan1.add(sc);
		  pan2.add(qt);
		  add(pan1,BorderLayout.NORTH);
		  add(pan2,BorderLayout.SOUTH);
		  setLocationRelativeTo(null);
		  setVisible(true);
    	
    }

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource() == qt) 
		{
			this.dispose();
			new Client(this.objetDistant);
		}
	}

}

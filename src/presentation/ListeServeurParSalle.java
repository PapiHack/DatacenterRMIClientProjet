package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import domaine.DataInterface;
import domaine.Salle;
import domaine.Serveur;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
@SuppressWarnings("serial")
public class ListeServeurParSalle extends JFrame implements ActionListener, ItemListener
{
	@SuppressWarnings("unused")
	private JTable table;
	private JLabel lsalle;
	@SuppressWarnings("rawtypes")
	private JComboBox chsalle;
	private ArrayList<Salle> salles;
	private ArrayList<Serveur> serveurs;
	private JScrollPane sc;
	private JPanel pan, pan1, pan2;
	private JButton qt;
	@SuppressWarnings("unused")
	private Salle salle;
    private DataInterface objetDistant;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ListeServeurParSalle(DataInterface objetServeur) 
    {
    	this.objetDistant = objetServeur;
    	this.chsalle = new JComboBox();
    	this.lsalle = new JLabel("Selectionnez une salle : ");
    	this.pan = new JPanel();
    	this.pan1 = new JPanel();
    	this.pan2 = new JPanel();
		this.qt = new JButton("Quitter");
		this.sc  = new JScrollPane();
    	
    	try 
    	{
    		this.salles = this.objetDistant.findAllSalle();
    		this.salle = this.salles.get(0);
        	this.serveurs = this.objetDistant.findAllServeur();
    	}
    	catch(RemoteException re) 
    	{
    		System.out.println(re.getMessage());
    	}
    	
    	for(Salle salle : salles) 
    	{
    		this.chsalle.addItem(salle);
    	}

			this.table = this.initTable(serveurs);
		
			this.pan.setLayout(new GridLayout(1, 1));
			this.pan.add(this.lsalle); this.pan.add(this.chsalle);
			setTitle("Client RMI : liste des serveurs par salle");
			this.chsalle.addItemListener(this);
			setSize(550, 600);
			qt.addActionListener(this); 
			pan1.add(sc);
			pan2.add(qt);
			add(pan,BorderLayout.NORTH);
			add(pan1,BorderLayout.CENTER);
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
			//new Client(this.objetDistant);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
    	try 
    	{
    		this.salle = (Salle) this.chsalle.getSelectedItem();
    		this.serveurs = this.objetDistant.findAllServerBySalle((Salle) this.chsalle.getSelectedItem());
    	}
    	catch(RemoteException re) 
    	{
    		System.out.println(re.getMessage());
    	}
    	this.initTable(this.serveurs);
	}
	
	public JTable initTable(ArrayList<Serveur> serveurs) 
	{
		JTable table = new JTable();
		DefaultTableModel modele = (DefaultTableModel)table.getModel();
		modele.addColumn("N° serveur");
		modele.addColumn("Nom serveur");
		modele.addColumn("Admin");
		modele.addColumn("Salle");
    	
    	int ligne = 0;
		
		  for (Serveur serveur : this.serveurs)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(serveur.getNumServ()), ligne, 0);
			  modele.setValueAt(serveur.getNomServ(), ligne, 1);
			  modele.setValueAt(serveur.getAdmin(), ligne, 2);
			  modele.setValueAt(serveur.getSalle(), ligne, 3);
			  ligne++;
		  }
		  

		this.sc.setViewportView(table);
		
		return table;
	}

}

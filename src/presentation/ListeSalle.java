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
public class ListeSalle extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Salle> liste;
	private JScrollPane sc;
	private JPanel pan1, pan2;
	private JButton qt;
    private DataInterface objetDistant;
    
    public ListeSalle(ArrayList <Salle> liste, DataInterface objetDistant) 
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
		modele.addColumn("N° salle");
		modele.addColumn("Nom salle");
		
		int ligne = 0;
		
		  for (Salle salle : this.liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(salle.getNumSalle()), ligne, 0);
			  modele.setValueAt(salle.getNomSalle(), ligne, 1);
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
			new GestionSalle(this.objetDistant);
		}
	}

}

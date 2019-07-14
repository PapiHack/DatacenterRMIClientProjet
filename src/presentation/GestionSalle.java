package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.*;

import domaine.*;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
@SuppressWarnings("serial")
public class GestionSalle extends JFrame implements ActionListener
{
	private DataInterface objetDistant;
	private JLabel lnum, lnom;
	private JTextField chnum, chnom;
	private JButton aj, qt, aff, rec, sup, mod;
	private JPanel pan1, pan2, pan3;
	
	public GestionSalle(DataInterface objetServeur) 
	{
		 this.objetDistant = objetServeur;
		 this.lnum = new JLabel("N° salle : ");
		 this.lnom = new JLabel("Nom salle : ");
		 this.chnom =new JTextField();
		 this.chnum = new JTextField();
		 this.aj = new JButton("Enregistrer");
		 this.qt = new JButton("Quitter");
		 this.rec = new JButton("Rechercher");
		 this.mod = new JButton("Modifier");
		 this.sup = new JButton("Supprimer");
		 this.aff= new JButton("Afficher");
		 this.aj.addActionListener(this);
		 this.aff.addActionListener(this);
		 this.qt.addActionListener(this);
		 this.mod.addActionListener(this);
		 this.rec.addActionListener(this);
		 this.sup.addActionListener(this);
		 pan1=new JPanel();
		 pan2=new JPanel();
		 pan3=new JPanel();
		 pan1.setLayout(new GridLayout(1,2));
		 pan1.add(lnum);
		 pan1.add(chnum);
		 pan1.add(rec);
		 pan2.setLayout(new GridLayout(2,1));
		 pan2.add(lnom);
		 pan2.add(chnom);
		 pan3.add(aj);
		 pan3.add(aff);
		 pan3.add(qt);
		 pan3.add(sup);
		 pan3.add(mod);
			
		 add(pan1,BorderLayout.NORTH);
		 add(pan2,BorderLayout.CENTER);
		 add(pan3,BorderLayout.SOUTH);
		 setTitle("Client RMI Swing");
		 setSize(600,200);
	   	 setLocationRelativeTo(null);
	   	 sup.setEnabled(false);
	   	 mod.setEnabled(false);
	   	 setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource() == aj) 
		{
			Salle salle = new Salle(chnum.getText(), chnom.getText());
			try 
			{
				this.objetDistant.addSalle(salle);
				this.chnum.setText("");
				this.chnom.setText("");
			}
			catch(RemoteException re) 
			{
				System.out.println(re.getMessage());
			}
		}
		else if(event.getSource() == aff) 
		{
			try 
			{
				ArrayList<Salle> listeSalle = this.objetDistant.findAllSalle();
				dispose();
				new ListeSalle(listeSalle, this.objetDistant);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else if(event.getSource() == mod) 
		{
			this.chnum.setEnabled(false);
			Salle salle = new Salle(chnum.getText(), chnom.getText());
			try 
			{
				this.objetDistant.updateSalle(salle);;
				this.chnum.setText("");
				this.chnom.setText("");
				
			}
			catch(RemoteException re) 
			{
				System.out.println(re.getMessage());
			}
		}
		else if(event.getSource() == rec) 
		{
			String num = this.chnum.getText();
			Salle salle;
			try 
			{
				salle = this.objetDistant.findSalleById(num);
				if(salle == null)
					JOptionPane.showMessageDialog(null,"Salle inexistante !!!");
				else 
				{
					this.chnum.setText(salle.getNumSalle());
					this.chnum.setEnabled(false);
					this.chnom.setText(salle.getNomSalle());
   					this.mod.setEnabled(true);
   					this.sup.setEnabled(true);
				}
			}
			catch(RemoteException re) 
			{
				System.out.println(re.getMessage());
			}
		}
		else if(event.getSource() == sup) 
		{
			String num = this.chnum.getText();
			try 
			{
				this.objetDistant.removeSalle(num);;
				this.chnum.setText("");
				this.chnom.setText("");
				this.sup.setEnabled(false);
			}
			catch(RemoteException re) 
			{
				System.out.println(re.getMessage());
			}
		}
		else if(event.getSource() == qt) 
		{
			this.dispose();
			System.exit(0);
		}
	}

}

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
public class GestionAdmin extends JFrame implements ActionListener
{
	private DataInterface objetDistant;
	private JLabel lid, lnom, lprenom;
	private JTextField chid, chnom, chprenom;
	private JButton aj, qt, aff, rec, sup, mod;
	private JPanel pan1, pan2, pan3;
	 
	 public GestionAdmin(DataInterface objetServeur) 
	 {
		 this.objetDistant = objetServeur;
		 this.lid = new JLabel("Identifiant : ");
		 this.lnom = new JLabel("Nom : ");
		 this.lprenom = new JLabel("Prénom : ");
		 chid= new JTextField(40);
		 chnom=new JTextField();
		 chprenom = new JTextField();
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
		 pan1.setLayout(new GridLayout(1,3));
		 pan1.add(lid);
		 pan1.add(chid);
		 pan1.add(rec);
		 pan2.setLayout(new GridLayout(3,2));
		 pan2.add(lnom);
		 pan2.add(chnom);
		 pan2.add(lprenom);
		 pan2.add(chprenom);
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
			Admin admin = new Admin(chnom.getText(), chprenom.getText());
			try 
			{
				this.objetDistant.addAdmin(admin);
				this.chid.setText("");
				this.chnom.setText("");
				this.chprenom.setText("");
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
				ArrayList<Admin> listeAdmin = this.objetDistant.findAllAdmin();
				dispose();
				new ListeAdmin(listeAdmin, this.objetDistant);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else if(event.getSource() == mod) 
		{
			this.chid.setEnabled(false);
			Admin admin = new Admin(chnom.getText(), chprenom.getText());
			admin.setId(Integer.parseInt(this.chid.getText()));
			try 
			{
				this.objetDistant.updateAdmin(admin);
				this.chid.setText("");
				this.chnom.setText("");
				this.chprenom.setText("");
				
			}
			catch(RemoteException re) 
			{
				System.out.println(re.getMessage());
			}
		}
		else if(event.getSource() == rec) 
		{
			int id = Integer.parseInt(this.chid.getText());
			Admin admin;
			try 
			{
				admin = this.objetDistant.findAdminById(id);
				if(admin == null)
					JOptionPane.showMessageDialog(null,"Admin inexistant !!!");
				else 
				{
					this.chid.setText(String.valueOf(admin.getId()));
					this.chid.setEnabled(false);
					this.chnom.setText(admin.getNom());
					this.chprenom.setText(admin.getPrenom());
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
			int id = Integer.parseInt(this.chid.getText());
			try 
			{
				this.objetDistant.removeAdmin(id);
				this.chid.setText("");
				this.chnom.setText("");
				this.chprenom.setText("");
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

package domaine;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
public class Admin 
{
	private int id;
	private String nom;
	private String prenom;
	
	public Admin(String nom, String prenom) 
	{
		this.setNom(nom);
		this.setPrenom(prenom);
	}
	
	public int getId() 
	{
		return this.id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getNom() 
	{
		return this.nom;
	}
	
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public String getPrenom() 
	{
		return this.prenom;
	}
	
	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}
}

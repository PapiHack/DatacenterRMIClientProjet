package domaine;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 
 * @author PapiH4ck3R
 * @since 11/07/19
 * @version 0.0.1
 *  
 */
public interface DataInterface extends Remote
{
	/*------------------------- SERVEUR -------------------------- */
	
	public void addServeur(Serveur serveur)throws RemoteException;
	public ArrayList <Serveur> findAllServeur() throws RemoteException;
	public ArrayList <Serveur> findAllServerBySalle(Salle salle) throws RemoteException;
	public ArrayList <Serveur> findAllServerByAdmin(Admin admin) throws RemoteException;
	public void updateServeur(Serveur serveur) throws RemoteException;
	public Serveur findServeurById(String numServeur) throws RemoteException;
	public void removeServeur(String numServeur) throws RemoteException;
	
	/*------------------------- ADMIN -------------------------- */
	
	public void addAdmin(Admin admin)throws RemoteException;
	public ArrayList <Admin> findAllAdmin() throws RemoteException;
	public void updateAdmin(Admin admin) throws RemoteException;
	public Admin findAdminById(int id) throws RemoteException;
	public void removeAdmin(int id) throws RemoteException;
	
	/*------------------------- SALLE -------------------------- */
	
	public void addSalle(Salle salle)throws RemoteException;
	public ArrayList <Salle> findAllSalle() throws RemoteException;
	public void updateSalle(Salle salle) throws RemoteException;
	public Salle findSalleById(String numsalle) throws RemoteException;
	public void removeSalle(String numsalle) throws RemoteException;
}

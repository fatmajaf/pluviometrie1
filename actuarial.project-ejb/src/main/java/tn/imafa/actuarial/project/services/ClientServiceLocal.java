package tn.imafa.actuarial.project.services;

import java.util.List;

import javax.ejb.Local;

import tn.imafa.actuarial.project.persistence.Client;
/**
*
* @author Fatma Jaafar
*/
@Local
public interface ClientServiceLocal {
	public List<Client> findAllClients() ;
	public void ajouterClient(Client c);
	public List<Client> findclientslimittaille(int taille);
	void updateClient(Client c);
	public List<String> findcities();
	public Long getNbClients();
	List<Client> findAllClientstridate();
	
}

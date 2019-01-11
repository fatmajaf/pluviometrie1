package tn.imafa.actuarial.project.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.imafa.actuarial.project.persistence.Client;
/**
*
* @author Fatma Jaafar
*/
@Stateless
@LocalBean
public class ClientService implements ClientServiceLocal{
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<Client> findAllClients() {
		String jpql = "SELECT u FROM Client u";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public void ajouterClient(Client c) {
		System.out.println("hererrrr");
		System.out.println(c);
		em.persist(c.getContract().getPrimes().get(0));
		em.persist(c.getContract().getGaranti().get(0));
		em.persist(c.getContract());
		
		em.persist(c);

	}
	@Override
	public void updateClient(Client c) {
		em.merge(c);
	}

	public ClientService() {
		super();
	}

	@Override
	public List<Client> findclientslimittaille(int taille) {
		String jpql = "SELECT u FROM Client u";
		Query query = em.createQuery(jpql);
		System.out.println(query.getResultList());
		System.out.println("yess");
		List<Client> clients= new ArrayList();
		clients=query.getResultList();
		if(taille< clients.size())
			
		{clients=query.getResultList().subList(0, taille-1);}
		 
		return clients;
	}

	@Override
	public List<String> findcities() {
		String jpql = "select DISTINCT(c.ville) from Client c";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	@Override
	public Long getNbClients() {
		String jpql = "SELECT count(u) FROM Client u";
		Query query = em.createQuery(jpql);
		return (Long) query.getSingleResult();
	}

	@Override
	public List<Client> findAllClientstridate() {
		String jpql = "SELECT c FROM Client c order by c.datesouscription desc";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}


}

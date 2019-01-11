package tn.imafa.actuarial.project.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.imafa.actuarial.project.persistence.Contract;

@Stateless
@LocalBean
public class ContratService implements ContratServiceLocal{
	@PersistenceContext
	private EntityManager em;
	@Override
	public void addcontrat(Contract c) {
		em.persist(c);
		
	}


@Override
	public Long getNbsinistres() {
		String jpql = "SELECT sum(u.nbsinistres) FROM Contract u";
		Query query = em.createQuery(jpql);
		return (Long) query.getSingleResult();
	}


@Override
	public double getsumsinistres() {
		String jpql = "SELECT sum(u.montantgaranti) FROM Garanti u";
		Query query = em.createQuery(jpql);
		return (Double) query.getSingleResult();
	}



@Override
	public double getsumpremiumcuryear() {
		String jpql = "SELECT sum(u.tarif) FROM Prime u ";
		Query query = em.createQuery(jpql);
		return (Double) query.getSingleResult();
	}
@Override
public List<Object[]> statjsfprimes() {
	Query q = em.createNativeQuery("Select sum(a.tarif) as nb ,MONTH(a.date) as month FROM( SELECT '1' AS MONTH UNION SELECT '2' AS MONTH UNION SELECT '3' AS MONTH UNION SELECT '4' AS MONTH UNION SELECT '5' AS MONTH UNION SELECT '6' AS MONTH UNION SELECT '7' AS MONTH UNION SELECT '8' AS MONTH UNION SELECT '9' AS MONTH UNION SELECT '10' AS MONTH UNION SELECT '11' AS MONTH UNION SELECT '12' AS MONTH ) AS m LEFT JOIN  prime a on m.MONTH= MONTH(a.date) WHERE YEAR(a.date)=YEAR(CURRENT_DATE) GROUP BY m.MONTH");
	
	List<Object[]> res = q.getResultList();
	return res;
}
}

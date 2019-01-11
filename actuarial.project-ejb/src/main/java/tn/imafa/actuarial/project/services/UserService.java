package tn.imafa.actuarial.project.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.imafa.actuarial.project.persistence.Client;
import tn.imafa.actuarial.project.persistence.User;
/**
*
* @author Fatma Jaafar
*/
@Stateless
@LocalBean
public class UserService implements UserServiceLocal{
	@PersistenceContext
	private EntityManager em;
	

@Override
	public User login(User user) {
		Query query = em.createQuery("SELECT u from User u where u.login=:login and u.password=:password");
		query.setParameter("login", user.getLogin());
		query.setParameter("password", user.getPassword());

		List<User> res = query.getResultList();
		if ((res != null) && (res.isEmpty() == false)) {
			System.out.println(res.get(0));
			return res.get(0);
			
		}
		return null;
	}

}

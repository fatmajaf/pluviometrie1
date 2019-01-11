package tn.imafa.actuarial.project.services;

import java.util.List;

import javax.ejb.Local;

import tn.imafa.actuarial.project.persistence.Client;
import tn.imafa.actuarial.project.persistence.User;
/**
*
* @author Fatma Jaafar
*/
@Local
public interface UserServiceLocal {
	public User login(User user);
	
}

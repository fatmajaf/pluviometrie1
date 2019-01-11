package tn.imafa.actuarial.project.presentation.mbeans;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.imafa.actuarial.project.persistence.Taille;
/**
*
* @author Fatma Jaafar
*/
@ManagedBean
@ApplicationScoped
public class Data {
	public Taille[] getTaille(){
		return Taille.values();
	}

}

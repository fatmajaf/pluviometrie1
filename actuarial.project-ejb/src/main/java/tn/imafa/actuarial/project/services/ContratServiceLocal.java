package tn.imafa.actuarial.project.services;

import java.util.List;

import javax.ejb.Local;

import tn.imafa.actuarial.project.persistence.Contract;

@Local
public interface ContratServiceLocal{
public void addcontrat(Contract c);

Long getNbsinistres();

double getsumsinistres();

double getsumpremiumcuryear();

List<Object[]> statjsfprimes();


}

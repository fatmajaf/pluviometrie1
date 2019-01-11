package tn.imafa.actuarial.project.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Contract implements Serializable{

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
    private int nbsinistres;
    @OneToMany(mappedBy="contract",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Prime> primes;
    @OneToMany(mappedBy="contract",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Garanti> garanti;

	
	
	
	
	
	public int getNbsinistres() {
		return nbsinistres;
	}
	public void setNbsinistres(int nbsinistres) {
		this.nbsinistres = nbsinistres;
	}
	public List<Prime> getPrimes() {
		return primes;
	}
	public void setPrimes(List<Prime> primes) {
		this.primes = primes;
	}
	public List<Garanti> getGaranti() {
		return garanti;
	}
	public void setGaranti(List<Garanti> garanti) {
		this.garanti = garanti;
	}
	public Contract() {
	
	}
	public Contract(Integer id,  int nbsinistres) {
		super();
		this.id = id;
		
		this.nbsinistres = nbsinistres;
		
	}
	
	public Contract( int nbsinistres) {
		super();
		
		this.nbsinistres = nbsinistres;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getNbsinistre() {
		return nbsinistres;
	}
	public void setNbsinistre(int nbsinistres) {
		this.nbsinistres = nbsinistres;
	}
	@Override
	public String toString() {
		return "Contract [id=" + id + ", tarif=" + ", montantgaranti="  + ", nbsinistres="
				+ nbsinistres + "]";
	}
	
	
	
	
	
	
}

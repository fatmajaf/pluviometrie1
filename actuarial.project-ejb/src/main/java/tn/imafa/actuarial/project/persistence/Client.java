package tn.imafa.actuarial.project.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
*
* @author Fatma Jaafar
*/
@Entity
@DiscriminatorValue(value="client")
public class Client extends User implements Serializable {

	
	private double CAmax;
	private double cf_fixe;
	private float seuil;
	@Temporal(TemporalType.DATE)
	private Date datesouscription;
	private String ville;


	private double lat;
	private double lon;
	private String secteur;
	
	
	private int tel;
	private int codepostale;
	private String email;
	private int periodedecouverture;
	@OneToOne
	private Contract contract;
	
	
	
	public Client(double cAmax, double cf_fixe, float seuil, Date datesouscription, String ville,  String secteur, int tel, int codepostale, String email, int periodedecouverture,
			Contract contract) {
		super();
		CAmax = cAmax;
		this.cf_fixe = cf_fixe;
		this.seuil = seuil;
		this.datesouscription = datesouscription;
		this.ville = ville;
		
		this.secteur = secteur;
		this.tel = tel;
		this.codepostale = codepostale;
		this.email = email;
		this.periodedecouverture = periodedecouverture;
		this.contract = contract;
	}
	public Client(double cAmax, double cf_fixe, float seuil,  String ville, String secteur, int tel, String email, int periodedecouverture,
			Contract contract) {
		super();
		CAmax = cAmax;
		this.cf_fixe = cf_fixe;
		this.seuil = seuil;
		
		this.ville = ville;
		
		this.secteur = secteur;
		this.tel = tel;
		
		this.email = email;
		this.periodedecouverture = periodedecouverture;
		this.contract = contract;
	}

	public Client( double cAmax, double cf_fixe, float seuil, String ville, 
			double lat, double lon, String secteur, int tel, int codepostale, String email, int periodedecouverture,
			Contract contract) {
		super();
		
		CAmax = cAmax;
		this.cf_fixe = cf_fixe;
		this.seuil = seuil;
		this.ville = ville;
	
		this.lat = lat;
		this.lon = lon;
		this.secteur = secteur;
		this.tel = tel;
		this.codepostale = codepostale;
		this.email = email;
		this.periodedecouverture = periodedecouverture;
		this.contract = contract;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	private static final long serialVersionUID = 1L;

	public Client() {
		
	} 
	
	public Client(double cAmax, double cf_fixe, float seuil, String ville, String nom, String prenom,
			String secteur, int tel, String email, int periodedecouverture, Contract contract) {
		super(nom,prenom);
		CAmax = cAmax;
		this.cf_fixe = cf_fixe;
		this.seuil = seuil;
		this.ville = ville;

		this.lat = lat;
		this.lon = lon;
		this.secteur = secteur;
		this.tel = tel;
		this.codepostale = codepostale;
		this.email = email;
		this.periodedecouverture = periodedecouverture;
		this.contract = contract;
		
	}
	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	 
	public double getCAmax() {
		return this.CAmax;
	}

	
	public void setCAmax(double CAmax) {
		this.CAmax = CAmax;
	}   
	public double getCf_fixe() {
		return this.cf_fixe;
	}

	public void setCf_fixe(double cf_fixe) {
		this.cf_fixe = cf_fixe;
	}   
	public float getSeuil() {
		return this.seuil;
	}

	public void setSeuil(float seuil) {
		this.seuil = seuil;
	}   
	public Date getDatesouscription() {
		return this.datesouscription;
	}

	public void setDatesouscription(Date datesouscription) {
		this.datesouscription = datesouscription;
	}   
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}   
	
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}


	
	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getCodepostale() {
		return codepostale;
	}

	public void setCodepostale(int codepostale) {
		this.codepostale = codepostale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPeriodedecouverture() {
		return periodedecouverture;
	}

	public void setPeriodedecouverture(int periodedecouverture) {
		this.periodedecouverture = periodedecouverture;
	}

	@Override
	public String toString() {
		return "Client [id=" + super.getId()+ ", CAmax=" + CAmax + ", cf_fixe=" + cf_fixe + ", seuil=" + seuil
				+ ", datesouscription=" + datesouscription + ", ville=" + ville + ", nom=" + super.getNom() + ", prenom=" + super.getPrenom()
				+ ", lat=" + lat + ", lon=" + lon + ", secteur=" + secteur + ", tel=" + tel + ", codepostale="
				+ codepostale + ", email=" + email + ", periodedecouverture=" + periodedecouverture + ", contract="
				+ contract + "]";
	}

	
   
}

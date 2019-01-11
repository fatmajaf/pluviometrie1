package tn.imafa.actuarial.project.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prime implements Serializable{
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	private double tarif;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	private Contract contract;

	
	
	
	
	public Prime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Prime(Integer id, double tarif, Date date, Contract contract) {
		super();
		this.id = id;
		this.tarif = tarif;
		this.date = date;
		this.contract = contract;
	}

	public Prime(double tarif, Date date, Contract contract) {
		super();
		this.tarif = tarif;
		this.date = date;
		this.contract = contract;
	}

	@Override
	public String toString() {
		return "Prime [id=" + id + ", tarif=" + tarif + ", date=" + date + ", contract=" + contract + "]";
	}
	
	
}

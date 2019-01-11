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
public class Garanti implements Serializable{
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	private double montantgaranti;
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne
	private Contract contract;
	
	
	
	
	public Garanti() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Garanti(Integer id, double montantgaranti, Date date, Contract contract) {
		super();
		this.id = id;
		this.montantgaranti = montantgaranti;
		this.date = date;
		this.contract = contract;
	}
	public Garanti(double montantgaranti, Date date, Contract contract) {
		super();
		this.montantgaranti = montantgaranti;
		this.date = date;
		this.contract = contract;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getMontantgaranti() {
		return montantgaranti;
	}
	public void setMontantgaranti(double montantgaranti) {
		this.montantgaranti = montantgaranti;
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
	@Override
	public String toString() {
		return "Garanti [id=" + id + ", montantgaranti=" + montantgaranti + ", date=" + date + ", contract=" + contract
				+ "]";
	}
	
	
	
	
}

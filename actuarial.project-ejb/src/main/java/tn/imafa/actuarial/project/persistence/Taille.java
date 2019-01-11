package tn.imafa.actuarial.project.persistence;
/**
*
* @author Fatma Jaafar
*/
public enum Taille {
cent(100),
mille(1000),
cinquemille(5000),
dixmille(10000);
	private final int value;
	private Taille(int value){
		this.value=value;
	}
public int getValue() {
	return value;
}
	//cent,mille,cinqmille,dixmille
}

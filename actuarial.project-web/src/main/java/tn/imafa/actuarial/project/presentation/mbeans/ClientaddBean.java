package tn.imafa.actuarial.project.presentation.mbeans;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Encoder;
import tn.imafa.actuarial.project.persistence.Client;
import tn.imafa.actuarial.project.persistence.Contract;
import tn.imafa.actuarial.project.persistence.Garanti;
import tn.imafa.actuarial.project.persistence.Prime;
import tn.imafa.actuarial.project.persistence.Taille;
import tn.imafa.actuarial.project.services.ClientService;
import tn.imafa.actuarial.project.services.ContratService;
/**
*
* @author Fatma Jaafar
*/
@ManagedBean
@SessionScoped
public class ClientaddBean {
	private ArrayList countryList ;
	private ArrayList sectorList;
	private double CAmax;
	private double cf_fixe;
	private float seuil;
	private Date datesouscription;
	private String ville;

	private String nom;
	private String prenom;
	private double lat;
	private double lon;
	private String secteur;
	
	
	private int tel;
	private int codepostale;
	private String email;
	private int periodedecouverture;
	
	
	private double tarif;
	private double montantgaranti;
	private int nbsinistres;
	
	private String msgval;
	private List<Client>latestclients;
	
	
	private double moyp;
	private double[] monthCounter ;
	
	
	
	
	private String[]villes;
	private int[]nbjoursplu;

	@EJB
	ClientService service;
    
	@EJB
	ContratService servicecontrat;
	
	
	@PostConstruct
	public void init(){

		
        countryList = new ArrayList(); 

        
       // countryList=csvreader.getCities();
//System.out.println(countryList);
        countryList.add("Toulouse");
        countryList.add("Grenoble");
        countryList.add("Toulouse");
        countryList.add("Brest");
        countryList.add("Canne");
        countryList.add("Metz");
        countryList.add("Aix-en-Provence");
        countryList.add("Toulon");
        countryList.add("Nantes");
        countryList.add("Lyon");
        countryList.add("Paris");
        countryList.add("Nice");
        countryList.add("Marseille");
        countryList.add("Lille");
        countryList.add("Bordeaux");
        sectorList = new ArrayList(); 

        
        // countryList=csvreader.getCities();
//System.out.println(countryList);
         sectorList.add("Agriculture");
         sectorList.add("Restauration");
         sectorList.add("Tourisme");
         
         
         
         
         
        latestclients = new ArrayList<>();
        latestclients=service.findAllClientstridate().subList(0,3);
        
        moyp=servicecontrat.getsumpremiumcuryear();
        
        
        monthCounter= new double[12];
        List<Object[]> liste=servicecontrat.statjsfprimes();
		for (Object objects[] : liste) {
			
			monthCounter[(int) (Double.parseDouble(objects[1].toString())-1)]=
					Double.parseDouble(objects[0].toString());
			
			
		}
		
		List<Client>clients= new ArrayList<Client>();
		List<String>lvilles=new ArrayList<>();
		List<Integer> lnb= new ArrayList<>();
		clients=service.findAllClients();
		for (Client client : clients) {
			if(!lvilles.contains(client.getVille())){
				lvilles.add(client.getVille());
				lnb.add(csvreader.getCitiespluvio(client.getVille(), "nb"));
			}
		}
		villes=new String[lvilles.size()];
		nbjoursplu= new int[lnb.size()];
		for (int i = 0; i < villes.length; i++) {
			villes[i]=lvilles.get(i);
			nbjoursplu[i]=lnb.get(i);
			
		}
		
         
         
	}
	
	
	
	public double getMoyp() {
		return moyp;
	}



	public void setMoyp(double moyp) {
		this.moyp = moyp;
	}



	public List<Client> getLatestclients() {
		return latestclients;
	}



	public void setLatestclients(List<Client> latestclients) {
		this.latestclients = latestclients;
	}



	public void cancel(){
		tarif=0;
		
		
	}
	 public List countryList() {        
		
		       
		
		         return countryList; 
		 
		     }

	 public List sectorList() {        
			
       
        

         return sectorList; 
 
     }

	public void changesmanagement(){
		if(CAmax<cf_fixe){
			msgval="Turnover must be superior than the fixed price !";


		}
		else 
		{
			msgval="";
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:foo");
	}
	 private String encryptLdapPassword(String algorithm, String password) {
	        String sEncrypted = password;
	        if ((password != null) && (password.length() > 0)) {
	            boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
	            boolean bSHA = algorithm.equalsIgnoreCase("SHA")
	                    || algorithm.equalsIgnoreCase("SHA1")
	                    || algorithm.equalsIgnoreCase("SHA-1");
	            if (bSHA || bMD5) {
	                String sAlgorithm = "MD5";
	                if (bSHA) {
	                    sAlgorithm = "SHA";
	                }
	                try {
	                    MessageDigest md = MessageDigest.getInstance(sAlgorithm);
	                    md.update(password.getBytes("UTF-8"));
	                    sEncrypted = "{" + sAlgorithm + "}" + (new BASE64Encoder()).encode(md.digest());
	                } catch (Exception e) {
	                    sEncrypted = null;
	                   
	                }
	            }
	        }
	        return sEncrypted;
	    }
	
	
	
	public void ajouterClient(){
	montantgaranti=cf_fixe;
		Long n= servicecontrat.getNbsinistres();
		Long N= service.getNbClients();
		double sommesinistres=servicecontrat.getsumsinistres();
		double moy=(sommesinistres/n);
		
          double f=(double)n/N;
          double tariff=(double)f*moy;
		System.out.println("nombre n "+n);
		System.out.println("nombre N"+N);
		System.out.println("f "+f);
		System.out.println("moy"+moy);
		System.out.println("nombre somme sin "+sommesinistres);
		System.out.println("tarif "+moy);
		 Contract contract= new Contract(0);
		 List<Prime>primes=new ArrayList<Prime>();
		 primes.add(new Prime(moy, new Date(), contract));
		 List<Garanti>garanti=new ArrayList<Garanti>();
		 garanti.add(new Garanti(montantgaranti, new Date(), contract));
		 contract.setPrimes(primes);
		 contract.setGaranti(garanti);
		Client client= new Client(CAmax,cf_fixe,seuil,ville,nom,prenom,secteur,tel,email, periodedecouverture,contract);
		client.setLat(csvreader.getLatLonfromCity(client.getVille(), "lat"));		
		client.setLon(csvreader.getLatLonfromCity(client.getVille(), "lon"));
		client.setCodepostale((int)csvreader.getLatLonfromCity(client.getVille(), "codepostale"));
		datesouscription= new Date();
		client.setDatesouscription(datesouscription);
		String logi=client.getPrenom()+Passwordgenerator.generate();
		client.setLogin(logi);
		String pwd=Passwordgenerator.generate();
		client.setPassword(encryptLdapPassword("SHA",pwd));
		
	
		service.ajouterClient(client);


		
		
		  String emailadr= client.getEmail();
	        String recipient= emailadr;
	         Mail mail = new Mail();
	        mail.setMailAddressRecipient(recipient);
	        mail.setPwd("imafa18PRSM");
	        
	        mail.setMailAddressSender("imafa18prsm@gmail.com");
	        mail.setMailSubject("Welcome !!!");
	      
	        String msg="Dear:  "+client.getNom()+" "+client.getPrenom()+"\n Welcome to InsureIt  \n your login : "+client.getLogin()+ "\n your password : "+pwd;
	       
	        mail.setMailObject(msg);
	    

	        MailConstruction mc = new MailConstruction();
	        mc.getMailProperties();


	        mc.getMailMessage( mail);
	        mc.SendMessage();
	}
	
	
	
	
	
	
	

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public ClientService getService() {
		return service;
	}

	public void setService(ClientService service) {
		this.service = service;
	}

	public double getCAmax() {
		return CAmax;
	}

	public void setCAmax(double cAmax) {
		CAmax = cAmax;
	}

	public double getCf_fixe() {
		return cf_fixe;
	}

	public void setCf_fixe(double cf_fixe) {
		this.cf_fixe = cf_fixe;
	}

	public float getSeuil() {
		return seuil;
	}

	public void setSeuil(float seuil) {
		this.seuil = seuil;
	}

	/*public Date getDatesouscription() {
		return datesouscription;
	}

	public void setDatesouscription(Date datesouscription) {
		this.datesouscription = datesouscription;
	}
*/
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
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
	

	public ArrayList getCountryList() {
		return countryList;
	}



	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}



	public ArrayList getSectorList() {
		return sectorList;
	}



	public void setSectorList(ArrayList sectorList) {
		this.sectorList = sectorList;
	}



	public int getPeriodedecouverture() {
		return periodedecouverture;
	}

	public void setPeriodedecouverture(int periodedecouverture) {
		this.periodedecouverture = periodedecouverture;
	}

	public double getMontantgaranti() {
		return montantgaranti;
	}

	public void setMontantgaranti(double montantgaranti) {
		this.montantgaranti = montantgaranti;
	}

	public int getNbsinistres() {
		return nbsinistres;
	}

	public void setNbsinistres(int nbsinistres) {
		this.nbsinistres = nbsinistres;
	}


	public Date getDatesouscription() {
		return datesouscription;
	}


	public void setDatesouscription(Date datesouscription) {
		this.datesouscription = datesouscription;
	}


	public ContratService getServicecontrat() {
		return servicecontrat;
	}


	public void setServicecontrat(ContratService servicecontrat) {
		this.servicecontrat = servicecontrat;
	}

	public String getMsgval() {
		return msgval;
	}

	public void setMsgval(String msgval) {
		this.msgval = msgval;
	}



	public double[] getMonthCounter() {
		return monthCounter;
	}



	public void setMonthCounter(double[] monthCounter) {
		this.monthCounter = monthCounter;
	}



	public String[] getVilles() {
		return villes;
	}



	public void setVilles(String[] villes) {
		this.villes = villes;
	}



	public int[] getNbjoursplu() {
		return nbjoursplu;
	}



	public void setNbjoursplu(int[] nbjoursplu) {
		this.nbjoursplu = nbjoursplu;
	}
	


	
	
}

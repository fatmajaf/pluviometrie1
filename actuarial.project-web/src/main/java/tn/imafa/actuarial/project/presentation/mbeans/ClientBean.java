package tn.imafa.actuarial.project.presentation.mbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.imafa.actuarial.project.persistence.Client;
import tn.imafa.actuarial.project.persistence.Contract;
import tn.imafa.actuarial.project.persistence.Taille;
import tn.imafa.actuarial.project.services.ClientService;
import tn.imafa.actuarial.project.services.ContratService;
/**
*
* @author Fatma Jaafar
*/
@ManagedBean
@SessionScoped
public class ClientBean {

	private List<Client> clients;
	private double tarifcollectif=0;
	private Taille taille;
	private List<Double> lats;
	private List<Double>lons;
	private Client clientadded;
	private HashMap<String, Integer> villepretoday; 
	private List<Client>clientsraintoday;

	@EJB
	ClientService service;
	@EJB
	ContratService servicecontrat;
	
    
	@PostConstruct
	public void init() throws JSONException{
	
		taille=Taille.cent;
		clients= new ArrayList();
		clients= service.findAllClients();
		lats= new ArrayList<>();
		lons= new ArrayList<>();
		for (Client client : clients) {
			
			
			if(client.getLat()==0)
			{
				client.setLat(csvreader.getLatLonfromCity(client.getVille(), "lat"));
				
			}
			if(client.getLon()==0)
			{
				client.setLon(csvreader.getLatLonfromCity(client.getVille(), "lon"));
				service.updateClient(client);
				
			}
			
			
		}
		
Long n= servicecontrat.getNbsinistres();
	Long N= service.getNbClients();
	double sommesinistres=servicecontrat.getsumsinistres();
	double moy=(sommesinistres/n);
	
      double f=(double)n/N;
      tarifcollectif=(double)f*moy;
		double testduplat=0;
		double testduplon=0;
		for (int i = 0; i < clients.size(); i++) {
			if (testduplat!=clients.get(i).getLat())
			{lats.add(clients.get(i).getLat());
			testduplat=clients.get(i).getLat();
			}
			if (testduplon!=clients.get(i).getLon())
			lons.add(clients.get(i).getLon());
			testduplon=clients.get(i).getLon();
		}
		System.out.println("la liste des clients : ");
		System.out.println(clients);
		villepretoday=new HashMap<>();
		double precipitation = 0;
		int nombre=0;
		JSONObject obj = null;
		clientsraintoday=new ArrayList<>();
		for (Client client : clients) {
			obj=WeatherData.getData(client.getVille(), "forecast");
			/*if (obj.has("rain")==true){
				try {
					precipitation=Double.valueOf(obj.getJSONObject("rain").optString("3h", "0"));
					System.out.println(precipitation);
				} catch (JSONException e) {
				
					e.printStackTrace();
				}
				if (client.getSeuil()<precipitation){
					/*nombre=	(int) villepretoday.get(client.getVille());
					if (nombre==0)
					{villepretoday.put(client.getVille(), 1);}
					else 
					{
						villepretoday.put(client.getVille(), nombre+1);
					}*/
					/*clientsraintoday.add(client);*/
			
			System.out.println(obj.getJSONArray("list"));
			JSONArray array=obj.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				System.out.print(array.getJSONObject(i).isNull("rain"));
				if (array.getJSONObject(i).has("rain")==true){
					if(array.getJSONObject(i).getJSONObject("rain").has("3h")){
						System.out.print(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"));
					    System.out.println(array.getJSONObject(i).getString("dt_txt"));
					    
					    precipitation=Double.valueOf(array.getJSONObject(i).getJSONObject("rain").optString("3h", "0"));
					    if (client.getSeuil()<precipitation && !clientsraintoday.contains(client)){
					    	clientsraintoday.add(client);
					    }
					
					}
				    }
					
				}
				
			}
			
			
	
		
		System.out.println("map ville nb pre siiiiiiize" +clientsraintoday);
		
		
	}
	

	public HashMap getVillepretoday() {
		return villepretoday;
	}


	

	public void imprimerDevis(Client client) throws DocumentException, IOException {
		
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		//String strDate = dateFormat.format(client.getDatesouscription());	
			
			String m=Double.toString(client.getContract().getGaranti().get(client.getContract().getGaranti().size()-1).getMontantgaranti());
			String t=Double.toString(client.getContract().getPrimes().get(client.getContract().getPrimes().size()-1).getTarif());
			String ca=Double.toString(client.getCAmax());
			String cf=Double.toString(client.getCf_fixe());
			String cop=Integer.toString(client.getCodepostale());
			String telp=Integer.toString(client.getTel());
			String perc=Integer.toString(client.getPeriodedecouverture());
			String s=Float.toString(client.getSeuil());
			String secteur =client.getSecteur();
			String ville =client.getVille();
			String nom =client.getNom();
			String prenom =client.getPrenom();
			String email =client.getEmail();
			//Date dateso= service.getDateSouscription();
			//String ds=Double.toString(dateso);*/
			
			 FileOutputStream file = new FileOutputStream(new File("C:/Users/AGORA/Desktop/customer quote.pdf"));
			 
		    Document document = new Document(PageSize.A4);
		    PdfWriter.getInstance(document, file);

		    document.open();
		    Image meteo = Image.getInstance("C:/Users/AGORA/Desktop/unnamed.png");
		  	  //  meteo.setAlignment(Element.ALIGN_CENTER);

		          meteo.setIndentationLeft(170);

		          document.add(meteo);
		    // logo 
		    Paragraph test = new Paragraph("Climate risk insurance ", new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                Font.BOLD,new BaseColor(9, 33, 97)));
	       
	        test.setIndentationLeft(10);

	        document.add(test);
	        //
	        test = new Paragraph("Nice: Meteorological Insurance -France ", new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                Font.BOLD,new BaseColor(9, 33, 97)));
	        test.setIndentationLeft(10);

	        document.add(test);
	        test = new Paragraph("Number Agency Telephone : 0766869940", new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                Font.BOLD,new BaseColor(9, 33, 97)));
	        test.setIndentationLeft(10);
	        

	        document.add(test);
	        Image cachet = Image.getInstance("C:/Users/AGORA/Desktop/cachet.PNG");
		  	  //  meteo.setAlignment(Element.ALIGN_CENTER);

		          cachet.setIndentationLeft(20);

		          document.add(cachet);
	      
	       /*   
	        //
	     /*   Path currentRelativePath = Paths.get("");
	        String v = currentRelativePath.toAbsolutePath().toUri().toURL().toString();
	        v += "/actuarial.project-web/src/main/java/image/devis-assurance-ligne.jpg";
	        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(v);
	        
	        
	        document.add(logo);*/
	   
		  //  document.setMargins(0, 0, 0, 0);
		 
	        Paragraph title = new Paragraph("RISK REPORTING FORM CLIMATE RISK INSURANCE: CUSTOMER'S ESTIMATE ", new Font(Font.FontFamily.TIMES_ROMAN, 18,
	                Font.BOLD,new BaseColor(9, 33, 97)));

	        title.setAlignment(Element.ALIGN_CENTER);

	        title.setIndentationLeft(10);

	        document.add(title);
	        //
	        document.add(
	                new Paragraph(" "));
	     
	     
		    PdfPTable pdfTable = new PdfPTable(2);
	        
	        // nom
	        PdfPCell cell1 = new PdfPCell(new Phrase("Last Name "));
	        //cell1.setBorder(0);

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(nom));
	        pdfTable.addCell(cell1);
	        // marque
	        cell1 = new PdfPCell(new Phrase("First Name  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(prenom));

	        pdfTable.addCell(cell1);
	        // category
	       // cell1 = new PdfPCell(new Phrase("Subscription date   "));

	      //  pdfTable.addCell(cell1);
	       // cell1 = new PdfPCell(new Phrase(strDate));
	        
	        //pdfTable.addCell(cell1);
	        // price
	        cell1 = new PdfPCell(new Phrase("City   "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(ville));
	        pdfTable.addCell(cell1);

	        ///
	        cell1 = new PdfPCell(new Phrase("Postal code  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(cop));
	        pdfTable.addCell(cell1); 
	        ////
	        cell1 = new PdfPCell(new Phrase("Email "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase( email));
	        pdfTable.addCell(cell1); 
	        ////
	        cell1 = new PdfPCell(new Phrase("Phone Number  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(telp));
	        pdfTable.addCell(cell1); 
	        ///
	        cell1 = new PdfPCell(new Phrase("sector  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(secteur));
	        pdfTable.addCell(cell1); 
	        ///
	        cell1 = new PdfPCell(new Phrase("Coverage period (an)"));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(perc));
	        pdfTable.addCell(cell1); 
	        ///
	        cell1 = new PdfPCell(new Phrase("Maximum turnover (euro)  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(ca));
	        pdfTable.addCell(cell1); 
	        ///
	        cell1 = new PdfPCell(new Phrase("Fixed cost (euro)  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(cf));
	        pdfTable.addCell(cell1); 
	        ///
	        cell1 = new PdfPCell(new Phrase("Rainfall threshold (mm)   "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(s));
	        pdfTable.addCell(cell1); 
	        ///
	        cell1 = new PdfPCell(new Phrase("premium (euro)  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(t));
	        pdfTable.addCell(cell1); 
	        ///
	        
	        cell1 = new PdfPCell(new Phrase("Guaranteed amount (euro)  "));

	        pdfTable.addCell(cell1);
	        cell1 = new PdfPCell(new Phrase(m));
	        pdfTable.addCell(cell1); 
	        
	        document.add(pdfTable);
	        document.add(
	                new Paragraph(" "));
	        Image signature = Image.getInstance("C:/Users/AGORA/Desktop/signature.PNG");
		  	  //  meteo.setAlignment(Element.ALIGN_CENTER);

	        signature.setIndentationLeft(220);

		          document.add(signature);
		          
		    document.close();
		        


		}


	public void afficherpartaille(){
		
		System.out.println("change : "+taille.getValue());
		clients=service.findclientslimittaille(taille.getValue());
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("foo:cli");
	}


	public Taille getTaille() {
		return taille;
	}




	public void setTaille(Taille taille) {
		this.taille = taille;
	}




	public Client getClientadded() {
		return clientadded;
	}


	public void setClientadded(Client clientadded) {
		this.clientadded = clientadded;
	}


	public void setVillepretoday(HashMap<String, Integer> villepretoday) {
		this.villepretoday = villepretoday;
	}


	public double getTarifcollectif() {
		return tarifcollectif;
	}


	public void setTarifcollectif(double tarifcollectif) {
		this.tarifcollectif = tarifcollectif;
	}


	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public ClientService getService() {
		return service;
	}

	public void setService(ClientService service) {
		this.service = service;
	}


	public List<Double> getLats() {
		return lats;
	}


	public void setLats(List<Double> lats) {
		this.lats = lats;
	}


	public List<Double> getLons() {
		return lons;
	}


	public void setLons(List<Double> lons) {
		this.lons = lons;
	}


	public List<Client> getClientsraintoday() {
		return clientsraintoday;
	}


	public void setClientsraintoday(List<Client> clientsraintoday) {
		this.clientsraintoday = clientsraintoday;
	}


	public ContratService getServicecontrat() {
		return servicecontrat;
	}


	public void setServicecontrat(ContratService servicecontrat) {
		this.servicecontrat = servicecontrat;
	}



	
	
	
}

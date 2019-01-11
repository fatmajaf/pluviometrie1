package tn.imafa.actuarial.project.presentation.mbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import sun.misc.BASE64Encoder;
import tn.imafa.actuarial.project.persistence.Client;
import tn.imafa.actuarial.project.persistence.User;
import tn.imafa.actuarial.project.services.ContratService;
import tn.imafa.actuarial.project.services.UserService;


@ManagedBean
@SessionScoped
public class LoginBean {
	private String login;
	private String password;
	private User us;
	private boolean loggedIn;
	private Client clientco;

	@EJB
	UserService service;
	ContratService service1;
	
	
	private void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

	public String verifConnexion() throws NamingException {
		String  navigateTo = null;
		if (login.equals("") && password.equals(""))

			{addMessage("Info", "Please enter your login and password");
			return null;
			}
			
		else
			{us= new User();
			us.setPassword(encryptLdapPassword("SHA",password));
			us.setLogin(login);
			User user = null;
					user=service.login(us);
					System.out.println("the user : "+user);
			if (user == null ) 
				{addMessage("Info", "Login or password incorrect");
				return null;
				}
			else 
				{
				us=user;
				if (us.getClass()==Client.class){
					loggedIn=true;
					addMessage("Info", "welcome");
					clientco = (Client)us;
					System.out.println("jajajjajajajjajajajjajaja : "+clientco);
					navigateTo = "/pages/client/homeclient?faces-redirect=true";
					return navigateTo;
				}
				else  if (us instanceof User)
					{loggedIn=true;
					addMessage("Info", "welcome");
					System.out.println("isssss "+loggedIn);
					navigateTo = "/pages/admin/relevance?faces-redirect=true";
					return navigateTo;}
				
				}
			return null;
			}
	   
				
			

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
	
	public String doLogout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}
	
public void imprimerDevis1() throws DocumentException, IOException {
		
		/*Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(client.getDatesouscription());	
			
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
		
			 FileOutputStream file = new FileOutputStream(new File("C:/Users/AGORA/Desktop/customer estimate.pdf"));
			 
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
	        test = new Paragraph("Agency Phone Number  : 0766869940", new Font(Font.FontFamily.TIMES_ROMAN, 10,
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
		 
	        Paragraph title = new Paragraph("CUSTOMER'S ESTIMATE ", new Font(Font.FontFamily.TIMES_ROMAN, 18,
	                Font.BOLD,new BaseColor(9, 33, 97)));

	        title.setAlignment(Element.ALIGN_CENTER);

	        title.setIndentationLeft(10);

	        document.add(title);
	        //
	     //   String prenom =client.getPrenom();
	       document.add(
	                new Paragraph(" "));
	                Paragraph title1 =
	                new Paragraph("Last Name :     "+clientco.getPrenom(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                 Font.BOLD,new BaseColor(9, 33, 97)));
	      
	                title1.setIndentationLeft(10);

	                document.add( title1);
	                
	                Paragraph title2 =
	                new Paragraph("First Name :     "+clientco.getNom(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                
	                title2.setIndentationLeft(10);

	                document.add( title2);
	                
	                Paragraph title3 =
	                new Paragraph("Subscription date :     "+clientco.getDatesouscription(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title3.setIndentationLeft(10);

	                document.add( title3);
	                 
	                Paragraph title4 =
	                new Paragraph("City  :     "+clientco.getVille(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title4.setIndentationLeft(10);

	                document.add( title4);
	                
	                Paragraph title5 =
	                new Paragraph("Postal code :     "+clientco.getCodepostale(),new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title5.setIndentationLeft(10);

	                document.add( title5);      
	                
	                Paragraph title6 =
	                new Paragraph("Email :    "+clientco.getEmail(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title6.setIndentationLeft(10);

	                document.add( title6);       
	                
	                Paragraph title7 =
	                new Paragraph("Phone Number :   "+clientco.getTel(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title7.setIndentationLeft(10);

	                document.add( title7);
	                Paragraph title8 =
	                new Paragraph("Sector :   "+clientco.getSecteur(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title8.setIndentationLeft(10);

	                document.add( title8);
	                
	                Paragraph title9 =
	                new Paragraph("Coverage period(an)    :"+clientco.getPeriodedecouverture(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title9.setIndentationLeft(10);

	                document.add( title9);       
	                
	                Paragraph title10 =
	                        new Paragraph("Maximum turnover(EURO)    :"+clientco.getCAmax(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                                Font.BOLD,new BaseColor(9, 33, 97)));
	                title10.setIndentationLeft(10);

	                document.add( title10);
	                
	                Paragraph title11 =
	                new Paragraph("Fixed cost(EURO)    :"+clientco.getCf_fixe(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title11.setIndentationLeft(10);

	                document.add( title11);       
	                
	                Paragraph title12 =
	                new Paragraph("Rainfall threshold(mm)    :"+clientco.getSeuil(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title12.setIndentationLeft(10);

	                document.add( title12);
	                
	                Paragraph title13 =
	                new Paragraph("premium(EURO) :   "+clientco.getContract().getPrimes().get(clientco.getContract().getPrimes().size()-1).getTarif(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97)));
	                title13.setIndentationLeft(10);

	                document.add( title13);      
	                
	                Paragraph title14 =
	                new Paragraph("Guaranteed amount(EURO)   :"+clientco.getContract().getGaranti().get(clientco.getContract().getGaranti().size()-1).getMontantgaranti(), new Font(Font.FontFamily.TIMES_ROMAN, 10,
	                        Font.BOLD,new BaseColor(9, 33, 97))); 
	                title14.setIndentationLeft(10);

	                document.add( title14);  
	        document.add(
	                new Paragraph(" "));
	        Image signature = Image.getInstance("C:/Users/AGORA/Desktop/signature.PNG");
		  	  //  meteo.setAlignment(Element.ALIGN_CENTER);

	        signature.setIndentationLeft(220);

		          document.add(signature);
		         
		    document.close();
		        


		}



	public UserService getService() {
		return service;
	}
	public User getUs() {
		return us;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public void setUs(User us) {
		this.us = us;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public Client getClientco() {
		return clientco;
	}


	public void setClientco(Client clientco) {
		this.clientco = clientco;
	}


	public ContratService getService1() {
		return service1;
	}


	public void setService1(ContratService service1) {
		this.service1 = service1;
	}
	
	
	
}

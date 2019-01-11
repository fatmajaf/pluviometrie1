package tn.imafa.actuarial.project.presentation.mbeans;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
*
* @author Fatma Jaafar
*/
public class csvreader {
	
	
	
	public static double getLatLonfromCity(String city, String lonorlat){
		String csvFile = "D:/jee/actuarial.project/actuarial.project-web/src/main/java/tn/imafa/actuarial/project/presentation/mbeans/france.csv";
        String line = "";
        String[] linesplit= new String[15];
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           while ((line = br.readLine()) != null) {
              i++;
             }

        } catch (IOException e) {
            e.printStackTrace();
        }
        i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
          while ((line = br.readLine()) != null) {
             if (line.toLowerCase().contains(city.toLowerCase()))
            	{
            		linesplit=line.split(",");
            		System.out.println("line :   "+line);
            	
            		if(lonorlat.equals("lat")){
            			return Double.parseDouble(linesplit[11]);
            		}
            		else if (lonorlat.equals("lon")) {
            			return Double.parseDouble(linesplit[12]);
            		}
            		else {
            			return Double.parseDouble(linesplit[1]);
            		}
            	
            	}
            	i++;
             }

        } catch (IOException e) {
            e.printStackTrace();
        }
		return 0; 
}
	
	
	
	
	public static ArrayList<String> getCities(){
		String csvFile = "D:/jee/actuarial.project/actuarial.project-web/src/main/java/tn/imafa/actuarial/project/presentation/mbeans/france.csv";
        String line = "";
        String[] linesplit= new String[15];
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           while ((line = br.readLine()) != null) {
              i++;
             }

        } catch (IOException e) {
            e.printStackTrace();
        }
        i=0;
        ArrayList<String>cities= new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
          while ((line = br.readLine()) != null) {
             
        	  
            		linesplit=line.split(",");
            		
            	
            		if(!cities.contains(linesplit[3]))
            				{
            			cities.add(linesplit[3]);
            				}
            	
            		
            	i++;
             }

        } catch (IOException e) {
            e.printStackTrace();
        }
        cities.remove(0);
		return cities; 
}
	
	public static int getCitiespluvio(String city, String nbmoy){
		String csvFile = "D:/jee/actuarial.project/actuarial.project-web/src/main/java/tn/imafa/actuarial/project/presentation/mbeans/pluviometrie.csv";
        String line = "";
        String[] linesplit= new String[3];
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           while ((line = br.readLine()) != null) {
              i++;
             }

        } catch (IOException e) {
            e.printStackTrace();
        }
        i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
               if (line.toLowerCase().contains(city.toLowerCase()))
              	{
              		linesplit=line.split(",");
              		System.out.println("line :   "+line);
              	
              	if(nbmoy.equals("nb")){
              			return Integer.parseInt(linesplit[1]);
              		}
              		else if (nbmoy.equals("moy")) {
              			return Integer.parseInt(linesplit[2]);
              		}
              		
              		System.out.println("linesplit 1 "+linesplit[1]);
              	
              	}
              	i++;
               }

          } catch (IOException e) {
              e.printStackTrace();
          }
  		return 0; 
}
public static void main(String[] args) throws IOException {	
	System.out.println(csvreader.getLatLonfromCity("paris", "lat"));
	System.out.println(csvreader.getLatLonfromCity("paris", "lon"));
}
	        		
				}
	        	
	        	
	    	
	        

	   




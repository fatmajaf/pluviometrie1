package tn.imafa.actuarial.project.presentation.mbeans;
/**
*
* @author Fatma Jaafar
*/
import java.io.*;
import java.util.Random;
import java.lang.*;
import java.lang.String;
	
 public class Passwordgenerator
 
  {
   private static final Random rand = new Random();
   private static final   String Xsi ="abcdefghijklmnopqrstuvwxyz";
   private static final String Gamm ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
   private static final String Iot = "1234567890";
   //private static final String Zeta="&~#|`-_)('/?,;:.";
 
   private static String demo =""; 
   private static double i =0;
   public static String generate() {


	for (i=0;i<31415926L;i++){
	demo="";
        while ((demo.length() != 8)&& (demo.length() != 9)&& (demo.length() != 10)&& (demo.length() != 11)&& (demo.length() != 12)) {
           
              int rPick=rand.nextInt(4);
           if (rPick ==0) {
	      int erzat=rand.nextInt(25);
              demo+=Xsi.charAt(erzat);
         } else if (rPick == 1) {
	      int erzat=rand.nextInt(9);
	      demo+=Iot.charAt(erzat);
         } else if (rPick==2) {
              int erzat=rand.nextInt(25);
              demo+=Gamm.charAt(erzat);
         }else if (rPick==3) {
              int erzat=rand.nextInt(15);
              demo+=Gamm.charAt(erzat);
	}

}
	}
   return demo;
}}

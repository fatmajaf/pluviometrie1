package tn.imafa.actuarial.project.presentation.mbeans;

import java.util.Locale;



public class testc {

	public static void main(String[] args) {
		testc obj = new testc();
		obj.run();

	    }

	    public void run() {

		String[] locales = Locale.getISOCountries();

		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);

			System.out.println("Country Code = " + obj.getCountry()
				+ ", Country Name = " + obj.getDisplayCountry());

		}

		System.out.println("Done");
	    }


	}



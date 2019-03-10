package com.example.ibrahimshaltout.uc_gp_1.accountactivity;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CountriesListClass {
    public int countries;

    public static void main(String[] args) {

    }
    public ArrayList<String> xx ()
    {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }
        System.out.println( "# countries found: " + countries.size());
        return countries;
    }
}

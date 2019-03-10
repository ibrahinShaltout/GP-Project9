package com.example.ibrahimshaltout.uc_gp_1.accountactivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.ibrahimshaltout.uc_gp_1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class yourLocation extends AppCompatActivity {

    AutoCompleteTextView locationCountriesSpinner;
    ArrayAdapter<String> arrayAdapterCountries;
    private int spinnerItemSelcectedCountries;
    private Button btnCountinueLocation,btnSkipLocation;
    private FirebaseAuth auth;


    CountriesListClass countriesListClass = new CountriesListClass();
    List countries = countriesListClass.xx();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_location);

        auth = FirebaseAuth.getInstance();
        btnCountinueLocation = (Button) findViewById(R.id.countinue_location_button);
        btnSkipLocation = (Button) findViewById(R.id.skip_Location_button);
        arrayAdapterCountries = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);
        locationCountriesSpinner = (AutoCompleteTextView) findViewById(R.id.locationSpinnerCountry);
        locationCountriesSpinner.setAdapter(arrayAdapterCountries);
        locationCountriesSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemSelcectedCountries = position;
                String x = CountryIs();
                Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();
            }
        });

        btnCountinueLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String individualId=auth.getUid();
                String listSkillString = locationCountriesSpinner.getText().toString();
                final String locationList[] = listSkillString.split(",");
                List listLocation=new ArrayList<String>(Arrays.asList(locationList));
                FirebaseDatabase.getInstance().getReference("Users").child(individualId).child("locationList").setValue(listLocation);

                Intent intent = new Intent (yourLocation.this, IndividualInfoActivity1.class );
                startActivity(intent);
                finish();

            }
        });
        btnSkipLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (yourLocation.this, IndividualInfoActivity1.class );
                startActivity(intent);
                finish();

            }
        });

    }

    private String CountryIs() {
        String theLevel="null";
        for (int i=0;i<countries.size();i++)
        {
            if (spinnerItemSelcectedCountries == i) {
                theLevel= (String) countries.get(i);
            }
        }
        return(theLevel);
    }
}
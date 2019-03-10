package com.example.ibrahimshaltout.uc_gp_1.accountactivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.example.ibrahimshaltout.uc_gp_1.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class IndividualinfoActivity2 extends AppCompatActivity {
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individualinfo_2);
        EditText as12 =(EditText) findViewById(R.id.as12);
        final EditText dateEditText = findViewById(R.id.date);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        dateEditText.setText(sdf.format(myCalendar.getTime()));
                    }
                };
                DatePickerDialog dialog =  new DatePickerDialog(IndividualinfoActivity2.this, dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

    }


}
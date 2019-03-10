package com.example.ibrahimshaltout.uc_gp_1.accountactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibrahimshaltout.uc_gp_1.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import com.example.ibrahimshaltout.uc_gp_1.R;

public class SignupOrgActivity extends AppCompatActivity {

    private EditText inputEmailOrg, inputPasswordOrg, inputNameOrg, inputPhoneNumberOrg;
    private Button btnSignInOrg, btnSignUpOrg;
    private ProgressBar progressBarOrg;
    private FirebaseAuth authOrg;
    MaterialBetterSpinner materialDesignSpinner;
    ArrayAdapter<String> arrayAdapter;
    OrganizationDataClass organizationDataClass;

    //    Spinner sp ;
    private String[] SPINNERLIST = {"Corporate", "University", "Centre"};
    private int spinnerItemSelcected;
    String name;
    String email;
    String password;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_org);

        authOrg = FirebaseAuth.getInstance();
        btnSignInOrg = (Button) findViewById(R.id.sign_in_buttonOrg);
        btnSignUpOrg = (Button) findViewById(R.id.sign_up_buttonOrg);
        inputEmailOrg = (EditText) findViewById(R.id.emailOrg);
        inputNameOrg = (EditText) findViewById(R.id.nameOrg);
        inputPhoneNumberOrg = (EditText) findViewById(R.id.phonenumberOrg);
        inputPasswordOrg = (EditText) findViewById(R.id.passwordOrg);
        progressBarOrg = (ProgressBar) findViewById(R.id.progressBarOrg);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        materialDesignSpinner = (MaterialBetterSpinner) findViewById(R.id.sign_up_asOrg);
        materialDesignSpinner.setAdapter(arrayAdapter);

        btnSignInOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // save selected position
        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemSelcected = position;
            }
        });

        btnSignUpOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputNameOrg.getText().toString().trim();
                email = inputEmailOrg.getText().toString().trim();
                password = inputPasswordOrg.getText().toString().trim();
                phoneNumber = inputPhoneNumberOrg.getText().toString().trim();

                // Toast empty
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(getApplicationContext(), "Enter phoneNumber!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBarOrg.setVisibility(View.VISIBLE);

                //create user email & password
                authOrg.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupOrgActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupOrgActivity.this, "createUserWithEmail:onComplete:"
                                        + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBarOrg.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupOrgActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    signUpAs();
                                }
                            }
                        });
            }
        });

    }

    private void getInsertedObject(String type) {
        DatabaseReference mFireBase = FirebaseDatabase.getInstance().getReference(type)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mFireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                OrganizationDataClass user = dataSnapshot.getValue(OrganizationDataClass.class);
                user.setGender("female");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void startNextScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("KEY", FirebaseAuth.getInstance().getCurrentUser().getUid());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private void signUpAs() {
        if (spinnerItemSelcected == 0) {
            Toast.makeText(SignupOrgActivity.this, "Corporate", Toast.LENGTH_LONG).show();
            addToTable("Corporate");
        } else if (spinnerItemSelcected == 1) {
            Toast.makeText(SignupOrgActivity.this, "University", Toast.LENGTH_LONG).show();
            addToTable("University");
        } else if (spinnerItemSelcected == 2) {
            addToTable("Center");
        }
    }

    private void addToTable(String tableName) {
        organizationDataClass = new OrganizationDataClass(tableName, name, email, phoneNumber);
        DatabaseReference mFireBase = FirebaseDatabase.getInstance().getReference(tableName)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        // organizationDataClass.setGender("Male");
        mFireBase.setValue(organizationDataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupOrgActivity.this, "registration_success", Toast.LENGTH_LONG).show();
                    startNextScreen();
                } else {
                    Toast.makeText(SignupOrgActivity.this, "registration_fial", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBarOrg.setVisibility(View.GONE);
    }

}


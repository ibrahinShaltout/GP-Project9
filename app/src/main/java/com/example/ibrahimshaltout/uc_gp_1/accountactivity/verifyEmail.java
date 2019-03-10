package com.example.ibrahimshaltout.uc_gp_1.accountactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibrahimshaltout.uc_gp_1.MainActivity;
import com.example.ibrahimshaltout.uc_gp_1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class verifyEmail extends AppCompatActivity {

    private Button  btnResendEmail,btnSkipVerify;
    private FirebaseAuth auth;
    private EditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        IndividualDataClass individualDataClass =new IndividualDataClass();
        auth = FirebaseAuth.getInstance();
        btnResendEmail = (Button) findViewById(R.id.Resend_Email_button);
        btnSkipVerify = (Button) findViewById(R.id.Skip_Email_button);
        Email = (EditText) findViewById(R.id.emailverify);
        String a= auth.getCurrentUser().getEmail();
        Email.setText(a);
        Toast.makeText(verifyEmail.this,a,Toast.LENGTH_LONG).show();
        btnResendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationEmail();


            }
        });

        btnSkipVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(verifyEmail.this, yourLocation.class);
                startActivity(intent);
                finish();

            }
        });

    }
    public void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            Toast.makeText(verifyEmail.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(verifyEmail.this, yourLocation.class);
            startActivity(intent);
            finish();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            // FirebaseAuth.getInstance().signOut();
            Toast.makeText(verifyEmail.this, "Your Email is not verified", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(verifyEmail.this, yourLocation.class);
            startActivity(intent);
            finish();

            //restart this activity

        }
    }
    public void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent
                            // after email is sent just logout the user and finish this activity
//                            FirebaseAuth.getInstance().signOut();
//                                startActivity(new Intent(verifyEmail.this, LoginActivity.class));
                            finish();
                        } else {
                            // email not sent, so display message and restart the activity or do whatever you wish to do
                            //restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            Intent intent = new Intent(verifyEmail.this, yourLocation.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

}
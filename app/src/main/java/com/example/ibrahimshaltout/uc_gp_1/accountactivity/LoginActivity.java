package com.example.ibrahimshaltout.uc_gp_1.accountactivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibrahimshaltout.uc_gp_1.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.support.v7.widget.Toolbar;
import com.example.ibrahimshaltout.uc_gp_1.R;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
//HI
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private Toolbar toolbarorg;
    private TextInputLayout inputPasswordLayout;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this,IndividualInfoActivity1.class));
            finish();
        }

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        inputPasswordLayout = (TextInputLayout) findViewById(R.id.textinputlayoutPassword);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        myToolbar.setTitle("hii");
//        setSupportActionBar(myToolbar);



        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupAsActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
//inputPassword.setOnTouchListener(new View.OnTouchListener() {
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        if (!TextUtils.isEmpty(inputPassword.getText())) {
//            inputPasswordLayout.setPasswordVisibilityToggleEnabled(true);
//        }
//        return false;
//    }
//});


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError("You need to enter an Email");
                    return;
                }
//                else {
//                    inputEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.yes, 0);
//                }

                if (TextUtils.isEmpty(password)) {
                    inputPasswordLayout.setPasswordVisibilityToggleEnabled(false);
                    inputPassword.setError("You need to enter a Password");

                    return;
                }
                inputPasswordLayout.setPasswordVisibilityToggleEnabled(true);


                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    checkIfEmailVerified();

                                }
                            }
                        });
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
            Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
//            FirebaseAuth.getInstance().signOut();
            Toast.makeText(LoginActivity.this, "Your Email is not verified", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, verifyEmail.class);
            startActivity(intent);
            finish();
            //restart this activity

        }
    }
}

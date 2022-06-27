package com.example.habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    final String LogTag = "SIGN_UP";
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance("https://habittrackercoursework-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


        Button BackButton = findViewById(R.id.back_button_signup);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button SignUpButton = findViewById(R.id.signup_button_signup);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email_editable = (EditText) findViewById(R.id.email_field_signup);
                EditText password_editable = (EditText) findViewById(R.id.password_field_signup);
                String email = email_editable.getText().toString();
                String password = password_editable.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    sign_up(email, password);
                }
                else {
                    Log.e(LogTag, "LOGIN EMPTY FIELDS");
                }
            }
        });
    }
    public void add_user_to_db(User user) {
        dbRef.child("users").child(user.UID).setValue(user);

    }

    private void sign_up(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(LogTag, "REG TASK COMPLETED: " + email + " " + password);

                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    FirebaseUser fbUser = mAuth.getCurrentUser();
                    while (fbUser == null) {
                        fbUser = mAuth.getCurrentUser();
                    }

                    MyApplication my_app = MyApplication.get_instance();
                    my_app.set_user(new User(email, fbUser.getUid()));

                    add_user_to_db(my_app.get_user());

                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    startActivity(intent);

                }
                else {
                    Log.e(LogTag, "REG TASK FAILED: " + email + " " + password);
                    Log.e(LogTag, "REG TASK FAILED: " + task.getException().getMessage());
                }
            }
        });
    }

}

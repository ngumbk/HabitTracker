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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final String LogTag = "LOGIN";
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance("https://habittrackercoursework-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

        Button LoginButton = findViewById(R.id.login_button_auth);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email_editable = findViewById(R.id.email_field_auth);
                EditText password_editable = findViewById(R.id.password_field_auth);
                String email = email_editable.getText().toString();
                String password = password_editable.getText().toString();

                if (!email.equals("") && !password.equals("")) {
                    login(email, password);
                }
                else {
                    Log.e(LogTag, "LOGIN EMPTY FIELDS");
                }

            }
        });
        Button SignUpButton = findViewById(R.id.signup_button_auth);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        Button BackButton = findViewById(R.id.back_button_auth);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }



    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(LogTag, "LOGIN TASK COMPLETED: " + email + " " + password);
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    FirebaseUser fbUser = mAuth.getCurrentUser();
                    while (fbUser == null) {
                        fbUser = mAuth.getCurrentUser();
                    }

                    MyApplication my_app = MyApplication.get_instance();

                    User user = new User(email, fbUser.getUid());
                    dbRef.child("users").child(fbUser.getUid()).child("habits").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                Map<String, Object> user_data = (HashMap<String, Object>) task.getResult().getValue();
                                Log.d(LogTag, "DB DOWNLOAD SUCCESS: " + user_data);
                                for (int i = 0; i < user_data.size(); i++) {
                                    Map HabitAttributes = user_data.get(i).values();
                                    user.add_habit();
                                }
                            }
                            else {
                                Log.e(LogTag, "DB DOWNLOAD FAILED" + task.getException().getMessage());
                            }
                        }
                    });
                    my_app.set_user(user);


                    startActivity(intent);

                }
                else {
                    Log.e(LogTag, "LOGIN TASK FAILED: " + email + " " + password);
                    Log.e(LogTag, "LOGIN TASK FAILED: " + task.getException().getMessage());
                }
            }
        });

    }

}

package com.example.habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HabitActivity extends AppCompatActivity {
    DatabaseReference dbRef;
    String LogTag = "HABIT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        Intent intent = getIntent();

        dbRef = FirebaseDatabase.getInstance("https://habittrackercoursework-default-rtdb.europe-west1.firebasedatabase.app").getReference();


        TextView HabitName = findViewById(R.id.habit_name);
        HabitName.setText(intent.getStringExtra("HabitName"));

        TextView HabitDescription = findViewById(R.id.habit_description);
        HabitDescription.setText(intent.getStringExtra("HabitDescription"));

        Button AffirmativeButton = findViewById(R.id.try_button);
        AffirmativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication my_app = MyApplication.get_instance();
                User user = my_app.get_user();
                TextView HabitName_field = findViewById(R.id.habit_name);
                TextView HabitDescription_field = findViewById(R.id.habit_description);
                Spinner Periodicity_field = findViewById(R.id.spinner_periodicity);
                EditText Notification_field = findViewById(R.id.edittext_notification_time);
                Spinner Tag_field = findViewById(R.id.spinner_tag);
                user.add_habit(HabitName_field.getText().toString(), HabitDescription_field.getText().toString(),
                        Periodicity_field.getSelectedItem().toString(), Notification_field.getText().toString(),
                        Tag_field.getSelectedItem().toString());
                Log.d(LogTag, "HABIT ADD: " + HabitName_field.getText().toString());

                update_db(user);

                Intent intent1 = new Intent(HabitActivity.this, HabitsListActivity.class);
                startActivity(intent1);
            }
        });
        Button NegativeButton = findViewById(R.id.dismiss_button);
        NegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HabitActivity.this, HabitsListActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void update_db(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("/users/" + user.UID, user.user_to_map());
        dbRef.updateChildren(map);

    }

}

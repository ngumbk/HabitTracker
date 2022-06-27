package com.example.habittracker;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class HabitsListActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_list);

        //setting listeners for all buttons
        Button CustomHabitButton = findViewById(R.id.habit_button_custom);
        CustomHabitButton.setOnClickListener(this);
        Button JoggingButton = findViewById(R.id.habit_button_jogging);
        JoggingButton.setOnClickListener(this);
        Button WaterButton = findViewById(R.id.habit_button_water);
        WaterButton.setOnClickListener(this);
        Button WarmupButton = findViewById(R.id.habit_button_warmup);
        WarmupButton.setOnClickListener(this);
        Button ParentsButton = findViewById(R.id.habit_button_call);
        ParentsButton.setOnClickListener(this);
        Button SmokingButton = findViewById(R.id.habit_button_smoking);
        SmokingButton.setOnClickListener(this);

        //Listener for back button
        Button BackButton = findViewById(R.id.back_button_habits);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HabitsListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HabitsListActivity.this, HabitActivity.class);;
        switch (view.getId()) {
            case R.id.habit_button_custom:
                intent.putExtra("HabitName", "User Habit");
                break;
            case R.id.habit_button_jogging:
                intent.putExtra("HabitName", "Jogging");
                intent.putExtra("HabitDescription", "You simply should spend some time jogging in the morning (or even evening)");
                break;
            case R.id.habit_button_water:
                intent.putExtra("HabitName", "GlassOfWater");
                intent.putExtra("HabitDescription", "Drink a glass of water every (preferred) 3 hours, which makes your body hydrated");
                break;
            case R.id.habit_button_warmup:
                intent.putExtra("HabitName", "Warmup");
                intent.putExtra("HabitDescription", "Morning warmup could give you a charge of power in the morning," +
                        " so you'll have your first hours in the day more productive");
                break;
            case R.id.habit_button_call:
                intent.putExtra("HabitName", "CallParents");
                intent.putExtra("HabitDescription", "Call your elder parents, they'll be glad to hear from you");
                break;
            case R.id.habit_button_smoking:
                intent.putExtra("HabitName", "GiveUpSmoking");
                intent.putExtra("HabitDescription", "Giving up smoking might be tricky, so it's better to track it more frequently");
                break;
            default:
                break;
        }
        startActivity(intent);
    }

}


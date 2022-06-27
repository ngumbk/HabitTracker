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

        MyApplication my_app = MyApplication.get_instance();
        User user = my_app.get_user();

        String chosen_habit = null;
        Intent intent = new Intent(HabitsListActivity.this, HabitActivity.class);
        switch (view.getId()) {
            case R.id.habit_button_custom:
                chosen_habit = "User Habit";
                break;
            case R.id.habit_button_jogging:
                chosen_habit = "Jogging";
                intent.putExtra("HabitDescription", "You simply should spend some time jogging in the morning (or even evening).");
                break;
            case R.id.habit_button_water:
                chosen_habit = "Glass Of Water";
                intent.putExtra("HabitDescription", "Drink a glass of water every (preferred) 3 hours, which makes your body hydrated.");
                break;
            case R.id.habit_button_warmup:
                chosen_habit = "Warmup";
                intent.putExtra("HabitDescription", "Morning warmup could give you a charge of power in the morning," +
                        " so you'll have your first hours in the day more productive.");
                break;
            case R.id.habit_button_call:
                chosen_habit = "Call Parents";
                intent.putExtra("HabitDescription", "Call your elder parents, they'll be glad to hear from you.");
                break;
            case R.id.habit_button_smoking:
                chosen_habit = "Give Up Smoking";
                intent.putExtra("HabitDescription", "Giving up smoking might be tricky, so it's better to track it more frequently.");
                break;
            default:
                break;
        }
        intent.putExtra("HabitName", chosen_habit);
        if(user.is_habit_tracked(chosen_habit)) {
            intent.putExtra("HabitTracked", true);
        }
        else {intent.putExtra("HabitTracked", false);}
        startActivity(intent);
    }

}


package com.example.habittracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private class Habit {
        public String HabitName, HabitDescription, Periodicity, NotificationTime, Tag;
        Habit(String HabitName, String HabitDescription, String Periodicity, String NotificationTime, String Tag) {
            this.HabitName = HabitName;
            this.HabitDescription = HabitDescription;
            this.Periodicity = Periodicity;
            this.NotificationTime = NotificationTime;
            this.Tag = Tag;
        }
        Habit() {
            this.HabitName = null;
            this.HabitDescription = null;
            this.Periodicity = null;
            this.NotificationTime = null;
            this.Tag = null;
        }
    }
    public void add_habit(String HabitName, String HabitDescription, String Periodicity, String NotificationTime, String Tag) {
        UserHabits.add(new Habit(HabitName, HabitDescription, Periodicity, NotificationTime, Tag));
    }
    public Map<String, Object> user_to_map() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("UID", UID);
        result.put("email", email);

        HashMap<String, Object> temp = new HashMap<>();
        for(int i = 0; i < UserHabits.size(); i++) {
            HashMap<String, Object> habits = new HashMap<>();

            habits.put("HabitDescription", UserHabits.get(i).HabitDescription);
            habits.put("Periodicity", UserHabits.get(i).Periodicity);
            habits.put("NotificationTime", UserHabits.get(i).NotificationTime);
            habits.put("Tag", UserHabits.get(i).Tag);

            temp.put(UserHabits.get(i).HabitName, habits);
        }
        result.put("habits", temp);

        return result;
    }

    public String email, UID;
    public ArrayList<Habit> UserHabits;
    User(String email, String UID) {
        this.email = email;
        this.UID = UID;
        UserHabits = new ArrayList<>();
    }
    User(){
        this.email = null;
        this.UID = null;
        UserHabits = new ArrayList<>();
        UserHabits.add(new Habit());
        UserHabits.add(new Habit());
    }

}

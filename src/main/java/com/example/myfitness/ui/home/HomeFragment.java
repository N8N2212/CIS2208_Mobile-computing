package com.example.myfitness.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import com.example.myfitness.R;
import com.example.myfitness.database.db_helper;
import com.example.myfitness.exercise;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView welcomeMessage;
    private TextView fitnessStats;
    private TextView motivationalQuote;
    private RecyclerView workoutRecommendations;
    private db_helper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI elements
        welcomeMessage = view.findViewById(R.id.welcome_message);
        fitnessStats = view.findViewById(R.id.fitness_stats);
        motivationalQuote = view.findViewById(R.id.motivational_quote);
        workoutRecommendations = view.findViewById(R.id.workout_recommendations);

        // Set up RecyclerView
        workoutRecommendations.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize database helper
        dbHelper = new db_helper(getContext());

        // Populate database if empty
        populateDatabaseIfEmpty();

        // Fetch random workouts and populate UI
        populateUI();

        return view;
    }

    private void populateUI() {
        welcomeMessage.setText("Welcome!");
        fitnessStats.setText("caloric surplus - ~+500 cal\ncaloric deficit - ~-500 cal");
        motivationalQuote.setText("Stay fit, stay healthy!");

        List<exercise> displayedExercises = new ArrayList<>();
        displayedExercises.addAll(dbHelper.getRandomExercisesByCategory("Push", 2));
        displayedExercises.addAll(dbHelper.getRandomExercisesByCategory("Pull", 2));
        displayedExercises.addAll(dbHelper.getRandomExercisesByCategory("Legs", 2));

        Collections.shuffle(displayedExercises);  // Optional: Shuffle to mix the categories
        workoutRecommendations.setAdapter(new workout_recommendations_adapter(displayedExercises));
    }


    private void populateDatabaseIfEmpty() {
        List<exercise> exercises = dbHelper.getExercises();
        if (exercises.isEmpty()) {
            List<exercise> predefinedExercises = exercise_data.getPredefinedExercises();
            for (exercise exercise : predefinedExercises) {
                dbHelper.insertExercise(exercise);
            }
        }
    }
}

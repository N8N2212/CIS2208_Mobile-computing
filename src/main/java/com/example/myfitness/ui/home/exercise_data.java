package com.example.myfitness.ui.home;

import com.example.myfitness.exercise;
import java.util.ArrayList;
import java.util.List;

public class exercise_data {

    public static List<exercise> getPredefinedExercises() {
        List<exercise> exercises = new ArrayList<>();

        // Push Day Exercises
        exercises.add(new exercise(0, "Bench Press", "Push", "Targets chest, shoulders, and triceps."));
        exercises.add(new exercise(1, "Overhead Press", "Push", "Primarily works the shoulders and triceps."));
        exercises.add(new exercise(2, "Incline Dumbbell Press", "Push", "Focuses on the upper chest and shoulders."));
        exercises.add(new exercise(3, "Push-Ups", "Push", "Works the chest, shoulders, and triceps."));
        exercises.add(new exercise(4, "Dips", "Push", "Excellent for triceps and lower chest."));
        exercises.add(new exercise(5, "Dumbbell Shoulder Press", "Push", "Isolates shoulders and upper arms."));
        exercises.add(new exercise(6, "Tricep Extensions", "Push", "Targets the tricep muscles."));
        exercises.add(new exercise(7, "Pec Deck Machine", "Push", "Isolates the chest muscles for hypertrophy."));
        exercises.add(new exercise(8, "Lateral Raises", "Push", "Works the side delts for broader shoulders."));
        exercises.add(new exercise(9, "Cable Crossovers", "Push", "Fine-tunes chest definition and works the inner chest."));

        // Pull Day Exercises
        exercises.add(new exercise(10,"Pull-Ups", "Pull", "Great for overall back development and biceps."));
        exercises.add(new exercise(11, "Barbell Rows", "Pull", "Targets the entire back and enhances thickness."));
        exercises.add(new exercise(12, "Deadlifts", "Pull", "Engages the whole back, hamstrings, and glutes."));
        exercises.add(new exercise(13, "Lat Pull-Downs", "Pull", "Focuses on the latissimus dorsi muscles."));
        exercises.add(new exercise(14, "Seated Cable Rows", "Pull", "Strengthens the mid-back and rhomboids."));
        exercises.add(new exercise(15, "Face Pulls", "Pull", "Excellent for rear deltoids and upper back."));
        exercises.add(new exercise(16, "Hammer Curls", "Pull", "Targets the biceps and brachialis."));
        exercises.add(new exercise(17, "EZ Bar Curls", "Pull", "Classic bicep-building exercise."));
        exercises.add(new exercise(18, "Single Arm Dumbbell Rows", "Pull", "Isolates each side of the back."));
        exercises.add(new exercise(19, "T-Bar Rows", "Pull", "Hits multiple back muscles and aids in width and thickness."));

        // Legs Day Exercises
        exercises.add(new exercise(20, "Squats", "Legs", "Fundamental for overall leg development."));
        exercises.add(new exercise(21, "Deadlifts", "Legs", "Works the legs, lower back, and core."));
        exercises.add(new exercise(22, "Leg Press", "Legs", "Focuses on quads, glutes, and hamstrings."));
        exercises.add(new exercise(23, "Lunges", "Legs", "Great for leg development and stability."));
        exercises.add(new exercise(24, "Leg Curls", "Legs", "Isolates the hamstrings."));
        exercises.add(new exercise(25, "Leg Extensions", "Legs", "Targets the quadriceps at the front of the thigh."));
        exercises.add(new exercise(26, "Calf Raises", "Legs", "Essential for developing the calf muscles."));
        exercises.add(new exercise(27, "Romanian Deadlifts", "Legs", "Emphasizes the hamstrings and glutes."));
        exercises.add(new exercise(28, "Step-Ups", "Legs", "Engages quads and glutes and enhances balance."));
        exercises.add(new exercise(29, "Hack Squats", "Legs", "Provides a different angle to target the quads and glutes."));

        return exercises;
    }
}

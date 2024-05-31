package com.example.myfitness.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.myfitness.R;
import com.example.myfitness.database.db_helper;
import com.example.myfitness.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DashboardFragment extends Fragment implements preset_adapter.OnDeletePresetListener {

    private RecyclerView presetsRecyclerView;
    private db_helper dbHelper;
    private Button addPresetButton;
    private Button savePresetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dbHelper = new db_helper(view.getContext());
        presetsRecyclerView = view.findViewById(R.id.presetsRecyclerView);
        presetsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        addPresetButton = view.findViewById(R.id.addPresetButton);
        savePresetButton = view.findViewById(R.id.savePresetButton);

        addPresetButton.setOnClickListener(v -> showWorkoutTypeChooser());
        savePresetButton.setOnClickListener(v -> savePreset());

        updatePresetsDisplay();
        return view;
    }

    private void showWorkoutTypeChooser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose a Workout Type");
        String[] types = {"Push", "Pull", "Legs"};
        builder.setItems(types, (dialog, which) -> {
            presetsRecyclerView.setAdapter(null);  // Clear previous adapter
            loadExercisesForType(types[which]);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void loadExercisesForType(String type) {
        List<exercise> exercises = dbHelper.getRandomExercisesByCategory(type, 10);
        updateExerciseList(exercises);
    }

    private void updateExerciseList(List<exercise> exercises) {
        exercise_adapter adapter = new exercise_adapter(exercises);
        presetsRecyclerView.setAdapter(adapter);
    }

    private void updatePresetsDisplay() {
        List<String> presets = dbHelper.getWorkoutPresets();
        preset_adapter adapter = new preset_adapter(presets, this);
        presetsRecyclerView.setAdapter(adapter);
    }

    private void savePreset() {
        Set<Long> selectedIds = ((exercise_adapter)presetsRecyclerView.getAdapter()).getSelectedExerciseIds();
        if (selectedIds.size() > 0 && dbHelper.getWorkoutPresets().size() < 3) {
            dbHelper.insertWorkoutPreset("Custom Preset " + (dbHelper.getWorkoutPresets().size() + 1), new ArrayList<>(selectedIds));
            Toast.makeText(getContext(), "Preset saved successfully", Toast.LENGTH_SHORT).show();
            updatePresetsDisplay();  // Refresh the preset display
        } else if (selectedIds.size() == 0) {
            Toast.makeText(getContext(), "No exercises selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Cannot add more than 3 presets", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeletePreset(int position) {
        dbHelper.deleteWorkoutPreset(position); // Assuming you have a method to delete presets by position or ID
        updatePresetsDisplay();
        Toast.makeText(getContext(), "Preset deleted", Toast.LENGTH_SHORT).show();
    }
}

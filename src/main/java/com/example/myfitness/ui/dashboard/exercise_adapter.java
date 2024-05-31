package com.example.myfitness.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.myfitness.exercise;
import com.example.myfitness.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class exercise_adapter extends RecyclerView.Adapter<exercise_adapter.ExerciseViewHolder> {
    private List<exercise> exercises;
    private Set<Long> selectedExerciseIds = new HashSet<>();

    public exercise_adapter(List<exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        exercise ex = exercises.get(position);
        holder.nameTextView.setText(ex.getName());
        holder.descriptionTextView.setText(ex.getDescription());

        // Checkbox selection handling
        holder.selectionCheckBox.setOnCheckedChangeListener(null); // to avoid triggering listener during setup
        holder.selectionCheckBox.setChecked(selectedExerciseIds.contains(ex.getId()));
        holder.selectionCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedExerciseIds.add(ex.getId());
            } else {
                selectedExerciseIds.remove(ex.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public Set<Long> getSelectedExerciseIds() {
        return selectedExerciseIds;
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        CheckBox selectionCheckBox;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.exercise_name);
            descriptionTextView = itemView.findViewById(R.id.exercise_description);
            selectionCheckBox = itemView.findViewById(R.id.exerciseCheckbox);
        }
    }
}

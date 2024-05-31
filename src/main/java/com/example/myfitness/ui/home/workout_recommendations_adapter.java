package com.example.myfitness.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfitness.R;
import com.example.myfitness.exercise;
import java.util.List;

public class workout_recommendations_adapter extends RecyclerView.Adapter<workout_recommendations_adapter.ViewHolder> {

    private final List<exercise> mWorkouts;

    public workout_recommendations_adapter(List<exercise> workouts) {
        mWorkouts = workouts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        exercise workout = mWorkouts.get(position);
        holder.workoutName.setText(workout.getName());
        holder.workoutCategory.setText(workout.getCategory());
    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView workoutName;
        TextView workoutCategory;

        ViewHolder(View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workout_name);
            workoutCategory = itemView.findViewById(R.id.workout_category);
        }
    }
}

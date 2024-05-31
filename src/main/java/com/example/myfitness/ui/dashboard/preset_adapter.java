package com.example.myfitness.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.myfitness.R;

public class preset_adapter extends RecyclerView.Adapter<preset_adapter.PresetViewHolder> {
    private List<String> presetList;
    private final OnDeletePresetListener deleteListener;

    public preset_adapter(List<String> presetList, OnDeletePresetListener listener) {
        this.presetList = presetList;
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public PresetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preset, parent, false);
        return new PresetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresetViewHolder holder, int position) {
        String preset = presetList.get(position);
        holder.presetNameTextView.setText(preset);
        holder.deletePresetIcon.setOnClickListener(v -> {
            deleteListener.onDeletePreset(position);
        });
    }

    @Override
    public int getItemCount() {
        return presetList.size();
    }

    static class PresetViewHolder extends RecyclerView.ViewHolder {
        TextView presetNameTextView;
        ImageView deletePresetIcon;

        public PresetViewHolder(@NonNull View itemView) {
            super(itemView);
            presetNameTextView = itemView.findViewById(R.id.presetNameTextView);
            deletePresetIcon = itemView.findViewById(R.id.delete_icon);
        }
    }

    public interface OnDeletePresetListener {
        void onDeletePreset(int position);
    }
}

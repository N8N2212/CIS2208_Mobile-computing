package com.example.myfitness.ui.bmi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfitness.R;

public class BmiFragment extends Fragment {

    private EditText heightInput;
    private EditText weightInput;
    private Button calculateBmiButton;
    private TextView bmiResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        heightInput = view.findViewById(R.id.height_input);
        weightInput = view.findViewById(R.id.weight_input);
        calculateBmiButton = view.findViewById(R.id.calculate_bmi_button);
        bmiResult = view.findViewById(R.id.bmi_result);

        calculateBmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });

        return view;
    }

    private void calculateBmi() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();

        if (TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
            bmiResult.setText("Please enter valid height and weight.");
            bmiResult.setVisibility(View.VISIBLE);
            return;
        }

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);
        double bmi = weight / (height * height);
        displayBmiResult(bmi);
    }

    private void displayBmiResult(double bmi) {
        String bmiCategory;

        if (bmi <= 18.5) {
            bmiCategory = "Underweight. You may need to gain weight.";
        } else if (bmi <= 24.99) {
            bmiCategory = "Normal weight. Keep maintaining your current lifestyle.";
        } else if (bmi <= 29.99) {
            bmiCategory = "Overweight. Consider a healthier diet and more exercise.";
        } else {
            bmiCategory = "Obese. Consult with a healthcare provider for advice.";
        }

        String resultText = String.format("Your BMI: %.2f\n%s", bmi, bmiCategory);
        bmiResult.setText(resultText);
        bmiResult.setVisibility(View.VISIBLE);
    }
}

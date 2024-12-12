package com.northcoders.calculatorapp;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String calcString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("on creat");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        TextView displayText = findViewById(R.id.display);


        button1.setOnClickListener(v ->  {calcString = calcString + "1"; displayText.setText(calcString); });
        button2.setOnClickListener(v -> {calcString = calcString + "2"; displayText.setText(calcString);});
        button3.setOnClickListener(v -> {calcString = calcString + "3"; displayText.setText(calcString);});
        button4.setOnClickListener(v ->  {calcString = calcString + "4"; displayText.setText(calcString);});;
        button5.setOnClickListener(v -> {calcString = calcString + "5"; displayText.setText(calcString);});
        button6.setOnClickListener(v -> {calcString = calcString + "6"; displayText.setText(calcString);});
        button7.setOnClickListener(v ->  {calcString = calcString + "7"; displayText.setText(calcString);});;
        button8.setOnClickListener(v -> {calcString = calcString + "8"; displayText.setText(calcString);});
        button9.setOnClickListener(v -> {calcString = calcString + "9"; displayText.setText(calcString);});
        button0.setOnClickListener(v ->  {calcString = calcString + "0"; displayText.setText(calcString);});
        buttonPlus.setOnClickListener(v -> {calcString = calcString + "+"; displayText.setText(calcString);});
        buttonMinus.setOnClickListener(v -> {calcString = calcString + "-"; displayText.setText(calcString);});
        buttonMultiply.setOnClickListener(v -> {calcString = calcString + "*"; displayText.setText(calcString);});
        buttonDivide.setOnClickListener(v -> {calcString = calcString + "/"; displayText.setText(calcString);});
        buttonClear.setOnClickListener(v -> {calcString = ""; displayText.setText("0");});
        buttonEquals.setOnClickListener(v -> {calcString = String.valueOf(calculate(calcString));

            displayText.setText(calcString);
        });

    }

    private double calculate(String input) {
        System.out.println("Calculating..." + input);

        double[] numbers = Arrays.stream(input.split("[+\\-*/]"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        List<Character> operators = new ArrayList<>();
        for (char c : input.toCharArray()){
            if (!Character.isDigit(c))
                operators.add(c);
        }
        double output = numbers[0];

        for (int i = 0 ; i < operators.size() ; i++) {
            System.out.println("i: " + i);
            switch (operators.get(i)) {
                case '+' -> output += numbers[1];
                case '-' -> output -= numbers[1];
                case '*' -> output *= numbers[1];
                case '/' -> output /= numbers[1];
                default -> System.out.println("Error: operator not recognised");
            }
        }

        System.out.println("result = " + output);
        return output;
    }

}
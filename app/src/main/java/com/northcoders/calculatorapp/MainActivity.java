package com.northcoders.calculatorapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.northcoders.calculatorapp.databinding.ActivityMainBinding;
import com.northcoders.calculatorapp.model.DisplayText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// TODO: Fix bug when operation on a 0 result causing crash

    ActivityMainBinding activityMainBinding;
    DisplayText dt = new DisplayText("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("on create");
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setDisplayText(dt);

        EdgeToEdge.enable(this);

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

        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonPlusMinus = findViewById(R.id.buttonPlusMinus);
        Button buttonModular = findViewById(R.id.buttonModular);
        Button buttonBackSpace = findViewById(R.id.buttonBackSpace);
        Button buttonDecimalSpace = findViewById(R.id.buttonDecimalPoint);


        button1.setOnClickListener(v -> number("1"));
        button2.setOnClickListener(v -> number("2"));
        button3.setOnClickListener(v -> number("3"));
        button4.setOnClickListener(v -> number("4"));
        button5.setOnClickListener(v -> number("5"));
        button6.setOnClickListener(v -> number("6"));
        button7.setOnClickListener(v -> number("7"));
        button8.setOnClickListener(v -> number("8"));
        button9.setOnClickListener(v -> number("9"));
        button0.setOnClickListener(v -> number("0"));

        buttonPlus.setOnClickListener(v -> operate("+"));
        buttonMinus.setOnClickListener(v -> operate("-"));
        buttonMultiply.setOnClickListener(v -> operate("*"));
        buttonDivide.setOnClickListener(v -> operate("/"));

        buttonClear.setOnClickListener(v -> clear());
        buttonPlusMinus.setOnClickListener(v -> doNothing());
        buttonModular.setOnClickListener(v -> doNothing());

        buttonBackSpace.setOnClickListener(v -> backSpace());
        buttonDecimalSpace.setOnClickListener(v -> decimalPoint());

        buttonEquals.setOnClickListener(v -> calculate());

    }

    private void clear() {
        dt.setDisplayTextString("0");
    }

    private void number(String num) {
        String input = dt.getDisplayTextString();
        if (input.equals("0"))
            dt.setDisplayTextString(num);
        else
            dt.setDisplayTextString(input + num);
    }

    private void operate(String op) {
        String input = dt.getDisplayTextString();

        if (input.endsWith(op))
            doNothing();
        else if (input.matches(".*[+\\-*/]"))
            dt.setDisplayTextString(input.substring(0, input.length() - 1) + op);
        else
            dt.setDisplayTextString(input + op);
    }

    private void decimalPoint() {
        String input = dt.getDisplayTextString();

        if (input.contains("."))
            doNothing();
        else
            dt.setDisplayTextString(input + ".");
    }

    private void doNothing() {
        dt.setDisplayTextString(dt.getDisplayTextString());
    }

    private void backSpace() {
        String input = dt.getDisplayTextString();
        dt.setDisplayTextString(input.substring(0, input.length() - 1));
    }

    private void calculate() {
        String input = dt.getDisplayTextString();
        System.out.println("Calculating..." + input);

        if (input.matches(".*[+\\-*/]"))
            doNothing();
        else {

            double[] numbers = Arrays.stream(input.split("[+\\-*/]"))
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            List<Character> operators = new ArrayList<>();
            for (char c : input.toCharArray()) {
                if (!Character.isDigit(c))
                    operators.add(c);
            }
            double output = numbers[0];

            for (int i = 0; i < operators.size(); i++) {
                switch (operators.get(i)) {
                    case '+' -> output += numbers[i+1];
                    case '-' -> output -= numbers[i+1];
                    case '*' -> output *= numbers[i+1];
                    case '/' -> output /= numbers[i+1];
                    default -> System.out.println("Error: operator not recognised");
                }
            }

            System.out.println("result = " + output);
            dt.setDisplayTextString(String.valueOf(output));
        }
    }

}
package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private double memory = 0; // Variable para almacenar el valor en memoria
    private String operator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                onButtonClick(buttonText);
            }
        };

        // Asigna el listener a todos los botones
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEquals, R.id.buttonClear,
                R.id.buttonMemoryAdd, R.id.buttonMemorySubtract, R.id.buttonMemoryClear, R.id.buttonMemoryRecall
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void onButtonClick(String input) {
        switch (input) {
            case "M+": // Sumar a la memoria
                memory += Double.parseDouble(display.getText().toString());
                break;
            case "M-": // Restar de la memoria
                memory -= Double.parseDouble(display.getText().toString());
                break;
            case "Mc": // Limpiar la memoria
                memory = 0;
                break;
            case "Mr": // Recuperar de la memoria
                display.setText(String.valueOf(memory));
                isNewOperation = true;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                operator = input;
                firstNumber = Double.parseDouble(display.getText().toString());
                isNewOperation = true;
                break;
            case "=":
                secondNumber = Double.parseDouble(display.getText().toString());
                double result = calculateResult();
                display.setText(String.valueOf(result));
                isNewOperation = true;
                break;
            case "C": // Caso para el botón Clear
                display.setText("0");
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
                isNewOperation = true;
                break;
            default: // Si es un número
                if (isNewOperation) {
                    display.setText(input);
                    isNewOperation = false;
                } else {
                    display.append(input);
                }
                break;
        }
    }

    private double calculateResult() {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return secondNumber != 0 ? firstNumber / secondNumber : 0;
            default:
                return 0;
        }
    }
}

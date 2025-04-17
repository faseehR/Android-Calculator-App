package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtDisplay;
    String input = "", operator = "";
    Double num1, num2, result;
    Double memory = 0.0; // Memory variable for M+, M-, MR, MS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);
        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(v -> {
                input += ((Button) v).getText().toString();
                txtDisplay.setText(input);
            });
        }

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(v -> {
                if (!input.isEmpty()) {
                    operator = ((Button) v).getText().toString();
                    num1 = Double.parseDouble(input);
                    input = "";
                    txtDisplay.setText(num1 + " " + operator);
                }
            });
        }

        findViewById(R.id.btnEqual).setOnClickListener(v -> {
            if (!input.isEmpty() && num1 != null) {
                num2 = Double.parseDouble(input);
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "−":
                        result = num1 - num2;
                        break;
                    case "×":
                        result = num1 * num2;
                        break;
                    case "÷":
                        result = (num2 == 0) ? null : num1 / num2;
                        break;
                    default:
                        result = null;
                }

                txtDisplay.setText(result != null ? String.valueOf(result) : "Error");
                input = result != null ? String.valueOf(result) : "";
                num1 = result;
                num2 = null;
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                txtDisplay.setText(input.isEmpty() ? "0" : input);
            }
        });

        findViewById(R.id.btnDot).setOnClickListener(v -> {
            if (!input.contains(".")) {
                input += ".";
                txtDisplay.setText(input);
            }
        });

        findViewById(R.id.btnC).setOnClickListener(v -> {
            input = "";
            num1 = num2 = result = null;
            operator = "";
            txtDisplay.setText("0");
        });

        findViewById(R.id.btnRoot).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                double num = Double.parseDouble(input);
                if (num < 0) {
                    txtDisplay.setText("Error");
                } else {
                    result = Math.sqrt(num);
                    if (num1 == null || operator.isEmpty()) {
                        num1 = result;
                    } else {
                        num2 = result;
                    }
                    input = String.valueOf(result);
                    txtDisplay.setText(input);
                }
            }
        });

        findViewById(R.id.btnPer).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                double num = Double.parseDouble(input);
                result = num / 100;
                if (num1 == null || operator.isEmpty()) {
                    num1 = result;
                } else {
                    num2 = result;
                }
                input = String.valueOf(result);
                txtDisplay.setText(input);
            }
        });

        findViewById(R.id.btnInverse).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                double num = Double.parseDouble(input);
                if (num == 0) {
                    txtDisplay.setText("Error");
                } else {
                    result = 1 / num;
                    if (num1 == null || operator.isEmpty()) {
                        num1 = result;
                    } else {
                        num2 = result;
                    }
                    input = String.valueOf(result);
                    txtDisplay.setText(input);
                }
            }
        });

        findViewById(R.id.btnSquare).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                double num = Double.parseDouble(input);
                result = num * num;
                if (num1 == null || operator.isEmpty()) {
                    num1 = result;
                } else {
                    num2 = result;
                }
                input = String.valueOf(result);
                txtDisplay.setText(input);
            }
        });

        findViewById(R.id.btnSign).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                double num = Double.parseDouble(input);
                result = -num;
                if (num1 == null || operator.isEmpty()) {
                    num1 = result;
                } else {
                    num2 = result;
                }
                input = String.valueOf(result);
                txtDisplay.setText(input);
            }
        });

        findViewById(R.id.btnCE).setOnClickListener(v -> {
            input = "";
            txtDisplay.setText("0");
        });


        findViewById(R.id.btnMC).setOnClickListener(v -> {
            memory = 0.0;
            txtDisplay.setText("0");
        });

        findViewById(R.id.btnMR).setOnClickListener(v -> {
            txtDisplay.setText(String.valueOf(memory));
            input = String.valueOf(memory);
        });

        findViewById(R.id.btnMPlus).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                memory += Double.parseDouble(input);
            }
        });

        findViewById(R.id.btnMMinus).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                memory -= Double.parseDouble(input);
            }
        });

        findViewById(R.id.btnMS).setOnClickListener(v -> {
            if (!input.isEmpty()) {
                memory = Double.parseDouble(input);
            }
        });

    }
}

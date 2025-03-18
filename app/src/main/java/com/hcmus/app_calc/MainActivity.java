package com.hcmus.app_calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmus.app_calc.Domain.Resoure;

public class MainActivity extends AppCompatActivity {
    private EditText editA, editB, editC;
    private Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editA = findViewById(R.id.NumberA);
        editB = findViewById(R.id.NumberB);
        editC = findViewById(R.id.NumberC);
        btnCalc = findViewById(R.id.Calc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveEquation();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void solveEquation() {
        try {
            Resoure resoure = new Resoure();
            resoure.setNumberA(Double.parseDouble(editA.getText().toString()));
            resoure.setNumberB(Double.parseDouble(editB.getText().toString()));
            resoure.setNumberC(Double.parseDouble(editC.getText().toString()));

            String result;

            if (resoure.getNumberA() == 0) {
                if (resoure.getNumberB() == 0) {
                    result = (resoure.getNumberC()== 0) ? "Vô số nghiệm" : "Vô nghiệm";
                } else {
                    result = "Nghiệm: x = " + (-resoure.getNumberC()/ resoure.getNumberB());
                }
            } else {
                double delta = resoure.getNumberB()* resoure.getNumberB()- 4 *resoure.getNumberA() * resoure.getNumberC();
                if (delta < 0) {
                    result = "Phương trình vô nghiệm";
                } else if (delta == 0) {
                    result = "Nghiệm kép: x = " + (-resoure.getNumberB()/ (2 *resoure.getNumberA()));
                } else {
                    double x1 = (-resoure.getNumberB()+ Math.sqrt(delta)) / (2 *resoure.getNumberA());
                    double x2 = (-resoure.getNumberB()- Math.sqrt(delta)) / (2 *resoure.getNumberA());
                    result = "Nghiệm x1 = " + x1 + ", x2 = " + x2;
                }
            }

            // Chuyển qua ResultActivity và truyền dữ liệu
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("RESULT", result);
            intent.putExtra("Number", resoure);
            startActivity(intent);

        } catch (NumberFormatException e) {
            editA.setError("Nhập số hợp lệ!");
            editB.setError("Nhập số hợp lệ!");
            editC.setError("Nhập số hợp lệ!");
        }
    }
}
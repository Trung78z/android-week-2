package com.hcmus.app_calc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hcmus.app_calc.Domain.Resoure;

public class ResultActivity extends AppCompatActivity {
    private Button btnBack, popupA, popupB, popupC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        String result = getIntent().getStringExtra("RESULT");
        Resoure resoure = (Resoure)  getIntent().getSerializableExtra("Number");
        TextView resultText = findViewById(R.id.resultText);
        Button btnBack = findViewById(R.id.btnBack);
        resultText.setText(result);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        popupA = findViewById(R.id.popupA);
        popupB = findViewById(R.id.popupB);
        popupC = findViewById(R.id.popupC);
        popupA.setOnClickListener(v -> showPopup("Số A", resoure.getNumberA()));
        popupB.setOnClickListener(v -> showPopup("Số B", resoure.getNumberB()));
        popupC.setOnClickListener(v -> showPopup("Số C", resoure.getNumberC()));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showPopup(String title, double value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage("Giá trị: " + value)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
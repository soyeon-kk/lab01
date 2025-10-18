package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbStart;
    private RadioGroup rgPets;
    private RadioButton rbDog, rbCat, rbRabbit;
    private Button btnDone;
    private ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // res/layout/activity_main.xml 있어야 함
        setTitle("애완동물 사진 보기");

        cbStart = findViewById(R.id.cbStart);
        rgPets = findViewById(R.id.rgPets);
        rbDog = findViewById(R.id.rbDog);
        rbCat = findViewById(R.id.rbCat);
        rbRabbit = findViewById(R.id.rbRabbit);
        btnDone = findViewById(R.id.btnDone);
        imgPet = findViewById(R.id.imgPet);

        setControlsEnabled(cbStart.isChecked());
        cbStart.setOnCheckedChangeListener((buttonView, isChecked) -> setControlsEnabled(isChecked));

        btnDone.setOnClickListener(v -> {
            int resId = 0;
            if (rbDog.isChecked()) resId = R.drawable.dog;
            else if (rbCat.isChecked()) resId = R.drawable.cat;
            else if (rbRabbit.isChecked()) resId = R.drawable.rabbit;

            if (resId != 0) {
                imgPet.setImageResource(resId);
                imgPet.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(MainActivity.this, "동물을 선택해 주세요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControlsEnabled(boolean enabled) {
        rgPets.setEnabled(enabled);
        for (int i = 0; i < rgPets.getChildCount(); i++) {
            View child = rgPets.getChildAt(i);
            child.setEnabled(enabled);
        }
        btnDone.setEnabled(enabled);
        if (!enabled) {
            imgPet.setImageDrawable(null);
            imgPet.setVisibility(View.GONE);
        }
    }
}

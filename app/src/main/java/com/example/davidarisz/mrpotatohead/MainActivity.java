package com.example.davidarisz.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toggle(View view) {
        // Cast generic view to CheckBox
        CheckBox checkbox = (CheckBox) view;
        String part = checkbox.getText().toString();
        toggleVisibility(part, !checkbox.isChecked());
    }

    private void toggleVisibility(String part, boolean visible) {
        View potatoHead = findViewById(R.id.potatohead_layout);
        View bodyPart = potatoHead.findViewWithTag(part);
        if (visible)
            bodyPart.setVisibility(View.INVISIBLE);
        else
            bodyPart.setVisibility(View.VISIBLE);
    }
}
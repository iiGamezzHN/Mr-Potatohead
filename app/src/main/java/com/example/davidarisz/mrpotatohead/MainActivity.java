package com.example.davidarisz.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String boxesTag = "boxes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            ArrayList<String> boxes = savedInstanceState.getStringArrayList(boxesTag);
            assert boxes != null;
            for (String part : boxes) {
                toggleVisibility(part, false);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        GridLayout layout = findViewById(R.id.gridLayout);

        ArrayList<String> boxes = new ArrayList<>();

        for(int i = 0; i < layout.getChildCount(); i++) {
            CheckBox checkbox = (CheckBox) layout.getChildAt(i);
            if (checkbox.isChecked()) {
                boxes.add(checkbox.getText().toString());
            }
        }

        savedInstanceState.putStringArrayList(boxesTag, boxes);
        super.onSaveInstanceState(savedInstanceState);
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
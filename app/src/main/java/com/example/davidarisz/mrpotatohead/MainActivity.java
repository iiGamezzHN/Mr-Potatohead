package com.example.davidarisz.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String boxesTag = "boxes";

    /**
     * This method is called by the Android system when the activity is created.
     * @param savedInstanceState argument that (might) contain stuff we need to
     *                           properly re-create our activity
     *                           (toggle the visibility, for example).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            ArrayList<String> boxes = savedInstanceState.getStringArrayList(boxesTag);
            assert boxes != null; // No bugging notification about possible NullPointerException
            for (String part : boxes) {
                // Toggle visibility manually
                toggleVisibility(part, false);
            }
        }
    }

    /**
     * Method that is called when the Android system decides to suspend the app.
     * We then quickly save a list of checked checkboxes so that we can toggle the corresponding
     * images in {@link #onCreate{Bundle}.
     * @param savedInstanceState see {@link #onCreate(Bundle)}
     */
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

    /**
     * This method is called by checking a checkbox.
     * It extracts the name of the part of which the visibility
     * has to be toggled and delegates this to {@link #toggleVisibility}.
     * @param view Argument given by Android.
     */
    public void toggle(View view) {
        // Cast generic view to CheckBox
        CheckBox checkbox = (CheckBox) view;
        String part = checkbox.getText().toString();
        toggleVisibility(part, !checkbox.isChecked());
    }

    /**
     * This (private) method handles the actual toggling of the visibility of the image.
     * @param part Part of Mr. Potatohead to toggle.
     * @param visible Whether part is visible now.
     */
    private void toggleVisibility(String part, boolean visible) {
        View potatoHead = findViewById(R.id.potatohead_layout);
        View bodyPart = potatoHead.findViewWithTag(part);
        if (visible)
            bodyPart.setVisibility(View.INVISIBLE);
        else
            bodyPart.setVisibility(View.VISIBLE);
    }
}
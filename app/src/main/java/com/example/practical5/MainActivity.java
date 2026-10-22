package com.example.practical5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /** Intent extra key shared with the Municipality activity. */
    public static final String EXTRA_MUNICIPALITY = "municipality_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.edit_text_municipality);

        // When the user presses "Go" on the soft keyboard, validate and navigate.
        input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_GO) {
                validateAndGo(input);
                return true;
            }
            return false;
        });
    }

    private void validateAndGo(EditText input) {
        String raw = input.getText().toString();
        String name = TextProcessor.processMunicipality(raw);

        if (name == null) {
            // Invalid input: stay on this screen and show an error.
            if (raw.trim().isEmpty()) {
                input.setError("Please enter a municipality name");
            } else {
                input.setError("Unknown municipality. Try Beijing, Shanghai, Tianjin or Chongqing");
            }
            return;
        }

        // Valid: explicit Intent to the second activity, pass the normalised name.
        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, name);
        startActivity(intent);
    }
}

package com.example.practical5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView nameView = findViewById(R.id.municipality_name);
        ImageView imageView = findViewById(R.id.municipality_image);
        TextView infoView = findViewById(R.id.municipality_info);
        Button visitButton = findViewById(R.id.visit_site_button);

        // Re-validate the name passed from MainActivity using the same TextProcessor.
        String name = TextProcessor.processMunicipality(
                getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY));

        if (name == null) {
            Toast.makeText(this, "Invalid municipality", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String[] summaries = getResources().getStringArray(R.array.municipalities_array);

        int colorRes;
        int imageRes;
        int infoIndex;
        String url;

        switch (name) {
            case "Shanghai":
                colorRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                infoIndex = 1;
                url = "https://english.shanghai.gov.cn/";
                break;
            case "Tianjin":
                colorRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                infoIndex = 2;
                url = "https://en.tj.gov.cn/";
                break;
            case "Chongqing":
                colorRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                infoIndex = 3;
                url = "https://english.cq.gov.cn/";
                break;
            case "Beijing":
            default:
                colorRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                infoIndex = 0;
                url = "https://english.beijing.gov.cn/";
                break;
        }

        int color = ContextCompat.getColor(this, colorRes);
        int lightText = ContextCompat.getColor(this, R.color.color_text_light);

        // Customise the UI programmatically according to the selected municipality.
        nameView.setText(name);
        nameView.setBackgroundColor(color);
        nameView.setTextColor(lightText);

        imageView.setImageResource(imageRes);

        infoView.setText(summaries[infoIndex]);
        infoView.setTextColor(color);

        visitButton.setBackgroundColor(color);
        visitButton.setTextColor(lightText);

        // Implicit Intent: open the official website of the displayed municipality.
        final String siteUrl = url;
        visitButton.setOnClickListener(v -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(siteUrl));
            try {
                startActivity(webIntent);
            } catch (Exception e) {
                Toast.makeText(Municipality.this,
                        "No app available to open the website",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

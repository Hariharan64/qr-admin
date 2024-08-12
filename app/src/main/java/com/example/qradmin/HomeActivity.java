package com.example.qradmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnScan;
    private TextView tvResult;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buttonShowImage = findViewById(R.id.button_show_image);
        ImageView imageView = findViewById(R.id.image_view);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button button=findViewById(R.id.button_show_ima);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView img=findViewById(R.id.image);

        // Set the image resource
        imageView.setImageResource(R.drawable.qr);

        img.setImageResource(R.drawable.qr);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView.getVisibility() == View.GONE) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }


            }
        });// Replace 'your_image_name' with the actual image name

        buttonShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of the ImageView
                if (imageView.getVisibility() == View.GONE) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }
}

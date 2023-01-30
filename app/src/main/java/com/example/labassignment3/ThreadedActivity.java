package com.example.labassignment3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThreadedActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvGreet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        iv = findViewById(R.id.imgVwProfile);
        Intent intent = getIntent();

        String strMsg = intent.getStringExtra("varStr1");
        tvGreet = findViewById(R.id.tvGreet);
        tvGreet.setText("Welcome to second Activity " + strMsg);

    }

    public void fnTakePic(View vw){

        Runnable run =  new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvGreet.setText("..This is Your Picture..");
                    }
                });
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }
}
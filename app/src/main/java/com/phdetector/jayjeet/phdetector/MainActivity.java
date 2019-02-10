package com.phdetector.jayjeet.phdetector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int r,g,b;
    public CustomImageView imageView;
    private TextView textView;
    private Button imagePickerButton,fetchPhButton;
    private Bitmap bitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALLERY = 2;


    // Select image from gallery
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if(data != null){
                //Toast.makeText(getApplicationContext(),"@NonNull Data",Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            Log.d("PHOTO",fullPhotoUri.toString());
            imageView.setImageURI(fullPhotoUri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (CustomImageView) findViewById(R.id.image_view);
        textView = findViewById(R.id.text_view);
        imagePickerButton = findViewById(R.id.image_picker_button);
        fetchPhButton = findViewById(R.id.ph_fetch_button);

        imagePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        fetchPhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ParamsActivity.class);
                intent.putExtra("red",String.valueOf(r));
                intent.putExtra("green",String.valueOf(g));
                intent.putExtra("blue",String.valueOf(b));
                startActivity(intent);
            }
        });


        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() ==  MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    int pixel = 0;
                    try{
                         pixel = bitmap.getPixel((int)event.getX(),(int)event.getY());
                         imageView.m.x = (int)event.getX();
                         imageView.m.y = (int)event.getY();
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Out of Bounds",Toast.LENGTH_SHORT).show();
                    }

                    r = Color.red(pixel);
                    g = Color.green(pixel);
                    b = Color.blue(pixel);

                    textView.setText("rgb(" + r + "," + g + "," + b + ")");
                }
                return  true;
            }
        });
    }
}

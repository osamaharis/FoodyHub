package com.example.foodhub;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class recipe_details extends AppCompatActivity {

    TextView rtitle,rdescription;
    ImageView rimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rtitle= findViewById(R.id.Recipename);
        rdescription= findViewById(R.id.Rec_detail);
        rimage= findViewById(R.id.Imageview);

       // get data from intent
        byte[] bytes = getIntent().getByteArrayExtra("image");
        String title= getIntent().getStringExtra("title");
        String descs= getIntent().getStringExtra("Description");
        Bitmap bmp= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        rtitle.setText(title);
        rdescription.setText(descs);
        rimage.setImageBitmap(bmp);


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

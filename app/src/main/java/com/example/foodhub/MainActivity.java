package com.example.foodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager; ///for sorting
    SharedPreferences sharedPreferences;// for saving sorting items////

    private Context context;
     private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        sharedPreferences= getSharedPreferences("sortsetting",MODE_PRIVATE);
        String msorting =sharedPreferences.getString("Sort", "Newest");
        if(msorting.equals("Newest"))
        {

            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
        }
        else if(msorting.equals("oldest"))

        {

            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setReverseLayout(false);
            linearLayoutManager.setStackFromEnd(false);

        }




        recyclerView.setLayoutManager(linearLayoutManager);




        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Foodies");

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("global");
        databaseReference.keepSynced(true);

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

    }

    private  void SearchItem(String seacher)

    {
        String query= seacher.toLowerCase();


        Query firebasesearcg = databaseReference.orderByChild("search").startAt(query).endAt(query+ "\uf8ff");
        FirebaseRecyclerAdapter<data ,ViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<data,ViewHolder>
                (data.class,R.layout.cardviewrows,
                ViewHolder.class,firebasesearcg) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, data model, int position) {

                viewHolder.Setdetails(context,model.getTitle(),model.getImage(),model.getDescription());
            }
//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                ViewHolder viewHolder =super.onCreateViewHolder(parent,viewType);
//                viewHolder.setonClicklistner(new ViewHolder.ClickListner() {
//                    @Override
//                    public void onItemclick(View view, int position) {
//                        //view details////
//                        TextView rTitle= view.findViewById(R.id.recipename);
//                       TextView rDescription= view.findViewById(R.id.details);
//                        ImageView RImgae= view.findViewById(R.id.imageview);
//
//                        ///get data from details///
//
//                        String Title= rTitle.getText().toString();
//                        String Description =rDescription.getText().toString();
//
//                        Drawable drawableiumag= RImgae.getDrawable();
//
//                        Bitmap bitmap=((BitmapDrawable)drawableiumag).getBitmap();
//
//                        //pass there data to next layout//////
//
//                        Intent gotodetails= new Intent(view.getContext(),recipe_details.class);
//                        ByteArrayOutputStream stream= new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
//                        byte[] bytes= stream.toByteArray();
//                        gotodetails.putExtra("image",bytes);
//                        gotodetails.putExtra("title",Title);
//                        gotodetails.putExtra("Description",Description);
//                        startActivity(gotodetails);
//
//
//                    }
//
//                    @Override
//                    public void onitemlongclick(View view, int position) {
//
//                    }
//                });
//
//
//
//                return viewHolder;
//            }


        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<data,ViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<data, ViewHolder>(data.class,
                R.layout.cardviewrows,ViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, data model, int position) {

                viewHolder.Setdetails(context,model.getTitle(),model.getImage(),model.getDescription());
            }


//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                ViewHolder viewHolder =super.onCreateViewHolder(parent,viewType);
//                viewHolder.setonClicklistner(new ViewHolder.ClickListner() {
//
//                    @Override
//                    public void onItemclick(View view, int position) {
//                        //view details////
//                        TextView rTitle= view.findViewById(R.id.recipename);
//                        TextView rDescription= view.findViewById(R.id.details);
//                        ImageView RImgae= view.findViewById(R.id.imageview);
//
//                        ///get data from details///
//
//                        String Title= rTitle.getText().toString();
//                        String Description =rDescription.getText().toString();
//
//                        Drawable drawableiumag= RImgae.getDrawable();
//
//                    Bitmap bitmap=((BitmapDrawable)drawableiumag).getBitmap();
//
//                    //pass there data to next layout//////
//
//                        Intent gotodetails= new Intent(view.getContext(),recipe_details.class);
//                        ByteArrayOutputStream stream= new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
//                        byte[] bytes= stream.toByteArray();
//                        gotodetails.putExtra("image",bytes);
//                        gotodetails.putExtra("title",Title);
//                        gotodetails.putExtra("Description",Description);
//                        startActivity(gotodetails);
//
//
//                    }
//
//                    @Override
//                    public void onitemlongclick(View view, int position) {
//
//                    }
//                });
//
//
//
//                return viewHolder;
//            }

        };
recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.searchview);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchItem(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchItem(newText);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sorting)
        {

        ShowsortingDailog();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ShowsortingDailog() {

        //option to display latest and old data/////
        String[] sortoption =  {"Newest","oldest"};
        ///Alertdailog ////
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Sort By").setIcon(R.drawable.sorting).setItems(sortoption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//the i argument is contains the index position of selected item
                ////o is newest and 1 is for oldeest////////

                if(i==0)
                {

                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("Sort","Newest");
                    editor.apply();
recreate();

                }
                else if(i==1)
                {

                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("Sort","oldest");
                    editor.apply();
                    recreate();

                }

            }
        });
builder.show();


    }
}

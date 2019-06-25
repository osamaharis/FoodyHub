package com.example.foodhub;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class ViewHolder extends RecyclerView.ViewHolder
{
    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;


//        //item clikc
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mclicklistner.onItemclick(view,getAdapterPosition());
//            }
//        });
//
/////long clcik
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                mclicklistner.onitemlongclick(view, getAdapterPosition());
//
//                return false;
//            }
//        });

    }
//    public void setRecipedetials(Context con,String title,String image,String Description)
//
//    {
//        TextView name= (TextView) view.findViewById(R.id.Recipename);
//        TextView details= (TextView)view.findViewById(R.id.details);
//        ImageView recipe_images= (ImageView)view.findViewById(R.id.Imageview);
//        Picasso.get().load(image).into(recipe_images);
//    }

    public void Setdetails(Context context,String title,String image,String Descrip)
    {
        TextView recipe_name= (TextView)view.findViewById(R.id.recipename);
        ImageView recipe_image= (ImageView) view.findViewById(R.id.imageview);
        TextView recipe_des= (TextView) view.findViewById(R.id.details);
        recipe_name.setText(title);
        recipe_des.setText(Descrip);
        Picasso.get().load(image).into(recipe_image);

    }





  //  private  ViewHolder.ClickListner mclicklistner;
    ///interface  to read callback///
//    public interface  ClickListner
//    {
//
//        void onItemclick(View view,int position);
//        void onitemlongclick(View view , int position);
//    }
//    public void setonClicklistner(ViewHolder.ClickListner clickListner)
//    {
//
//    }











}

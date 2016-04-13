package com.example.cstagiaire.demo_staggeredgrid;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by cstagiaire on 31/03/2016.
 */
public class Colors {

    private ArrayList<Integer>  colors ;

    public Colors(Context context){
        colors = new ArrayList();
        colors.add(context.getResources().getColor(R.color.bloc_color_1));
        colors.add(context.getResources().getColor(R.color.bloc_color_2));
        colors.add(context.getResources().getColor(R.color.bloc_color_3));
        colors.add(context.getResources().getColor(R.color.bloc_color_4));
        colors.add(context.getResources().getColor(R.color.bloc_color_5));
        colors.add(context.getResources().getColor(R.color.bloc_color_6));
        colors.add(context.getResources().getColor(R.color.bloc_color_7));
        colors.add(context.getResources().getColor(R.color.bloc_color_8));
        colors.add(context.getResources().getColor(R.color.bloc_color_9));
        colors.add(context.getResources().getColor(R.color.bloc_color_10));
        colors.add(context.getResources().getColor(R.color.bloc_color_11));
        colors.add(context.getResources().getColor(R.color.bloc_color_12));
    }

    public int taille(){
        return colors.size();
    }
     public int getColor(int index){
         return colors.get(index);
     }


}

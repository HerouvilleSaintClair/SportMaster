package com.example.cstagiaire.demo_staggeredgrid;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by cstagiaire on 11/04/2016.
 */


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewHolder> {
    private ArrayList<String> items = new ArrayList<>();
    private Colors colors;
    private Context context;//接收从别处传来的参数
    private TextView nom;//接收从别处传来的参数
    private int resolution;//接收从别处传来的参数

    //当环境和参数不可在本class中用时，要想着从别处传一个过来
    public void setTextView(TextView textView){
        this.nom = textView;
    }
    public void setContext(Context context){
        this.context= context;
        colors = new Colors(context);
    }
    public void setResolution(int resolution){
        this.resolution = resolution;

    }
    // ------------------end-----------------------
    public ItemAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card,
                viewGroup, false);

        return new viewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public int widthCartView(){
        int width = 100;
        if(resolution<1500){
            width = 280;
        }
        else {
            width = 550;
        }

        return width;
    }
    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position) {
        String info = items.get(position);
        View view = viewHolder.itemView;
        TextView textView = (TextView) view.findViewById(R.id.info_text);
        textView.setTextSize(20);
        textView.setWidth(widthCartView());//
        textView.setText(info);
        textView.setBackgroundColor(colors.getColor(position));
        textView.setOnTouchListener(new BlocTouchLisenter());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(View itemView) {
            super(itemView);
        }
    }
    private final class BlocTouchLisenter implements View.OnTouchListener{

        float x = 0 ;
        float y = 0 ;
        public boolean onTouch(View view ,MotionEvent event){
            if(event.getAction() == event.ACTION_DOWN){
                ClipData date = ClipData.newPlainText("","");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(date, shadowBuilder, view, 0);
                x = event.getX();
                y = event.getY();
                nom.setText("Touché !");
                return true;
            }
            else {
                return false;
            }
        }
    }
}
package com.example.cstagiaire.demo_staggeredgrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> items = new ArrayList<>();
    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.nom);
        initView();

    }
    private int spanCount(){
        int resultat = 1;
        int resolution = getWindowManager().getDefaultDisplay().getWidth();
        if(resolution<1000){
            resultat = 2;
        }
        else if(1000< resolution && resolution < 2000){
            resultat = 3;
        }
        else{
            resultat = 4;
        }
        return resultat;
    }
    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.base);
        //列数为两列



        mLayoutManager = new StaggeredGridLayoutManager(
                spanCount(),
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //构建一个临时数据源
        String entree = "Entrée\n\n Dureil Monique\n" +"Pascal François\n" + "Dureil Monique\n" + "Pascal François\n" + "Dureil Monique\n" + "Pascal François\n" + "Dureil Monique\n" + "Pascal François";
        String sortie = "Sorties\n\n" + "Adam Adelle\n" + "Piacé Albert";
        String soins = "Soins\n\n 3 prélèvements\n" +"4 glycémies\n" + "2 pansements";
        String anniversaire = "Anniversaires\n\n" + "Dupent JAcques\n" + "Maries Simone" ;
        String notiffications ="Notiffications\n\n 3 non lues" ;
        String absences = "Absences \n\n"+"David\n"+"Nick\nKea\nCaroline";
        String titre1 ="Titre 1 \n\n info1\n  info2\n" +"  info3\n" +"  info4\n" + "  info5\n" +"  info6\n" +"  info7\n" +
                "  info8\n" +"  info9\n" +"  info10\n" + "  info11\n" +"  info12\n";
        String titre2 = "Titre 2 \n\n infos 1 \n infos 2";
        items.add(entree);
        items.add(sortie);
        items.add(soins);
        items.add(anniversaire);
        items.add(notiffications);
        items.add(absences);
        items.add(titre1);
        items.add(titre2);


        mAdapter = new ItemAdapter(items);
        mAdapter.setTextView(textView);
        mAdapter.setContext(this);
        mAdapter.setResolution(getWindowManager().getDefaultDisplay().getWidth());
        mRecyclerView.setAdapter(mAdapter);
    }
}

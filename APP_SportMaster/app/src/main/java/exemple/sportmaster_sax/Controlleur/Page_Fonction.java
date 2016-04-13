package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import exemple.sportmaster_sax.Model.Trajet;
import exemple.sportmaster_sax.R;

public class Page_Fonction extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__fonction);
        textView=(TextView)findViewById(R.id.textView_fonction);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        // Test les trajectoire sélectionné sont dans le même trajet ou pas
        if(Page_Fichier.multiSelectSpinner.getSelectedStrings().size()==1){
            textView.setText("Vous sélectionnez :"+Page_Fichier.multiSelectSpinner.getSelectedStrings().get(0).toString());
        }
        else{
            HashMap<String,Boolean> map_true = new HashMap();
            int i = 0 ;
            /*      Si on ajouter test ici , il y a toujour erreur 
            if(Page_Fichier.multiSelectSpinner.getSelectedStrings().size() !=2) {
                while (i < Page_Fichier.multiSelectSpinner.getSelectedStrings().size()) {

                    double base_distance = Page_GetSource._LISTE_TRAJET.get(0).distanceParcour(0, Page_GetSource._LISTE_TRAJET.get(0).taille());
                    double base_debut_lat = Page_GetSource._LISTE_TRAJET.get(0).getPoint(0).get_lat();
                    double base_debut_lon = Page_GetSource._LISTE_TRAJET.get(0).getPoint(0).get_lon();
                    double base_fin_lat = Page_GetSource._LISTE_TRAJET.get(0).getPoint(Page_GetSource._LISTE_TRAJET.get(0).taille() - 1).get_lat();
                    double base_fin_lon = Page_GetSource._LISTE_TRAJET.get(0).getPoint(Page_GetSource._LISTE_TRAJET.get(0).taille() - 1).get_lon();
                    // condition 1 : la soustraction de distanceParcour entre toutes les trajet inférieur 20 m
                    boolean condition_1 = Page_GetSource._LISTE_TRAJET.get(i).distanceParcour(0, Page_GetSource._LISTE_TRAJET.get(i).taille()) - base_distance <= 20;
                    // condition 2 : la distance entre les début est inférieur 20 m
                    double trajet_debut_lat = Page_GetSource._LISTE_TRAJET.get(i).getPoint(0).get_lat();
                    double trajet_debut_lon = Page_GetSource._LISTE_TRAJET.get(i).getPoint(0).get_lon();
                    boolean condition_2 = Trajet.degalageDistance(trajet_debut_lat, trajet_debut_lon, base_debut_lat, base_debut_lon) <= 20;
                    // condition 3 : la distane entre les fin est inférieur 20 m
                    double trajet_fin_lat = Page_GetSource._LISTE_TRAJET.get(i).getPoint(Page_GetSource._LISTE_TRAJET.get(i).taille() - 1).get_lat();
                    double trajet_fin_lon = Page_GetSource._LISTE_TRAJET.get(i).getPoint(Page_GetSource._LISTE_TRAJET.get(i).taille() - 1).get_lon();
                    boolean condition_3 = Trajet.degalageDistance(trajet_fin_lat, trajet_fin_lon, base_fin_lat, base_fin_lon) <= 20;
                    map_true.put(i + "", (condition_1 && condition_2 && condition_3));
                    i++;

                }
                Log.d("map",map_true.toString());
                for (int t = 0; t < map_true.size(); t++) {
                    if (map_true.get(t) == true) {
                        textView.setText("les trajectoir sélectionné sont dans même trajet");
                    } else {
                        textView.setText("trajetctoir " + Page_Fichier.multiSelectSpinner.getSelectedStrings().get(t).toString() + " n'est pas le même trajet avec les autres, re-sélectionnez s'il vous plaît");
                    }
                }
            }
            else{
            */
                double base_distance = Page_GetSource._LISTE_TRAJET.get(0).distanceParcour(0, Page_GetSource._LISTE_TRAJET.get(0).taille());
                double base_debut_lat = Page_GetSource._LISTE_TRAJET.get(0).getPoint(0).get_lat();
                double base_debut_lon = Page_GetSource._LISTE_TRAJET.get(0).getPoint(0).get_lon();
                double base_fin_lat = Page_GetSource._LISTE_TRAJET.get(0).getPoint(Page_GetSource._LISTE_TRAJET.get(0).taille() - 1).get_lat();
                double base_fin_lon = Page_GetSource._LISTE_TRAJET.get(0).getPoint(Page_GetSource._LISTE_TRAJET.get(0).taille() - 1).get_lon();
                // condition 1 : la soustraction de distanceParcour entre toutes les trajet inférieur 30 m
                boolean condition_1 = Page_GetSource._LISTE_TRAJET.get(i).distanceParcour(0, Page_GetSource._LISTE_TRAJET.get(i).taille()) - base_distance <= 30;
                // condition 2 : la distance entre les début est inférieur 30 m
                double trajet_debut_lat = Page_GetSource._LISTE_TRAJET.get(1).getPoint(0).get_lat();
                double trajet_debut_lon = Page_GetSource._LISTE_TRAJET.get(1).getPoint(0).get_lon();
                boolean condition_2 = Trajet.degalageDistance(trajet_debut_lat, trajet_debut_lon, base_debut_lat, base_debut_lon) <= 10;
                // condition 3 : la distane entre les fin est inférieur 30 m
                double trajet_fin_lat = Page_GetSource._LISTE_TRAJET.get(1).getPoint(Page_GetSource._LISTE_TRAJET.get(1).taille() - 1).get_lat();
                double trajet_fin_lon = Page_GetSource._LISTE_TRAJET.get(1).getPoint(Page_GetSource._LISTE_TRAJET.get(1).taille() - 1).get_lon();
                boolean condition_3 = Trajet.degalageDistance(trajet_fin_lat, trajet_fin_lon, base_fin_lat, base_fin_lon) <= 10;
                Log.d("boolean",condition_1+" "+ condition_2+" "+condition_3);
                Log.d("valeur", Trajet.degalageDistance(trajet_fin_lat, trajet_fin_lon, base_fin_lat, base_fin_lon)+"");
                if(condition_1&&condition_2&&condition_3){
                    textView.setText("les trajectoires sont dans le même trajet");
                }
                else{
                    textView.setText("les trajectiones ne sont pas dans le même trajet , re-sélectionnez s'il vous plaît");
                }



        }
        // end test


    }

    public void toCarte(View view){
        Intent intent_toCarte = new Intent(this,Page_Carte.class);
        startActivity(intent_toCarte);
    }
    public void toAnalyse(View view){
        Intent intent_toAnalyse = new Intent(this,Page_Analyse.class);
        startActivity(intent_toAnalyse);
    }
    public void toDiagramme(View view){
        Intent intent_toDiagramme = new Intent(this,Page_diagramme.class);
        startActivity(intent_toDiagramme);
    }

}

package exemple.sportmaster_sax.Controlleur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import exemple.sportmaster_sax.Model.Point;
import exemple.sportmaster_sax.Model.Trajet;
import exemple.sportmaster_sax.R;
/*
        Cette activity permet d'afficher les détaillées du trajet par rapport à un trjalet base , mais il y des problème pas encore
        résoudre , la façon à réaliser peut-être n'est pas correct , il va utiliser prèsque 64 Mo de RAM , mais je n'ai pas le temps
        résoudre cette problème , donc je le laisse maintenant .
 */
public class Page_Dia_trajet_norme_detail extends AppCompatActivity {

    TextView donnees;
    TextView nom_trajet_1;
    TextView nom_trajet_2;
    private int limite ;// limite est interval de comparation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__dia_trajet_norme_detail);

        donnees=(TextView)findViewById(R.id.affichier_resultat_dia);
        donnees.setMovementMethod(ScrollingMovementMethod.getInstance());
        nom_trajet_1=(TextView)findViewById(R.id.nom_trajet_1);
        nom_trajet_2=(TextView)findViewById(R.id.nom_trajet_2);
        //得到base的numero
        int num_trajet = 0;
        for(int i = 0 ; i < Page_Fichier.multiSelectSpinner.getSelectedStrings().size() ; i ++){
            if(Page_Choisir_base.dia_trajet_base.getSelectedStrings().get(0) == Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i)){
                num_trajet = i;

            }
        }
        nom_trajet_2.setText(Page_Choisir_base.dia_trajet_base.getSelectedStrings().get(0).toString());
        nom_trajet_1.setText(Page_Choisir_base.dia_trajet_base.getSelectedStrings().get(1-num_trajet).toString());

        //obtenir le trajet base
        Trajet base = Page_GetSource._LISTE_TRAJET.get(num_trajet);
        Trajet trajet ;
        String infos = "";
        String string_sub_vitesse ="";
        String string_sub_distance ="";
        String string_sub_accleration = "";
        // Pour obtenir limite
        for (int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size() ; i ++) {
            trajet = Page_GetSource._LISTE_TRAJET.get(i);
            limite = 0;
            if (trajet.taille() < base.taille()) {
                limite = trajet.taille();
            } else {
                limite = base.taille();
            }
        }
        for(int j = 0 ; j < limite ; j ++){
            Point point = Page_GetSource._LISTE_TRAJET.get(1-num_trajet).getPoint(j);
            infos += point.get_acceleration()+"\n";

        }
/*
        for(int j = 0 ; j < limite ; j ++){
            Point point = Page_GetSource._LISTE_TRAJET.get(1-num_trajet).getPoint(j);
            double sub_speed = point.get_speed()-base.getPoint(j).get_speed();
            string_sub_vitesse += sub_speed+" km/h";

            // distance parcour pour tout les points de trajet
            double point_trajet_distance = 0 ;
            point_trajet_distance += point.get_distance_nextPoint();
            // distance parcour pout tout les points de base
            double point_base_distance = 0 ;
            point_base_distance += base.getPoint(j).get_distance_nextPoint();
            double sub_distance = point_trajet_distance-point_base_distance;
            string_sub_distance += sub_distance+" m";

            double sub_acceleration = point.get_acceleration() - base.getPoint(j).get_acceleration();
            string_sub_accleration += sub_acceleration+"m/s^2";
            infos+="            "+j+"                        "+string_sub_distance+
                    "              "+string_sub_vitesse+"        "+string_sub_accleration+"\n";
        }
*/


        //            0                        -3m              0.3km/h        0.01m/s^2
        donnees.setText(infos);


    }
}

package exemple.sportmaster_sax.Controlleur;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;

import exemple.sportmaster_sax.Model.Trajet;
import exemple.sportmaster_sax.R;

public class Page_Analyse extends AppCompatActivity {

    TextView donnees;
    TextView noms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__analyse);
        donnees=(TextView)findViewById(R.id.textView_analyse_donnee);
        donnees.setMovementMethod(ScrollingMovementMethod.getInstance());
        noms=(TextView)findViewById(R.id.textView_analyse_nom_trajet);
        noms.setMovementMethod(ScrollingMovementMethod.getInstance());



        String noms_trajet = "";
        for(int i = 0 ; i < Page_Fichier.multiSelectSpinner.getSelectedStrings().size();i++) {
            noms_trajet +="N° "+i+" := "+Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i)+"\n";
        }
        noms.setText(noms_trajet);
        String info = "" ;
        for(int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size();i++){
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            // selon new BigDecimal , on peut faire règle de cinq
            // Les condition seulement pour afficher les données bien rangé
            if(trajet.distanceParcour(0, trajet.taille() - 1)<100){
                if(trajet.dureeParcour(0,trajet.taille()-1)/1000<10){
                    Log.d("fonction", "oui");
                    info += i+"       "

                            +new BigDecimal(trajet.distanceParcour(0, trajet.taille() - 1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"m         "
                            +trajet.dureeParcour(0,trajet.taille()-1)+"s            "
                            +new BigDecimal(trajet.vitesseMoyenneParcour(0,trajet.taille()-1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"km/h                "
                            +trajet.maxViteese(0,trajet.taille()-1)+"km/h                "
                            +trajet.minVitesse(0,trajet.taille()-1)+"km/h        "
                            +new BigDecimal(trajet.maxAcelerationParrcour(0,trajet.taille()-1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"m/s^2                 "
                            +trajet.ercdt(0,trajet.taille()-1)+"s\n";
                }
                else{
                    info += i+"       "
                            +new BigDecimal(trajet.distanceParcour(0, trajet.taille() - 1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"m       "
                            +trajet.dureeParcour(0,trajet.taille()-1)+"s           "
                            +new BigDecimal(trajet.vitesseMoyenneParcour(0,trajet.taille()-1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"km/h                "
                            +trajet.maxViteese(0,trajet.taille()-1)+"km/h              "
                            +trajet.minVitesse(0,trajet.taille()-1)+"km/h        "
                            +new BigDecimal(trajet.maxAcelerationParrcour(0,trajet.taille()-1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"m/s^2                 "
                            +trajet.ercdt(0,trajet.taille()-1)+"s\n";

                }
            }
            else{
                info += i+"   "
                        +new BigDecimal(trajet.distanceParcour(0, trajet.taille() - 1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"m    "
                        +trajet.dureeParcour(0,trajet.taille()-1)+"s         "
                        +new BigDecimal(trajet.vitesseMoyenneParcour(0,trajet.taille()-1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"km/h                "
                        +trajet.maxViteese(0,trajet.taille()-1)+"km/h            "
                        +trajet.minVitesse(0,trajet.taille()-1)+"km/h     "
                        +new BigDecimal(trajet.maxAcelerationParrcour(0,trajet.taille()-1)).setScale(2,BigDecimal.ROUND_HALF_UP)+"m/s^2              "
                        +trajet.ercdt(0,trajet.taille()-1)+"s\n";
            }




        }
        donnees.setText(info);
    }
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}

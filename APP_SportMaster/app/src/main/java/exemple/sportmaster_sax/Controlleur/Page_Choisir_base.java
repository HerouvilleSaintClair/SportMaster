package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import exemple.sportmaster_sax.Model.MultiSelectSpinner;
import exemple.sportmaster_sax.R;

public class Page_Choisir_base extends AppCompatActivity {

    public static MultiSelectSpinner dia_trajet_base;
    List<String> nom_trajet = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__choisir_base);

        dia_trajet_base = (MultiSelectSpinner)findViewById(R.id.spinner);

        if(Page_Fichier.multiSelectSpinner.getSelectedStrings() != null){
            for(int i = 0 ; i <Page_Fichier.multiSelectSpinner.getSelectedStrings().size();i++){
                nom_trajet.add(Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i));
            }
        }
        dia_trajet_base.setItems(nom_trajet);

    }
    public void toUnTrajetBase(View view){
        Intent intent_UnTrajetBase = new Intent(this,Page_Dia_trajet_norme.class);
        startActivity(intent_UnTrajetBase);
    }
    public void toDtaille(View view){
        Intent intent = new Intent(this,Page_Dia_trajet_norme_detail.class);
        startActivity(intent);
    }
}

package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import exemple.sportmaster_sax.R;

public class Page_diagramme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_diagramme);
    }

    public void toDistance_Temps(View view){
        Intent intent_toDstance_temps = new Intent(this,Page_Dia_distance_temps.class);
        startActivity(intent_toDstance_temps);
    }
    public void toVitesse_temps(View view){
        Intent intent_toVitesse_temps = new Intent(this,Page_Dia_vitesse_temps.class);
        startActivity(intent_toVitesse_temps);
    }
    public void toChoisir_base(View view){
        Intent intent_toChoisir_base = new Intent(this,Page_Choisir_base.class);
        startActivity(intent_toChoisir_base);
    }
}

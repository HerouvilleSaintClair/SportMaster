package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import exemple.sportmaster_sax.R;

public class Page_Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__accueil);

    }

    public void toAide(View view){
        Intent intent_accueil_to_Aide = new Intent(this,Page_Aide.class);
        startActivity(intent_accueil_to_Aide);
    }
    public void toFichier(View view){
        Intent intent_accueil_to_fichier = new Intent(this,Page_Fichier.class);
        startActivity(intent_accueil_to_fichier);
    }
    public void toPropos(View view){
        Intent intent_accueil_to_a_propos = new Intent(this,Page_A_Propos.class);
        startActivity(intent_accueil_to_a_propos);
    }


}

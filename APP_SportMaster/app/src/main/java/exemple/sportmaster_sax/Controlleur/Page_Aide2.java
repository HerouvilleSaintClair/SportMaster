package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import exemple.sportmaster_sax.R;

public class Page_Aide2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__aide2);
    }
    public void toAide3(View view){
        Intent intent = new Intent(this,Page_Aide3.class);
        startActivity(intent);
    }
    public void toAccueil(View view){
        Intent intent = new Intent(this,Page_Accueil.class);
        startActivity(intent);
    }
}

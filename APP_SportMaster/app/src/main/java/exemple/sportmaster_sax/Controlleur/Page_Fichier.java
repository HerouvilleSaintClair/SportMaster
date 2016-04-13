package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import exemple.sportmaster_sax.Model.MultiSelectSpinner;
import exemple.sportmaster_sax.R;

public class Page_Fichier extends AppCompatActivity {

    public static MultiSelectSpinner multiSelectSpinner;
    List<String> noms_fichiers = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__fichier);
        multiSelectSpinner = (MultiSelectSpinner)findViewById(R.id.spinner);

        File file = new File(Environment.getExternalStorageDirectory().toString() + "/temps");
        //  Pour sélectionner tout les fichiers gpx et les affichier
        if(file.listFiles() != null){
            for(int i = 0 ; i < file.listFiles().length;i++){
                if(file.listFiles()[i].getName().endsWith(".gpx")){
                    noms_fichiers.add(file.listFiles()[i].getName());
                }
            }
        }
        multiSelectSpinner.setItems(noms_fichiers);
    }

    public void toGetSource(View view){
        Intent intent_toGetSource = new Intent(this,Page_GetSource.class);
        startActivity(intent_toGetSource);
    }
}

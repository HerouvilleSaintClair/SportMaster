package exemple.sportmaster_sax.Controlleur;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import exemple.sportmaster_sax.Model.Dia_TrajetNorme;
import exemple.sportmaster_sax.R;

public class Page_Dia_trajet_norme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__dia_trajet_norme);
        setContentView(new Dia_TrajetNorme(this));

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

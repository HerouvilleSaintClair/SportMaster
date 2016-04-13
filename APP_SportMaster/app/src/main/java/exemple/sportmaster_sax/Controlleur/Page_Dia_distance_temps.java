package exemple.sportmaster_sax.Controlleur;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import exemple.sportmaster_sax.Model.Dia_DistanceTemps;
import exemple.sportmaster_sax.R;

public class Page_Dia_distance_temps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__dia_distance_temps);
        setContentView(new Dia_DistanceTemps(this));
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

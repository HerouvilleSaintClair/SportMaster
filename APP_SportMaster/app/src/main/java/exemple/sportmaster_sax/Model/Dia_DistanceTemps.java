package exemple.sportmaster_sax.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

import exemple.sportmaster_sax.Controlleur.Page_Fichier;
import exemple.sportmaster_sax.Controlleur.Page_GetSource;

/**
 * Created by Sunyuzhe on 16/3/2.
 */
public class Dia_DistanceTemps extends View {

    private int abscisse ;
    private int prev_abscisse;
    private int ordonnee ;
    private int prev_ordonnee;


    public Dia_DistanceTemps(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        Paint paint_0 = new Paint();
        paint_0.setColor(Color.LTGRAY);
        ArrayList<Integer> colors = new ArrayList();
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.DKGRAY);
        colors.add(Color.BLACK);

        canvas.drawLine(25, 25, 25, 375, paint);
        canvas.drawLine(25, 375, 975, 375, paint);
        canvas.drawText("Distance (m)", 950, 390, paint);
        canvas.drawText("Temps (s)", 10, 10, paint);


        String test = "" ;
        int total_duree = 0;
        int total_distance = 0;
        /* test
        //Log.d("diagramme","_LISTE_TRAJET.size() : "+ Page_GetSource._LISTE_TRAJET.size());
        for(int x = 0; x < Page_GetSource._LISTE_TRAJET.size();x++){
            for(int y = 0;y < Page_GetSource._LISTE_TRAJET.get(x).taille();y++){
                Point point = Page_GetSource._LISTE_TRAJET.get(x).getPoint(y);
                Log.d("diagramme","point_"+y+" ( "+point.get_lat()+" , "+point.get_lon()+" , "+point.get_time()+" , "
                +point.get_speed()+" , "+point.get_distance_prevPoint()+" , "+point.get_distance_nextPoint()+" , "
                +point.get_duree_prevPoint()+" , "+point.get_duree_nextPoint()+" , "+point.get_acceleration());
            }
        }
        end test
        */
        for(int t = 0 ; t < Page_GetSource._LISTE_TRAJET.size();t++){
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(t);
            for(int r = 1 ; r < trajet.taille();r++){
                total_duree = (int)trajet.dureeParcour(0,r);
                total_distance = (int)trajet.distanceParcour(0,r);
             //   Log.d("diagramme","total_distance = "+total_distance);
              //  Log.d("diagramme","total_durée = "+total_duree);
            }
        }
        for (int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size() ; i ++){

            Paint paint_colors = new Paint();
            paint_colors.setColor(colors.get(i));
            // Pour séparer les nombre pair et inpair
            if(i%2 == 0){
                canvas.drawText(Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i).toString(),25+(i*100),400,paint_colors);
            }
            else{
                canvas.drawText(Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i).toString(),25+(i*100),450,paint_colors);
            }
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            abscisse = 0;
            prev_abscisse = 0;
            ordonnee = 0;
            prev_ordonnee = 0 ;

            for (int j = 1 ; j < trajet.taille() ; j++){
                Point point =trajet.getPoint(j);
                abscisse =(int) trajet.distanceParcour(0, j);
                ordonnee =(int) trajet.dureeParcour(0, j);
               // Log.d("diagramme", "ordonnee = " + ordonnee);
                //Log.d("diagramme", "abscisse = " + abscisse);
                canvas.drawLine((int) (25 + prev_abscisse *  (950.000 / total_distance)), (int) (375 - prev_ordonnee * (350.000 / total_duree)), (int) (25 + abscisse * (950.000 / total_distance)), (int) (375 - ordonnee *  (350.000 / total_duree)), paint_colors);
                prev_abscisse = abscisse;
                prev_ordonnee = ordonnee;


            }


            //canvas.drawText(test,25,400,paint);
        }
    }
}

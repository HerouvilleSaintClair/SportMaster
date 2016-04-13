package exemple.sportmaster_sax.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import exemple.sportmaster_sax.Controlleur.Page_Choisir_base;
import exemple.sportmaster_sax.Controlleur.Page_Fichier;
import exemple.sportmaster_sax.Controlleur.Page_GetSource;

/**
 * Created by Sunyuzhe on 16/3/2.
 */
public class Dia_TrajetNorme extends View {
    private int abscisse ;
    private int prev_abscisse;
    private int ordonnee ;
    private int prev_ordonnee;
    private int sub_speed;
    private int limite;// limite est interval de comparation
    public Dia_TrajetNorme(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        Paint paint_0 = new Paint();
        paint_0.setColor(Color.LTGRAY);
        ArrayList<Integer> colors = new ArrayList();
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.MAGENTA);
        colors.add(Color.BLUE);
        colors.add(Color.DKGRAY);
        colors.add(Color.BLACK);


        canvas.drawLine(25, 25, 25, 375, paint);
        canvas.drawLine(25, 200, 975, 200, paint);


        //得到base的numero
        int num_trajet = 0;

        for(int i = 0 ; i < Page_Fichier.multiSelectSpinner.getSelectedStrings().size() ; i ++){
            if(Page_Choisir_base.dia_trajet_base.getSelectedStrings().get(0) == Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i)){
                num_trajet = i;
            }
        }
        //obtenir le trajet base
        Trajet base = Page_GetSource._LISTE_TRAJET.get(num_trajet);

        int max_duree = 0;
        int max_vitess = 0;
        for(int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size();i++){
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            if(max_duree<(int)trajet.dureeParcour(0,trajet.taille()-1)){
                max_duree = (int) (trajet.dureeParcour(0,trajet.taille()-1));


            }
            for(int j = 1 ; j < trajet.taille();j++){
                if(max_vitess<trajet.getPoint(j).get_speed()){
                    max_vitess = (int)trajet.getPoint(j).get_speed();
                }
            }
        }
        Log.d("最大时间和最大速度","max_durée : "+max_duree+" , max_vitesse : "+max_vitess);

        int max_sub_vitess = 0;
        for (int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size() ; i ++) {
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            limite = 0;
            if(trajet.taille() < base.taille()){
                limite = trajet.taille();
            }
            else{
                limite = base.taille();

            }
            for (int j = 1; j <limite; j++) {
                Point point = trajet.getPoint(j);
                sub_speed = (int)(point.get_speed()-base.getPoint(j).get_speed());
                if(max_sub_vitess < Math.abs(sub_speed)){
                    max_sub_vitess = Math.abs(sub_speed) ;
                }
            }
        }

        for (int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size() ; i ++) {
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            abscisse = 0;
            prev_abscisse = 25;
            ordonnee = 0;
            prev_ordonnee = 200;

            Paint paint_colors = new Paint();
            paint_colors.setColor(colors.get(i));

            // lignes standards
            canvas.drawLine(25, 375, 950, 375, paint_0);
            canvas.drawText("vitesse : -" + max_sub_vitess + " km/h", 30, 375, paint_0);
            canvas.drawLine(25, 287, 950, 287, paint_0);
            canvas.drawText("vitess : -"+max_sub_vitess/2+" km/h" ,30,287,paint_0);

            canvas.drawLine(25, 25, 950, 25, paint_0);
            canvas.drawText("vitesse : +" + max_sub_vitess + " km/h", 30, 25, paint_0);
            canvas.drawLine(25,113,950,113,paint_0);
            canvas.drawText("vitess : +"+max_sub_vitess/2+" km/h" ,30,113,paint_0);

            for (int j = 1; j <limite; j++) {

                Point point = trajet.getPoint(j);
                sub_speed = (int)(point.get_speed()-base.getPoint(j).get_speed());
               // sub += sub_speed+" ";
                //Ordonnee+=ordonnee+" ";
                ordonnee = (int)(200-sub_speed*(175.000/max_sub_vitess));
                abscisse =(int)(25+(trajet.dureeParcour(0,j))*(950.000/max_duree));
                canvas.drawLine(prev_abscisse,prev_ordonnee,abscisse,ordonnee, paint_colors);
                prev_abscisse = abscisse;
                prev_ordonnee = ordonnee;

                sub_speed=0;
               // Log.d("坐标","x = "+abscisse+" , y = "+ordonnee);
            }

        }

    }
}

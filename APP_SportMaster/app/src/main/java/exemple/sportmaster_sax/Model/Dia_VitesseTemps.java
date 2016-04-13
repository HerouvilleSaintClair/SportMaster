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
public class Dia_VitesseTemps extends View {
    private int abscisse ;
    private int prev_abscisse;
    private int ordonnee ;
    private int prev_ordonnee;

    public Dia_VitesseTemps(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        Paint paint_0 = new Paint();
        paint_0.setColor(Color.WHITE);
        ArrayList<Integer> colors = new ArrayList();
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.BLACK);
        colors.add(Color.LTGRAY);
        canvas.drawLine(25, 25, 25, 375, paint);
        canvas.drawLine(25, 375, 975, 375, paint);
        canvas.drawText("Durée (s)", 950, 390, paint);
        canvas.drawText("Vitesse (km/h)", 10, 10, paint);



        int total_duree = 0;
        int max_vitesse = 0;
        for(int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size();i++){
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            for(int j = 1 ; j < trajet.taille();j++){
                total_duree =(int) trajet.dureeParcour(0,j);
                if(max_vitesse <trajet.getPoint(j).get_speed()){
                    max_vitesse = (int)Page_GetSource._LISTE_TRAJET.get(i).getPoint(j).get_speed();
                }
            }
        }
        // ligne standard

        canvas.drawLine(25, 25, 950, 25,paint_0);
        canvas.drawText(max_vitesse + "", 10, 25, paint_0);
        canvas.drawLine(25, 25 + 50, 950, 25 + 50, paint_0);
        canvas.drawText((max_vitesse - 1) + "", 10, 75, paint_0);
        canvas.drawLine(25, 25 + 100, 950, 25 + 100, paint_0);
        canvas.drawText((max_vitesse-2) +"",10,125,paint_0);
        canvas.drawLine(25, 25 + 150, 950, 25+150,paint_0);
        canvas.drawText((max_vitesse-3)+"",10,175,paint_0);
        canvas.drawLine(25, 25+200, 950, 25+200,paint_0);
        canvas.drawText((max_vitesse-4)+"",10,225,paint_0);
        canvas.drawLine(25, 25+250, 950, 25+250,paint_0);
        canvas.drawText((max_vitesse-5)+"",10,275,paint_0);
        canvas.drawLine(25, 25+300, 950, 25+300,paint_0);
        canvas.drawText((max_vitesse-6)+"",10,325,paint_0);
        for (int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size() ; i ++) {

            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            Paint paint_colors = new Paint();
            paint_colors.setColor(colors.get(i));
            // Pour séparer les nombre pair et inpair
            if(i%2 == 0){
                canvas.drawText(Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i).toString(),25+(i*100),400,paint_colors);
            }
            else{
                canvas.drawText(Page_Fichier.multiSelectSpinner.getSelectedStrings().get(i).toString(),25+(i*100),450,paint_colors);
            }
            abscisse = 0;
            prev_abscisse = 0;
            prev_ordonnee = 0;

            for (int j = 1; j <trajet.taille(); j++) {
                // ordonnee faut être définit ici car chaque fois de boucle , il faut être initialisement
                ordonnee = 0;


                Point point = trajet.getPoint(j);
                abscisse = (int)trajet.dureeParcour(0,j);
                ordonnee =(int) point.get_speed();



                // ligne de chaque trajet
                canvas.drawLine((int)(25 + prev_abscisse * (950.000 / total_duree)), (int)(375 - prev_ordonnee * (350.000 / max_vitesse)),
                        (int)(25 + abscisse * (950.000 / total_duree)), (int)(375 - ordonnee * (350.000 / max_vitesse)), paint_colors);
                prev_abscisse = abscisse;
                prev_ordonnee = ordonnee;

                if(ordonnee>370){
                    canvas.drawText(abscisse+"s",abscisse,380,paint_0);
                }

              //  Log.d("坐标","x = "+abscisse+" , y = "+ordonnee);

            }
        }
    }
}

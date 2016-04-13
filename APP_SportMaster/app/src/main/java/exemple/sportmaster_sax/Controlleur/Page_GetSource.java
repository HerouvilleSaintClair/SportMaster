package exemple.sportmaster_sax.Controlleur;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exemple.sportmaster_sax.Model.Point;
import exemple.sportmaster_sax.Model.Trajet;
import exemple.sportmaster_sax.R;

/*

            - Dans ReadXML , on peut obtenir un point avec lat,lon,speed,time
            - Ensuite dans onCreate , on calcule les distance_next , distance_prev , durée_prev , durée_next , accélération après ajouter
              les attributs dans le même point , à la fin ajouter les point dans trajet.
 */
public class Page_GetSource extends AppCompatActivity {

    private String lat, lon, time, speed, lat_prev, lat_next, lon_prev, lon_next, time_prev, time_next, speed_prev, speed_next;

    public static ArrayList<Trajet> _LISTE_TRAJET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__get_source);


        this._LISTE_TRAJET = new ArrayList();

        if (Page_Fichier.multiSelectSpinner != null) {
            List<String> stringList = Page_Fichier.multiSelectSpinner.getSelectedStrings();
           // Log.d("etat", "MultiSelectSpinner is not null !");
           // Log.d("multiSelect", Page_Fichier.multiSelectSpinner.getSelectedStrings().toString());
            this._LISTE_TRAJET.clear();
            for (int num = 0; num < stringList.size(); num++) {
                //   Log.d("multiSelectSpinner", Page_Fichier.multiSelectSpinner.getSelectedStrings().size() + "");
                String path = Environment.getExternalStorageDirectory().toString() + "/temps/" + stringList.get(num).toString();
                //   Log.d("fichierSélectionné", stringList.get(i).toString());
                try {
                    FileInputStream inputStream = new FileInputStream(path);
                  //  Log.d("point","开始读GPX文件了");
                    // 运行一遍程序，得到一条trajet，然后把trajet加入到_LISTE_TRAJET中
                    Trajet trajet_temporaire = readXML(inputStream);
                    Trajet trajet_finale = new Trajet();
                  //  Log.d("point","第一条trajet大小 "+trajet_temporaire.taille());
                    //Log.d("trajet_temporaire","lat "+trajet_temporaire.getPoint(0).get_lat());
                    // test
                    for(int pp = 0; pp < trajet_temporaire.taille()-1 ; pp++){
                        Point point = trajet_temporaire.getPoint(pp);
                     //   Log.d("trajet_temportaire里有什么",point.get_lat()+" "+point.get_lon()+" "+point.get_time()+"\n");
                    }
                    // end test
                    for (int i = 1; i < trajet_temporaire.taille() - 1; i++) {
                    //    Log.d("err?", "64行ok ！");
                        // si i == 1 , alors ajouter le premier et deuxième point dans trajet_finale
                        if (i == 1) {
                            double distance_next_point0 = Trajet.degalageDistance(trajet_temporaire.getPoint(0).get_lat(), trajet_temporaire.getPoint(0).get_lon(), trajet_temporaire.getPoint(1).get_lat(), trajet_temporaire.getPoint(1).get_lon());
                            int duree_next_point0 = (int) Trajet.degalageDuree(trajet_temporaire.getPoint(0).get_time().getTime(), trajet_temporaire.getPoint(1).get_time().getTime())/1000;
                            trajet_temporaire.getPoint(0).setDistance_nextPoint(distance_next_point0);
                            trajet_temporaire.getPoint(0).setDuree_nextPoint(duree_next_point0);
                            trajet_temporaire.getPoint(1).setDistance_prevPoint(distance_next_point0);
                            trajet_temporaire.getPoint(1).setDuree_prevPoint(duree_next_point0);
                            trajet_temporaire.getPoint(1).setDistance_nextPoint(Trajet.degalageDistance(trajet_temporaire.getPoint(1).get_lat(), trajet_temporaire.getPoint(1).get_lon(), trajet_temporaire.getPoint(2).get_lat(), trajet_temporaire.getPoint(2).get_lon()));
                            trajet_temporaire.getPoint(1).setDuree_nextPoint((int) Trajet.degalageDuree(trajet_temporaire.getPoint(1).get_time().getTime(), trajet_temporaire.getPoint(2).get_time().getTime()) / 1000);
                            trajet_temporaire.getPoint(1).setAcceleration(Trajet.acceleration(trajet_temporaire.getPoint(1).get_speed(), trajet_temporaire.getPoint(2).get_speed()));
                            // 把 trajet_temporaire中的处理过的点加入到trajet当中储存起来，然后分享给其他类
                            trajet_finale.ajouter(trajet_temporaire.getPoint(0));
                            trajet_finale.ajouter(trajet_temporaire.getPoint(1));
                        }
                        // Si i == trajet_temporaire.taille()-2 , alors ajouter le avant dernière point et dernière point dans trajet_finale
                        else if(i == trajet_temporaire.taille()-2){
                            Point point_fin = trajet_temporaire.getPoint(trajet_temporaire.taille() - 1);
                            Point point_avant_fin = trajet_temporaire.getPoint(trajet_temporaire.taille() - 2);
                            double distance_prev_pointFin = Trajet.degalageDistance(point_avant_fin.get_lat(), point_avant_fin.get_lon(), point_fin.get_lat(), point_fin.get_lon());
                            int duree_prev_pointFin = (int)Trajet.degalageDuree(point_avant_fin.get_time().getTime(),point_fin.get_time().getTime())/1000;
                            point_fin.setDistance_prevPoint(distance_prev_pointFin);
                            point_fin.setDuree_prevPoint(duree_prev_pointFin);

                            point_avant_fin.setDistance_nextPoint(point_fin.get_distance_prevPoint());
                            point_avant_fin.setDuree_nextPoint(point_fin.get_duree_prevPoint());
                            point_avant_fin.setDuree_prevPoint((int) Trajet.degalageDuree(trajet_temporaire.getPoint(trajet_temporaire.taille() - 3).get_time().getTime(), trajet_temporaire.getPoint(trajet_temporaire.taille() - 2).get_time().getTime()) / 1000);
                            point_avant_fin.setDistance_prevPoint(Trajet.degalageDistance(trajet_temporaire.getPoint(trajet_temporaire.taille() - 3).get_lat(), trajet_temporaire.getPoint(trajet_temporaire.taille() - 3).get_lon(), trajet_temporaire.getPoint(trajet_temporaire.taille() - 2).get_lat(), trajet_temporaire.getPoint(trajet_temporaire.taille() - 2).get_lon()));
                            point_avant_fin.setAcceleration(Trajet.acceleration(trajet_temporaire.getPoint(trajet_temporaire.taille()-3).get_speed(),trajet_temporaire.getPoint(trajet_temporaire.taille()-2).get_speed()));
                            trajet_finale.ajouter(point_avant_fin);
                            trajet_finale.ajouter(point_fin);
                        }
                        else {
                            Point point_prev = trajet_temporaire.getPoint(i-1);
                            Point point_press = trajet_temporaire.getPoint(i);
                            Point point_next = trajet_temporaire.getPoint(i+1);
                            double distance_prev = Trajet.degalageDistance(point_prev.get_lat(), point_prev.get_lon(), point_press.get_lat(), point_press.get_lon());
                            double distance_next = Trajet.degalageDistance(point_press.get_lat(), point_press.get_lon(), point_next.get_lat(), point_next.get_lon());
                            int duree_prev = (int)Trajet.degalageDuree(point_prev.get_time().getTime(),point_press.get_time().getTime())/1000;
                            int duree_next = (int)Trajet.degalageDuree(point_press.get_time().getTime(),point_next.get_time().getTime())/1000;
                            double acceleration = Trajet.acceleration(point_prev.get_acceleration(),point_next.get_acceleration());
                            trajet_temporaire.getPoint(i).setDistance_prevPoint(distance_prev);
                            trajet_temporaire.getPoint(i).setDistance_nextPoint(distance_next);
                            trajet_temporaire.getPoint(i).setDuree_prevPoint(duree_prev);
                            trajet_temporaire.getPoint(i).setDuree_nextPoint(duree_next);
                            trajet_temporaire.getPoint(i).setAcceleration(acceleration);
                            trajet_finale.ajouter(trajet_temporaire.getPoint(i));
                        }
/*
                        Log.d("trajet_finale", "infos point 0 : " + trajet_finale.getPoint(0).get_lat() + " " + trajet_finale.getPoint(0).get_lon()
                                + " " + trajet_finale.getPoint(0).get_speed() + " " + trajet_finale.getPoint(0).get_time() + " "
                                + trajet_finale.getPoint(0).get_distance_prevPoint() + " " + trajet_finale.getPoint(0).get_distance_nextPoint()
                                + " " + trajet_finale.getPoint(0).get_duree_prevPoint() + " " + trajet_finale.getPoint(0).get_duree_nextPoint()
                                + " " + trajet_finale.getPoint(0).get_acceleration() + "\n"
                                + " infos point dernière : " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_lat() + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_lon()
                                + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_speed() + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_time() + " "
                                + trajet_finale.getPoint(trajet_finale.taille() - 1).get_distance_prevPoint() + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_distance_nextPoint()
                                + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_duree_prevPoint() + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_duree_nextPoint()
                                + " " + trajet_finale.getPoint(trajet_finale.taille() - 1).get_acceleration());
*/

                    }

                    _LISTE_TRAJET.add(trajet_finale);
                    inputStream.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        Log.d("_LISTE_TRAJET",_LISTE_TRAJET.size()+"");

    }

    public Trajet readXML(InputStream inStream) {
        XmlPullParser parser = Xml.newPullParser();
        Trajet trajet = new Trajet();


        try {
            parser.setInput(inStream, "UTF-8");// 设置数据源编码
            int eventType = parser.getEventType();// 获取事件类型
            Point point = null ;
            double lat = 0;
            double lon = 0;
            Date time = null;
            double speed = 0;
            int compter = 0;
            while (eventType != XmlPullParser.END_DOCUMENT) {



                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                            compter++;
                            break;
                        case XmlPullParser.START_TAG://开始读取某个标签
                            //通过getName判断读到哪个标签，然后通过nextText()获取文本节点值，或通过getAttributeValue(i)获取属性节点值
                            String name = parser.getName();
                            if (name.equalsIgnoreCase("trkpt")) {
                                lat = Double.parseDouble(parser.getAttributeValue(null, "lat"));
                                lon = Double.parseDouble(parser.getAttributeValue(null, "lon"));

                           //     Log.d("valeur","lat :"+lat+"lon :"+lon);
                            }

                            if (name.equalsIgnoreCase("time")) {
                                DateFormat iso8600 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                                time = iso8600.parse(parser.nextText());

                          //      Log.d("valeur_time","time :"+time.toString());
                                // 如果后面是Text元素,即返回它的值
                            } else if (name.equalsIgnoreCase("speed")) {
                                speed = Double.parseDouble(parser.nextText());

                          //      Log.d("valeur","speed :"+speed);
                            }

                            compter++;
                            break;
                        case XmlPullParser.END_TAG:// 结束元素事件
                            //读完一个Person，可以将其添加到集合类中
                            if (parser.getName().equalsIgnoreCase("trkpt") && point != null) {



                            }
                            break;
                    }
                    if(lat != 0 && lon !=0 && time != null && speed != 0){
                        point = new Point(lat,lon,time,speed,0,0,0,0,0);
                        trajet.ajouter(point);
                        // Log.d("point", "informations de points" + point.get_lat() + " " + point.get_lon() + " " + point.get_time() + " " + point.get_speed());
                        lat=0;
                        lon=0;
                        time=null;
                        speed=0;
                    }




//                Log.d("compte",compter+"");
                eventType = parser.next();

            }
            inStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trajet;

    }

    public void toFonction(View view){
        Intent intent = new Intent(this,Page_Fonction.class);
        startActivity(intent);
    }


}



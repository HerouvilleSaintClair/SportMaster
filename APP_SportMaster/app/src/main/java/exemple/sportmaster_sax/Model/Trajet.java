package exemple.sportmaster_sax.Model;

import java.util.ArrayList;

public class Trajet{
    private ArrayList<Point> points;

    public Trajet(){

        points = new ArrayList();
    }
    public void ajouter(Point point){

        points.add(point);
    }
    public Point getPoint(int i){

        return points.get(i);
    }
    public void clear(){
        points.clear();
    }
    public int taille(){

        return points.size();
    }

    // numéro de Premier point est 0 !!!!!!
    public double distanceParcour(int numero_point_1 , int numero_point_2){
        double resultat_distance = 0 ;
        for(int i = numero_point_1 ; i < numero_point_2; i ++){
            resultat_distance += points.get(i).get_distance_nextPoint();
        }
        return resultat_distance;
    }

    public double vitesseMoyenneParcour(int numero_point_1 , int numero_point_2){
        double moyenneVitesse = 0;
        double sommeVitesse = 0 ;
        for(int i = numero_point_1;i < numero_point_2+1 ; i ++){
            sommeVitesse += points.get(i).get_speed();
        }
        moyenneVitesse = sommeVitesse/(numero_point_2-numero_point_1+1);
        return moyenneVitesse;
    }
    public long dureeParcour(int numero_point_1 , int numero_point_2 ){
        long resultat_duree = 0 ;
        for(int i =numero_point_1 ; i < numero_point_2 ; i++){
            resultat_duree += points.get(i).get_duree_nextPoint();
        }
        return resultat_duree;
    }

    public long dureeMoyenneParcour(int numero_point_1 , int numero_point_2){
        long moyenneDuree = 0;
        long dureeTotal = 0 ;
        for(int i = numero_point_1 ; i < numero_point_2 ; i ++){
            dureeTotal += points.get(i).get_duree_nextPoint();
        }
        moyenneDuree = dureeTotal/(numero_point_2-numero_point_1+1);
        return moyenneDuree;
    }
    public double maxAcelerationParrcour(int numero_point_1 , int numero_point_2){
        double maxAceleration = 0;
        for(int i = numero_point_1 ;i < numero_point_2 ; i ++){
            if(maxAceleration < points.get(i).get_acceleration()){
                maxAceleration = points.get(i).get_acceleration();
            }
        }
        return maxAceleration;
    }
    public double maxViteese(int numero_point_1 , int numero_point_2){
        double maxvitesse = 0 ;
        for(int i = numero_point_1 ; i < numero_point_2 ; i++){
            if(maxvitesse < points.get(i).get_speed()){
                maxvitesse = points.get(i).get_speed();
            }
        }
        return maxvitesse ;
    }
    public double minVitesse(int numero_point_1 , int numero_point_2){
        double minvitesse = 9999999 ;
        for(int i = numero_point_1 ; i < numero_point_2 ; i ++){
            if(minvitesse > points.get(i).get_speed()){
                minvitesse = points.get(i).get_speed();
            }
        }
        return minvitesse ;
    }
    //ERCDT :l’Effort Reste Constant Dans le Temps
    public long ercdt(int numero_point_1 , int numero_point_2){
        int tsnd = 0;
        int duree = 0;
        for(int i = numero_point_1 ; i < numero_point_2 ; i++){
            if(points.get(i+1).get_speed()-points.get(i).get_speed()>=0){
                duree+=1;
            }
            else{
                duree = 0 ;
            }
            if(tsnd < duree){
                tsnd = duree;
            }
        }
        return tsnd;
    }
    static public double degalageDistance(double lat_1 , double lon_1 , double lat_2 , double lon_2){
        // point_2 - point_1
        double resultat_angle = 0;
        double resultat_distance = 0 ;
        resultat_angle = Math.sin(lat_1)*Math.sin(lat_2)+Math.cos(lat_1)* Math.cos(lat_2)* Math.cos(lon_1 - lon_2) ;
        resultat_distance = 6371.004*Math.acos(resultat_angle)*Math.PI/180;

        return resultat_distance*1000;
    }
    static public long degalageDuree(long time_A,long time_B){
        // point_2 - point_1
        long resultat_time = 0;

        resultat_time = time_B-time_A ;

        return resultat_time;
    }
    static public double acceleration(double vitesse_1 , double vitesse_2){

        return (vitesse_2-vitesse_1)/2 ;
    }

}


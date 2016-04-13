package exemple.sportmaster_sax.Controlleur;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import exemple.sportmaster_sax.Model.Point;
import exemple.sportmaster_sax.Model.Trajet;
import exemple.sportmaster_sax.R;

public class Page_Carte extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__carte);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<MarkerOptions> markercolors = new ArrayList();
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        markercolors.add(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Page_GetSource._LISTE_TRAJET.get(0).getPoint(0).get_lat(), Page_GetSource._LISTE_TRAJET.get(0).getPoint(0).get_lon()), 17));


        for (int i = 0 ; i < Page_GetSource._LISTE_TRAJET.size() ; i ++){
            Trajet trajet = Page_GetSource._LISTE_TRAJET.get(i);
            for(int j = 0 ; j < trajet.taille() ; j ++){
                Point point = Page_GetSource._LISTE_TRAJET.get(i).getPoint(j);
                LatLng noeud = new LatLng(point.get_lat(),point.get_lon());
                String info = "";
                String title = "";
                title = "trajet_"+i+" point_"+j;
                info += new BigDecimal(trajet.distanceParcour(0,j)).setScale(2, BigDecimal.ROUND_HALF_UP)+"m , "+trajet.dureeParcour(0,j)+"s";
                mMap.addMarker(markercolors.get(i).position(noeud).title(title).snippet(info));

            }
        }
    }
}

package xebia.ismail.e_learning.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import xebia.ismail.e_learning.R;

/**
 * Created by Admin on 3/13/2017.
 */
public class MapsActivity extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final LatLng punto1 = new LatLng(-12.072941, -77.165595);
    private static final LatLng punto2 = new LatLng(-12.072201, -77.164656);
    private static final LatLng punto3 = new LatLng(-12.071755, -77.164603);
    private static final LatLng punto4 = new LatLng(-12.072967, -77.163589);
    private static final LatLng punto5 = new LatLng(-12.073743, -77.163599);
    private static final LatLng punto6 = new LatLng(-12.073198, -77.162178);
    private static final LatLng punto7 = new LatLng(-12.073040, -77.162328);
    private static final LatLng punto8 = new LatLng(-12.071897, -77.162151);
    private static final LatLng punto9 = new LatLng(-12.071398, -77.162210);
    private static final LatLng punto10 = new LatLng(-12.070365, -77.163127);
    private static final LatLng punto11 = new LatLng(-12.069667, -77.163122);
    private static final LatLng punto12 = new LatLng(-12.069290, -77.162366);
    private static final LatLng punto13 = new LatLng(-12.069164, -77.162028);
    private static final LatLng punto14 = new LatLng(-12.070407, -77.161894);
    private static final LatLng punto15 = new LatLng(-12.069961, -77.161153);
    private static final LatLng punto16 = new LatLng(-12.069562, -77.160681);
    private static final LatLng punto17 = new LatLng(-12.068419, -77.160091);
    private static final LatLng punto18 = new LatLng(-12.068980, -77.158611);
    private static final LatLng punto19 = new LatLng(-12.073045, -77.167928);


    private Marker punto1m;
    private Marker punto2m;
    private Marker punto3m;
    private Marker punto4m;
    private Marker punto5m;
    private Marker punto6m;
    private Marker punto7m;
    private Marker punto8m;
    private Marker punto9m;
    private Marker punto10m;
    private Marker punto11m;
    private Marker punto12m;
    private Marker punto13m;
    private Marker punto14m;
    private Marker punto15m;
    private Marker punto16m;
    private Marker punto17m;
    private Marker punto18m;
    private Marker punto19m;

    private GoogleMap mMap;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_maps, container, false);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add some markers to the map, and add a data object to each marker.
        punto1m = mMap.addMarker(new MarkerOptions()
                .position(punto1)
                .title("Punto 1 (Av. Bolognesi N°11)"));
        punto1m.setTag(0);

        punto2m = mMap.addMarker(new MarkerOptions()
                .position(punto2)
                .title(" (Punto 2 (Av. Bolognesi cuadra 1)"));
        punto2m.setTag(0);

        punto3m = mMap.addMarker(new MarkerOptions()
                .position(punto3)
                .title("Punto 3 (Jr. Sáenz Peña cdra. 4)"));
        punto3m.setTag(0);

        // Add some markers to the map, and add a data object to each marker.
        punto4m = mMap.addMarker(new MarkerOptions()
                .position(punto4)
                .title("Punto 5 (Sáenz Peña N° 275 y 285)"));
        punto4m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto5m = mMap.addMarker(new MarkerOptions()
                .position(punto5)
                .title("Punto 5 (Jr. Tarapacá N° 155)"));
        punto5m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto6m = mMap.addMarker(new MarkerOptions()
                .position(punto6)
                .title("Punto 6 (Jr. Larco N° 151)"));
        punto6m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto7m = mMap.addMarker(new MarkerOptions()
                .position(punto7)
                .title("Punto 7 (Jr. Tarapacá N° 288)"));
        punto7m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto8m = mMap.addMarker(new MarkerOptions()
                .position(punto8)
                .title("Punto 9 (Jr. Arrieta N° 295)"));
        punto8m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto9m = mMap.addMarker(new MarkerOptions()
                .position(punto9)
                .title("Punto 9 (Jr. Arrieta N° 320)"));
        punto9m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto10m = mMap.addMarker(new MarkerOptions()
                .position(punto10)
                .title("Punto 10 (Jr. Arrieta N° 492)"));
        punto10m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto11m = mMap.addMarker(new MarkerOptions()
                .position(punto11)
                .title("Punto 11 (Jr. Figueredo N° 470)"));
        punto11m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto12m = mMap.addMarker(new MarkerOptions()
                .position(punto12)
                .title("Punto 12 (Jr. Figueredo N° 520)"));
        punto12m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto13m = mMap.addMarker(new MarkerOptions()
                .position(punto13)
                .title("Punto 13 (Jr. Moore N° 496)"));
        punto13m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto14m = mMap.addMarker(new MarkerOptions()
                .position(punto14)
                .title("Punto 14 (Av. Bolognesi N° 508)"));
        punto14m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto15m = mMap.addMarker(new MarkerOptions()
                .position(punto15)
                .title("Punto 15 (Jr. Moore N° 380)"));
        punto15m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto16m = mMap.addMarker(new MarkerOptions()
                .position(punto16)
                .title("Punto 16 (Jr. Teniente Palacios N°375)"));
        punto16m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto17m = mMap.addMarker(new MarkerOptions()
                .position(punto17)
                .title("Punto 17 (Jr. Ferré N° 460)"));
        punto17m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto18m = mMap.addMarker(new MarkerOptions()
                .position(punto18)
                .title("Punto 18 (Jr. Elías Aguirre N° 155)"));
        punto18m.setTag(0);
        // Add some markers to the map, and add a data object to each marker.
        punto19m = mMap.addMarker(new MarkerOptions()
                .position(punto19)
                .title("Punto 19 (Escuela Naval)"));
        punto19m.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

//the include method will calculate the min and max bound.
        builder.include(punto1m.getPosition());
        builder.include(punto2m.getPosition());
        builder.include(punto3m.getPosition());
        builder.include(punto4m.getPosition());
        builder.include(punto5m.getPosition());
        builder.include(punto6m.getPosition());
        builder.include(punto7m.getPosition());
        builder.include(punto8m.getPosition());
        builder.include(punto9m.getPosition());
        builder.include(punto10m.getPosition());
        builder.include(punto11m.getPosition());
        builder.include(punto12m.getPosition());
        builder.include(punto13m.getPosition());
        builder.include(punto14m.getPosition());
        builder.include(punto15m.getPosition());
        builder.include(punto16m.getPosition());
        builder.include(punto17m.getPosition());
        builder.include(punto18m.getPosition());
        builder.include(punto19m.getPosition());

        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.animateCamera(cu);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            //Toast.makeText(this, marker.getTitle()+ "has been clicked"+clickCount+" times.", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}


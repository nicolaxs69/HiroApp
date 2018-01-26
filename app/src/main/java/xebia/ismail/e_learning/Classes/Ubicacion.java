package xebia.ismail.e_learning.Classes;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Nicolas on 24/01/2018.
 */

public class Ubicacion {

    private LatLng latitudLongitud;
    private double distancia;

    public Ubicacion() {
        super();
    }

    public LatLng getLatitudLongitud() {
        return latitudLongitud;
    }

    public void setLatitudLongitud(LatLng latitudLongitud) {
        this.latitudLongitud = latitudLongitud;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "latitudLongitud=" + latitudLongitud.toString() +
                ", distancia=" + distancia +
                '}';
    }
}

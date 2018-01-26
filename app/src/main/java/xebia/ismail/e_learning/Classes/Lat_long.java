package xebia.ismail.e_learning.Classes;

/**
 * Created by Nicolas on 24/01/2018.
 */

public class Lat_long {
    private double latitud;
    private double longuitud;


    public Lat_long(double latitud, double longuitud) {
        super();
        this.latitud = latitud;
        this.longuitud= longuitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(double longuitud) {
        this.longuitud = longuitud;
    }


    @Override
    public String toString() {
        return  latitud + "," + longuitud ;
    }
}



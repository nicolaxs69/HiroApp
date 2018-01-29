package xebia.ismail.e_learning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import xebia.ismail.e_learning.Classes.Ubicacion;
import xebia.ismail.e_learning.Classes.UpdateHelper;
import xebia.ismail.e_learning.fragment.ConfigurationProfile;
import xebia.ismail.e_learning.fragment.HomeFragment;
import xebia.ismail.e_learning.fragment.MapsActivity;
import xebia.ismail.e_learning.fragment.TabProfile;
import xebia.ismail.e_learning.fragment.VolumeFragment;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UpdateHelper.OnUpdateCheckListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {


    private FirebaseAnalytics mFirebaseAnalytics;
    private  final String SELECTED_ITEM_ID = "SELECTED_ITEM_ID";
    private  Handler mDrawerHandler = new Handler();
    private DrawerLayout mDrawerLayout;
    private int mPrevSelectedId;
    private NavigationView mNavigationView;
    private int mSelectedId;
    private Toolbar mToolbar;
    private Context mContext;
    private String id;
    private String name;
    private final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 111;
    private GoogleApiClient mGoogleApiClient;
            private LocationRequest mLocationRequest;
    private LocationListener listener;
    private LatLng currentLatLng;
    public static Handler handlerDatos;

    private  final LatLng punto1 = new LatLng(-12.072941, -77.165595);
    private  final LatLng punto2 = new LatLng(-12.072201, -77.164656);
    private  final LatLng punto3 = new LatLng(-12.071755, -77.164603);
    private  final LatLng punto4 = new LatLng(-12.072967, -77.163589);
    private  final LatLng punto5 = new LatLng(-12.073743, -77.163599);
    private  final LatLng punto6 = new LatLng(-12.073198, -77.162178);
    private  final LatLng punto7 = new LatLng(-12.073040, -77.162328);
    private  final LatLng punto8 = new LatLng(-12.071897, -77.162151);
    private  final LatLng punto9 = new LatLng(-12.071398, -77.162210);
    private  final LatLng punto10 = new LatLng(-12.070365, -77.163127);
    private  final LatLng punto11 = new LatLng(-12.069667, -77.163122);
    private  final LatLng punto12 = new LatLng(-12.069290, -77.162366);
    private  final LatLng punto13 = new LatLng(-12.069164, -77.162028);
    private  final LatLng punto14 = new LatLng(-12.070407, -77.161894);
    private  final LatLng punto15 = new LatLng(-12.069961, -77.161153);
    private  final LatLng punto16 = new LatLng(-12.069562, -77.160681);
    private  final LatLng punto17 = new LatLng(-12.068419, -77.160091);
    private  final LatLng punto18 = new LatLng(-12.068980, -77.158611);
    private  final LatLng punto19 = new LatLng(-12.073045, -77.167928);

    List<Ubicacion> ubicaciones = new ArrayList<>();
    Ubicacion ubicacion = new Ubicacion();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        mContext = getApplicationContext();

        UpdateHelper.with(this)
                .onUpdateCheck(this)
                .check();

        //PASO 2. Ejecuto el hilo que recibe el mensaje de la peticion GPS
        //mThread.run();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        FirebaseMessaging.getInstance().subscribeToTopic("NEWYORK_WEATHER");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        assert mNavigationView != null;
        mNavigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                super.onDrawerSlide(drawerView, 0);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mSelectedId = mNavigationView.getMenu().getItem(prefs.getInt("default_view", 0)).getItemId();
        mSelectedId = savedInstanceState == null ? mSelectedId : savedInstanceState.getInt(SELECTED_ITEM_ID);
        mPrevSelectedId = mSelectedId;
        mNavigationView.getMenu().findItem(mSelectedId).setChecked(true);


        if (savedInstanceState == null) {
            mDrawerHandler.removeCallbacksAndMessages(null);
            mDrawerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigate(mSelectedId);
                }
            }, 250);

            boolean openDrawer = prefs.getBoolean("open_drawer", false);

            if (openDrawer)
                mDrawerLayout.openDrawer(GravityCompat.START);
            else
                mDrawerLayout.closeDrawers();
        }

        //GPS PARTE
        listener = new LocationListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onLocationChanged(Location location) {
                //txtOutput.setText("Lattitude:" + Double.toString(location.getLatitude()) + "\n Longitude: " + Double.toString(location.getLongitude()));
                //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, listener);
                currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                showDistance2(ubicaciones);

            }
        };
        initElements();

        // Check for permisson Android 6.0 Marshmellow API 23
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, // Activity
                    new String[]{ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }

                    handlerDatos = new Handler() {
                @SuppressLint("MissingPermission")
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    handlerDatos.obtainMessage();

                    if (msg.getData().getString("data") == "Send GPS Coordinates") {
                        // Ejecutar metodos y enviar resultados al servicio
                        EnableGPSAutoMatically();
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, listener);

                    }
                }
            };

    }

    private void initElements() {

        //Gps Google init
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // Check if the GPS is turn on
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //toast("GPS is not on");
            //EnableGPSAutoMatically();
        }

        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto1);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto2);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto3);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto4);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto5);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto6);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto7);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto8);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto10);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto11);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto12);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto13);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto14);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto15);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto16);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto17);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto18);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
        ubicacion.setLatitudLongitud(punto19);
        ubicaciones.add(ubicacion);
        ubicacion = new Ubicacion();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10); // Update location every second
        mLocationRequest.setNumUpdates(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        //mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }


    //PASO 2. Handler que escucha las peticiones de prender GPS

//    public class MyThreadListen extends Thread {
//        @Override
//        public void run() {
//            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_MORE_FAVORABLE);
//            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
//            //Looper.prepare();
//
//            handlerDatos = new Handler() {
//                @SuppressLint("MissingPermission")
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    handlerDatos.obtainMessage();
//
//                    if (msg.getData().getString("data") == "Send GPS Coordinates") {
//                        // Ejecutar metodos y enviar resultados al servicio
//                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, listener);
//                        EnableGPSAutoMatically();
//
//                    }
//                }
//            };
//
//            //Looper.loop();
//        }
//    }
//    MyThreadListen mThread = new MyThreadListen();


    // Ejecuto hablitar el GPS y habilitar los permisos y envio las coordenadas al hilo de firebaseService

    public void showDistance2(List<Ubicacion> ubicacionList) {
        Double meters=new Double(0D);

        for (int i = 0; i < ubicacionList.size(); i++) {
            Location locationA = new Location("Location A");
            locationA.setLatitude(ubicacionList.get(i).getLatitudLongitud().latitude);
            locationA.setLongitude(ubicacionList.get(i).getLatitudLongitud().longitude);
            Location locationB = new Location("Location B");
            locationB.setLatitude(currentLatLng.latitude);
            locationB.setLongitude(currentLatLng.longitude);
            meters = Double.parseDouble(new DecimalFormat("##.##").format(locationA.distanceTo(locationB)));
            ubicacionList.get(i).setDistancia(meters);
            meters=new Double(0D);
        }

        // The lower value and return the GPS Coordenades
        Collections.sort(ubicacionList, new Comparator<Ubicacion>() {
            @Override
            public int compare(Ubicacion obj1, Ubicacion obj2) {
                return Double.compare(obj1.getDistancia(), obj2.getDistancia());
            }
        });

        // Catch the Lattitude and the Longuitude
       final String latLong = new String(String.valueOf(ubicaciones.get(0).getLatitudLongitud()));
        String lat = latLong.substring(10, 19);
        String lon = latLong.substring(20, 29);

        Log.d("LattitudeLonguitude",latLong);

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                Message dataToSend2 = sendBundle(latLong);
                if (FirebaseMessagingService.handlerDatos != null) {
                    FirebaseMessagingService.handlerDatos.sendMessage(dataToSend2);
                }
                Looper.loop();
            }
        }).start();


//        Log.d("Ubicacion mas cercana", String.valueOf(ubicaciones.get(0).getLatitudLongitud()));
//        txtOutput.setText( "La Ubicacion mas cercana se encuentras en las Coordenadas: "+ lat +","+ lon +
//                " a " +String.valueOf(ubicaciones.get(0).getDistancia())+" de distancia");
    }

    public void EnableGPSAutoMatically() {

        GoogleApiClient googleApiClient = null;
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            // **************************
            builder.setAlwaysShow(true); // this is the key ingredient
            // **************************

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                    .checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result
                            .getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, listener);
                            //toast("Success");
                            // All location settings are satisfied. The client can
                            // initialize location
                            // requests here.
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            //toast("GPS is not on");
                            // Location settings are not satisfied. But could be
                            // fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling
                                // startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(MainActivity.this, 1000);

                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                           // toast("Setting change not allowed");
                            // Location settings are not satisfied. However, we have
                            // no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }
    }
    public void switchFragment(int itemId) {
        mSelectedId = mNavigationView.getMenu().getItem(itemId).getItemId();
        mNavigationView.getMenu().findItem(mSelectedId).setChecked(true);
        mDrawerHandler.removeCallbacksAndMessages(null);
        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(mSelectedId);
            }
        }, 250);
        mDrawerLayout.closeDrawers();
    }
    private void navigate(final int itemId) {
        final View elevation = findViewById(R.id.elevation);

        Fragment navFragment = null;

        switch (itemId) {

            case R.id.nav_1:
                id = "1";
                name = "Zonas Seguras";
                AnalyticsEvent(id,name);
                mPrevSelectedId = itemId;
                setTitle(R.string.nav_home);
                navFragment = new MapsActivity();
                break;

                case R.id.nav_2:
                id = "2";
                name = "Mochila de emergencia";
                AnalyticsEvent(id,name);
                mPrevSelectedId = itemId;
                setTitle(R.string.nav_reward);
                navFragment = new VolumeFragment();
                break;

            case R.id.nav_3:
                id = "3";
                name = "telefonos de emergencia";
                AnalyticsEvent(id,name);
                mPrevSelectedId = itemId;
                setTitle(R.string.nav_home2);
                navFragment = new HomeFragment();

                break;

            case R.id.nav_4:
                id = "3";
                name = "Mi perfil";
                AnalyticsEvent(id,name);
                mPrevSelectedId = itemId;
                setTitle(R.string.nav_home3);
                navFragment = new TabProfile();
                break;


            case R.id.nav_5:
                mPrevSelectedId = itemId;
                setTitle(R.string.nav_home4);
                navFragment = new ConfigurationProfile();
                break;

            case R.id.nav_6:
                startActivity(new Intent(this, AboutActivity.class));
                mNavigationView.getMenu().findItem(mPrevSelectedId).setChecked(true);
                return;
        }


        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(4));

        if (navFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

            try {
                transaction.replace(R.id.content_frame, navFragment).commit();

                //setting jarak elevasi bayangan ketika menggunakan tabs

                if (elevation != null) {
                    params.topMargin = navFragment instanceof VolumeFragment ? dp(48) : 0;

                    Animation a = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            elevation.setLayoutParams(params);
                        }
                    };
                    a.setDuration(150);
                    elevation.startAnimation(a);
                }
            } catch (IllegalStateException ignored) {
            }
        }
    }
    private void AnalyticsEvent(String id, String name){

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Abrio la actividad"+name);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }
    public int dp(float value) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;

        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        mDrawerHandler.removeCallbacksAndMessages(null);
        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(mSelectedId);
            }
        }, 250);
        mDrawerLayout.closeDrawers();
        return true;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onUpdateCheckListener(final String urlApp) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Hay una nueva versión  disponible")
                .setMessage("Por favor actualice a la nueva versión para continuar")
                .setPositiveButton("ACTUALIZAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, ""+urlApp, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();

    }
    private Message sendBundle(String mensaje) {
        Message dataToSend = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("data", mensaje);
        dataToSend.setData(bundle);
        return dataToSend;
    }
}
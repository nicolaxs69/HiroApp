package xebia.ismail.e_learning.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import xebia.ismail.e_learning.R;

/**
 * Created by Admin on 3/13/2017.
 */
public class HomeFragment extends Fragment {

    ImageButton imgButton1;
    ImageButton imgButton2;
    ImageButton imgButton3;
    ImageButton imgButton4;
    private int entries = 4;
    private String phoneNum[];

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_maps, container, false);
        phoneNum = new String[entries];
        populateArrays();

        imgButton1 = (ImageButton) view.findViewById(R.id.foto1);
        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialer(phoneNum[0]);
            }
        });

        imgButton2 = (ImageButton) view.findViewById(R.id.foto2);
        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialer(phoneNum[1]);
            }
        });

        imgButton3 = (ImageButton) view.findViewById(R.id.foto3);
        imgButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialer(phoneNum[2]);
            }
        });


        imgButton4 = (ImageButton) view.findViewById(R.id.foto4);
        imgButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialer(phoneNum[3]);
            }
        });
        return view;
    }

    public void launchDialer(String number) {
        String numberToDial = "tel:" + number;
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
    }

    public void populateArrays() {
        phoneNum[0] = "105";
        phoneNum[1] = "116";
        phoneNum[2] = "01-482-8988";
        phoneNum[3] = "01-711-6000";
    }
}


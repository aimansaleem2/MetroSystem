package com.nexus.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetroStations extends AppCompatActivity {

   /* String arr[]={
            "Miyapur R00",
            "JNTU College R01",
            "KPHB Colony R02",
            "Kukatpally R03",
            "Balanagar R04",
            "Moosapet R05",
            "Bharatnagar R06",
            "Erragadda R07",
            "ESI Hospital R08",
            "SR Nagar R09",
            "Ameerpet R10",
            "Punjagutta R11",
            "Irrum Manzil R12",
            "Khairatabad R13",
            "Lakdi Ka Pul R14",
            "Assembly R15",
            "Nampally R16",
            "Gandhi Bhavan R17",
            "Osmania Medical College R18",
            "MG Bus station R19",
            "Malakpet R20",
            "New Market R21",
            "Musarambagh R22",
            "Dilsukhnagar R23",
            "Chaitanyapuri R24",
            "Victoria Memorial R25",
            "LB Nagar R26",


            "Secunderabad West G49",
            "Gandhi Hospital G50",
            "Musheerabad G51",
            "RTC X Roads G52",
            "Chikkadpally G53",
            "Narayanguda G54",
            "Sultan Bazaar G55",


            "Nagole B27",
            "Uppal B28",
            "Stadium B29",
            "NGRI B30",
            "Habsiguda B31",
            "Tarnaka B32",
            "Mettuguda B33",
            "Secunderabad East B34",
            "JBS Parade Ground B35",
            "Paradise B36",
            "Rasoolpura B37",
            "Prakash Nagar B38",
            "Begumpet B39",
            "Madhura Nagar B40",
            "Yousufguda B41",
            "Jubilee Hills Road No 5  B42",
            "Jubilee Hills Check Post B43",
            "Peddamma Gudi B44",
            "Madhapur B45",
            "Durgam Cheruvu B46",
            "Hitec City B47",
            "Raidurg B48"
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_stations);


        MainActivity a=new MainActivity();
        List ans = Arrays.asList(a.arr);
        ArrayList<String> change=new ArrayList<>();

        ListView ls=findViewById(R.id.list);

        Myadapter myadpt=new Myadapter(this,ans,change);
        ls.setAdapter(myadpt);

    }
}
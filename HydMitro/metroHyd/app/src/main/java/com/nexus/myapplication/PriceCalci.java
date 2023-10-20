package com.nexus.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class PriceCalci extends AppCompatActivity {
    String arr[]={
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
            "RTC Cross Roads G52",
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
            "Parade Ground B35",
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
    };

    String s="";
    public int src=-1;
    public int dest=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_calci);

        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.source1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.select_dialog_item, arr);

        autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);

        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                src=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });



        AutoCompleteTextView autocomplete2 = (AutoCompleteTextView) findViewById(R.id.destination);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, arr);

        autocomplete2.setThreshold(1);
        autocomplete2.setAdapter(adapter2);

        autocomplete2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                dest=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });




    }

   public  void clicked(View view){
if(src==dest){
    TextView text1=(TextView) findViewById(R.id.textView10);
    text1.setText("YOUR FARE:");

    TextView text2=(TextView) findViewById(R.id.textView5);
    text2.setText("");

    TextView tx3=(TextView) findViewById(R.id.textView11);
    tx3.setText("0");

    TextView TextOffer=(TextView) findViewById(R.id.textView13);
    TextOffer.setText("YOU ARE ALREADY AT THE DESTINATION");

    return;
}
        InputStream is= getResources().openRawResource(R.raw.fare);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        String as="";
        ArrayList<String> res=new ArrayList<>();

        try {
            int count=0;
            while(true){
                try {
                    if (!((as= reader.readLine())!=null)) break;
                    else{
                        if(count==src)
                            res.add(as);
                        count++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            Context context = getApplicationContext();
            CharSequence text = "SORRY CAN'T LOAD";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        String[] fare=res.get(0).split(",");

        TextView tx3=(TextView) findViewById(R.id.textView11);
        tx3.setText(fare[dest]+" Rs");

        TextView TextOffer=(TextView) findViewById(R.id.textView13);
        TextOffer.setText("(online payments have a 10% discount)");

       TextView text=(TextView) findViewById(R.id.textView10);
       text.setText("YOUR FARE:");

       TextView text2=(TextView) findViewById(R.id.textView5);
       text2.setText("");

    }

}
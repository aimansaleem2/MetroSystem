package com.nexus.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

   public String arr[]={
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
    };

    String s;
    int src=-1;
    int dest=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    public void P1_bay(View view){
        Intent i=new Intent(this,MapView.class);
        startActivity(i);
    }
    public void P1_bay2(View view){
        Intent i=new Intent(this,PriceCalci.class);
        startActivity(i);
    }
    public void P1_bay3(View view){

        Intent i=new Intent(this,MetroStations.class);
        startActivity(i);

    }
    public void P1_bay4(View view){
        Intent i=new Intent(this,nearestMetro.class);
        startActivity(i);
    }

    public void findDest(View view){


        if(src!=-1 && dest!=-1 && src!=dest) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add(src+"");
            ans.add(dest+"");

            Intent i = new Intent(this, Map_List.class);
            i.putStringArrayListExtra("list", ans);
            startActivity(i);
        }
        else if(src==dest && src!=-1){
            Context context = getApplicationContext();
            CharSequence text = "YOU ARE ALREADY AT THE LOCATION";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "PLEASE ENTER THE REQUIRED FIELDS";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //to reset the fiels when back button is pressed
        src=-1;
        dest=-1;
        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.source1);
        autocomplete.setText("");

        AutoCompleteTextView autocomplete2 = (AutoCompleteTextView) findViewById(R.id.destination);
        autocomplete2.setText("");
    }

       // A utility function to print the constructed distance
    // array
    void printSolution(int dist[])
    {
        System.out.println(
            "Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }
 
    // Function that implements Dijkstra's single source
    // shortest path algorithm for a graph represented using
    // adjacency matrix representation
    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array.
                                 // dist[i] will hold
        // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in
        // shortest path tree or shortest distance from src
        // to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[]
        // as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set
            // of vertices not yet processed. u is always
            // equal to src in first iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of
            // the picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update dist[v] only if is not in sptSet,
                // there is an edge from u to v, and total
                // weight of path from src to v through u is
                // smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0
                    && dist[u] != Integer.MAX_VALUE
                    && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        // print the constructed distance array
        printSolution(dist);
    }

    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        LinkedList<Integer> queue
            = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);
 
        while (queue.size() != 0) {
 
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");
 
            // Get all adjacent vertices of the dequeued
            // vertex s.
            // If an adjacent has not been visited,
            // then mark it visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
   

}

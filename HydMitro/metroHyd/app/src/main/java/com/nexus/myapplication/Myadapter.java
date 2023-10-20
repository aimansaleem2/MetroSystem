package com.nexus.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Myadapter extends ArrayAdapter {

    List<String> title;
    Context context;
    List<String> change;

    public Myadapter(@NonNull Context context, List<String> title, List<String> change) {
        super(context, R.layout.items, title);
        this.title = title;
        this.context = context;
        this.change = change;
    }

    @NonNull
    @Override

    public View getView(int position, View convertview, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);


        TextView txt = view.findViewById(R.id.textView2);
        //remove codes of stations
        String getStr=title.get(position);
        txt.setText(getStr.substring(0,getStr.length()-4));
        char ch = getStr.charAt(getStr.length() - 3);


        CardView C = (CardView) view.findViewById(R.id.cardView);

        if (ch == 'R') {
            C.setBackgroundResource(R.color.red);
        } else if (ch == 'G') {
            C.setBackgroundResource(R.color.green);
        } else if (ch == 'B') {
            C.setBackgroundResource(R.color.blue);
        }


        TextView t = (TextView) view.findViewById(R.id.alert);

        if (change.contains(txt.getText())) {
            C.setBackgroundResource(R.color.black);
            t.setText("    (change here)");
        }
        TextView t2 = (TextView) view.findViewById(R.id.fairValue);

        return view;
        }


    
 
// Solves the all-pairs shortest path problem using
// Johnson's algorithm
void floydWarshall(int graph[][V])
{
    int dist[V][V], i, j, k;
 
    /* Initialize the solution matrix same as input graph
       matrix. Or we can say the initial values of shortest
       distances are based
       on shortest paths considering no intermediate vertex.
     */
    for (i = 0; i < V; i++)
        for (j = 0; j < V; j++)
            dist[i][j] = graph[i][j];
 
    /* Add all vertices one by one to the set of
      intermediate vertices.
      ---> Before start of a iteration, we have shortest
      distances between all pairs of vertices such that the
      shortest distances consider only the vertices in set
      {0, 1, 2, .. k-1} as intermediate vertices.
      ----> After the end of a iteration, vertex no. k is
      added to the set of
      intermediate vertices and the set becomes {0, 1, 2, ..
      k} */
    for (k = 0; k < V; k++) {
        // Pick all vertices as source one by one
        for (i = 0; i < V; i++) {
            // Pick all vertices as destination for the
            // above picked source
            for (j = 0; j < V; j++) {
                // If vertex k is on the shortest path from
                // i to j, then update the value of
                // dist[i][j]
                if (dist[i][k] + dist[k][j] < dist[i][j])
                    dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }
 
    // Print the shortest distance matrix
    printSolution(dist);
}
}

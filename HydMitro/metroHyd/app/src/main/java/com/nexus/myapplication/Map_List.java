package com.nexus.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Map_List extends AppCompatActivity {
    public Graph gh;//graph instance referance
    ArrayList<String> change=new ArrayList<>(); //interchanges values
    public int dest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_list);

        //graph building
        gh=new Graph();
        gh.buildGraph();



        //call function
        findWay();
    }
    public void findWay(){

        //get source and destination values from main activity
        ArrayList<String> ar1=getIntent().getExtras().getStringArrayList("list");

        //src and destination
        int src=Integer.parseInt(ar1.get(0));
        dest=Integer.parseInt(ar1.get(1));

        //shortest path stations from graph class
        ArrayList<String> ans=gh.shortestPath(src,dest);

        //List view
        ListView ls=findViewById(R.id.list_item);
        //recycler view
        Myadapter myadpt=new Myadapter(this,ans,change);
        ls.setAdapter(myadpt);

        //setting stations values
        TextView tx=(TextView) findViewById(R.id.stationsValue);
        tx.setText(ans.size()+"");


        for(int i=1;i<ans.size()-1;i++){
            int b=ans.get(i-1).length();
            char B=ans.get(i-1).charAt(b-3);

            int c=ans.get(i+1).length();
            char C=ans.get(i+1).charAt(c-3);

            if(B!=C&& (ans.get(i).equals("Ameerpet R10")||ans.get(i).equals("MG Bus Station R19")||ans.get(i).equals("JBS Parade Ground B35"))){
                change.add(ans.get(i));
            }
        }

        //setting interchange number
        TextView tx2=(TextView) findViewById(R.id.changesValue);
        tx2.setText(change.size()+"");


        InputStream is= getResources().openRawResource(R.raw.fare);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is,Charset.defaultCharset()));
        String s="";
        ArrayList<String> res=new ArrayList<>();

        try {
           int count=0;
           while(true){
               try {
                   if (!((s= reader.readLine())!=null)) break;
                   else{
                      if(count==src)
                       res.add(s);
                       count++;
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }




        }
        catch (Exception e){

        }
        String[] fare=res.get(0).split(",");

        TextView tx3=(TextView) findViewById(R.id.fairValue);
        tx3.setText(fare[dest]+"");

}


class Graph{

    //adj list
    ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
    HashMap<Integer,String> hs=new HashMap<>();

    Graph(){

        //RED LINE
        hs.put(0,"Miyapur R00");
        hs.put(1,"JNTU College R01");
        hs.put(2,"KPHB Colony R02");
        hs.put(3,"Kukatpally R03");
        hs.put(4,"Balanagar R04");
        hs.put(5,"Moosapet R05");
        hs.put(6,"Bharatnagar R06");
        hs.put(7,"Erragadda R07");
        hs.put(8,"ESI Hospital R08");
        hs.put(9,"SR Nagar R09");
        hs.put(10,"Ameerpet R10");
        hs.put(11,"Punjagutta R11");
        hs.put(12,"Irrum Manzil R12");
        hs.put(13,"Khairatabad R13");
        hs.put(14,"Lakdi Ka Pul R14");
        hs.put(15,"Assembly R15");
        hs.put(16,"Nampally R16");
        hs.put(17,"Gandhi Bhavan R17");
        hs.put(18,"Osmania Medical College R18");
        hs.put(19,"MG Bus Station R19");
        hs.put(20,"Malakpet R20");
        hs.put(21,"New Market R21");
        hs.put(22,"Musarambagh R22");
        hs.put(23,"Dilsukhnagar R23");
        hs.put(24,"Chaitanyapuri R24");
        hs.put(25,"Victoria Memorial R25");
        hs.put(26,"LB Nagar R26");

        //BLUE LINE
        hs.put(27,"Nagole B27");
        hs.put(28,"Uppal B28");
        hs.put(29,"Stadium B29");
        hs.put(30,"NGRI B30");
        hs.put(31,"Habsiguda B31");
        hs.put(32,"Tarnaka B32");
        hs.put(33,"Mettuguda B33");
        hs.put(34,"Secunderabad East B34");
        hs.put(35,"JBS Parade Ground B35");
        hs.put(36,"Paradise B36");
        hs.put(37,"Rasoolpura B37");
        hs.put(38,"Prakash Nagar B38");
        hs.put(39,"Begumpet B39");
        hs.put(40,"Madhura Nagar B40");
        hs.put(41,"Yusufguda B41");
        hs.put(42,"Jubilee Hills Road No 5  B42");
        hs.put(43,"Jubilee Hills Check Post B43");
        hs.put(44,"Peddamma Gudi B44");
        hs.put(45,"Madhapur B45");
        hs.put(46,"Durgam Cheruvu B46");
        hs.put(47,"Hitec City B47");
        hs.put(48,"Raidug B48");

        //GREEN LINE
        hs.put(49,"Secunderabad West G49");
        hs.put(50,"Gandhi Hospital G50");
        hs.put(51,"Musheerabad G51");
        hs.put(52,"RTC X Roads G52");
        hs.put(53,"Chikkadpally G53");
        hs.put(54,"Narayanaguda G54");
        hs.put(55,"Sultan Bazaar G55");
    }

    //TOTAL # STATIONS 56;
    //JUNCTIONS 3

    public void addEdge(ArrayList<ArrayList<Integer> > adj,int u, int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void buildGraph(){

        for(int i=0;i<56;i++){
            adj.add(new ArrayList<Integer>());
        }

        //RED non junction stations
        for(int i=0;i<26;i++){
            addEdge(adj,i,i+1);
        }

        //RED junction stations(considered above)

        //BULE non junction stations
        for(int i=27;i<=38;i++){
            addEdge(adj,i,i+1);
        }

        //BLUE JUNCTION AMEERPET
        addEdge(adj,39,10);
        addEdge(adj,10,40);

        //BULE non junction stations  after junction
        for(int i=40;i<48;i++){
            addEdge(adj,i,i+1);
        }

        //GREEN non junction stations
        for(int i=49;i<55;i++){
            addEdge(adj,i,i+1);
        }

        //GREEN JUNCTION 2
        addEdge(adj,55,19);
        addEdge(adj,35,49);
    }


    public ArrayList<String> shortestPath(int src, int dest) {
        //shortest path
        int visited[] = new int[56];
        int parent[] = new int[56];


        // BFS Implementation
        Queue<Integer> q = new LinkedList<>();
        parent[src]=-1;
        q.add(src);

        while(!q.isEmpty()) {
            int node = q.peek();
            q.remove();
            for(int it : adj.get(node)) {
                if(visited[it]==0) {
                    visited[it]=1;
                    parent[it]=node;
                    q.add(it);
                }
            }
        }

        //printing shortest path
        int curr=dest;
        ArrayList<String> res=new ArrayList<>();
        res.add(hs.get(curr));

        while(curr!=src) {
            System.out.println();
            res.add(hs.get(parent[curr]));
            curr = parent[curr];
        }

        Collections.reverse(res);
        return res;
    }

    //BFS algorithm
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
    
}}

package com.nexus.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import android.os.Bundle;

public class MapView extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        imageView=findViewById(R.id.imageView);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    void BellmanFord(Graph graph, int src)
    {
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];
 
        // Step 1: Initialize distances from src to all
        // other vertices as INFINITE
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
 
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at-most |V| - 1 edges
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE
                    && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }
 
        // Step 3: check for negative-weight cycles. The
        // above step guarantees shortest distances if graph
        // doesn't contain negative weight cycle. If we get
        // a shorter path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE
                && dist[u] + weight < dist[v]) {
                System.out.println(
                    "Graph contains negative weight cycle");
                return;
            }
        }
        printArr(dist, V);
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

package com.example.graph;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.graph.giorgio.customs.CustomView;
import com.example.graph.giorgio.customs.CustomViewEdge;
import com.example.graph.giorgio.graph.algorithms.search.MinPathDijkstra;
import com.example.graph.giorgio.graph.stuffs.Edge;
import com.example.graph.giorgio.graph.stuffs.Graph;
import com.example.graph.giorgio.graph.stuffs.Nodo;

import java.util.ArrayList;
import java.util.Random;

import static com.example.graph.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private LinearLayout.LayoutParams linearLayoutParams;
    private Graph<Nodo,String> grafo;
    private Canvas canvas;
    private Bitmap bitmap;
    private Bitmap operations;
    private ImageView imageView;
    private  BitmapDrawable ambp;
    private InvioDati invio;
    private Nodo[] nodes = new Nodo[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        imageView = (ImageView) findViewById(R.id.mappa);
        ambp = (BitmapDrawable) imageView.getDrawable();
        bitmap = ambp.getBitmap();

        linearLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        invio = (InvioDati) new InvioDati(this).execute("http://192.168.1.174/DemoWebBeacon/caricaGrafo.php?piano=55");

        /*grafo = new SparseGraph<Nodo,String>();
        nodes[0] = new Nodo(20,20);
        nodes[1] = new Nodo(20,400);
        nodes[2] = new Nodo(20,800);
        nodes[3] = new Nodo(200,300);
        nodes[4] = new Nodo(800,20);
        nodes[5] = new Nodo(500,200);
        nodes[6] = new Nodo(400,600);
        nodes[7] = new Nodo(700,800);
        nodes[8] = new Nodo(400,1100);
        nodes[9] = new Nodo(1000,300);
        for (int i =0 ; i<10; i++){
            grafo.addVertex(nodes[i]);
        }

        System.out.println("archi del grafo");
        grafo.addEdge(nodes[0], nodes[1],"");
        grafo.addEdge(nodes[0], nodes[4],"");
        grafo.addEdge(nodes[0], nodes[5],"");
        grafo.addEdge(nodes[1], nodes[2],"");
        grafo.addEdge(nodes[1], nodes[6],"");
        grafo.addEdge(nodes[5], nodes[3],"");
        grafo.addEdge(nodes[3], nodes[9],"");
        grafo.addEdge(nodes[6], nodes[8],"");
        grafo.addEdge(nodes[8], nodes[7],"");
        grafo.addEdge(nodes[3], nodes[6],"");

        ArrayList<Nodo> listaNodi = new ArrayList<>();
        for (int i = 0; i <10; i ++){
            listaNodi.add(nodes[i]);
        }*/


    }

    public void drawGraph(Graph<Nodo,String> graph){
        operations = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.RGB_565);

        canvas = new Canvas(operations);
        canvas.drawBitmap(bitmap,0,0,null);
        ArrayList<Nodo> nodi = graph.vertices();
        for (Nodo nod: nodi) {
            CustomView cv = new CustomView(this, nod);
            cv.draw(canvas);
            ArrayList<Edge<Nodo,String>> archi = graph.getAllEdges();
            for (Edge e : archi){

                CustomViewEdge cve = new CustomViewEdge(this, (Nodo) e.getIn(), (Nodo) e.getOut());
                cve.draw(canvas);
            }
        }
        imageView.setImageDrawable( new BitmapDrawable(getResources(),operations));

    }

    public void drawMinPath(ArrayList<Nodo> toDraw){
        operations = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.RGB_565);

        canvas = new Canvas(operations);
        canvas.drawBitmap(bitmap,0,0,null);

        Nodo start = toDraw.get(0);

        for(Nodo nod: toDraw) {
            CustomView cv = new CustomView(this, nod);
            cv.changeColor();
            cv.draw(canvas);
            System.out.println(start.toString()+"Secondo nodo: " +nod.toString());
            CustomViewEdge cve = new CustomViewEdge(this, start, nod );
            cve.draw(canvas);
            start = nod;
        }

        imageView.setImageDrawable( new BitmapDrawable(getResources(),operations));
    }

   public void newRandomPath(View v) {
        if(!grafo.vertices().isEmpty()) {
            ArrayList<Nodo> nodi = grafo.vertices();
            MinPathDijkstra<Nodo, String> dijkstra = new MinPathDijkstra<Nodo, String>();
            Random rn = new Random();
            int destination = rn.nextInt(nodi.size() - 1) + 1;
            grafo = invio.getGrafo();

            ArrayList<Nodo> result = dijkstra.minPath(grafo, nodi.get(0), nodi.get(destination));

            System.out.println(nodi.get(0)+"  " +nodi.get(destination));
            if (result != null) {
                Toast.makeText(this, "Cammino trovato!!", Toast.LENGTH_SHORT).show();
                drawMinPath(result);

            } else {
                Toast.makeText(this, "Cammino non trovato!!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Carica prima il grafo!", Toast.LENGTH_SHORT).show();
        }
    }
    public void caricaGrafo(View v){
        grafo = invio.getGrafo();
        if(!grafo.vertices().isEmpty()) {

            drawGraph(grafo);
        }
        else{
            Toast.makeText(this, "Nessun grafo è stato caricato", Toast.LENGTH_SHORT).show();
        }
    }
}
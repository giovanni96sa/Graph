package com.example.graph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.io.IOException;

import static com.example.graph.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements VertexAnalyser {
    private LinearLayout.LayoutParams linearLayoutParams;
    private SparseGraph<Nodo,String> grafo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        linearLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        grafo = new SparseGraph<Nodo,String>();
        //System.out.println("vertici del grafo");
        Nodo n1 = new Nodo(200,200);
        Nodo n2 = new Nodo(250,300);
        Nodo n3 = new Nodo(400,206);
        Nodo n4 = new Nodo(700,208);
        Nodo n5 = new Nodo(500,209);
        Nodo n6 = new Nodo(20,20);
        grafo.addVertex(n1);
        grafo.addVertex(n2);
        grafo.addVertex(n3);
        grafo.addVertex(n4);
        grafo.addVertex(n5);
        grafo.addVertex(n6);


        grafo.printVertex();
        System.out.println("archi del grafo");
        grafo.addEdge(n1, n2, "4->8");
        grafo.addEdge(n2, n3,"4->11");
        grafo.addEdge(n3, n4, "8->3");
        grafo.addEdge(n4, n5, "3->9");
        grafo.addEdge(n6, n1, "11->9");

        /*grafo.printNeighbors(n1);
        grafo.printNeighbors(3);
        grafo.printNeighbors(5);
        System.out.println("numero nodi: "+grafo.n);
        System.out.println("numero arch: "+grafo.m);
        try {
            grafo.toDot("grafo");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        GraphVisitImplements<Nodo, String> gv = new GraphVisitImplements<Nodo, String>();
        //VertexAnalyserImplements<Nodo> va = new VertexAnalyserImplements<Nodo>();
        System.out.println("visita in profondit�");
        gv.depthFirst(grafo, n1, this);
        System.out.println("visita in ampiezza");
        //gv.breadthFirst(grafo, n1, this);

    }

    @Override
    public void analyse(Object vertex) {
        Nodo n = (Nodo) vertex;
        //setContentView(new CustomView(this, n.getX(), n.getY()));
        addContentView(new CustomView(this, n.getX(), n.getY()), linearLayoutParams);
        addContentView(new CustomView2(this, n, grafo.neighbors(n)), linearLayoutParams);

        System.out.println(vertex);
    }
}

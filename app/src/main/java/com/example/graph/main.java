package com.example.graph;

import java.io.IOException;
import java.util.*;


public class main {
	public static void main(String[]args) throws IOException{
		Graph<Integer,String> grafo = new SparseGraph<Integer,String>();
		MinPathDijkstra<Integer,String> dijkstra = new MinPathDijkstra<Integer, String>();
		grafo.addVertex(4);
		grafo.addVertex(8);
		grafo.addVertex(11);
		grafo.addVertex(3);
		grafo.addVertex(9);
		
	
		grafo.addEdge(4, 8,2,"4->8");
		grafo.addEdge(4, 11,3,"4->11");
		grafo.addEdge(8, 3,11,"8->3");
		grafo.addEdge(3, 9,21,"3->9");
		grafo.addEdge(11, 9,2,"11->9");
		
	
		
		//grafo.toDot("dijkstra");
		
		
		ArrayList<Object> result = dijkstra.minPath(grafo, 4, 9);
		if(result!=null){
			System.out.println("cammino trovato");
			
			for(Object i: result) {
					System.out.println(i);
				} 
		}else System.out.println("non esiste un cammino valido");
		
		Graph<Integer,String> minGrafo = dijkstra.minPath(grafo, 4);
		
		GraphVisitImplements<Integer, String> gv = new GraphVisitImplements<Integer, String>();
		VertexAnalyserImplements<Integer> va = new VertexAnalyserImplements<Integer>();
		System.out.println("visita in profondit�");
		gv.depthFirst(minGrafo, 4, va);
		System.out.println("visita in ampiezza");
		gv.breadthFirst(grafo, 4, va);
		System.out.println("archi di minGraph");
		for(Integer in:minGrafo.vertices()){
				for( Integer out: minGrafo.neighbors(in)){
					System.out.println("iniziale: "+out+" finale: "+in);
				}
		}
	}
	

	
}

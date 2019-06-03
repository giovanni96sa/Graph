package com.example.graph.giorgio.graph.algorithms.search;

import com.example.graph.giorgio.graph.stuffs.Graph;
import com.example.graph.giorgio.graph.stuffs.SparseGraph;
import com.example.graph.giorgio.graph.priorityqueue.HeapPriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Dijkstra Algorithm
 * class wich implement the min path function in a graph
 * @author Giorgio
 * @param <V> generic type to represent the vertex of the graph
 * @param <E> generic type to represent the information about the edges of the graph
 */
public class MinPathDijkstra<V,E> {
	HeapPriorityQueue<V> q;
	HashMap<V,Double> dist;
	Map<V,V> fathers;
	ArrayList<Object> path;
	
	/**
	 * Dijkstra to find a min path from a source node s to a destination node d
	 * @param g graph in input
	 * @param s source node
	 * @param d destination node
	 * @return sequence of nodes that form the min path from s to d
	 */
	public ArrayList<V> minPath(Graph<V,E> g, V s, V d){
		
		q = new HeapPriorityQueue<V>(g.vertices().size());
		dist = new HashMap<V,Double>();
		fathers = new HashMap<V,V>();
		ArrayList<V> minPath = new ArrayList<V>();
		
		ArrayList<V> vertex = g.vertices();
		for(V x:vertex){
			dist.put(x,Double.POSITIVE_INFINITY);
			fathers.put(x,null);
		}
		fathers.put(s, s);
		dist.put(s, 0.0);
		for(V x:vertex){
			q.insert(x, dist.get(x));
		}

		while(!q.isEmpty()){
			V u = q.extractfirst();
			for(V v:g.neighbors(u)){
				double weight = g.getWeight(u, v);
					double distanceThrought = dist.get(u)+weight;
					if(distanceThrought<dist.get(v)){
						dist.put(v, distanceThrought);
						fathers.put(v, u);
						q.decreasePriority(v, dist.get(v));
					}
			}
		}
		V step;
		step = d;
		if(fathers.get(d)==null)
			return null;
		minPath.add(step);
		while(fathers.get(step)!=null){
			if(step.equals(s)) break;
			step = fathers.get(step);
			minPath.add(step);
		}
		Collections.reverse(minPath);
		return minPath;
	}
	
	/**
	 * Dijkstra to find a min path from a source node s to the others nodes of an input graph
	 * @param g graph in input
	 * @param s source node
	 * @return sequence of nodes that form the min path from d to s
	 */
	public Graph<V,E> minPath(Graph<V,E> g,V s){
		q = new HeapPriorityQueue<V>(g.vertices().size());
		dist = new HashMap<V,Double>();
		fathers = new HashMap<V,V>();
		path = new ArrayList<Object>();
		Graph<V,E> minPath = new SparseGraph<V,E>();
		
		ArrayList<V> vertex = g.vertices();
		for(V x:vertex){
			dist.put(x,Double.POSITIVE_INFINITY);
			fathers.put(x,null);
		}
		fathers.put(s, s);
		dist.put(s, 0.0);
		for(V x:vertex){
			q.insert(x, dist.get(x));
		}

		while(!q.isEmpty()){
			V u = q.extractfirst();
			for(V v:g.neighbors(u)){
				double weight = g.getWeight(u, v);
					double distanceThrought = dist.get(u)+weight;//distanza del padre + la sua distanza
					if(distanceThrought<dist.get(v)){//se il costo � inferiore al nodo attuale
						dist.put(v, distanceThrought);
						if(!(minPath.vertices().contains(v))) minPath.addVertex(v);
						if(!(minPath.vertices().contains(u))) minPath.addVertex(u);
						E info = null;
						minPath.addEdge(v, u,info);
						fathers.put(v, u);
						q.decreasePriority(v, dist.get(v));
					}
			}
		}	
		return minPath;
	}
	
	public ArrayList<V> nodiPozzo(Graph<V,E> g){
		ArrayList<V> nodiPozzo = new ArrayList<V>();
		for(V v:g.vertices()){
			if(g.neighbors(v)==null) nodiPozzo.add(v);
		}
		return nodiPozzo;
	}
}


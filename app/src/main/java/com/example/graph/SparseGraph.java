package com.example.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * the class that implements the interface Graph
 *  
 * @author Giorgio
 *
 * @param <V> generic type to represent the vertex of the graph
 * @param <E> generic type to represent the information about the edges of the graph
 */
public class SparseGraph<V,E> implements Graph<V,E>{
	ArrayList<V> nodes;	
	HashMap<V,ArrayList<E>> edges;
	int n;
	int m;
	
	/**
	 * constructor that inizialize:
	 *  - ArrayList of vertices (nodes)
	 *  - l'HashMap of the edges
	 *  - the vertex number to 0
	 *  - the vertices number to 0
	 */
	public SparseGraph(){
		nodes = new ArrayList<V>();
		edges = new HashMap<V,ArrayList<E>>();
		n = 0;
		m = 0;
	}
	
	/**
	 * add a vertex v to the graph
	 * the method add the V element to the ArrayList, create a list of neighbors using the HashMap and increment the number of vertex in the graph.
	 * @param vertex vertex to add in the graph
	 * @return boolean to check if the insert was successful or not 
	 */
	@Override
	public boolean addVertex(V vertex) {
		if(!nodes.contains(vertex)){
			nodes.add(vertex);
			n++;
			ArrayList<E> neighbors = new ArrayList<E>();
			edges.put(vertex,(ArrayList<E>) neighbors);
			return true;
		}
		
		return false;
	}

	/**
	 * add an oriented edge.
	 * the method check if the two vertices are present in the graph
	 * if they are not present the method add them and the relative edge
	 * add him to the neighbors of v1
	 * @param v1 the source vertex
	 * @param v2 the vertex adjacent to v1
	 * @param info information about the edge, it can be null
	 * @exception IllegalArgumentException if one of both the edges are null.
	 * @return true if the edge is successful added, false if not
	 */
	@Override
	public boolean addEdge(V v1, V v2, E info) {
		if(v1 == null || v2 == null ) throw new IllegalArgumentException("The node can't be null");
		if(nodes.contains(v1)){
			if(!nodes.contains(v1)) addVertex(v1);
			if(!nodes.contains(v2)) addVertex(v2);
			Edge<V,E> a = new Edge<V, E>(info,v1,v2);
			ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(v1);
			neighbors.add(a);
			m++;
			return true;
		}
		return false;
	}

	/**
	 * add an oriented edge with relative weight.
	 * the method check if the two vertices are present in the graph
	 * if they are not present the method add them and the relative edge
	 * add him to the neighbors of v1
	 * @param v1 the source vertex
	 * @param v2 the vertex adjacent to v1
	 * @param weight the weight of the edge
	 * @param info information about the edge, it can be null
	 * @exception IllegalArgumentException if one of both the edges are null.
	 * @return true if the edge is successful added, false if not
	 */
	@Override 
	public boolean addEdge(V v1, V v2, double weight, E info) {
		if(v1 == null || v2 == null ) throw new IllegalArgumentException("il nodo non pu� essere null");
		if(nodes.contains(v1)){
			if(!nodes.contains(v1)) addVertex(v1);
			if(!nodes.contains(v2)) addVertex(v2);
			Edge<V,E> a = new Edge<V, E>(info,v1,v2,weight);
		
			ArrayList<Edge<V,E>> neighbors = (ArrayList<Edge<V,E>>)edges.get(v1);
			neighbors.add(a);
			m++;
			return true;
		}
		return false;
	}
	
	/**
	 * add a NOT oriented edge.
	 * the method create two edges: v1->v2 and v2->v1
	 * use the method addEdge
	 * the edge will be added to the adjacent list of v1 and v2
	 * @param v1 vertex 
	 * @param v2 vertex adjacent to v1
	 * @param info information about the edge, it can be null
	 * @return boolean, true if the edge is successful added in both the adjacent list, false if they are present yet.
	 */
	@Override
	public boolean addUndirectedEdge(V v1, V v2, E info) {
		if(addEdge(v1,v2,info) || addEdge(v2,v1,info)) return true;
		else return false;
	}


	/**
	 * add a NOT oriented edge with relative weight.
	 * the method create two edges: v1->v2 and v2->v1
	 * use the method addEdge
	 * the edge will be added to the adjacent list of v1 and v2
	 * @param v1 vertex 
	 * @param v2 vertex adjacent to v1
	 * @param weight the weight of the edge
	 * @param info information about the edge, it can be null
	 * @return boolean, true if the edge is successful added in both the adjacent list, false if they are present yet.
	 */
	@Override
	public boolean addUndirectedEdge(V v1, V v2, double weight, E info) {
		addEdge(v1,v2,weight,info);
		addEdge(v2,v1,weight,info);
		return true;
	}
	/**
	 *check if the vertex is present in the graph
	 * @param vertex to check
	 * @return boolean true if it is present not otherwise
	 */
	@Override
	public boolean hasVertex(V vertex) {
		if(nodes.contains(vertex))
			return true;
		return false;
	}
/**
 * check if the Edge v1 -> v2 is present
 * the method check for first if the vertices are both present, than check in the adjacent list if they are both present.
 * @param v1 source node
 * @param v2 destination node
 * @return boolean, true if the Edge v1 -> v2 is in the graph, otherwise
 */
	@Override
	public boolean hasEdge(V v1, V v2) {
		if(nodes.contains(v1)){
			if(nodes.contains(v2)){
				ArrayList<Edge<V,E>> neighbors = (ArrayList<Edge<V,E>>)edges.get(v1);
				if(neighbors.contains(new Edge(v1,v2)));
				return true;
			}
		}
		return false;
	}

	/**
	 * return the weight of an Edge source -> dest
	 * in case of an Edge without weight it returns 1
	 * @param source source node of the edge
	 * @param dest destination node of the edge
	 * @return weight of the edge
	 */
	@Override
	public double getWeight(V source, V dest) {
		return getEdge(source,dest).getWeight();
	}

	/**
	 * return the list of vertices
	 * @return ArrayList of all the inserted vertices
	 */
	@Override
	public ArrayList<V> vertices() {
		return nodes;
	}

	/**
	 * return the list of the adjacent nodes of a selected one.
	 * @param vertex selected vertex
	 * @return the list of the adjacent nodes of the vertex, null if he havent .
	 */
	@Override
	public ArrayList<V> neighbors(V vertex) {
	if(nodes.contains(vertex)){
		ArrayList<V> neighbors = new ArrayList<V>();
		ArrayList<Edge> arch = (ArrayList<Edge>)edges.get(vertex);
	
		for(Edge a: arch)
		neighbors.add((V) a.getOut());
		return neighbors;
	}
	return null;
	}
	
	/**
	 * return the Edge source -> dest if it is present in the graph.
	 * the method search into the edges list of the node sourde if exist an edge with source as source and dest as destination
	 * @param source source node of the edge source->dest
	 * @param dest destination of the Edge source -> dest
	 * @return true if the is present Edge source --> dest, null otherwise
	 */
	public Edge getEdge(V source, V dest){
		ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(source);
		Edge find = new Edge(source,dest);
		for(Edge<V,E> a : neighbors){
			if((a.getOut().equals(find.getOut()))&&(a.getIn().equals(find.getIn())))
				return a;
		}
		return null;
	}
	/**
	 * print the list of nodes in the console
	 */
	public void printVertex(){
		for(V i : nodes)
			System.out.println(i);
	}
	/**
	 *print the adjacent list of a certain vertex passed as parameter
	 * @param vertex 
	 */
	public void printNeighbors(V vertex){
		ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(vertex);
		for(Edge<V,E> a : neighbors)
			System.out.println("initial: "+a.getIn()+" final: "+a.getOut()+" info: "+a.getInfo());
	}
	
	public void toDot(String GraphName) throws IOException{
		String Graph = GraphName+".dot";
	  FileWriter outFile = new FileWriter(Graph, false);
	  PrintWriter out = new PrintWriter(outFile);
	  out.println("digraph "+GraphName+"{");
	  for(V i : nodes){
	  	ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(i);
			for(Edge<V,E> a : neighbors){
				out.println(a.getIn()+" -> "+a.getOut()+";");
			}
	  }
	  out.println("}");
	  out.close();
	}
}

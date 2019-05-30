package com.example.graph;
/**
 * interface
 * @author Giorgio
 *
 * @param <V> generic type to represent the vertex of the graph
 */
public interface VertexAnalyser<V> {
	/**
	 * method to analyze a vertex
	 * @param vertex vertex to analyze
	 */
	void analyse(V vertex);
}
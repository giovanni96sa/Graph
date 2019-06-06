package com.example.graph.giorgio.graph.stuffs;

import java.io.IOException;
import java.util.ArrayList;

/*
 * @author giorgio
 */

public class DenseGraph implements Graph{

	@Override
	public boolean addVertex(Object vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(Object v1, Object v2, Object info) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(Object v1, Object v2, double weight, Object info) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUndirectedEdge(Object v1, Object v2, Object info) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUndirectedEdge(Object v1, Object v2, double weight,
			Object info) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasVertex(Object vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasEdge(Object v1, Object v2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getWeight(Object source, Object dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Edge> getAllEdges() {
		return null;
	}

	@Override
	public ArrayList neighbors(Object vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toDot(String string) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
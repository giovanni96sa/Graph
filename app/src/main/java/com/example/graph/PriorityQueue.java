package com.example.graph;

public interface PriorityQueue<V> {
	boolean isEmpty();
	void insert(V element, double priority);
	V extractfirst();
	V getFirst();
	boolean decreasePriority(V element, double newPriority);
	void remove(V element);
}

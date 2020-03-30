package com.mj.set;

import com.mj.map.Map;
import com.mj.map.TreeMap;

/**
 * 利用TreeMap实现TreeSet
 */
public class TreeSet<E> implements Set<E> {

	private Map<E, Object> map = new TreeMap<>();
	
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public void claer() {
		map.clear();
	}

	public boolean contains(E element) {
		return map.containsKey(element);
	}

	public void add(E element) {
		map.put(element, null);
	}

	public void remove(E element) {
		map.remove(element);
	}

	public void traversal(Visitor<E> visitor) {
		map.traversal(new Map.Visitor<E, Object>() {
			public boolean visit(E key, Object value) {
				return visitor.visit(key);
			}
		});
	}

}

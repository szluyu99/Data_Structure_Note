package com.mj;

import java.util.Collection;
import java.util.Comparator;

/**
 * 二叉堆（最小堆）
 * @author MJ Lee
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class MinHeap<E> {
	private int size;
	private Comparator<E> comparator;
	private int compare(E e1, E e2) {
		return comparator != null ? comparator.compare(e1, e2) 
				: ((Comparable<E>)e1).compareTo(e2);
	}
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public MinHeap(Collection<E> elements, Comparator<E> comparator) {
		this.comparator = comparator;
		
		size = elements == null ? 0 : elements.size();
		if (size == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		} else {
			int capacity = Math.max(size, DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			int i = 0;
			for (E element : elements) {
				this.elements[i++] = element;
			}
			heapify();
		}
	}
	
	public MinHeap(E[] elements, Comparator<E> comparator)  {
		this.comparator = comparator;
		
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		} else {
			size = elements.length;
			int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			heapify();
		}
	}
	
	public MinHeap(Collection<E> elements)  {
		this(elements, null);
	}
	
	public MinHeap(E[] elements)  {
		this(elements, null);
	}
	
	public MinHeap(Comparator<E> comparator) {
		this.comparator = comparator;
		this.elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public MinHeap() {
		this.elements = (E[]) new Object[DEFAULT_CAPACITY];
	}

	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addAll(Collection<E> elements) {
		if (elements == null) return;
		for (E element : elements) {
			add(element);
		}
	}
	
	public void addAll(E[] elements) {
		if (elements == null) return;
		for (E element : elements) {
			add(element);
		}
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		elements[size++] = element;
		siftUp(size - 1);
	}

	public E get() {
		emptyCheck();
		return elements[0];
	}

	public E remove() {
		emptyCheck();
		
		int lastIndex = --size;
		E root = elements[0];
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
		
		siftDown(0);
		return root;
	}

	public E replace(E element) {
		elementNotNullCheck(element);
		
		E root = null;
		if (size == 0) {
			elements[0] = element;
			size++;
		} else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}
	
	/**
	 * 批量建堆
	 */
	protected void heapify() {
		// 自下而上的下滤
		for (int i = (size >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
	}
	
	/**
	 * 让index位置的元素下滤
	 * @param index
	 */
	private void siftDown(int index) {
		E element = elements[index];
		int half = size >> 1;
		// 第一个叶子节点的索引 == 非叶子节点的数量
		// index < 第一个叶子节点的索引
		// 必须保证index位置是非叶子节点
		while (index < half) { 
			// index的节点有2种情况
			// 1.只有左子节点
			// 2.同时有左右子节点
			
			// 默认为左子节点跟它进行比较
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			
			// 右子节点
			int rightIndex = childIndex + 1;
			
			// 选出左右子节点最小的那个
			if (rightIndex < size && compare(elements[rightIndex], child) < 0) {
				child = elements[childIndex = rightIndex];
			}
			
			if (compare(element, child) <= 0) break;

			// 将子节点存放到index位置
			elements[index] = child;
			// 重新设置index
			index = childIndex;
		}
		elements[index] = element;
	}
	
	/**
	 * 让index位置的元素上滤
	 * @param index
	 */
	private void siftUp(int index) {
		E element = elements[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parent = elements[parentIndex];
			if (compare(element, parent) >= 0) break;
			
			// 将父元素存储在index位置
			elements[index] = parent;
			
			// 重新赋值index
			index = parentIndex;
		}
		elements[index] = element;
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}

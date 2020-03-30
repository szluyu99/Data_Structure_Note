package com.mj.list;

/**
 * 双向链表
 * @author yusael
 * @param <E>
 */
public class LinkedList<E> extends AbstractList<E> {

	private Node<E> first;
	private Node<E> last;

	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;

		public Node(Node<E> prev, E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			if(prev != null){
				sb.append(prev.element);
			}else{
				sb.append("null");
			}
			sb.append("_").append(element).append("_");
			if(next != null){
				sb.append(next.element);
			}else{
				sb.append("null");
			}
			
			return sb.toString();
		}
	}
	
	/**
	 * 根据索引找到节点
	 * 
	 * @param index
	 * @return
	 */
	private Node<E> node(int index) {
		rangeCheck(index);
		
		if (index < (size >> 1)) { // 索引小于一半从前往后找
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else { // 索引大于一半从后往前找
			Node<E> node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			return node;
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		E old = node(index).element;
		node(index).element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);

		if (index == size) { // 往最后面添加元素
			Node<E> oldLasg = last;
			last = new Node<>(oldLasg, element, null);
			if (oldLasg == null) { // 这是链表添加的第一个元素
				first = last;
			} else {
				oldLasg.next = last;
			}
		} else { // 正常添加元素
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(prev, element, next);
			next.prev = node;
			if (prev == null) { // index==0
				first = node;
			} else {
				prev.next = node;
			}
		}
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		Node<E> node = node(index);
		Node<E> prev = node.prev;
		Node<E> next = node.next;
		
		if (prev == null) { // index == 0
			first = next;
		} else {
			prev.next = next;
		}
		
		if (next == null) { // index == size - 1
			last = prev;
		} else {
			next.prev = prev;
		}
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == element) return i;
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element.equals(element)) return i;
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[size=").append(size).append(", ");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(node);
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}

}
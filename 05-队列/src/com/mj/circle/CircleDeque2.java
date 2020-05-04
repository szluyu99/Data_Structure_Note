package com.mj.circle;

/**
 * 循环双端队列
 * @author yusael
 */
@SuppressWarnings("unchecked")
public class CircleDeque2<E> {
	private int front; // 队头指针
	private int size; // 元素数量
	private E elements[]; // 元素
	public static final int DEFAULT_CAPACITY = 10; // 初始容量

	public CircleDeque2() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 * 元素的数量
	 */
	public int size() {
		return size;
	}

	/**
	 * 是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 清空
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[index(i)] = null;
		}
		front = 0;
		size = 0;
	}

	/**
	 * 从队尾入队
	 */
	public void enQueueRear(E element) {
		// 头 1 r(2) null null null f(6) 7 8 9 尾
		ensureCapacity(size + 1);

		elements[index(size)] = element;
		size++;
	}

	/**
	 * 从队头入队
	 */
	public void enQueueFront(E element) {
		ensureCapacity(size + 1);

		front = index(-1);
		elements[front] = element;
		size++;
	}

	/**
	 * 从队尾出队
	 */
	public E deQueueRear() {
		int rearIndex = index(size - 1);
		E rear = elements[rearIndex];
		elements[rearIndex] = null;
		size--;
		return rear;
	}

	/**
	 * 从队头出队
	 */
	// 头 1 r(2) null null f(5) 6 7 8 9 尾
	public E deQueueFront() {
		E frontElement = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return frontElement;
	}

	/**
	 * 获取队列的头元素
	 */
	public E front() {
		return elements[front];
	}

	/**
	 * 获取队列的尾元素
	 */
	public E rear() {
		return elements[index(size - 1)];
	}

	// 索引封装映射
	private int index(int index) {
		index += front;
		if (index < 0) { // index 为负数
			return index + elements.length;
		}
		// index 为正数
		return index % elements.length;
	}

	// 数组扩容
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity)
			return;

		int newCapacity = oldCapacity + (oldCapacity >> 1); // 扩容为1.5倍
		E newElements[] = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[index(i)];
		}
		elements = newElements;
		front = 0; // 重置front
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capcacity=").append(elements.length).append(" size=").append(size).append(" front=")
				.append(front).append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				string.append(", ");
			}

			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
	
}

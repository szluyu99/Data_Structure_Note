package com.mj.tree;

import java.util.Comparator;
/**
 * 二叉搜索树
 */
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	/**
	 * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) { // 比较器不为空，则使用比较器
			return comparator.compare(e1, e2);
		}
		// 没有传入比较器，则要求元素本身必须实现Comparable接口
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	/**
	 * 辅助方法
	 * 通过元素值获取对应节点
	 */
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) return node;
			if (cmp > 0) {
				node = node.right;
			} else { // cmp < 0
				node = node.left;
			}
		}
		return null;
	}
	
	/**
	 * 添加节点
	 */
	public void add(E element) {
		elementNotNullCheck(element); // 传入元素不能为空
		
		// 添加第一个节点
		if (root == null) {
			root = createNode(element, null);
			size++;

			// 新添加节点之后的处理
			// BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
			afterAdd(root); 
			return;
		}
		
		// 添加的不是第一个节点
		// 找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		do {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // 相等
				// 相等时的操作按需求来定，这里选择了覆盖
				node.element = element;
				return;
			}
		} while (node != null);

		// 通过上面的while循环找到了要插入节点的父节点
		// 看看插入到父节点的哪个位置
		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
		
		// 新添加节点之后的处理
		// BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
		afterAdd(newNode);
	}
	
	/**
	 * 删除节点
	 */
	public void remove(E element) {
		remove(node(element));
	}
	private void remove(Node<E> node) {
		if (node == null) return;
		
		size--;
		
		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到后继节点
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			// 删除后继节点
			// 这里是因为后面必然会删除node节点
			// 所以直接将后继节点赋给node,在后面将它删除
			node = s;
		}
		
		// 删除node节点（node的度必然是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { // node是度为1的节点
			// 核心：用子节点替代原节点的位置
			// 更改parent
			replacement.parent = node.parent;
			// 更改parent的left、right的指向
			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}
			
			// 删除节点之后的处理，BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
			afterRemove(replacement);
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
			
			// 删除节点之后的处理，BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
			afterRemove(node);
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) { // 是左子树
				node.parent.left = null;
			} else { // node == node.parent.right // 是右子树
				node.parent.right = null;
			}
			
			// 删除节点之后的处理，BST中无需处理，为 AVL树 和 B树提供可覆盖的方法
			afterRemove(node);
		}
	}
	
	/**
	 * 是否包含某元素
	 */
	public boolean contains(E element) {
		return node(element) != null;
	}
	/**
	 * 添加node之后的调整
	 * @param node 新添加的节点
	 */
	protected void afterAdd(Node<E> node) { }
	
	/**
	 * 删除node之后的调整
	 * @param node 被删除的节点 或者 用以取代被删除节点的子节点（当被删除节点的度为1）
	 */
	protected void afterRemove(Node<E> node) { }
	
	/**
	 * 辅助方法
	 * 检测传入元素值是否为null
	 * 为null则抛出异常
	 * @param element
	 */
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}

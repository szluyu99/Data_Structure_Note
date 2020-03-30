package com.mj.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.mj.printer.BinaryTreeInfo;
/**
 * 二叉树类
 */
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
	protected int size; 	// 节点个数量
	protected Node<E> root; // 节点
	/**
	 * 访问器
	 * 用来控制遍历访问元素时的行为与如何停止
	 */
	public static abstract class Visitor<E> {
		boolean stop;
		abstract boolean visit(E element); // 如果返回true，就代表停止遍历
	}
	/**
	 * 节点类
	 */
	protected static class Node<E> {
		E element;		// 元素值
		Node<E> left;	// 左子节点
		Node<E> right;	// 右子节点
		Node<E> parent;	// 父亲节点
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() { // 是否为叶子结点
			return left == null && right == null;
		}
		
		public boolean hasTwoChildren() { // 是否有两个子节点
			return left != null && right != null;
		}
		
		public boolean isLeftChild() { // 是否为左节点
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() { // 是否为右节点
			return parent != null && this == parent.right;
		}
		
		public Node<E> sibling() { // 返回兄弟节点
			if (isLeftChild()) {
				return parent.right;
			}
			
			if (isRightChild()) {
				return parent.left;
			}
			return null;
		}
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * 先序遍历（递归）
	 */
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		
		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}

	/**
	 * 中序遍历（递归）
	 */
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		
		inorder(node.left, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	
	/**
	 * 后序遍历（递归）
	 */
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) return;
		
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}
	
	/**
	 * 层次遍历（队列实现）
	 */
	public void levelOrder(Visitor<E> visitor) {
		if (root == null || visitor == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (visitor.visit(node.element)) return;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	/**
	 * 是否为完全二叉树
	 */
	public boolean isComplete() {
		if (root == null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) return false;

			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) {
				// 能进来这里说明 node.left == null
				// 同时 node.right != null 必然不是完全二叉树
				return false;
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			} else { // 后面遍历的节点都必须是叶子节点
				// 能进来这里说明 node.right == null
				// 无论 node.left == null 还是 node.left != null
				// 都需要从这以后的节点是叶子节点
				leaf = true;
			}
		}
		
		return true;
	}
	/**
	 * 求树的高度（非递归）
	 */
	public int height() {
		if (root == null) return 0;
		
		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}

			if (levelSize == 0) { // 意味着即将要访问下一层
				levelSize = queue.size(); // 核心
				height++;
			}
		}
		
		return height;
	}
	
	/**
	 * 求树的高度（递归）
	 */
	public int height2() {
		return height(root);
	}
	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	/**
	 * 创造节点
	 * AVL树 与 B数 的节点各自有其特性
	 * 因此在 BinaryTree 中提供一个方法让他们去覆盖
	 */
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	/**
	 * 前驱节点：中序遍历中的前一个节点
	 */
	protected Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（left.right.right.right....）
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}

		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}
	
	/**
	 * 后继节点：中序遍历中的后一个节点
	 * 写法与前驱节点正好相反
	 */
	protected Node<E> successor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（right.left.left.left....）
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}

		return node.parent;
	}
	/**
	 * BinaryTreeInfo 工具类
	 */
	@Override
	public Object root() {
		return root;
	}
	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}
	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}
	@Override
	public Object string(Object node) {
		return node;
	}
}

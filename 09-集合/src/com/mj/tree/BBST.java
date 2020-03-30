package com.mj.tree;

import java.util.Comparator;
/**
 * 平衡二叉树搜索树
 */
public class BBST<E> extends BST<E> {
	public BBST() {
		this(null);
	}
	public BBST(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * 左旋
	 */
	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}
	
	/**
	 * 右旋
	 */
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}
	
	/**
	 * 公共代码：不管是左旋、右旋，都要执行的
	 * @param grand 失衡节点
	 * @param parenet 失衡节点的tallerChild
	 * @param child g和p需要交换的子树（本来是p的子树，后来会变成g的子树）
	 */
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// 让parent称为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) { // grand是左子树
			grand.parent.left = parent;
		} else if (grand.isRightChild()) { // grand是右子树
			grand.parent.right = parent;
		} else { // grand是root节点
			root = parent;
		}
		
		// 更新child的parent
		if (child != null) {
			child.parent = grand;
		}
		
		// 更新grand的parent
		grand.parent = parent;
	}
	
	/**
	 * 统一处理的旋转代码（神奇）
	 */
	protected void rotate(
			Node<E> r, // 子树的根节点
			Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f) {
		// 让d成为这棵子树的根节点
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}
		
		//b-c
		b.right = c;
		if (c != null) {
			c.parent = b;
		}
		
		// e-f
		f.left = e;
		if (e != null) {
			e.parent = f;
		}
		
		// b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
	}
}

	package com.mj.tree;
	
	import java.util.Comparator;
	/**
	 * AVL树
	 */
	public class AVLTree<E> extends BBST<E> {
		public AVLTree() {
			this(null);
		}
		public AVLTree(Comparator<E> comparator) {
			super(comparator);
		}
		
		private static class AVLNode<E> extends Node<E> {
			int height = 1; // AVL树中的节点，需要高度这个属性来计算平衡因子
			
			public AVLNode(E element, Node<E> parent) {
				super(element, parent);
			}
			// 平衡因子
			public int balanceFactor() {
				int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
				int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
				return leftHeight - rightHeight;
			}
			// 更新高度
			public void updateHeight() {
				int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
				int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
				height = 1 + Math.max(leftHeight, rightHeight);
			}
			// 获取左右子树中，高度最长的节点
			public Node<E> tallerChild() {
				int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
				int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
				if (leftHeight > rightHeight) return left;
				if (leftHeight < rightHeight) return right;
				return isLeftChild() ? left : right;
			}
			@Override
			public String toString() {
				String parentString = "null";
				if (parent != null) {
					parentString = parent.element.toString();
				}
				return element + "_p(" + parentString + ")_h(" + height + ")";
			}
		}
		/**
		 * 是否平衡
		 * 平衡因子的绝对值 <= 1 则平衡
		 */
		private boolean isBalanced(Node<E> node) {
			return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
		}
		/**
		 * 更新高度
		 */
		private void updateHeight(Node<E> node) {
			((AVLNode<E>)node).updateHeight();
		}
		/**
		 * 覆盖了BST中的afterAdd方法
		 * BST中add以后无需调整，而AVL树需要恢复平衡和更新高度
		 */
		@Override
		protected void afterAdd(Node<E> node) {
			while ((node = node.parent) != null) { // 从当前节点探测根结点，逐个调整平衡
				if (isBalanced(node)) { // 已经平衡
					// 更新高度
					updateHeight(node);
				} else { // 不平衡
					// 恢复平衡
					rebalance(node);
					// 只需要恢复了最下面那个不平衡的节点，则整棵树恢复平衡，无需继续往上探测
					break;
				}
			}
		}
		
		@Override
		protected void afterRemove(Node<E> node) {
			while ((node = node.parent) != null) { // 从当前节点探测根结点，逐个调整平衡
				if (isBalanced(node)) { // 已经平衡
					// 更新高度
					updateHeight(node);
				} else { // 不平衡
					// 恢复平衡
					rebalance(node);
				}
			}
		}
		
		/**
		 * AVL树的节点有其特性(height)，与BinaryTree.java中的节点不同
		 */
		@Override
		protected Node<E> createNode(E element, Node<E> parent) {
			return new AVLNode<>(element, parent);
		}
		
		/**
		 * 恢复平衡
		 * @param grand 高度最低的那个不平衡节点
		 */
		/**
		 * 恢复平衡
		 * 利用“统一旋转”的代码
		 * @param grand 高度最低的那个不平衡节点
		 */
		private void rebalance(Node<E> grand) { 
			Node<E> parent = ((AVLNode<E>)grand).tallerChild();
			Node<E> node = ((AVLNode<E>)parent).tallerChild();
			if (parent.isLeftChild()) { // L
				if (node.isLeftChild()) { // LL
					rotate(grand, node, node.right, parent, parent.right, grand);
				} else { // LR
					rotate(grand, parent, node.left, node, node.right, grand);
				}
			} else { // R
				if (node.isLeftChild()) { // RL
					rotate(grand, grand, node.left, node, node.right, parent);
				} else { // RR
					rotate(grand, grand, parent.left, parent, node.left, node);
				}
			}
		}
		/**
		 * 恢复平衡
		 * “左旋”与“右旋”分别进行
		 * @param grand 高度最低的那个不平衡节点
		 */
		@SuppressWarnings("unused")
		private void rebalance2(Node<E> grand) {
			Node<E> parent = ((AVLNode<E>)grand).tallerChild();
			Node<E> node = ((AVLNode<E>)parent).tallerChild();
			if (parent.isLeftChild()) { // L
				if (node.isLeftChild()) { // LL
					rotateRight(grand);
				} else { // LR
					rotateLeft(parent);
					rotateRight(grand);
				}
			} else { // R
				if (node.isLeftChild()) { // RL
					rotateRight(parent);
					rotateLeft(grand);
				} else { // RR
					rotateLeft(grand);
				}
			}
		}
		
		@Override
		protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
			super.afterRotate(grand, parent, child);
			
			// 更新高度
			updateHeight(grand);
			updateHeight(parent);
		}
		
		@Override
		protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
			super.rotate(r, b, c, d, e, f);
			
			// 更新高度
			updateHeight(b);
			updateHeight(f);
			updateHeight(d);
		}
		
	}

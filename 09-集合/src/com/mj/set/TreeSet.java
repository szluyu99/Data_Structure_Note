package com.mj.set;

import com.mj.tree.BinaryTree;
import com.mj.tree.RBTree;
/**
 * 红黑树实现集合
 */
public class TreeSet<E> implements Set<E>{
	private RBTree<E> tree = new RBTree<>();
	
	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public void claer() {
		tree.clear();
	}

	@Override
	public boolean contains(E element) {
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		tree.add(element); // 红黑树自带去重
	}

	@Override
	public void remove(E element) {
		tree.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		tree.inorder(new BinaryTree.Visitor<E>() {
			@Override
			public boolean visit(E element) {
				return visitor.visit(element);
			}
		});
	}

}

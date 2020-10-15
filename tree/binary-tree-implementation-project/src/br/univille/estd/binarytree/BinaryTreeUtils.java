package br.univille.estd.binarytree;

public class BinaryTreeUtils {
	public static <E> String toStringPreOrder(LinkedBinaryTree<E> T, BTPosition<E> v) {
		String text = v.getElement().toString();
		if(T.hasLeft(v)) {
			text += ", " + toStringPreOrder(T, v.getLeft());
		}
		if(T.hasRight(v)) {
			text += ", " + toStringPreOrder(T, v.getRight());
		}
		return text;
	}
	
	public static <E> String toStringPostOrder(LinkedBinaryTree<E> T, BTPosition<E> v) {
		String text = "";
		if(T.hasLeft(v)) {
			text += toStringPostOrder(T, v.getLeft()) + ", ";
		}
		if(T.hasRight(v)) {
			text += toStringPostOrder(T, v.getRight()) + ", ";
		}
		text += v.getElement().toString();
		return text;
	}
	
	public static <E> String toStringInOrder(LinkedBinaryTree<E> T, BTPosition<E> v) {
		String text = "";
		if(T.hasLeft(v)) {
			text += toStringInOrder(T, v.getLeft()) + ", ";
		}
		text += v.getElement().toString();
		if(T.hasRight(v)) {
			text += ", " + toStringInOrder(T, v.getRight());
		}
		return text;
	}
}

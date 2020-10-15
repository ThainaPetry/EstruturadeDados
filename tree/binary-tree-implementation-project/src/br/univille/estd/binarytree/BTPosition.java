package br.univille.estd.binarytree;

public class BTPosition<E> {
	
	private E element;
	private BTPosition<E> parent;
	private BTPosition<E> left;
	private BTPosition<E> right;
	
	/**
	 * Construtor padrao
	 */
	public BTPosition() {}
	
	/**
	 * Construtor padrao que define o elemento desta posicao
	 */
	public BTPosition(E element) {
		this(element,null,null,null);
	}
	
	/**
	 * Construtor padrao que define o elemento e o pai desta posicao
	 */
	public BTPosition(E element, BTPosition<E> parent) {
		this(element,parent,null,null);
	}
	
	/**
	 * Construtor padrao que define o elemento, pai, filho da esquerda e o filho da direita desta posicao
	 */
	public BTPosition(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}
	
	/**
	 * @return Retorna o elemento armazenado nesta posicao
	 */
	public E getElement() {
		return element;
	}
	/**
	 * @param Define o elemento armazenado nesta posicao
	 */
	public void setElement(E element) {
		this.element = element;
	}
	/**
	 * @return Retorna o pai desta posicao
	 */
	public BTPosition<E> getParent() {
		return parent;
	}
	/**
	 * @param Define o pai desta posicao
	 */
	public void setParent(BTPosition<E> parent) {
		this.parent = parent;
	}
	/**
	 * @return Retorna o filho da esquerda desta posicao
	 */
	public BTPosition<E> getLeft() {
		return left;
	}
	/**
	 * @param Define o filho da esquerda desta posicao
	 */
	public void setLeft(BTPosition<E> left) {
		this.left = left;
	}
	/**
	 * @return Retorna o filho da direita desta posicao
	 */
	public BTPosition<E> getRight() {
		return right;
	}
	/**
	 * @param Define o filho da direita desta posicao
	 */
	public void setRight(BTPosition<E> right) {
		this.right = right;
	}
	

}

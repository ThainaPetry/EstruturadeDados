package br.univille.estd.binarytree;

/**
 * Implementacao de uma Ã¡rvore binÃ¡ria usando estrutura encadeada
 * @author leandersonandre
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> {
	
	protected BTPosition<E> root; // Referencia para a raiz
	protected int size;           // Numero de nodos
	
	/**
	 * Construtor de uma Ã¡rvore vazia
	 */
	public LinkedBinaryTree() {
		root = null; // inicia com uma árvore vazia
		size = 0;
	}
	
	/**
	 * Retorna o numero de nodos da árvore
	 * @return
	 */
	public int size() {
		return size;
	}
	
	private void checkIsPositionIsValid(BTPosition<E> v) throws InvalidPositionException{
		if(v == null) throw new InvalidPositionException("");
		
	}
	
	/**
	 * Retorna se um nodo é interno
	 */
	public boolean isInternal(BTPosition<E> v) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		return hasLeft(v) || hasRight(v);
	}
	
	
	/**
	 * Retorna se um nodo é externo
	 */
	public boolean isExternal(BTPosition<E> v) throws InvalidPositionException{
		return !isInternal(v);
	}
	
	/**
	 * Retorna se um nodo é a raiz
	 */
	public boolean isRoot(BTPosition<E> v) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		return v == root;
	}
	
	/**
	 * Retorna se um nodo tem o filho da esquerda
	 */
	public boolean hasLeft(BTPosition<E> v) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		return v.getLeft() != null;
	}
	
	/**
	 * Retorna se um nodo tem o filho da direita
	 */
	public boolean hasRight(BTPosition<E> v) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		return v.getRight() != null;
	}
	
	/**
	 * Retorna a raiz da árvore
	 */
	public BTPosition<E> root() throws EmptyTreeException{
		if(size() < 1) throw new EmptyTreeException("");
		return root;
	}
	
	/**
	 * Retorna o filho da esquerda de um nodo
	 * Lança BoundaryViolationException se nao tiver filho da esquerda
	 */
	public BTPosition<E> left(BTPosition<E> v) throws InvalidPositionException, BoundaryViolationException{
		checkIsPositionIsValid(v);
		if(!hasLeft(v)) throw new BoundaryViolationException("Nao possui filho esquerda");
		return v.getLeft();
	}
	
	/**
	 * Retorna o filho da direita de um nodo
	 * Lança BoundaryViolationException se nao tiver filho da direita
	 */
	public BTPosition<E> right(BTPosition<E> v) throws InvalidPositionException, BoundaryViolationException{
		checkIsPositionIsValid(v);
		if(!hasRight(v)) throw new BoundaryViolationException("Nao possui filho direita");
		return v.getRight();
	}
	
	/**
	 * Retorna o pai de um nodo
	 * Lança BoundaryViolationException se nao tiver pai
	 */
	public BTPosition<E> parent(BTPosition<E> v) throws InvalidPositionException, BoundaryViolationException{
		checkIsPositionIsValid(v);
		if(v.getParent() == null) throw new BoundaryViolationException("Posicao nao possui pai");
		return v.getParent();
	}
	
	/**
	 * Substitui o elemento armazenado no nodo
	 * Retorna o elemento substituido
	 */
	public E replace(BTPosition<E> v, E o) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		E replaced = v.getElement();
		v.setElement(o);
		return replaced;
	}
	
	/**
	 * Retorna o irmao de um nodo
	 * Lança BoundaryViolationException se nao tiver um irmao
	 */
	public BTPosition<E> sibling(BTPosition<E> v) throws InvalidPositionException, BoundaryViolationException{
		checkIsPositionIsValid(v);
		if(v.getParent() == null) throw new BoundaryViolationException("Posicao nao tem pai");
		BTPosition<E> parent = parent(v);
		if(!(hasLeft(parent) && hasRight(parent))) throw new BoundaryViolationException("Posicao nao tem dois filhos");
		BTPosition<E> sibling = null;
		if(left(parent) == v) {
			sibling = right(parent);
		}else {
			sibling = left(parent);
		}
		return sibling;
	}
	
	/**
	 * Insere a raiz em uma arvore vazia
	 */
	public BTPosition<E> addRoot(E e) throws NonEmptyTreeException{
		if(size() > 0) throw new NonEmptyTreeException("");
		root = createNode(e	, null, null, null);
		size++;
		return root;
	}
	
	/**
	 * Insere o filho da esquerda em um nodo
	 */
	public BTPosition<E> insertLeft(BTPosition<E> v, E e) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		if(hasLeft(v)) throw new InvalidPositionException("Posicao ja possui filho da esquerda");
		v.setLeft(createNode(e, v, null, null));
		size++;
		return v.getLeft();
	}
	
	/**
	 * Insere o filho da direita em um nodo
	 */
	public BTPosition<E> insertRight(BTPosition<E> v, E e) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		if(hasRight(v)) throw new InvalidPositionException("Posicao ja possui filho da direita");
		v.setRight(createNode(e, v, null, null));
		size++;
		return v.getRight();
	}
	
	/**
	 * Remove um nodo com zero ou um filho
	 * Nao pode remover um nodo com dois filhos. Deve lancar InvalidPositionException
	 */
	public E remove(BTPosition<E> v) throws InvalidPositionException{
		checkIsPositionIsValid(v);
		if(hasLeft(v) && hasRight(v)) throw new InvalidPositionException("Posicao tem dois filhos");
		E removed = v.getElement();
		if(isRoot(v)) {
			// EH O ROOT
			if(isExternal(v)) {
				root = null;
			}else {
				// Descobrir em qual lado esta o filho
				if(hasLeft(v)) {
					// v esta na esquerda
					root = v.getLeft();
				}else {
					// v esta na direita
					root = v.getRight();
				}
				// Remove o pai do root.
				root.setParent(null);
			}
		}else{
			BTPosition<E> parent = parent(v);
			// NAO EH O ROOT
			if(isExternal(v)) {
				// Descobrir em qual lado esta o filho
				if(hasLeft(parent)) {
					// v esta na esquerda
					parent.setLeft(null);
				}else {
					// v esta na direita
					parent.setRight(null);
				}
			}else {
				// Verifica se tem o filho da esquerda
				// e o filho da esquerda é  V, onde v é a posicao que será removida
				if(hasLeft(parent) && left(parent) == v) {
					// v esta na esquerda
					
					// Verificar onde esta o filho de v
					if(hasLeft(v)) {
						// filho de v na esquerda
						parent.setLeft(v.getLeft());
						parent.getLeft().setParent(parent);
					}else {
						// filho de v na direita
						parent.setLeft(v.getRight());
					}
					// atualiza o pai do novo filho da esquerda
					parent.getLeft().setParent(parent);
				}else {
					// 	v esta na direita
					
					// Verificar onde esta o filho de v
					if(hasLeft(v)) {
						// filho de v na esquerda
						parent.setRight(v.getLeft());
					}else {
						// filho de v na direita
						parent.setRight(v.getRight());
					}
					// atualiza o pai do novo filho da direita
					parent.getRight().setParent(parent);
				}
				
			}
		}
		size--;
		return removed;
	}
	
	/**
	 *  Cria um novo nodo para a arvore binaria
	 */
	protected BTPosition<E> createNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		return new BTPosition<E>(element,parent,left,right);
	}

}
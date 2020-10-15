package br.univille.estd.binarytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class LinkedBinaryTreeTests {
	
	/**
	 * Criar um novo nodo para uma arvore binaria
	 */
	@Test
	public void createNode() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> parent = new BTPosition<>(1);
		BTPosition<Integer> left = new BTPosition<>(2);
		BTPosition<Integer> right = new BTPosition<>(3);
		
		BTPosition<Integer> node = tree.createNode(4, parent, left, right);

		assertEquals("Criacao de um nodo para arvore binaria.", node.getElement(), new Integer(4));
		assertEquals("Parent do nodo.", parent, node.getParent() );
		assertEquals("Filho da esquerda.", left, node.getLeft() );
		assertEquals("Filho da direita.", right, node.getRight() );
	}
	
	/**
	 * Adicionar a raiz da arvore binaria
	 */
	@Test
	public void raizDaArvore() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		try {
			assertEquals("Tamanho da arvore binaria vazia.", tree.size(), 0);
			tree.root();
			fail("Arvore vazia");
		}catch(EmptyTreeException e) {
			
		}
		
		BTPosition<Integer> root = tree.addRoot(10);

		assertEquals("Raiz da arvore binaria.", new Integer(10), root.getElement() );
		assertEquals("Raiz da arvore binaria.", new Integer(10), tree.root().getElement() );
		assertEquals("Tamanho da arvore binaria.", 1, tree.size());
		
		try {
			tree.addRoot(11);
			fail("Arvore ja possui raiz");
		}catch(NonEmptyTreeException e) {
			
		}
		
	}
	
	/**
	 * Adicionar filho da esquerda
	 */
	@Test
	public void adicionarFilhoDaEsquerda() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		
		try {
			tree.left(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		try {
			assertEquals("Raiz nao tem filho da esquerda.", false, tree.hasLeft(root) );
			tree.left(root);
			fail("Raiz nao tem filho da esquerda");
		}catch(BoundaryViolationException e) {
			
		}

		BTPosition<Integer> left = tree.insertLeft(root,11);
		
		assertEquals("Raiz da arvore binaria.",  new Integer(11), left.getElement());
		assertEquals("Raiz  tem filho da esquerda.",true, tree.hasLeft(root));
		assertEquals("Filho da esquerda.", left, tree.left(root) );
		assertEquals("Tamanho da arvore binaria.", 2, tree.size());
		
	}
	
	/**
	 * Adicionar filho da direita
	 */
	@Test
	public void adicionarFilhoDaDireita() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		
		try {
			tree.right(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		try {
			assertEquals("Raiz nao tem filho da direita.",false, tree.hasRight(root));
			tree.right(root);
			fail("Raiz nao tem filho da direita");
		}catch(BoundaryViolationException e) {
			
		}
		
		BTPosition<Integer> right = tree.insertRight(root,11);

		assertEquals("Raiz da arvore binaria.", new Integer(11), right.getElement() );
		assertEquals("Raiz  tem filho da direita.", true, tree.hasRight(root) );
		assertEquals("Filho da direita.", right, tree.right(root) );
		assertEquals("Tamanho da arvore binaria.", 2, tree.size() );
		
	}
	
	/**
	 * Verifica se o nodo e a raiz da arvore binaria
	 */
	@Test
	public void verificaSeONodoEARaiz() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		BTPosition<Integer> other = tree.createNode(11, null, null, null);
		
		try {
			tree.isRoot(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}

		assertEquals("Nodo nao e a raiz da arvore binaria.", false, tree.isRoot(other) );
		assertEquals("Nodo e a raiz da arvore binaria.", true, tree.isRoot(root));
	}
	
	/**
	 * Retorna o pai de um nodo da arvore binaria
	 */
	@Test
	public void retornaOPaiDeUmNodo() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		BTPosition<Integer> left = tree.insertLeft(root,10);
		
		try {
			tree.parent(root);
			fail("Raiz nao tem pai");
		}catch(BoundaryViolationException e) {
			
		}
		
		try {
			tree.parent(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}

		assertEquals("Pai do nodo.", tree.parent(left), root);
	}
	
	/**
	 * Verifica se o nodo e interno ou externo
	 */
	@Test
	public void externoOuInterno() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		BTPosition<Integer> left = tree.insertLeft(root,9);
		BTPosition<Integer> right = tree.insertRight(root,11);
		

		try {
			tree.isInternal(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		

		try {
			tree.isExternal(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		

		assertEquals("nodo e externo.", true, tree.isExternal(left));
		assertEquals("nodo nao e externo.", false, tree.isExternal(root));
		assertEquals("nodo e interno.", true, tree.isInternal(root));
		assertEquals("nodo nao e interno.", false, tree.isInternal(left));
	}
		
	
	/**
	 * Substitui o elemento do nodo e retorna o valor substituido
	 */
	@Test
	public void substituiOElement() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		
		
		
		try {
			tree.replace(null,1);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		
		Integer substituido = tree.replace(root,9);

		assertEquals("Valor substituido do nodo.", new Integer(10)	, substituido);
		assertEquals("Novo Valor do nodo.", new Integer(9)	, tree.root().getElement());
	}
	
	/**
	 * Retorna o irmao do nodo
	 */
	@Test
	public void buscaOIrmaoDoNodo() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		BTPosition<Integer> left = tree.insertLeft(root,9);
		
		try {
			tree.sibling(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		
		try {
			tree.sibling(left);
			fail("Nodo nao possui irmao");
		}catch(BoundaryViolationException e) {
			
		}
		
		LinkedBinaryTree<Integer> tree2 = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root2 = tree2.addRoot(10);
		BTPosition<Integer> right = tree2.insertRight(root2,10);
		try {
			tree2.sibling(right);
			fail("Nodo nao possui irmao");
		}catch(BoundaryViolationException e) {
			
		}
		
		BTPosition<Integer> left2 = tree2.insertLeft(root2,11);

		assertEquals("Irmao da direita.", right	, tree2.sibling(left2));
		assertEquals("Irmao da esquerda.", left2	, tree2.sibling(right));
		
	}
	
	
	/**
	 * Retorna o irmao do nodo
	 */
	@Test
	public void removeUmNodo() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		
		BTPosition<Integer> root = tree.addRoot(10);
		BTPosition<Integer> left = tree.insertLeft(root,9);
		BTPosition<Integer> right = tree.insertRight(root,11);
		
		
		try {
			tree.remove(null);
			fail("Nodo invalido");
		}catch(InvalidPositionException e) {
			
		}
		
		try {
			tree.remove(root);
			fail("Nodo com dois filhos nao pode ser removido");
		}catch(InvalidPositionException e) {
			
		}
		
		BTPosition<Integer> right2 = tree.insertRight(right,12);
		assertEquals("Tamanho da arvore.", 4	, tree.size());
		assertEquals("Remove um nodo com 1 filho na direita.", new Integer(11)	, tree.remove(right));
		assertEquals("Novo nodo da direita.", new Integer(12)	, tree.root().getRight().getElement());
		assertEquals("Tamanho da arvore.", 3	, tree.size());

		
		
		BTPosition<Integer> left2 = tree.insertLeft(left,8);
		assertEquals("Tamanho da arvore.", 4	, tree.size());
		assertEquals("Remove um nodo com 1 filho na esquerda.", new Integer(9)	, tree.remove(left));
		assertEquals("Novo nodo da esquerda.", new Integer(8)	, tree.root().getLeft().getElement());
		assertEquals("Tamanho da arvore.", 3	, tree.size());
		
		// arvore 
		//   10
		// 8   12
		System.out.println("root "+tree.root());
		System.out.println("left "+tree.root().getLeft());
		System.out.println("right "+tree.root().getRight());
		
		assertEquals("Remove um nodo com nenhum filho .", new Integer(8)	, tree.remove(tree.root().getLeft()));
		assertEquals("Tamanho da arvore.", 2	, tree.size());

		
		assertEquals("Remove a raiz da arvore .", new Integer(10)	, tree.remove(tree.root()));
		assertEquals("Nova raiz .", new Integer(12)	, tree.root().getElement());
		assertEquals("Tamanho da arvore.", 1	, tree.size());
		
	}
	

}

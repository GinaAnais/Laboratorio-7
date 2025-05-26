package Ejercicio3;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;
import bstreeInterface.BinarySearchTree;
import Ejercicio1.Node;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private Node<E> root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node<E> insertRec(Node<E> node, E data) throws ItemDuplicated {
        if (node == null) return new Node<>(data);
        int cmp = data.compareTo(node.getData());
        if (cmp == 0) throw new ItemDuplicated();
        if (cmp < 0) node.setLeft(insertRec(node.getLeft(), data));
        else node.setRight(insertRec(node.getRight(), data));
        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        return searchRec(root, data);
    }

    private E searchRec(Node<E> node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound();
        int cmp = data.compareTo(node.getData());
        if (cmp == 0) return node.getData();
        if (cmp < 0) return searchRec(node.getLeft(), data);
        else return searchRec(node.getRight(), data);
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) throw new ExceptionIsEmpty();
        root = deleteRec(root, data);
    }

    private Node<E> deleteRec(Node<E> node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.getData());
        if (cmp < 0) node.setLeft(deleteRec(node.getLeft(), data));
        else if (cmp > 0) node.setRight(deleteRec(node.getRight(), data));
        else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();
            Node<E> min = findMinNode(node.getRight());
            node.setData(min.getData());
            node.setRight(deleteRec(node.getRight(), min.getData()));
        }
        return node;
    }

    private Node<E> findMinNode(Node<E> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    private Node<E> findNode(Node<E> node, E x) {
        while (node != null) {
            int cmp = x.compareTo(node.getData());
            if (cmp == 0) return node;
            else if (cmp < 0) node = node.getLeft();
            else node = node.getRight();
        }
        return null;
    }

    // elimina todos los nodos de un BST
    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) throw new ExceptionIsEmpty();
        root = null;
    }

    // Retorna y cuenta el número de nodos 
    public int countAllNodes() {
        return countAllNodesRec(root);
    }

    private int countAllNodesRec(Node<E> node) {
        if (node == null) return 0;
        return 1 + countAllNodesRec(node.getLeft()) + countAllNodesRec(node.getRight());
    }

    // Retorna solo los nodos 
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node<E> node) {
        if (node == null || (node.getLeft() == null && node.getRight() == null)) return 0;
        return 1 + countNodesRec(node.getLeft()) + countNodesRec(node.getRight());
    }

    // Retorna la altura del subárbol
    public int height(E x) {
        Node<E> start = findNode(root, x);
        if (start == null) return -1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(start);
        int height = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                Node<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }

        return height;
    }

    // Retorna la anchura de todo el árbol 
    public int amplitude(int level) {
        if (root == null || level < 0) return 0;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currentLevel == level) return size;
            for (int i = 0; i < size; i++) {
                Node<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
            currentLevel++;
        }
        return 0;
    }
    
    // Devuelve el número de hojas del árbol
    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(Node<E> node) {
        if (node == null) return 0;
        if (node.getLeft() == null && node.getRight() == null) return 1;
        return countLeavesRec(node.getLeft()) + countLeavesRec(node.getRight());
    }

    // Devuelve la altura del árbol desde la raíz
    public int treeHeight() {
        return treeHeightRec(root);
    }

    private int treeHeightRec(Node<E> node) {
        if (node == null) return -1;
        int leftHeight = treeHeightRec(node.getLeft());
        int rightHeight = treeHeightRec(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Calcula el área del árbol
    public int areaBST() {
        return countLeaves() * treeHeight();
    }
    
    public void drawBST() {
        drawBSTRec(root, "", true);
    }

    private void drawBSTRec(Node<E> node, String prefix, boolean isTail) {
        if (node == null) return;

        if (node.getRight() != null) {
            drawBSTRec(node.getRight(), prefix + (isTail ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.toString());

        if (node.getLeft() != null) {
            drawBSTRec(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
        }
    }
    
    public void parenthesize() {
        parenthesizeRec(root, 0); // Inicia impresión desde la raíz con nivel 0
    }

    private void parenthesizeRec(Node<E> node, int level) {
        if (node == null) return; // Caso base: nodo vacío no imprime nada

        for (int i = 0; i < level; i++) System.out.print("    "); // Imprime sangría según nivel
        System.out.print(node.getData()); // Imprime dato del nodo

        boolean hasChildren = node.getLeft() != null || node.getRight() != null; // Verifica si tiene hijos

        if (hasChildren) {
            System.out.println(" ("); // Abre paréntesis si hay hijos
            if (node.getLeft() != null) parenthesizeRec(node.getLeft(), level + 1); // Procesa hijo izquierdo
            if (node.getRight() != null) parenthesizeRec(node.getRight(), level + 1); // Procesa hijo derecho
            for (int i = 0; i < level; i++) System.out.print("    "); // Sangría para cerrar paréntesis
            System.out.println(")"); // Cierra paréntesis del nodo
        } else {
            System.out.println(); // Si no tiene hijos, solo salto de línea
        }
    }



}

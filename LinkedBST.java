package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;
import Exceptions.ExceptionIsEmpty;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private Node<E> root;

    public LinkedBST() {
        this.root = null;
    }

    // Agregar elemento validando duplicado
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node<E> insertRec(Node<E> node, E data) throws ItemDuplicated {
        if (node == null) return new Node<>(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = insertRec(node.left, data);
        else if (cmp > 0) node.right = insertRec(node.right, data);
        else throw new ItemDuplicated("Elemento duplicado: " + data);
        return node;
    }

    // Buscar elemento validando si fue encontrado
    public E search(E data) throws ItemNotFound {
        return searchRec(root, data);
    }

    private E searchRec(Node<E> node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("No se encontró: " + data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) return searchRec(node.left, data);
        else if (cmp > 0) return searchRec(node.right, data);
        else return node.data;
    }

    // Eliminar elemento validando si árbol está vacío
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) throw new ExceptionIsEmpty("Árbol vacío");
        root = deleteRec(root, data);
    }

    private Node<E> deleteRec(Node<E> node, E data) {
        if (node == null) return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = deleteRec(node.left, data);
        else if (cmp > 0) node.right = deleteRec(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<E> min = findMinNode(node.right);
            node.data = min.data;
            node.right = deleteRec(node.right, min.data);
        }
        return node;
    }

    // Mostrar árbol in-order como cadena
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }

    private void inOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.data).append(" ");
            inOrder(node.right, sb);
        }
    }

    private void preOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    private void postOrder(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    public String printPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public String printPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString();
    }

    private Node<E> findMinNode(Node<E> node) {
        if (node == null) return null;
        while (node.left != null) node = node.left;
        return node;
    }

    public E findMin() throws ItemNotFound {
        Node<E> min = findMinNode(root);
        if (min == null) throw new ItemNotFound("No se encontró el mínimo.");
        return min.data;
    }

    private Node<E> findMaxNode(Node<E> node) {
        if (node == null) return null;
        while (node.right != null) node = node.right;
        return node;
    }

    public E findMax() throws ItemNotFound {
        Node<E> max = findMaxNode(root);
        if (max == null) throw new ItemNotFound("No se encontró el máximo.");
        return max.data;
    }
}


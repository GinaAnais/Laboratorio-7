package Test;

import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.*;

public class Main {
    public static void main(String[] args) {
        try {
            LinkedBST<Integer> tree = new LinkedBST<>();
            tree.insert(400);
            tree.insert(100);
            tree.insert(50);
            tree.insert(75);
            tree.insert(200);
            tree.insert(700);


            System.out.println("In-Orden: " + tree.toString());
            System.out.println("Pre-Orden: " + tree.printPreOrder());
            System.out.println("Post-Orden: " + tree.printPostOrder());
            System.out.println("Mínimo: " + tree.findMin());
            System.out.println("Máximo: " + tree.findMax());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

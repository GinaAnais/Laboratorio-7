package Ejercicio1;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class Main01 {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            bst.insert(400);
            bst.insert(100);
            bst.insert(50);
            bst.insert(75);
            bst.insert(200);
            bst.insert(700);

            System.out.println("Todos los nodos: " + bst.countAllNodes()); 
            System.out.println("Nodos no-hojas: " + bst.countNodes());    
            System.out.println("Altura del subárbol : " + bst.height(400)); 
            System.out.println("Amplitud nivel: " + bst.amplitude(0));   
            System.out.println("Amplitud nivel: " + bst.amplitude(1));
            System.out.println("Amplitud nivel: " + bst.amplitude(2));
            System.out.println("Amplitud nivel: " + bst.amplitude(3));
            
            bst.destroyNodes();
            System.out.println("Después de destruir, nodos: " + bst.countAllNodes()); // 0

        } catch (ItemDuplicated |  ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

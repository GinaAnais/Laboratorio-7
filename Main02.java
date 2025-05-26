package Ejercicio2;

public class Main02 {
    public static boolean sameArea(LinkedBST<?> bst1, LinkedBST<?> bst2) {
        return bst1.areaBST() == bst2.areaBST();
    }

    public static void main(String[] args) throws Exception {
        LinkedBST<Integer> t1 = new LinkedBST<>();
        LinkedBST<Integer> t2 = new LinkedBST<>();

        t1.insert(10);
        t1.insert(5);
        t1.insert(15);

        t2.insert(20);
        t2.insert(10);
        t2.insert(30);

        System.out.println("Área árbol 1: " + t1.areaBST());
        System.out.println("Área árbol 2: " + t2.areaBST());
        System.out.println("¿Tienen la misma área?: " + sameArea(t1, t2));

        System.out.println("Árbol 1:");
        t1.drawBST();

        System.out.println("Árbol 2:");
        t2.drawBST();
    }
}

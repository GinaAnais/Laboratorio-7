package Ejercicio3;

public class Main03 {
    public static void main(String[] args) throws Exception {
        LinkedBST<String> tree = new LinkedBST<>();
        tree.insert("Sales");
        tree.insert("Domestic");
        tree.insert("International");
        tree.insert("Canada");
        tree.insert("S. America");
        tree.insert("Overseas");
        tree.insert("Africa");
        tree.insert("Europe");
        tree.insert("Asia");
        tree.insert("Australia");

        System.out.println("Visualización:");
        tree.parenthesize();
    }
}

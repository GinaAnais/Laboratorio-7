package Exceptions;

public class ItemNotFound extends Exception {
    public ItemNotFound(String msg) {
        super(msg);
    }

    public ItemNotFound() {
        super("El elemento no fue encontrado.");
    }
}

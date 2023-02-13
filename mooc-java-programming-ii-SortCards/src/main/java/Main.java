package main.java;

public class Main {

    public static void main(String[] args) {
        // test your code here

        BySuitInValueOrder comparator = new BySuitInValueOrder();
        Card first = new Card(4, Suit.SPADE);
        Card second = new Card(2, Suit.HEART);
        System.out.println(comparator.compare(first, second));
    }
}
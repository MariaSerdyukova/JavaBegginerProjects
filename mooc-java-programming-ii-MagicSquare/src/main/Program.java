package main;

public class Program {

    public static void main(String[] args) {
        // Test the MagicSquare class here

        //MagicSquareFactory msFactory = new MagicSquareFactory();
        //System.out.println(msFactory.createMagicSquare(5));
        MagicSquareFactory magic = new MagicSquareFactory();

        System.out.println(magic.createMagicSquare(3));
    }
}

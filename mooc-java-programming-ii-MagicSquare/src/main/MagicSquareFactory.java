package main;

public class MagicSquareFactory {

    private int previousNumber;

    public MagicSquare createMagicSquare(int size) {

        MagicSquare square = new MagicSquare(size);
        int targetOfValues = square.getHeight() * square.getWidth();
        int i = 0, j = square.getWidth() / 2;
        int countOfValues = 0;

        while (countOfValues < targetOfValues) {

            if (square.readValue(j, i) == 0) {

                square.placeValue(j, i, getNextNumber());
                countOfValues++;
            }

            int potentialNewI = i - 1;
            int potentialNewJ = j + 1;

            if (potentialNewI < 0) {
                potentialNewI = square.getHeight() - 1;
            }

            if (potentialNewJ == square.getWidth()) {
                potentialNewJ = 0;
            }

            if (square.readValue(potentialNewJ, potentialNewI) != 0) {
                i++;
                if (i >= square.getHeight()) {
                    i = 0;
                }
            } else {
                i = potentialNewI;
                j = potentialNewJ;
            }
        }

        // implement the creation of a magic square with the Siamese method algorithm here

        return square;
    }

    private int getNextNumber() {

        return this.previousNumber += 1;
    }
}

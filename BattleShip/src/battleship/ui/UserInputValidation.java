package battleship.ui;

import battleship.data.ShipType;

import static java.lang.Math.abs;

/**
 * todo Document type UserInputValidation
 */
public class UserInputValidation {

    private static boolean validateShipLength(int[] inputedCoordinates, int lengthOfShip) {
        return abs(inputedCoordinates[0] - inputedCoordinates[2]) == lengthOfShip - 1 ||
            abs(inputedCoordinates[1] - inputedCoordinates[3]) == lengthOfShip - 1;
    }

    private static boolean validateShipPositon(int[] inputedCoordinates) {
        return inputedCoordinates[1] - inputedCoordinates[3] == 0 ||
            inputedCoordinates[0] - inputedCoordinates[2] == 0;
    }

    private static boolean validateNearestShipPositon(int[] inputedCoordinates, String[][] battleShip) {
        int i = 0;
        int x1, y1, x2, y2;

        while (i < inputedCoordinates.length) {
            x1 = inputedCoordinates[i];
            y1 = inputedCoordinates[i + 1];
            x2 = inputedCoordinates[i + 2];
            y2 = inputedCoordinates[i + 3];

            if (x1 == x2) {
                if (y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                for (int k = y1; k <= y2; k++) {
                    if (x1 > 1 && battleShip[x1 - 1][k].equals("O ") ||
                        x1 < 10 && battleShip[x1 + 1][k].equals("O ") ||
                        k > 1 && battleShip[x1][k - 1].equals("O ") ||
                        k < 10 && battleShip[x1][k + 1].equals("O ") ||
                        battleShip[x1][k].equals("O ") ||
                        x1 > 1 && k > 1 && battleShip[x1 - 1][k - 1].equals("O ") ||
                        x1 < 10 && k > 1 && battleShip[x1 + 1][k - 1].equals("O ") ||
                        x1 > 1 && k < 10 && battleShip[x1 - 1][k + 1].equals("O ") ||
                        x1 < 10 && k < 10 && battleShip[x1 + 1][k + 1].equals("O ")) {

                        return false;
                    }
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                for (int m = x1; m <= x2; m++) {
                    if (m > 1 && battleShip[m - 1][y1].equals("O ") ||
                        m < 10 && battleShip[m + 1][y1].equals("O ") ||
                        y1 > 1 && battleShip[m][y1 - 1].equals("O ") ||
                        y1 < 10 && battleShip[m][y1 + 1].equals("O ") ||
                        battleShip[m][y1].equals("O ") ||
                        m > 1 && y1 > 1 && battleShip[m - 1][y1 - 1].equals("O ") ||
                        m < 10 && y1 > 1 && battleShip[m + 1][y1 - 1].equals("O ") ||
                        m > 1 && y1 < 10 && battleShip[m - 1][y1 + 1].equals("O ") ||
                        m < 10 && y1 < 10 && battleShip[m + 1][y1 + 1].equals("O ")) {
                        return false;
                    }
                }
            }

            i += 4;
        }
        return true;
    }

    public static boolean validate(int[] inputedCoordinates, ShipType shipType, String[][] battleShip) {
        if (!validateShipLength(inputedCoordinates, shipType.getLength())) {
            //Write error
            System.out.println("Error! Wrong length of the " + shipType.getName() + "! Try again:\n");
            return false;
        }
        if (!validateShipPositon(inputedCoordinates)) {
            //Write error
            System.out.println("Error! Wrong ship location! Try again:\n");
            return false;
        }
        if (!validateNearestShipPositon(inputedCoordinates, battleShip)) {
            //Write error
            System.out.println("Error! You placed it too close to another one. Try again:\n");
            return false;
        }

        return true;
    }

}

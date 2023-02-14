package battleship.logic;

import java.util.Arrays;

/**
 * todo Document type MapBuilder
 */
public class MapBuilder {
    public static int[] getCoordinatesFromInterval(String[] interval) {
        int[] result = new int[interval.length * 2];
        int index = 0;
        for (String value : interval) {
            result[index] = value.toCharArray()[0] - 'A' + 1;
            result[index + 1] = Integer.parseInt(value.substring(1));
            index += 2;
        }
        return result;
    }

    public static void placeShipOnTheMap(String[][] battleShip, int[] coordinates) {
        int i = 0;
        while (i < coordinates.length) {
            int x1, y1, x2, y2;
            x1 = coordinates[i];
            y1 = coordinates[i + 1];
            x2 = coordinates[i + 2];
            y2 = coordinates[i + 3];

            if (x1 == x2) {
                if (y1 > y2) {
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                for (int k = y1; k <= y2; k++) {
                    battleShip[x1][k] = "O ";
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                for (int m = x1; m <= x2; m++) {
                    battleShip[m][y1] = "O ";
                }
            }

            i += 4;
        }
    }

    public static String[][] replaceWithNumbers(String[][] battleShip) {
        char numberToPrint = '1';
        for (int i = 0; i < battleShip.length; i++) {
            for (int j = 0; j < battleShip[i].length; j++) {

                if (i == 0 && j == 0) {
                    battleShip[i][j] = "  ";
                } else if (i == 0 && j < 10) {
                    battleShip[i][j] = battleShip[i][j].replace('~', numberToPrint);
                    numberToPrint++;
                } else if (i == 0) {
                    battleShip[i][j] = "10";
                }
            }
        }
        return battleShip;
    }


    public static String[][] fillBattleShip() {
        String[][] battleField = new String[11][11];
        for (String[] strings : battleField) {
            Arrays.fill(strings, "~ ");
        }
        return battleField;
    }

    public static String[][] replaceWithLetters(String[][] battleShip) {
        char charToPrint = 'A';
        for (int i = 0; i < battleShip.length; i++) {
            for (int j = 0; j < battleShip[i].length; j++) {
                if (j == 0 && i > 0) {
                    battleShip[i][j] = battleShip[i][j].replace('~', charToPrint);
                    charToPrint++;
                }
            }
        }
        return battleShip;
    }
}

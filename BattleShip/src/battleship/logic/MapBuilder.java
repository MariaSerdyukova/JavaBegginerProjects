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

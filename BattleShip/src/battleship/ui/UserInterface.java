package battleship.ui;

import battleship.logic.ShipType;

import java.util.Arrays;
import java.util.Scanner;

import static battleship.logic.MapBuilder.getCoordinatesFromInterval;
import static battleship.logic.MapBuilder.placeShipOnTheMap;

/**
 * todo Document type UserInterface
 */
public class UserInterface {

    public static void start() {
        String[][] battleField = replaceWithNumbers(replaceWithLetters(fillBattleShip()));
        printBattleShipMap(battleField);

        askForCoordinates(ShipType.AIRCRAFT_CARRIER.getName(), ShipType.AIRCRAFT_CARRIER.getLength());
        placeShipOnTheMap(battleField, getCoordinatesFromInterval(readInputFromUser()));
        printBattleShipMap(battleField);

        askForCoordinates(ShipType.BATTLESHIP.getName(), ShipType.BATTLESHIP.getLength());
        placeShipOnTheMap(battleField, getCoordinatesFromInterval(readInputFromUser()));
        printBattleShipMap(battleField);

        askForCoordinates(ShipType.SUBMARINE.getName(), ShipType.SUBMARINE.getLength());
        placeShipOnTheMap(battleField, getCoordinatesFromInterval(readInputFromUser()));
        printBattleShipMap(battleField);

        askForCoordinates(ShipType.CRUISER.getName(), ShipType.CRUISER.getLength());
        placeShipOnTheMap(battleField, getCoordinatesFromInterval(readInputFromUser()));
        printBattleShipMap(battleField);

        askForCoordinates(ShipType.DESTROYER.getName(), ShipType.DESTROYER.getLength());
        placeShipOnTheMap(battleField, getCoordinatesFromInterval(readInputFromUser()));
        printBattleShipMap(battleField);
    }

    public static void printBattleShipMap(String[][] battleField) {
        for (String[] strings : battleField) {
            for (int j = 0; j < strings.length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }

    public static void askForCoordinates(String nameOfTheShip, int shipLength) {
        System.out.println("Enter the coordinates of the " + nameOfTheShip + " (" + shipLength + " cells):");
    }

    public static String[] readInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine().split(" ");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return new String[0];
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

package battleship.ui;

import battleship.data.ShipType;

import java.util.Arrays;
import java.util.Scanner;

import static battleship.logic.MapBuilder.*;
import static battleship.logic.Shooter.shootUntilTheEnd;
import static java.lang.Math.abs;

/**
 * todo Document type UserInterface
 */
public class UserInterface {

    public static void start() {
        String[][] battleField = replaceWithNumbers(replaceWithLetters(fillBattleShip()));
        String[][] battleFieldWithFog = replaceWithNumbers(replaceWithLetters(fillBattleShip()));

        printBattleShipMap(battleField);
        ShipType[] shipTypes = ShipType.values();

        for (int i = 0; i < shipTypes.length; ) {
            askForCoordinates(shipTypes[i].getName(), shipTypes[i].getLength());
            int[] inputedCoordinates = getCoordinatesFromInterval(readInputFromUser());
            if (UserInputValidation.validate(inputedCoordinates, shipTypes[i], battleField)) {
                placeShipOnTheMap(battleField, inputedCoordinates);
                printBattleShipMap(battleField);
                i++;
            }
        }

        System.out.println("The game starts!");
        printBattleShipMap(battleFieldWithFog);
        System.out.println("Take a shot!");
        shootUntilTheEnd(battleField, battleFieldWithFog);
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public static void printBattleShipMap(String[][] battleField) {
        for (String[] strings : battleField) {
            for (String string : strings) {
                System.out.print(string);
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
}

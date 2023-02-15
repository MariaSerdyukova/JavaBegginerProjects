package battleship.ui;

import battleship.data.PlayerMap;
import battleship.data.ShipType;
import battleship.logic.Shooter;

import java.util.Arrays;
import java.util.Scanner;

import static battleship.logic.MapBuilder.*;

/**
 * todo Document type UserInterface
 */
public class UserInterface {
    final static Scanner scanner = new Scanner(System.in);

    public static void startGame() {
        PlayerMap firstPlayer = new PlayerMap("Player 1");
        PlayerMap secondPlayer = new PlayerMap("Player 2");

        System.out.println(firstPlayer.getPlayerName() + ", place your ships on the game field");
        enterPlayerShips(firstPlayer);
        System.out.print("Press Enter and pass the move to another player");
        if (scanner.nextLine().equals("")) {
            System.out.println("...");

            System.out.println(secondPlayer.getPlayerName() + ", place your ships on the game field");
            enterPlayerShips(secondPlayer);

            var result = Shooter.shootUntilTheEnd(firstPlayer, secondPlayer);
            System.out.println("You sank the last ship. You won. Congratulations!");
        }

    }

    public static void enterPlayerShips(PlayerMap playerMap) {
        System.out.println(playerMap.toString());
        ShipType[] shipTypes = ShipType.values();

        for (int i = 0; i < shipTypes.length; ) {
            askForCoordinates(shipTypes[i].getName(), shipTypes[i].getLength());
            int[] inputedCoordinates = getCoordinatesFromInterval(readInputFromUser());
            if (UserInputValidation.validate(inputedCoordinates, shipTypes[i], playerMap)) {
                playerMap.placeShipOnTheMap(inputedCoordinates);
                System.out.println(playerMap.toString());
                i++;
            }
        }
    }

    public static void askForCoordinates(String nameOfTheShip, int shipLength) {
        System.out.println("Enter the coordinates of the " + nameOfTheShip + " (" + shipLength + " cells):");
    }

    public static String[] readInputFromUser() {
        try {
            return scanner.nextLine().split(" ");
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
            return new String[0];
        }
    }
}

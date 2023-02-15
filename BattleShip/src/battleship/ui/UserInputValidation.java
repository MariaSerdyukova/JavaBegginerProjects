package battleship.ui;

import battleship.data.PlayerMap;
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


    public static boolean validate(int[] inputedCoordinates, ShipType shipType, PlayerMap playerMap) {
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
        if (!playerMap.isShipPlacementAllowed(inputedCoordinates)) {
            //Write error
            System.out.println("Error! You placed it too close to another one. Try again:\n");
            return false;
        }

        return true;
    }

}
